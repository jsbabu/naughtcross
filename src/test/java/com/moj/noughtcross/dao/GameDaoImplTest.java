package com.moj.noughtcross.dao;


import org.junit.Before;
import org.junit.Test;

import com.moj.noughtcross.domain.GameStatus;
import com.moj.noughtcross.domain.NoughtCrossGame;
import com.moj.noughtcross.domain.Player;
import com.moj.noughtcross.exception.NoughtCrossGameException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class GameDaoImplTest {

	private GameDao gameDao;
	
	@Before
	public void setUp(){
		gameDao = new GameDaoImpl();
	}
	
	@Test
	public void testToStoreFirstNoughtCrossGame() throws Exception {
		//Given
		NoughtCrossGame noughtCrossGame = new NoughtCrossGame();
		
		//When
		int newGame = gameDao.createNewGame(noughtCrossGame);
		
		//Then
		assertThat(newGame, equalTo(1));
		
	}
	
	@Test
	public void testToStoreSubsequentNoughtCrossGame() throws Exception {
		//Given
		NoughtCrossGame noughtCrossGame = new NoughtCrossGame();
		gameDao.createNewGame(noughtCrossGame);
		
		//When
		int newGame = gameDao.createNewGame(noughtCrossGame);
		//Then
		assertThat(newGame, equalTo(2));
		
	}
	
	@Test(expected=NoughtCrossGameException.class)
	public void testToThrowNoughtCrossGameExceptionIfNoGameFoundForGivenId() throws Exception {
		//Given
		NoughtCrossGame noughtCrossGame = new NoughtCrossGame();
		gameDao.createNewGame(noughtCrossGame);
		
		//When
		gameDao.getGameById(5);
	}
	
	
	@Test
	public void testToRetrieveNoughtCrossGameForGivenGameId() throws Exception {
		//Given
		Player player1 = new Player(123, 'X');
		Player player2 = new Player(321, 'O');
		NoughtCrossGame noughtCrossGame = new NoughtCrossGame(player1, player2);
		gameDao.createNewGame(noughtCrossGame);
		
		//When
		NoughtCrossGame retrievedNoughtCrossGame = gameDao.getGameById(1);
		
		//Then
		assertNotNull(retrievedNoughtCrossGame);
		assertThat(retrievedNoughtCrossGame.getId(), equalTo(1));
		assertThat(retrievedNoughtCrossGame.getGameStatus(), equalTo(GameStatus.NEW));
		assertThat(retrievedNoughtCrossGame.getBoard().getCellList().size(), equalTo(9));
	}
	
	
	@Test
	public void testToUpdateNoughtCrossGame() throws Exception {
		//Given
		Player player1 = new Player(123, 'X');
		Player player2 = new Player(321, 'O');
		NoughtCrossGame noughtCrossGame = new NoughtCrossGame(player1, player2);
		gameDao.createNewGame(noughtCrossGame);
				
		noughtCrossGame.setGameStatus(GameStatus.WON);
		
		//When
		gameDao.updateGame(noughtCrossGame);
		
		//Then
		NoughtCrossGame updatedGame = gameDao.getGameById(1);
		
		assertThat(updatedGame.getId(), equalTo(1));
		assertThat(updatedGame.getGameStatus(), equalTo(GameStatus.WON));
		
	}
	
	
	
}
