package de.rmrw.ReversiKata.code;

public class Spieler {
	private String name;
	private Colors color;
	private boolean amZug;

	public Spieler(){ }
	
	public void init(String name_, Colors color_, boolean amZug_) {
		setName(name_);
		setColor(color_);
		setAmZug(amZug_);
	}
	
	
	public Colors getColor() {
		return color;
	}
	
	public void setColor(Colors color_) {
		color = color_;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAmZug() {
		return amZug;
	}

	public void setAmZug(boolean amZug) {
		this.amZug = amZug;
	}
	
	
}
