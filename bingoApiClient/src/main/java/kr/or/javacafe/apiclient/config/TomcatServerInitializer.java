package kr.or.javacafe.apiclient.config;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;

/**
 * 톰켓 설정을 JavaConfig로 변경
 * 
 * @author hrkim
 *
 */
public class TomcatServerInitializer extends ServerProperties {

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {

		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/WEB-INF/jsp/404.jsp"));
		container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/WEB-INF/jsp/500.jsp"));

		super.customize(container);
	}	
}
