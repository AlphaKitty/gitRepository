/**
 * Created by Administrator on 2017-3-15.
 */

window["variableuid"] = 0;

$(document).ready(function () {

    //第一级所有用户
    $('.alluser').click(function () {
        $("#usertable").remove();
        $("#posttable").remove();
        $("#condition").remove();
        $.ajax({
            type: "POST",
            contentType: 'application/json',
            url: "http://" + globalip + ":8080/forum/user/queryalluser",
            data: JSON.stringify({}),
            error: function (msg) {
                console.log("error");
            },
            success: function (data) {
                // data=JSON.parse(data);
                jsonData = data;
                initUserTable();
            }
        });

    });

    //显示选中用户发过的所有帖子
    $('.container').on('click',".posts", function(){
        var finalid = $(this)[0].value;
        variableuid = finalid;
        $("#usertable").remove();
        $("#posttable").remove();
        $("#condition").remove();
        $.ajax({
            type: "POST",
            contentType: 'application/json',
            url: "http://" + globalip + ":8080/forum/post/querypostbyuid",
            data: JSON.stringify({
                user:{
                    uid:finalid
                }
            }),
            error: function (msg) {
                console.log("error");
            },
            success: function (data) {
                // data=JSON.parse(data);
                jsonData = data;
                initPostTable();
            }
        })
    });

    //第二级条件查询
    $('.conditionselect').click(function () {
        $("#usertable").remove();
        $("#posttable").remove();
        $("#condition").remove();
        conditionSelect();
    });

    //删除选中的帖子
    $('.container').on('click',".postdelete", function() {
        var finalid = $(this)[0].value;
        $.ajax({
            type: "POST",
            contentType: 'application/json',
            url: "http://"+globalip+":8080/forum/post/deletepost",
            data: JSON.stringify({
                pid:finalid
            }),
            error: function (msg) {
                console.log("error");
            },
            success: function (data) {
                jsonData = data;
                $("#posttable").remove();
                $.ajax({
                    type: "POST",
                    contentType: 'application/json',
                    url: "http://"+globalip+":8080/forum/post/querypostbyuid",
                    data: JSON.stringify({
                        user:{
                            uid:variableuid
                        }
                    }),
                    error: function (msg) {
                        console.log("error");
                    },
                    success: function (data) {
                        // data=JSON.parse(data);
                        jsonData = data;
                        initPostTable();
                    }
                });
            }
        });
    });

});

//初始化动态User表生成
function initUserTable() {
    var html = '<table border=1 id="usertable">';
    html += '<tr>';
    html += '<td>' + "uid" + '</td>';
    html += '<td>' + "username" + '</td>';
    html += '<td>' + "password" + '</td>';
    html += '<td>' + "email" + '</td>';
    html += '<td>' + "portrait" + '</td>';
    html += '<td>' + "regdate" + '</td>';
    html += '<td>' + "posts" + '</td>';
    html += '<td>' + "signature" + '</td>';
    html += '<td>' + "code" + '</td>';
    html += '<td>' + "uiid" + '</td>';
    html += '<td>' + "" + '</td>';
    html += '<td>' + "" + '</td>';
    html += '</tr>';
    for (var j = 0; j < jsonData.length; j++) {
        var obj = jsonData[j];
        html += '<tr>';
        html += '<td>' + obj.uid + '</td>';
        html += '<td>' + obj.username + '</td>';
        html += '<td>' + obj.password + '</td>';
        html += '<td>' + obj.email + '</td>';
        html += '<td>' + obj.portrait + '</td>';
        html += '<td>' + obj.regdate + '</td>';
        html += '<td>' + obj.posts + '</td>';
        html += '<td>' + obj.signature + '</td>';
        html += '<td>' + obj.code + '</td>';
        html += '<td>' + obj.uiid + '</td>';
        // if(obj.pic.length==0){
        //     html += '<td  ><a href="#" onclick="box()"><button value="'+obj.id+'" class ="filedownload">简历下载</button> '+ '</a></td>';
        // }else{
        //     html += '<td  ><a href="'+'http://"+globalip+":8080/usercenter/user/download?fileName='+obj.pic+'"><button value="'+obj.id+'" class ="filedownload">简历下载</button> '+ '</a></td>';
        // }
        // // html += '<td  ><button value="'+obj.id+'" class ="filedownload">简历下载</button> '+ '</td>';
        html += '<td  ><button style="color: #000;" value="' + obj.uid + '" class ="posts">TA的帖子</button> ' + '</td>';
        html += '<td  ><button style="color: #000;" value="' + obj.uid + '" class ="delete">删除</button> ' + '</td>';
        html += '</tr>'
    }
    html += '</table>';

    $('.container').append(html);
}

//初始化动态Post表生成
function initPostTable() {
    var html = '<table border=1 id="posttable">';
    html += '<tr>';
    html += '<td>' + "pid" + '</td>';
    html += '<td>' + "uid" + '</td>';
    html += '<td>' + "title" + '</td>';
    html += '<td>' + "post_time" + '</td>';
    html += '<td>' + "content" + '</td>';
    html += '<td>' + "" + '</td>';
    html += '<td>' + "" + '</td>';
    html += '</tr>';
    for (var j = 0; j < jsonData.length; j++) {
        var obj = jsonData[j];
        html += '<tr>';
        html += '<td>' + obj.pid + '</td>';
        html += '<td>' + obj.user.uid + '</td>';
        html += '<td>' + obj.title + '</td>';
        html += '<td>' + obj.postTime + '</td>';
        html += '<td>' + obj.content + '</td>';
        // if(obj.pic.length==0){
        //     html += '<td  ><a href="#" onclick="box()"><button value="'+obj.id+'" class ="filedownload">简历下载</button> '+ '</a></td>';
        // }else{
        //     html += '<td  ><a href="'+'http://"+globalip+":8080/usercenter/user/download?fileName='+obj.pic+'"><button value="'+obj.id+'" class ="filedownload">简历下载</button> '+ '</a></td>';
        // }
        // // html += '<td  ><button value="'+obj.id+'" class ="filedownload">简历下载</button> '+ '</td>';
        html += '<td  ><button style="color: #000;" value="' + obj.pid + '" class ="postupdate">修改</button> ' + '</td>';
        html += '<td  ><button style="color: #000;" value="' + obj.pid + '" class ="postdelete">删除</button> ' + '</td>';
        html += '</tr>'
    }
    html += '</table>';

    $('.container').append(html);
}

//没这个第二级还跳不过去
function conditionSelect() {
    var html = '<div id="condition">';
    html += '<form>';
    html += '<label><input type="text" id="usernametext" placeholder="name"></label>';
    html += '<label><input type="text" id="teltext" placeholder="tel"></label>';
    html += '<label><input type="text" id="sextext" placeholder="sex"></label>';
    html += '</form>';
    html += '<button class ="select">查询</button>';
    html += '</div>';
    $('.container').append(html);
}