package com.serviceimpl;

import com.FunctionDao;
import com.domain.Function;
import com.domain.PageBean;
import com.domain.User;
import com.service.IFunctionService;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {
    @Autowired
    FunctionDao functionDao;

    public List<Function> ajaxList() {
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        List<Function> functions;
        if (user.getUsername().equals("xuan")) {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
            detachedCriteria.add(Restrictions.eq("generatemenu", "1"));
            functions = functionDao.findEntityByDe(detachedCriteria);
        } else {
            functions = functionDao.generateMenuByUserId(user.getId());
        }

        return functions;

    }

    @Override
    public void save(Function t) {
        Function parentFunction = t.getParentFunction();
        if (parentFunction != null && !parentFunction.getId().equals("")) {
            functionDao.saveOrUpdate(t);
        } else {
            t.setParentFunction(null);
            functionDao.saveOrUpdate(t);
        }

    }

    public void pageQuery(PageBean pageBean) {
        functionDao.pageQuery(pageBean);

    }
}
