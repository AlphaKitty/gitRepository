
window["username"]="";
window["password"]="";
$(document).ready( function() {

    // console.log(document.cookie);

    /*登录*/
    console.log("这里是userlogin.js");
    $('.btn-success').click(function () {
        console.log("登录按钮的点击事件触发");
        $.ajax({
            type: "POST",
            contentType : 'application/json',
            // async: false,
            secureuri:false,
            crossDomain: true,
            xhrFields: {
                withCredentials: true
            },
            url: "http://"+globalip+":8080/forum/user/login",
            data : JSON.stringify({
                username:$('#username').val(),
                password:$('#password').val()
            }),
            error:function (msg) {
                console.log(msg);
            },
            success: function(data){
                console.log(data);
                if("success"==data){
                    var url="../html/main_qgq.htm?username="+$('#username').val()+"password="+$('#password').val();
                    console.log(url);
                    window.location.href=url;
                }
                else{
                    alert("用户名或密码错误!");
                }
            }
        });
    });

    /*没有通过邮箱验证的简单注册*/
    $('#submit').click(function () {
        console.log("注册提交按钮的点击事件触发");
        $.ajax({
            type: "POST",
            contentType : 'application/json',
            async: false,
            secureuri:false,
            xhrFields: {
                withCredentials: true
            },
            url: "http://"+globalip+":8080/forum/user/register",
            data : JSON.stringify({
                email:$('#setemail').val(),
                password:$('#setpassword').val()
            }),
            error:function (msg) {
                console.log(msg);
            },
            success: function(data){
                console.log(data);
            }
        });
    });
    
    /*这里控制注册清空文本框内容*/
    $('#register').click(function () {
        console.log("清空文本框内容");
        $('#setemail').val("");
        $('#setpassword').val("");
        $('#repeatpassword').val("");
    })
});