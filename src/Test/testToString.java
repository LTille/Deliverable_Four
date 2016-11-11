package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lifePanel.Cell;
import lifePanel.MainPanel;

import static org.mockito.Mockito.*;

/**
 * 
 * Test toString method in Cell class
 *
 */
public class testToString {

	/**
	 * Alive cell should return "X"
	 */
	@Test
	public void testAliveCell() {

		Cell cell = new Cell(true);
		assertEquals("X", cell.toString());
	}

	/**
	 * Dead cell should return "."
	 */
	@Test
	public void testDeadCell() {
		Cell cell = new Cell(false);
		assertEquals(".", cell.toString());
	}
	
	/** 
	 * A previously alive cell, once set to dead, the text for this cell will become " "
	 * which will also return "." in toString()
	 */
	@Test
	public void testdead1() {
		Cell newcell = new Cell(true);
		newcell.setAlive(false);
		assertEquals(".", newcell.toString());
	}
	
	/**
	 * The cell in the initial board should be all dead, meaning all "."
	 */
	@Test
	public void testBoard() {
		Cell[][] board = new Cell[3][3];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = new Cell();
				board[i][j].setAlive(true);
				sb.append(".");
			}
			sb.append("\n");
		}
		Cell mockCell = mock(Cell.class);
        when(mockCell.toString()).thenReturn(".");
        MainPanel panel = new MainPanel(3);  
        String status = panel.toString();
        assertEquals(sb.toString(), status);
	}
}
