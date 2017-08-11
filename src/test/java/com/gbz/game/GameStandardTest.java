package com.gbz.game;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import java.util.Optional;
import org.junit.Before;
import com.gbz.tennismatch.players.Players;
import com.gbz.tennismatch.sets.game.Game;
import com.gbz.tennismatch.sets.game.Game;
import com.gbz.tennismatch.sets.game.standard.StandardGame;

public class GameStandardTest {

	Game sut;

	@Before
	public void setUp() {
		sut = new StandardGame();
	}

	@Test
	public void without_any_player_scoring_the_score_should_be_0_0() {
		assertThat(sut.getCurrentScore(), is("0 - 0"));
	}

	@Test
	public void when_game_is_not_finished_there_is_no_winner() {
		Optional<Integer> empty = Optional.empty();
		Optional<Integer> winner = sut.getWinner();

		assertThat(sut.isGameOver(), is(false));
		assertThat(winner, is(empty));
	}

	@Test
	public void after_player1_score_the_score_should_be_15_0() {
		// act
		sut.player1Score();

		// assert
		assertThat(sut.getCurrentScore(), is("15 - 0"));
	}

	@Test
	public void after_player2_score_the_score_should_be_0_15() {
		// act
		sut.player2Score();

		// assert
		assertThat(sut.getCurrentScore(), is("0 - 15"));
	}

	@Test
	public void on_15_15_after_player_1_score_the_score_should_be_30_15() {
		// arrange
		sut.player1Score();
		sut.player2Score();

		// act
		sut.player1Score();

		// assert
		assertThat(sut.getCurrentScore(), is("30 - 15"));
	}

	@Test
	public void on_30_15_after_player_1_score_the_score_should_be_40_15() {
		// arrange
		sut.player1Score();
		sut.player1Score();
		sut.player2Score();

		// act
		sut.player1Score();

		// assert
		assertThat(sut.getCurrentScore(), is("40 - 15"));
	}

	@Test
	public void on_40_15_after_player_1_score_the_game_should_be_won_by_player_1() {
		// arrange
		sut.player1Score();
		sut.player1Score();
		sut.player1Score();
		sut.player2Score();

		// act
		sut.player1Score();

		// arrange
		assertThat(sut.isGameOver(), is(true));
		assertThat(sut.getWinner().get(), is(Players.PLAYER_1_POS));

	}

	@Test
	public void on_0_40_after_player_2_score_the_game_should_be_won_by_player_2() {
		// arrange
		sut.player2Score();
		sut.player2Score();
		sut.player2Score();

		// act
		sut.player2Score();

		// assert
		assertThat(sut.isGameOver(), is(true));
		assertThat(sut.getWinner().get(), is(Players.PLAYER_2_POS));
	}

	@Test
	public void assert_40_40_produce_a_deuce() {
		// arrange
		sut.player1Score();
		sut.player1Score();
		sut.player1Score();

		sut.player2Score();
		sut.player2Score();
		sut.player2Score();

		// assert
		assertThat(sut.getCurrentScore(), is("Deuce"));
	}

	@Test
	public void a_deuce_scored_become_an_advantage() {
		// arrange
		assert_40_40_produce_a_deuce();

		// act
		sut.player1Score();

		// assert
		assertThat(sut.getCurrentScore(), is("Advantage"));
		assertThat(sut.isGameOver(), is(false));
	}

	@Test
	public void an_advantage_scored_win_the_match() {
		// arrange
		a_deuce_scored_become_an_advantage();

		// act
		sut.player1Score();

		// assert
		assertThat(sut.isGameOver(), is(true));
		assertThat(sut.getWinner().get(), is(Players.PLAYER_1_POS));

	}

	@Test
	public void game_go_back_to_deuce_after_losing_point_on_advantage() {
		// arrange
		assert_40_40_produce_a_deuce();

		sut.player1Score();
		sut.player2Score();

		assertThat(sut.getCurrentScore(), is("Deuce"));
		assertThat(sut.isGameOver(), is(false));

	}

}
