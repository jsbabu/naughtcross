package com.moj.noughtcross.service;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.moj.noughtcross.dao.GameDao;
import com.moj.noughtcross.domain.Board;
import com.moj.noughtcross.domain.Cell;
import com.moj.noughtcross.domain.Game;
import com.moj.noughtcross.domain.GameStatus;
import com.moj.noughtcross.domain.NoughtCrossGame;
import com.moj.noughtcross.domain.Player;
import com.moj.noughtcross.domain.PlayerSymbol;
import com.moj.noughtcross.exception.NoughtCrossGameException;

public class GameServiceImpl implements GameService {

	private static final String INVALID_PLAYER = "Invalid player %d";

	private static final String INVALID_PLAYER_TO_START_GAME = "Invalid player to start game %d";

	private static final String INVALID_MOVE_ROWID = "Invalid move rowid:%d, columnid:%d";

	private static final String INVALID_ROW_OR_COLUMN_ID = "Invalid row or column id rowid:%d, columnid:%d";

	private static final String COMPLETED_GAME = "Already Completed Game";
	
	@Autowired
	private GameDao gameDao;
	
	@Autowired
	private BoardService boardService;
	
	
	public int createNewGame(Game game) {

		Player crossPlayer = new Player(game.getCrossplayer(), PlayerSymbol.CROSS.getSymbol());
		Player noughtPlayer = new Player(game.getNoughtplayer(), PlayerSymbol.NOUGHT.getSymbol());

		NoughtCrossGame noughtCrossGame = new NoughtCrossGame(crossPlayer, noughtPlayer);

		return gameDao.createNewGame(noughtCrossGame);
	}


	@Override
	public NoughtCrossGame getGameById(int gameid) {
		return gameDao.getGameById(gameid);
	}

	@Override
	public void playGame(int gameid, int playerid, int rowid, int columnid) {

		isRowAndColumnIdValid(rowid, columnid);
		
		NoughtCrossGame noughtCrossGame = gameDao.getGameById(gameid);

		isValidPlayer(playerid, noughtCrossGame);

		isGameInValidState(noughtCrossGame);

		Board board = noughtCrossGame.getBoard();

		if (noughtCrossGame.getGameStatus() == GameStatus.NEW) {

			playNewGame(playerid, rowid, columnid, noughtCrossGame, board);

		} else {

			playInPlayGame(playerid, rowid, columnid, noughtCrossGame, board);

		}

	}
	
	private void isRowAndColumnIdValid(int rowid, int columnid) {
		if (rowid < 1 || rowid > 3 || columnid < 1 || columnid > 3){
			throw new NoughtCrossGameException(String.format(INVALID_ROW_OR_COLUMN_ID, rowid, columnid),
					Response.Status.BAD_REQUEST);
		}
	}
	
	
	private void playInPlayGame(int playerid, int rowid, int columnid, NoughtCrossGame noughtCrossGame, Board board) {

		if (noughtCrossGame.getLastPlayerId() == playerid) {
			throw new NoughtCrossGameException(String.format(INVALID_MOVE_ROWID, rowid, columnid),
					Response.Status.BAD_REQUEST);
		} else {

			int cellIndex = board.getCellIndex(rowid, columnid);
			Cell cell = board.getCellList().get(cellIndex);

			if (cell.getValue() == PlayerSymbol.CROSS.getSymbol()
					|| cell.getValue() == PlayerSymbol.NOUGHT.getSymbol()) {
				throw new NoughtCrossGameException(String.format(INVALID_MOVE_ROWID, rowid, columnid),
						Response.Status.BAD_REQUEST);
			} else {

				populateCellValue(playerid, noughtCrossGame, cellIndex);
				noughtCrossGame.setLastPlayerId(playerid);

				char currentPlayerSymbol = getCurrentPlayerSymbol(playerid, noughtCrossGame);

				if (boardService.isGameWon(board, currentPlayerSymbol)) {
					noughtCrossGame.setGameStatus(GameStatus.WON);
					noughtCrossGame.setWonPlayerId(playerid);
				} else if (boardService.isBoardFull(board)) {
					noughtCrossGame.setGameStatus(GameStatus.DRAW);
				}
				gameDao.updateGame(noughtCrossGame);
			}

		}
	}

	private void playNewGame(int playerid, int rowid, int columnid, NoughtCrossGame noughtCrossGame, Board board) {
		// First player should be crossplayer

		isCrossPlayer(playerid, noughtCrossGame);

		int cellIndex = board.getCellIndex(rowid, columnid);
		Cell cell = board.getCellList().get(cellIndex);

		if (cell.getValue() == PlayerSymbol.CROSS.getSymbol() || cell.getValue() == PlayerSymbol.NOUGHT.getSymbol()) {
			throw new NoughtCrossGameException(String.format(INVALID_MOVE_ROWID, rowid, columnid),
					Response.Status.BAD_REQUEST);
		} else {

			populateCellValue(playerid, noughtCrossGame, cellIndex);
			noughtCrossGame.setGameStatus(GameStatus.INPLAY);
			noughtCrossGame.setLastPlayerId(playerid);
			gameDao.updateGame(noughtCrossGame);

		}
	}

	private void isCrossPlayer(int playerid, NoughtCrossGame noughtCrossGame) {
		if (noughtCrossGame.getCrossPlayer().getId() != playerid) {
			throw new NoughtCrossGameException(String.format(INVALID_PLAYER_TO_START_GAME, playerid),
					Response.Status.BAD_REQUEST);
		}
	}



	private char getCurrentPlayerSymbol(int playerid, NoughtCrossGame noughtCrossGame) {

		if (noughtCrossGame.getCrossPlayer().getId() == playerid) {
			return noughtCrossGame.getCrossPlayer().getSymbol();
		} else {
			return noughtCrossGame.getNoughtPlayer().getSymbol();
		}
	}

	
	private void populateCellValue(int playerid, NoughtCrossGame noughtCrossGame, int cellIndex) {
		Cell gameCell = noughtCrossGame.getBoard().getCellList().get(cellIndex);

		if (noughtCrossGame.getCrossPlayer().getId() == playerid) {
			gameCell.setValue(PlayerSymbol.CROSS.getSymbol());
		} else {
			gameCell.setValue(PlayerSymbol.NOUGHT.getSymbol());
		}
	}

	private void isGameInValidState(NoughtCrossGame noughtCrossGame) {
		if (noughtCrossGame.getGameStatus() == GameStatus.WON || noughtCrossGame.getGameStatus() == GameStatus.DRAW)
			throw new NoughtCrossGameException(COMPLETED_GAME, Response.Status.BAD_REQUEST);
	}

	private boolean isValidPlayer(int playerid, NoughtCrossGame noughtCrossGame) {
		if (noughtCrossGame.getCrossPlayer().getId() == playerid)
			return true;
		else if (noughtCrossGame.getNoughtPlayer().getId() == playerid)
			return true;
		else {
			throw new NoughtCrossGameException(String.format(INVALID_PLAYER, playerid),
					Response.Status.BAD_REQUEST);
		} 
			
	}

	public void setGameDao(GameDao gameDao) {
		this.gameDao = gameDao;
	}

}
