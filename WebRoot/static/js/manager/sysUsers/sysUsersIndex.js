$(function(){
$("#a_refresh").click(function(){
	addReload();
});
});
function search_user(){
	var username=$("#username").val();
	$("#sysUsersGrid").datagrid({
        url:"../UserController/getAllUsers.do",
        queryParams:{
        	username:username
        }
 });
}
//跳转到添加系统角色的页面
function addUser(){
	top.framework.openTab('新增用户', 'manager/UserController/toAddUser.do');
}

function edit_sysUsers(){
	// 获得选中的编号
	
	var selRows = $('#sysUsersGrid').datagrid('getSelections');
	if(selRows.length==0){
		top.$.messager.alert("提示","<span style='color:red'>请选择需要编辑的数据!</span>","info");
	}else if(selRows.length>=2){
		top.$.messager.alert("提示","<span style='color:red'>最多只能选择一条记录!</span>","info");
	}else{
		top.framework.openTab('编辑用户', '/manager/SysUsersController/toEditSysUsers.do?id='+selRows[0].id);
	}
}

//查看
function show_sysUsers(){
	// 获得选中的编号
	var selRows = $('#sysUsersGrid').datagrid('getSelections');
	if(selRows.length==0){
		top.$.messager.alert("提示","<span style='color:red'>请选择需要查看的数据!</span>","info");
	}else if(selRows.length>=2){
		top.$.messager.alert("提示","<span style='color:red'>最多只能选择一条记录!</span>","info");
	}else{
		top.framework.openTab('查看用户', '/manager/SysUsersController/toShowSysUsers.do?id='+selRows[0].id);
	}
}


function save_editSysUser(buttonName,buttonCode,page,action) {
	var username=$("#username").val();
	var truename=$("#truename").val();
	var roleid=$("#roleid").val();
	var systype=$("#systype").val();
	var enterpriseid=$("#enterpriseid").val();
	var typename=$("#typecode").find("option:selected").text();
	if(username==null||username==""){
		alert("用户名不能为空");
		return false;
	}
	if(truename==null||truename==""){
		alert("真实姓名不能为空");
		return false;
	}
	if(roleid==null||roleid==""){
		alert("角色不能为空");
		return false;
	}
	if(systype==null||systype==""){
		alert("所属系统不能为空");
		return false;
	}
	if(enterpriseid==null||enterpriseid==""){
		alert("企业不能为空");
		return false;
	}
	$('#sysUsers_form').form('submit',
			{
				url : basePath+"/manager/SysUsersController/editSysUsers.do?typename="+typename+"&buttonName="+buttonName+"&buttonCode="+buttonCode+"&action="+action+"&page="+page,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(data) {
					var mess=JSON.parse(data);
					if (mess.flag == 'true') {
						top.$.messager.show({
							title : "提示",
							msg : "<span style='color:blue'>"+mess.message+"</span>",
							timeout : 3000
						});
						addReload();
						top.framework.closeAndReloadTab('编辑用户', "用户管理",'yhgl');
					}else{
						top.$.messager.show({
							title : "提示",
							msg : "<span style='color:red'>"+mess.message+"</span>",
							timeout : 3000
						});
						addReload();
					} 
				}
			});
}
function save_addSysUser() {
	/*var username=$("#username").val();
	var truename=$("#truename").val();
	var roleid=$("#roleid").val();
	var systype=$("#systype").val();
	var enterpriseid=$("#enterpriseid").val();
	if(username==null||username==""){
		alert("用户名不能为空");
		return false;
	}
	if(truename==null||truename==""){
		alert("真实姓名不能为空");
		return false;
	}
	if(roleid==null||roleid==""){
		alert("角色不能为空");
		return false;
	}
	if(systype==null||systype==""){
		alert("所属系统不能为空");
		return false;
	}
	if(enterpriseid==null||enterpriseid==""){
		alert("企业不能为空");
		return false;
	}
	var typename=$("#typecode").find("option:selected").text();*/

	$('#sysUsers_form').form('submit',
			{
				url : basePath+"/manager/UserController/addUser.do",

				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(data) {
					var mess=JSON.parse(data);
					if (mess.flag == 'FAILED') {
						top.$.messager.show({
							title : "提示",
							msg : "<span style='color:blue'>"+mess.message+"</span>",
							timeout : 3000
						});
						//top.framework.closeAndReloadTab('新增用户', "用户管理",'yhgl');
					}else{
						top.$.messager.show({
							title : "提示",
							msg : "<span style='color:red'>"+mess.message+"</span>",
							timeout : 3000
						});
						top.framework.closeAndReloadTab('新增用户', "用户管理",'yhgl');
					} 
				}
			});
}
function addReload(){
	$("#sysUsersGrid").datagrid("reload");
}
function choseRoles (id){
	layer.open({
		type:2,
		title:"请选择角色",
		area:["45%","80%"],
		shade:0,
		content:basePath+"/manager/SysUsersController/toChooseRoles.do?id="+id
	})
}
function choseSys(id){
	layer.open({
		type:2,
		title:"请选择系统",
		area:["45%","70%"],
		shade:0,
		content:basePath+"/manager/SysUsersController/toChooseSys.do?id="+id
	})
}
function choseEnterprise(){
	layer.open({
		type:2,
		title:"请选择企业",
		area:["80%","80%"],
		shade:0,
		content:basePath+"/manager/SysUsersController/toChooseEnterprise.do"
	})
}


