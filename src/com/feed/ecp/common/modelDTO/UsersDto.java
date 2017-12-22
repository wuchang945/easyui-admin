package com.feed.ecp.common.modelDTO;

import com.feed.ecp.common.model.Users;
import com.feed.ecp.common.pager.PagerParams;


public class UsersDto extends Users{
	
	private PagerParams pagerParams;
	
	public PagerParams getPagerParams() {
		return pagerParams;
	}

	public void setPagerParams(PagerParams pagerParams) {
		this.pagerParams = pagerParams;
	}

}
