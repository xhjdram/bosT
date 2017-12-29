package com.serviceimpl;
import com.MD5Utils;
import com.UserDao;
import com.domain.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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


}
