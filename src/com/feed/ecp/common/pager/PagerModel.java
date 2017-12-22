package com.feed.ecp.common.pager;

import java.util.List;
/**
 * @author 金雷
 *  date : 2013-5-15 下午04:30:42 
 *  description ：分页对象模型
 */ 
public class PagerModel {

	/**
	 * 总记录数
	 */
	private int total;
	
	/**
	 * 当前页结果集
	 */
	private List<?> rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
