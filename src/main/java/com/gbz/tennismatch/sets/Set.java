package com.gbz.tennismatch.sets;

import java.util.ArrayList;
import java.util.List;

import com.gbz.tennismatch.sets.game.Game;

public class Set {

	private List<Game> games;

	public Set() {
		this.games = new ArrayList<Game>();
	}

	private void anyPlayerScore() {

	}

	public void player1Score() {
		anyPlayerScore();
	}

	public void player2Score() {
		anyPlayerScore();
	}
}
