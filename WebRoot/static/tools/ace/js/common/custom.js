//var urlparas = 'http://www.hatmsyj.com:8889';
//var urlpara = 'http://www.hatmsyj.com:1024';

var urlparas = 'http://${cyPath}';
var urlpara = 'http://www.hatmsyj.com:8086';
//获取url传递参数值
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}
function check_img(url)
{
    if (url.indexOf('img/kong.jpg')>=0) {
        return "";
    } else {
        return url
    }
}
function navMenu(_path) {
    $("li a").each(function () {
        var href = $(this).attr("href");
        if (_path == '/' + href) {
            $('.icon-dashboard').parent().parent().removeClass("active");
            $('.breadcrumb').find('.active').html($(this).find('span').html())
            $(this).parents("ul").parent("li").addClass("active");
            $(this).parent("li").addClass("active");
        }
    });
}

