package de.rmrw.ReversiKata.codeTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.rmrw.ReversiKata.code.ReversiSpielRahmen;
import de.rmrw.ReversiKata.views.IFSpielRahmenView;

public class ReversiSpielRahmenTest {
	
	private ReversiSpielRahmen spielRahmen = null;
	private IFSpielRahmenView spielRahmenView;

	@Before
	public void setUp() throws Exception {
		spielRahmenView = Mockito.mock(IFSpielRahmenView.class);
		spielRahmen = Mockito.spy(new ReversiSpielRahmen());
		spielRahmen.addView(spielRahmenView);
	}

	@Test
	public final void testNeuesSpiel() {
		spielRahmen.neuesSpiel();
		Assert.assertEquals(2, ((ReversiSpielRahmen) spielRahmen).getSpiel().getSteineAufFeld(1));
	}

}
