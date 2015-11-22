package kr.or.javacafe.core.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.javacafe.core.spring.prop.DatabaseProperty;
import kr.or.javacafe.core.spring.prop.SystemProperty;

/**
 * UI 템플릿 인터셉터
 * 
 * UI 그리는데 필요한 기본적인 정보들을 담아서 JSP로 전달한다.
 * 
 * @author hrkim
 *
 */
public class UITemplateInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(UITemplateInterceptor.class);

	@Autowired
	private Environment env;
	
	@Autowired
	private DatabaseProperty databaseProp;
	
	@Autowired
	private SystemProperty systemProp;

	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		ModelMap model = modelAndView.getModelMap();
		//model.addAttribute("_profile", Arrays.toString((env.getActiveProfiles())));
		model.addAttribute("_profile", env.getActiveProfiles()[0]);
		model.addAttribute("_databaseProp", databaseProp);
		model.addAttribute("_systemProp", systemProp);
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
