package Test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import lifePanel.MainPanel;

public class testIterateCell {

	/*
	 * a method called iterateCellFlag() is created in MainPanel Class
	 * which takes alive status and numNeigbors as arguements
	 * and test output against original codes with different input values
	 */
	private static MainPanel panel;
	
	@Before
	public void setUp() {
		 panel = new MainPanel(15);
	}
	
	/**
	 * A dead cell with exactly 3 alive neighbors will live
	 */
	@Test
	public void testiterateCell1(){
		boolean alive = false;	
		int numNeighbors = 3;
		boolean res = panel.iterateCellFlag(alive, numNeighbors);
		assertEquals(true, res);
	}

	/**
	 * A dead cell with not equals to 3 alive neighbors will still be dead
	 */
	@Test
	public void testiterateCell2(){
		boolean alive = false;	
		int numNeighbors = 2;
		boolean res = panel.iterateCellFlag(alive, numNeighbors);
		assertEquals(false, res);
	}

	/**
	 * An alive cell with larger than 3 alive neighbors will die
	 */
	@Test
	public void testiterateCell3(){
		boolean alive = true;	
		int numNeighbors = 4;
		boolean res = panel.iterateCellFlag(alive, numNeighbors);
		assertEquals(false, res);
	}

	/**
	 * An alive cell with less than 2 alive neighbors will die
	 */
	@Test
	public void testiterateCell4(){
		boolean alive = true;	
		int numNeighbors = 0;
		boolean res = panel.iterateCellFlag(alive, numNeighbors);
		assertEquals(false, res);
	}
	
	/**
	 * An alive cell with exactly 2 alive neighbors will live
	 */
	@Test
	public void testiterateCell5(){
		boolean alive = true;	
		int numNeighbors = 4;
		boolean res = panel.iterateCellFlag(alive, numNeighbors);
		assertEquals(false, res);
	}
	
	

}
