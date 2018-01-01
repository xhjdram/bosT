package com.service;

import com.domain.PageBean;
import com.domain.User;

import java.util.List;

public interface UserService {
    User getUserByPassWordAndUserName(User user);

    int updateUser(User user);

    void save(String[] roleIds, User t);

    void pageQuery(PageBean pageBean);
}
