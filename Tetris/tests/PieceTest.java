import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class PieceTest {
	Piece p;
	
	@Before
	public void setUp() {
		String[] args = {""};
		Game.getInstance().main(args);
		p = new Piece(10, 10, Color.BLACK, new Tetromino());
	}

	@Test
	public void testGetPixelCoordinateX() {
		
		Assert.assertEquals(200, p.getPixelCoordinateX());
	}
	
	@Test
	public void testGetPixelCoordinateY() {
		Assert.assertEquals(580, p.getPixelCoordinateY());
	}
	
	@Test
	public void testGetX() {
		Assert.assertEquals(10, p.getX());
	}
	
	@Test
	public void testGetY() {
		Assert.assertEquals(10, p.getY());
	}
	
	@Test
	public void testMoveRight() {
		p.moveRight();
		Assert.assertEquals(11, p.getX());
	}
	
	@Test
	public void testMoveLeft() {
		p.moveLeft();
		Assert.assertEquals(9, p.getX());
	}

}
