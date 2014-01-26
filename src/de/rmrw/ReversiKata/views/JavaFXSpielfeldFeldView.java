package de.rmrw.ReversiKata.views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import de.rmrw.ReversiKata.code.IFSpielModel;
import de.rmrw.ReversiKata.code.SpielfeldFeldZustand;
import de.rmrw.ReversiKata.guiComponents.JavaFXSpielfeldFeld;
import de.rmrw.ReversiKata.guiComponents.JavaFXSpielfeldFeldZustand;


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
		this.spielfeldFeld.addListener(new ChangeListener<JavaFXSpielfeldFeldZustand>() {
			 
			@Override
			public void changed(
					ObservableValue<? extends JavaFXSpielfeldFeldZustand> observable,
					JavaFXSpielfeldFeldZustand oldValue, JavaFXSpielfeldFeldZustand newValue) {
				if (oldValue.equals(JavaFXSpielfeldFeldZustand.LEER_UND_BESETZBAR1) && newValue.equals(JavaFXSpielfeldFeldZustand.BESETZT1))
					model.setzeSpielstein(1, zeile, spalte);  
				if (oldValue.equals(JavaFXSpielfeldFeldZustand.LEER_UND_BESETZBAR2) && newValue.equals(JavaFXSpielfeldFeldZustand.BESETZT2))
					model.setzeSpielstein(2, zeile, spalte);  
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
		JavaFXSpielfeldFeldZustand zustand = uebersetzeZustand(model.getFeldZustand(zeile, spalte));
		spielfeldFeld.setZustand(zustand);
	}

	private JavaFXSpielfeldFeldZustand uebersetzeZustand(
			SpielfeldFeldZustand feldZustand) {
		if (feldZustand.equals(SpielfeldFeldZustand.BESETZT1))
			return JavaFXSpielfeldFeldZustand.BESETZT1;
		if (feldZustand.equals(SpielfeldFeldZustand.BESETZT2))
			return JavaFXSpielfeldFeldZustand.BESETZT2;
		if (feldZustand.equals(SpielfeldFeldZustand.LEER_UND_BESETZBAR1))
			return JavaFXSpielfeldFeldZustand.LEER_UND_BESETZBAR1;
		if (feldZustand.equals(SpielfeldFeldZustand.LEER_UND_BESETZBAR2))
			return JavaFXSpielfeldFeldZustand.LEER_UND_BESETZBAR2;
		return JavaFXSpielfeldFeldZustand.LEER_UND_NICHT_BESETZBAR;
	}

	
}
