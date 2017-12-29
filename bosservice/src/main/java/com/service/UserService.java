package com.service;

import com.domain.User;

public interface UserService{
    User getUserByPassWordAndUserName(User user);
    int updateUser(User user);
}
