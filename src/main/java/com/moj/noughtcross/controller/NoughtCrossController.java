package com.moj.noughtcross.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.moj.noughtcross.domain.Game;
import com.moj.noughtcross.domain.NoughtCrossGame;
import com.moj.noughtcross.exception.NoughtCrossGameException;
import com.moj.noughtcross.service.GameService;

@Path("/noughtcross")
public class NoughtCrossController {

	private static final String CROSS_OR_NOUGHT_OR_BOTH_ID_MISSING = "Cross or Nought Or Both Player Id missing";
	
	@Autowired
	private GameService gameService;
	
	@POST
	@Path("/game/new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Game startNewGame(Game game) {
		if(game.getCrossplayer()< 1 ||game.getNoughtplayer() < 1 )
			throw new NoughtCrossGameException(CROSS_OR_NOUGHT_OR_BOTH_ID_MISSING, Response.Status.BAD_REQUEST);
		
		int newGameId = this.gameService.createNewGame(game);
		Game newGame = new Game(newGameId, game.getCrossplayer(), game.getNoughtplayer());
		return newGame;
	}

	
	@GET
	@Path("/game/{gameid}/status")
	@Produces(MediaType.APPLICATION_JSON)
	public NoughtCrossGame getGame(@PathParam("gameid") int gameid) {
		NoughtCrossGame noughtCrossGame = gameService.getGameById(gameid);
		return noughtCrossGame;
	}

	@PUT
	@Path("/play/{gameid}/{playerid}/{rowid}/{columnid}")
	@Produces(MediaType.APPLICATION_JSON)
	public void playGame(@PathParam("gameid") int gameid, @PathParam("playerid") int playerid,  @PathParam("rowid") int rowid,@PathParam("columnid") int columnid) {
		gameService.playGame(gameid,playerid,rowid,columnid);
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}




}
