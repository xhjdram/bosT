package com.service;

import com.domain.Role;

import java.util.List;

public interface IRoleService {
    public void save(Role role,String functionsId);

    List<Role> listRole();
}
