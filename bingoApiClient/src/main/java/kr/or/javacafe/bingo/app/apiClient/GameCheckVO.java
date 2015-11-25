package kr.or.javacafe.bingo.app.apiClient;

public class GameCheckVO {

	private Long id;
	private GameVO game;
	private int checkNumber;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public GameVO getGame() {
		return game;
	}
	public void setGame(GameVO game) {
		this.game = game;
	}
	public int getCheckNumber() {
		return checkNumber;
	}
	public void setCheckNumber(int checkNumber) {
		this.checkNumber = checkNumber;
	}

	

	
}
