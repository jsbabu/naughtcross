package com.moj.noughtcross.domain;

public class Player {

	private int id;
	private char symbol;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	
	public Player(int id, char symbol){
		this.id = id;
		this.symbol = symbol;
	}
	
}
