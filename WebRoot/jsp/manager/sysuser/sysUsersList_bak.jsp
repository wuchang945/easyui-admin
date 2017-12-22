<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统角色</title>
<script type="text/javascript" src="${basePath}/static/js/manager/sysUsers/sysUsersIndex.js"></script>
<jsp:include page="/Common/taglib/taglibs.jsp"></jsp:include>
<jsp:include page="/Common/easyui/easyui.jsp"></jsp:include>
<%--<jsp:include page="/Common/meta/meta.jsp"></jsp:include>--%>
<script type="text/javascript">
var basePath="${basePath}";
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar-2">
        <div class="wu-toolbar-button">
            <a href="#" class="easyui-linkbutton" iconCls="icon-user-add" onclick="openAdd()" plain="true">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-user-edit" onclick="openEdit()" plain="true">修改</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()" plain="true">删除</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()" plain="true">取消</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()" plain="true">刷新</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-print" onclick="openAdd()" plain="true">打印</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-help" onclick="openEdit()" plain="true">帮助</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="remove()" plain="true">撤销</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-redo" onclick="cancel()" plain="true">重做</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-sum" onclick="reload()" plain="true">总计</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-back" onclick="reload()" plain="true">返回</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-tip" onclick="reload()" plain="true">提示</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="reload()" plain="true">保存</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-cut" onclick="reload()" plain="true">剪切</a>
        </div>
        <div class="wu-toolbar-search">
            <label>起始时间：</label><input class="easyui-datetimebox" style="width:100px">
            <label>结束时间：</label><input class="easyui-datetimebox" style="width:100px">
            <label>用户组：</label> 
            <select class="easyui-combobox" panelHeight="auto" style="width:100px">
                <option value="0">选择用户组</option>
                <option value="1">黄钻</option>
                <option value="2">红钻</option>
                <option value="3">蓝钻</option>
            </select>
            <label>关键词：</label><input class="wu-text" style="width:100px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search">检索</a>
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