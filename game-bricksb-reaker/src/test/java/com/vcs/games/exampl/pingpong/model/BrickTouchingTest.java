package com.vcs.games.exampl.pingpong.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.vcs.bb.game.model.Brick;
import com.vcs.bb.game.model.BrickType;

public class BrickTouchingTest {

	@Test

	public void test() {

		Brick b = new Brick(BrickType.B0, 10, 10, 10, 10);

		assertFalse(b.isTouching(0, 0, 2));
		assertFalse(b.isTouching(10, 0, 2));
		assertFalse(b.isTouching(0, 10, 2));
		assertFalse(b.isTouching(21, 21, 2));

		assertTrue(b.isTouching(8, 8, 2));
		assertTrue(b.isTouching(20, 20, 2));
		assertTrue(b.isTouching(8, 20, 2));
		assertTrue(b.isTouching(20, 8, 2));

		assertTrue(b.isTouching(11, 11, 2));
	}

}
