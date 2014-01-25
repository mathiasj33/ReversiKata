package de.rmrw.ReversiKata.guiComponents;

public enum JavaFXSpielfeldFeldZustand {
	LEER_UND_NICHT_BESETZBAR("Leer und nicht besetzbar"),
	LEER_UND_BESETZBAR1("Leer und besetzbar durch Spieler 1"),
	LEER_UND_BESETZBAR2("Leer und besetzbar durch Spieler 2"),
	BESETZT1("Besetzt durch Spieler 1"),
	BESETZT2("Besetzt durch Spieler 2");
	
	private String name=null;
	
	JavaFXSpielfeldFeldZustand(String _name){
		this.setName(_name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
	
	public static JavaFXSpielfeldFeldZustand getZustandFromString(String name){
		for (JavaFXSpielfeldFeldZustand sffz : JavaFXSpielfeldFeldZustand.values())
			if (sffz.getName().equals(name))
				return sffz;
		return null;
	}
	
}
