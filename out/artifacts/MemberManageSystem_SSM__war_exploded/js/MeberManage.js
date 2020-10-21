import {ajax} from "./ajax.js";

$(()=>{
    init();

});
//初始化页面
function init(){
    initlocal();
    initInt();
}
//初始化本地页面
var initlocal=()=>{
    $("#memberManage-wranning-block").hide();
    $("#memberManage-addWranning-block").hide();
    $("#memberManage-success-block").hide();
    $("#memberManage-table-addember").click(()=>{
        $("#staticBackdropLabel").attr("isNewMember",true);
        //清空表单
        for (var i=0 ;i<$("#memberManage-from p input").length;i++) {
            $("#memberManage-from p input")[i].value = null;
            // console.log(i)
        }
    })
}


//初始化数据
var initInt=()=>{
    //用户信息请求
    ajax({
        url:"/member/getUserInfo",
        method:"get",
        success:(data,xhr)=>{
            if(data.trim()=="登陆失效") loginCheckingFailure();
            else $("#memberManage-user-username").text(data);
        }
    })
    // //添加会员
    $("#memberManage-addMemebr-submitBtn").click(()=>{
        // console.log("click")
        setMemberAdapter();
    })
    
    //会员信息请求
    getMembersInfo({"normal":true});

    $("#memberManage-search-btn").click(()=>{
        var search = $("#memeberManage-search-input").val();
        getMembersInfo({
            "normal":false,
            "search":search
        })
    })
    
    


    // 退出请求
    $("#memberManage-a-loginout").click(()=>{
        $.ajax({
            type:"GET",
            url:"/loginout",
            dataType:"text",
            success:(data,xhr)=>{
                // console.log(data);
                if(data.trim()=="退出成功") location.href = "/";
                else showBlock($("#memberManage-wranning-block"),"退出失败，请检查网络");
            },
            failure:(data,xhr)=>{
                showBlock($("#memberManage-wranning-block"),"退出失败，请检查网络");
            }
        })
    })
}



//请求表信息函数
var getMembersInfo=obj=>{
    $.ajax({
        url:"/member/getMembersInfo",
        type:"GET",
        data:obj,
        dataType: "text",
        contentType:'application/json;charset=utf-8',
        success:(data,xhr)=>{
            setTable(JSON.parse(data));
        }
    })
}
// 操作表函数
var setTable=data=>{
    // console.log(data);
    var max_page = parseInt((data.length-1)/10);
    var now_page = 0;
    setLongTable(now_page,max_page,data);
    $("#memberManage-table-prepage").click(()=>{
        now_page--;
        setLongTable(now_page,max_page,data);
    })
    $("#memberManage-table-nextpage").click(()=>{
        now_page++;
        setLongTable(now_page,max_page,data);
    })
}
var setLongTable=(now_page,max_page,data)=>{
    var str;
    // 当数据长度不小于10时，分页
    if(now_page==max_page){
        str = data.slice(now_page*10);
    }else{
        str = data.slice(now_page*10,now_page*10+10)
    }
    setTableAdapter(str);
        // console.log($("#memberManage-table-page span")[0])
    setTablePage(now_page,max_page);
}
var setTableAdapter=data=>{
    $("table tbody").text("");
    for(var i=0;i<data.length;i++){
        //动态增加元素
        $("table tbody").append("<tr><th scope=\"row\">"+
                data[i].id+"</th><td>"+
                data[i].username+"</td><td>"+
                data[i].level+"</td><td>"+
                "<button class=\"btn btn-success btn-sm\" id=\"moreInfo"+data[i].id+"\" data-target=\"#staticBackdrop\" data-toggle=\"modal\" >详细信息</button>"+
                "<button class=\"btn btn-warning btn-sm\" id=\"dropInfo"+data[i].id+"\">删除会员</button>"+
                "</td></tr>");
        //绑定删除事件
        dropMemberInfo(data[i].id);
        //绑定详细会员信息事件
        moreMemberInfo(data[i].id);
    }
}

