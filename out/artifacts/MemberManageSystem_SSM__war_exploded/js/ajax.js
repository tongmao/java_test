/**
 *该函数需要传入一个ajax对象，要求对象定义请求地址（url），请求方式（method），
 *请求数据（data,为一个对象），与成功函数success()和failure（）
 *注意，post方式需要定义报头header(json 和 urlencoded枚举)
 * @param {*} option（一个ajax对象）
 */
function ajax(option) {
    var xhr = new XMLHttpRequest();
    var urlParams = "",
        jsonParams;
    //对传入的数据进行urlencoded处理和json处理
    for (var k in option.data) {
        urlParams += k + "=" + option.data[k] + "&";
    }
    urlParams = urlParams.substr(0, urlParams.length - 1);
    jsonParams = JSON.stringify(option.data);
    ////////////////////////////////////////
    if (option.method === "get") {
        xhr.open("get", option.url + "?" + urlParams);
        xhr.send();
    }
    if (option.method === "post") {
        xhr.open("post", option.url);
        if (option.header === "json") {
            xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            xhr.send(JSON.stringify(option.data));
        } else if (option.header === "urlencoded") {
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send(urlParams);
        }
    }
    xhr.onload = () => {
        //根据http状态码来执行成功或者失败函数  
        if (xhr.status === 200) {
            option.success(xhr.responseText, xhr);
        } else {
            option.failure(xhr.responseText, xhr);
        }
    }
}
export {
    ajax
}