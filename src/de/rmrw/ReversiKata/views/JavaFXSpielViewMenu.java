package de.rmrw.ReversiKata.views;

import java.io.File;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import de.rmrw.ReversiKata.code.IFSpielRahmenModel;

public class JavaFXSpielViewMenu extends MenuBar {


	public JavaFXSpielViewMenu()
	{
		
		addMenuDatei();

		addMenuBearbeiten();

		addMenuSpieler();
	}

	private void addMenuSpieler() {
		Menu menuSpieler = new Menu("Spieler");
		MenuItem itemSpielerUmbenennen = new MenuItem("Namen ändern...");
		this.getMenus().add(menuSpieler);
		menuSpieler.getItems().add(itemSpielerUmbenennen);
		itemSpielerUmbenennen.setOnAction((ActionEvent) -> {
                    onItemSpielerUmbenennen();
                });
	}

	public void onItemSpielerUmbenennen() {
		// TODO Auto-generated method stub
		
	}

	private void addMenuBearbeiten() {
		Menu menuBearbeiten = new Menu("Bearbeiten");
		MenuItem itemBearbeitenRueckgaengig = new MenuItem("Rückgängig");
		MenuItem itemBearbeitenWiederholen = new MenuItem("Wiederholen");
		this.getMenus().add(menuBearbeiten);
		menuBearbeiten.getItems().addAll(itemBearbeitenRueckgaengig, itemBearbeitenWiederholen);

		itemBearbeitenRueckgaengig.setOnAction((ActionEvent) -> {
				onItemBearbeitenRueckgaengig();
				});

		itemBearbeitenWiederholen.setOnAction((ActionEvent) -> {
                    onItemBearbeitenWiederholen();
                });

	}

	public void onItemBearbeitenWiederholen() {
		getModel().redo();
	}

	public void onItemBearbeitenRueckgaengig() {
		getModel().undo();
	}

	private void addMenuDatei() {
		Menu menuDatei = new Menu("Datei");
		MenuItem itemDateiNeu = new MenuItem("Neu");
		MenuItem itemDateiOeffnen = new MenuItem("Öffnen...");
		MenuItem itemDateiSpeichern = new MenuItem("Speichern...");
		this.getMenus().add(menuDatei);
		menuDatei.getItems().addAll(itemDateiNeu, itemDateiOeffnen, itemDateiSpeichern);
		
		itemDateiNeu.setOnAction((ActionEvent) -> {
                    onItemDateiNeu();
                });

		itemDateiOeffnen.setOnAction((ActionEvent) -> {
                    onItemDateiOeffnen();
                });

		itemDateiSpeichern.setOnAction((ActionEvent) -> {
                    onItemDateiSpeichern();
                });
}

	public void onItemDateiNeu() {
		getModel().neuesSpiel();
		JavaFXSpielRahmenView parent = getJavaFXSpielRahmenViewParent();
		// Model und Views wieder richtig zuordnen (unschön)
		parent.getSpielView().setModel(getModel().getSpiel());
		getModel().getSpiel().addView(parent.getSpielView());
		parent.update();
	}
	
	public void onItemDateiOeffnen() {
		FileChooser fileChooser = new FileChooser();
		Stage stage = (Stage) this.getScene().getWindow();
		File file = fileChooser.showOpenDialog(stage);
		
		getModel().ladeSpiel(file.getPath());
		JavaFXSpielRahmenView parent = getJavaFXSpielRahmenViewParent();
		// Model und Views wieder richtig zuordnen (unschön)
		parent.getSpielView().setModel(getModel().getSpiel());
		getModel().getSpiel().addView(parent.getSpielView());
		parent.update();
	}

	private void onItemDateiSpeichern() {
		FileChooser fileChooser = new FileChooser();
		Stage stage = (Stage) this.getScene().getWindow();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("xml files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
		
		File file = fileChooser.showSaveDialog(stage);
		getModel().spielSpeichern(file.getPath());
		
	}
	
	
	
	
	public IFSpielRahmenModel getModel() {
		return getJavaFXSpielRahmenViewParent().getRahmenModel();
	}

	private JavaFXSpielRahmenView getJavaFXSpielRahmenViewParent(){
		return (JavaFXSpielRahmenView) this.getParent();
	}
	
	
	
}
