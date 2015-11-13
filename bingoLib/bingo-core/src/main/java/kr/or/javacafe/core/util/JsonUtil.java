package kr.or.javacafe.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.javacafe.core.util.vo.AjaxResult;

public class JsonUtil {

	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
	private static ObjectMapper om = new ObjectMapper();
	
	
	public static String toJSON(Object obj) {
		try {
			return toJsonFormat(obj);
		} catch (Exception ex) {
			return getJsonFail(ex);
		}
	}
	
	private static String toJsonFormat(Object obj) {		
		String strJSON = getJsonFail();
		
		try {
			AjaxResult atObj = new AjaxResult();
			atObj.setResult("success");
			atObj.setData(obj);
			
			//strJSON = om.defaultPrettyPrintingWriter().writeValueAsString(atObj);			
			strJSON = om.writeValueAsString(atObj);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
		
		return strJSON;
	}
		
	private static String getJsonFail(Exception ex) {
		ex.printStackTrace();
		logger.error(ex.getMessage());
		
		return getJsonFail();
	}
	
	public static String getJsonFail() {
		return "{\"result\":\"fail\"}";
	}
	
}


