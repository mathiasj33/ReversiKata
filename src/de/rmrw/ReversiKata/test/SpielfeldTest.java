package de.rmrw.ReversiKata.test;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import de.rmrw.ReversiKata.code.Colors;
import de.rmrw.ReversiKata.code.DirectionIterator;
import de.rmrw.ReversiKata.code.LineIterator;
import de.rmrw.ReversiKata.code.Pos;
import de.rmrw.ReversiKata.code.Spielfeld;
import de.rmrw.ReversiKata.code.SpielfeldIterator;

public class SpielfeldTest {
	
	
	
	@Test 
	public final void testGetColor() {
		Spielfeld spielfeld = SpielfeldFactory.getSpielfeld2x2ForTestGetColorAndForToString();
		// b  o
		// o  w
		Assert.assertEquals(Colors.BLACK, spielfeld.getColor(new Pos(0,0)));
		Assert.assertEquals(Colors.VOID, spielfeld.getColor(new Pos(0,1)));
		Assert.assertEquals(Colors.WHITE, spielfeld.getColor(new Pos(1,1)));
	}
	

	@Test
	public final void testToString() {
		// mock Iterator
		SpielfeldIterator mockSpielfeldIterator = mock(SpielfeldIterator.class);
		when(mockSpielfeldIterator.hasNext()).thenReturn(true, true, true, true, false);
		when(mockSpielfeldIterator.next()).thenReturn( new Pos(0,0), new Pos(0,1), new Pos(1,0), new Pos(1,1));
		// mock Iterator - Ende
		// Partielles Mocking Spielfeld
		Spielfeld spySpielfeld = spy(SpielfeldFactory.getSpielfeld2x2ForTestGetColorAndForToString());
		doReturn(mockSpielfeldIterator).when(spySpielfeld).createSpielfeldIterator();
		// Partielles Mocking Spielfeld - Ende
		String s = spySpielfeld.toString();
		Assert.assertEquals("b o"+System.getProperty("line.separator")+
							"o w"+System.getProperty("line.separator"),
							s);
	}
	
	@Test
	public void testWoKannSchwarzMitMoeglichkeitAufLeeremFeld() {
		// Partielles Mocking: Methode "woKann" wird getestet, Methode "esGibtEinenWegVonPosZuFarbe" wird gemockt
		Spielfeld spySpielfeld = spy(SpielfeldFactory.getSpielfeld2x2ForWoKannSchwarz());
		//  b  w
		//  o  o
		// Eigentlich geht da gar nix, unser stub fuer esGibtEinenWegVonPosZuFarbe tut aber so, als ob
		//  b  w
		// (b) o
		
		doReturn(false).when(spySpielfeld).esGibtEinenWegVonPosZuFarbe(new Pos(0,0),Colors.BLACK);
		doReturn(false).when(spySpielfeld).esGibtEinenWegVonPosZuFarbe(new Pos(0,1),Colors.BLACK);
		doReturn(true).when(spySpielfeld).esGibtEinenWegVonPosZuFarbe(new Pos(1,0),Colors.BLACK);
		doReturn(false).when(spySpielfeld).esGibtEinenWegVonPosZuFarbe(new Pos(1,1),Colors.BLACK);
		// Mock-Ende;
		
		Set<Pos> ergebnisMenge = spySpielfeld.woKann(Colors.BLACK);
		Assert.assertEquals(1,ergebnisMenge.size());
		Assert.assertTrue(ergebnisMenge.contains(new Pos(1,0))); // Gegenprobe nicht n�tig, da nur 1 Element in der Menge
	}
	
