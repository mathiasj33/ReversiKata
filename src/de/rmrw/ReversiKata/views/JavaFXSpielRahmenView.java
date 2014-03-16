package de.rmrw.ReversiKata.views;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import de.rmrw.ReversiKata.code.IFSpielRahmenModel;

public class JavaFXSpielRahmenView extends VBox implements IFSpielRahmenView {

	private static final Color ANGEDEUTETEFARBESPIELER2 = Color.LIGHTCORAL;
	private static final Color ANGEDEUTETEFARBESPIELER1 = Color.LIGHTBLUE;
	private static final Color FARBESPIELER2 = Color.RED;
	private static final Color FARBESPIELER1 = Color.BLUE;
	private static final Color GRUNDFARBE = Color.BEIGE;

	
	private IFSpielRahmenModel rahmenModel = null;
	
	private JavaFXSpielView    spielView = null;
	private JavaFXSpielViewMenu menuView = null;

	public JavaFXSpielRahmenView(IFSpielRahmenModel model) {
		rahmenModel = model;
		model.addView(this);
	}

	public void init() {
		JavaFXSpielfeldFeldProperties spielfeldFeldProperties = new JavaFXSpielfeldFeldProperties(
				50,            // Groesse
				GRUNDFARBE,   // Grundfarbe
				FARBESPIELER1,    // Farbe Spieler1
				FARBESPIELER2,     // Farbe Spieler2
				ANGEDEUTETEFARBESPIELER1, // Angedeutete Farbe Sp1
				ANGEDEUTETEFARBESPIELER2  // Angedeutete Farbe Sp2
				);

		spielView = new JavaFXSpielView(rahmenModel.getSpiel(), spielfeldFeldProperties);
		getSpielView().init();
		menuView = new JavaFXSpielViewMenu(rahmenModel);
		this.getChildren().addAll(menuView,spielView);
	}

	@Override
	public void update() {
		getSpielView().update();
	}

	public JavaFXSpielView getSpielView() {
		return spielView ;
	}

	public JavaFXSpielViewMenu getMenuView() {
		return menuView;
	}

}
