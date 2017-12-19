/**
 * Created by rocking on 2017/6/20 0026.
 * 后台公用js
 */

//Data处理
$(function () {
    //底部二级菜单
    $('.query-date-menu').hide();
    $('.historyData').click(function() {
        $('.my2').hide();
        return $('.my1').toggle();
    });
    $('.reportData').click(function() {
        $('.my1').hide();
        return $('.my2').toggle();
    });
    $('.query-date').click(function() {
        $('.query-date').not($(this)).removeClass('selected');
        $(this).addClass('selected');
        $('#query-date span').text($(this).text());
    });
    //时间处理
    Date.prototype.pattern = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
            "H+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        var week = {
            "0": "/u65e5",
            "1": "/u4e00",
            "2": "/u4e8c",
            "3": "/u4e09",
            "4": "/u56db",
            "5": "/u4e94",
            "6": "/u516d"
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        if (/(E+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f" : "/u5468") : "") + week[this.getDay() + ""]);
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    };

    String.prototype.format = function () {
        var args = arguments;
        return this.replace(/\{(\d+)\}/g,
            function (m, i) {
                return args[i];
            });
    };

    $.ajaxSetup({
        //设置ajax请求结束后的执行动作
        complete: function (req, status, err) {
            var auth = req.getResponseHeader("SESSIONSTATUS");
            var auth_url = req.getResponseHeader("CONTEXTPATH");
            if (auth == 'noSession' && auth_url) {
                window.location.href = auth_url;
            }
        }, error: function (req, status, err) {
            var auth = req.getResponseHeader("SESSIONSTATUS");
            var auth_url = req.getResponseHeader("CONTEXTPATH");
            if (auth == 'noSession' && auth_url) {
                window.location.href = auth_url;
            }
        }
    });


});

//获取url中的参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}