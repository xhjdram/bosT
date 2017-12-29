package com.service;

import com.domain.BcDecidedzoneEntity;
import com.domain.PageBean;

public interface IDecidedzone {
    void save(BcDecidedzoneEntity t, String[] subarea_id);

    void pageQuery(PageBean pageBean);
}
