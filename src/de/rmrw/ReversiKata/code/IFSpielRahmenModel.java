package de.rmrw.ReversiKata.code;

import de.rmrw.ReversiKata.views.IFSpielRahmenView;

public interface IFSpielRahmenModel {

	void neuesSpiel();

	IFSpielModel getSpiel();

	void addView(IFSpielRahmenView spielRahmenView);

	void spielSpeichern(String string);

	void ladeSpiel(String string);
        
        default void undo() {
            getSpiel().undo();
        };
        
        default void redo() {
            getSpiel().redo();
        };

}
