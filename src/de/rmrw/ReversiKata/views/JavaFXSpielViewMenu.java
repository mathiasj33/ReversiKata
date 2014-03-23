package de.rmrw.ReversiKata.views;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
		itemSpielerUmbenennen.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				onItemSpielerUmbenennen();
				
			}});
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

		itemBearbeitenRueckgaengig.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				onItemBearbeitenRueckgaengig();
				
			}});

		itemBearbeitenWiederholen.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				onItemBearbeitenWiederholen();
				
			}});

	}

	public void onItemBearbeitenWiederholen() {
		// TODO Auto-generated method stub
		
	}

	public void onItemBearbeitenRueckgaengig() {
		// TODO Auto-generated method stub
		
	}

	private void addMenuDatei() {
		Menu menuDatei = new Menu("Datei");
		MenuItem itemDateiNeu = new MenuItem("Neu");
		MenuItem itemDateiOeffnen = new MenuItem("Öffnen...");
		MenuItem itemDateiSpeichern = new MenuItem("Speichern...");
		this.getMenus().add(menuDatei);
		menuDatei.getItems().addAll(itemDateiNeu, itemDateiOeffnen, itemDateiSpeichern);
		
		itemDateiNeu.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				onItemDateiNeu();
				
			}});

		itemDateiOeffnen.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				onItemDateiOeffnen();
				
			}});

		itemDateiSpeichern.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				onItemDateiSpeichern();
				
			}});
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
