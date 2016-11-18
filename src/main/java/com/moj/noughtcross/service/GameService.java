package com.moj.noughtcross.service;

import com.moj.noughtcross.domain.Game;
import com.moj.noughtcross.domain.NoughtCrossGame;

public interface GameService {
	/**
	 * Return new Game Id
	 * @param game
	 * @return
	 */
	int createNewGame(Game game);

	/**
	 * Returns NoughtCrossGame for given game id	
	 * @param gameid
	 * @return
	 */
	NoughtCrossGame getGameById(int gameid);
	
	
	/**
	 * Play game
	 * @param gameid
	 * @param playerid
	 * @param rowid
	 * @param columnid
	 */
	void playGame(int gameid, int playerid, int rowid, int columnid);
	
	
	
}
