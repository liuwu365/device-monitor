$(function () {
    commStyle(1, 4);
    $("#title_1").click(function () {
        commStyle(1, 4)
        $(".coldWarn tbody").html('');
    });
    $("#title_2").click(function () {
        commStyle(2, 4);
        $(".sewageWarn tbody").html('');
    });
    $("#title_3").click(function () {
        commStyle(3, 4);
        $(".waterWarn tbody").html('');
    });
    $("#title_4").click(function () {
        commStyle(4, 4);
        $(".boiler tbody").html('');
    });
    //tab隔行换色
    $("table:last tr:odd").addClass("odd");
    $("table:last tr:even").addClass("even");
});

//tab切换;n当前的,count总tab数
function commStyle(n, count) {
    for (var i = 1; i <= count; i++) {
        if (n == i) {
            $(".content_" + n).show();
            $("#hx_" + n).show();
            $("#hx_" + n).addClass('tabOn');
            $("#title_" + n).addClass('whiteColor');
        } else {
            $(".content_" + i).hide();
            $("#hx_" + i).hide();
            $("#hx_" + i).removeClass('tabOn');
            $("#title_" + i).removeClass('whiteColor');
        }
    }
}

