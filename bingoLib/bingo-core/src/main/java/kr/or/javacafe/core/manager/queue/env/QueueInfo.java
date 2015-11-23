package kr.or.javacafe.core.manager.queue.env;

public class QueueInfo {

	public final static String QUEUE_SERVER = "localhost";
	public final static String QUEUE_USER = "guest";
	public final static String QUEUE_PW = "guest";

	public final static String CLIENT_QUEUE_NAME = "bingo.api.client";
	public final static String GATEWAY_QUEUE_NAME = "bingo.api.gateway";
	public final static String SERVICE_USER_QUEUE_NAME = "bingo.service.user";
	public final static String SERVICE_GAME_QUEUE_NAME = "bingo.service.game";
	public final static String SERVICE_RANKING_QUEUE_NAME = "bingo.service.ranking";
	
	public final static String EXCHANGE_NAME = "ex.bingo";
	public final static String EXCHANGE_CLIENT_QUEUE_NAME = "ex.bingo.api.client";
	public final static String EXCHANGE_GATEWAY_QUEUE_NAME = "ex.bingo.api.gateway";
	public final static String EXCHANGE_SERVICE_USER_QUEUE_NAME = "ex.bingo.service.user";
	public final static String EXCHANGE_SERVICE_GAME_QUEUE_NAME = "ex.bingo.service.game";
	public final static String EXCHANGE_SERVICE_RANKING_QUEUE_NAME = "ex.bingo.service.ranking";
	
}




