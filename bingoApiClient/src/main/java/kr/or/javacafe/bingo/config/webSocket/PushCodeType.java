package kr.or.javacafe.bingo.config.webSocket;

public enum PushCodeType {

	WELCOME_TEXT("WELCOME_TEXT", "클라이언트 환영 메시지"),
	CHECK_NUMBER("CHECK_NUMBER", "숫자 체크값 메시지"),
	RANKING_RESET("RANKING_RESET", "Ranking 데이터 리셋 메시지");
	
	
	String id;
	String name;
	
	PushCodeType(String id, String name) {
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
