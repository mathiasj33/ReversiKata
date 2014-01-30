package de.rmrw.ReversiKata.code;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

import de.rmrw.ReversiKata.views.IFSpielView;

public class ReversiSpiel implements IFSpielModel {
	
	private ArrayList<IFSpielView> views;
	private int size;
	private Spielfeld spielfeld;
	private Hashtable<Integer,Spieler> spieler = null;
	
	public ReversiSpiel(int size_, ArrayList<IFSpielView> views_) {
		views = views_;
		spieler = new Hashtable<Integer,Spieler>();
		setSize(size_);
		spielfeld = new Spielfeld(getSize());
	}

	public void initSpiel() {
		getSpielfeld().setForInit(Colors.WHITE, new Pos(getSize()/2-1,getSize()/2-1));
		getSpielfeld().setForInit(Colors.BLACK, new Pos(getSize()/2,getSize()/2-1));
		getSpielfeld().setForInit(Colors.BLACK, new Pos(getSize()/2-1,getSize()/2));
		getSpielfeld().setForInit(Colors.WHITE, new Pos(getSize()/2,getSize()/2));
		updateAllViews();
	}

	private void updateAllViews() {
		for(IFSpielView v : views) {
			v.update();
		}	
	}
	
	public Spieler registriereSpieler(String name) {
		if (spieler.size()>2) return null;
		Spieler neuerSpieler = new Spieler(	name, 
											(spieler.size()==0) ? Colors.WHITE : Colors.BLACK, 
											(spieler.size()==0) ? true         : false
										);
		spieler.put(spieler.size()+1, neuerSpieler);
		updateAllViews();
		return neuerSpieler;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public Spielfeld getSpielfeld() {
		return spielfeld;
	}
	
	public Set<Pos> woKann(Spieler s) {
		return getSpielfeld().woKann(s.getColor());
	}
	
	public void setzeSpielstein(Spieler s, Pos p) {
		getSpielfeld().setzeSpielstein(s.getColor(), p);
		if (getSpielfeld().woKann(getGegner(s).getColor()).size()>0){
			getGegner(s).setAmZug(true);
			s.setAmZug(false);
		}
		updateAllViews();
	}
	
	public Spieler spielerAmZug() {
		Enumeration<Spieler> spielerEnum = spieler.elements();
		while (spielerEnum.hasMoreElements()){
			Spieler s = spielerEnum.nextElement();
			if (s.isAmZug())
				return s;
		}
		return null;
	}

	@Override
	public void setzeSpielstein(int spielerNummer, int zeile, int spalte) {
		setzeSpielstein( spieler.get(spielerNummer),new Pos(zeile,spalte));
	}

	@Override
	public SpielfeldFeldZustand getFeldZustand(int zeile, int spalte) {
		Pos pos = new Pos(zeile,spalte);
		Colors colorFeld  = getSpielfeld().getColor(pos);
		
		for (Colors playerColor : Colors.PLAYERCOLORS) {
			if (colorFeld.equals(playerColor))
				if (getSpielerNummerFromColor(playerColor)==1)
					return SpielfeldFeldZustand.BESETZT1;
				else
					return SpielfeldFeldZustand.BESETZT2;
		}
		
		// Ab hier ist klar, dass das Feld leer ist
		Colors colorSpielerAmZug = spielerAmZug().getColor();
		if (getSpielfeld().esGibtEinenWegVonPosZuFarbe(pos, colorSpielerAmZug))
			if (getSpielerNummerFromColor(colorSpielerAmZug)==1)
				return SpielfeldFeldZustand.LEER_UND_BESETZBAR1;
			else
				return SpielfeldFeldZustand.LEER_UND_BESETZBAR2;
			
		// Ab hier ist klar, dass das Feld leer und nicht besetzbar ist 
		return SpielfeldFeldZustand.LEER_UND_NICHT_BESETZBAR;
	}
	
	private int getSpielerNummerFromColor(Colors color)
	{
		if (spieler.get(1).getColor().equals(color))
			return 1;
		if (spieler.get(2).getColor().equals(color))
			return 2;
		return -1;
	}

	public Spieler getGegner(Spieler spielerX) {
		if (spielerX.equals(spieler.get(1))) return spieler.get(2);
		if (spielerX.equals(spieler.get(2))) return spieler.get(1);
		return null;
	}


	@Override
	public void addView(IFSpielView view) {
		views.add(view);
	}

	@Override
	public Spieler getSpieler(int i) {
		return spieler.get(i);
	}
	
	@Override
	public int getSteineAufFeld(Spieler s) {
		return getSpielfeld().anzahl(s.getColor());
	}

}
