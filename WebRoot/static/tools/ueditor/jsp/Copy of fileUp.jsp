<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="javax.servlet.ServletContext"%>
<%@ page import="com.jschrj.ecp.ue.Uploader"%>
<%@ page import="com.jschrj.ecp.ue.ApplicationConstant"%>

<%
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    
    ServletContext context = request.getSession().getServletContext();
	
    String rootPath = String.valueOf(context.getAttribute(ApplicationConstant.UPLOADROOTPATH));
    String uploadfilepath = String.valueOf(context.getAttribute(ApplicationConstant.UPLOADFILEPATH));
    Long maxSize = Long.parseLong(String.valueOf(context.getAttribute(ApplicationConstant.UPLOADFILESIZEMAX)));
    
    Uploader up = new Uploader(request);
    up.setSavePath(uploadfilepath); //保存路径
  	//设置根路径
	up.setRootPath(rootPath);
    String[] fileType = {".rar" , ".doc" , ".docx" , ".zip" , ".pdf" , ".txt" , ".swf", ".wmv"};  //允许的文件类型
    up.setAllowFiles(fileType);
    up.setMaxSize(maxSize);        //允许的文件最大尺寸，单位KB
    up.upload();
    response.getWriter().print("{'url':'"+getRealPath(request) + "/" +up.getUrl()+"','fileType':'"+up.getType()+"','state':'"+up.getState()+"','original':'"+up.getOriginalName()+"'}");

%>

<%!
public String getRealPath(HttpServletRequest request){
	ServletContext application = request.getSession().getServletContext();
	return application.getAttribute(ApplicationConstant.WEBIMAGEPATH) + "/upload";
}
%>
