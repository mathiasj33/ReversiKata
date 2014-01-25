package de.rmrw.ReversiKata.views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import de.rmrw.ReversiKata.code.IFSpielModel;
import de.rmrw.ReversiKata.code.SpielfeldFeldZustand;


public class JavaFXSpielfeldFeldView extends Pane implements IFSpielView {
	
	private JavaFXSpielfeldFeld spielfeldFeld = null;
	private IFSpielModel model;
	private int zeile;
	private int spalte;
	private JavaFXSpielfeldFeldProperties properties;
	
	public JavaFXSpielfeldFeldView(IFSpielModel model_, int zeile_, int spalte_,
			JavaFXSpielfeldFeldProperties spielfeldFeldProperties) {
		super();
		this.properties 	= spielfeldFeldProperties;
		this.model 			= model_;
		this.zeile 			= zeile_;
		this.spalte 		= spalte_;
	}
	
	public void init(){
		this.spielfeldFeld 	= createJavaFXSpielfeldFeld();
		this.spielfeldFeld.addListener(new ChangeListener<SpielfeldFeldZustand>() {
			 
			@Override
			public void changed(
					ObservableValue<? extends SpielfeldFeldZustand> observable,
					SpielfeldFeldZustand oldValue, SpielfeldFeldZustand newValue) {
				if (oldValue.equals(SpielfeldFeldZustand.LEER_UND_BESETZBAR1) && newValue.equals(SpielfeldFeldZustand.BESETZT1))
					model.besetzeFeld(zeile, spalte, 1);
				if (oldValue.equals(SpielfeldFeldZustand.LEER_UND_BESETZBAR2) && newValue.equals(SpielfeldFeldZustand.BESETZT2))
					model.besetzeFeld(zeile, spalte, 2);
			}    
        });
		addSpielfeldFeldToChildren();
	}

	public void addSpielfeldFeldToChildren() {
		this.getChildren().add(spielfeldFeld);
	}

	public JavaFXSpielfeldFeld createJavaFXSpielfeldFeld() {
		return new JavaFXSpielfeldFeld(properties);
	}
	

	public Paint getActiveColor() {
		return spielfeldFeld.getCircleColor();
	}

	@Override
	public void update() {
		SpielfeldFeldZustand zustand = model.getFeldZustand(zeile, spalte);
		spielfeldFeld.setZustand(zustand);
	}

	
}
