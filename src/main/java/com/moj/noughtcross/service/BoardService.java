package com.moj.noughtcross.service;

import com.moj.noughtcross.domain.Board;

public interface BoardService {

	boolean isBoardFull(Board board);
	
	boolean isGameWon(Board board, char playerSymbol);
	
	
}
