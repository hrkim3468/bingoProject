package kr.or.javacafe.bingo.app.adminApiClient;

public class RankingUserVO {

	private Long id;
	private Long gameId;
	private String uuid;
	private int clearLineCount;

	private String name;
	private String email;
	private String company;
	private String phone;
	
	private int ranking;
	
	
	
	
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	


	
	
}
