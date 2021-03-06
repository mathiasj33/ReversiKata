package de.rmrw.ReversiKata.viewsTest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import javafx.scene.paint.Color;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.rmrw.ReversiKata.code.IFSpielModel;
import de.rmrw.ReversiKata.code.SpielfeldFeldZustand;
import de.rmrw.ReversiKata.guiComponents.JavaFXSpielfeldFeld;
import de.rmrw.ReversiKata.guiComponents.JavaFXSpielfeldFeldZustand;
import de.rmrw.ReversiKata.views.JavaFXSpielfeldFeldProperties;
import de.rmrw.ReversiKata.views.JavaFXSpielfeldFeldView;
import de.rmrw.ReversiKata.views.JavaFXSpielfeldView;


public class JavaFXSpielfeldFeldViewTest {
	
	private static final Color ANGEDEUTETEFARBESPIELER2 = Color.LIGHTCORAL;
	private static final Color ANGEDEUTETEFARBESPIELER1 = Color.LIGHTBLUE;
	private static final Color FARBESPIELER2 = Color.RED;
	private static final Color FARBESPIELER1 = Color.BLUE;
	private static final Color GRUNDFARBE = Color.BEIGE;
	private IFSpielModel 				mockModel = null;
	private JavaFXSpielfeldFeldView 	spyFeld = null;
	
	private JavaFXSpielfeldFeldProperties 	spielfeldFeldProperties = null;
	private JavaFXSpielfeldFeld 			mockFeldIntern = null;
	private JavaFXSpielfeldView 			mockSpielfeldView = null;
	
	@Before
	public void setUp() throws Exception {
		mockModel = mock(IFSpielModel.class);
		mockSpielfeldView  = mock(JavaFXSpielfeldView.class);
		when(mockSpielfeldView.getModel()).thenReturn(mockModel);
		
		spielfeldFeldProperties = new JavaFXSpielfeldFeldProperties(50,            // Groesse
																	GRUNDFARBE,   // Grundfarbe
																	FARBESPIELER1,    // Farbe Spieler1
																	FARBESPIELER2,     // Farbe Spieler2
																	ANGEDEUTETEFARBESPIELER1, // Angedeutete Farbe Sp1
																	ANGEDEUTETEFARBESPIELER2  // Angedeutete Farbe Sp2
																	);
		JavaFXSpielfeldFeldView temp = new JavaFXSpielfeldFeldView(
				1,             // Zeile
				0,             // Spalte
				spielfeldFeldProperties
				);
		spyFeld = spy(temp);
		mockFeldIntern = mock(JavaFXSpielfeldFeld.class);
		doReturn(mockFeldIntern).when(spyFeld).createJavaFXSpielfeldFeld();
		doNothing().when(spyFeld).addSpielfeldFeldToChildren();
		doReturn(mockSpielfeldView).when(spyFeld).getJavaFXSpielfeldViewParent();
		spyFeld.init();
	}

	@Test
	public final void testKonstruktorUndInit() {
		Mockito.when(mockFeldIntern.getCircleColor()).thenReturn(Color.GOLD);
		Assert.assertEquals(Color.GOLD, spyFeld.getActiveColor());
	}
	
	@Test
	public final void testUpdate(){

		when(mockModel.getFeldZustand(1, 0)).thenReturn(SpielfeldFeldZustand.LEER_UND_BESETZBAR1);
		spyFeld.update();
		verify(mockFeldIntern).setZustand(JavaFXSpielfeldFeldZustand.LEER_UND_BESETZBAR1);
	}
	
	@Test
	public final void testComponentChanges(){
		JavaFXSpielfeldFeldView spyFeld2 = spy(new JavaFXSpielfeldFeldView(/*mockModel,     // Modell zum View*/
				2,             // Zeile
				0,             // Spalte
				spielfeldFeldProperties
				));
		JavaFXSpielfeldFeld feldIntern2 = new JavaFXSpielfeldFeld(spielfeldFeldProperties);
		Mockito.doReturn(feldIntern2).when(spyFeld2).createJavaFXSpielfeldFeld();
		doNothing().when(spyFeld2).addSpielfeldFeldToChildren();
		doReturn(mockSpielfeldView).when(spyFeld2).getJavaFXSpielfeldViewParent();
		spyFeld2.init();
		
		when(mockModel.getFeldZustand(2, 0)).thenReturn(SpielfeldFeldZustand.LEER_UND_BESETZBAR2);
		
		spyFeld2.update();
		feldIntern2.onMousePressed();
		verify(mockModel).setzeSpielstein(2, 2, 0);
	}

	@Test
	public final void testGetParent() {
		boolean b = (spyFeld.getJavaFXSpielfeldViewParent() instanceof JavaFXSpielfeldView);
		Assert.assertTrue(b);
		
	}

}
