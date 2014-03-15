package de.rmrw.ReversiKata.views;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import de.rmrw.ReversiKata.code.IFSpielModel;

public class JavaFXSpielerView extends VBox {
	
	private Label label1;
	private Label label2;
	private final static String STEINE_AUF_DEM_SPIELFELD = " Stein(e) auf dem Spielfeld";
	
	public JavaFXSpielerView(double spacing) {
		super(spacing);
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
		label1.setText(getModel().getSpielerName(1) + ": " + getModel().getSteineAufFeld(1) + STEINE_AUF_DEM_SPIELFELD);
		label2.setText(getModel().getSpielerName(2) + ": " + getModel().getSteineAufFeld(2) + STEINE_AUF_DEM_SPIELFELD);
	}

	public JavaFXSpielView getJavaFXSpielViewParent()
	{
		return (JavaFXSpielView) getParent();
	}

	public IFSpielModel getModel() {
		return getJavaFXSpielViewParent().getModel();
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
