package com.gbz.tennismatch.sets.game.standard;

public enum StandardGameScore {

	// java does not allow "just a number" as id.
	//i would have prefered 0, 15, ...
	SCORE_0(0, "0"), SCORE_15(1, "15"), SCORE_30(2, "30"), SCORE_40(3, "40"), AVANTAGE(4, "Avantage");

	private String name;
	private int value;

	private StandardGameScore(int value, String name) {
		this.name = name;
		this.value = value;
	}

}
