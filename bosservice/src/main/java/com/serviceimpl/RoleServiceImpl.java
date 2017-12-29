package com.serviceimpl;

import com.RoleDao;
import com.domain.Function;
import com.domain.Role;
import com.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    RoleDao roleDao;
    @Override
    public void save(Role role, String functionsId) {
        if(StringUtils.isNotBlank(functionsId)){
            String[] split = functionsId.split(",");
            for(String str : split) {
                Function function = new Function();
                function.setId(str);
                role.getFunctions().add(function);
            }
            roleDao.saveOrUpdate(role);
        }else{
            throw  new RuntimeException("不能为空");
        }
    }
}
