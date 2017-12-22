//在右边center区域打开菜单，新增tab
function addTab(text, url,iframeid) {
    if ($("#tabs").tabs('exists', text)) {
    	$('#tabs').tabs('select', text);
    	var tab = $('#tabs').tabs('getSelected');
    	/* tab.panel('refresh', url); */
    	$('#tabs').tabs('update', {
    		tab: tab,
    		options: {
    			content : createFrame(url,iframeid)
    		}
    	});
    } else {
        $('#tabs').tabs('add', {
            title : text,
            closable : true,
            content : createFrame(url,iframeid)
        });
    }
}
//在右边center区域打开菜单，关闭tab
function closeTab(menu, type) {
    var curTabTitle = $(menu).data("tabTitle");
    var tabs = $("#tabs");
    
    if (type === "close") {
        tabs.tabs("close", curTabTitle);
        return;
    }
    
    var allTabs = tabs.tabs("tabs");
    var closeTabsTitle = [];
    
    $.each(allTabs, function () {
        var opt = $(this).panel("options");
        if (opt.closable && opt.title != curTabTitle && type === "Other") {
            closeTabsTitle.push(opt.title);
        } else if (opt.closable && type === "All") {
            closeTabsTitle.push(opt.title);
        }
    });
    
    for (var i = 0; i < closeTabsTitle.length; i++) {
        tabs.tabs("close", closeTabsTitle[i]);
    }
}

function createFrame(url,iframeid) {  
    var frame = '<iframe id="'+iframeid+'" name="'+iframeid+'" scrolling="no" frameborder="0px"  src="../' + url 
    var frame = '<iframe id="'+iframeid+'" name="'+iframeid+'" scrolling="auto" frameborder="0px"  src="../' + url 
//    + '" style=\"width:1190px;height:470px;\"></iframe>'; 
    + '" style=\"width:100%;height:100%;\"></iframe>';  
    return frame;  
}

function Wraper(){}
Wraper.prototype = {
		openTab : function(text, url) {
			addTab(text,url);
		},
		closeTab : function(text) {
			var tabs = $("#tabs");
			tabs.tabs("close", text);
		},
		closeAndReloadTab : function(closeText,reloadText,iframename) {
			var tabs = $("#tabs");
			tabs.tabs("close", closeText);
			var tab = tabs.tabs("getTab", reloadText);
			// 重新刷新		
			if (tab != null){
				if(iframename != null && iframename != ""){
					window.frames[iframename].addReload();
				}
			}
		}
};
window.framework= new Wraper();
$(function () {
	// 绑定左侧菜单
	initleftmenuByParent();
	//绑定tabs的右键菜单
    $("#tabs").tabs({
        onContextMenu : function (e, title) {
            e.preventDefault();
            $('#tabsMenu').menu('show', {
                left : e.pageX,
                top : e.pageY
            }).data("tabTitle", title);
        }
    });
	
    //操作选项卡 add by lichunyuan start
    $("#close").bind("click",function(){  
    	var tab = $('#tabs').tabs('getSelected');  
    	var index = $('#tabs').tabs('getTabIndex',tab); 
    	if(index==0){
    	}else{
    		$('#tabs').tabs('close',index);
    	}
    });  
    //关闭所有标签页  
    $("#closeall").bind("click",function(){  
        var tablist = $('#tabs').tabs('tabs');  
        for(var i=tablist.length-1;i>=1;i--){  
            $('#tabs').tabs('close',i);  
        }  
    });  
    //关闭其他页面（先关闭右侧，再关闭左侧）  
    $("#closeother").bind("click",function(){  
        var tablist = $('#tabs').tabs('tabs');  
        var tab = $('#tabs').tabs('getSelected');  
        var index = $('#tabs').tabs('getTabIndex',tab);  
        for(var i=tablist.length-1;i>index;i--){  
            $('#tabs').tabs('close',i);  
        }  
        var num = index-1;  
        if(num < 1){  
            return;  
        }else{  
            for(var i=num;i>=1;i--){  
                $('#tabs').tabs('close',i);  
            }  
            $("#tabs").tabs("select", 1);  
        }  
    });  
    
$("#closeright").bind("click",function(){
	 var tablist = $('#tabs').tabs('tabs');  
     var tab = $('#tabs').tabs('getSelected');  
     var index = $('#tabs').tabs('getTabIndex',tab);  
     for(var i=tablist.length-1;i>index;i--){  
         $('#tabs').tabs('close',i);  
     }  

});
$("#closeleft").bind("click",function(){ 
	 var tablist = $('#tabs').tabs('tabs');  
     var tab = $('#tabs').tabs('getSelected');  
     var index = $('#tabs').tabs('getTabIndex',tab);  
     var num = index-1;  
     if(num < 1){  
         return;  
     }else{  
         for(var i=num;i>=1;i--){  
             $('#tabs').tabs('close',i);  
         }  
         $("#tabs").tabs("select", 1);  
     }  
});
    
    
        //操作选项卡 add by lichunyuan end
    
  	//实例化menu的onClick事件
    $("#tabsMenu").menu({
        onClick : function (item) {
        	closeTab(this, item.name);
        }
    });
});

// 绑定系统的左侧菜单 -- 父栏目使用easyui的accrdion绑定
function initleftmenuByParent(){
	// 加载父栏目名称
	 var $obj = $('#wnav');
	    $obj.accordion({ animate: true, fit: true, border: true });
	    $.each(allmenus.menus, function () {
	    	var html = "";
	    	var parentid = "";
	    	if(this.parentid == "0"){
	    		 html = '<div title="'+this.name+'"  icon="'+this.code+'" style="overflow:auto;">';
	    		 parentid = this.id; // 父菜单id
	    		 html += '<ul>';
	    		 // 循环子菜单
    			 $.each(allmenus.menus, function(j, o) {
    				 if(o.parentid == parentid){
    					 var str="";
    					 if(o.url.indexOf("?")>0){
    						 str+="&moduleid="+o.id;
    					 }else{
    						 str+="?moduleid="+o.id;
    					 }
    					 html += '<li><div><a onclick="addTab(\''+o.name+'\',\''+o.url+str+'\',\''+o.menuPin+'\')" href="javascript:void(0)" ><span class="iconC '+o.code+'" ></span>' + o.name + '</a></div></li>';
    				 }
	    	     });
	 	        html += '</ul></div>';
	 	       $obj.accordion('add', {
		            title: this.name,
		            content: html,
		            iconCls: this.code,
		            border: false
		        });
	    	}
	    });
	    var panels = $obj.accordion('panels');
	    if(panels[0]!=null&&panels[0]!=""){
	    $obj.accordion('select', panels[0].panel('options').title);	    
	    $obj.find('li').click(function () {
	        $obj.find('li div').removeClass("selected");
	        $(this).children('div').addClass("selected");

	        var link = $(this).find('a');
	        var title = link.children('.nav').text();
	        var url = link.attr("rel");
	        var code = link.attr("ref");
	        var icon = link.children('.icon').attr('class');
	    }).hover(function () {
	        $(this).children('div').addClass("hover");
	    }, function () {
	        $(this).children('div').removeClass("hover");
	    });
	    }
}