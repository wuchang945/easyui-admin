<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>淮安市透明食药监督综合管理平台</title>
    <link href="static/css/manager/login/common.css" rel="stylesheet" type="text/css" />
    <script src="static/tools/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
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
                                zhmk += "<li><a href=\"" + item.url + "?systype=" + item.sysType +"\" class=\"fourSys\" ><img src=\"static/images/manager/setPage/4.png\" width=\"105\" height=\"153\" /></a></li>";
                            }
                            //透明生产
                            if (item.sysType == 2) {
                                content += "<li><a href=\"" + item.url + "?systype=" + item.sysType + "\" class=\"threeSys\"><img src=\"static/images/manager/setPage/3.png\" width=\"105\" height=\"153\"/></a></li>";
                            }
//                             透明餐饮
                            if (item.sysType == 3) {
                               
                                content += "<li><a href=\"" + item.url + "?systype=" + item.sysType + "\" class=\"twoSys\"><img src=\"static/images/manager/setPage/2.png\" width=\"105\" height=\"153\"/></a></li>";
                            }
//                             透明菜市场
                            if (item.sysType == 4) {
                                content += "<li><a href=\"" + item.url + "?systype=" + item.sysType + "\" class=\"firstSys\"><img src=\"static/images/manager/setPage/1.png\" width=\"105\" height=\"153\"/></a></li>";
                            }
                            //透明预包装
                            if (item.sysType == 5) {
                                content += "<li><a class=\"firstSys\" href=\"" + item.url + "?systype=" + item.sysType + "\"><img src=\"static/images/manager/setPage/tmybz.png\" width=\"105\" height=\"153\"/></a></li>";
                            }
                            //监督执法
                            if (item.sysType == 6) {
                                content += "<li><a href=\"" + item.url + "?systype=" + item.sysType + "\" class=\"firstSys\"><img src=\"static/images/manager/setPage/sjfx.png\" width=\"105\" height=\"153\"/></a></li>";
                            }
                            //透明药品
                            if (item.sysType == 7) {
                                content += "<li><a href=\"#\"  onclick=\"javascript:alert('系统开发中！')\"><img src=\"static/images/manager/setPage/tmyp.png\" width=\"105\" height=\"153\"/></a></li>";
                            }
                            //产品溯源
                            if (item.sysType == 8) {
                                content += "<li><a href=\"" + item.url + "\" class=\"firstSys\"><img src=\"static/images/manager/setPage/cpsy.png\" width=\"105\" height=\"153\"/></a></li>";
                            }
                        });
                        content += zhmk;
                        $(".loginSetting ul").html(content);
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
    <style>
    .loginSetting ul
    {
       width:100%;
        margin:0 auto;
        }
    </style>
</head>
<body>
    <img alt="" src="static/images/manager/login_bg/login.jpg" width="100%" height="100%" style="z-index: -1;" />
    <div class="loginHeader">
        <img alt="" src="static/images/manager/login_bg/logo.png" width="630" height="97" />
    </div>
    <div class="loginMiddle">
        <div class="loginSetting">
            <ul>
            </ul>
        </div>
    </div>
</body>
</html>
