package com.serviceimpl;

import com.DecidedzoneDao;
import com.IBaseDao;
import com.NoticeBillDao;
import com.WorkBillDao;
import com.domain.BcDecidedzoneEntity;
import com.domain.BcStaffEntity;
import com.domain.QpNoticebillEntity;
import com.domain.QpWorkbillEntity;
import com.service.INoticeBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service
@Transactional
public class NoticeBillServiceImpl implements INoticeBillService {
    @Autowired
    WorkBillDao workBillDao;
    @Autowired
    DecidedzoneDao decidedzoneDao;
    @Autowired
    IBaseDao iBaseDao;
    @Autowired
    private NoticeBillDao noticeBillDao;
    @Override
    public void add(QpNoticebillEntity qpNoticebillEntity) {
        String pickaddress = qpNoticebillEntity.getPickaddress();
        String decidedZoneIdByAddress = iBaseDao.findDecidedZoneIdByAddress(pickaddress);
        BcDecidedzoneEntity bcDecidedzoneEntity =decidedzoneDao.selectEntityById(decidedZoneIdByAddress);
        noticeBillDao.addEntity(qpNoticebillEntity);
        if(bcDecidedzoneEntity!=null){
            //根据定区获取去取派员
            BcStaffEntity bcStaffEntity = bcDecidedzoneEntity.getBcStaffEntity();
            QpWorkbillEntity qpWorkbillEntity =new QpWorkbillEntity();
            qpWorkbillEntity.setBcStaffEntity(bcStaffEntity);
            qpWorkbillEntity.setBuildtime(new Timestamp(System.currentTimeMillis()));
            qpWorkbillEntity.setQpNoticebillByNoticebillId(qpNoticebillEntity);
            workBillDao.saveOrUpdate(qpWorkbillEntity);
        }else {
            System.out.println("人工处理");
        }


    }
}
