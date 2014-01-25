package de.rmrw.ReversiKata.CodeTest;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import de.rmrw.ReversiKata.code.DirectionIterator;
import de.rmrw.ReversiKata.code.Pos;
import de.rmrw.ReversiKata.code.Spielfeld;

public class DirectionIteratorTest {

	@Test
	public final void testDirectionIteratorLinksOben() {
		Set<Pos> resultSet = new HashSet<Pos>();
		Spielfeld sp = SpielfeldFactory.createDirectionsIteratorSpielfeld();
		//o o o
		//o o o
		//o o o
		DirectionIterator dit = new DirectionIterator(new Pos(0,0), sp);
		while(dit.hasNext()) {
			resultSet.add(dit.next());
		}
		Assert.assertTrue(resultSet.contains(new Pos(0,1)));
		Assert.assertTrue(resultSet.contains(new Pos(1,0)));
		Assert.assertTrue(resultSet.contains(new Pos(1,1)));
		Assert.assertEquals(3, resultSet.size());
	}
	
	@Test
	public void testAnotherExample() {
		Spielfeld sp = SpielfeldFactory.getSpielfeld4x4ForSetzeSpielstein_1HorizDrehen_2VertDrehen_0DiagDrehen();
		DirectionIterator dit = new DirectionIterator(new Pos(0,3), sp);
		Set<Pos> resultSet = new HashSet<Pos>();
		while(dit.hasNext()) {
			resultSet.add(dit.next());
		}
		Assert.assertEquals(3, resultSet.size());
	}

}
