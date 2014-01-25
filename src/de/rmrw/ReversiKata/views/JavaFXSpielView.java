package de.rmrw.ReversiKata.views;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import de.rmrw.ReversiKata.code.IFSpielModel;

public class JavaFXSpielView extends BorderPane implements IFSpielView{
	
	private IFSpielModel model 										= null;
	
	private JavaFXSpielfeldFeldProperties spielfeldFeldProperties 	= null;
	private JavaFXSpielfeldView spielfeldView;

	private JavaFXSpielerView spielerView;

	
	public JavaFXSpielView(IFSpielModel model_, JavaFXSpielfeldFeldProperties spielfeldFeldProperties_) {
		setModel(model_);
		setPadding(new Insets(40, 40, 40, 40));
		spielfeldFeldProperties = spielfeldFeldProperties_;
	}
	
	public void init() {
		spielfeldView = createJavaFXSpielfeldView();
		spielfeldView.init();
		subClassSetCenter(spielfeldView);
		spielerView = createJavaFXSpielerView();
		subClassSetLeft(spielerView);
		model.addView(this);
	}

	public void subClassSetCenter(JavaFXSpielfeldView sv) {
		super.setCenter(sv);
	}

	
	public void subClassSetLeft(JavaFXSpielerView sv) {
		super.setLeft(sv);
	}

	public JavaFXSpielerView createJavaFXSpielerView() {
		return new JavaFXSpielerView(10);
	}

	public JavaFXSpielfeldView createJavaFXSpielfeldView() {
		return new JavaFXSpielfeldView(model, spielfeldFeldProperties);
	}
	
	@Override
	public void update() {
		spielfeldView.update();
		spielerView.update();
	}

	public IFSpielModel getModel() {
		return model;
	}

	public void setModel(IFSpielModel model) {
		this.model = model;
	}

	public JavaFXSpielfeldView getJavaFXSpielfeldView() {
		return spielfeldView;
	}

	public void setJavaFXSpielfeldView(JavaFXSpielfeldView spielfeldView) {
		this.spielfeldView = spielfeldView;
	}

	public JavaFXSpielerView getJavaFXSpielerView() {
		return spielerView;
	}

	public void setJavaFXSpielerView(JavaFXSpielerView spielerView) {
		this.spielerView = spielerView;
	}

}
