<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/Common/taglib/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>用户编辑</title>
<jsp:include page="/Common/easyui/easyui.jsp"></jsp:include>
<jsp:include page="/Common/layer/layer.jsp"></jsp:include>
<script type="text/javascript" src="${basePath}/static/js/manager/sysUsers/sysUsersIndex.js"></script>
</head>
<body class="z-body">
	<div class="title_right" style="margin-top: 20px;">
		<strong>基本信息</strong>
	</div>
	<form id="sysUsers_form" method="post" novalidate>
		<div title="表单信息" style="width:90%; margin:auto">
			<table class="table table-bordered">
				<tr>
					<td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">用户名：</td>
					<td width="38%"><input type="text" name="username" id="username"
						class="wu-text easyui-validatebox" required="required"   value="${account.account }"/>
					</td>
					<td width="12%" align="right" bgcolor="#f1f1f1">真实姓名：</td>
					<td><input type="text" class="wu-text easyui-validatebox"
						id="truename" name="truename" value=""required="required" />
					</td>
				</tr>
				<tr>
				<td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">电话：</td>
					<td width="38%"><input type="text" name="tellphone" id="tellphone"
						class="wu-text easyui-validatebox" value="" />
					</td>
					<td width="12%" align="right" bgcolor="#f1f1f1">手机：</td>
					<td><input type="text" class="wu-text easyui-validatebox"
						id="mobilephone" name="mobilephone" value=""  required="required"/>
					</td>
				</tr>
				<tr>
				<td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">角色：</td>
					<td width="38%"><input type="text" name="fullname" id="fullname"
						class="wu-text easyui-validatebox" required="required"  value="" />
						<input type="hidden" name="roleid" id="roleid"
						class="wu-text" readonly="readonly" value="" />
					</td>
					<td width="12%" align="right" bgcolor="#f1f1f1">性别：</td>
					<td><input type="radio" class="w"
						 name="sex" value="1" checked="checked"/>男
						 <input type="radio" class=""
						 name="sex" value="2" />女
					</td>
				</tr>
				<tr>
				<td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">单位名称：</td>
					<td width="38%"><input type="text" name="enterprisename" id="enterprisename"
						class="wu-text easyui-validatebox" required="required"  value="" />
						<input type="hidden" name="enterpriseid" id="enterpriseid" 
						class="wu-text" readonly="readonly" value="" />
						<input type="hidden" name="treecode" id="treecode" value=""
						class="wu-text"/>
					</td>
					<td width="12%" align="right" bgcolor="#f1f1f1">用户状态：</td>
					<td><input type="radio" class=""
						 name="userstatus" value="1" checked="checked"/>有效
						 <input type="radio" class=""
						 name="userstatus" value="0" />无效
					</td>
				</tr>
				<tr>
				<td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">头像：</td>
					<td width="38%">
					<input type="hidden"  name="icon" value=""/>
					<div ><img id="imgsContent" alt="暂未上传图片" style="width:100px;heigth:100px;" src="${basePath }/static/images/manager/frameimg/null.png" /></div>
					<input name="myfiles" id="imgpath"  type="file"/>
						<a class="btn btn-info" style="padding:2px;color:white;" href="javascript:ajaxFileUpload('imgpath')">上传</a>
					</td>
					<td width="12%" align="right" bgcolor="#f1f1f1">出生日期：</td>
					<td>
					<input type="text" class="easyui-datetimebox" id="birthday" editable="false" name="birthday" value=""/>
					</td>
				</tr>
			</table>
			<table class="margin-bottom-20 table  no-border">
				<tr>
					<td class="text-center">
					<input type="button" value="确定" id="save"
						class="btn btn-info " style="width:80px;" onclick="save_addSysUser()" />
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>