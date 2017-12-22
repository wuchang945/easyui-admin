package com.feed.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.feed.ecp.common.constants.Constants;
import com.feed.ecp.common.model.Users;



public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private String[] allowUrls;
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");    
        if(null != allowUrls && allowUrls.length>=1)  
            for(String url : allowUrls) {    
                if(requestUrl.contains(url)) {    
                    return true;    
                }    
            }  
		//获取当前用户可以访问的资源
		Users sessionUser=(Users) request.getSession().getAttribute(Constants.SessionUser);
			if(sessionUser==null){
				 throw new SessionTimeoutException();
			}
			return true;
		
	}

	public String[] getAllowUrls() {
		return allowUrls;
	}

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

//	@Override
//	public void afterCompletion(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("afterCompletion");
//		super.afterCompletion(request, response, handler, ex);
//	}
//
//	@Override
//	public void afterConcurrentHandlingStarted(HttpServletRequest request,
//			HttpServletResponse response, Object handler) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("afterConcurrentHandlingStarted");
//		super.afterConcurrentHandlingStarted(request, response, handler);
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("postHandle");
//		super.postHandle(request, response, handler, modelAndView);
//	}
//	
	
	
}
