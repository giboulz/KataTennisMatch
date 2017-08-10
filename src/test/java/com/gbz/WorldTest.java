package com.gbz;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.gbz.tennismatch.World;
import com.gbz.tennismatch.status.MatchStatus;

public class WorldTest {

	World sut;

	private static final String PLAYER1 = "Player 1 name";
	private static final String PLAYER2 = "Player 2 name ";

	@Before
	public void setUp() {
		sut = new World(PLAYER1, PLAYER2);
	}

	@Test
	public void after_world_creation_game_status_should_be_ready_to_start() {
		// assert
		assertThat(sut.getMatchStatus(), is(MatchStatus.READY_TO_START));
	}

	@Test
	public void after_world_creation_players_name_should_be_set() {
		// assert
		assertThat(sut.getPlayer1().get().getName(), is(PLAYER1));
		assertThat(sut.getPlayer2().get().getName(), is(PLAYER2));
	}

	@Test
	public void after_player1_first_point_is_score_game_status_should_be_in_progress() {
		// act
		sut.player1Score();

		// assert
		assertThat(sut.getMatchStatus(), is(MatchStatus.IN_PROGRESS));
	}

	@Test
	public void after_player2_first_point_is_score_game_status_should_be_in_progress() {
		// act
		sut.player2Score();

		// assert
		assertThat(sut.getMatchStatus(), is(MatchStatus.IN_PROGRESS));
	}
}
