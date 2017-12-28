/**
 * Created by Administrator on 2017/12/27.
 */
var websocket = null;
if ('WebSocket' in window) {
    if (websocket == null) {
        websocket = new WebSocket("ws://192.168.1.110:8081/websocket");
    }
} else {
    alert('Not support websocket')
}
websocket.onerror = function () {
    console.log("连接出错!!!");
};
websocket.onopen = function (event) {
    console.log("连接已打开");
};
websocket.onmessage = function (event) {
    $("#message").html(event.data + "<br/>");
};
websocket.onclose = function () {
    console.log("连接已关闭");
};
window.onbeforeunload = function () {
    websocket.close();
};