package com.gbz.sets;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.gbz.tennismatch.players.Players;
import com.gbz.tennismatch.sets.Set;

public class SetTest {

	Set sut;

	@Before
	public void setUp() {
		sut = new Set();
	}

	@Test
	public void player_1_win_a_game_the_set_score_should_be_1_0() {
		// arrange
		addScoreForCurrentSet(1, 0);

		// assert
		assertThat(sut.getCurrentScore(), is("1 - 0"));
	}

	@Test
	public void on_going_set_should_not_be_over() {
		// arrange
		addScoreForCurrentSet(1, 0);

		// assert
		assertThat(sut.isSetOver(), is(false));
	}

	@Test
	public void player_2_win_a_game_the_set_score_should_be_0_1() {
		// arrange
		addScoreForCurrentSet(0, 1);

		// assert
		assertThat(sut.getCurrentScore(), is("0 - 1"));
	}

	@Test
	public void winning_a_game_should_launch_new_game_ready_to_be_scored() {
		// arrange
		addScoreForCurrentSet(1, 0);

		// act
		addScoreForCurrentSet(1, 0);

		// assert
		assertThat(sut.getCurrentScore(), is("2 - 0"));
	}

	@Test
	public void player_1_and_player_2_should_be_at_equals_score() {
		// arrange
		addScoreForCurrentSet(4, 4);

		// assert
		assertThat(sut.getCurrentScore(), is("4 - 4"));
	}

	@Test
	public void player_1_should_be_winning_lot_more_game() {
		// arrange
		addScoreForCurrentSet(5, 1);

		// assert
		assertThat(sut.getCurrentScore(), is("5 - 1"));
	}

	@Test
	public void on_5_1_player_1_score_the_set_should_be_over_and_won_by_player_1() {
		// arrange
		addScoreForCurrentSet(5, 1);

		// act
		addScoreForCurrentSet(1, 0);

		// assert
		assertThat(sut.isSetOver(), is(true));
		assertThat(sut.getWinner().get(), is(Players.PLAYER_1_POS));
	}

	@Test
	public void on_1_5_player_2_score_the_set_should_be_over_and_won_by_player_2() {
		// arrange
		addScoreForCurrentSet(1, 5);

		// act
		addScoreForCurrentSet(0, 1);

		// assert
		assertThat(sut.isSetOver(), is(true));
		assertThat(sut.getWinner().get(), is(Players.PLAYER_2_POS));
	}

	@Test
	public void if_set_is_not_over_there_is_no_winner() {
		// arrange
		addScoreForCurrentSet(5, 1);

		// assert
		assertThat(sut.isSetOver(), is(false));
		Optional<Integer> empty = Optional.empty();
		Optional<Integer> result = sut.getWinner();
		assertThat(result, is(empty));
	}

	@Test
	public void standard_game_should_be_created_for_score_under_5_5() {
		for (int i = 0; i < 5; i++) {
			// arrange
			addScoreForCurrentSet(1, 0);

			// assert
			assertThat(sut.getCurrentGameType(), is("Standard Game"));

		}

		for (int i = 0; i < 5; i++) {
			// arrange
			addScoreForCurrentSet(0, 1);

			// assert
			assertThat(sut.getCurrentGameType(), is("Standard Game"));

		}
	}

	@Test
	public void standard_game_should_be_created_when_score_is_6_5() {
		// arrange
		addScoreForCurrentSet(5, 5);

		// act
		addScoreForCurrentSet(1, 0);

		// assert
		assertThat(sut.getCurrentScore(), is("6 - 5"));
		assertThat(sut.getCurrentGameType(), is("Standard Game"));
	}

	@Test
	public void standard_game_should_be_created_when_score_is_5_6() {
		// arrange
		addScoreForCurrentSet(5, 5);

		// act
		addScoreForCurrentSet(0, 1);

		// assert
		assertThat(sut.getCurrentScore(), is("5 - 6"));
		assertThat(sut.getCurrentGameType(), is("Standard Game"));
	}

	@Test
	public void on_6_6_a_tie_break_should_be_created() {
		// arrange
		addScoreForCurrentSet(5, 5);

		// act
		addScoreForCurrentSet(1, 1);

		// assert
		assertThat(sut.getCurrentScore(), is("6 - 6"));
		assertThat(sut.getCurrentGameType(), is("Tie Break"));

	}

	@Test
	public void player_1_score_tie_break_set_should_be_won_by_player_1() {
		// arrange
		on_6_6_a_tie_break_should_be_created();

		// act
		markPointForTieBreak(7, 0);

		// assert
		assertThat(sut.getCurrentScore(), is("7 - 6"));
		assertThat(sut.isSetOver(), is(true));
		assertThat(sut.getWinner().get(), is(Players.PLAYER_1_POS));
	}

	@Test
	public void player_2_score_tie_break_set_should_be_won_by_player_2() {
		// arrange
		on_6_6_a_tie_break_should_be_created();

		// act
		markPointForTieBreak(0, 7);

		// assert
		assertThat(sut.getCurrentScore(), is("6 - 7"));
		assertThat(sut.isSetOver(), is(true));
		assertThat(sut.getWinner().get(), is(Players.PLAYER_2_POS));
	}

	private void addScoreForCurrentSet(int scorePlayer1, int scorePlayer2) {
		for (int i = 0; i < scorePlayer1; i++) {
			for (int j = 0; j < 4; j++) {
				sut.player1Score();
			}
		}

		for (int i = 0; i < scorePlayer2; i++) {
			for (int j = 0; j < 4; j++) {
				sut.player2Score();
			}
		}
	}

	private void markPointForTieBreak(int scorePlayer1, int scorePlayer2) {
		for (int i = 0; i < scorePlayer1; i++) {
			sut.player1Score();
		}

		for (int i = 0; i < scorePlayer2; i++) {
			sut.player2Score();
		}

	}
}
