package com.moj.noughtcross.domain;

public class Cell {

	private int row;
	private int column;
	private char value;
	
	public Cell(int row, int column, char value){
		this.row = row;
		this.column = column;
		this.value = value;
	}
	
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public char getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
	}

	
	
	
	
}
