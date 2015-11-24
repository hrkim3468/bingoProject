package kr.or.javacafe.bingo.app.common;

import java.util.List;

import kr.or.javacafe.bingo.app.number.GameData;
import kr.or.javacafe.bingo.app.numberCheck.GameCheck;

public class GameUtil {

	
	public static int lineClearCheck(List<GameData> gameDatas, List<GameCheck> gameChecks) {
		
		// 개인 데이터 구조 생성
		int[][] arrGameData = new int[5][5];
		int index = 1;
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				arrGameData[i][j] = getGameData(gameDatas, index);
				index++;
			}
		}
		
		// 가로줄 Clear 체크
		int hLineClearCount = getHLineClearCheck(gameChecks, arrGameData);
		
		// 세로줄 Clear 체크
		int vLineClearCount = getVLineClearCheck(gameChecks, arrGameData);
		
		// 대각선 Clear 체크
		int x1LineClearCount = getX1LineClearCheck(gameChecks, arrGameData);
		int x2LineClearCount = getX2LineClearCheck(gameChecks, arrGameData);
		
		// 가로줄 Clear 수 + 세로줄 Clear 수 + 대각선 Clear 수
		return hLineClearCount + vLineClearCount + x1LineClearCount + x2LineClearCount;
	}
	
	
	
	
	
	private static int getHLineClearCheck(List<GameCheck> gameChecks, int[][] arrGameData) {
		int hLineClearCount = 0;
		
		for (int i=0; i<5; i++) {
			int lineClearCount = 0;
			for (int j=0; j<5; j++) {
				boolean cellClear = isCellClear(gameChecks, arrGameData[i][j]);
				if (cellClear) {
					lineClearCount++;
				}
			}
			if (lineClearCount == 5) {
				hLineClearCount++;
			}
		}
		
		return hLineClearCount;
	}
	
	
	
	private static int getVLineClearCheck(List<GameCheck> gameChecks, int[][] arrGameData) {
		int vLineClearCount = 0;
		
		for (int i=0; i<5; i++) {
			int lineClearCount = 0;
			for (int j=0; j<5; j++) {
				boolean cellClear = isCellClear(gameChecks, arrGameData[j][i]);
				if (cellClear) {
					lineClearCount++;
				}
			}
			if (lineClearCount == 5) {
				vLineClearCount++;
			}
		}
		
		return vLineClearCount;
	}
	
	
	
	private static int getX1LineClearCheck(List<GameCheck> gameChecks, int[][] arrGameData) {
		boolean cellClear = false;
		
		int lineClearCount = 0;
		cellClear = isCellClear(gameChecks, arrGameData[0][0]);
		if (cellClear) {
			lineClearCount++;
		}
		cellClear = isCellClear(gameChecks, arrGameData[1][1]);
		if (cellClear) {
			lineClearCount++;
		}
		cellClear = isCellClear(gameChecks, arrGameData[2][2]);
		if (cellClear) {
			lineClearCount++;
		}
		cellClear = isCellClear(gameChecks, arrGameData[3][3]);
		if (cellClear) {
			lineClearCount++;
		}
		cellClear = isCellClear(gameChecks, arrGameData[4][4]);
		if (cellClear) {
			lineClearCount++;
		}
		
		if (lineClearCount == 5) {
			return 1;
		}
		return 0;
	}

	
	
	private static int getX2LineClearCheck(List<GameCheck> gameChecks, int[][] arrGameData) {
		boolean cellClear = false;
		
		int lineClearCount = 0;
		cellClear = isCellClear(gameChecks, arrGameData[4][0]);
		if (cellClear) {
			lineClearCount++;
		}
		cellClear = isCellClear(gameChecks, arrGameData[3][1]);
		if (cellClear) {
			lineClearCount++;
		}
		cellClear = isCellClear(gameChecks, arrGameData[2][2]);
		if (cellClear) {
			lineClearCount++;
		}
		cellClear = isCellClear(gameChecks, arrGameData[1][3]);
		if (cellClear) {
			lineClearCount++;
		}
		cellClear = isCellClear(gameChecks, arrGameData[0][4]);
		if (cellClear) {
			lineClearCount++;
		}
		
		if (lineClearCount == 5) {
			return 1;
		}
		return 0;
	}
	
	
	
	private static int getGameData(List<GameData> gameDatas, int index) {
		for (GameData obj : gameDatas) {
			if (index == obj.getDataIndex()) {
				return obj.getDataNumber();
			}
		}
		
		return 0;
	}
	
	
	private static boolean isCellClear(List<GameCheck> gameChecks, int gameDataNumber) {
		for (GameCheck obj : gameChecks) {
			if (gameDataNumber == obj.getCheckNumber()) {
				return true;
			}
		}
		
		return false;
	}
	
	
}
