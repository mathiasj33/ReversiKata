package de.rmrw.ReversiKata.viewsTest;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javafx.scene.Node;

import org.junit.Before;
import org.junit.Test;

import de.rmrw.ReversiKata.code.IFSpielModel;
import de.rmrw.ReversiKata.views.JavaFXSpielfeldFeld;
import de.rmrw.ReversiKata.views.JavaFXSpielfeldFeldProperties;
import de.rmrw.ReversiKata.views.JavaFXSpielfeldFeldView;
import de.rmrw.ReversiKata.views.JavaFXSpielfeldView;

public class JavaFXSpielfeldViewTest {
	
	private IFSpielModel mockModel = null;
	private JavaFXSpielfeldFeldProperties mockSpielfeldFeldProperties = null;
	private JavaFXSpielfeldFeldView mockSpielfeldFeld = null;
	private JavaFXSpielfeldFeldView mockSpielfeldFeld2 = null;
	private JavaFXSpielfeldView spielfeldView = null;
	private JavaFXSpielfeldView spySpielfeldView = null;
	
	private static int size = 10;
	
	@Before
	public void setUp() throws Exception {
		mockModel = mock(IFSpielModel.class);
		when(mockModel.getSize()).thenReturn(size);
		mockSpielfeldFeldProperties = mock(JavaFXSpielfeldFeldProperties.class);
		
		mockSpielfeldFeld = mock(JavaFXSpielfeldFeldView.class);
		mockSpielfeldFeld2= mock(JavaFXSpielfeldFeldView.class);

		spielfeldView = new JavaFXSpielfeldView (
				mockModel,
				mockSpielfeldFeldProperties
				);
		spySpielfeldView = spy(spielfeldView);
	}

	@Test
	public final void testKonstruktorUndInit() {
		doReturn(mockSpielfeldFeld).when(spySpielfeldView).
			createJavaFXSpielfeldFeldView(eq(mockModel), anyInt(), anyInt(), eq(mockSpielfeldFeldProperties));
		doNothing().when(spySpielfeldView).addFeldToChildren(mockSpielfeldFeld);
		doNothing().when(spySpielfeldView).setGridPaneColumnAndRowIndex(anyInt(), anyInt(), eq(mockSpielfeldFeld));
		spySpielfeldView.init();
		
		verify(spySpielfeldView,times(size*size)).createJavaFXSpielfeldFeldView(eq(mockModel), anyInt(), anyInt(), eq(mockSpielfeldFeldProperties));
		verify(spySpielfeldView,times(size*size)).addFeldToChildren(mockSpielfeldFeld);
		verify(spySpielfeldView,times(size*size)).setGridPaneColumnAndRowIndex(anyInt(), anyInt(), eq(mockSpielfeldFeld));
		verify(spySpielfeldView,times(1)).createJavaFXSpielfeldFeldView(mockModel, size-1, size-1, mockSpielfeldFeldProperties);
		verify(spySpielfeldView,times(1)).createJavaFXSpielfeldFeldView(mockModel, 0, 0, mockSpielfeldFeldProperties);
	}

	@Test
	public final void testUpdate() {
		ArrayList<Node> list = new ArrayList<Node>();
		list.add(mockSpielfeldFeld);
		list.add(mockSpielfeldFeld2);
		doReturn(list).when(spySpielfeldView).subClassGetChildren();
		spySpielfeldView.update();
		verify(mockSpielfeldFeld,times(1)).update();
		verify(mockSpielfeldFeld2,times(1)).update();
	}

}
