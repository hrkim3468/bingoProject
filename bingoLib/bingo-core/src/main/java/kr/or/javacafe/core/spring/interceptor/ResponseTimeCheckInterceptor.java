package kr.or.javacafe.core.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 응답시간 로깅 인터셉터
 * 
 * 사용자 응답에 걸린 시간을 로깅한다. 
 * 
 * @author hrkim
 *
 */
public class ResponseTimeCheckInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(ResponseTimeCheckInterceptor.class);

	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 체크 시작시간 기록
		request.setAttribute("__checkStartTime", System.currentTimeMillis());
		
		return super.preHandle(request, response, handler);
	}




	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// 체크 시간을 계산하여 응답에 소요된 시간을 로깅한다.
		long checkStartTime = (Long)request.getAttribute("__checkStartTime");
		long checkEndTime = System.currentTimeMillis();
		
		request.removeAttribute("__checkStartTime");
		
		long responseTime = checkEndTime - checkStartTime;
		logger.debug("[응답시간] " + responseTime + " msec");	
		
		ModelMap model = modelAndView.getModelMap();
		model.addAttribute("_responseTime", responseTime);
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
