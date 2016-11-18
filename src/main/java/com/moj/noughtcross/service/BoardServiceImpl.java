package com.moj.noughtcross.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.moj.noughtcross.domain.Board;
import com.moj.noughtcross.domain.Cell;

public class BoardServiceImpl implements BoardService {

	@Override
	public boolean isGameWon(Board board, char playerSymbol) {
		
		if (checkRowForWin(board, playerSymbol) || checkColumnForWin(board, playerSymbol)
				|| checkDiagonalForWin(board, playerSymbol)) {
			return true;
		}
		else
		return false;
	}

	@Override
	public boolean isBoardFull(Board board) {
		Map<Integer, List<Cell>> collect = board.getCellList().stream().collect(Collectors.groupingBy(Cell::getRow));
		Set<Integer> keySet = collect.keySet();

		if( keySet.stream()
		.filter(i -> collect.get(i).stream().noneMatch(cell -> cell.getValue() == '-')).count()==3)
			return true;
		else
			return false;

	}
	
	
	private boolean checkRowForWin(Board board, char playerSymbol) {

		Map<Integer, List<Cell>> collect = board.getCellList().stream().collect(Collectors.groupingBy(Cell::getRow));

		Set<Integer> keySet = collect.keySet();

		if (keySet.stream().filter(i -> collect.get(i).stream().allMatch(cell -> cell.getValue() == playerSymbol))
				.count() == 1) {
			return true;
		}
		;

		return false;

	}
	
	private boolean checkColumnForWin(Board board, char playerSymbol) {

		Map<Integer, List<Cell>> collect = board.getCellList().stream().collect(Collectors.groupingBy(Cell::getColumn));

		Set<Integer> keySet = collect.keySet();

		if (keySet.stream().filter(i -> collect.get(i).stream().allMatch(cell -> cell.getValue() == playerSymbol))
				.count() == 1) {
			return true;
		}
		;

		return false;

	}

	private boolean checkDiagonalForWin(Board board, char playerSymbol) {

		List<Cell> cellList = board.getCellList();

		if (cellList.get(0).getValue() == playerSymbol && cellList.get(4).getValue() == playerSymbol
				&& cellList.get(8).getValue() == playerSymbol) {
			return true;
		} else if (cellList.get(2).getValue() == playerSymbol && cellList.get(4).getValue() == playerSymbol
				&& cellList.get(6).getValue() == playerSymbol) {
			return true;
		}
		return false;

	}

}
