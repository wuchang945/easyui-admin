package com.feed.ecp.common.modelDTO;

/**
 * @ClassName: ResultDto
 * @author: 周伟        @date: 2016-3-2
 * @Description: 结果展示
 */
public class ResultDto {
	private String flag;
	//webservice接口返回的是error
	private String error;
	private String message;
	
	private String text;
	private String id;
	private String name;
	private String systype;

	public ResultDto(){
		
	}

	public ResultDto(String flag, String message) {
		super();
		this.flag = flag;
		this.message = message;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

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

	public String getSystype() {
		return systype;
	}

	public void setSystype(String systype) {
		this.systype = systype;
	}
	
	
}
