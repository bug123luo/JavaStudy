/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  WebSocketServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter13.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月17日 上午10:33:06   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.service.impl;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;
import lombok.Data;

/**   
 * @ClassName:  WebSocketServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月17日 上午10:33:06   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@ServerEndpoint("/ws")
@Service
@Data
public class WebSocketServiceImpl {
	
	private static int onlineCount = 0;
	private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet = new CopyOnWriteArraySet<>();
	private Session session;
	
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this);
		addOnlineCount();
		System.out.println("有新连接加入！当前在线人数为"+getOnlineCount());
		try {
			sendMessage("又新连接加入了！！");
		} catch (Exception e) {
			System.out.println("IO异常");
		}
	}
	
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);
		subOnlineCount();
		System.out.println("有一连接关闭！当前在线人数为"+getOnlineCount());
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("来自客户端的消息："+message);
		
		for (WebSocketServiceImpl item : webSocketSet) {
			try {
				//String userName = item.getSession().getUserPrincipal().getName();
				//System.out.println(userName);
				item.sendMessage(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}
	
	private void sendMessage(String message) throws IOException{
		this.session.getBasicRemote().sendText(message);
	}
	
	private static synchronized int getOnlineCount() {
		return onlineCount;
	}
	
	private static synchronized void addOnlineCount() {
		WebSocketServiceImpl.onlineCount++;
	}
	
	private static synchronized void subOnlineCount() {
		WebSocketServiceImpl.onlineCount--;
	}
}
