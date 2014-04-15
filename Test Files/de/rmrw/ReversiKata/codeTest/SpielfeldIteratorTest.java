package de.rmrw.ReversiKata.codeTest;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.rmrw.ReversiKata.code.Pos;
import de.rmrw.ReversiKata.code.Spielfeld;
import de.rmrw.ReversiKata.code.SpielfeldIterator;

public class SpielfeldIteratorTest {
	
	private Spielfeld sf;
	
	@Before
	public void setUp() {
		sf = new Spielfeld(); sf.init(4);
	}
	
	@Test
	public void testSpielfeldIterator()  {
		Set<Pos> resultSet = new HashSet<Pos>();
		SpielfeldIterator sI = new SpielfeldIterator(sf);
		while (sI.hasNext()){
			resultSet.add(sI.next());
		}
		Assert.assertTrue(resultSet.contains(new Pos(0,0)));
		Assert.assertTrue(resultSet.contains(new Pos(0,1)));
		Assert.assertTrue(resultSet.contains(new Pos(0,2)));
		Assert.assertTrue(resultSet.contains(new Pos(0,3)));
		Assert.assertTrue(resultSet.contains(new Pos(1,0)));
		Assert.assertTrue(resultSet.contains(new Pos(1,1)));
		Assert.assertTrue(resultSet.contains(new Pos(1,2)));
		Assert.assertTrue(resultSet.contains(new Pos(1,3)));
		Assert.assertTrue(resultSet.contains(new Pos(2,0)));
		Assert.assertTrue(resultSet.contains(new Pos(2,1)));
		Assert.assertTrue(resultSet.contains(new Pos(2,2)));
		Assert.assertTrue(resultSet.contains(new Pos(2,3)));
		Assert.assertTrue(resultSet.contains(new Pos(3,0)));
		Assert.assertTrue(resultSet.contains(new Pos(3,1)));
		Assert.assertTrue(resultSet.contains(new Pos(3,2)));
		Assert.assertTrue(resultSet.contains(new Pos(3,3)));
	}

}
