package de.rmrw.ReversiKata.guiComponents;

import de.rmrw.ReversiKata.views.JavaFXSpielfeldFeldProperties;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class JavaFXSpielfeldFeld extends Pane {
	
	private static final double GROESSEABGERUNDETEECKENINPROZENT = 10;
	private static final double PROZENTKREISVONFELD = 85;	

	private JavaFXSpielfeldFeldProperties spielfeldFeldProperties; 
	
	private Rectangle rect = null;
	private Circle kreis = null;
	private JavaFXSpielfeldFeldZustand zustand = null;
	private ChangeListener<JavaFXSpielfeldFeldZustand> changeListener;

	public JavaFXSpielfeldFeld(
			JavaFXSpielfeldFeldProperties properties) {
		super();
		
		this.setMinSize(properties.getPixelSize(), properties.getPixelSize());
		rect = new Rectangle(properties.getPixelSize(), properties.getPixelSize(), properties.getGrundFarbe());
		rect.setArcHeight(GROESSEABGERUNDETEECKENINPROZENT);
		rect.setArcWidth(GROESSEABGERUNDETEECKENINPROZENT);
		rect.setX(0);
		rect.setY(0);
		this.spielfeldFeldProperties = properties;
		this.kreis = new Circle(rect.getWidth()/2.0, 
								rect.getHeight()/2.0,
								rect.getWidth()/2.0*PROZENTKREISVONFELD/100, 
								Color.TRANSPARENT);
		this.getChildren().add(rect);
		this.getChildren().add(kreis);
		
		kreis.setOnMousePressed(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				onMousePressed();
			}

		});
		
		kreis.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				onMouseEnter();
			}

		});

		kreis.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				onMouseExited();
			}

		});
		setZustand(JavaFXSpielfeldFeldZustand.LEER_UND_NICHT_BESETZBAR);
	}

	public void addListener(ChangeListener<JavaFXSpielfeldFeldZustand> changeListener_) {
		this.changeListener = changeListener_;
	}

	public Color getAngedeuteteFarbeSpieler1() {
		return spielfeldFeldProperties.getAngedeuteteFarbeSpieler1();
	}

	public Color getAngedeuteteFarbeSpieler2() {
		return spielfeldFeldProperties.getAngedeuteteFarbeSpieler2();
	}

	public Paint getCircleColor() {
		return kreis.getFill();
	}

	public void onMousePressed() {
		if (getZustand()==JavaFXSpielfeldFeldZustand.LEER_UND_BESETZBAR1){
			setZustand(JavaFXSpielfeldFeldZustand.BESETZT1);
			changeListener.changed(null, JavaFXSpielfeldFeldZustand.LEER_UND_BESETZBAR1, JavaFXSpielfeldFeldZustand.BESETZT1);
		}
		if (getZustand()==JavaFXSpielfeldFeldZustand.LEER_UND_BESETZBAR2){
			setZustand(JavaFXSpielfeldFeldZustand.BESETZT2);
			changeListener.changed(null, JavaFXSpielfeldFeldZustand.LEER_UND_BESETZBAR2, JavaFXSpielfeldFeldZustand.BESETZT2);
		}
	}

	public void onMouseEnter() {
		if (getZustand()==JavaFXSpielfeldFeldZustand.LEER_UND_BESETZBAR1)
			kreis.setFill(getAngedeuteteFarbeSpieler1());
		if (getZustand()==JavaFXSpielfeldFeldZustand.LEER_UND_BESETZBAR2)
			kreis.setFill(getAngedeuteteFarbeSpieler2());
	}

	public void onMouseExited() {
		if (getZustand()==JavaFXSpielfeldFeldZustand.LEER_UND_BESETZBAR1 ||
			getZustand()==JavaFXSpielfeldFeldZustand.LEER_UND_BESETZBAR2)
			kreis.setFill(Color.TRANSPARENT);
	}

	
	public void setZustand(JavaFXSpielfeldFeldZustand zustand_) {
		this.zustand = zustand_;
		update();
	}

	
	public JavaFXSpielfeldFeldZustand getZustand() {
		return this.zustand;
	}
	
	private void update() {
		System.out.println("JavaFXSpielfeldFeld.update()");
		if (getZustand()==JavaFXSpielfeldFeldZustand.BESETZT1)
			kreis.setFill(spielfeldFeldProperties.getFarbeSpieler1());
		if (getZustand()==JavaFXSpielfeldFeldZustand.BESETZT2)
			kreis.setFill(spielfeldFeldProperties.getFarbeSpieler2());
		if (getZustand()==JavaFXSpielfeldFeldZustand.LEER_UND_BESETZBAR1 || 
			getZustand()==JavaFXSpielfeldFeldZustand.LEER_UND_BESETZBAR2 ||
			getZustand()==JavaFXSpielfeldFeldZustand.LEER_UND_NICHT_BESETZBAR)
			kreis.setFill(Color.TRANSPARENT);
	}


}
