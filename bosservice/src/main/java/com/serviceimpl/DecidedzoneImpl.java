package com.serviceimpl;
import com.DecidedzoneDao;
import com.SubareaDao;
import com.domain.BcDecidedzoneEntity;
import com.domain.BcSubareaEntity;
import com.domain.PageBean;
import com.service.IDecidedzone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class DecidedzoneImpl implements IDecidedzone {
    @Autowired
    private DecidedzoneDao decidedzoneDao;
    @Autowired
    private SubareaDao subareaDao;
    @Override
    public void save(BcDecidedzoneEntity t, String[] subarea_id) {
        decidedzoneDao.saveOrUpdate(t);
        for(String str :subarea_id){
            BcSubareaEntity bcSubareaEntity = subareaDao.selectEntityById(str);
            bcSubareaEntity.setBcDecidedzoneByDecidedzoneId(t);
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        decidedzoneDao.pageQuery(pageBean);
    }
}