	@Test
	public void testWoKannSchwarzMitMoeglichkeitAufBesetzemFeld() {
		// Partielles Mocking: Methode "woKann" wird getestet, Methode "esGibtEinenWegVonPosZuFarbe" wird gemockt
		Spielfeld spySpielfeld = spy(SpielfeldFactory.getSpielfeld2x2ForWoKannSchwarz());
		//  b  w
		//  o  o
		// Eigentlich geht da gar nix, unser stub fuer esGibtEinenWegVonPosZuFarbe tut aber so, als ob bei (0,1) was gehen w�rde, wenn da kein Stein l�ge
		//  b  w<-(b)
		//  o  o
		
		doReturn(true).when(spySpielfeld).esGibtEinenWegVonPosZuFarbe(new Pos(0,1),Colors.BLACK);
		doReturn(false).when(spySpielfeld).esGibtEinenWegVonPosZuFarbe(new Pos(0,0),Colors.BLACK);
		doReturn(false).when(spySpielfeld).esGibtEinenWegVonPosZuFarbe(new Pos(1,0),Colors.BLACK);
		doReturn(false).when(spySpielfeld).esGibtEinenWegVonPosZuFarbe(new Pos(1,1),Colors.BLACK);
		// Mock-Ende;
		
		Set<Pos> ergebnisMenge = spySpielfeld.woKann(Colors.BLACK);
		Assert.assertEquals(0,ergebnisMenge.size());
	}

	
	@Test
	public void testContains() {
		Spielfeld spielfeld = SpielfeldFactory.getSpielfeld2x2ForContains();
		Assert.assertTrue(spielfeld.contains(new Pos(1,1)));
		Assert.assertFalse(spielfeld.contains(new Pos(2,0)));
	}

	@Test
	public void testEsGibtEinenWegVonPosZuFarbeHorizontal() {  
		Spielfeld spySpielfeld = spy(SpielfeldFactory.getSpielfeld3x3ForEsGibtEinenWegVonPosZuFarbeHorizontal());
		//o  w  b
		//o  o  o
		//o  o  o
		mockingFuerEsGibtEinenOderKeinenHorizontal(spySpielfeld);
		Assert.assertTrue(spySpielfeld.esGibtEinenWegVonPosZuFarbe(new Pos(0,0), Colors.BLACK));
	}

	@Test
	public void testEsGibtKeinenWegVonPosZuFarbeHorizontal() {  
		Spielfeld spySpielfeld = spy(SpielfeldFactory.getSpielfeld3x3ForEsGibtKeinenWegVonPosZuFarbeHorizontal());
		//o  o  b
		//o  o  o
		//o  o  o
		mockingFuerEsGibtEinenOderKeinenHorizontal(spySpielfeld);
		Assert.assertFalse(spySpielfeld.esGibtEinenWegVonPosZuFarbe(new Pos(1,1), Colors.BLACK));
	}


	private void mockingFuerEsGibtEinenOderKeinenHorizontal(
			Spielfeld spySpielfeld) {
		// Mock Iteratoren
		DirectionIterator mockDI = mock(DirectionIterator.class);
		when(mockDI.hasNext()).thenReturn( true, true, false);
		when(mockDI.next()).thenReturn( new Pos(0,1), new Pos(1,1));
		
		LineIterator mockLIHorizontal = mock(LineIterator.class);
		when(mockLIHorizontal.hasNext()).thenReturn( true, true, true, false);
		when(mockLIHorizontal.next()).thenReturn( new Pos(0,0),new Pos(0,1),new Pos(0,2));
		
		LineIterator mockLIDiagonal = mock(LineIterator.class);
		when(mockLIDiagonal.hasNext()).thenReturn( true, true, true, false);
		when(mockLIDiagonal.next()).thenReturn( new Pos(0,0),new Pos(1,1),new Pos(2,2));
		//  Mock Iteratoren-Ende
		doReturn(mockDI).when(spySpielfeld).createDirectionIterator();
		doReturn(mockLIHorizontal).when(spySpielfeld).createLineIterator(new Pos(0,0), new Pos(0,1));
		doReturn(mockLIDiagonal).when(spySpielfeld).createLineIterator(new Pos(0,0), new Pos(1,1));
		// Partielles Mocking-Ende
	}

