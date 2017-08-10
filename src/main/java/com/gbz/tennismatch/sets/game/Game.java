package com.gbz.tennismatch.sets.game;

import java.util.Optional;

public interface Game {

	String getCurrentScore();

	void player1Score();

	void player2Score();

	boolean isGameOver(); 
	
	Optional<Integer> getWinner(); 
}
