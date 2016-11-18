package com.moj.noughtcross.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.moj.noughtcross.dao.GameDao;
import com.moj.noughtcross.domain.Game;
import com.moj.noughtcross.domain.NoughtCrossGame;
import com.moj.noughtcross.exception.NoughtCrossGameException;


public class GameServiceImplTest {

	@Mock
	private GameDao gameDao;
	
	@Mock
	private NoughtCrossGame noughtCrossGame;
	
	private GameServiceImpl gameService;
	
	@Before
	public void setUp(){
		initMocks(this);
		gameService = new GameServiceImpl();
		gameService.setGameDao(gameDao);
	}

	@Test
	public void testToCreateANewGame() throws Exception {
		//Given
		Game game = new Game(0,123,321);
		when(gameDao.createNewGame(any(NoughtCrossGame.class))).thenReturn(325);
		
		//When
		int gameId = this.gameService.createNewGame(game);
		
		//Then
		verify(gameDao).createNewGame(any(NoughtCrossGame.class));
		assertThat(gameId, equalTo(325));
		
	}
	
	
	@Test
	public void testToRetriveNoughtCrossGameById() throws Exception {
		//Given
		int gameId =1;
		when(gameDao.getGameById(gameId)).thenReturn(noughtCrossGame);
		
		//When
		NoughtCrossGame noughtCrossGameById = gameService.getGameById(gameId);
		
		//Then
		assertNotNull(noughtCrossGameById);
		assertThat(noughtCrossGameById, equalTo(noughtCrossGame));
	}
	
	@Test(expected=NoughtCrossGameException.class)
	public void testToThrowExceptionIfRowIdIsInvalid() throws Exception {
		//Given
		int gameid =1;
		int playerid=2;
		int rowid=5;
		int columnid =1;
		
		
		//When
		gameService.playGame(gameid,playerid,rowid, columnid);
		
	}
	
	@Test(expected=NoughtCrossGameException.class)
	public void testToThrowExceptionIfColumnIsInvalid() throws Exception {
		//Given
		int gameid =1;
		int playerid=2;
		int rowid=5;
		int columnid =1;
		
		
		//When
		gameService.playGame(gameid,playerid,rowid, columnid);
		
	}
	
}
