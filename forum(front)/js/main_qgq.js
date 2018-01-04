/**
 * Created by 张友谅 on 2017-3-30.
 */
window["username"]="";
window["password"]="";

$(document).ready( function() {

    console.log("这里是main_qgq.js");
    totop();
    /*初始化页面时显示所有帖子*/
    $.ajax({
        type: "POST",
        // async: false,
        xhrFields: {
            withCredentials: true
        },
        contentType : 'application/json',
        crossDomain: true,
        url: "http://"+globalip+":8080/forum/post/querypostbycondition",
        data : JSON.stringify({}),
        error:function (msg) {
            console.log("error");
        },
        success: function(data){
            var json=JSON.parse(data);
            // console.log(json);
            /*如果向后台获取数据成功,则显示所有查到的帖子*/
            for(var i=0;i<json.length;i++){
                var html = '<div>';
                html += '<blockquote style="background-color: #ffffff">';
                html += '<h3 style="background-color: #e3e3e3">'+'<a href="../html/blog.htm?pid='+json[i].pid+'"> '+json[i].title+'</a>'+'</h3>';
                html += '<p>';
                html += json[i].content;
                html += '</p>';
                html += '<hr>';
                html += '<a href="'+'personalinfo.html?uid='+json[i].user.uid+'">'+json[i].user.username+'</a>';
                html += '<br>';
                html += '<div style="font-size: smaller;float: right">';
                html += '<b>'+'发表于 '+json[i].postTime+'</b>';
                html += '</div>';
                html += '</blockquote>';
                html += '</div>';
                $('.marketing').append(html);
            }
        }
    });

    /*获取从上一个页面传过来的信息*/
    var temp=window.location.search;
    // console.log(window.location.search);
    username=temp.substring(temp.indexOf("username=")+9,temp.indexOf("password="));
    // console.log(username);
    password=temp.substring(temp.indexOf("password=")+9);
    // console.log(username);

    /*发帖*/
    $('#ft_btn').click(function () {

        console.log("发帖的点击事件触发");
        /*发帖,显示在页面上的数据*/
        var myDate = new Date();
        var time=myDate.getFullYear()+'年'+myDate.getMonth()+'月'+myDate.getDate()+'日  '+myDate.getHours()+':'+myDate.getMinutes()+':'+myDate.getSeconds();
        var html = '<div>';
        html += '<blockquote style="background-color: #ffffff">';
        html += '<h3 style="background-color: #e3e3e3">'+$("#ft_title").val()+'</h3>';
        html += '<p>';
        html += $("#ft").val();
        html += '</p>';
        html += '<hr>';
        // class='+"atright"+'
        html += '<a href="'+'personalinfo.html?username='+'">'+username+'</a>';
        html += '<br>';
        html += '<div style="font-size: smaller;float: right">';
        html += '<b>'+'发表于 '+time+'</b>';
        html += '</div>';
        html += '</blockquote>';
        html += '</div>';
        $('.marketing').append(html);

        /*发帖,向数据库插入记录*/
        $.ajax({
            type: "POST",
            async: false,
            xhrFields: {
                withCredentials: true
            },
            contentType : 'application/json',
            crossDomain: true,
            url: "http://"+globalip+":8080/forum/post/saveorupdate",
            data : JSON.stringify({
                user:{"uid":123},
                title:$("#ft_title").val(),
                postTime:time,
                content:$("#ft").val()
            }),
            error:function (msg) {
                console.log("error");
            },
            success: function(data){
                console.log("success");
            }
        });

        /*清空发帖区*/
        $("#ft_title").val("");
        $("#ft").val("");

        /*重新加载页面*/
        window.location.reload();
        totop();
    });

    /*跳到底部*/
    $('#post').click(function () {
        console.log("跳到底部的点击事件触发");
        tobottom();
    });

});

function tobottom() {
    document.getElementsByTagName('BODY')[0].scrollTop=document.getElementsByTagName('BODY')[0].scrollHeight;
}

function totop() {
    document.getElementsByTagName('BODY')[0].scrollTop=0;
}