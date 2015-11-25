package kr.or.javacafe.bingo.app.apiClient;

public class RankingVO {

	private Long id;
	private Long gameId;
	private String uuid;
	private int clearLineCount;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
