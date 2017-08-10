package com.gbz.tennismatch.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Players {

	private List<Player> players;

	public static final int PLAYER_1_POS = 0;
	public static final int PLAYER_2_POS = 1;

	public Players(String player1Name, String player2Name) {
		this.players = new ArrayList<Player>();

		addPlayerAtPos(player1Name, PLAYER_1_POS);
		addPlayerAtPos(player2Name, PLAYER_2_POS);

	}

	private void addPlayerAtPos(String player1Name, int pos) {
		Player player1 = new Player();
		player1.setName(player1Name);
		players.add(pos, player1);
	}

	public Optional<Player> getPlayer(int pos) {
		if (pos != PLAYER_1_POS && pos != PLAYER_2_POS) {
			return Optional.empty();
		}
		return Optional.of(players.get(pos));
	}

}
