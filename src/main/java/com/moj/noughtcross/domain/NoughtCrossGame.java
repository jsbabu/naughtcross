package com.moj.noughtcross.domain;

public class NoughtCrossGame {
	
	private int id;
	private Board board;
	private GameStatus gameStatus;
	
	private Player crossPlayer;
	private Player noughtPlayer;
	private int lastPlayerId;
	private int wonPlayerId;
	
	
	public NoughtCrossGame(){
		// For JSON
	}
	
	public NoughtCrossGame(Player crossPlayer,Player noughtPlayer ) {
		this.crossPlayer = crossPlayer;
		this.noughtPlayer = noughtPlayer;
		board = new Board();
		gameStatus = GameStatus.NEW;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Player getCrossPlayer() {
		return crossPlayer;
	}

	public void setCrossPlayer(Player crossPlayer) {
		this.crossPlayer = crossPlayer;
	}

	public Player getNoughtPlayer() {
		return noughtPlayer;
	}

	public void setNoughtPlayer(Player noughtPlayer) {
		this.noughtPlayer = noughtPlayer;
	}

	public int getLastPlayerId() {
		return lastPlayerId;
	}

	public void setLastPlayerId(int lastPlayerId) {
		this.lastPlayerId = lastPlayerId;
	}

	public int getWonPlayerId() {
		return wonPlayerId;
	}

	public void setWonPlayerId(int wonPlayerId) {
		this.wonPlayerId = wonPlayerId;
	}

}
