package com.domain;

import java.util.HashSet;
import java.util.Set;

public class User {
//    id                   varchar(32) not null,
//    username             varchar(20) not null,
//    password             varchar(32) not null,
//    salary               double,
//    birthday             date,
//    gender               varchar(10),
//    station              varchar(40),
//    telephone            varchar(11),
//    remark               varchar(255),
//    primary key (id)


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
private  String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String username;
    private String password;
    private Double salary;
    private String birthday;
    private String gender;
    private String station;
    private String telephone;
    private String remark;

    public Set<Role> getSet() {
        return set;
    }

    public void setSet(Set<Role> set) {
        this.set = set;
    }

    private Set<Role> set=new HashSet<Role>();

}