	@Test
	public void testEsGibtEinenWegVonPosZuFarbeVertikal() {
		Spielfeld spySpielfeld = spy(SpielfeldFactory.getSpielfeld4x4ForEsGibtEinenWegVonPosZuFarbeVertikal());
		//b  o  o  o
		//w  o  o  o
		//w  o  o  o
		//o  o  o  o
		mockingFuerEsGibtEinenOderKeinenVertikal(spySpielfeld);
		Assert.assertTrue(spySpielfeld.esGibtEinenWegVonPosZuFarbe(new Pos(3,0), Colors.BLACK));
	}

	@Test
	public void testEsGibtKeinenWegVonPosZuFarbeVertikal() {
		Spielfeld spySpielfeld = spy(SpielfeldFactory.getSpielfeld4x4ForEsGibtKeinenWegVonPosZuFarbeVertikal());
		//b  o  o  o
		//w  o  o  o
		//o  o  o  o
		//o  o  o  o
		mockingFuerEsGibtEinenOderKeinenVertikal(spySpielfeld);
		Assert.assertFalse(spySpielfeld.esGibtEinenWegVonPosZuFarbe(new Pos(3,0), Colors.BLACK));
	}


	private void mockingFuerEsGibtEinenOderKeinenVertikal(Spielfeld spySpielfeld) {
		// mocking - Iteratoren vollst�ndig-Beginn
		DirectionIterator mockDI = mock(DirectionIterator.class);
		when(mockDI.hasNext()).thenReturn( true, true, false);
		when(mockDI.next()).thenReturn( new Pos(-1,0), new Pos(0,1)); // pr�ft ausschlie�lich vertikal (nach oben) und horizontal (nach rechts)
		
		LineIterator mockLIVertikal = mock(LineIterator.class);
		when(mockLIVertikal.hasNext()).thenReturn( true, true, true, true, false);
		when(mockLIVertikal.next()).thenReturn( new Pos(3,0), new Pos(2,0), new Pos(1,0), new Pos(0,0));
		
		LineIterator mockLIHorizontal = mock(LineIterator.class);
		when(mockLIHorizontal.hasNext()).thenReturn( true, true, true, true, false);
		when(mockLIHorizontal.next()).thenReturn( new Pos(3,0), new Pos(3,1), new Pos(3,2), new Pos(3,3));
		// mocking - Iteratoren vollst�ndig-Ende
		
		// mocking - Spielfeld partiell/Teil 2-Beginn
		doReturn(mockDI).when(spySpielfeld).createDirectionIterator();
		doReturn(mockLIVertikal).when(spySpielfeld).createLineIterator(new Pos(3,0), new Pos(-1,0));
		doReturn(mockLIHorizontal).when(spySpielfeld).createLineIterator(new Pos(3,0), new Pos(0,1));
		// mocking - Spielfeld partiell/Teil 2-Ende
	}
	
	@Test
	public void testEsGibtEinenWegVonPosZuFarbeDiagonal() {
		Spielfeld spySpielfeld = spy(SpielfeldFactory.getSpielfeld4x4ForEsGibtEinenWegVonPosZuFarbeDiagonal());
		mockingFuerEsGibtEinenOderKeinenDiagonal(spySpielfeld);
		Assert.assertTrue(spySpielfeld.esGibtEinenWegVonPosZuFarbe(new Pos(0,3), Colors.BLACK));
		//Assert.assertFalse(spielfeld.esGibtEinenWegVonPosZuFarbe(new Pos(1,1), Colors.BLACK));
	}
	
