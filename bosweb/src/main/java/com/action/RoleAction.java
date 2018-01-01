package com.action;

import com.domain.Role;
import com.service.IRoleService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

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
    public String listRole(){
        List<Role> list=iRoleService.listRole();
        JsonConfig jsonConfig =new JsonConfig();
        jsonConfig.setExcludes(new String[]{"users","functions"});
        String s = JSONArray.fromObject(list,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
