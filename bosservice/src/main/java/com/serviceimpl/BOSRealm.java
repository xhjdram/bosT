package com.serviceimpl;

import com.UserDao;
import com.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class BOSRealm extends AuthorizingRealm {
    @Autowired
    UserDao userDao;

    //授权方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
   //TODO 以后可以送数据库获取当前user的权限
        authorizationInfo.addStringPermission("staff-list");
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
