package com.serviceimpl;

import com.FunctionDao;
import com.domain.Function;
import com.domain.PageBean;
import com.service.IFunctionService;
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
        List<Function> functions = functionDao.selectEntityAll();
        return functions;

    }
    @Override
    public void save(Function t) {
        Function parentFunction = t.getParentFunction();
        if(parentFunction!=null&&!parentFunction.getId().equals("")){
            functionDao.saveOrUpdate(t);
        }else {
            t.setParentFunction(null);
            functionDao.saveOrUpdate(t);
        }

    }

    public void pageQuery(PageBean pageBean) {
        functionDao.pageQuery(pageBean);

    }
}
