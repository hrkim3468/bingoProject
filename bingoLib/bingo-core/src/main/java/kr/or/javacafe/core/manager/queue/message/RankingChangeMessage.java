package kr.or.javacafe.core.manager.queue.message;

public class RankingChangeMessage implements Message {

	private Long gameId;

	
	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	



}
