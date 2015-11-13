package kr.or.javacafe.apigateway.config.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "system")
@Component
public class SystemProperty {

	/**
	 * 논리적인 프로젝트명
	 */
	private String name;
	
	/**
	 * 서비스 도메인
	 */
	private String domain;
	
	/**
	 * 물리적인 서버명
	 */
	private String machineName;
	
	/**
	 * 서비스 IP
	 */
	private String ip;
	
	/**
	 * 서버에서 Listen 중 인 메세지큐 이름
	 */
	private String listenQueueName;
	
	
	
	
	/**
	 * {@link #name} 을(를) 반환한다.
	 * @return {@link #name}
	 */
	public String getName() {
		return name;
	}
	/**
	 * {@link #name} 을(를) 설정한다.
	 * @param name 설정할 {@link #name}
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * {@link #domain} 을(를) 반환한다.
	 * @return {@link #domain}
	 */
	public String getDomain() {
		return domain;
	}
	/**
	 * {@link #domain} 을(를) 설정한다.
	 * @param domain 설정할 {@link #domain}
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	/**
	 * {@link #machineName} 을(를) 반환한다.
	 * @return {@link #machineName}
	 */
	public String getMachineName() {
		return machineName;
	}
	/**
	 * {@link #machineName} 을(를) 설정한다.
	 * @param machineName 설정할 {@link #machineName}
	 */
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	/**
	 * {@link #ip} 을(를) 반환한다.
	 * @return {@link #ip}
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * {@link #ip} 을(를) 설정한다.
	 * @param ip 설정할 {@link #ip}
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * {@link #listenQueueName} 을(를) 반환한다.
	 * @return {@link #listenQueueName}
	 */
	public String getListenQueueName() {
		return listenQueueName;
	}
	/**
	 * {@link #listenQueueName} 을(를) 설정한다.
	 * @param listenQueueName 설정할 {@link #listenQueueName}
	 */
	public void setListenQueueName(String listenQueueName) {
		this.listenQueueName = listenQueueName;
	}
	
	
	
	

}
