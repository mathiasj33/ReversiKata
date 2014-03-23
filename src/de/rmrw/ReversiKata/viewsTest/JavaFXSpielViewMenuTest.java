package de.rmrw.ReversiKata.viewsTest;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.rmrw.ReversiKata.views.JavaFXSpielViewMenu;

public class JavaFXSpielViewMenuTest {
	
	private static final String SPIELER = "Spieler";
	private static final String BEARBEITEN = "Bearbeiten";
	private static final String DATEI = "Datei";

	private JavaFXSpielViewMenu menuBar = null;

	@Before
	public void setUp() throws Exception {
		 menuBar = new JavaFXSpielViewMenu();
	}

	@Test
	public final void testJavaFXSpielViewMenuContents() {
		Assert.assertEquals(3, menuBar.getMenus().size());
		Assert.assertNotNull(getMenuFromText(menuBar, DATEI));
		Assert.assertNotNull(getMenuFromText(menuBar, BEARBEITEN));
		Assert.assertNotNull(getMenuFromText(menuBar, SPIELER));
		
		Assert.assertEquals(3, getMenuFromText(menuBar, DATEI).getItems().size());
		Assert.assertNotNull(getMenuItemFromText(menuBar, DATEI, "Neu"));
		Assert.assertNotNull(getMenuItemFromText(menuBar, DATEI, "Öffnen..."));
		Assert.assertNotNull(getMenuItemFromText(menuBar, DATEI, "Speichern..."));
		
		Assert.assertEquals(2, getMenuFromText(menuBar, BEARBEITEN).getItems().size());
		Assert.assertNotNull(getMenuItemFromText(menuBar, BEARBEITEN, "Rückgängig"));
		Assert.assertNotNull(getMenuItemFromText(menuBar, BEARBEITEN, "Wiederholen"));
		
		Assert.assertEquals(1, getMenuFromText(menuBar, SPIELER).getItems().size());
		Assert.assertNotNull(getMenuItemFromText(menuBar, SPIELER, "Namen ändern..."));
		
	}

	private Menu getMenuFromText(JavaFXSpielViewMenu menuBar_, String s) {
		for (Menu menu : menuBar_.getMenus()) {
			if (menu.getText().equals(s))
				return menu;
		}
		return null;
	}
	
	private MenuItem getMenuItemFromText(JavaFXSpielViewMenu menuBar_, String menuText, String menuItemText)
	{
		Menu menu = getMenuFromText(menuBar_, menuText);
		if (menu==null)
			return null;
		for (MenuItem menuItem : menu.getItems()) {
			if (menuItem.getText().equals(menuItemText))
				return menuItem;
		}
		return null;
	}
	
}
