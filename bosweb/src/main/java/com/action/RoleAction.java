package com.action;

import com.domain.Role;
import com.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
    @Autowired
    IRoleService iRoleService;
    public String functionsId ;

    public void setFunctionIds(String functionIds) {
        this.functionsId = functionIds;
    }

    public String save(){
        iRoleService.save(t,functionsId);
        return "list";
    }
}
