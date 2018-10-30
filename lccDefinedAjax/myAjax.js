//封装AJAX对象
function Ajax(dataType,async){
	var ajax= new Object();
    
    ajax.dataType = dataType?dataType.toUpperCase:'HTML';

    if(typeof(async)=='undefined'){
        ajax.async=true;
    }else{
        ajax.async=async;
    }
    
    ajax.createXMLHttpRequest = function(){
        var xmlRequest = false;
        if(window.XMLHttpRequest){
            xmlRequest = new XMLHttpRequest;
        }else if(window.ActiveXObject){
            xmlRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }

        return xmlRequest;
    }
    
    ajax.xmlRequest = ajax.createXMLHttpRequest();
    
    //onreadystatechange事件调用函数
    ajax.changeFunction = function(){
        if(ajax.xmlRequest.readyState == 4&& ajax.xmlRequest.status == 200){
            if(ajax.dataType =='HTML'){
                ajax.callback(ajax.xmlRequest.responseText);
            }else if(ajax.dataType == 'JSON'){
                ajax.callback(JSON.parse(ajax.xmlRequest.responseText));
            }else if(ajax.dataType == 'XML'){
                ajax.callback(ajax.xmlRequest.responseXML);
            }
        }
    }
  
    /**Ajax中get请求方法
    * @param string url 请求的url
    * @param function callback 用来接收服务器返回数据的回调函数
    */

    ajax.get=function(url,callback){
        if(callback != null){
            ajax.callback = callback;

            ajax.xmlRequest.onreadystatechange = ajax.changeFunction;
        }

        ajax.xmlRequest.open('GET',url,ajax.async);

        ajax.xmlRequest.send();
    }
    
    /**Ajax中的 post请求方法
    * @param string utl 请求的url
    * @param string/object sendString post 请求携带的参数 字符串或者JSON对象
    * @param function callback 用来接收服务器返回数据的回调函数
    */
    ajax.post = function(url ,sendString, callback){
        if(typeof(sendString == 'object')){
            var str = '';
            for(var key in sendString){
                str += key + '='+sendString[key]+'&';
            }

            sendString = str.substr(0,str.length-1);
        }

        if(callback !=null){
            ajax.callback = callback;
            ajax.xmlRequest.onreadystatechange = ajax.changeFunction;
        }

        ajax.xmlRequest.open('POST', url, ajax.async);

        ajax.xmlRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
        ajax.xmlRequest.send(sendString);
    }
    
    return ajax;
}    

    

    


