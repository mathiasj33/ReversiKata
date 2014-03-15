package de.rmrw.ReversiKata.views;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import de.rmrw.ReversiKata.code.IFSpielModel;

public class JavaFXSpielfeldView extends GridPane {
	
	private JavaFXSpielfeldFeldProperties properties 	= null;
	
	public JavaFXSpielfeldView( JavaFXSpielfeldFeldProperties properties_) {
		super();
		this.setHgap(4);
		this.setVgap(4);
		setSpielfeldFeldProperties(properties_);
	}

	public void init() {
		for(int i = 0; i < getModel().getSize(); i++) {
			for(int j = 0; j < getModel().getSize(); j++) {
				JavaFXSpielfeldFeldView feld = 
						createJavaFXSpielfeldFeldView(getModel(), i, j, getSpielfeldFeldProperties());
				addFeldToChildren(feld);
				setGridPaneColumnAndRowIndex(i, j, feld);
			}
		}
	}
	
	public void setGridPaneColumnAndRowIndex(int i, int j,
			JavaFXSpielfeldFeldView feld) {
		GridPane.setColumnIndex(feld, j);
		GridPane.setRowIndex(feld, i);
	}

	public void addFeldToChildren(JavaFXSpielfeldFeldView feld) {
		getChildren().add(feld);
	}

	public JavaFXSpielfeldFeldView createJavaFXSpielfeldFeldView(IFSpielModel model, int zeile, int spalte,
			JavaFXSpielfeldFeldProperties properties) {
		JavaFXSpielfeldFeldView spielfeldFeldView =
			new JavaFXSpielfeldFeldView(
								zeile,             
								spalte,             
								properties
								);
		spielfeldFeldView.init();
		return spielfeldFeldView;
	}
	
	public IFSpielModel getModel() {
		return getJavaFXSpielViewParent().getModel();
	}


	public JavaFXSpielfeldFeldProperties getSpielfeldFeldProperties() {
		return properties;
	}



	private void setSpielfeldFeldProperties(JavaFXSpielfeldFeldProperties properties) {
		this.properties = properties;
	}



	public void update(){
		for (Node n : subClassGetChildren()) {
			((JavaFXSpielfeldFeldView)n).update();
		}
	}


	public ArrayList<Node> subClassGetChildren() {
		ArrayList<Node> al = new ArrayList<Node>();
		al.addAll(super.getChildren());
		return al;
	}

	public JavaFXSpielView getJavaFXSpielViewParent() {
		return (JavaFXSpielView) this.getParent();
	}

}