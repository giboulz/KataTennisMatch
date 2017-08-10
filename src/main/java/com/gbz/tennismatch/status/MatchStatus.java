package com.gbz.tennismatch.status;

public enum MatchStatus {

	READY_TO_START("Match is waiting to start"), IN_PROGRESS("In progress"), PLAYER_1_WIN(
			"Player 1 wins"), PLAYER_2_WIN("Players 2 wins");

	private String name;

	private MatchStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return "Match Status : " + this.getName();
	}

}
