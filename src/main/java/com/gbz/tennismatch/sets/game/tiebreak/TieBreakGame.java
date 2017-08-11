package com.gbz.tennismatch.sets.game.tiebreak;

import java.util.Optional;

import com.gbz.tennismatch.players.Players;
import com.gbz.tennismatch.sets.game.Game;

public class TieBreakGame extends Game {

	private int player1Score;

	private int player2Score;

	private static final int MINIMUM_SCORE_TO_WIN = 7;
	private static final int MINIMUM_GAP_TO_WIN = 2;

	public TieBreakGame() {
		super();
		player1Score = 0;
		player2Score = 0;

	}

	@Override
	public String getCurrentScore() {
		// TODO Auto-generated method stub
		return player1Score + " - " + player2Score;
	}

	@Override
	public void player1Score() {
		player1Score++;
		checkIfGameIsOver(player1Score, player2Score, Players.PLAYER_1_POS);
	}

	@Override
	public void player2Score() {
		player2Score++;
		checkIfGameIsOver(player2Score, player1Score, Players.PLAYER_2_POS);
	}

	private void checkIfGameIsOver(int currentPlayerScore, int oppositePlayerScore, int idPlayer) {
		if (currentPlayerScore >= MINIMUM_SCORE_TO_WIN
				&& currentPlayerScore - oppositePlayerScore >= MINIMUM_GAP_TO_WIN) {
			setWinner(idPlayer);
		}
	}

	@Override
	public String getGameType() {
		// TODO Auto-generated method stub
		return "Tie Break";
	}

}
