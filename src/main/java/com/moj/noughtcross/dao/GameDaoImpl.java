package com.moj.noughtcross.dao;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.core.Response;

import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moj.noughtcross.domain.NoughtCrossGame;
import com.moj.noughtcross.exception.NoughtCrossGameException;

/**
 * This is a mock implementation and should not be used in Live
 * This implementation should be changed to use proper Datastore
 * @author jsbabu
 *
 */
public class GameDaoImpl implements GameDao {

	private static final String GAME_ID_NOT_FOUND = "Game Id: %s not found";
	private Map<Integer, String> gameIdDataMap = new ConcurrentHashMap<Integer, String>();
	
	@Override
	public int createNewGame(NoughtCrossGame noughtCrossGame) {
		int nextGameId = getNextGameId();
		noughtCrossGame.setId(nextGameId);
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		
		String jsonNoughtCrossGame = gson.toJson(noughtCrossGame);
		
		getGameIdDataMap().put(nextGameId, jsonNoughtCrossGame);
		return nextGameId;
	}
	
	@Override
	public NoughtCrossGame getGameById(int gameid) {
		
		String noughtCrossGameStr = gameIdDataMap.get(gameid);
		
		if(!StringUtils.isEmpty(noughtCrossGameStr)){
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		
		NoughtCrossGame noughtCrossGame = gson.fromJson(noughtCrossGameStr, NoughtCrossGame.class);
		return noughtCrossGame;
		
		} else {
			throw new NoughtCrossGameException(String.format(GAME_ID_NOT_FOUND, gameid), Response.Status.NOT_FOUND);
		}
	}
	
	
	@Override
	public void updateGame(NoughtCrossGame noughtCrossGame) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String jsonNoughtCrossGame = gson.toJson(noughtCrossGame);
		
		getGameIdDataMap().put(noughtCrossGame.getId(), jsonNoughtCrossGame);
		
	}
	
	
	private int getNextGameId() {
		
		int nextGameId;
		
		if(getGameIdDataMap().isEmpty()){
			nextGameId = 1;
		}
		else{
			Optional<Integer> maxId = getGameIdDataMap().keySet().stream().max(Integer::compareTo);
			nextGameId = maxId.get() + 1;
		}
		return nextGameId;
	}

	public Map<Integer, String> getGameIdDataMap() {
		return gameIdDataMap;
	}

	public void setGameIdDataMap(Map<Integer, String> gameIdDataMap) {
		this.gameIdDataMap = gameIdDataMap;
	}






}
