<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>淮安市透明食药监督综合管理平台</title>
    <link href="${basePath}/static/css/manager/login/common.css" rel="stylesheet" type="text/css" />
    <script src="${basePath}/static/tools/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script>
        /*回车事件触发*/
        document.onkeydown = function (e) {
            var theEvent = window.event || e;
            var code = theEvent.keyCode || theEvent.which;
            if (code == 13) {
                $("#btn_login").click();
            }
        }
        
function loadTopWindow(){ 
    if (window.top!=null && window.top.document.URL!=document.URL){ 
        window.top.location= document.URL; //这样就可以让登陆窗口显示在整个窗口了 
    } 
}
        
        var result = true;
        $(function () {
            //非极速模式下   
            //极速模式下的 值为 Netscape  
            //if (navigator.appName === 'Microsoft Internet Explorer') {
            //    //动态添加 meta元素 并设置相关项  
            //    var headV = document.getElementById("header");
            //    var metaV = document.createElement('meta');
            //    metaV.name = 'renderer';
            //    metaV.content = 'webkit';
            //    headV.appendChild(metaV);
            //}

            $("#btn_login").click(function () {
                if ($("#username").val() == "") {
                    alert("用户名不能为空！");
                    $("#username").focus();
                    return false;
                }
                if ($("#password").val() == "") {
                    alert("密码不能为空！");
                    $("#password").focus();
                    return false;
                }
                $.ajax({
                    url: "${basePath}/loginController/login.do",
                    type: "post",
                    data: { "username": $.trim($("#username").val()), "password": $.trim($("#password").val()) },
                    dataType: "json",
                    async: false,
                    success: function (data) {
                    if(data.flag=='true'){
                           window.location.href = "${basePath}/loginController/index.do";
                    }else{
                    alert(data.message);
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
            });
        });
    </script>
</head>
<body onload="loadTopWindow()">
    <img alt="" src="${basePath}/static/images/manager/login_bg/login.jpg" width="100%" height="100%" style="z-index: -1;" />
    <div class="loginHeader">
        <img alt="" src="${basePath}/static/images/manager/login_bg/logo.png" width="630" height="97" />
    </div>
    <div class="loginMiddle">
        <div class="loginContent">
            <div class="loginItem">
                <span class="loginItem-title"><img alt="" src="${basePath}/static/images/manager/login_bg/user.png" />用户名</span>
                <input id="username" name="username" type="text" class="loginItem-input" />
            </div>
            <div class="loginItem">
                <span class="loginItem-title"><img alt="" src="${basePath}/static/images/manager/login_bg/lock.png" />密&nbsp;&nbsp;&nbsp;&nbsp;码</span>
                <input id="password" name="password" type="password" class="loginItem-input" />
            </div>
            <div class="loginItemSubmit">
                <button id="btn_login" name="btn_login" style="outline:none;">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
            </div>
        </div>
    </div>
</body>
</html>
