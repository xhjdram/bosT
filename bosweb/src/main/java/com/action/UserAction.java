package com.action;
import com.MD5Utils;
import com.domain.User;
import com.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }


    @Resource
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String loginAction() {
        t.getUsername();
//        验证码
        HttpSession session = ServletActionContext.getRequest().getSession();
        String key = (String) session.getAttribute("key");
        if (StringUtils.isNotBlank(checkcode) && checkcode.equals(key)) {
            //获得当前 user
            Subject subject = SecurityUtils.getSubject();
            //令牌
            AuthenticationToken authenticationToken = new UsernamePasswordToken(t.getUsername(), MD5Utils.md5(t.getPassword()));
            try{
                subject.login(authenticationToken);
                User user = (User) subject.getPrincipal();
                ServletActionContext.getRequest().getSession().setAttribute("user", user);
            }catch (Exception e){
                e.printStackTrace();
                this.addActionError("用户名或密码错误");
                return LOGIN;
            }
            return "index";
//            User user = userService.getUserByPassWordAndUserName(t);
//            if (user == null) {
//                this.addActionError("用户名或密码错误");
//                return LOGIN;
//            } else {
//                ServletActionContext.getRequest().getSession().setAttribute("user", user);
//                return "index";
//            }
//
//        }
        }else {
            this.addActionError("验证码错误");
            return LOGIN;
        }

    }

    public String editPassword(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        String password = t.getPassword();
        user.setPassword(password);
        int flag= userService.updateUser(user);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        if(flag==1){
            try {
                ServletActionContext.getResponse().getWriter().write("1");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                ServletActionContext.getResponse().getWriter().write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return NONE;
    }
    public String exitAction(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.invalidate();
        return LOGIN;
    }

}
