package de.rmrw.ReversiKata.CodeTest;

import org.junit.Assert;
import org.junit.Test;

import de.rmrw.ReversiKata.code.Colors;

public class ColorsTest {

	@Test
	public final void testToString() {
		Assert.assertEquals("b", Colors.BLACK.toString());
		Assert.assertEquals("w", Colors.WHITE.toString());
		Assert.assertEquals("o", Colors.VOID.toString());
	}

	@Test
	public final void testGetOppositeColor() {
		Assert.assertEquals(Colors.BLACK, Colors.WHITE.getOppositeColor());
		Assert.assertEquals(Colors.WHITE, Colors.BLACK.getOppositeColor());
		Assert.assertEquals(null, Colors.VOID.getOppositeColor());
	}

}
