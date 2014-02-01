package de.rmrw.ReversiKata.viewsTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.rmrw.ReversiKata.code.IFSpielModel;
import de.rmrw.ReversiKata.views.JavaFXSpielerView;

public class JavaFXSpielerViewTest {
	
	private IFSpielModel mockModel;
	private JavaFXSpielerView sv;
	
	@Before
	public void setUp() {
		mockModel = Mockito.mock(IFSpielModel.class);
		sv = new JavaFXSpielerView(mockModel, 0);
		Mockito.when(mockModel.getSpielerName(1)).thenReturn("s1");
		Mockito.when(mockModel.getSpielerName(2)).thenReturn("s2");
	}
	
	@Test
	public void testInit() {
		Mockito.when(mockModel.getSteineAufFeld(1)).thenReturn(5);
		Mockito.when(mockModel.getSteineAufFeld(2)).thenReturn(7);
		sv.init();
		Assert.assertTrue(sv.getChildren().contains(sv.getLabel1()) && sv.getChildren().contains(sv.getLabel2()));
		Assert.assertEquals("s1: 5 Stein(e) auf dem Spielfeld", sv.getLabel1().getText());
		Assert.assertEquals("s2: 7 Stein(e) auf dem Spielfeld", sv.getLabel2().getText());
	}
	
	@Test
	public void testUpdate() {
		Mockito.when(mockModel.getSteineAufFeld(1)).thenReturn(5);
		Mockito.when(mockModel.getSteineAufFeld(2)).thenReturn(7);
		sv.init();
		Mockito.when(mockModel.getSteineAufFeld(1)).thenReturn(6);
		Mockito.when(mockModel.getSteineAufFeld(2)).thenReturn(9);
		sv.update();
		Assert.assertEquals("s1: 6 Stein(e) auf dem Spielfeld", sv.getLabel1().getText());
		Assert.assertEquals("s2: 9 Stein(e) auf dem Spielfeld", sv.getLabel2().getText());
	}

}
