package kr.or.javacafe.bingo.app.ranking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/ranking")
public class RankingController {

	@Autowired
	private RankingService service;
	


	
	/**
	 * 게임 id에 모든 랭킹
	 * @return
	 */
	@RequestMapping(value = "/game/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findAll(@PathVariable Long id) {
		List<Ranking> gameRankings = service.findRanking(id);
		
		return new ResponseEntity<>(gameRankings, HttpStatus.OK);
	}

	
	/**
	 * 게임 id에 사용자별 랭킹
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/game/{id}/uuid/{uuid}", method = RequestMethod.GET)
	public ResponseEntity<Ranking> findGame(@PathVariable Long id, @PathVariable String uuid) {
		Ranking gameRanking = service.findRanking(id, uuid);
		
		return new ResponseEntity<>(gameRanking, HttpStatus.OK);
	}
	


	
}
