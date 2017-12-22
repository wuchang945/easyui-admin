function TipMsg(msg) {
    top.showTips(msg);
}

String.prototype.isDate = function () {
    var arr = this.match(/^(\d{4})-(\d{2})-(\d{2})$/);
    if (arr == null) {
        arr = this.match(/^(\d{4})(\d{2})(\d{2})$/);
        if (arr == null) return null;
    }
    var dt = new Date(arr[1], arr[2] - 1, arr[3]);
    if (dt.getFullYear() == parseInt(arr[1]) && dt.getMonth() + 1 == parseInt(arr[2], 10) && dt.getDate() == parseInt(arr[3], 10))
        return dt;
};

function SetCookie(cookieName, cookieValue, nDays) {
    var today = new Date();
    var expire = new Date();
    if (nDays == null || nDays == 0) nDays = 1;
    expire.setTime(today.getTime() + 3600000 * 24 * nDays);
    document.cookie = cookieName + "=" + escape(cookieValue) + ";path=/;expires=" + expire.toGMTString();
}

function GetCookie(namex) {
    var cookieString = new String(document.cookie);
    var cookieHeader = namex + "=";
    var beginPosition = cookieString.indexOf(cookieHeader);
    if (beginPosition != -1) {
        cookieString = cookieString.substring(beginPosition + cookieHeader.length);
        if (cookieString.indexOf(";") > -1) {
            cookieString = cookieString.substring(0, cookieString.indexOf(";"));
        }
        return unescape(cookieString);
    }
    return "";
}

function GetCert(pentitycode, entitycode, id) {

    if (entitycode == "") return;

    var code = entitycode;
    var html = "  (索证：";
    $.ajax({
        type: "get",
        url: "../../Gsj/splt/spltManage.do?forward=CommonAJAX&type=cert&entitycode=" + encodeURI(code) + "&pentitycode=" + encodeURI(pentitycode),
        dataType: "json",
        success: function (obj) {
			
            if (obj.error == "") {
				
                for (var i = 0; i < obj.certs.length; i++) {
                    var cert = obj.certs[i];
		
					if(cert.CertType==1){
		
						html = html + "[<a href='../../upload/" + cert.CertPath + "' target='_blank'>S</a>]";
					}
                    if(cert.CertType==2){
						html = html + "[<a href='../../upload/" + cert.CertPath + "' target='_blank'>C</a>]";
					}
					if(cert.CertType==3){
						html = html + "[<a href='../../upload/" + cert.CertPath + "' target='_blank'>L</a>]";
					}
					if(cert.CertType==4){
						html = html + "[<a href='../../upload/" + cert.CertPath + "' target='_blank'>W</a>]";
					}
					if(cert.CertType==5){
						html = html + "[<a href='../../upload/" + cert.CertPath + "' target='_blank'>Y</a>]";
					}
                }
				html = html + ")";
                $("#" + id).html(html);
            } else {
                $("#" + id).html("");
            }
        }
    });
}


function GetCert2(pentitycode, entitycode, id) {
    if (entitycode == "") return;

    var code = entitycode;
    var html = "  (索证：";
    $.ajax({
        type: "get",
        url: "../common/ajax.aspx?ajax=" + Math.random() + "&type=cert&entitycode=" + encodeURI(code) + "&pentitycode=" + encodeURI(pentitycode),
        dataType: "json",
        success: function (obj) {
            if (obj.error == "") {
                for (var i = 0; i < obj.certs.length; i++) {
                    var cert = obj.certs[i];
                    html = html + "[<a href='" + cert.CertPath + "' target='_blank'>" + cert.CertType + "</a>]";
                }
                html = html + ")";
                $("#" + id).html(html);
            } else {
                $("#" + id).html("");
            }
        }
    });
}

