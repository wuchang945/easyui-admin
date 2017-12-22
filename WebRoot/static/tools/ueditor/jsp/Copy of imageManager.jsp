<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.ServletContext"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="com.jschrj.ecp.ue.ApplicationConstant"%>
<% 
    
	String path = String.valueOf(session.getServletContext().getAttribute(ApplicationConstant.UPLOADROOTPATH));
	String imgStr ="";
	String realpath = getRealPath(request) + "/" + path;
	List<File> files = getFiles(realpath,new ArrayList());
	for(File file :files ){
		imgStr+=file.getPath().replace(getRealPath(request),"")+"ue_separate_ue";
	}
	if(imgStr!=""){
        imgStr = imgStr.substring(0,imgStr.lastIndexOf("ue_separate_ue")).replace(File.separator, "/").trim();
    }
	out.print(imgStr);		
%>
<%!
public List getFiles(String realpath, List files) {
	
	File realFile = new File(realpath);
	if (realFile.isDirectory()) {
		File[] subfiles = realFile.listFiles();
		for(File file :subfiles ){
			if(file.isDirectory()){
				getFiles(file.getAbsolutePath(),files);
			}else{
				if(!getFileType(file.getName()).equals("")) {
					files.add(file);
				}
			}
		}
	}
	return files;
}

public String getRealPath(HttpServletRequest request){
	ServletContext application = request.getSession().getServletContext();
	return application.getAttribute(ApplicationConstant.WEBIMAGEPATH) + "/upload";
}

public String getFileType(String fileName){
	String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
	Iterator<String> type = Arrays.asList(fileType).iterator();
	while(type.hasNext()){
		String t = type.next();
		if(fileName.toLowerCase().endsWith(t)){
			return t;
		}
	}
	return "";
}
%>