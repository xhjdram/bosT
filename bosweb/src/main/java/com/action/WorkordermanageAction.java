package com.action;

import com.domain.QpWorkordermanageEntity;
import com.service.IWorkordermanageService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<QpWorkordermanageEntity> {
    @Autowired
    IWorkordermanageService iWorkordermanageService;

    public String add() {
        String add = "1";
        try {
            iWorkordermanageService.add(t);
        } catch (Exception e) {
            add = "0";
            e.printStackTrace();
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
