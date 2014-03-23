package de.rmrw.ReversiKata.code;

public class Pos implements Comparable<Pos> {
	
	private int zeile;
	private int spalte;
	
	public Pos(){}
	
	public Pos(int zeile, int spalte) {
		this.setZeile(zeile);
		this.setSpalte(spalte);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + zeile;
		result = prime * result + spalte;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pos other = (Pos) obj;
		if (zeile != other.zeile)
			return false;
		if (spalte != other.spalte)
			return false;
		return true;
	}



	public int getZeile() {
		return zeile;
	}

	public void setZeile(int x) {
		this.zeile = x;
	}

	public int getSpalte() {
		return spalte;
	}

	public void setSpalte(int y) {
		this.spalte = y;
	}
	
	public String toString() {
		return new String("Zeile: " + zeile + " Spalte: " + spalte);
	}
	
	public Pos add(Pos pos2) {
		return new Pos(zeile + pos2.getZeile(), spalte + pos2.getSpalte());
	}
	
	public int compareTo(Pos pos2) {
		if (this.getZeile() < pos2.getZeile()) 
			return -1;
		if (this.getZeile() > pos2.getZeile())
			return 1;
		// Ab hier gilt x1==x2
		if (this.getSpalte() < pos2.getSpalte()) 
			return -1;
		if (this.getSpalte() > pos2.getSpalte())
			return 1;
		// Ab hier gilt x1==x2 && y1==y2
		return 0;
	}

}
