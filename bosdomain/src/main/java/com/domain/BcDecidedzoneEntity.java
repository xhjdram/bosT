package com.domain;
public class BcDecidedzoneEntity {
    private String id;
    private String name;

    public BcStaffEntity getBcStaffEntity() {
        return bcStaffEntity;
    }

    public void setBcStaffEntity(BcStaffEntity bcStaffEntity) {
        this.bcStaffEntity = bcStaffEntity;
    }

    private BcStaffEntity bcStaffEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BcDecidedzoneEntity that = (BcDecidedzoneEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
