package kr.or.javacafe.core.spring.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 리소스 디렉토리에 존재하는 application-system-config.yml 파일에 정의된 정보를
 * profile 정보에 의해 동적으로 맵핑해 POJO로 돌려준다.
 * 
 * @author hrkim
 *
 */
@ConfigurationProperties(prefix = "system")
@Component
public class SystemProperty {

	/**
	 * 서비스 타입
	 */
	private String type;
	
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

	
	
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getListenQueueName() {
		return listenQueueName;
	}

	public void setListenQueueName(String listenQueueName) {
		this.listenQueueName = listenQueueName;
	}
	
	
	

	

}
