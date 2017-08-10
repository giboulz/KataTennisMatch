package com.gbz.tennismatch.sets.game.standard;

import java.util.Optional;

public enum StandardGameScore {

	// java does not allow "just a number" as id.
	// i would have prefered 0, 15, ...
	SCORE_0("0"), SCORE_15("15"), SCORE_30("30"), SCORE_40("40"), ADVANTAGE("Avantage"), GAME_OVER("game over");

	private String name;

	private StandardGameScore(String name) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Optional<StandardGameScore> getNextPoint() {
		Optional<StandardGameScore> result = null;
		switch (this) {
		case SCORE_0:
			result = Optional.of(SCORE_15);
			break;
		case SCORE_15:
			result = Optional.of(SCORE_30);
			break;
		case SCORE_30:
			result = Optional.of(SCORE_40);
			break;
		case SCORE_40:
			result = Optional.of(ADVANTAGE);
			break;
		case ADVANTAGE:
			result = Optional.of(GAME_OVER);
			break;
		default:
			result = Optional.empty();
		}
		return result;
	}
}
