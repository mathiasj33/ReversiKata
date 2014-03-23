package de.rmrw.ReversiKata.code;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
		reversiSpiel = new ReversiSpiel();
		reversiSpiel.initSpiel(8,new ArrayList<IFSpielView>());
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

	@Override
	public void spielSpeichern(String dateiName) {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance( ReversiSpiel.class );
			Marshaller m = context.createMarshaller();
			m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
			Writer fw = new FileWriter(dateiName );
			m.marshal( getSpiel(), fw );
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ladeSpiel(String dateiName) {
		try {
			reversiSpiel = JAXB.unmarshal(new FileReader( dateiName ), ReversiSpiel.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
