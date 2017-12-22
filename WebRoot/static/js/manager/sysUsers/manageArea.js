$(function(){ 
	onloadback();
});

function onloadback(){
    $('#tree').treegrid({ 
url:'../../manager/sysUsersController/getManagerAreaData.do', 
iconCls: 'icon-ok',
idField: 'id',
treeField:'text', 
animate:true,
collapsible: true,
fitColumns: true,
columns:[[ 
{title:'区域名称',field:'text',width:400,height:'50px'},
{title:'操作',field:'operation',width:400,height:'50px'}
]],
onBeforeExpand : function(row) {
      if (row) {
        //点击展开分类要根据当前行的“分类id”查找其下的所有分类
        $(this).treegrid('options').url = "../../manager/sysUsersController/getManagerAreaData.do";
        } else {
          }
 }
}); 
}
   
   
   function settreevalue()
{
     
    func.getFuncTreeJson(function(res)
    {
         
        if(res.success)
            {
         
            $('#tg').treegrid("loadData", res.jsonArray);
            }
    });
}
 
function formatProgress(value) {
    if (value) {
        var s = '<div style="width:100%;border:1px solid #ccc">'
                + '<div style="width:' + value
                + '%;background:#cc0000;color:#fff">' + value + '%' + '</div>'
        '</div>';
        return s;
    } else {
        return '';
    }
}
function onContextMenu(e, row) {
    e.preventDefault();
    $(this).treegrid('select', row.id);
    $('#mm').menu('show', {
        left : e.pageX,
        top : e.pageY
    });
}
 
function insert(){
 
    var _data = {"fname":"yuheng","fid":"111"};
    var row = $('#tree').treegrid('getSelected');
      
    $('#tree').treegrid('append',{
        parent: row.fid,  // 这里指定父级标识就可以了
        data: [_data]
    });
      
    $('#tree').treegrid('beginEdit',_data.id);
    
}
function remove() {
    var node = $('#tree').treegrid('getSelected');
    if (node) {
        $('#tree').treegrid('remove', node.id);
    }
}
 

function addNewArea(){
	var node = $('#tree').treegrid('getSelected');
	layer.open({
		type:2,
		title:"添加区域",
		skin: 'demo-class',
		maxmin:true,
		area:["70%","70%"],
		content:"../../manager/sysUsersController/addNewArea.do?areaId="+node.id
	})
}

//增加区域
function saveNewArea(){
	$('#area_form').form('submit',
			{
				url : basePath+'/manager/SysUsersController/saveManageArea.do',
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(data) {
					var mess=JSON.parse(data);
					if (mess.flag == 'success') {
						top.$.messager.show({
							title : "提示",
							msg : "<span style='color:blue'>"+mess.message+"</span>",
							timeout : 3000
						});
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
						parent.addReload();
					}else{
						top.$.messager.show({
							title : "提示",
							msg : "<span style='color:red'>"+mess.message+"</span>",
							timeout : 3000
						});
					} 
				}
			});
	
}

//跳转到编辑页面
function editArea(){
	var node = $('#tree').treegrid('getSelected');
	layer.open({
		type:2,
		title:"编辑区域",
		skin: 'demo-class',
		maxmin:true,
		area:["70%","70%"],
		content:"../../manager/sysUsersController/toEditArea.do?areaId="+node.id
	})
}

//删除
function delArea(){
	$.messager.confirm("<span style='color:red'>提示</span>","<span style='color:red'>删除该区域后，该区域下所有的企业主体和监管单位将会被删除，确认删除?</span>",function(r){
	    if (r){
	    	var node = $('#tree').treegrid('getSelected');
			$.post(basePath+"/manager/SysUsersController/deleteArea.do",{id:node.id},function(data){
				$('#tree').treegrid('remove', node.id);
			})
	    }
	});
}

//编辑保存
function modifyArea(){
	$('#area_form').form('submit',
			{
				url : basePath+'/manager/SysUsersController/modifyManageArea.do',
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(data) {
					var mess=JSON.parse(data);
					if (mess.flag == 'success') {
						top.$.messager.show({
							title : "提示",
							msg : "<span style='color:blue'>"+mess.message+"</span>",
							timeout : 3000
						});
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
						parent.addReload();
					}else{
						top.$.messager.show({
							title : "提示",
							msg : "<span style='color:red'>"+mess.message+"</span>",
							timeout : 3000
						});
					} 
				}
			});
	
}

//刷新
function addReload(){
	onloadback();
}