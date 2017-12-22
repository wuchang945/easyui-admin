package com.feed.ecp.common.model;

import java.util.Date;

public class Users {
    private Integer id;

    private String username;

    private String password;

    private String truename;

    private String tellphone;

    private String icon;

    private String usergroupcode;

    private String usergroupname;

    private Integer status;

    private Date registertime;

    private String enterpriseid;

    private String enterprisename;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public String getTellphone() {
        return tellphone;
    }

    public void setTellphone(String tellphone) {
        this.tellphone = tellphone == null ? null : tellphone.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getUsergroupcode() {
        return usergroupcode;
    }

    public void setUsergroupcode(String usergroupcode) {
        this.usergroupcode = usergroupcode == null ? null : usergroupcode.trim();
    }

    public String getUsergroupname() {
        return usergroupname;
    }

    public void setUsergroupname(String usergroupname) {
        this.usergroupname = usergroupname == null ? null : usergroupname.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public String getEnterpriseid() {
        return enterpriseid;
    }

    public void setEnterpriseid(String enterpriseid) {
        this.enterpriseid = enterpriseid == null ? null : enterpriseid.trim();
    }

    public String getEnterprisename() {
        return enterprisename;
    }

    public void setEnterprisename(String enterprisename) {
        this.enterprisename = enterprisename == null ? null : enterprisename.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}