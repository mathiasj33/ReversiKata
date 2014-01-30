package de.rmrw.ReversiKata.views;

import java.util.Hashtable;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import de.rmrw.ReversiKata.code.IFSpielModel;
import de.rmrw.ReversiKata.code.Spieler;

public class JavaFXSpielerView extends VBox {
	
	private IFSpielModel model;
	private Hashtable<Integer,Spieler> spieler;
	private Label label1;
	private Label label2;
	private final static String STEINE_AUF_DEM_SPIELFELD = " Stein(e) auf dem Spielfeld";
	
	public JavaFXSpielerView(IFSpielModel model_, double spacing) {
		super(spacing);
		setModel(model_);
		spieler = new Hashtable<Integer, Spieler>();
	}
	
	public void init() {
		spieler.put(1, model.getSpieler(1));
		spieler.put(2, model.getSpieler(2));
		label1 = new Label(spieler.get(1).getName() + ": " + model.getSteineAufFeld(spieler.get(1)) + STEINE_AUF_DEM_SPIELFELD);
		label2 = new Label(spieler.get(2).getName() + ": " + model.getSteineAufFeld(spieler.get(2))+ STEINE_AUF_DEM_SPIELFELD);
		label1.setTextFill(Color.WHITE);
		label2.setTextFill(Color.WHITE);
		getChildren().addAll(getLabel1(), getLabel2());
	}

	public void update() {
		label1.setText(spieler.get(1).getName() + ": " + model.getSteineAufFeld(spieler.get(1)) + STEINE_AUF_DEM_SPIELFELD);
		label2.setText(spieler.get(2).getName() + ": " + model.getSteineAufFeld(spieler.get(2))+ STEINE_AUF_DEM_SPIELFELD);
	}

	public IFSpielModel getModel() {
		return model;
	}

	public void setModel(IFSpielModel model) {
		this.model = model;
	}
	
	public void setSpieler(Spieler s, int i) {
		spieler.put(i, s);
	}
	
	public Spieler getSpieler(int i) {
		return spieler.get(i);
	}

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label label1) {
		this.label1 = label1;
	}

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label label2) {
		this.label2 = label2;
	}
	
}
