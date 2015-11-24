package kr.or.javacafe.core.manager.queue.message;

public class RankingMessage implements Message {

	private Long gameId;
	private String uuid;
	private int clearLineCount;
	
	
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getClearLineCount() {
		return clearLineCount;
	}
	public void setClearLineCount(int clearLineCount) {
		this.clearLineCount = clearLineCount;
	}

	


}
