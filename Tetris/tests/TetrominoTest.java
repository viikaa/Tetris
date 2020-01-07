import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TetrominoTest {
	
	private Tetromino t;
	
	@Before
	public void setUp() {
		String[] args = {""};
		Game.getInstance().main(args);
		t = new Tetromino();
	}
	
	@Test
	public void testFall() {	
		int[] xCoordinates = new int[4];
		int[] yCoordinates = new int[4];
		for(int i = 0; i < 4; i++) {
			xCoordinates[i] = t.getPieces().get(i).getX();
			yCoordinates[i] = t.getPieces().get(i).getY();
		}
		t.fall();
		for(int i = 0; i < 4; i++) {
			org.junit.Assert.assertEquals(xCoordinates[i], t.getPieces().get(i).getX());
			org.junit.Assert.assertEquals(yCoordinates[i] - 1, t.getPieces().get(i).getY());
		}
	}
	
	@Test
	public void testMoveRight() {
		int[] xCoordinates = new int[4];
		int[] yCoordinates = new int[4];
		for(int i = 0; i < 4; i++) {
			xCoordinates[i] = t.getPieces().get(i).getX();
			yCoordinates[i] = t.getPieces().get(i).getY();
		}
		t.moveRight();
		for(int i = 0; i < 4; i++) {
			org.junit.Assert.assertEquals(xCoordinates[i] + 1, t.getPieces().get(i).getX());
			org.junit.Assert.assertEquals(yCoordinates[i], t.getPieces().get(i).getY());
		}
	}
	
	@Test
	public void testMoveLeft() {
		int[] xCoordinates = new int[4];
		int[] yCoordinates = new int[4];
		for(int i = 0; i < 4; i++) {
			xCoordinates[i] = t.getPieces().get(i).getX();
			yCoordinates[i] = t.getPieces().get(i).getY();
		}
		t.moveLeft();
		for(int i = 0; i < 4; i++) {
			org.junit.Assert.assertEquals(xCoordinates[i] - 1, t.getPieces().get(i).getX());
			org.junit.Assert.assertEquals(yCoordinates[i], t.getPieces().get(i).getY());
		}
	}

}
