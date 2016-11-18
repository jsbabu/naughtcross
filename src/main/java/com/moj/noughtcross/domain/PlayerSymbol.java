package com.moj.noughtcross.domain;

public enum PlayerSymbol {
	CROSS('X'), NOUGHT('O');
	
	private char symbol;
	
	private PlayerSymbol(char symbol){
		this.symbol = symbol;
	}
	
	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
}
