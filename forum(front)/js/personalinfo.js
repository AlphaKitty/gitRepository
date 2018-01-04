/**
 * Created by Administrator on 2017-4-7.
 */
window["uid"]="";
var temp = window.location.search;
uid = temp.substring(temp.indexOf("=")+1);
$(document).ready( function() {
    console.log(uid);

    /*和用户基本信息相关的部分*/
    $.ajax({
        type: "POST",
        // async: false,
        xhrFields: {
            withCredentials: true
        },
        contentType : 'application/json',
        crossDomain: true,
        url: "http://"+globalip+":8080/forum/user/querybyid",
        data : JSON.stringify({
            "uid":uid
        }),
        error:function (msg) {
            console.log("error");
        },
        success: function(data){
            console.log(data);
            $('#username').html(data.username);
            $('#signature').html(data.signature);
        }
    });

    /*和帖子内容相关的部分*/
    $.ajax({
        type: "POST",
        // async: false,
        xhrFields: {
            withCredentials: true
        },
        contentType : 'application/json',
        crossDomain: true,
        url: "http://"+globalip+":8080/forum/user/querybyid",
        data : JSON.stringify({
            "uid":uid
        }),
        error:function (msg) {
            console.log("error");
        },
        success: function(data){
            console.log(data);
            $('#username').html(data.username);
            $('#signature').html(data.signature);
        }
    });
});