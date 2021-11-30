/**
 * @author Ambar Singh
 * @email_id singhambar55@gmail.com
 **/
var stompClient = null;
var WEB_SOCKET_APP_PREFIX="/app";
var WEB_SOCKET_TOPIC="/topic";
var WEB_SOCKET_STOMP_ENDPOINT="/singhambar-websocket";
var WEB_SOCKET_RECEIVER_ENDPOINT="/receive-from-browser";
var WEB_SOCKET_SUBSCRIBER_ENDPOINT="/sent-to-browser";
var WEB_SOCKET_PUSH_NOTIFICATION_ENDPOINT = "/push-notification-to-browser";

function connect() {
    var socket = new SockJS(WEB_SOCKET_STOMP_ENDPOINT),
        btn = $("#connect");
    btn.text('Connecting ...')
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        btn.text('Connected')
        //Message subscriber
        stompClient.subscribe(WEB_SOCKET_TOPIC + WEB_SOCKET_SUBSCRIBER_ENDPOINT, function (res) {
            showServerMessage(JSON.parse(res.body).message);
        });
        //Push Notification subscriber
        stompClient.subscribe(WEB_SOCKET_TOPIC + WEB_SOCKET_PUSH_NOTIFICATION_ENDPOINT, function (res) {
            showPushNotification(JSON.parse(res.body).message);
        });
    });
}

function setConnected(connected) {
    var cBtn = $("#connect"),
        dBtn = $("#disconnect");
    cBtn.prop("disabled", connected);
    dBtn.prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
        dBtn.text('Disconnect from Server');
    }else {
        $("#conversation").hide();
        cBtn.text('Connect to Server');
    }
    $("#server-message").html("");
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    $("#disconnect").text("Disconnected");
}

function sendMessageToServer() {
    stompClient.send(WEB_SOCKET_APP_PREFIX + WEB_SOCKET_RECEIVER_ENDPOINT, {}, JSON.stringify({'message': $("#message").val()}));
}

function showPushNotification(message) {
    var div = $('.push-notification');
    div.text(message);
    div.fadeIn(1500).delay(5000).fadeOut(1000);
}

function showServerMessage(message) {
    $("#server-message").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function() { connect(); });
    $("#disconnect").click(function() { disconnect(); });
    $("#send").click(function() { sendMessageToServer(); });
});