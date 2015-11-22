package kr.or.javacafe.apigateway.config.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "db")
@Component
public class DatabaseProperty {

	/**
	 * 드라이버
	 */
	private String driverClassName;
	/**
	 * 드라이버 URL
	 */
	private String url;
	/**
	 * DB User
	 */
	private String username;
	/**
	 * DB User 비밀번호
	 */
	private String password;
	
	
	
	
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	


	
	
}
