<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="javax.servlet.ServletContext"%>
<%@ page import="com.jschrj.ecp.ue.Uploader"%>
<%@ page import="com.jschrj.ecp.ue.ApplicationConstant"%>

<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	
	ServletContext context = request.getSession().getServletContext();
	String uploadimagepath = String.valueOf(context.getAttribute(ApplicationConstant.UPLOADIMAGEPATH));
	String rootPath = String.valueOf(context.getAttribute(ApplicationConstant.UPLOADROOTPATH));
	Long maxSize = Long.parseLong(String.valueOf(context.getAttribute(ApplicationConstant.UPLOADSIZEMAX)));
	
	List<String> savePath = Arrays.asList(uploadimagepath.split(","));

	//获取存储目录结构
	if (request.getParameter("fetch") != null) {

		response.setHeader("Content-Type", "text/javascript");

		//构造json数据
		Iterator<String> iterator = savePath.iterator();

		String dirs = "[";
		while (iterator.hasNext()) {

			dirs += "'" + iterator.next() + "'";

			if (iterator.hasNext()) {
				dirs += ",";
			}

		}
		dirs += "]";
		response.getWriter().print("updateSavePath( " + dirs + " );");
		return;

	}

	Uploader up = new Uploader(request);
	
	//设置根路径
	up.setRootPath(rootPath);
	//获取前端提交的path路径
	String dir = request.getParameter("dir");

	//普通请求中拿不到参数， 则从上传表单中拿
	if (dir == null) {
		dir = up.getParameter("dir");
	}

	if (dir == null || "".equals(dir)) {

		//赋予默认值
		dir = savePath.get(0);

		//安全验证
	} else if (!savePath.contains(dir)) {

		response
				.getWriter()
				.print(
						"{'state':'\\u975e\\u6cd5\\u4e0a\\u4f20\\u76ee\\u5f55'}");
		return;

	}
	
	up.setSavePath(dir);
	String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
	up.setAllowFiles(fileType);
	up.setMaxSize(maxSize); //单位KB
	up.upload();
	response.getWriter().print(
			"{'original':'" + up.getOriginalName() + "','url':'"
					+ getRealPath(request) + "/" +up.getUrl() + "','title':'" + up.getTitle()
					+ "','state':'" + up.getState() + "'}");
%>
<%!
public String getRealPath(HttpServletRequest request){
	ServletContext application = request.getSession().getServletContext();
	return application.getAttribute(ApplicationConstant.WEBIMAGEPATH) + "/upload";
}
%>
