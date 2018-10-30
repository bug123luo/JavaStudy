//��װAjax����
function Ajax(dataType, async) {
	var ajax = new Object();  //����һ���µ�ajax����
	
	//������������,�趨�������ͣ������趨�ģ���ת��Ϊ��д��û���趨��Ĭ��HTML
	ajax.dataType = dataType ? dataType.toUpperCase() : 'HTML';
	
	//����ͬ���첽
	if (typeof(bool)=="undefined") {  //���û���趨ͬ���첽�Ĳ�����Ĭ���첽
		ajax.async = true;
	} else {                          //����趨��ͬ���첽���������趨��
		ajax.async = async;
	}
	
	//����XMLHttpRequest����
	ajax.createXMLHttpRequest = function() {
		var xmlRequset = false;     //����յ�xmlRequset����
		if (window.XMLHttpRequest) {//�������������XMLHttpRequest����
			xmlRequset = new XMLHttpRequest();
		} else if (window.ActiveXObject) {//IE5��IE6����XMLHttpRequest����
			xmlRequset = new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xmlRequset;
	}
	
	//���ú�������XMLHttpRequest,�Ѵ����õ�XMLHttpRequest�����ajax��һ������
	ajax.xmlRequset = ajax.createXMLHttpRequest();
	
	//onreadystatechange�¼����õĺ���
	ajax.changeFuncition = function() {
		//alert(ajax.xmlRequset.readyState);
		//�жϷ�������Ӧ״̬��ֻ��״̬200����readyState=4��ʱ�򣬲�����Ӧ�ɹ�
		if (ajax.xmlRequset.readyState == 4 && ajax.xmlRequset.status == 200) {
			//�����û��趨���������ͷ�����Ӧ�ĸ�ʽ������
			if (ajax.dataType == 'HTML') {   			//HTML���ݸ�ʽ
				ajax.callback(ajax.xmlRequset.responseText);
			} else if (ajax.dataType == 'JSON') {       //JSON���ݸ�ʽ
				//ͨ��ȫ�ֺ���JSON.parse�ѷ��ص�JSON�ַ���ת��ΪJSON����
				ajax.callback(JSON.parse(ajax.xmlRequset.responseText));
			} else if (ajax.dataType == 'XML') {       	//XML���ݸ�ʽ
				ajax.callback(ajax.xmlRequset.responseXML);
			}
		}
	}
	
	/** Ajax��get���󷽷�
	 * @param string url �����url
	 * @param function callback �������շ������������ݵĻص�����
	 */
	ajax.get = function(url, callback) {
		//�ж��Ƿ��лص��������лص������Ļ�Ҫ����onreadystatechange�¼�����
		if (callback != null) {
			//�Ѵ��ݹ����Ļص�������ajax��һ������
			ajax.callback = callback;   //����onreadystatechange�¼�����ʹ��
			//����onreadystatechange�¼��������ѷ��������ص����ݸ��ص�����
			ajax.xmlRequset.onreadystatechange = ajax.changeFuncition;
		}
		ajax.xmlRequset.open('GET', url, ajax.async);  		//Ȼ���ʼ��GET����
		ajax.xmlRequset.send();								//��������
	}
	
	/** Ajax��post���󷽷�
	 * @param string url �����url
	 * @param string/object sendString post����Я���Ĳ��� �ַ�������JSON����
	 * @param function callback �������շ������������ݵĻص�����
	 */
	ajax.post = function(url, sendString, callback) {
		//����Ҫ��������ύ�Ĳ�����ΪXML֧�ֵĸ�ʽ
		if (typeof(sendString == 'object')) {  //�����JSON����,ת�����ַ���
			var str = '';
			for (var key in sendString) {  	   //ѭ���������JSON����
				//��JSON����ת����name=Tom&age=19����ʽ
				str += key + '=' + sendString[key] + '&';
			}
			//ɾ���ַ������һ���ַ�'&'
			sendString = str.substr(0, str.length-1);
		}
		//�ж��Ƿ��лص��������лص������Ļ�Ҫ����onreadystatechange�¼�����
		if (callback != null) {
			//�Ѵ��ݹ����Ļص�������ajax��һ������
			ajax.callback = callback;   //����onreadystatechange�¼�����ʹ��
			//����onreadystatechange�¼��������ѷ��������ص����ݸ��ص�����
			ajax.xmlRequset.onreadystatechange = ajax.changeFuncition;
		}
		ajax.xmlRequset.open('POST', url, ajax.async);  	//Ȼ���ʼ��POST����
		//����POST����ͷ��Ϣ
		ajax.xmlRequset.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		ajax.xmlRequset.send(sendString);	                //��������
	}
	
	/** JSONP��������
	 * @param string url �����url
	 * @param function callback �������շ������������ݵĻص�����
	 */
	ajax.jsonp = function(url, callback) {
		//��̬����script��ǩ��Ϊ��ʹ������src����ȥ���������
		var script = document.createElement('script');
		//������ɺ���������Ȼ���ȡ���ػ����js�ļ�
		var time = new Date();
		var funcName = 'jsonp' + time.getTime();
				
		//ƴ��URL,�ж�url���Ƿ��в���
		if (url.indexOf('?') > 0) {  //���url���д��ݲ���������ƴ��URL
			url = url + '&callback=' + funcName;
		} else { //���urlû���д��ݲ���������ƴ��URL
			url = url + '?callback=' + funcName;
		}
		
		//ע��ص�������ȫ��
		window[funcName] = function(data) {
			callback(data);
			//�����ݸ��ص�����֮����������ע��ĺ����ʹ�����script��ǩ
			delete window[funcName];               //ɾ������
			script.parentNode.removeChild(script); //ɾ��script��ǩ
		}

		//����script��ǩ��src����
		script.setAttribute('src', url);
		//��script��ǩ����head������������õ�����
		document.getElementsByTagName('head')[0].appendChild(script);
	}
	
	/** window.name��������
	 * @param string url �����url
	 * @param function callback �������շ������������ݵĻص�����
	 */
	ajax.iframe = function(url, callback){
		var iframe = document.createElement('iframe'); //����iframe��ǩ
		iframe.style.display = 'none';                 //��������Ϊ����
		iframe.src = url; 							   //ָ��iframe��src
		//��iframe�ڵ�д�뵽body��
		document.getElementsByTagName('body')[0].appendChild(iframe);	   
		var flag = true;        //���ñ�־�������жϣ���֤srcֻ�趨һ��
		ajax.onload = function(){ //src�ı�һ�Σ�iframe���¼���һ��
			if(flag){           //�жϱ�־״̬����ֹ�ظ�����src
				iframe.src = 'about:blank';           //�������ó�ͬ��
				flag = false;   //һ������src��֮��,���̸ı��־״̬
			} else {    //ͬ���src���غ�֮�󣬾Ϳ��Ի�ȡwindow.name��ֵ��
				var data = iframe.contentWindow.name; //��ȡ�����趨nameֵ
				//�ж��û���Ҫ���յ���������
				if (ajax.dataType == 'JSON') {
					data = JSON.parse(data);//��JSON��ʽ�ַ���ת��ΪJSON����
				}
				callback(data);                       //�����ݸ��ص�����  
				document.body.removeChild(iframe);    //���ٽڵ�
			}			
		};
		//���ǵ�������������⣬�������¼�
		if(iframe.attachEvent){
            iframe.attachEvent('onload', ajax.onload);
        } else {
            iframe.onload = ajax.onload;
        }
	}
	
	
	return ajax;              //����ajax����
}


/*
//������HTMLҳ��һ������ʹ�����Ƿ�װ��Ajax��
<script src="./myAjax.js"></script>  //�������Ƿ�װ��Ajax
<script>
	//����ʹ��Ajax��get��ʽ�첽�����ֵ�������
	Ajax('HTML', true).get('http://www.lampbrother.net/', function(data){
		alert(data);  //���data���Ƿ�����������������
	});
</script>

//���������Ƿ�װ��get����:
ajax.get(url, callback);
//��һ������urlûʲô���⣬���ǰ�ʹ�����������ʱ�򴫵ݹ����ĵڶ��������������������
//Ҳ���� 
callback = function(data){}; 
//��ô�Ϳ���д���������ˣ�
ajax.get(url, function(data){});
//�������ǵķ�װ������ʲô���ǵģ�������ص���������ajax�����һ�����ԣ�
ajax.callback = callback;
//Ҳ���ǣ�
ajax.callback = function(data){}
//����һ����������Ajax()�����ڵ��κεط�������ʹ��ajax.callback���������
//��Ȼ��onreadystatechange�¼���������Ҳ����������
ajax.callback(ajax.xmlRequset.responseText);
//���Կ������ǰѷ������ķ���ֵajax.xmlRequset.responseText���ݸ������callback
//�ǲ��ǿ���д��������
ajax.callback = function(ajax.xmlRequset.responseText){
	//���ﴦ����������ص�����
}
//������б�Դ��ajax.callbackd�ı�Դ���ģ��ǵľ�������ǰ��ʹ��get()������ʱ��
//���ݵĵڶ����������ص�����������һ�оͺ������ˣ��ǲ��ǿ�������������
//��onreadystatechange�¼��������ǵ���������������ѷ������Ĳ�����ֵ�����������
//��ô������ǰ���ʹ��get()������ʱ��data�������͵��ڷ��������ص�ֵ����
Ajax().get(url, function(data){
	data = ajax.xmlRequset.responseText;  //����ǻص������ı���
});

//�ܽ�һ�£�
1. ������ʹ��Ajax��ʱ��get()�����ڶ���������һ���ص�����
2. ���������onreadystatechange�¼������б����ã����ѷ��������ص����ݴ���������ص�����
3. ���ǾͿ�����ʹ��get()������ʱ�򣬵ڶ��������еĻص��������÷��������ص�����
4. Ϊʲô�ڷ�װAjax��ʱ�򣬲���callback�ص�����ֱ�Ӵ��θ�onreadystatechange�¼�����,����
   ��Ϊajax�����һ�����Լ�ӵ��ã�
   ������Ϊonreadystatechange�¼����������в���������ֻ�����߾ȹ���

*/





