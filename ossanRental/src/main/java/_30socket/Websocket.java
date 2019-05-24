package _30socket;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/index.do")
public class Websocket {
	static Map<String, RemoteEndpoint.Basic> allRemote = new HashMap<String, RemoteEndpoint.Basic>();
	
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println(session.getId() + "has opened a connection");
		try {
			RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
			
			allRemote.put(session.getId(), basicRemote);
			
			basicRemote.sendText("連線成功");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("Message from " + session.getId() + " : " + message);
		try {
			session.getBasicRemote().sendText(message);
			for (String id : allRemote.keySet()) {
				RemoteEndpoint.Basic basic = allRemote.get(id);
				basic.sendText("123....." + message);
			}
			System.out.println("allRemote... " + allRemote.size());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		allRemote.remove(session.getId());
		System.out.println("Session " + session.getId()+" has ended");
	}
		
}
