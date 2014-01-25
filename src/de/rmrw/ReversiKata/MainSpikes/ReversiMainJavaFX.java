package de.rmrw.ReversiKata.MainSpikes;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.mockito.Mockito;

import de.rmrw.ReversiKata.code.IFSpielModel;
import de.rmrw.ReversiKata.code.ReversiSpiel;
import de.rmrw.ReversiKata.views.IFSpielView;
import de.rmrw.ReversiKata.views.JavaFXSpielView;
import de.rmrw.ReversiKata.views.JavaFXSpielfeldFeldProperties;

public class ReversiMainJavaFX extends Application {
	
	private static final Color ANGEDEUTETEFARBESPIELER2 = Color.LIGHTCORAL;
	private static final Color ANGEDEUTETEFARBESPIELER1 = Color.LIGHTBLUE;
	private static final Color FARBESPIELER2 = Color.RED;
	private static final Color FARBESPIELER1 = Color.BLUE;
	private static final Color GRUNDFARBE = Color.BEIGE;

	@Override
	public void start(Stage primaryStage) {
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
		Scene s = new Scene(sV,500,500);
		s.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
