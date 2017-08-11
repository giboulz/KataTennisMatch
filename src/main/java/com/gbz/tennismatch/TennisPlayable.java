package com.gbz.tennismatch;

import java.util.Optional;

public interface TennisPlayable {

	void player1Score();

	void player2Score();

	Optional<Integer> getWinner();

	String getCurrentScore();

}
