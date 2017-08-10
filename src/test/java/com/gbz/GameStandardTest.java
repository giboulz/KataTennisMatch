package com.gbz;

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

import org.junit.Before;

import com.gbz.tennismatch.sets.game.Game;
import com.gbz.tennismatch.sets.game.standard.StandardGame;

public class GameStandardTest {

	Game sut;

	@Before
	public void setUp() {
		sut = new StandardGame();
	}

	@Test
	public void after_player1_score_the_score_should_be_15_0() {
		// act
		sut.player1Score();

		// assert
		assertThat(sut.getCurrentScore(), is("15 - 0"));

	}
}
