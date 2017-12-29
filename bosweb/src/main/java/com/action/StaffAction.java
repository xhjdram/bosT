package com.action;

import com.domain.BcStaffEntity;
import com.domain.PageBean;
import com.service.IStaffService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<BcStaffEntity> {
    @Autowired
    private IStaffService iStaffService;
    private int page;
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    private int rows;

    public String add() {
        iStaffService.addEntity(t);
        return "list";
    }

    public String pageQuery() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BcStaffEntity.class);
        PageBean pageBean = new PageBean();
        pageBean.setDetachedCriteria(detachedCriteria);
        pageBean.setPageSize(rows);
        pageBean.setCurrentPage(page);
        iStaffService.pageQuery(pageBean);
        String s = JSONObject.fromObject(pageBean).toString();
        try {
            ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
            ServletActionContext.getResponse().getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequiresPermissions("delete_Staff")
    public String deleteStaff() {
        String[] split = ids.split(",");
        iStaffService.deleteStaff(split);
        return "list";
    }

    public String editStaff() {
        String id = t.getId();
        BcStaffEntity bcStaffEntity = iStaffService.getStaffById(id);
        bcStaffEntity.setDeltag(t.getDeltag());
        bcStaffEntity.setHaspda(t.getHaspda());
        bcStaffEntity.setName(t.getName());
        bcStaffEntity.setStandard(t.getStandard());
        iStaffService.updataStaff(bcStaffEntity);
        return "list";
    }

    public String findStaff() {
        List<BcStaffEntity> list = iStaffService.findStaffNoDelete();
        String s = JSONArray.fromObject(list).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
