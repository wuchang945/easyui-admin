package com.feed.ecp.common.pager;


import java.io.Serializable;


/**
 * 页面分页参数转化
 * @author Administrator
 *
 */
public class PagerParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1425900853293603640L;
	
	/**
	 * 页号
	 */
	private Integer page = 1;
	
	/**
	 * 分页大小
	 */
	private Integer rows = 10;
	
	/**
	 * 开始位置
	 */
	private Integer pageIndex=(page-1)*rows;

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

	public Integer getPageIndex() {
		return (page-1)*rows;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	
	
/*
	public PagerParams() {
		this.pageNum = SystemContext.getPagenum()+"";
		this.numPerPage = SystemContext.getPagesize()+"";
		if(!StringUtil.isEmpty(this.pageNum)) {
			page = Integer.parseInt(this.pageNum);
		}
		if(!StringUtil.isEmpty(this.numPerPage)) {
			limit = Integer.parseInt(this.numPerPage);
		}
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = SystemContext.getPagenum()+"";
		if(!StringUtil.isEmpty(this.pageNum)) {
			page = Integer.parseInt(this.pageNum);
		}
	}

	public String getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(String numPerPage) {
		this.numPerPage = SystemContext.getPagesize()+"";
		if(!StringUtil.isEmpty(this.numPerPage)) {
			limit = Integer.parseInt(this.numPerPage);
		}
	}*/
	
}
