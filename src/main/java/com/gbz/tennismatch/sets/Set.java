package com.gbz.tennismatch.sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import com.gbz.tennismatch.players.Players;
import com.gbz.tennismatch.sets.game.Game;
import com.gbz.tennismatch.sets.game.standard.StandardGame;
import com.gbz.tennismatch.sets.game.tiebreak.TieBreakGame;

public class Set {

	private List<Game> games;

	private static final int NUMBER_OF_GAME_TO_WIN = 6;
	private static final int GAP_OF_SCORE_TO_WIN = 2;
	private static final int NUMBER_OF_GAME_TO_TRIGGER_TIE_BREAK = 6;

	public Set() {
		this.games = new ArrayList<Game>();
		initialiseFirstGame();
	}

	private void initialiseFirstGame() {
		Game firstGame = new StandardGame();
		games.add(firstGame);
	}

	private Game getCurrentGame() {
		return games.get(games.size() - 1);
	}

	public void player1Score() {
		getCurrentGame().player1Score();
		addNewGameIfNeeded();
	}

	public void player2Score() {
		getCurrentGame().player2Score();
		addNewGameIfNeeded();
	}

	private void addNewGameIfNeeded() {
		if (!isSetOver() && getCurrentGame().isGameOver()) {
			addNewGame();
		}

	}

	private void addNewGame() {
		int player1Score = computeScoreForPlayer(Players.PLAYER_1_POS);
		int player2Score = computeScoreForPlayer(Players.PLAYER_2_POS);

		if (player1Score == NUMBER_OF_GAME_TO_TRIGGER_TIE_BREAK
				&& player2Score == NUMBER_OF_GAME_TO_TRIGGER_TIE_BREAK) {
			Game tieBreak = new TieBreakGame();
			games.add(tieBreak);
		} else {
			Game standardGame = new StandardGame();
			games.add(standardGame);
		}
	}

	public String getCurrentScore() {
		int player1Score = computeScoreForPlayer(Players.PLAYER_1_POS);
		int player2Score = computeScoreForPlayer(Players.PLAYER_2_POS);

		return player1Score + " - " + player2Score;
	}

	private int computeScoreForPlayer(int idPlayer) {
		long result = games.stream().filter(filterForGameThatAreOver()).filter(filterForCurrentPlayer(idPlayer))
				.count();
		return (int) result;
	}

	private Predicate<? super Game> filterForCurrentPlayer(int idPlayer) {
		return x -> x.getWinner().get().compareTo(idPlayer) == 0;
	}

	private Predicate<? super Game> filterForGameThatAreOver() {
		return x -> x.isGameOver() == true;
	}

	public boolean isSetOver() {
		boolean result = false;
		int player1Score = computeScoreForPlayer(Players.PLAYER_1_POS);
		int player2Score = computeScoreForPlayer(Players.PLAYER_2_POS);

		if (player1Score >= NUMBER_OF_GAME_TO_TRIGGER_TIE_BREAK
				&& player2Score >= NUMBER_OF_GAME_TO_TRIGGER_TIE_BREAK) {
			result = checkScoreWhenTieBreakTriggered(result, player1Score, player2Score);
		} else {
			result = checkScoreForStandardGame(result, player1Score, player2Score);
		}
		return result;
	}

	private boolean checkScoreWhenTieBreakTriggered(boolean result, int player1Score, int player2Score) {
		if (player1Score == NUMBER_OF_GAME_TO_WIN + 1 || player2Score == NUMBER_OF_GAME_TO_WIN + 1) {
			result = true;
		}

		return result;
	}

	private boolean checkScoreForStandardGame(boolean result, int player1Score, int player2Score) {
		if (player1Score >= NUMBER_OF_GAME_TO_WIN || player2Score >= NUMBER_OF_GAME_TO_WIN) {
			if (Math.abs(player2Score - player1Score) >= GAP_OF_SCORE_TO_WIN) {
				result = true;
			}
		}
		return result;
	}

	public Optional<Integer> getWinner() {
		Integer result = null;
		if (!isSetOver()) {
			return Optional.empty();
		}
		// must recompute score for player, as it is not that consuming, i am
		// leaving it
		// like this.
		int player1Score = computeScoreForPlayer(Players.PLAYER_1_POS);
		int player2Score = computeScoreForPlayer(Players.PLAYER_2_POS);

		if (player1Score > player2Score) {
			result = Players.PLAYER_1_POS;
		} else {
			result = Players.PLAYER_2_POS;
		}
		return Optional.of(result);
	}

	public String getCurrentGameType() {
		return getCurrentGame().getGameType();
	}
}
