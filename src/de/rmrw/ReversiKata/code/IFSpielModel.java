package de.rmrw.ReversiKata.code;

import de.rmrw.ReversiKata.guiComponents.JavaFXSpielfeldFeldZustand;
import de.rmrw.ReversiKata.views.IFSpielView;

public interface IFSpielModel {
	public void besetzeFeld(int zeile, int spalte, int spielerNummer);

	public JavaFXSpielfeldFeldZustand getFeldZustand(int zeile, int spalte);
	
	public void addView(IFSpielView view);
	
	public int getSize();
}
