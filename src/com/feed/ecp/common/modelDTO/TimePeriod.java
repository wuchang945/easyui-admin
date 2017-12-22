package com.feed.ecp.common.modelDTO;

import java.util.Date;

/**
 * 时间对象
 * @author AmoX
 *
 */
public class TimePeriod {
	
	// 当前月
	private Integer curMonth;
	
	// 开始日期
	private Date visitTimeLower;
	
	// 结束日期
	private Date visitTimeUpper;

	public TimePeriod() {
		super();
	}
	
	public TimePeriod(Integer curMonth, Date visitTimeLower, Date visitTimeUpper) {
		super();
		this.curMonth = curMonth;
		this.visitTimeLower = visitTimeLower;
		this.visitTimeUpper = visitTimeUpper;
	}

	public Integer getCurMonth() {
		return curMonth;
	}

	public void setCurMonth(Integer curMonth) {
		this.curMonth = curMonth;
	}

	public Date getVisitTimeLower() {
		return visitTimeLower;
	}

	public void setVisitTimeLower(Date visitTimeLower) {
		this.visitTimeLower = visitTimeLower;
	}

	public Date getVisitTimeUpper() {
		return visitTimeUpper;
	}

	public void setVisitTimeUpper(Date visitTimeUpper) {
		this.visitTimeUpper = visitTimeUpper;
	}
	
}
