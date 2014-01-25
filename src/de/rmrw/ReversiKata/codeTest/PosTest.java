package de.rmrw.ReversiKata.codeTest;

import org.junit.Assert;
import org.junit.Test;

import de.rmrw.ReversiKata.code.Pos;

public class PosTest {
	
	@Test
	public void testAdd() {
		Pos pos1 = new Pos(1,1);
		Pos pos2 = new Pos(4,3);
		Pos pos3 = pos1.add(pos2);
		Assert.assertEquals(5,pos3.getZeile());
		Assert.assertEquals(4, pos3.getSpalte());
	}

}
