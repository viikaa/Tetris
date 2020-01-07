import java.awt.Color;

public class Piece{
	private int id;
	private int x;
	private int y;
	private int size = Game.PIECE_SIZE;
	private Color color;
	
	Piece(int x, int y, Color c, Tetromino t){
		id = t.getID();
		this.x = x;
		this.y = y;
		color = c;
		Game.fields[x][y].take(this);
	}
	
	public int getID() {
		return id;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int xValue) {
		x = xValue;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int yValue) {
		y = yValue;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void moveDown() {
		y -= 1;
		Game.fields[x][y].take(this);
	}
	
	public void moveRight() {
		x += 1;
		Game.fields[x][y].take(this);
	}
	
	public void moveLeft() {
		x -= 1;
		Game.fields[x][y].take(this);
	}
	
	public int getPixelCoordinateX() {
		return x * size;
	}
	
	public int getPixelCoordinateY() {
		return Math.abs((y - 39) * size);
	}
}