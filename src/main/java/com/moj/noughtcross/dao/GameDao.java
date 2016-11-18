package com.moj.noughtcross.dao;

import com.moj.noughtcross.domain.NoughtCrossGame;

public interface GameDao {
	
	/**
	 * Stores new game and returns game id
	 * @param noughtCrossGame
	 * @return
	 */
	
	int createNewGame(NoughtCrossGame noughtCrossGame); 
	
	/**
	 * Returns NoughCrossGame for given game id
	 * @param gameid
	 * @return
	 */
	
	NoughtCrossGame getGameById(int gameid);

	/**
	 * Updates the given game
	 * @param noughtCrossGame
	 */
	
	void updateGame(NoughtCrossGame noughtCrossGame);

	
}
