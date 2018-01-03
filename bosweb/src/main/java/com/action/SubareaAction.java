package com.action;

import com.FileUtils;
import com.domain.BcRegionEntity;
import com.domain.BcSubareaEntity;
import com.domain.PageBean;
import com.service.ISubareaService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;


@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<BcSubareaEntity> {
    @Autowired
    private ISubareaService iSubareaService;
    private Integer page;
    private Integer rows;

    public String saveSubarea() {
        iSubareaService.save(t);
        return "list";
    }

    public String pageQuery() {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BcSubareaEntity.class);
        String addresskey = t.getAddresskey();
        if (StringUtils.isNotBlank(addresskey)) {
            detachedCriteria.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
        }
        BcRegionEntity bcRegionByRegionId = t.getBcRegionByRegionId();
        detachedCriteria.createAlias("bcRegionByRegionId", "r");
        if (bcRegionByRegionId != null) {
            String city = bcRegionByRegionId.getCity();
            String district = bcRegionByRegionId.getDistrict();
            String province = bcRegionByRegionId.getProvince();
            if (StringUtils.isNotBlank(city)) {
                detachedCriteria.add(Restrictions.like("r.city", "%" + city + "%"));
            }
            if (StringUtils.isNotBlank(district)) {
                detachedCriteria.add(Restrictions.like("r.district", "%" + district + "%"));
            }
            if (StringUtils.isNotBlank(province)) {
                detachedCriteria.add(Restrictions.like("r.province", "%" + province + "%"));
            }
        }

        pageBean.setDetachedCriteria(detachedCriteria);
        iSubareaService.pageQuery(pageBean);
        JsonConfig jsonConfig =new JsonConfig();
//        jsonConfig.addIgnoreFieldAnnotation("BcDecidedzoneEntity");
        String excludes[] = new  String[]{"bcDecidedzoneByDecidedzoneId"};
        jsonConfig.setExcludes(excludes);
        String s = JSONObject.fromObject(pageBean,jsonConfig).toString();
        try {
            ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
            ServletActionContext.getResponse().getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String export() {
        List<BcSubareaEntity> all = iSubareaService.findAll();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("分区编号");
        row.createCell(1).setCellValue("起始编号");
        row.createCell(2).setCellValue("结束编号");
        row.createCell(3).setCellValue("省市区");
        for (BcSubareaEntity bcSubareaEntity : all) {
             row = sheet.createRow(sheet.getLastRowNum()+ 1);
            row.createCell(0).setCellValue(bcSubareaEntity.getId());
            row.createCell(1).setCellValue(bcSubareaEntity.getStartnum());
            row.createCell(2).setCellValue(bcSubareaEntity.getEndnum());
            row.createCell(3).setCellValue(bcSubareaEntity.getBcRegionByRegionId().getName());
        }
        try {
            String fileName = "分区数据.xls";
            String mimeType = ServletActionContext.getServletContext().getMimeType(fileName);
            String header = ServletActionContext.getRequest().getHeader("User-Agent");
            ServletActionContext.getResponse().setContentType(mimeType);
            fileName = FileUtils.encodeDownloadFilename(fileName, header);
           	ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+fileName);
            ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
            hssfWorkbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
    public String findSubarea(){
        List<BcSubareaEntity> list=iSubareaService.findSubarea();
        String s = JSONArray.fromObject(list).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //分区分布图
    public String picture(){
        List<Object> objects = iSubareaService.subareaPicture();
        String s = JSONArray.fromObject(objects).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

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
}
