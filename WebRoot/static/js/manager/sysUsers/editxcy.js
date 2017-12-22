function chaUserName(){
	var username=$("#username").val();
	if(username.trim()==null||username.trim()==""){
		 alert("系统登陆名不能为空");  
	}else{
	$.ajax({
			url: basePath+"/manager/SysUsersController/checkUserName.do",
			type:"POST",
			data:{username:username},
			dataType:"json",
			async: false,
			success:function(data){
				//var mess = eval('(' + data + ')');
				if (data.flag == 'failed') {
					top.$.messager.alert("提示",data.message,"error");
					$("#username").val("");
					$("#username").focus();
				}
			}
		});
	}
}

	var menuname = '';
	// 保存管理员
	function save_manager(buttonName,buttonCode,page,action) {
		var mobilephone = $("#mobilephone").val();
		var tellphone=$("#tellphone").val();
		var truename=$("#truename").val();
		var enterpriseid=$("#enterpriseid").val();
	
	if(truename.trim()==null||truename.trim()==""){
		 alert("姓名不能为空");  
        return ; 
	}
	
	if(mobilephone.trim()==null||mobilephone.trim()==""){
		 alert("手机号码不能为空");  
        return ; 
	}
	if(enterpriseid.trim()==null||enterpriseid.trim()==""){
		 alert("所在网格不能为空");  
        return ; 
	}
		 if(!(/^1[34578]\d{9}$/.test(mobilephone))){ 
	        alert("手机号码有误，请重填");  
	        return ; 
	    } 
	    //香港00852
		//澳门00853
		//台湾00886
	    if(tellphone.trim()!=null&&tellphone.trim()!=""){
		 if(!(/^((0\d{2,3}-\d{7,8}))$/.test(tellphone))){ 
	        alert("固定电话有误，请重填");  
	        return ; 
	    } 
	    }
		$('#manager_form').form('submit',{
					url : basePath+"/manager/SysUsersController/editxcy.do?buttonName="+buttonName+"&buttonCode="+buttonCode+"&action="+action+"&page="+page,onSubmit : function() {
						return $(this).form('validate');
					},
					success : function(result) {
						var mess = eval('(' + result + ')');
						if (mess.error == 'true') {
							top.$.messager.alert("提示",mess.message,"error");
						} else {
							menuname = $("#menuname").val();
							top.framework.closeAndReloadTab('编辑巡查员', menuname,
									'xcygl');
						}
					}
				});
	}
	
	