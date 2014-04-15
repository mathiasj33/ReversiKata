package de.rmrw.ReversiKata.viewsTest;

import javafx.scene.layout.VBox;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.rmrw.ReversiKata.code.IFSpielRahmenModel;
import de.rmrw.ReversiKata.views.JavaFXSpielRahmenView;
import de.rmrw.ReversiKata.views.JavaFXSpielView;
import de.rmrw.ReversiKata.views.JavaFXSpielViewMenu;

public class JavaFXSpielRahmenViewTest {

	private IFSpielRahmenModel     rahmenModel;
	private JavaFXSpielView        spielView;
	private JavaFXSpielRahmenView  rahmenView;
	
	@Before
	public void setUp() throws Exception {
		rahmenModel = Mockito.mock(IFSpielRahmenModel.class);
		spielView = Mockito.mock(JavaFXSpielView.class);
		rahmenView = Mockito.spy(new JavaFXSpielRahmenView(rahmenModel)); // <-- der wird getestet
		Mockito.doReturn(spielView).when(rahmenView).getSpielView();
		Mockito.doNothing().when(spielView).init();
	}
	
	@Test
	public final void testConstructor() {
		rahmenView.init();
		Mockito.verify(spielView).init();
		Assert.assertTrue(rahmenView instanceof VBox);
		Assert.assertTrue(rahmenView.getMenuView() instanceof JavaFXSpielViewMenu);
		Assert.assertEquals(2, rahmenView.getChildren().size());
	}

	
	@Test
	public final void testUpdate() {
		rahmenView.update();
		Mockito.verify(spielView).update();
	}

}
