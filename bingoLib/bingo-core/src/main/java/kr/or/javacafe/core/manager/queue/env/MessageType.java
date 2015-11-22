package kr.or.javacafe.core.manager.queue.env;

public enum MessageType {

	SERVER_CONFIG("SERVER_CONFIG", "서버정보 메시지"),
	RESET("RESET", "리셋 메시지");
	
	
	String id;
	String name;
	
	MessageType(String id, String name) {
		setId(id);
		setName(name);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
