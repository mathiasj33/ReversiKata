package de.rmrw.ReversiKata.code;

import de.rmrw.ReversiKata.views.IFSpielRahmenView;

public interface IFSpielRahmenModel {

	void neuesSpiel();

	IFSpielModel getSpiel();

	void addView(IFSpielRahmenView spielRahmenView);

}
