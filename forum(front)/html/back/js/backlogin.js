/**
 * Created by Administrator on 2017-3-15.
 */

// window["fileName"] = "";

$(document).ready(function () {


    //以下为管理员登录验证
    $('#submit').click(function () {
        var username = console.log($("#username").val());
        var password = console.log($("#password").val());
        if("a"==username&&"a"==password){
            window.location.href="www.baidu.com";
        }else{
            console.log("用户名或密码错误!")
        }
        // $.ajax({
        //     type: "POST",
        //     contentType: 'application/json',
        //     url: "http://"+globleip+":8080/usercenter/user/queryByCondition",
        //     data: JSON.stringify({}),
        //     error: function (msg) {
        //         console.log("error");
        //     },
        //     success: function (data) {
        //         data=JSON.parse(data);
        //         jsonData = data;
        //         initTable();
        //     }
        // });

    });

});
