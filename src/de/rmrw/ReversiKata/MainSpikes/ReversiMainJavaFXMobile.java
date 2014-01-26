package de.rmrw.ReversiKata.MainSpikes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import org.eclipse.fx.ui.mobile.MobileApp;

import de.rmrw.ReversiKata.code.ReversiSpiel;
import de.rmrw.ReversiKata.views.IFSpielView;
import de.rmrw.ReversiKata.views.JavaFXSpielView;
import de.rmrw.ReversiKata.views.JavaFXSpielfeldFeldProperties;

public class ReversiMainJavaFXMobile extends MobileApp {
	
	private static final Color ANGEDEUTETEFARBESPIELER2 = Color.LIGHTCORAL;
	private static final Color ANGEDEUTETEFARBESPIELER1 = Color.LIGHTBLUE;
	private static final Color FARBESPIELER2 = Color.RED;
	private static final Color FARBESPIELER1 = Color.BLUE;
	private static final Color GRUNDFARBE = Color.BEIGE;

	public Region createUI() {
		ReversiSpiel model = new ReversiSpiel(8, new ArrayList<IFSpielView>());
		model.initSpiel();
		model.registriereSpieler("Mathias");
		model.registriereSpieler("Robert");
		
		JavaFXSpielfeldFeldProperties spielfeldFeldProperties = new JavaFXSpielfeldFeldProperties(
				50,            // Groesse
				GRUNDFARBE,   // Grundfarbe
				FARBESPIELER1,    // Farbe Spieler1
				FARBESPIELER2,     // Farbe Spieler2
				ANGEDEUTETEFARBESPIELER1, // Angedeutete Farbe Sp1
				ANGEDEUTETEFARBESPIELER2  // Angedeutete Farbe Sp2
				);

		JavaFXSpielView sV = new JavaFXSpielView(
				model,
				spielfeldFeldProperties
				);
		sV.init();
		sV.setPadding(new Insets(300,100,40,40));
		sV.update();
		return sV;
//		Scene s = new Scene(sV,500,500);
//		s.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		primaryStage.setScene(s);
//		primaryStage.show();
	}
	
	public List<String> getInitialStylesheets() {
		return Collections.singletonList(getClass().getResource("application.css").toExternalForm());
	}

}
