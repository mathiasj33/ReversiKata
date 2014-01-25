package de.rmrw.ReversiKata.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.rmrw.ReversiKata.viewsTest.JavaFXSpielTest;
import de.rmrw.ReversiKata.viewsTest.JavaFXSpielfeldFeldTest;
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
	JavaFXSpielTest.class,
	JavaFXSpielfeldViewTest.class
	} )
public final class AllTests {}
