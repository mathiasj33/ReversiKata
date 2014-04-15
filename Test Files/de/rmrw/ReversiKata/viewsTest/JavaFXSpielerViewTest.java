package de.rmrw.ReversiKata.viewsTest;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.rmrw.ReversiKata.code.IFSpielModel;
import de.rmrw.ReversiKata.views.JavaFXSpielView;
import de.rmrw.ReversiKata.views.JavaFXSpielerView;

public class JavaFXSpielerViewTest {
	
	private IFSpielModel mockModel;
	private JavaFXSpielView mockSpielView;
	private JavaFXSpielerView sv;
	
	@Before
	public void setUp() {
		mockModel = mock(IFSpielModel.class);
		mockSpielView = mock(JavaFXSpielView.class);
		when(mockSpielView.getModel()).thenReturn(mockModel);
		
		JavaFXSpielerView temp = new JavaFXSpielerView( 0);
		sv = spy(temp);
		doReturn(mockSpielView).when(sv).getJavaFXSpielViewParent();
		when(mockModel.getSpielerName(1)).thenReturn("s1");
		when(mockModel.getSpielerName(2)).thenReturn("s2");
	}
	
	@Test
	public void testInit() {
		when(mockModel.getSteineAufFeld(1)).thenReturn(5);
		when(mockModel.getSteineAufFeld(2)).thenReturn(7);
		sv.init();
		Assert.assertTrue(sv.getChildren().contains(sv.getLabel1()) && sv.getChildren().contains(sv.getLabel2()));
		Assert.assertEquals("s1: 5 Stein(e) auf dem Spielfeld", sv.getLabel1().getText());
		Assert.assertEquals("s2: 7 Stein(e) auf dem Spielfeld", sv.getLabel2().getText());
	}
	
	@Test
	public void testUpdate() {
		when(mockModel.getSteineAufFeld(1)).thenReturn(5);
		when(mockModel.getSteineAufFeld(2)).thenReturn(7);
		sv.init();
		when(mockModel.getSteineAufFeld(1)).thenReturn(6);
		when(mockModel.getSteineAufFeld(2)).thenReturn(9);
		sv.update();
		Assert.assertEquals("s1: 6 Stein(e) auf dem Spielfeld", sv.getLabel1().getText());
		Assert.assertEquals("s2: 9 Stein(e) auf dem Spielfeld", sv.getLabel2().getText());
	}

}
