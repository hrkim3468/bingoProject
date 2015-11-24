package kr.or.javacafe.bingo.app.ranking;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

	@Autowired
	private RankingRepository repository;
	
	
	
	@Transactional
	public Ranking save(Long gameId, String uuid, int clearLineCount) {
		
		Ranking tempRanking = findRanking(gameId, uuid);
		
		if (null == tempRanking || null == tempRanking.getId()) {
			Ranking ranking = new Ranking();
			ranking.setGameId(gameId);
			ranking.setUuid(uuid);
			ranking.setClearLineCount(clearLineCount);
			
			return repository.save(ranking);
		}
		
		tempRanking.setClearLineCount(clearLineCount);
		
		return repository.save(tempRanking);
	}
	
	
	public List<Ranking> findRanking(Long gameId) {
		return repository.findByGameId(gameId);
	}
	

	public Ranking findRanking(Long gameId, String uuid) {
		return repository.findByGameIdAndUuid(gameId, uuid);
	}

	
	
	
}
