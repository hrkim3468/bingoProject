package kr.or.javacafe.core.spring.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessLogFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);
	
	
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}


	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * 요청된 Access Log를 로깅한다. (Health Check 요청일 경우 제외)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		if (req instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest)req;
			String requestUrl = (request.getRequestURL() == null) ? "" : request.getRequestURL().toString();
			String referer = StringUtils.defaultString(request.getHeader("Referer"), "-");
			String queryString = StringUtils.defaultIfEmpty(request.getQueryString(), "");
			
			String fullURL = requestUrl + (StringUtils.isNotEmpty(queryString) ? "?" + queryString : queryString);
			if (!fullURL.contains("healthcheck")) {
				StringBuilder result = new StringBuilder();
				result.append("요청된 URL").append("  ■  ").append(fullURL).append("  ■  ").append(referer);
				logger.info("[LOG FILTER] " + result.toString());
			}
		}
		
		chain.doFilter(req, res);
	}



	
}
