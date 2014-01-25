package de.rmrw.ReversiKata.AllTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.rmrw.ReversiKata.codeTest.ColorsTest;
import de.rmrw.ReversiKata.codeTest.DirectionIteratorTest;
import de.rmrw.ReversiKata.codeTest.LineIteratorTest;
import de.rmrw.ReversiKata.codeTest.PosTest;
import de.rmrw.ReversiKata.codeTest.ReversiSpielTest;
import de.rmrw.ReversiKata.codeTest.SpielfeldIteratorTest;
import de.rmrw.ReversiKata.codeTest.SpielfeldTest;
import de.rmrw.ReversiKata.guiComponentsTest.JavaFXSpielfeldFeldTest;
import de.rmrw.ReversiKata.guiComponentsTest.JavaFXSpielfeldFeldZustandTest;
import de.rmrw.ReversiKata.viewsTest.JavaFXSpielViewTest;
import de.rmrw.ReversiKata.viewsTest.JavaFXSpielfeldFeldViewTest;
import de.rmrw.ReversiKata.viewsTest.JavaFXSpielfeldViewTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	SpielfeldTest.class, 
	ColorsTest.class, 
	SpielfeldIteratorTest.class, 
	LineIteratorTest.class, 
	PosTest.class,
	DirectionIteratorTest.class,
	ReversiSpielTest.class,
	JavaFXSpielfeldFeldZustandTest.class,
	JavaFXSpielfeldFeldTest.class,
	JavaFXSpielfeldFeldViewTest.class,
	JavaFXSpielViewTest.class,
	JavaFXSpielfeldViewTest.class
	} )
public final class AllTests {}
