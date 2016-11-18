package com.moj.noughtcross.domain;

public class Game {

	private int id;
	private int crossplayer;
	private int noughtplayer;
	
	public Game(){
		// For JSON
	}
	
	public Game(int id, int crossplayer, int naughtplayer) {
		this.id = id;
		this.crossplayer = crossplayer;
		this.noughtplayer = naughtplayer;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCrossplayer() {
		return crossplayer;
	}
	public void setCrossplayer(int crossplayer) {
		this.crossplayer = crossplayer;
	}
	public int getNoughtplayer() {
		return noughtplayer;
	}
	public void setNoughtplayer(int noughtplayer) {
		this.noughtplayer = noughtplayer;
	}

	
	
	
}
