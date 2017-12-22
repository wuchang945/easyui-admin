//弹出框使用
function dialog(title, type, width, height, content) {
    layer.open({
        type: type,
        skin: 'layui-layer-lan', //墨绿色
        title: [title, 'font-weight: 500;font-size:18px;font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;color: inherit;'],
        area: [width, height],
        content: content
    });
}

/*关闭弹窗*/
function CloseDialog() {
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭
}

// ajax请求
function AjaxJson(url, callBack) {
    var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
    $.ajax({
        url: url,
        type: "post",
        dataType: "json",
        async: true,
        success: function (data) {
            layer.close(index);
            callBack(data);
        },
        error: function (data) {
            layer.close(index);
            showMessage("失败");
        }
    });
}
//====提取url参数
$.extend({
    getUrlParams: function () {
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for (var i = 0; i < hashes.length; i++) {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    },
    getUrlParam: function (name) {
        return $.getUrlParams()[name];
    }
});

// 判断是否为空
function IsNull(val) {
    if (typeof (val) == "undefined" || val == undefined || val == "" || val == 'null' || val == 'undefined') {
        return "";
    }
    else {
        return val;
    }
}

// 判断是否为空
function IsEmpty(val) {
    if (typeof (val) == "undefined" || val == undefined || val == "" || val == 'null' || val == 'undefined') {
        return true;
    }
    else {
        return false;
    }
}


function findFileSuffix(resFileName) {
    // 取得文件名和后辍
    var suffix = "";
    if (resFileName.indexOf(".") != -1) {
        suffix = resFileName.substring(resFileName.lastIndexOf("."));
    }

    return suffix;
}


//获取页面内容
function AjaxHtml(url, callBack, dType) {
    $.ajax({
        url: url,
        type: "get",
        dataType: dType,
        async: false,
        success: function (data) {
            callBack(data);
        },
        error: function (data) {
            LayMsg("加载超时！");
        }
    });
}

// 表单验证
function Validate(formName, msgTipName, btnName, callBack) {
    formSubmit = $(formName).Validform({
        tiptype: function (msg, o, cssctl) {
            var objtip = $(msgTipName);
            cssctl(objtip, o.type);
            objtip.text(msg);
        },
        postonce: true,
        ajaxPost: true,
        beforeSubmit: function () {
            $(btnName).prop("disabled", true);
        },
        callback: function (data) {
            callBack(data);
            // if (data.status == 1) {
            //     callBack(data);
            //     // var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            //     // parent.layer.close(index); //再执行关闭
            // }
            // else if (data.status == 2) {
            //     LayMsg(data.msg);
            //     parent.window.location = "/Manage/frame/login.htm";
            // }
            // else if (data.status == 0) {
            //     window.parent.LayMsg(data.msg);
            //     var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            //     parent.layer.close(index); //再执行关闭
            // }
            // else if (data.status == 4) {
            //     callBack(data);
            // }
            // else {
            //     $("#msg").removeClass();
            //     $("#msg").text("");
            // }
        }
    });
}

// 提示
function showMessage(msg) {
    layer.msg(msg);
}

// 右下角提示信息
function showSuccessMsg(msg) {
    top.$.messager.show({
        title: "提示",
        msg: "<span style='color:blue'>" + msg + "</span>",
        timeout: 3000
    });
}

// 右下角提示信息
function showErrorMsg(msg) {
    top.$.messager.show({
        title: "提示",
        msg: "<span style='color:red'>" + msg + "</span>",
        timeout: 3000
    });
}

/*
 自动给控件赋值
 */
function SetWebControls(data) {
    try {
        for (var key in data) {
            var name = $("[name='" + key + "']");
            if (name.attr('name')) {
                var value = $.trim(data[key]).replace("&nbsp;", "").replace(/</g, "<").replace(/>/, ">");
                var type = name.attr('type');
                switch (type) {
                    case "checkbox":
                        if (value == "是") {
                            name.attr("checked", 'checked');
                        } else {
                            name.removeAttr("checked");
                        }
                        break;
                    case "radio":
                        $("input[name='" + key + "'][value='" + value + "']").attr("checked", 'checked');
                        break;
                    case "select":
                        $("select[name='" + key + "']").find("option:selected").removeAttr("selected");
                        $("select[name='" + key + "']").find("option[value='" + value + "']").attr("selected", true);
                        break;
                    default:
                        name.val(value);
                        break;
                }
            }
        }
    } catch (e) {
        alert(e)
    }
}

/**
 * 获取动态table：键、值，返回JSON
 * var GetTableData = GetTableDataJson("table的ID");
 */
function GetTableDataJson(tableId) {
    var item_Key_Value = "";
    var index = 1;
    var trjson = "";
    if ($(tableId + " tbody tr").length > 0) {
        $(tableId + " tbody tr").each(function () {
            var tdjson = "";
            $(this).find('td').find('input,select,textarea').each(function () {
                var pk_id = $(this).attr('id');
                var pk_value = "";
                if ($("#" + pk_id).attr('type') == "checkbox") {
                    if ($("#" + pk_id).attr("checked")) {
                        pk_value = "1";
                    } else {
                        pk_value = "0";
                    }
                } else {
                    pk_value = $("#" + pk_id).val();
                }
                var array = new Array();
                array = pk_id.split("➩"); //字符分割
                tdjson += '"' + array[0] + '"' + ':' + '"' + $.trim(pk_value) + '",'
            })
            tdjson = tdjson.substr(0, tdjson.length - 1);
            trjson += '{' + tdjson + '},';
        });
    }
    else {
        $(tableId + " tr").each(function () {
            var tdjson = "";
            $(this).find('td').find('input,select,textarea').each(function () {
                var pk_id = $(this).attr('id');
                var pk_value = "";
                if ($("#" + pk_id).attr('type') == "checkbox") {
                    if ($("#" + pk_id).attr("checked")) {
                        pk_value = "1";
                    } else {
                        pk_value = "0";
                    }
                } else {
                    pk_value = $("#" + pk_id).val();
                }
                var array = new Array();
                array = pk_id.split("➩"); //字符分割
                tdjson += '"' + array[0] + '"' + ':' + '"' + $.trim(pk_value) + '",'
            })
            tdjson = tdjson.substr(0, tdjson.length - 1);
            trjson += '{' + tdjson + '},';
        });
    }
    trjson = trjson.substr(0, trjson.length - 1);
    if (trjson == '{}') {
        trjson = "";
    }
    return '[' + trjson + ']';
}

// 文件上传
function ajaxFileUpload(id) {
    //开始上传文件时显示一个图片,文件上传完成将图片隐藏
    //$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});
    //执行上传文件操作的函数
    $.ajaxFileUpload({
        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
        url: basePath + '/manager/PdLicenseController/fileUpload.do',
        secureuri: false, //是否启用安全提交,默认为false
        fileElementId: id, //文件选择框的id属性
        dataType: 'json', //服务器返回的格式,可以是json或xml等
        success: function (data) { //服务器响应成功时的处理函数
            if (data.flag == 1) { //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
                $("#form1").append("<input type='hidden' name='" + id.replace("file", "image") + "' value='" + data.file_Name + "'>");
                var fileSuffix = findFileSuffix(data.file_Name)
                    .toLowerCase();
                if (fileSuffix != "" && fileSuffix != ".jpg"
                    && fileSuffix != ".png" && fileSuffix != ".bmp"
                    && fileSuffix != ".jpeg" && fileSuffix != ".jif") {
                    $("#" + id.replace("file", "container")).append("<a target='_blank' class='grid_link' href='" + data.fileName + "'><span class='upload_img'>文件</a>");
                } else {
                    $("#" + id.replace("file", "container")).append("<a target='_blank' href='" + data.fileName + "'><img src='" + data.fileName + "' class='upload_img'></a>");
                }

                var file = $("#" + id);
                file.after(file.clone().val(""));
                file.remove();
                showMessage("上传成功");
            } else {
                showMessage("上传失败");
            }
        },
        error: function (data, status, e) { //服务器响应失败时的处理函数
            showMessage("上传失败");
        }
    });
}

/**
 * 获取选中行
 * @param object table对象
 * @returns {null}
 */
function getSelectRow(object) {
    var selRow = object.datagrid('getSelected');
    if (selRow == null) {
        return null;
    }
    return selRow.id;
}