/*根据前台输入查询*/
function search1(){
	var username = $("#username").val();
	var enterprisename = $("#enterprisename").val();
	$("#fdEnterpriseUserList").datagrid({
		url:basePath+"/manager/sysUsersController/selectEnterpriseUserByCondition.do",
		queryParams:{
			username:username,
			enterprisename:enterprisename,
			status:1
		},
		pageNumber: 1,
		pageSize: 10
	});
};


function addEnterpriseUser(){
	top.framework.openTab('新增企业用户','/manager/sysuser/toAddEnterpriseUser.do');
}
function act(msg,msg2){
	if(msg==2){
		return "<a onclick='changeStatus(1,\""+msg2+"\")'>启用</a>&nbsp;&nbsp;<a onclick='initPassword(\""+msg2+"\")'>还原密码</a>";
	}else if(msg==1){
		return "<a onclick='changeStatus(2,\""+msg2+"\")'>禁用</a>&nbsp;&nbsp;<a onclick='initPassword(\""+msg2+"\")'>还原密码</a>";
	}
};
/*修改用户状态（是否禁用）*/
function changeStatus(msg,msg2){
	$.ajax({
		type: "POST",
		url: basePath+"/manager/sysUsersController/changeFdUserStatus.do",
		data:  {'userstatus':msg,'id':msg2},
		dataType:"json",
		success: function(mess){
			$.messager.progress('close');// 如果提交成功则隐藏进度条
			if (mess.flag == 'error'){
				top.$.messager.alert("提示","<span style='color:red'>"+mess.message+"</span>","info");
			} else {
				$('#fdEnterpriseUserList').datagrid('reload');
			}
		}
	});
};

/**
 * 还原密码*/

function initPassword(msg){
	$.ajax({
		type: "POST",
		url: basePath+"/manager/sysUsersController/initFdUserPassword.do",
		data:  {'id':msg},
		dataType:"json",
		success: function(mess){
			$.messager.progress('close');// 如果提交成功则隐藏进度条
			if (mess.flag == 'error'){
				top.$.messager.alert("提示","<span style='color:red'>"+mess.message+"</span>","info");
			} else {
				top.$.messager.alert("提示","<span >"+mess.message+"</span>","info");
				$('#fdEnterpriseUserList').datagrid('reload');
			}
		}
	});
};
/**
 * 修改企业用户信息
 */
function editFdEnterpriseUser(){
	var fdEnterpriseUserList = $('#fdEnterpriseUserList').datagrid('getSelections');
	if(fdEnterpriseUserList.length==1){
		//后台获得该id的数据，跳转到详情页面
		top.framework.openTab('编辑企业用户','/manager/sysUsersController/toEditFdEnterpriseUser.do?id='+fdEnterpriseUserList[0].id);
	 }else{
		//未选或者选多了，返回提示信息‘请选择一条数据’
		top.$.messager.alert("提示","<span style='color:red'>请选择一条需要修改的企业用户!</span>","info");
	}
};
/**
 * 删除用户信息
 */
function deleteFdEnterpriseUser(){
	var fdEnterpriseUserList = $('#fdEnterpriseUserList').datagrid('getSelections');
	if(fdEnterpriseUserList.length==1){
		layer.confirm("确认删除该用户？", function (index) {
	       		//后台获得该id的数据，跳转到详情页面
			$.ajax({
				type: "POST",
				url: basePath+"/manager/sysUsersController/deleteFdEnterpriseUser.do",
				data:  {'id':fdEnterpriseUserList[0].id},
				dataType:"json",
				success: function(mess){
					if (mess.flag == 'error'){
						top.$.messager.alert("提示","<span style='color:red'>"+mess.message+"</span>","info");
					} else {
						$('#fdEnterpriseUserList').datagrid('reload');
					}
				}
			});
			 layer.close(index);
   		 });
		
	 }else{
		//未选或者选多了，返回提示信息‘请选择一条数据’
		top.$.messager.alert("提示","<span style='color:red'>请选择一条需要删除的企业用户!</span>","info");
	}
};

function addReload(){
	
	$("#fdEnterpriseUserList").datagrid("reload");
}
