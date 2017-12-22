package com.feed.ecp.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class viewFilter implements Filter {
	private HttpServletRequest request;

    private HttpServletResponse response;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
	    request = (HttpServletRequest) servletRequest;
	    response = (HttpServletResponse) servletResponse;
	    String url =request.getServletPath();
	    if(url.contains("home")){
	    	request.getRequestDispatcher("login.jsp").forward(request,response); 
	    }else if(url.contains("web")){
	    	request.getRequestDispatcher("/jsp/index/ps/index.jsp").forward(request,response); 
	    }else{
	    	request.getRequestDispatcher(url).forward(request,response);
	    }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
