package com.action;

import com.MD5Utils;
import com.domain.PageBean;
import com.domain.User;
import com.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
    //顶一个数组用来接收所有的角色Id
    private String[] roleIds;
    private String checkcode;
    @Resource
    private UserService userService;
    //定义当前页
    private Integer page;
    //定义每页显示的行数
    private Integer rows;

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String loginAction() {

//        验证码
        HttpSession session = ServletActionContext.getRequest().getSession();
        String key = (String) session.getAttribute("key");
        if (StringUtils.isNotBlank(checkcode) && checkcode.equals(key)) {
            //获得当前 user
            Subject subject = SecurityUtils.getSubject();
            //令牌
            AuthenticationToken authenticationToken = new UsernamePasswordToken(t.getUsername(), MD5Utils.md5(t.getPassword()));
            try {
                subject.login(authenticationToken);
                User user = (User) subject.getPrincipal();
                ServletActionContext.getRequest().getSession().setAttribute("user", user);
            } catch (Exception e) {
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
        } else {
            this.addActionError("验证码错误");
            return LOGIN;
        }

    }

    public String editPassword() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        String password = t.getPassword();
        user.setPassword(password);
        int flag = userService.updateUser(user);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        if (flag == 1) {
            try {
                ServletActionContext.getResponse().getWriter().write("1");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ServletActionContext.getResponse().getWriter().write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return NONE;
    }

    public String exitAction() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.invalidate();
        return LOGIN;
    }

    //保存用户
    public String save() {
        userService.save(roleIds, t);
        return "lsit";
    }

    //分页查询
    public String listUser() {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        pageBean.setDetachedCriteria(detachedCriteria);
        userService.pageQuery(pageBean);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"set"});
        String s = JSONObject.fromObject(pageBean, jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

}
