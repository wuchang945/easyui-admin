<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/Common/taglib/taglibs.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统角色</title>
<jsp:include page="/Common/easyui/easyui.jsp"></jsp:include>
<script type="text/javascript" src="${basePath}/static/js/manager/sysUsers/sysUsersIndex.js"></script>
<script type="text/javascript">
var basePath="${basePath}";
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar-2">
        <div class="wu-toolbar-button">
        <c:forEach items="${list }" var="sysmenu">
            <a  class="easyui-linkbutton" iconCls="${sysmenu.code }" onclick="${sysmenu.url}" plain="true">${sysmenu.name }</a>
        </c:forEach>
        </div>
        <div class="wu-toolbar-search">
            <label>用户姓名：</label><input class="wu-text" id="username" style="width:100px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="search_user()">检索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="sysUsersGrid" class="easyui-datagrid" style="width:98%;"
			data-options="rownumbers:true,fitColumns:true,fit:true,pagination:true,
			showRefresh:false,url:'../UserController/getAllUsers.do',singleSelect:false,
			method:'post'" toolbar="#wu-toolbar-2">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',hidden:true">id</th>
					<th
						data-options="field:'username',width:100,align:'left',sortable:'true'">用户名</th>
					<th
						data-options="field:'truename',width:120,align:'center',sortable:'true'">真实姓名</th>
					<th
						data-options="field:'enterprisename',width:100,align:'center',sortable:'true'">企业名称</th>
					<th
						data-options="field:'mobilephone',width:100,align:'center',sortable:'true'">手机号码</th>
					<th
						data-options="field:'createtime',width:150,align:'center',sortable:'true',formatter:function(value,row){
		            	if(value!=null){
		            	return showDate(value);
		            	}
		            	}">创建时间</th>
					<th
						data-options="field:'status',width:50,align:'center',sortable:'true',formatter:function(value,row){
						if(value==1){
						return '正常';
						}else if(value==2){
						return '冻结';
						}else{return '无效';}
						}">状态</th>
			</thead>
			
			</table>
</div>
</body>
</html>