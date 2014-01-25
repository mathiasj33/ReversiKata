package de.rmrw.ReversiKata.CodeTest;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.rmrw.ReversiKata.code.LineIterator;
import de.rmrw.ReversiKata.code.Pos;
import de.rmrw.ReversiKata.code.Spielfeld;

public class LineIteratorTest {
	
	private Spielfeld sf;
	
	
	@Before
	public void setUp() {
		sf = new Spielfeld(4);
	}
	
	// Spalte:       0  1  2  3
	//
	// Zeile 0:      o  o  o  o
	// Zeile 1:      o  o  o  o
	// Zeile 2:      o  o  o  o
	// Zeile 3:      o  o  o  o
	//

	
	@Test
	public void testLineIteratorNachUnten() {
		Set<Pos> result = new HashSet<Pos>();
		LineIterator lI = new LineIterator(sf, new Pos(2,0), new Pos(1,0)); 
		while(lI.hasNext()) {
			result.add(lI.next());
		}
		Assert.assertTrue(result.contains(new Pos(2,0)));
		Assert.assertTrue(result.contains(new Pos(3,0)));
		Assert.assertFalse(result.contains(new Pos(0,1)));
	}

	@Test
	public void testLineIteratorNachObenRechts() {
		Set<Pos> result;
		LineIterator lI;
		result= new HashSet<Pos>();
		lI = new LineIterator(sf, new Pos(3,0), new Pos(-1,1));  
		while(lI.hasNext()) {
			result.add(lI.next());
		}
		Assert.assertTrue(result.contains(new Pos(2,1)));
		Assert.assertTrue(result.contains(new Pos(1,2)));
		Assert.assertTrue(result.contains(new Pos(0,3)));
		Assert.assertFalse(result.contains(new Pos(2,0)));
	}

	@Test
	public void testLineIteratorNachUntenRechts() {
		Set<Pos> result;
		LineIterator lI;
		result= new HashSet<Pos>();
		lI = new LineIterator(sf, new Pos(0,0), new Pos(1,1));  //unten rechts
		while(lI.hasNext()) {
			result.add(lI.next());
		}
		Assert.assertTrue(result.contains(new Pos(1,1)));
		Assert.assertTrue(result.contains(new Pos(2,2)));
		Assert.assertTrue(result.contains(new Pos(3,3)));
		Assert.assertFalse(result.contains(new Pos(3,0)));
	}

	@Test
	public void testLineIteratorNachObenLinks() {
		Set<Pos> result;
		LineIterator lI;
		result= new HashSet<Pos>();
		lI = new LineIterator(sf, new Pos(3,3), new Pos(-1,-1));  //oben links
		while(lI.hasNext()) {
			result.add(lI.next());
		}
		Assert.assertTrue(result.contains(new Pos(2,2)));
		Assert.assertTrue(result.contains(new Pos(1,1)));
		Assert.assertTrue(result.contains(new Pos(0,0)));
		Assert.assertFalse(result.contains(new Pos(3,0)));
	}

	@Test
	public void testLineIteratorNachUntenLinks() {
		Set<Pos> result;
		LineIterator lI;
		result= new HashSet<Pos>();
		
		lI = new LineIterator(sf, new Pos(1,2), new Pos(1,-1)); 
		while(lI.hasNext()) {
			result.add(lI.next());
		}
		Assert.assertTrue(result.contains(new Pos(1,2)));
		Assert.assertTrue(result.contains(new Pos(2,1)));
		Assert.assertTrue(result.contains(new Pos(3,0)));
		Assert.assertFalse(result.contains(new Pos(0,0)));
	}

	@Test
	public void testLineIteratorNachRechts() {
		Set<Pos> result;
		LineIterator lI;
		result= new HashSet<Pos>();
		lI = new LineIterator(sf, new Pos(0,0), new Pos(0,1));  
		while(lI.hasNext()) {
			result.add(lI.next());
		}
		Assert.assertTrue(result.contains(new Pos(0,0)));
		Assert.assertTrue(result.contains(new Pos(0,1)));
		Assert.assertTrue(result.contains(new Pos(0,2)));
		Assert.assertFalse(result.contains(new Pos(1,0)));
	}

	@Test
	public void testLineIteratorNachLinks() {
		Set<Pos> result;
		LineIterator lI;
		result= new HashSet<Pos>();
		lI = new LineIterator(sf, new Pos(0,3), new Pos(0,-1));  
		while(lI.hasNext()) {
			result.add(lI.next());
		}
		Assert.assertTrue(result.contains(new Pos(0,2)));
		Assert.assertTrue(result.contains(new Pos(0,1)));
		Assert.assertTrue(result.contains(new Pos(0,0)));
		Assert.assertFalse(result.contains(new Pos(1,0)));
	}

	@Test
	public void testLineIteratorNachOben() {
		Set<Pos> result;
		LineIterator lI;
		result= new HashSet<Pos>();
		lI = new LineIterator(sf, new Pos(3,1), new Pos(-1,0));  
		while(lI.hasNext()) {
			result.add(lI.next());
		}
		Assert.assertTrue(result.contains(new Pos(2,1)));
		Assert.assertTrue(result.contains(new Pos(1,1)));
		Assert.assertTrue(result.contains(new Pos(0,1)));
		Assert.assertFalse(result.contains(new Pos(0,0)));
	}
}
