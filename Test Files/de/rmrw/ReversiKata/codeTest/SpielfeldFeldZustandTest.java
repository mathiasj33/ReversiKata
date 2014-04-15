package de.rmrw.ReversiKata.codeTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.rmrw.ReversiKata.guiComponents.JavaFXSpielfeldFeldZustand;

public class SpielfeldFeldZustandTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testGetName() {
		Assert.assertEquals(JavaFXSpielfeldFeldZustand.LEER_UND_NICHT_BESETZBAR, JavaFXSpielfeldFeldZustand.getZustandFromString("Leer und nicht besetzbar"));
	}

}