	@Test
	public void testEsGibtKeinenWegVonPosZuFarbeDiagonal() {
		Spielfeld spySpielfeld = spy(SpielfeldFactory.getSpielfeld4x4ForEsGibtKeinenWegVonPosZuFarbeDiagonal());
		mockingFuerEsGibtEinenOderKeinenDiagonal(spySpielfeld);
		Assert.assertFalse(spySpielfeld.esGibtEinenWegVonPosZuFarbe(new Pos(1,1), Colors.BLACK));
	}

	
	private void mockingFuerEsGibtEinenOderKeinenDiagonal(Spielfeld spySpielfeld) {
		// mocking - Iteratoren vollst�ndig-Beginn
		DirectionIterator mockDI = mock(DirectionIterator.class);
		when(mockDI.hasNext()).thenReturn( true, false);
		when(mockDI.next()).thenReturn( new Pos(1,-1)); // pr�ft ausschlie�lich diagonal rechts oben nach links unten
		
		LineIterator mockLIDiagonal = mock(LineIterator.class);
		when(mockLIDiagonal.hasNext()).thenReturn( true, true, true, true, false);
		when(mockLIDiagonal.next()).thenReturn( new Pos(0,3), new Pos(1,2), new Pos(2,1), new Pos(3,0));
		// mocking - Iteratoren vollst�ndig-Ende
		
		// mocking - Spielfeld partiell/Teil 2-Beginn
		doReturn(mockDI).when(spySpielfeld).createDirectionIterator();
		doReturn(mockLIDiagonal).when(spySpielfeld).createLineIterator(new Pos(0,3), new Pos(1,-1));
		// mocking - Spielfeld partiell/Teil 2-Ende
	}
	
