package com.feed.ecp.common.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.feed.ecp.common.constants.Constants;
import com.feed.ecp.common.model.Users;
import com.feed.ecp.common.modelDTO.ResultDto;
import com.feed.ecp.common.pager.PagerModel;
import com.feed.ecp.common.util.DateUtil;
import com.feed.ecp.common.web.editor.DateEditor;
import com.feed.ecp.common.web.editor.DoubleEditor;
import com.feed.ecp.common.web.editor.IntegerEditor;
import com.feed.ecp.common.web.editor.LongEditor;
import com.feed.mq.MQUtils;
import com.feed.util.ObjectSerializeUtil;
import com.feed.util.SPUtils;

import flexjson.JSONSerializer;


public abstract class BaseController {
	
//	@Autowired
//	protected SpringContextHolder _contextHolder;
	
	@Autowired
	protected ResourceBundleMessageSource _res;
	
	protected ThreadLocal<HttpServletRequest> request=new ThreadLocal<HttpServletRequest>();
	
	protected ThreadLocal<HttpServletResponse> response=new ThreadLocal<HttpServletResponse>();
	
	protected ThreadLocal<HttpSession> session=new ThreadLocal<HttpSession>();
	
//	protected User user;
	
	protected String basePath;
	
	protected String source;
	
	/*@Autowired
	private ConstantService constService;*/
	
	@ModelAttribute
	public void setReqAndResp(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		this.request.set(request);
		this.response.set(response);
		this.session.set(request.getSession());
//		this.user = (User)session.get().getAttribute(ConstantUtil.SESSION_USER);
		this.basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		this.source = request.getParameter("source");
		/*if(StringUtils.isNotBlank(source)) {
			if(!ArrayUtils.contains(ConstantUtil.AREALEVELS, source.substring(0, 1)) && !ArrayUtils.contains(ConstantUtil.AREALEVELS, source.substring(0, 2))) {
				User user=(User)session.getAttribute(ConstantUtil.SESSION_USER);
				this.source = (user != null ? user.getOrg().getExt1() : "") + source;
			}
		} else {
			this.source = null;
		}*/
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	
	protected ModelAndView ajaxDone(int statusCode, String message) {
		ModelAndView mav = new ModelAndView("ajaxDone");
		mav.addObject("statusCode", statusCode);
		mav.addObject("message", message);
		return mav;
	}

	protected ModelAndView ajaxDoneSuccess(String message) {
		return ajaxDone(200, message);
	}

	protected ModelAndView ajaxDoneError(String message) {
		return ajaxDone(200, message);
	}
	protected String getMessage(String code) {
		return this.getMessage(code, new Object[] {});
	}

	protected String getMessage(String code, Object arg0) {
		return getMessage(code, new Object[] { arg0 });
	}

	protected String getMessage(String code, Object arg0, Object arg1) {
		return getMessage(code, new Object[] { arg0, arg1 });
	}

	protected String getMessage(String code, Object arg0, Object arg1,
			Object arg2) {
		return getMessage(code, new Object[] { arg0, arg1, arg2 });
	}

	protected String getMessage(String code, Object arg0, Object arg1,
			Object arg2, Object arg3) {
		return getMessage(code, new Object[] { arg0, arg1, arg2, arg3 });
	}

	protected String getMessage(String code, Object[] args) {
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request.get());
		Locale locale = localeResolver.resolveLocale(request.get());

		return _res.getMessage(code, args, locale);
	}
	
	/**
	 * 构建分页对象
	 * @param data 查询出的数据对象
	 * @return
	 */
	protected <T> PagerModel buildPM(List<T> data) {
		//PageList<T> pageList = (PageList<T>)data;
		
		PagerModel pm = new PagerModel();
		pm.setRows(data);
		//pm.setTotal(data.size());
		
		return pm;
	}
	
	/**
	 * 从缓存中得到常量缓存
	 * @return
	 *//*
	protected Map<String, String> constantMap() {
		return constService.findConstantsMap(new ConstantX());
	}*/
	
	public Users getSessionUser(){
		Users user=(Users) request.get().getSession().getAttribute(Constants.SessionUser);
		return user;
	}
	
	public String str2Json(Object object){
		JSONSerializer jsonSerializer=new JSONSerializer();
		jsonSerializer.exclude("*.class");
		return jsonSerializer.deepSerialize(object);
	}
	
}
