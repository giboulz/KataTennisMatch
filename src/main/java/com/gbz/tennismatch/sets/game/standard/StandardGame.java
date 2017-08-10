package com.gbz.tennismatch.sets.game.standard;

import java.util.Optional;

import com.gbz.tennismatch.players.Players;
import com.gbz.tennismatch.sets.game.Game;
import com.gbz.tennismatch.sets.game.Game;

public class StandardGame extends Game {

	private StandardGameScore player1Score;

	private StandardGameScore player2Score;

	public StandardGame() {
		super();
		player1Score = StandardGameScore.SCORE_0;
		player2Score = StandardGameScore.SCORE_0;
	}

	public String getCurrentScore() {
		String result = "";
		result = computeScoreForNormalCases();
		result = computeScoreForDeuce(result);
		result = computeScoreForAdvantage(result);
		return result;
	}

	private String computeScoreForAdvantage(String result) {
		if (player1Score == StandardGameScore.ADVANTAGE || player2Score == StandardGameScore.ADVANTAGE) {
			// nothing is asked on giving the name of the player who has taken
			// the lead.
			result = "Advantage";
		}
		return result;
	}

	private String computeScoreForDeuce(String result) {
		if (player1Score == StandardGameScore.SCORE_40 && player2Score == StandardGameScore.SCORE_40) {
			result = "Deuce";
		}
		return result;
	}

	public void player1Score() {
		if (!isGameOver()) {
			checkIfGameIsOver(player1Score, player2Score, Players.PLAYER_1_POS);
			if (checkIfPlayerIsInAdvantage(player2Score)) {
				player2Score = StandardGameScore.SCORE_40;
			} else {
				player1Score = player1Score.getNextPoint().get();
			}

		}
	}

	public void player2Score() {
		if (!isGameOver()) {
			checkIfGameIsOver(player2Score, player1Score, Players.PLAYER_2_POS);
			if (checkIfPlayerIsInAdvantage(player1Score)) {
				player1Score = StandardGameScore.SCORE_40;
			} else {
				player2Score = player2Score.getNextPoint().get();
			}
		}
	}

	private boolean checkIfPlayerIsInAdvantage(StandardGameScore playerScore) {
		if (playerScore == StandardGameScore.ADVANTAGE) {
			return true;
		}
		return false;
	}



	private String computeScoreForNormalCases() {
		String result;
		result = player1Score.getName() + " - " + player2Score.getName();
		return result;
	}

	private void checkIfGameIsOver(StandardGameScore score, StandardGameScore oppositePlayerScore, int idPlayer) {

		if (!checkIfGameIsInDeuceState() && !checkIfPlayerIsInAdvantage(oppositePlayerScore)) {
			if (score == StandardGameScore.SCORE_40) {
				setWinner(idPlayer);
			}
		}

		if (score == StandardGameScore.ADVANTAGE) {
			setWinner(idPlayer);
		}

	}

	private boolean checkIfGameIsInDeuceState() {
		boolean result = false;
		if (player1Score == StandardGameScore.SCORE_40 && player2Score == StandardGameScore.SCORE_40) {
			result = true;
		}
		return result;
	}

}