	@Test
	public void testSetzeSpielstein_1HorizDrehen_2VertDrehen_0DiagDrehen()
	{
		Spielfeld spielfeld = SpielfeldFactory.getSpielfeld4x4ForSetzeSpielstein_1HorizDrehen_2VertDrehen_0DiagDrehen();
		// o  w  b  o 
		// o  o  b  b
		// o  o  o  b
		// o  o  o  w
		spielfeld.setzeSpielstein(Colors.WHITE,new Pos(0,3));
		Assert.assertEquals(6, spielfeld.anzahl(Colors.WHITE));
		Assert.assertEquals(1, spielfeld.anzahl(Colors.BLACK));
	}
/*
	
	@Test
	public void testSetzeSpielstein_1HorizDrehen_2VertDrehen_0DiagDrehen()
	{
		Spielfeld spySpielfeld = spy(SpielfeldFactory.getSpielfeld4x4ForSetzeSpielstein_1HorizDrehen_2VertDrehen_0DiagDrehen());
		// o  w  b  o 
		// o  o  b  b
		// o  o  o  b
		// o  o  o  w
		
		// mock Iteratoren
		DirectionIterator mockDI = mock(DirectionIterator.class);
		when(mockDI.hasNext()).thenReturn( true, true, true, false);
		when(mockDI.next()).thenReturn( new Pos(0,-1), new Pos(1,-1),new Pos(1,0)); // horiz. links, diagonal ro->lu, vert. unten
		
		LineIterator mockLIHorizontal = mock(LineIterator.class);
		when(mockLIHorizontal.hasNext()).thenReturn( true, true, true, true, false);
		when(mockLIHorizontal.next()).thenReturn( new Pos(0,3), new Pos(0,2), new Pos(0,1), new Pos(0,0));
		
		LineIterator mockLIDiagonal = mock(LineIterator.class);
		when(mockLIDiagonal.hasNext()).thenReturn( true, true, true, true, false);
		when(mockLIDiagonal.next()).thenReturn( new Pos(0,3), new Pos(1,2), new Pos(2,1), new Pos(3,0));
		
		LineIterator mockLIVertikal = mock(LineIterator.class);
		when(mockLIVertikal.hasNext()).thenReturn( true, true, true, true, false);
		when(mockLIVertikal.next()).thenReturn( new Pos(0,3), new Pos(1,3), new Pos(2,3), new Pos(3,3));
		// mocking - Iteratoren vollst�ndig-Ende
		
		// stub Iteratoren-Erzeugung
		doReturn(mockDI).when(spySpielfeld).createDirectionIterator();
		doReturn(mockLIHorizontal).when(spySpielfeld).createLineIterator(new Pos(0,3), new Pos(0,-1));
		doReturn(mockLIDiagonal).when(spySpielfeld).createLineIterator(new Pos(0,3), new Pos(1,-1));
		doReturn(mockLIVertikal).when(spySpielfeld).createLineIterator(new Pos(0,3), new Pos(1,0));
		// Ende stub Iteratoren-Erzeugung
		
		spySpielfeld.setzeSpielstein(Colors.WHITE,new Pos(0,3));
		Assert.assertEquals(6, spySpielfeld.anzahl(Colors.WHITE));
		Assert.assertEquals(1, spySpielfeld.anzahl(Colors.BLACK));
	}
*/
	@Test
	public void testSetzeSpielstein_Nur1HorizDrehen()
	{
		Spielfeld spySpielfeld = spy(SpielfeldFactory.getSpielfeld3x3ForSetzeSpielstein_Nur1HorizDrehen());
//		 o b w
//		 o o o
//		 w o o
		// mock Iteratoren
		DirectionIterator mockDI = mock(DirectionIterator.class);
		when(mockDI.hasNext()).thenReturn( true, true, true, false);
		when(mockDI.next()).thenReturn( new Pos(0,1), new Pos(1,1),new Pos(1,0)); // horiz. rechts, diagonal lo->ru, vert. unten
		
		LineIterator mockLIHorizontal = mock(LineIterator.class);
		when(mockLIHorizontal.hasNext()).thenReturn( true, true, true, false);
		when(mockLIHorizontal.next()).thenReturn( new Pos(0,0), new Pos(0,1), new Pos(0,2));
		
		LineIterator mockLIDiagonal = mock(LineIterator.class);
		when(mockLIDiagonal.hasNext()).thenReturn( true, true, true, false);
		when(mockLIDiagonal.next()).thenReturn( new Pos(0,0), new Pos(1,1), new Pos(2,2));
		
		LineIterator mockLIVertikal = mock(LineIterator.class);
		when(mockLIVertikal.hasNext()).thenReturn( true, true, true, false);
		when(mockLIVertikal.next()).thenReturn( new Pos(0,0), new Pos(1,0), new Pos(2,0));
		// mocking - Iteratoren vollst�ndig-Ende

		// stub Iteratoren-Erzeugung
		doReturn(mockDI).when(spySpielfeld).createDirectionIterator();
		doReturn(mockLIHorizontal).when(spySpielfeld).createLineIterator(new Pos(0,3), new Pos(0,-1));
		doReturn(mockLIDiagonal).when(spySpielfeld).createLineIterator(new Pos(0,3), new Pos(1,-1));
		doReturn(mockLIVertikal).when(spySpielfeld).createLineIterator(new Pos(0,3), new Pos(1,0));
		// Ende stub Iteratoren-Erzeugung
		
		spySpielfeld.setzeSpielstein(Colors.WHITE,new Pos(0,0));
		
		Assert.assertEquals(Colors.VOID, spySpielfeld.getColor(new Pos(1,0)));
	}

	
	
	@Test
	public void testSetzeSpielstein_3HorizDrehen()
	{
		Spielfeld spielfeld = SpielfeldFactory.getSpielfeld5x5ForSetzeSpielstein_3HorizDrehen();
//		o w w w b
//		o o o o o
//		o o o o o
//		o o o o o
//		o o o o o
		System.out.println("Vor setzeSpielstein:");
		System.out.println(spielfeld);
		spielfeld.setzeSpielstein(Colors.BLACK, new Pos(0,0));
		System.out.println("Nach setzeSpielstein:");
		System.out.println(spielfeld);
		Assert.assertEquals(5, spielfeld.anzahl(Colors.BLACK));
		Assert.assertEquals(0, spielfeld.anzahl(Colors.WHITE));
	}

	
}
