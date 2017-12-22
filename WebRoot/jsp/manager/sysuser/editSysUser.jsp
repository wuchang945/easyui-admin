<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>用户编辑</title>
<script type="text/javascript" src="${basePath}/static/tools/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${basePath}/static/tools/ajaxfileupload.js"></script>
<script type="text/javascript" src="${basePath}/static/js/manager/sysUsers/sysUsersIndex.js"></script>
<jsp:include page="/Common/taglib/taglib.jsp"></jsp:include>
<jsp:include page="/Common/bootstrap/bootstrap.jsp"></jsp:include>
<jsp:include page="/Common/easyui/easyui.jsp"></jsp:include>
<jsp:include page="/Common/layer/layer.jsp"></jsp:include>
<script type="text/javascript">
var basePath='${basePath}';
$(function (){
	//用户角色
	var list='${listUser}';
	list=eval(list);
	var fullname="";
	var roleid="";
	$.each(list,function(index,entry){
		if(list.length==1){
			fullname+=entry["fullname"];
			roleid+=entry["roleid"];
		}else{
			fullname+=entry["fullname"]+",";
			roleid+=entry["roleid"]+",";
<%--			if(index==list.length-1){--%>
<%--				fullname+=",";--%>
<%--				roleid+=",";--%>
<%--			}--%>
		}
		
	})
	$("#fullname").val(fullname);
	$("#roleid").val(roleid);
	//所属系统
	var str='${sysUsers.systype}';
	var strs= new Array();
	strs=str.split(",");
	var systype="";
	for(var i=0;i<strs.length;i++){
		if(1==strs[i]){
			systype+="综合,";
		}
		if(2==strs[i]){
			systype+="透明合格生产,";
		}
		if(3==strs[i]){
			systype+="透明安全餐饮,";
		}
		if(4==strs[i]){
			systype+="透明放心菜市场,";
		}
		if(5==strs[i]){
			systype+="透明食品流通,";
		}
		if(6==strs[i]){
			systype+="执法,";
		}
		if(7==strs[i]){
			systype+="药品,";
		}
		if(8==strs[i]){
			systype+="追溯";
		}
	}
	$("#systypename").val(systype);
	$("#systype").val(str);
})
</script>
</head>
<body class="z-body">
	<div class="title_right" style="margin-top: 20px;">
		<strong>基本信息</strong>
	</div>
	<form id="sysUsers_form" method="post" novalidate>
		<div title="表单信息" style="width:90%; margin:auto">
			<input id="id" name="id" value="${sysUsers.id }" type="hidden"/> 
			<table class="table table-bordered">
				<tr>
					<td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">用户名：</td>
					<td width="38%"><input type="text" name="username" id="username"
						class="span1-1 easyui-validatebox" readonly="readonly"  value="${sysUsers.username }" /><span class="reqired">*</span>
					</td>
					<td width="12%" align="right" bgcolor="#f1f1f1">真实姓名：</td>
					<td><input type="text" class="laydate-icon span1-1 easyui-validatebox"
						id="truename" name="truename" required="required" value="${sysUsers.truename }" /><span class="reqired">*</span>
					</td>
				</tr>
				<tr>
				<td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">电话：</td>
					<td width="38%"><input type="text" name="tellphone" id="tellphone"
						class="span1-1 easyui-validatebox" required="required"  value="${sysUsers.tellphone }" /><span class="reqired">*</span>
					</td>
					<td width="12%" align="right" bgcolor="#f1f1f1">手机：</td>
					<td><input type="text" class="laydate-icon span1-1 easyui-validatebox"
						id="mobilephone" name="mobilephone" value="${sysUsers.mobilephone}" />
					</td>
				</tr>
				<tr>
				<td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">角色：</td>
					<td width="38%"><input type="text" name="fullname" id="fullname"
						class="span1-1 easyui-validatebox" readonly="readonly" value="" onfocus="choseRoles('${sysUsers.id }')" /><span class="reqired">*</span>
						<input type="hidden" name="roleid" id="roleid"
						class="span1-1 easyui-validatebox" readonly="readonly" value="" />
					</td>
					<td width="12%" align="right" bgcolor="#f1f1f1">所属系统：</td>
					<td><input type="text" class="laydate-icon span1-1 easyui-validatebox"
						id="systypename" name="systypename" value="" onfocus="choseSys('${sysUsers.id }')"/><span class="reqired">*</span>
						<input type="hidden" class="laydate-icon span1-1 easyui-validatebox"
						id="systype" name="systype" value=""/>
					</td>
				</tr>
				<tr>
				<td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">单位名称：</td>
					<td width="38%"><input type="text" name="enterprisename" id="enterprisename"
						class="span1-1 easyui-validatebox" readonly="readonly" value="${sysUsers.enterprisename }" onfocus="choseEnterprise()" /><span class="reqired">*</span>
						<input type="hidden" name="enterpriseid" id="enterpriseid"
						class="span1-1 easyui-validatebox" readonly="readonly" value="${sysUsers.enterpriseid }" />
					</td>
					<td width="12%" align="right" bgcolor="#f1f1f1">用户状态：</td>
					<td><input type="radio" class="laydate-icon span1-1 easyui-validatebox"
						 name="userstatus" value="1" <c:if test="${sysUsers.userstatus ==1}"> checked="checked" </c:if> />有效
						 <input type="radio" class="laydate-icon span1-1 easyui-validatebox"
						 name="userstatus" value="0" <c:if test="${sysUsers.userstatus ==0}"> checked="checked" </c:if> />无效
					</td>
				</tr>
				<tr>
				<td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">头像：</td>
					<td width="38%">
