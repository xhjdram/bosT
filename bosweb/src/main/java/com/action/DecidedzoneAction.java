package com.action;

import com.Customer;
import com.IBaseDao;
import com.domain.BcDecidedzoneEntity;
import com.domain.BcSubareaEntity;
import com.domain.PageBean;
import com.service.IDecidedzone;
import com.service.ISubareaService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<BcDecidedzoneEntity> {
    @Autowired
    private ISubareaService iSubareaService;
    private Integer rows;
    private Integer page;
    @Autowired
    private com.IBaseDao iBaseDao;

    @Autowired
    private IDecidedzone iDecidedzone;

    private String[] subarea_id;

    public void setSubarea_id(String[] subarea_id) {
        this.subarea_id = subarea_id;
    }

    public String save() {
        iDecidedzone.save(t, subarea_id);
        return "list";
    }

    public String pageQuery() {
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(rows);
        pageBean.setCurrentPage(page);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BcDecidedzoneEntity.class);
        pageBean.setDetachedCriteria(detachedCriteria);
        iDecidedzone.pageQuery(pageBean);
//        JsonConfig jsonConfig = new JsonConfig();
//        String[] str = new String[]{};
//        jsonConfig.setExcludes();
        String s = JSONObject.fromObject(pageBean).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String findNoassociationSelect() {
        List<Customer> notAssociate = iBaseDao.findNotAssociate();
        String jsonObject = JSONArray.fromObject(notAssociate).toString();
        try {
            ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
            ServletActionContext.getResponse().getWriter().write(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String findAssociationSelect() {
        String id = t.getId();
        List<Customer> notAssociate = iBaseDao.findAssociate(id);
        String jsonObject = JSONArray.fromObject(notAssociate).toString();
        try {
            ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
            ServletActionContext.getResponse().getWriter().write(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Integer> customerIds;

    public String assigncustomerstodecidedzone() {
        iBaseDao.assigncustomerstodecidedzone(t.getId(), customerIds);
        return "list";
    }

    public String associationSubarea() {
        String id = t.getId();
        List<BcSubareaEntity> subareaByDecidedzoneId = iSubareaService.findSubareaByDecidedzoneId(id);
        String[] ex = new String[]{"bcDecidedzoneByDecidedzoneId"};
        JsonConfig jsonConfig =new JsonConfig();
        jsonConfig.setExcludes(ex);
        String s = JSONArray.fromObject(subareaByDecidedzoneId,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public String associationCustomer(){
        String id = t.getId();
        List<Customer> associate = iBaseDao.findAssociate(id);
        String s = JSONArray.fromObject(associate).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }
}
