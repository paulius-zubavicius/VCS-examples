package com.vcs.colors.game.com.vsc.colors.game.pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.vcs.colors.game.pattern.line.ColoredItem;
import com.vcs.colors.game.pattern.line.ColoredLine;
import org.junit.Before;
import org.junit.Test;

import com.vcs.colors.game.pattern.KasAntraTokiaPati;

import java.util.ArrayList;
import java.util.List;


public class KasAntraTokiaPatiTest {
	private KasAntraTokiaPati patt;

	@Before
	public void init() {
		patt = new KasAntraTokiaPati();



	}

	@Test
	public void kasAntraTokiaPatiSuccess() {

		/*ArrayList<ColoredLine> vienas = new ArrayList<ColoredLine>();
		vienas.add(ColoredItem.RED);
*/
		assertTrue(patt.isPatternMathed(patt.getValidExample().get(0)));
		assertTrue(patt.isPatternMathed(patt.getValidExample().get(1)));
		assertTrue(patt.isPatternMathed(patt.getValidExample().get(2)));
		assertTrue(patt.isPatternMathed(patt.getValidExample().get(3)));
		assertTrue(patt.isPatternMathed(patt.getValidExample().get(4)));


	}

	@Test
	public void kasAntraTokiaPatiFail() {

		assertFalse(patt.isPatternMathed(patt.getNotValidExample().get(0)));
		assertFalse(patt.isPatternMathed(patt.getNotValidExample().get(1)));
		assertFalse(patt.isPatternMathed(patt.getNotValidExample().get(2)));
		assertFalse(patt.isPatternMathed(patt.getNotValidExample().get(3)));
		assertFalse(patt.isPatternMathed(patt.getNotValidExample().get(4)));


	}

}