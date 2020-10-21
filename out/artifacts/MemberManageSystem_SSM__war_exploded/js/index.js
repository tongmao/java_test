import {ajax} from "./ajax.js";
import {hex_md5} from "./md5.js";

$(()=>{
    init();
})
//初始化
function init(){
    initLocal();
    initInt();
}
//本地初始化
function initLocal(){
    $("#index-wranning-block").hide();

}
//网络初始化
function initInt(){
    //验证用户是否已登陆
        $.ajax({
        type:"GET",
        url:"/login",
        dataType:"text",
        contentType:'application/json;charset=utf-8',
        success:(data)=>{
            // console.log(data)
            if(data.trim()==="已登录"){
                location.href="/MemberManage.html";
            }
        }
    });
    //如未登录则留在当前界面
    $("#index-input-btn").click(()=>{
        var username = $("#index-input-username").val();
        var pwd = $("#index-input-pwd").val();
        var checkbox = $("#index-input-checkbox").prop("checked");
        // console.log(username+","+pwd+","+checkbox);
        if(username && pwd){
            $.ajax({
                type:"POST",
                url:"/login",
                dataType: "text",
                contentType:'application/json;charset=utf-8',
                data:JSON.stringify({
                    "username":username,
                    "password":hex_md5(pwd),
                    "checkbox":checkbox
                }),
                success:(data)=>{
                    // console.log(encodeURIComponent(data.trim()))
                    // console.log(encodeURIComponent("登录成功"))
                    // console.log("登录成功"===data)
                    if(data.trim()=="登录成功"){
                        location.href="/MemberManage.html";
                    }else{
                        $("#index-wranning-block").text(data).show();
                        setTimeout(()=>{
                            $("#index-wranning-block").hide();
                        },2000);
                    }
                },
                failure:()=>{
                    $("#index-wranning-block").text("服务器连接失败，请重试！").show();
                    setTimeout(()=>{
                        $("#index-wranning-block").hide();
                    },2000);
                }
            });
        }else{
            $("#index-wranning-block").text("输入不能为空哦！").show();
            setTimeout(()=>{
                $("#index-wranning-block").hide();
            },2000);
        }
    })
}