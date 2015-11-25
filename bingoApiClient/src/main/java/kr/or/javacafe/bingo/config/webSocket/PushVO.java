package kr.or.javacafe.bingo.config.webSocket;

public class PushVO {

	private PushCodeType code;
	private String text;
	
	
	public PushCodeType getCode() {
		return code;
	}
	public void setCode(PushCodeType code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
