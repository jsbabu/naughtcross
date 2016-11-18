package com.moj.noughtcross.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.moj.noughtcross.domain.Board;
import com.moj.noughtcross.domain.Cell;

public class BoardServiceImplTest {

	
	private BoardService boardService;

	@Before
	public void setUp(){
		boardService = new BoardServiceImpl();
	}
	
	
	@Test
	public void testToReturnTrueIfGameWonByRow() throws Exception {
		//Given
		Board board = new Board();
		List<Cell> cellList = board.getCellList();
		
		cellList.get(0).setValue('X');
		cellList.get(1).setValue('X');
		cellList.get(2).setValue('X');
		
		//When
		boolean gameWon = boardService.isGameWon(board, 'X');
		
		assertThat(gameWon,equalTo(true));
		
	}
	
	@Test
	public void testToReturnTrueIfGameWonByColumn() throws Exception {
		//Given
		Board board = new Board();
		List<Cell> cellList = board.getCellList();
		
		cellList.get(0).setValue('X');
		cellList.get(3).setValue('X');
		cellList.get(6).setValue('X');
		
		//When
		boolean gameWon = boardService.isGameWon(board, 'X');
		
		assertThat(gameWon,equalTo(true));
		
	}
	
	@Test
	public void testToReturnTrueIfGameWonByDiagonal() throws Exception {
		//Given
		Board board = new Board();
		List<Cell> cellList = board.getCellList();
		
		cellList.get(0).setValue('X');
		cellList.get(4).setValue('X');
		cellList.get(8).setValue('X');
		
		//When
		boolean gameWon = boardService.isGameWon(board, 'X');
		
		assertThat(gameWon,equalTo(true));
		
	}
	
	
	@Test
	public void testToReturnFalseIfGameNotWon() throws Exception {
		//Given
		Board board = new Board();
		List<Cell> cellList = board.getCellList();
		cellList.get(0).setValue('X');

		//When
		boolean gameWon = boardService.isGameWon(board, 'X');
		
		assertThat(gameWon,equalTo(false));
		
	}
	

	
	
	@Test
	public void testToReturnFalseIfBoardIsNotFull() throws Exception {
		//Given
		Board board = new Board();
		List<Cell> cellList = board.getCellList();
		cellList.get(0).setValue('X');
		cellList.get(1).setValue('X');
		//When
		boolean boardFull = boardService.isBoardFull(board);
		
		assertThat(boardFull, equalTo(false));
		
	}
	
	
	@Test
	public void testToReturnTrueIfBoardIsFull() throws Exception {
		//Given
		Board board = new Board();
		List<Cell> cellList = board.getCellList();
		
		for (Cell cell : cellList) {
			cell.setValue('X');
		}
		
		
		//When
		boolean boardFull = boardService.isBoardFull(board);
		
		assertThat(boardFull, equalTo(true));
		
	}
	

	
}
