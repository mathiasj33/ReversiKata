package de.rmrw.ReversiKata.codeTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.rmrw.ReversiKata.code.ReversiSpielRahmen;
import de.rmrw.ReversiKata.code.SpielfeldFeldZustand;
import de.rmrw.ReversiKata.views.IFSpielRahmenView;

public class ReversiSpielRahmenTest {
	
	private ReversiSpielRahmen spySpielRahmen = null;
	private ReversiSpielRahmen spielRahmen = null;
	private IFSpielRahmenView spielRahmenView;

	@Before
	public void setUp() throws Exception {
		spielRahmenView = Mockito.mock(IFSpielRahmenView.class);
		spielRahmen = new ReversiSpielRahmen();
		spySpielRahmen = Mockito.spy(spielRahmen);
		spySpielRahmen.addView(spielRahmenView);
	}

	@Test
	public final void testNeuesSpiel() {
		spySpielRahmen.neuesSpiel();
		Assert.assertEquals(2, ((ReversiSpielRahmen) spySpielRahmen).getSpiel().getSteineAufFeld(1));
	}
	
	@Test
	public final void testSpielLadenUndSpeichern() // Eigentlich zu viele Aspekte auf einmal getestet... :(
	{
		spielRahmen.ladeSpiel("src\\de\\rmrw\\ReversiKata\\codeTest\\TestSpiel.xml");
		Assert.assertEquals(SpielfeldFeldZustand.BESETZT1, spielRahmen.getSpiel().getFeldZustand(2, 0));
		Assert.assertEquals(SpielfeldFeldZustand.LEER_UND_NICHT_BESETZBAR, spielRahmen.getSpiel().getFeldZustand(7, 0));
		Assert.assertEquals(SpielfeldFeldZustand.LEER_UND_BESETZBAR2, spielRahmen.getSpiel().getFeldZustand(7, 3));
		spielRahmen.getSpiel().setzeSpielstein(2, 7, 3);
		spielRahmen.spielSpeichern("TestSpielTmp.xml");
		spielRahmen.ladeSpiel("src\\de\\rmrw\\ReversiKata\\codeTest\\TestSpiel.xml");
		Assert.assertEquals(SpielfeldFeldZustand.LEER_UND_BESETZBAR2, spielRahmen.getSpiel().getFeldZustand(7, 3));
		spielRahmen.ladeSpiel("TestSpielTmp.xml");
		Assert.assertEquals(SpielfeldFeldZustand.BESETZT2, spielRahmen.getSpiel().getFeldZustand(7, 3));
	}
	

}
