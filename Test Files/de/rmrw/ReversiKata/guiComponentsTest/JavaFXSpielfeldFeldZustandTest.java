package de.rmrw.ReversiKata.guiComponentsTest;

import org.junit.Assert;
import org.junit.Test;

import de.rmrw.ReversiKata.guiComponents.JavaFXSpielfeldFeldZustand;



public class JavaFXSpielfeldFeldZustandTest {

	@Test
	public final void testGetZustandFromString() {
		Assert.assertEquals(JavaFXSpielfeldFeldZustand.LEER_UND_NICHT_BESETZBAR, JavaFXSpielfeldFeldZustand.getZustandFromString("Leer und nicht besetzbar"));
	}

}