function moreMemberInfo(id){
    $("#moreInfo"+id).click(()=>{
        $("#staticBackdropLabel").attr("isNewMember",false);
        $("#staticBackdropLabel").attr("memberID",id);
        $.ajax({
            url:"/member/getMoreInfo",
            type:"GET",
            dataType:"text",
            data: {id:id},
            success:(data,xhr)=>{
                // console.log(JSON.parse(data));
                setModal(JSON.parse(data));
            },
            failure:(data,xhr)=>{
                showBlock($("#memberManage-addWranning-block"),"获取数据失败，请重试");
            }
        })
    })
}
var setModal=data=>{
    console.log(data)
    var t = $("#memberManage-from p input");
    t[0].value = data.name;
    t[1].value = data.age;
    t[2].value = data.username;
    t[3].value = data.level;
    t[4].value = data.tel;
    t[5].value = data.endtime;
    t[6].value = data.email;
    t[6].value = data.address;
}
//删除会员事件函数
function dropMemberInfo(id){
    $("#dropInfo"+id).click(()=>{
        // console.log(id)
        $.ajax({
            url:"/member/dropMemberInfo",
            type:"GET",
            dataType:"text",
            data:{
                "id":id
            },
            success:(data,xhr)=>{
                if(data=="删除成功"){
                    showBlock($("#memberManage-success-block"),data);
                    getMembersInfo({"normal":true});
                }else{
                    showBlock($("#memberManage-wranning-block"),data);
                }
            },
            failure:(data,xhr)=>{
                showBlock($("#memberManage-wranning-block"),"删除失败，请重试");
            }
        })
    })
    
    
    
}
var setTablePage=(now_page,max_page)=>{
    $("#memberManage-table-nowpage").text(now_page+1);
    $("#memberManage-table-maxpage").text(max_page+1);
    if(now_page==0){
        $("#memberManage-table-prepage").attr("disabled",true);
    }else if(now_page>0){
        $("#memberManage-table-prepage").attr("disabled",false);
    }
    if(now_page == max_page){
        $("#memberManage-table-nextpage").attr("disabled",true);
    }else if(now_page<max_page){
        $("#memberManage-table-nextpage").attr("disabled",false);
    }
    
}
//登录校验失败函数
var loginCheckingFailure=()=>{
    location.href="/";
}
//主页面提示框函数
var showBlock=(block,str)=>{
    block.text(str).show();
    setTimeout(()=>{
       block.hide();
    },2000);
}
var setMemberAdapter=()=>{
    var t = $("#memberManage-from p input");
        var arr = new Array;
        for(var i=0;i < t.length ;i++){
            // console.log(t[i])
            var input = t[i];
            arr.push(input.value);
        }
        // console.log(arr);
        arr.reverse();
        var obj={
            "name":arr.pop(),
            "age":arr.pop(),
            "username":arr.pop(),
            "level":arr.pop(),
            "tel":arr.pop(),
            "endtime":arr.pop(),
            "email":arr.pop(),
            "address":arr.pop(),
            "isNewMember":$("#staticBackdropLabel").attr("isNewMember")
        }
        if(obj.isNewMember=="false") obj.memberID = $("#staticBackdropLabel").attr("memberID");
        // console.log(obj);
        //输入合法性判断
        if(isLegal(obj)){
            setMember(obj);
        }
}
//添加会员函数
//修改会员事件函数
var setMember=obj=>{
    console.log(obj);
    $.ajax({
        type:"POST",
        url:"/member/setMemberInfo",
        data:JSON.stringify(obj),
        contentType:'application/json;charset=utf-8',
        dataType:"text",
        success:(data,xhr)=>{
            $("#staticBackdrop").modal('hide');
            if(data.trim()=="添加成功" || data.trim() =="修改成功"){
                showBlock($("#memberManage-success-block"),data);
                //刷新表
                getMembersInfo({"normal":true});
            }else{
                showBlock($("#memberManage-wranning-block"),data);
            }
        },
        failure:()=>{
            showBlock($("#memberManage-addWranning-block"),"操作失败");
        }
    })
}

//合法性判断函数
var isLegal=obj=>{
    //姓名/用户名判断
    if(obj.name.length < 2 || obj.username.length<2 || obj.name.length>255 || obj.username.length>255){
        showBlock($("#memberManage-addWranning-Block"),"姓名/用户名的长度至少大于2并且小于255位哦");
        return false;
    }
    //年龄判断
    if(obj.age<18){
        showBlock($("#memberManage-addWranning-Block"),"年龄需要大于18岁哦！");
        return false;
    }else if(obj > 150){
        showBlock($("#memberManage-addWranning-Block"),"请输入正确的年龄");
        return false;
    }
    //会员等级判断
    if(obj.level<0 || obj.level>3){
        showBlock($("#memberManage-addWranning-Block"),"会员等级区间为0-3哦");
        return false;
    }
    //电话判断
    if(obj.tel.length<3 || obj.tel.length>15){
        showBlock($("#memberManage-addWranning-Block"),"请输入正确的电话号码");
        return false;
    }
    //返回结果
    return true;
}
