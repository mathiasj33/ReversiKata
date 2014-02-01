package de.rmrw.ReversiKata.views;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import de.rmrw.ReversiKata.code.IFSpielModel;

public class JavaFXSpielerView extends VBox {
	
	private IFSpielModel model;
	private Label label1;
	private Label label2;
	private final static String STEINE_AUF_DEM_SPIELFELD = " Stein(e) auf dem Spielfeld";
	
	public JavaFXSpielerView(IFSpielModel model_, double spacing) {
		super(spacing);
		setModel(model_);
	}
	
	public void init() {
		label1 = new Label();
		label2 = new Label();
		label1.setTextFill(Color.WHITE);
		label2.setTextFill(Color.WHITE);
		getChildren().addAll(getLabel1(), getLabel2());
		update();
	}

	public void update() {
		label1.setText(model.getSpielerName(1) + ": " + model.getSteineAufFeld(1) + STEINE_AUF_DEM_SPIELFELD);
		label2.setText(model.getSpielerName(2) + ": " + model.getSteineAufFeld(2) + STEINE_AUF_DEM_SPIELFELD);
	}

	public IFSpielModel getModel() {
		return model;
	}

	public void setModel(IFSpielModel model) {
		this.model = model;
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
