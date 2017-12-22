<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Common/taglib/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>修改密码</title>
<%@ include file="Common/meta/meta.jsp" %>
<script type="text/javascript" src="${basePath}/static/tools/jquery/jquery-1.9.1.min.js"></script>
<%@ include file="/Common/easyui/easyui.jsp"%>
<script type="text/javascript">
	 function validatePwd(){ 
		var oldPassword=$("#oldPassword").val();
		var newPassword=$("#newPassword").val();
		var rnewPassword=$("#rnewPassword").val();
		$('#password_info').html("");
		if(oldPassword!=null && oldPassword!=""){
			$.ajax({ 
            type: "post", 
            url: "validatePwd.do", 
            data: "oldPassword="+$("#oldPassword").val(), 
            success: function(data){ 
<%--            	var data = eval('('+result+')');--%>
				if(data.error=='false'){
					top.$.messager.show({
						title:"提示",
						msg:data.message,
						timeout:3000
					});
				}else{
					top.$.messager.show({
						title:"提示",
						msg:data.message,
						timeout:3000
					});
					$("#oldPassword").focus();
				}
              } 
        	}); 
		}
    }
	function updatePassword(){
		var newPassword=$("#newPassword").val();
		var rnewPassword=$("#rnewPassword").val();
		if(newPassword!=rnewPassword){
			top.$.messager.show({
				title:"提示",
				msg:"两次输入密码不一致",
				timeout:3000
			});
			$("#rnewPassword").focus();
		}else{
		$("#updatePassword").form("submit",{
			url:"updatePassword.do",
			onsumbit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				var data = eval('('+result+')');
				if(data.error=='false'){
					top.$.messager.show({
						title:"提示",
						msg:data.message,
						timeout:3000
					});
				}else{
					top.$.messager.show({
						title:"提示",
						msg:data.message,
						timeout:3000
					});
				}
	        }		
		});
	}
}		
</script>
</head>
<body class="z-body">
	<form id="updatePassword" method="post">
	<div class="z-toolbar">
				<a id="a_save" href="#" plain="true" class="easyui-linkbutton"
					icon="icon-save" title="保存" onclick="updatePassword()">提交</a>
				<a id="a_undo" href="#" plain="true" class="easyui-linkbutton"
					icon="icon-undo"
					data-bind="click:rejectClick,linkbuttonDisable:readonly" title="撤消">撤消</a>
		</div>
	<div class="container_12" style="position:relative;">
			<div class="grid_1 lbl">旧密码</div>
			<div class="grid_2 val">
				<input class="z-txt easyui-validatebox" required="true" type="password" name="oldPassword" id="oldPassword" size="30" onblur="validatePwd()" />
				<span id="password_info" style="color:red"></span>
			</div>
			<div class="clear"></div>	
			<div class="grid_1 lbl">新密码</div>
			<div class="grid_2 val">
				<input class="z-txt easyui-validatebox" required="true" type="password" id="newPassword"  name="newPassword" size="30"/>
			</div>
			<div class="clear"></div>	
			<div class="grid_1 lbl">重新输入新密码</div>
			<div class="grid_2 val">
				<input class="z-txt easyui-validatebox" required="true" type="password" id="rnewPassword" name="rnewPassword" size="30" />
				<span id="rnewPassword_info" style="color:red"></span>
			</div>
		</div>	
	</form>	
</body>
</html>