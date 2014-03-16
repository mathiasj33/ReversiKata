package de.rmrw.ReversiKata.code;

import java.util.ArrayList;
import java.util.List;

import de.rmrw.ReversiKata.views.IFSpielRahmenView;
import de.rmrw.ReversiKata.views.IFSpielView;

public class ReversiSpielRahmen implements IFSpielRahmenModel {

	private ReversiSpiel reversiSpiel = null;
	private List<IFSpielRahmenView> spielRahmenViews = null;
	
	public ReversiSpielRahmen() {
		spielRahmenViews= new ArrayList<IFSpielRahmenView>();
	}

	@Override
	public void neuesSpiel() {
		reversiSpiel = new ReversiSpiel(8,new ArrayList<IFSpielView>());
		reversiSpiel.initSpiel();
		reversiSpiel.registriereSpieler("Mathias");
		reversiSpiel.registriereSpieler("Robert");
	}

	public ReversiSpiel getSpiel() {
		return reversiSpiel;
	}

	@Override
	public void addView(IFSpielRahmenView spielRahmenView) {
		spielRahmenViews.add(spielRahmenView);
	}

	void updateViews(){
		for (IFSpielRahmenView rahmenView : spielRahmenViews) {
			rahmenView.update();
		}
	}
	
}
