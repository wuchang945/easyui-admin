<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="Common/taglib/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>透明食药监系统管理平台</title>
	<%@ include file="Common/easyui/easyui.jsp" %>
	<script type="text/javascript" src="${basePath}/static/js/manager/frame/index.js"></script>
	<script type="text/javascript" src="${basePath}/static/tools/layer/layer.js"></script>
	<script type="text/javascript">
		// 系统所有的菜单
		var menus = '${allmenu}';
		var allmenus = eval("("+menus+")");
		$(function(){
		var stateObject = {};
		var title = "放心透明菜市场";
		var newUrl = "index.do";
		history.pushState(stateObject,title,newUrl);
        //start();
		});


		function updatePassword(){
			 top.framework.openTab('修改密码', 'LoginController/toUpdatePassword.do');
		}
		$.ajax({
	 	url:"../loginController/getIpInfo.do",
	 	type:"post",
	 	async : true,
	 	success:function(data){
	 	var json=JSON.parse(data);
	 	if(json!=null){
	 	var ipinfo="当前ip:"+json.ip+"，类型："+json.isp+"";
	 	$("#ip_info").html(ipinfo);
	 	}
	 	}
	 	});

		function showUserInfo(){
				$("#show").css("display","block");
		}
		function changeUserInfos(){
			var str=$("#userInfos").css("display");
			if(str=="none"){
				$("#userInfos").css("display","block");
			}else{
				$("#userInfos").css("display","none");
			}
		}
		
	</script>
</head>
<body class="easyui-layout" fit="true"  scroll="no">
<%--	<%--%>
<%--	String systype = request.getParameter("systype");--%>
<%--	application.setAttribute("systype", systype);--%>
<%--	%>--%>
   <noscript>
       <div class="noscript">
           <img src="~/Content/images/noscript.gif" alt="抱歉，请开启脚本支持！" />
       </div>
   </noscript>
    <div region="north" class="head-north" split="true" border="false"  >
       <div class="head head-right" id="userInfo"  >
           <a href="#" onclick="changeUserInfos()"  style="text-decoration: none;color:white;" plain="true">&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.loginuser.truename}&nbsp;&nbsp;</a>
<%--				<ul id="userInfos" style="display: block;position: absolute;">--%>
<%--					<li><a  href='#' plain='true' style="text-decoration: none;color:#424242;" onclick="updatePassword()">&nbsp;&nbsp;&nbsp;&nbsp;修改密码</a></li>--%>
       	   			<a  href='${basePath}/loginController/logout.do' style="text-decoration: none;color:#424242;" plain='true'  title="退出系统"><span class="iconC icon-stop"></span></a>
<%--				</ul>--%>

       </div>
       <span id="head-logo" class="head-left">
       		EasyUI Web Admin
           	<%-- <img alt="logo" src="${basePath}/static/images/logo/logo.png"> --%>
       </span>
    </div>

    <div region="west" split="true" title="工作平台" style="width:180px;" id="west">
        <div id="wnav"></div>
    </div>

    <div region="south" split="true" class="head-south">
	   <div class="footer"><span>系统消息提示：您好！欢迎使用系统管理平台</span><span style="margin-left:20px;" id="ip_info"></span></div>
    </div>

    <div region="center" id="mainPanle" style="background: #eee;">
	    <div id="tabs" class="easyui-tabs"  fit="true"  style="overflow:auto;" border="false" >
		    <div title="我的主页" style="overflow:visible;" id="home">
		    <div>
		    	<span id="audit"></span>
		    </div>
		    </div>
	    </div>
    </div>

	<div id="tabsMenu" class="easyui-menu hide" style="width:180px;">
<!-- 	    <div id="refresh">刷新</div> -->
	    <div class="menu-sep"></div>
	    <div id="close">关闭</div>
	    <div id="closeall">全部关闭</div>
	    <div id="closeother">除选中项之外全部关闭</div>
	    <div class="menu-sep"></div>
	    <div id="closeright">关闭选中项右侧标签</div>
	    <div id="closeleft">关闭选中项左侧标签</div>
	    <div class="menu-sep"></div>
<!-- 	    <div id="exit">退出</div> -->
	 </div>

     <div id="w"></div>
     <div id="notity"></div>
</body>
</html>