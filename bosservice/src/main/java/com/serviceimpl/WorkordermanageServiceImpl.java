package com.serviceimpl;

import com.WorkordermanageDao;
import com.domain.QpWorkordermanageEntity;
import com.service.IWorkordermanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService {
    @Autowired
    WorkordermanageDao workordermanageDao;
    @Override
    public void add(QpWorkordermanageEntity qpWorkordermanageEntity) {


            workordermanageDao.addEntity(qpWorkordermanageEntity);



    }
}
