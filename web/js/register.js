import {ajax} from "./ajax.js";
import {hex_md5} from "./md5.js";
$(()=>{
    init();
})
function init(){
    $("#register-wranning-block").hide();
    // 授权码提示
    $("#register-tipcaret-key").hide();
    $("#register-tip-key").hide();
    $("#register-tipfont-key").bind("mouseover",()=>{
        $("#register-tipcaret-key").show();
        $("#register-tip-key").show();
    })
    $("#register-tipfont-key").bind("mouseout",()=>{
        $("#register-tipcaret-key").hide();
        $("#register-tip-key").hide();
    })
    // 发送ajax
    $("#register-input-btn").bind("click",()=>{
        var username = $("#register-input-username").val();
        var pwd = $("#register-input-pwd").val();
        var key = $("#register-input-key").val();
        // console.log(username+","+pwd+","+key)
        if(username && pwd && key){
            // console.log("1111111111");
            ajax({  
                method:"post",
                url:"/register",
                data:{
                    "username":username,
                    "password":hex_md5(pwd),
                    "key":key,
                    "registertime":new Date()
                    },
                header:"json",
                success:(data,xhr)=>{
                    // console.log(data);
                    if(data=="注册成功"){
                        //注册成功的操作
                        location.href="/MemberManage.html"
                    }else{
                        $("#register-wranning-block>span").text(data);
                        $("#register-wranning-block").show();
                        setTimeout(()=>{
                            $("#register-wranning-block").hide();
                            },2000)
                        }
                    },
                failure:(data,xhr)=>{
                    $("#register-wranning-block>span").text("网络连接失败，请重试");
                    $("#register-wranning-block").show();
                    setTimeout(()=>{
                        $("#register-wranning-block").hide();
                        },2000)
                    }
                });
        }else{
            $("#register-wranning-block>span").text("输入不能为空哦");
            $("#register-wranning-block").show();
            setTimeout(()=>{
                $("#register-wranning-block").hide();
            },2000)
        }
    })


    
    
}
