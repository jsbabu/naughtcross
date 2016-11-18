package com.moj.noughtcross.domain;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private List<Cell> cellList;

	public Board() {
		populateBoard();
	}

	private void populateBoard() {
		cellList = new ArrayList<Cell>(9);
		cellList.add(0, new Cell(1, 1, '-'));
		cellList.add(1, new Cell(1, 2, '-'));
		cellList.add(2, new Cell(1, 3, '-'));

		cellList.add(3, new Cell(2, 1, '-'));
		cellList.add(4, new Cell(2, 2, '-'));
		cellList.add(5, new Cell(2, 3, '-'));

		cellList.add(6, new Cell(3, 1, '-'));
		cellList.add(7, new Cell(3, 2, '-'));
		cellList.add(8, new Cell(3, 3, '-'));
	}

	public List<Cell> getCellList() {
		return cellList;
	}

	public void setCellList(List<Cell> cellList) {
		this.cellList = cellList;
	}

	public int getCellIndex(int row, int column) {

		int index = 0;

		for (int count = 0; count < cellList.size(); count++) {

			Cell cell = cellList.get(count);

			if (cell.getRow() == row && cell.getColumn() == column) {
				index = count;
			}

		}
		return index;
	}

}
