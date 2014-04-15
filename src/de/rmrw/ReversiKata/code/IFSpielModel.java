package de.rmrw.ReversiKata.code;

import de.rmrw.ReversiKata.views.IFSpielView;

public interface IFSpielModel {
	
	public void setzeSpielstein(int spielerNummer, int zeile, int spalte);

	public SpielfeldFeldZustand getFeldZustand(int zeile, int spalte);
	
	public void addView(IFSpielView view);
	
	public int getSize();
	
	public Spieler getSpieler(int i);
	
	public int getSteineAufFeld(int spielerNummer);
	
	public String getSpielerName(int spielerNummer);
        
        void undo();
        
        void redo();
}
