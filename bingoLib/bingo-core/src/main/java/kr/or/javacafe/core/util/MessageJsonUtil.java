package kr.or.javacafe.core.util;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.javacafe.core.manager.queue.env.MessageType;
import kr.or.javacafe.core.util.vo.MessageResult;

public class MessageJsonUtil {

	private static Logger logger = LoggerFactory.getLogger(MessageJsonUtil.class);
	
	private static ObjectMapper om = new ObjectMapper();
	
	
	
	/**
	 * 객체를 메세지 패킷 객체로 확장하여 JSON 문자열로 변환
	 */
	public static String toMessageJSON(MessageType messageType, Object obj) {
		try {
			return getJson(messageType, obj);
		} catch (Exception ex) {
			return getJsonFail(messageType, ex);
		}
	}
	
	
	/**
	 * JSON 문자열을 파싱하여 MessageResult 객체로 변환
	 */	
	public static MessageResult toMessageObj(String json) {
		try {
			return om.readValue(json, MessageResult.class);
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
		
		return null;
	}	
	
	
	
	
	
	
	private static String getJson(MessageType messageType, Object obj) {		
		String strJSON = getJsonFail(messageType);
		
		try {
			MessageResult resultObj = new MessageResult();
			resultObj.setResult("success");
			resultObj.setMessageType(messageType);
			resultObj.setMessageCreateTime(new Date());
			resultObj.setData(obj);
			
			//strJSON = om.defaultPrettyPrintingWriter().writeValueAsString(resultObj);			
			strJSON = om.writeValueAsString(resultObj);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
		
		return strJSON;
	}
	
		
	private static String getJsonFail(MessageType messageType, Exception ex) {
		ex.printStackTrace();
		logger.error(ex.getMessage());
		
		return getJsonFail(messageType);
	}
	
	
	public static String getJsonFail(MessageType messageType) {
		return "{\"result\":\"fail\",\"messageType\":\"" + messageType + "\"}";
	}
	
}


