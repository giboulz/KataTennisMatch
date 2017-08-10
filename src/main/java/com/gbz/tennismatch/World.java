package com.gbz.tennismatch;

import java.util.Optional;

import com.gbz.tennismatch.players.Player;
import com.gbz.tennismatch.players.Players;
import com.gbz.tennismatch.sets.Sets;
import com.gbz.tennismatch.status.MatchStatus;

public class World {

	private MatchStatus matchStatus;

	private Players players;

	private Sets sets;

	public World(String player1, String player2) {

		players = new Players(player1, player2);
		startMatch();
		this.matchStatus = MatchStatus.READY_TO_START;
	}

	public void startMatch() {

	}

	public MatchStatus getMatchStatus() {
		return matchStatus;
	}

	public Optional<Player> getPlayer1() {
		return players.getPlayer(Players.PLAYER_1_POS);
	}

	public Optional<Player> getPlayer2() {
		return players.getPlayer(Players.PLAYER_2_POS);
	}

	private void playerScore() {
		if (getMatchStatus() == MatchStatus.READY_TO_START) {
			this.matchStatus = MatchStatus.IN_PROGRESS;
		}
	}

	public void player1Score() {
		playerScore();

	}

	public void player2Score() {
		playerScore();

	}

}
