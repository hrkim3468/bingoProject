package kr.or.javacafe.bingo.app.ranking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "RANKING")
public class Ranking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Long gameId;
	
	@Column(nullable = false)
	private String uuid;
	
	@Column
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
