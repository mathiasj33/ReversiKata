package de.rmrw.ReversiKata.codeTest;

import de.rmrw.ReversiKata.code.Colors;
import de.rmrw.ReversiKata.code.Pos;
import de.rmrw.ReversiKata.code.Spielfeld;

public class SpielfeldFactory {
	
	private static Spielfeld sp;
	
	public static Spielfeld getSpielfeld2x2ForTestGetColorAndForToString() {
		// b  o
		// o  w
		sp = new Spielfeld(); sp.init(2);
		sp.setForInit(Colors.BLACK, new Pos(0,0));
		sp.setForInit(Colors.VOID, new Pos(1,0));
		sp.setForInit(Colors.WHITE, new Pos(1,1));
		return sp;
	}
	
	public static Spielfeld getSpielfeld2x2ForWoKannSchwarz() {
		// b  w
		// o  o
		sp = new Spielfeld(); sp.init(2);
		sp.setForInit(Colors.BLACK, new Pos(0,0));
		sp.setForInit(Colors.WHITE, new Pos(0,1));
		return sp;
	}
	
	public static Spielfeld getSpielfeld3x3ForEsGibtEinenWegVonPosZuFarbeHorizontal() {
		//o  w  b
		//o  o  o
		//o  o  o
		sp = getSpielfeld3x3ForEsGibtKeinenWegVonPosZuFarbeHorizontal();
		sp.setForInit(Colors.WHITE, new Pos(0,1));
		return sp;
	}

	public static Spielfeld getSpielfeld3x3ForEsGibtKeinenWegVonPosZuFarbeHorizontal() {
		//o  o  b
		//o  o  o
		//o  o  o
		sp = new Spielfeld(); sp.init(3);
		sp.setForInit(Colors.BLACK, new Pos(0,2));
		return sp;
	}
	
	
	public static Spielfeld getSpielfeld4x4ForEsGibtEinenWegVonPosZuFarbeVertikal() {
		//b  o  o  o
		//w  o  o  o
		//w  o  o  o
		//o  o  o  o
		sp = getSpielfeld4x4ForEsGibtKeinenWegVonPosZuFarbeVertikal();
		sp.setForInit(Colors.WHITE, new Pos(2,0));
		return sp;
	}

	public static Spielfeld getSpielfeld4x4ForEsGibtKeinenWegVonPosZuFarbeVertikal() {
		//b  o  o  o
		//w  o  o  o
		//o  o  o  o
		//o  o  o  o
		sp = new Spielfeld(); sp.init(4);
		sp.setForInit(Colors.BLACK, new Pos(0,0));
		sp.setForInit(Colors.WHITE, new Pos(1,0));
		return sp;
	}

	
	public static Spielfeld getSpielfeld4x4ForEsGibtEinenWegVonPosZuFarbeDiagonal() {
		//o  o  o  o
		//o  o  w  o
		//o  w  o  o
		//b  o  o  o
		sp =getSpielfeld4x4ForEsGibtKeinenWegVonPosZuFarbeDiagonal();
		sp.setForInit(Colors.WHITE, new Pos(2,1));
		return sp;
	}
	
	public static Spielfeld getSpielfeld4x4ForEsGibtKeinenWegVonPosZuFarbeDiagonal() {
		//o  o  o  o
		//o  o  w  o
		//o  o  o  o
		//b  o  o  o
		sp = new Spielfeld(); sp.init(4);
		sp.setForInit(Colors.BLACK, new Pos(3,0));
		sp.setForInit(Colors.WHITE, new Pos(1,2));
		return sp;
	}


	public static Spielfeld getSpielfeld2x2ForContains() {
		sp = new Spielfeld(); sp.init(2);
		return sp;
	}

	public static Spielfeld getSpielfeld4x4ForSetzeSpielstein_1HorizDrehen_2VertDrehen_0DiagDrehen() {
		sp = new Spielfeld(); sp.init(4);
		// o  w  b  o 
		// o  o  b  b
		// o  o  o  b
		// o  o  o  w
		sp.setForInit(Colors.WHITE, new Pos(0,1));
		sp.setForInit(Colors.BLACK, new Pos(0,2));
		sp.setForInit(Colors.BLACK, new Pos(1,2));
		sp.setForInit(Colors.BLACK, new Pos(1,3));
		sp.setForInit(Colors.BLACK, new Pos(2,3));
		sp.setForInit(Colors.WHITE, new Pos(3,3));
		return sp;
	}

	public static Spielfeld getSpielfeld3x3ForSetzeSpielstein_Nur1HorizDrehen() {
		sp = new Spielfeld(); sp.init(3);
//		 o b w
//		 o o o
//		 w o o
		sp.setForInit(Colors.BLACK, new Pos(0,1));
		sp.setForInit(Colors.WHITE, new Pos(0,2));
		sp.setForInit(Colors.WHITE, new Pos(2,0));
		return sp;
	}
	
	public static Spielfeld getSpielfeld5x5ForSetzeSpielstein_3HorizDrehen() {
//		o w w w b
//		o o o o o
//		o o o o o
//		o o o o o
//		o o o o o
		sp = new Spielfeld(); sp.init(5);
		for (int i=1; i<4; i++) 
			sp.setForInit(Colors.WHITE, new Pos(0,i));
		sp.setForInit(Colors.BLACK, new Pos(0,4));
		return sp;
	}
	
	public static Spielfeld createDirectionsIteratorSpielfeld() {
		sp = new Spielfeld(); sp.init(3);
		return sp;
	}


}
