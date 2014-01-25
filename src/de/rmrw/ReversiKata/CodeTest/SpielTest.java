package de.rmrw.ReversiKata.CodeTest;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.rmrw.ReversiKata.code.Colors;
import de.rmrw.ReversiKata.code.Pos;
import de.rmrw.ReversiKata.code.ReversiSpiel;
import de.rmrw.ReversiKata.views.IFSpielView;
import de.rmrw.ReversiKata.code.Spieler;
import de.rmrw.ReversiKata.code.Spielfeld;

public class SpielTest {

	private ArrayList<IFSpielView> views = null;
	private IFSpielView mockView1 = null;
	private IFSpielView mockView2 = null;
	private ReversiSpiel reversi1 = null;
	private ReversiSpiel reversi2 = null;
	private Spieler spieler1 = null;
	private Spieler spieler2 = null;
	
	@Before
	public void setUp() throws Exception {
		views = new ArrayList<IFSpielView>();
		mockView1 = mock(IFSpielView.class);
		views.add(mockView1);
		mockView2 = mock(IFSpielView.class);
		views.add(mockView2);
		reversi1 = new ReversiSpiel(8,views); // besetzt die mittleren Felder und ruft schon 1mal update für alle Views auf
		reversi1.initSpiel();
	}

	private void registriereSpieler(ReversiSpiel reversi) {
		spieler1 = reversi.registriereSpieler("Mathi");  // ruft update für alle Views auf
		spieler2 = reversi.registriereSpieler("Robert"); // ruft update für alle Views auf
	}

	@Test
	public final void testInitialisierung() {
		Mockito.verify(mockView1).update();
		Mockito.verify(mockView2).update();
	}
	
	@Test
	public final void testRegistriereSpieler() {
		registriereSpieler(reversi1);
		Mockito.verify(mockView1, times(3)).update();
		Mockito.verify(mockView2, times(3)).update();
		Assert.assertEquals("Mathi", spieler1.getName());
		Assert.assertEquals(Colors.WHITE, spieler1.getColor());
		Assert.assertEquals("Robert", spieler2.getName());
		Assert.assertEquals(Colors.BLACK, spieler2.getColor());
		Assert.assertEquals(spieler1,reversi1.spielerAmZug());
	}

	
	@Test
	public final void testWoKann() {
		registriereSpieler(reversi1);
		
		Spielfeld mockSpielfeld = mock(Spielfeld.class);
		ReversiSpiel spyReversi = spy(reversi1);
		doReturn(mockSpielfeld).when(spyReversi).getSpielfeld();
		Set<Pos> setPosWoKann = new HashSet<Pos>();
		setPosWoKann.add(new Pos(2,4)); // Das ist zwar fachlich falsch, zum Abprüfen unten reicht es aber.
		when(mockSpielfeld.woKann(Colors.WHITE)).thenReturn(setPosWoKann);
		
		Set<Pos> setPosWoKannErgebnis=spyReversi.woKann(spieler1);
		Assert.assertEquals(1,setPosWoKannErgebnis.size());
	}


	@Test
	public final void testSetzeSpielstein() {
		registriereSpieler(reversi1);
		Spielfeld mockSpielfeld = mock(Spielfeld.class);
		ReversiSpiel spyReversi = spy(reversi1);
		doReturn(mockSpielfeld).when(spyReversi).getSpielfeld();
		when(mockSpielfeld.setzeSpielstein(spieler1.getColor(), new Pos(2,4))).thenReturn(true);
		Set<Pos> woKannResult = new HashSet<Pos>();
		woKannResult.add(new Pos(2,3));
		when(mockSpielfeld.woKann(spieler2.getColor())).thenReturn(woKannResult);
		
		spyReversi.setzeSpielstein(spieler1,new Pos(2,4));
		
		Mockito.verify(mockView1, times(4)).update();
		Mockito.verify(mockView2, times(4)).update();
		Assert.assertEquals(spieler2,spyReversi.spielerAmZug());
	}

	@Test
	public final void testKleineresSpielfeldGeradeGroesse() {
		reversi2 = new ReversiSpiel(4,views);
		subTestKleineresSpielfeld();
	}

	@Test
	public final void testKleineresSpielfeldUngeradeGroesse() {
		reversi2 = new ReversiSpiel(5,views);
		subTestKleineresSpielfeld();
	}

	private void subTestKleineresSpielfeld() {
		Spielfeld mockSpielfeld = mock(Spielfeld.class);
		ReversiSpiel spyReversi = spy(reversi2);
		doReturn(mockSpielfeld).when(spyReversi).getSpielfeld();

		spyReversi.initSpiel();

		Mockito.verify(mockSpielfeld).setForInit(Colors.WHITE, new Pos(1,1));
		Mockito.verify(mockSpielfeld).setForInit(Colors.WHITE, new Pos(2,2));
		Mockito.verify(mockSpielfeld).setForInit(Colors.BLACK, new Pos(2,1));
		Mockito.verify(mockSpielfeld).setForInit(Colors.BLACK, new Pos(1,2));
	}
	
	@Test
	public final void testZweimalGleicherSpielerDran() {
		reversi2 = new ReversiSpiel(4,views);
		reversi2.initSpiel();
		registriereSpieler(reversi2);
		ReversiSpiel spyReversi = spy(reversi2);
		Spielfeld mockSpielfeld = mock(Spielfeld.class);
		doReturn(mockSpielfeld).when(spyReversi).getSpielfeld();
		
		when(mockSpielfeld.setzeSpielstein(Colors.WHITE, new Pos(1,3))).thenReturn(true);
		when(mockSpielfeld.woKann(Colors.BLACK)).thenReturn(new HashSet<Pos>());
		
		spyReversi.setzeSpielstein(spieler1, new Pos(1,3));
		
		Assert.assertTrue(spieler1.isAmZug());
	}
	
	
}