<%--					<input type="hidden"  name="icon" value=""/>--%>
					<c:if test="${sysUsers.icon!=null and sysUsers.icon!=''}">
							<input type="hidden"  name="icon" value="${sysUsers.icon}"/>
							<div ><img id="imgsContent" style="width:100px;heigth:100px;" src="${filePath}${sysUsers.icon}" /></div>
						</c:if>
						<c:if test="${sysUsers.icon ==null or sysUsers.icon==''}">
							<input type="hidden"  name="icon" value=""/>
							<div ><img id="imgsContent" alt="暂未上传图片" style="width:100px;heigth:100px;" src="${basePath }/static/images/manager/frameimg/null.png" /></div>
						</c:if>
					<input name="myfiles" id="imgpath"  type="file"/>
						<a class="btn btn-info" style="padding:2px;" href="javascript:ajaxFileUpload('imgpath')">上传</a>
					</td>
					<td width="12%" align="right" bgcolor="#f1f1f1">出生日期：</td>
					<td>
					<input type="text" class="easyui-datebox" editable="false" id="birthday" name="birthday" value="${sysUsers.birthday }"/>
					</td>
				</tr>
				<tr>
				<td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">职务：</td>
					<td width="38%">
					<input type="text" name="job" id="job"
						class="span1-1 easyui-validatebox" required="required" readonly="readonly" value="${sysUsers.job }" onfocus="choseJob()" /><span class="reqired">*</span>
					</td>
					<td width="12%" align="right" bgcolor="#f1f1f1">性别：</td>
					<td><input type="radio" class="laydate-icon span1-1 easyui-validatebox"
						 name="sex" value="1" <c:if test="${sysUsers.sex ==1}"> checked="checked" </c:if>/>男
						 <input type="radio" class="laydate-icon span1-1 easyui-validatebox"
						 name="sex" value="2" <c:if test="${sysUsers.sex ==2}"> checked="checked" </c:if>/>女
					</td>
				</tr>
			</table>
			<table class="margin-bottom-20 table  no-border">
				<tr>
					<td class="text-center">
					<input type="button" value="确定"
						class="btn btn-info " style="width:80px;" onclick="save_editSysUser('确定','save_editSysUser','editSysUser.jsp','编辑用户')" />
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>