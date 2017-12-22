<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>淮安市透明食药监督综合管理平台</title>
<!--     <link href="static/css/manager/login/common.css" rel="stylesheet" type="text/css" /> -->
    <script src="static/tools/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${basePath }/static/css/manager/css/css_whole.css" />
    <script>
        $(function () {
            $.ajax({
                url: "loginController/userAuthority.do",
                type: "post",
                dataType: "json",
                success: function (data) {
                    if (data.length > 1) {
                        var content = "";
                        var zhmk = ""; //综合模块
                        $.each(data, function (i, item) {
                        //系统管理
                            if (item.sysType == 1) {
                                zhmk += "<div class='galleryImage Box2'><a href=\"" + item.url + "?systype=" + item.sysType +"\" class=\"post-img\" ><div><img src=\"static/images/manager/setPage/y7.png\"  /></div></a></div>";
                            }
                            //透明生产
                            if (item.sysType == 2) {
                                content += "<div class='galleryImage Box2'><a href=\"" + item.url + "?systype=" + item.sysType + "\" class=\"post-img\"><div><img src=\"static/images/manager/setPage/y1.png\"/></div></a></div>";
                            }
//                             透明餐饮
                            if (item.sysType == 3) {
                               
                                content += "<div class='galleryImage Box2'><a href=\"" + item.url + "?systype=" + item.sysType + "\" class=\"post-img\"><div><img src=\"static/images/manager/setPage/y2.png\"/></div></a></div>";
                            }
//                             透明菜市场
                            if (item.sysType == 4) {
                                content += "<div class='galleryImage Box2'><a href=\"" + item.url + "?systype=" + item.sysType + "\" class=\"post-img\"><div><img src=\"static/images/manager/setPage/y3.png\" /></div></a></div>";
                            }
                            //透明预包装
                            if (item.sysType == 5) {
                                content += "<div class='galleryImage Box2'><a class=\"post-img\" href=\"" + item.url + "?systype=" + item.sysType + "\"><div><img src=\"static/images/manager/setPage/y4.png\" /></div></a></div>";
                            }
                            //监督执法
                            if (item.sysType == 6) {
                                content += "<div class='galleryImage Box2'><a href=\"" + item.url + "?systype=" + item.sysType + "\" class=\"post-img\"><div><img src=\"static/images/manager/setPage/y5.png\" /></div></a></div>";
                            }
                            //透明药品
                            if (item.sysType == 7) {
                                content += "<div class='galleryImage Box2'><a href=\"#\"  onclick=\"javascript:alert('系统开发中！')\"><div><img src=\"static/images/manager/setPage/y6.png\" /></div></a></div>";
                            }
                            //产品溯源
//                             if (item.sysType == 8) {
//                                 content += "<li><a href=\"" + item.url + "\" class=\"firstSys\"><img src=\"static/images/manager/setPage/cpsy.png\" width=\"105\" height=\"153\"/></a></li>";
//                             }
                        });
                        content += zhmk;
                        $("#loginSetting").html(content);
                    }
                    else {
                        postData(data[0].sysType, data[0].url);
                    }
                }
            });
        });

        //传数据
        function postData(sys,url) {
            window.location.href=url+"?systype="+sys;
        }
    </script>
</head>
<body>
<body>
		<div class="main_box">
			<div class="logoimg"><img src="${basePath }/static/images/manager/setPage/logo.png" /></div>
			<div class="HomeContain content_bg ">
				<div class="BoxTop" id="loginSetting">
<!-- 					<div class="galleryImage Box1"> -->
<!-- 						<a class="post-img" href='wx/zjmlDK/index_33.html'> -->
<!-- 							<div><img src="images/y1.png" /></div> -->
<!-- 						</a> -->
<!-- 					</div> -->
<!--                     <div class="galleryImage Box2"> -->
<!-- 						<a class="post-img" href="mzpb/Scheduwx.html"> -->
<!-- 							<div><img src="images/y2.png" /></div> -->

<!-- 						</a> -->
<!-- 					</div> -->
<!--                     <div class="galleryImage Box2"> -->
<!-- 						<a class="post-img" href="mzpb/Scheduwx.html"> -->
<!-- 							<div><img src="images/y3.png" /></div> -->

<!-- 						</a> -->
<!-- 					</div> -->
<!-- 					<div class="galleryImage Box2"> -->
<!-- 						<a class="post-img" href="mzpb/Scheduwx.html"> -->
<!-- 							<div><img src="images/y4.png" /></div> -->

<!-- 						</a> -->
<!-- 					</div> -->
<!-- 					<div class="galleryImage Box2"> -->
<!-- 						<a class="post-img" href="mzpb/Scheduwx.html"> -->
<!-- 							<div><img src="images/y5.png" /></div> -->

<!-- 						</a> -->
<!-- 					</div> -->
<!-- 					<div class="galleryImage Box2"> -->
<!-- 						<a class="post-img" href="mzpb/Scheduwx.html"> -->
<!-- 							<div><img src="images/y6.png" /></div> -->

<!-- 						</a> -->
<!-- 					</div> -->
				</div>
			</div>
		</div>
	</body>
</body>
</html>
