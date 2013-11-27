package de.rmrw.ReversiKata.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReversiMain {

	static final int spielfeldgroesse=8;
	private Spielfeld spielfeld;
	public ReversiMain() {
		spielfeld = new Spielfeld(spielfeldgroesse);
		spielfeld.setForInit(Colors.WHITE, new Pos(3,3));
		spielfeld.setForInit(Colors.WHITE, new Pos(4,4));
		spielfeld.setForInit(Colors.BLACK, new Pos(3,4));
		spielfeld.setForInit(Colors.BLACK, new Pos(4,3));
	}
	
	
	
	Spielfeld getSpielfeld() {
		return spielfeld;
	}



	public static void main(String args[])
	{
		ReversiMain spiel = new ReversiMain();
		Colors dran = Colors.WHITE;
		while (true) {
			int zeile = 0;
			int spalte = 0;
			System.out.println(spiel.getSpielfeld());
			if (spiel.getSpielfeld().woKann(dran).size()==0)
				dran = dran.getOppositeColor();
			System.out.println(dran + " ist dran.");
			BufferedReader buffy = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("zeile = ");
			try {
				zeile = Integer.parseInt(buffy.readLine());
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("spalte = ");
			try {
				spalte = Integer.parseInt(buffy.readLine());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (spalte<0 || zeile<0) {
				System.out.println("Abbruch.");
				break;
			}
			if (spiel.getSpielfeld().esGibtEinenWegVonPosZuFarbe(new Pos(zeile,spalte), dran))
				spiel.getSpielfeld().setzeSpielstein(dran, new Pos(zeile,spalte));
			if (spiel.getSpielfeld().anzahl(Colors.WHITE)+spiel.getSpielfeld().anzahl(Colors.BLACK)==spielfeldgroesse*spielfeldgroesse) {
				System.out.println("Spiel beendet. Wei�="+spiel.getSpielfeld().anzahl(Colors.WHITE)+"  Schwarz="+spiel.getSpielfeld().anzahl(Colors.BLACK));
				break;
			}
			dran = dran.getOppositeColor();
		}
	}


}
