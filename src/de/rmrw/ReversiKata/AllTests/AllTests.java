package de.rmrw.ReversiKata.AllTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.rmrw.ReversiKata.CodeTest.ColorsTest;
import de.rmrw.ReversiKata.CodeTest.DirectionIteratorTest;
import de.rmrw.ReversiKata.CodeTest.LineIteratorTest;
import de.rmrw.ReversiKata.CodeTest.PosTest;
import de.rmrw.ReversiKata.CodeTest.SpielTest;
import de.rmrw.ReversiKata.CodeTest.SpielfeldFeldZustandTest;
import de.rmrw.ReversiKata.CodeTest.SpielfeldIteratorTest;
import de.rmrw.ReversiKata.CodeTest.SpielfeldTest;
import de.rmrw.ReversiKata.guiComponentsTest.JavaFXSpielfeldFeldTest;
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
	SpielTest.class,
	SpielfeldFeldZustandTest.class,
	JavaFXSpielfeldFeldTest.class,
	JavaFXSpielfeldFeldViewTest.class,
	JavaFXSpielViewTest.class,
	JavaFXSpielfeldViewTest.class
	} )
public final class AllTests {}
