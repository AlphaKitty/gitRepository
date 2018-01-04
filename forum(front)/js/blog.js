/**
 * Created by 张友谅 on 2017-3-30.
 */

window["pid"]="";
window["uid"]="";
window["applyTime"]="";
$(document).ready( function() {

    totop();
    /*获取从上一个页面传过来的信息*/
    var temp=window.location.search;
    // console.log(window.location.search);
    pid=temp.substring(temp.indexOf("pid=")+4);
    // console.log(pid);

    /*帖子详细页面作为一个模板,里面的内容都是即时添加*/
    $.ajax({
        type: "POST",
        // async: false,
        xhrFields: {
            withCredentials: true
        },
        contentType : 'application/json',
        crossDomain: true,
        url: "http://"+globalip+":8080/forum/post/querypostbypid",
        data : JSON.stringify({
            pid:pid
        }),
        error:function (msg) {
            console.log(msg);
        },
        success: function(data){
            // console.log(data);
            uid=data.user.uid;
            /*如果获取数据成功,则显示查到帖子的详细信息*/
            $('#post_title').html(data.title);
            $('#post_time').html(data.postTime);
            $('#post_user').html(data.user.username);
            $('#post_content').html(data.content);
            $('#signature').html(data.user.signature);
        }
    });

    /*这里回显的是回帖信息*/
    $.ajax({
        type: "POST",
        // async: false,
        xhrFields: {
            withCredentials: true
        },
        contentType : 'application/json',
        crossDomain: true,
        url: "http://"+globalip+":8080/forum/reply/queryreplybypid",
        data : JSON.stringify({
            post:{
                pid:pid
            }
        }),
        error:function (msg) {
            console.log("error");
        },
        success: function(data){
            // console.log(data);
            for(var i=0;i<data.length;i++){
                var html = '<div>';
                html += '<blockquote style="background-color: #ffffff;">';
                html += '<div style="float: right;color: #9b9999">';
                html += '#'+data[i].rid;
                html += '</div>';
                html += '<p style="font-size: smaller">';
                html += data[i].content;
                html += '</p>';
                html += '<hr>';
                html += '<a href="'+'personalinfo.html?uid='+data[i].user.uid+'">'+data[i].user.username+'</a>';
                html += '<br>';
                html += '<div style="font-size: smaller;float: right">';
                html += '<b>'+'发表于 '+data[i].replyTime+'</b>';
                html += '</div>';
                html += '</blockquote>';
                html += '</div>';
                $('.blog-post').append(html);
            }
            /*如果获取数据成功,则显示查到帖子回帖的详细信息*/
            // $('#post_title').html(data.title);
            // $('#post_time').html(data.postTime);
            // $('#post_user').html(data.user.username);
            // $('#post_content').html(data.content);
        }
    });

    console.log("这里是blog.js");
    /*回帖*/
    $('#reply_submit').click(function () {
        console.log("回帖的点击事件触发");

        var myDate = new Date();
        applyTime=myDate.getFullYear()+'年'+myDate.getMonth()+'月'+myDate.getDate()+'日  '+myDate.getHours()+':'+myDate.getMinutes()+':'+myDate.getSeconds();
        console.log(applyTime);
        // myDate.getYear();        //获取当前年份(2位)
        // myDate.getFullYear();    //获取完整的年份(4位,1970-????)
        // myDate.getMonth();       //获取当前月份(0-11,0代表1月)
        // myDate.getDate();        //获取当前日(1-31)
        // myDate.getDay();         //获取当前星期X(0-6,0代表星期天)
        // myDate.getTime();        //获取当前时间(从1970.1.1开始的毫秒数)
        // myDate.getHours();       //获取当前小时数(0-23)
        // myDate.getMinutes();     //获取当前分钟数(0-59)
        // myDate.getSeconds();     //获取当前秒数(0-59)
        // myDate.getMilliseconds();    //获取当前毫秒数(0-999)
        // myDate.toLocaleDateString();     //获取当前日期
        // var mytime=myDate.toLocaleTimeString();     //获取当前时间
        // myDate.toLocaleString( );        //获取日期与时间

        // class="'+"reply_forum"+'"
        var html = '<div>';
        html += '<blockquote style="background-color: #ffffff;">';
        html += '<p>';
        html += $("#reply_content").val();
        html += '</p>';
        html += '<hr>';
        // class='+"atright"+'
        html += '<a href="'+'personalinfo.html?uid='+uid+'">旧时明月</a>';
        html += '<br>';
        html += '<div style="font-size: smaller;float: right">';
        html += '<b>'+'发表于 '+myDate.getFullYear()+'年'+myDate.getMonth()+'月'+myDate.getDate()+'日  '+myDate.getHours()+':'+myDate.getMinutes()+':'+myDate.getSeconds()+'</b>';
        html += '</div>';
        html += '</blockquote>';
        html += '</div>';
        $('.blog-post').append(html);

        /*清空回帖区*/
        // $("#reply_content").val("");

        /*向数据库保存回帖*/
        $.ajax({
            type: "POST",
            async: false,
            xhrFields: {
                withCredentials: true
            },
            contentType : 'application/json',
            crossDomain: true,
            url: "http://"+globalip+":8080/forum/reply/saveorupdate",
            data : JSON.stringify({
                content:$("#reply_content").val(),
                replyTime:applyTime,
                user:{uid:uid},
                post:{pid:pid}
            }),
            error:function (msg) {
                console.log("error");
            },
            success: function(data){
                console.log(data);
            }
        });

        /*重新加载页面*/
        window.location.reload();
        /*跳到顶部*/
        totop();
    });

    /*跳到底部准备回帖*/
    $('#post_comment').click(function () {
        console.log("跳到底部的点击事件触发");
        tobottom();
    });

    /*跳到顶部*/
    $('#totoop').click(function () {
        console.log("跳到顶部");
        totop();
    });

});

function tobottom() {
    document.getElementsByTagName('BODY')[0].scrollTop=document.getElementsByTagName('BODY')[0].scrollHeight;
}

function totop() {
    document.getElementsByTagName('BODY')[0].scrollTop=0;
}