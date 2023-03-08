package com.kh.websocket;

import java.io.IOException;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.kh.websocket.model.vo.Message;

@ServerEndpoint(value="/chatting2.do" , 
				decoders= {JSONDecoder.class},
				encoders= {JSONEncoder.class})	

public class ChattingServer2 {


	@OnOpen
	public void open(Session session, EndpointConfig config) {
		System.out.println("클라이언트 접속");
		System.out.println(session.getId());
		
	}
	
	@OnMessage
	public void message(Session session, Message msg) {
		System.out.println(msg);
		//클라이언트가 객체로 보낸 메세지 파싱하기
//		Message data = new Gson().fromJson(msg, Message.class);
//		System.out.println(data);
		
		session.getUserProperties().put("msg", msg);
		
		// 접속한 클라이언트 관리하는 두번째 방법
		// session클래스에서 접속한 모든 session을 알수있는 메소드 사용
		// session.getOpenSession()
		// -> 현재 웹소켓에 접속해서 유지되고 있는(open) 모든 session값을 반환해줌
		Set<Session> clients = session.getOpenSessions();
		
		for(Session s : clients) {
			Message m = (Message)s.getUserProperties().get("msg");
			if(m != null) {
				if(m.getReceiver() != null && !m.getReceiver().equals("")) {
					if(m.getSender().equals(msg.getReceiver()) || m.getSender().equals(msg.getSender())) {
						
						
						try {
							s.getBasicRemote().sendObject(msg);
						} catch (IOException | EncodeException e) {
							e.printStackTrace();
						}
					}
				}else {
					try {
						s.getBasicRemote().sendObject(msg);
					} catch (IOException | EncodeException e) {
						e.printStackTrace();
					}
				} 
				
				
			}
		}
	}
	
	
}
