<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
request.setCharacterEncoding("utf-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>百度地图</title>
<style type="text/css">
html {
	height: 100%
}

body {
	height: 96%;
	margin: 0px;
	padding: 0px;
	background-color: white;
}

#container {
	height: 80%;width:100%;
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=1.5&ak=SI1IjQ2BYFMl6jLf4NHfm7So">
	//v1.5版本的引用方式：src="http://api.map.baidu.com/api?v=1.5&ak=您的密钥"
	//v1.4版本及以前版本的引用方式：src="http://api.map.baidu.com/api?v=1.4&key=您的密钥&callback=initialize"
</script>
<script type="text/javascript" src="static/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
function confirmChose(){
	var jd=document.getElementById("jd").value;
	var wd=document.getElementById("wd").value;
	 if(jd==null || jd==""){
		 alert("请选择坐标");
	}
	 else{
		parent.$("#geographicalcoordinates").val(jd+","+wd);
		var index=parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
}
</script>
</head>

<body>
	<p class="head_nav_con_lab_p2">
		<!--存放地点-->
		地图中心点定位、或用鼠标点击定位！
		经度：<input type="text" readonly="readonly" id="jd" name="jd" size="10"> 
		纬度：<input type="text" name="wd" readonly="readonly" id="wd" size="10">
		<a id="a_refresh" href="#" plain="true" class="easyui-linkbutton"
			icon="icon-ok" title="查看" onclick="confirmChose()">确认选择</a> 
	</p>
	<p>
		搜索地址！
		城市：<input type="text" id="shi" name="shi" size="6" value="淮安市"> 
		地址：<input type="text" name="dizhi" id="dizhi" size="20">
		<a href="#" title="查看" onclick="showMap()">搜索</a> 
	</p>
	<div id="container"></div>
	<script type="text/javascript">
	showMap();
	function showMap(){
		var shi=document.getElementById("shi").value;
		var dizhi=document.getElementById("dizhi").value;
		var map = new BMap.Map("container");
		if(dizhi==null || dizhi==""){
			map.centerAndZoom("淮安", 13);
		}
		if(shi==null || shi=="" || dizhi==null || dizhi==""){
			 
		}
		else{
			var myGeo = new BMap.Geocoder();
			// 将地址解析结果显示在地图上,并调整地图视野
			myGeo.getPoint(dizhi, function(point){
				if (point) {
					map.centerAndZoom(point, 18);
					map.addOverlay(new BMap.Marker(point));
					var jd=point.lng;
					var wd=point.lat;
					document.getElementById("jd").value=jd;
					document.getElementById("wd").value=wd;
				}else{
					alert("您输入的地址没有找到，请输入其他关键字!");
				}
			}, shi);
		}
		
		map.addControl(new BMap.NavigationControl());
		map.addControl(new BMap.NavigationControl());
		map.addControl(new BMap.ScaleControl());
		map.addControl(new BMap.OverviewMapControl());
		map.addControl(new BMap.MapTypeControl());
		if(dizhi==null || dizhi==""){
			map.setCurrentCity("淮安");
		}
		map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		map.addEventListener("click",function(e){
			//alert(e.point.lng + "," + e.point.lat);
			var allOverlay = map.getOverlays();
			for(var i = 0; i <= allOverlay.length -1; i++){
				map.removeOverlay(allOverlay[i]);
			}
			map.addOverlay(new BMap.Marker(e.point));
			var jd=e.point.lng;
			var wd=e.point.lat;
			document.getElementById("jd").value=jd;
			document.getElementById("wd").value=wd;
		});
		map.addEventListener("dragend", function() {
			var center = map.getCenter();
			var allOverlay = map.getOverlays();
			for(var i = 0; i <= allOverlay.length -1; i++){
				map.removeOverlay(allOverlay[i]);
			}
			map.addOverlay(new BMap.Marker(center));
			//alert("地图中心点变更为：" + center.lng + ", " + center.lat);
			var jd=center.lng;
			var wd=center.lat;
			document.getElementById("jd").value=jd;
			document.getElementById("wd").value=wd;
		});
		
	}
	</script>
	
	
</body>
</html>