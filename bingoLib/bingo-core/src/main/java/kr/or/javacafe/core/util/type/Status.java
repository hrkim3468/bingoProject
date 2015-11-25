package kr.or.javacafe.core.util.type;

public enum Status {
	
	USE("USE", "사용"),
	END("END", "종료"),
	DEL("DEL", "삭제");
	
	
	String id;
	String name;
	
	Status(String id, String name) {
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
