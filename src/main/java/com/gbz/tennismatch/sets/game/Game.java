package com.gbz.tennismatch.sets.game;

import java.util.Optional;

public abstract class Game {

	private boolean gameOver;

	private int idWinner;

	public abstract String getCurrentScore();

	public abstract void player1Score();

	public abstract void player2Score();

	public Game() {
		gameOver = false;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public Optional<Integer> getWinner() {
		if (!isGameOver()) {
			return Optional.empty();
		}
		return Optional.of(idWinner);
	}

	protected void setWinner(int idPlayer) {
		this.gameOver = true;
		this.idWinner = idPlayer;
	}

	public abstract String getGameType();

}
