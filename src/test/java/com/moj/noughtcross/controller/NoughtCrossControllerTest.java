package com.moj.noughtcross.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.moj.noughtcross.domain.Game;
import com.moj.noughtcross.domain.NoughtCrossGame;
import com.moj.noughtcross.exception.NoughtCrossGameException;
import com.moj.noughtcross.service.GameService;


public class NoughtCrossControllerTest {

	@Mock
	private GameService gameService;
	
	@Mock
	private NoughtCrossGame noughtCrossGame;
	
	private NoughtCrossController noughtCrossController;
	
	@Before
	public void setUp(){
		initMocks(this);
		noughtCrossController = new NoughtCrossController();
		noughtCrossController.setGameService(gameService);
	}

	@Test(expected=NoughtCrossGameException.class)
	public void testToThrowNoughtCrossGameExceptionIfCrossPlayerIdIsLessThanOne() throws Exception {
		//Given
		Game game = new Game(1,0,123);
		
		//When
		noughtCrossController.startNewGame(game);
	}
	
	@Test(expected=NoughtCrossGameException.class)
	public void testToThrowNoughtCrossGameExceptionIfNoughtPlayerIdIsLessThanOne() throws Exception {
		//Given
		Game game = new Game(1,123,0);
		
		//When
		noughtCrossController.startNewGame(game);
	}
	
	@Test
	public void testToReturnGameWithIdIfTheGivenPlayerIdsAreValid() throws Exception {
		//Given
		Game game = new Game(0,123,321);
		when(gameService.createNewGame(game)).thenReturn(325);
		
		//When
	    Game newGame = noughtCrossController.startNewGame(game);
	    
	    //Then
	   assertThat(newGame.getId(), equalTo(325));
	   assertThat(newGame.getCrossplayer(), equalTo(123));
	   assertThat(newGame.getNoughtplayer(), equalTo(321));
	    
	}
	
	
	@Test
	public void testToRetrieveNoughtCrossGameById() throws Exception {
		//Given
		int gameid = 1;
		when(gameService.getGameById(gameid)).thenReturn(noughtCrossGame);
		
		//When
		NoughtCrossGame noughtCrossGameById = noughtCrossController.getGame(gameid);
		
		//Then
		assertNotNull(noughtCrossGameById);
		assertThat(noughtCrossGameById, equalTo(noughtCrossGame));
	}
	
	@Test
	public void testToProcessGamePlayPutRequest() throws Exception {
		//Given
		int gameid = 5;
		int playerid =44343;
		int rowid = 3;
		int columnid = 3;
		//When
		noughtCrossController.playGame(gameid, playerid, rowid, columnid);
	}
	
}
