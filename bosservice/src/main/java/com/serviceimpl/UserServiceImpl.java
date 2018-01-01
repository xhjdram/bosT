package com.serviceimpl;
import com.MD5Utils;
import com.UserDao;
import com.domain.PageBean;
import com.domain.Role;
import com.domain.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User getUserByPassWordAndUserName(User user) {
        String passWord = MD5Utils.md5(user.getPassword());
        String name = user.getUsername();
        return userDao.getUserByPassWordAndUserName(name, passWord);
    }

    public int updateUser(User user) {
        String password = user.getPassword();
        String md5 = MD5Utils.md5(password);
        String id = user.getId();
        int updateUser = userDao.update("updateUser", md5, id);
        return updateUser;
    }

    @Override
    public void save(String[] roleIds, User t) {
        //
        //把接受的密码进行MD5 加密
        String password = t.getPassword();
        password = MD5Utils.md5(password);
        t.setPassword(password);
        //保存用户
        //把配置文件的主键生成策略改成uuid
        userDao.saveOrUpdate(t);
        //遍历数组生成游离的对象利用id
        for(String str : roleIds){
            Role role =new Role(str);
            t.getSet().add(role);
        }
    }

    @Override
    public void  pageQuery(PageBean pageBean) {
          userDao.pageQuery(pageBean);

    }


}
