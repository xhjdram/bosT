package com.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

public class MyInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        Object user = ServletActionContext.getRequest().getSession().getAttribute("user");
        if(user==null){
            return "login";
        }else {
            return actionInvocation.invoke();
        }

    }
}
