package com.feed.ecp.common.model;

import com.feed.util.PinYinUtil;

public class SysMenu {
    private Integer id;
    
    private String code;

    private String name;

    private String url;

    private Integer sort;

    private Integer parentid;

    private String status;

    private String ismenu;

    private String menuPin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIsmenu() {
        return ismenu;
    }

    public void setIsmenu(String ismenu) {
        this.ismenu = ismenu == null ? null : ismenu.trim();
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

    public String getMenuPin() {
        // 调用方法获得首字母拼音
        if(this.name != null && !this.name.equals(""))
            return PinYinUtil.getFirstCase(this.name);
        return null;
    }

    public void setMenuPin(String menuPin) {
        this.menuPin = menuPin;
    }
}