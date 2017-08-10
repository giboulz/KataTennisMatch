package com.gbz.game;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import com.gbz.tennismatch.players.Players;
import com.gbz.tennismatch.sets.game.Game;
import com.gbz.tennismatch.sets.game.tiebreak.TieBreakGame;

public class TieBreackGameTest {

	Game sut;

	@Before
	public void setUp() {
		sut = new TieBreakGame();
	}

	@Test
	public void new_tiebreack_should_have_0_0_score() {
		// arrange
		assertThat(sut.getCurrentScore(), is("0 - 0"));
	}

	@Test
	public void new_tiebreack_should_not_be_over() {
		// arrange
		assertThat(sut.isGameOver(), is(false));
	}

	@Test
	public void player_1_score_twice_the_score_should_be_2_0() {
		// act
		sut.player1Score();
		sut.player1Score();

		// arrange
		assertThat(sut.getCurrentScore(), is("2 - 0"));
	}

	@Test
	public void player_1_and_player_2_score_2_points_each_the_score_should_be_2_2() {
		// act
		sut.player1Score();
		sut.player1Score();

		sut.player2Score();
		sut.player2Score();

		// arrange
		assertThat(sut.getCurrentScore(), is("2 - 2"));
	}

	@Test
	public void on_6_0_player_1_score_should_win_the_game() {
		// arrange
		for (int i = 0; i < 6; i++) {
			sut.player1Score();
		}

		// act
		sut.player1Score();

		// assert
		assertThat(sut.getCurrentScore(), is("7 - 0"));
		assertThat(sut.isGameOver(), is(true));
		assertThat(sut.getWinner().get(), is(Players.PLAYER_1_POS));
	}

	@Test
	public void on_6_6_player_1_should_not_win_the_game_gap_point_is_needed() {
		// arrange
		for (int i = 0; i < 6; i++) {
			sut.player1Score();
			sut.player2Score();
		}

		// act
		sut.player1Score();

		// assert
		assertThat(sut.getCurrentScore(), is("7 - 6"));
		assertThat(sut.isGameOver(), is(false));
	}

	@Test
	public void on_7_6_player_1_should_win_the_game_gap_point_condition_is_fullfill() {
		// arrange
		for (int i = 0; i < 6; i++) {
			sut.player1Score();
			sut.player2Score();
		}
		sut.player1Score();

		// act
		sut.player1Score();

		// assert
		assertThat(sut.getCurrentScore(), is("8 - 6"));
		assertThat(sut.isGameOver(), is(true));
		assertThat(sut.getWinner().get(), is(Players.PLAYER_1_POS));
	}

	@Test
	public void testing_extremly_long_tie_break_should_be_won_by_gap_margin() {
		// arrange
		for (int i = 0; i < 100; i++) {
			sut.player1Score();
			sut.player2Score();
		}
		sut.player2Score();

		// act
		sut.player2Score();

		// assert
		assertThat(sut.getCurrentScore(), is("100 - 102"));
		assertThat(sut.isGameOver(), is(true));
		assertThat(sut.getWinner().get(), is(Players.PLAYER_2_POS));
	}

}