function GetIpc(procode, ipc, id, entitycode) {
    if (procode == "" || ipc == "")
        return;

    $.ajax({
        type: "get",
        url: "../common/ajax.aspx?ajax=" + Math.random() + "&type=ipc&procode=" + procode + "&ipc=" + ipc + "&entitycode=" + encodeURI(entitycode),
        dataType: "json",
        success: function (obj) {

            if (obj.error == "") {
                if (obj.ipc.url != "") {
                    $("#" + id).html("<a href=\"javascript:ShowPicDialog('" + obj.ipc.url + "')\">有</a>");
                }
                else
                    $("#" + id).html("&nbsp;");
            } else {
                $("#" + id).html("&nbsp;");
            }
        }
    });
}
//用于处理乳制品管理相关的路径问题 因改动较大 所以新加方法
function GetIpcDariy(procode, ipc, id, entitycode) {
    if (procode == "" || ipc == "")
        return;

    $.ajax({
        type: "get",
        url: "../../common/ajax.aspx?ajax=" + Math.random() + "&type=ipc&procode=" + procode + "&ipc=" + ipc + "&entitycode=" + encodeURI(entitycode),
        dataType: "json",
        success: function (obj) {

            if (obj.error == "") {
                if (obj.ipc.url != "") {
                    $("#" + id).html("<a href=\"javascript:ShowPicDialog('" + obj.ipc.url + "')\">有</a>");
                }
                else
                    $("#" + id).html("&nbsp;");
            } else {
                $("#" + id).html("&nbsp;");
            }
        }
    });
}; 

function GetCertByType(entitycode, pEntitycode, type, id) {

    
    $.ajax({
        type: "get",
        url: "../common/ajax.aspx?ajax=" + Math.random() + "&type=getcert&entitycode=" + entitycode + "&certtype=" + type + "&pentitycode=" + pEntitycode,
        dataType: "json",
        success: function (obj) {
          
            if (obj.error == "") {

                if (obj.certs.outdate != "") {
                    $("#" + id + "_1").html(obj.certs.outdate.replaceAll("-",""));
                } else {
                    $("#" + id + "_1").html("无");
                }
                if (obj.certs.certcode != "") {
                    // $("#" + id).html(obj.certs.certcode);
                    $("#" + id).html(obj.certs.certcode);
                } else {
                    $("#" + id).html("无");
                }


            } else {
                $("#" + id).html("无");
                $("#" + id + "_1").html("无");
            }
        }
    })
}


function GetIpc(procode, ipc, id, entitycode, showimg) {
    if (procode == "" || ipc == "")
        return;

    $.ajax({
        type: "get",
        url: "../common/ajax.aspx?ajax=" + Math.random() + "&type=ipc&procode=" + procode + "&ipc=" + ipc + "&entitycode=" + entitycode,
        dataType: "json",
        success: function (obj) {

            if (obj.error == "") {
                if (obj.ipc.url != "")
                    $("#" + id).html("<a style='color:black;' href=\"javascript:ShowPicDialog('" + obj.ipc.url + "')\">有</a>");
                else
                    $("#" + id).html("&nbsp;");
            } else {
                $("#" + id).html("&nbsp;");
            }
        }
    });
}

function GetProNum(entitycode, id) {
    if (entitycode == "") return;
    var code = entitycode;
    var html = "";
    $.ajax({
        type: "get",
        url: "../common/ajax.aspx?ajax=" + Math.random() + "&type=getpronum&entitycode=" + code,
        dataType: "json",
        success: function (obj) {
            if (obj.error == "") {
                $("#" + id).html(obj.count);
            } else {
                $("#" + id).html("");
            }
        }
    });
}

/*
function GetAsyncCert(entitycode, id) {
    if (entitycode == "") return;
    var url = "http://192.168.0.5/spba4/ShareCert.aspx?type=cert&entitycode=" + entitycode + "&jsoncallback=?";
    $.getJSON(url, { hid: '1007' }, function (data) {
        var html = "";
        if (data.error != "")
            return;
        for (var i = 0; i < data.data.length; i++) {
            var cert = data.data[i];
            html = html + "[<a style='color:red' href='" + cert.path + "' target='_blank'>" + cert.type + "</a>]";
        }
        if (html != "") {
            $("#" + id).html(html);
        }
    });
}

function GetAsyncIPCCert(procode,ipc, id) {
    if (entitycode == "") return;
    var url = "http://192.168.0.5/spba4/ShareCert.aspx?type=ipccert&procode=" + entitycode + "&ipc"+ipc+"jsoncallback=?";
    $.getJSON(url, { hid: '1007' }, function (data) {
        var html = "";
        if (data.error != "")
            return;
        var cert = data.data;
        html = html + "<a style='color:red' href='" + cert.path + "' target='_blank'>" + cert.type + "</a>";
        if (html != "") {
            $("#" + id).html(html);
        }
    });
}
*/