	// 根据条件查询，重新绑定datagrid
	function search_dict(){
// 		var dicttype =$("#dicttypes").combobox("getValue");
		var truename=$("#truename").val();
		// 调用后台方法进行
		$("#dictgrid").datagrid({
	            url:basePath+'/manager/SysUsersController/getXcyList.do',
	            queryParams:{
	            	truename:truename
	            },
	            pageNumber: 1,
				pageSize: 10
	     });
	}
	
	// 新增巡查员
	function add_manager(){
    	top.framework.openTab('巡查员操作', 'manager/SysUsersController/toaddxcy.do');
	}
	
	// 编辑巡查员
	function edit_dict(){
		// 获得选中的编号
		var selRows = $('#dictgrid').datagrid('getSelections');
		if(selRows.length !=1){
			top.$.messager.show({
				title:"错误提示",
				msg:"请您选择一个需要修改的数据",
				timeout:3000
			});
		}else{
			var id = selRows[0].id;
			top.framework.openTab('编辑巡查员', 'manager/SysUsersController/toeditxcy.do?id='+id);
		}
	}
	
	// 新增后的回调方法，用于提示并且刷新表格
	function addReload(){
		top.$.messager.show({
			title:"提示",
			msg:"操作成功",
			timeout:3000
		});
		$("#dictgrid").datagrid("reload");
	}
	
	// 删除选中的记录
	function del_dict(){
		// 获得选中的编号
		var selRows = $('#dictgrid').datagrid('getSelections');
		if(selRows == null){
			top.$.messager.alert('提示','请选择需要删除的数据','info');
		}else{
			top.$.messager.confirm('提示', '确定删除该记录？', function(r){
		    	if (r){
		    		var id = selRows[0].id;
		    		$.ajax({
		    			url: basePath+"/manager/SysUsersController/delxcy.do?id="+id,
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

	// 分配管理市场
	function change_law(){
		// 获得选中的编号
		var selRows = $('#dictgrid').datagrid('getSelected');
		if(selRows ==null){
			top.$.messager.alert('提示','请选择一个需要分配市场的执法人员','error');
		}else{
			// 弹出展示所有市场的页面
			var id = selRows.id;
			var username = selRows.username;
			  layer.open({
			  type: 2,
			  title: '执法人员分配市场',
			  shadeClose: true,
			  shade: 0.2,
			  area: ['380px', '80%'],
			  content: basePath+'/manager/SysUsersController/change_tolaw.do?userid='+id+'&username='+username
			}); 
		}
	}
	
	function showInfo(rows){
		return "<span class='btn btn-info' onclick='showMarket(\""+rows.id+"\")'> 查看市场</span>";
	}
	
	function showMarket(id){
		layer.open({
			 type:2,
			 title: '执法人员分配市场',
			 area: ['350px', '75%'],
			 content: basePath+'/manager/VmInspectorRelationMarketController/VmInspectorRelationMarketBySc.do?userid='+id
			}); 
			
	}
	
	function clearClick(){
		$("#truename").val("");
	}