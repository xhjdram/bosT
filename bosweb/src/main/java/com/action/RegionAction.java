package com.action;

import com.PinYin4jUtils;
import com.domain.BcRegionEntity;
import com.domain.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IRegionService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class RegionAction extends ActionSupport {
    @Autowired
    private IRegionService iRegionService;
    private File region;
    private List<BcRegionEntity> list = new ArrayList<BcRegionEntity>();
    private Integer page;
    private Integer rows;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public void setRegion(File region) {
        this.region = region;
    }

    public String regionUpload() throws Exception {

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(region));
        HSSFSheet sheet1 = hssfWorkbook.getSheet("sheet1");
        for (Row row : sheet1) {
            if (row.getRowNum() == 0) {
                continue;
            }
            BcRegionEntity bcRegionEntity = new BcRegionEntity();
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String distrct = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();
            bcRegionEntity.setId(id);
            bcRegionEntity.setProvince(province);
            bcRegionEntity.setCity(city);
            bcRegionEntity.setDistrict(distrct);
            bcRegionEntity.setPostcode(postcode);
            province = province.substring(0, province.length() - 1);
            city = city.substring(0, city.length() - 1);
            distrct = distrct.substring(0, distrct.length() - 1);
            String shortCode = province + city + distrct;
            String[] headByString = PinYin4jUtils.getHeadByString(shortCode);
            shortCode = StringUtils.join(headByString, "");
            distrct = PinYin4jUtils.hanziToPinyin(distrct, "");
            bcRegionEntity.setShortcode(shortCode);
            bcRegionEntity.setCitycode(distrct);
            list.add(bcRegionEntity);
        }
        iRegionService.saveOrupdate(list);

        return null;
    }

    public String listRegion() {
        PageBean pageBean= new PageBean();
        if(page==null&&rows ==null ){
            List<BcRegionEntity> list=iRegionService.findAll();
            String s = JSONArray.fromObject(list).toString();
            ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
            try {
                ServletActionContext.getResponse().getWriter().write(s);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {    pageBean.setCurrentPage(page);
            pageBean.setPageSize(rows);
            DetachedCriteria detachedCriteria =DetachedCriteria.forClass(BcRegionEntity.class);
            pageBean.setDetachedCriteria(detachedCriteria);
            iRegionService.listRegion(pageBean);
            String s = JSONObject.fromObject(pageBean).toString();
            ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
            try {
                ServletActionContext.getResponse().getWriter().write(s);
            } catch (IOException e) {
                e.printStackTrace();
                
            }
        }

        return null;
    }
}
