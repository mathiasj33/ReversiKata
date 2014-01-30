package de.rmrw.ReversiKata.viewsTest;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.rmrw.ReversiKata.code.Colors;
import de.rmrw.ReversiKata.code.IFSpielModel;
import de.rmrw.ReversiKata.code.Spieler;
import de.rmrw.ReversiKata.views.JavaFXSpielerView;

public class JavaFXSpielerViewTest {
	
	private IFSpielModel mockModel;
	private JavaFXSpielerView sv;
	private Spieler spieler1;
	private Spieler spieler2;
	
	@Before
	public void setUp() {
		mockModel = Mockito.mock(IFSpielModel.class);
		sv = new JavaFXSpielerView(mockModel, 0);
		spieler1 = new Spieler("s1", Colors.WHITE, true);
		spieler2 = new Spieler("s2", Colors.BLACK, false);
		Mockito.when(mockModel.getSpieler(1)).thenReturn(spieler1);
		Mockito.when(mockModel.getSpieler(2)).thenReturn(spieler2);
	}
	
	@Test
	public void testInit() {
		Mockito.when(mockModel.getSteineAufFeld(spieler1)).thenReturn(5);
		Mockito.when(mockModel.getSteineAufFeld(spieler2)).thenReturn(7);
		sv.init();
		Assert.assertEquals(spieler1, sv.getSpieler(1));
		Assert.assertEquals(spieler2, sv.getSpieler(2));
		Assert.assertTrue(sv.getChildren().contains(sv.getLabel1()) && sv.getChildren().contains(sv.getLabel2()));
		Assert.assertEquals("s1: 5 Steine auf dem Spielfeld", sv.getLabel1().getText());
		Assert.assertEquals("s2: 7 Steine auf dem Spielfeld", sv.getLabel2().getText());
	}
	
	@Test
	public void testUpdate() {
		Mockito.when(mockModel.getSteineAufFeld(spieler1)).thenReturn(5);
		Mockito.when(mockModel.getSteineAufFeld(spieler2)).thenReturn(7);
		sv.init();
		Mockito.when(mockModel.getSteineAufFeld(spieler1)).thenReturn(6);
		Mockito.when(mockModel.getSteineAufFeld(spieler2)).thenReturn(9);
		sv.update();
		Assert.assertEquals("s1: 6 Steine auf dem Spielfeld", sv.getLabel1().getText());
		Assert.assertEquals("s2: 9 Steine auf dem Spielfeld", sv.getLabel2().getText());
	}

}
