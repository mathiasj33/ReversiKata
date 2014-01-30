package de.rmrw.ReversiKata.viewsTest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import org.junit.Assert;
import org.junit.Test;

import de.rmrw.ReversiKata.code.IFSpielModel;
import de.rmrw.ReversiKata.views.JavaFXSpielView;
import de.rmrw.ReversiKata.views.JavaFXSpielerView;
import de.rmrw.ReversiKata.views.JavaFXSpielfeldFeldProperties;
import de.rmrw.ReversiKata.views.JavaFXSpielfeldView;

public class JavaFXSpielViewTest {

	private IFSpielModel mockModel;
	private JavaFXSpielfeldFeldProperties mockProperties;
	private JavaFXSpielfeldView mockSpielfeldView;
	private JavaFXSpielerView mockSpielerView;
	private JavaFXSpielView spielView;
	private JavaFXSpielView spySpielView;
	
	private void vorbereitungTestKonstruktorUndInit() {
		mockModel = mock(IFSpielModel.class);
		
		mockSpielfeldView = mock(JavaFXSpielfeldView.class);
		mockSpielerView = mock(JavaFXSpielerView.class);
		mockProperties = mock(JavaFXSpielfeldFeldProperties.class);

		spielView = new JavaFXSpielView( mockModel,
									 mockProperties
		   				);
		spySpielView = spy(spielView);
		
		doReturn(mockSpielfeldView).when(spySpielView).createJavaFXSpielfeldView();
		doReturn(mockSpielerView).when(spySpielView).createJavaFXSpielerView();
		doNothing().when(spySpielView).subClassSetCenter(mockSpielfeldView);
		doNothing().when(spySpielView).subClassSetBottom(mockSpielerView);
	}

	
	@Test
	public final void testKonstruktorUndInitSetzenDieRichtigenUnterfenster() {
		vorbereitungTestKonstruktorUndInit();
		
		spySpielView.init();
		
		verify(spySpielView, times(1)).subClassSetCenter(mockSpielfeldView);
		verify(mockSpielfeldView, times(1)).init();
		verify(spySpielView, times(1)).subClassSetBottom(mockSpielerView);
		Assert.assertTrue(spySpielView instanceof BorderPane);
		Assert.assertTrue(spySpielView.getJavaFXSpielfeldView() instanceof GridPane);
		Assert.assertTrue(spySpielView.getJavaFXSpielerView() instanceof VBox);
		Assert.assertEquals(mockModel, spySpielView.getModel());
		
	}

	@Test
	public final void testUpdate() {
		vorbereitungTestKonstruktorUndInit();
		
		spySpielView.init();
		spySpielView.update();
		
		verify(mockSpielfeldView,times(1)).update();
		verify(mockSpielerView,times(1)).update();
	}

}
