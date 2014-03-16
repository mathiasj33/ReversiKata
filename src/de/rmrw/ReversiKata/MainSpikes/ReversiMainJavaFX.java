package de.rmrw.ReversiKata.MainSpikes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import de.rmrw.ReversiKata.code.ReversiSpielRahmen;
import de.rmrw.ReversiKata.views.JavaFXSpielRahmenView;

public class ReversiMainJavaFX extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		ReversiSpielRahmen spielRahmen = new ReversiSpielRahmen();
		JavaFXSpielRahmenView rahmenView = new JavaFXSpielRahmenView(spielRahmen);
		spielRahmen.addView(rahmenView);
		spielRahmen.neuesSpiel();
		rahmenView.init();
		rahmenView.update();
		
		Scene s = new Scene(rahmenView,500,584);
		s.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
