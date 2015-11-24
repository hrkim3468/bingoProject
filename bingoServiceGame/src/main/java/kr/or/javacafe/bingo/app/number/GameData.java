package kr.or.javacafe.bingo.app.number;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import kr.or.javacafe.bingo.app.game.Game;

@Entity(name = "GAME_DATA")
public class GameData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Game game;

	@Column(nullable = false)
	private String uuid;
	
	@Column
	private int dataIndex;
	
	@Column
	private int dataNumber;
		
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getDataIndex() {
		return dataIndex;
	}

	public void setDataIndex(int dataIndex) {
		this.dataIndex = dataIndex;
	}

	public int getDataNumber() {
		return dataNumber;
	}

	public void setDataNumber(int dataNumber) {
		this.dataNumber = dataNumber;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	


	
	
	

	
	
}
