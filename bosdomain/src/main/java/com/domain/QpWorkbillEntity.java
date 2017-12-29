package com.domain;

import java.sql.Timestamp;

public class QpWorkbillEntity {
    private String id;
    private String type;
    private String pickstate;
    private Timestamp buildtime;
    private Integer attachbilltimes;
    private String remark;
    private QpNoticebillEntity qpNoticebillByNoticebillId;
    private BcStaffEntity bcStaffEntity;

    public BcStaffEntity getBcStaffEntity() {
        return bcStaffEntity;
    }

    public void setBcStaffEntity(BcStaffEntity bcStaffEntity) {
        this.bcStaffEntity = bcStaffEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPickstate() {
        return pickstate;
    }

    public void setPickstate(String pickstate) {
        this.pickstate = pickstate;
    }

    public Timestamp getBuildtime() {
        return buildtime;
    }

    public void setBuildtime(Timestamp buildtime) {
        this.buildtime = buildtime;
    }

    public Integer getAttachbilltimes() {
        return attachbilltimes;
    }

    public void setAttachbilltimes(Integer attachbilltimes) {
        this.attachbilltimes = attachbilltimes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QpWorkbillEntity that = (QpWorkbillEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (pickstate != null ? !pickstate.equals(that.pickstate) : that.pickstate != null) return false;
        if (buildtime != null ? !buildtime.equals(that.buildtime) : that.buildtime != null) return false;
        if (attachbilltimes != null ? !attachbilltimes.equals(that.attachbilltimes) : that.attachbilltimes != null)
            return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (pickstate != null ? pickstate.hashCode() : 0);
        result = 31 * result + (buildtime != null ? buildtime.hashCode() : 0);
        result = 31 * result + (attachbilltimes != null ? attachbilltimes.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    public QpNoticebillEntity getQpNoticebillByNoticebillId() {
        return qpNoticebillByNoticebillId;
    }

    public void setQpNoticebillByNoticebillId(QpNoticebillEntity qpNoticebillByNoticebillId) {
        this.qpNoticebillByNoticebillId = qpNoticebillByNoticebillId;
    }
}