//选择权限用户下面所有的监管单位
function chosePsUnit(){
	layer.open({
		type:2,
		title:"请选择监管单位",
		area:["80%","80%"],
		shade:0,
		content:basePath+"/manager/psUnitController/toChoosePsUnit.do"
	})
}

function checkUserName(){
    $.ajax({
        url: basePath+"/manager/SysUsersController/checkUserName.do",
        type: "post",
        data: { "username": $.trim($("#username").val()) },
        dataType: "json",
        async: false,
        success: function (data) {
        if(data.flag=='success'){
        	$("#save").disabled=false; 
        }else{
        alert(data.message);
        $("#username").focus();
        $("#save").disabled=true;
        }
        },
        error: function (XMLHttpRequest, status) {
            if (status == "timeout") {
                alert("加载超时，请重试");
            } else {
                alert(XMLHttpRequest.responseText);
            }
        }
    });
}
		function  del_sysUsers(){
			
		
				// 获得选中的编号
				var selRows = $('#sysUsersGrid').datagrid('getSelections');
				if(selRows == null){
					top.$.messager.alert('提示','请选择需要删除的数据','info');
				}else{
					top.$.messager.confirm('提示', '确定删除该记录？', function(r){
				    	if (r){
				    		var id = selRows[0].id;
				    		$.ajax({
				    			url: basePath+"/manager/managerController/manager_del.do?id="+id,
				    			type:"POST",
				    			dataType:"json",
				    			success:function(data){
				    				if(data.error == "true"){
				    					top.$.messager.alert('提示','删除数据失败，请重新进行操作','error');
				    				}else{
				    					addReload();
				    				}
				    			}
				    		});
				    	}
				    });
				}
			}
		function ajaxFileUpload(id){
			//开始上传文件时显示一个图片,文件上传完成将图片隐藏
			//$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});
			//执行上传文件操作的函数
			$.ajaxFileUpload({
				//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
				url:basePath+'/manager/PsAttachController/fileUpload.do',
				secureuri : false, //是否启用安全提交,默认为false 
				fileElementId : id, //文件选择框的id属性
				dataType : 'json', //服务器返回的格式,可以是json或xml等
				success : function(data) { //服务器响应成功时的处理函数
					if (data.flag == 1) { //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
						$("input[name=icon]").val(data.file_Name);
						$("#imgsContent").attr("src", data.fileName);
						top.$.messager.show({
							type : 1,
							title : "提示",
							msg : "<span style='color:blue'>图片上传成功！</span>"
						});
					} else {
						top.$.messager.show({
							type : 1,
							title : "提示",
							msg : "<span style='color:red'>图片上传失败！</span>"
						});
					}
				},
				error : function(data, status, e) { //服务器响应失败时的处理函数
					$('#result').html('图片上传失败，请重试！！');
				}
			});
		}	
		function choseJob(){
			layer.open({
				type:2,
				title:"请选择职务",
				area:["80%","80%"],
				shade:0,
				content:basePath+"/manager/SysTypeController/toChoseJob.do"
			})
		}