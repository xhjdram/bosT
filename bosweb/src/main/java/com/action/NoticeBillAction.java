package com.action;

import com.Customer;
import com.IBaseDao;
import com.domain.QpNoticebillEntity;
import com.google.common.io.ByteStreams;
import com.service.INoticeBillService;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
@Scope("prototype")
public class NoticeBillAction extends BaseAction<QpNoticebillEntity> {
    @Autowired
    IBaseDao iBaseDao;
    @Autowired
    private INoticeBillService iNoticeBillService;

    public String findCustomerByTelephoneNumber() {
        String telephone = t.getTelephone();
        Customer customerByPhoneNumber = iBaseDao.findCustomerByPhoneNumber(telephone);
        String s = JSONObject.fromObject(customerByPhoneNumber).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String add() {
//        Date pickdate = t.getPickdate();
//        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
//        String format = dateFormat.format(pickdate);
//        System.out.println(format);
        iNoticeBillService.add(t);
        return "list";
    }
}
