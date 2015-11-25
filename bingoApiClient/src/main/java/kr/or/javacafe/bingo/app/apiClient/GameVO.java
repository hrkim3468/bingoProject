package kr.or.javacafe.bingo.app.apiClient;

import java.util.Date;
import kr.or.javacafe.core.util.type.Status;

public class GameVO {

	private Long id;
	private Status status = Status.USE;
	private Date createTime = new Date();

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
