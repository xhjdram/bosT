package com.serviceimpl;

import com.FunctionDao;
import com.UserDao;
import com.domain.Function;
import com.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BOSRealm extends AuthorizingRealm {
    @Autowired
    UserDao userDao;
    @Autowired
    FunctionDao functionDao;

    //授权方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前用户
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<Function> list =new ArrayList<>();
        if (user != null && user.getUsername().equals("xuan")) {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
             list = functionDao.findEntityByDe(detachedCriteria);
        }else {
           list=functionDao.findFunctonsByUserId(user.getId());
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for(Function function:list){
            authorizationInfo.addStringPermission(function.getCode());
        }
        return authorizationInfo;
    }

    //认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("realm中的认证方法执行了。。。。");
        UsernamePasswordToken mytoken = (UsernamePasswordToken) authenticationToken;
        String username = mytoken.getUsername();
        //根据用户名查询数据库中的密码
        User user = userDao.findUserByUserName(username);
        if (user == null) {
            //用户名不存在
            return null;
        }
        //如果能查询到，再由框架比对数据库中查询到的密码和页面提交的密码是否一致
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        return info;

    }
}
