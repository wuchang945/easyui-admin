<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
	String rootPath = application.getRealPath( "/" );
	System.out.println("action="+request.getParameter("action"));
	String s= new ActionEnter( request, rootPath ).exec();
	System.out.println("s="+s);
	out.write(s);
	
%>

