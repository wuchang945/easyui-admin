package com.feed.ecp.common.web.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import com.feed.ecp.common.util.DateUtil;


public class DateEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			setValue(DateUtil.string2Date(text, DateUtil.PATTERN_STANDARD));
		}
	}

	@Override
	public String getAsText() {

		return getValue().toString();
	}
}
