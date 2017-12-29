package com.action;

import com.domain.Function;
import com.domain.PageBean;
import com.service.IFunctionService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
    @Autowired
    IFunctionService iFunctionService;
    public String ajaxList(){
        List<Function> functions = iFunctionService.ajaxList();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[] {"roles","parentFunction"});
        String s = JSONArray.fromObject(functions, jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private  Integer page;
    private  Integer rows;

    public String save(){
        iFunctionService.save(t);
        return "list";
    }

    public String pageQuery(){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
        PageBean pageBean = new PageBean();
        pageBean.setDetachedCriteria(detachedCriteria);
        pageBean.setPageSize(rows);
        pageBean.setCurrentPage(Integer.parseInt(t.getPage()));
       iFunctionService.pageQuery(pageBean);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"roles","children","parentFunction"});
        String s = JSONObject.fromObject(pageBean, jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
