package kr.or.javacafe.bingo.config.webSocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.or.javacafe.core.util.JsonUtil;

@Component
public class PushHandler extends TextWebSocketHandler {
	
	private Logger logger = LoggerFactory.getLogger(PushHandler.class);
	
	
	private Map<String, WebSocketSession> clients = new HashMap<String, WebSocketSession>();
	

	
	
	
	/**
	 * 클라이언트에서 접속이 된 경우 발생하는 이벤트
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String sessionKey = getSessionKey(session);
		clients.put(sessionKey, session);

		logger.debug("@@@@@@@@@@@@@@@@@@ WebSocket 클라이언트 " + sessionKey + " CONNECT @@@@@@@@@@@@@@@@@@");
		
		String json = getResultJson(PushCodeType.WELCOME_TEXT, "Welcome [" + sessionKey + "] client. All connection clients is " + clients.size() + " ea.");
		TextMessage result = new TextMessage(json);
		
		session.sendMessage(result);
		
		super.afterConnectionEstablished(session);
	}

	
	/**
	 * 클라이언트에서 접속이 종료 된 경우 발생하는 이벤트
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String sessionKey = getSessionKey(session);
		clients.remove(sessionKey);

		logger.debug("@@@@@@@@@@@@@@@@@@ WebSocket 클라이언트 " + sessionKey + "DISCONNECT @@@@@@@@@@@@@@@@@@");
		
		super.afterConnectionClosed(session, status);
	}

	
	/**
	 * 클라이언트에서 send로 메세지를 보낼때 호출되는 콜백함수
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		logger.debug("@@@@@@@@@@@@@@@@@@ WebSocket 클라이언트 전송 메시지 : " + msg + " @@@@@@@@@@@@@@@@@@");
		
		
		// 메시지를 보낸 클라이언트에게 다시 Push한다.
		TextMessage result = new TextMessage("ECHO : " + msg);
		session.sendMessage(result);
		
		super.handleTextMessage(session, message);
	}

	
	
	/**
	 * 클라이언트에 데이터를 Push 한다.
	 * @param uuid
	 */
	public void sendMessage(PushCodeType pushCode, Long gameId, int checkNumber, String uuid) {
		for (Entry<String, WebSocketSession> entry : clients.entrySet()) {
			String key = entry.getKey();
			String[] temp = key.split(":");
			
			// 게임판이 일치하는 사용자에게만 Push
			if (gameId.toString().equals(temp[0]) && uuid.equals(temp[1])) {
				if (PushCodeType.CHECK_NUMBER.equals(pushCode)) {
					push(entry.getValue(), pushCode, (new Integer(checkNumber)).toString());
					
				} else if (PushCodeType.RANKING_RESET.equals(pushCode)) {
					push(entry.getValue(), pushCode, "RESET");
				}
			}
		}
	}
	
	
	
	private void push(WebSocketSession session, PushCodeType pushCode, String data) {
		try {
			String json = getResultJson(pushCode, data);
			TextMessage result = new TextMessage(json);
			
			session.sendMessage(result);
			
		} catch (IOException e) {
			logger.error("Client로 데이터 푸시 실패");
			e.printStackTrace();
		}
	}
	
	
	private String getSessionKey(WebSocketSession session) {
		String[] params = session.getUri().getQuery().toString().split("&");
		String[] tempGameId = params[0].split("=");
		String[] tempUuid = params[1].split("=");
		
		String sessionKey = tempGameId[1] + ":" + tempUuid[1] + ":" + session.getId();
		
		return sessionKey;
	}
	
	
	private String getResultJson(PushCodeType pushCode, String text) {
		PushVO vo = new PushVO();
		vo.setCode(pushCode);
		vo.setText(text);
		
		return JsonUtil.toJSON(vo);
	}
}





