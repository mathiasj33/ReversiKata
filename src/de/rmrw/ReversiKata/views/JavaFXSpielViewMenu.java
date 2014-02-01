package de.rmrw.ReversiKata.views;

import de.rmrw.ReversiKata.code.IFSpielModel;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class JavaFXSpielViewMenu extends MenuBar {

	private Menu menuDatei=null;
	private Menu menuSpieler=null;
	private MenuItem itemDateiNeu=null;
	private MenuItem itemDateiOeffnen=null;
	private MenuItem itemDateiSpeichern=null;
	private MenuItem itemSpielerUmbenennen=null;
	
	private IFSpielModel model;
	
	public JavaFXSpielViewMenu(IFSpielModel model_)
	{
		model = model_;
		menuDatei = new Menu("Datei");
		menuSpieler = new Menu("Spieler");
		itemDateiNeu = new MenuItem("Neu");
		itemDateiOeffnen = new MenuItem("Öffnen...");
		itemDateiSpeichern = new MenuItem("Speichern...");
		itemSpielerUmbenennen = new MenuItem("Umbenennen...");
		this.getMenus().addAll(menuDatei,menuSpieler);
		menuDatei.getItems().addAll(itemDateiNeu, itemDateiOeffnen, itemDateiSpeichern);
		menuSpieler.getItems().add(itemSpielerUmbenennen);
	}
}
