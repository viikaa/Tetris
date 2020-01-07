

public class Field{
	
	private int size = Game.PIECE_SIZE;
	private int pixelCoordinateX;
	private int pixelCoordinateY;
	private Boolean hasPiece = false;
	private Piece piece = null;
	
	public Field(int x, int y) {
		pixelCoordinateX = x * size;
		pixelCoordinateY = Math.abs((y - 39) * size);
	}
	
	public int getPixelCoordinateX() {
		return pixelCoordinateX;
	}
	
	public int getPixelCoordinateY() {
		return pixelCoordinateY;
	}
	
	public Boolean isTaken() {
		return hasPiece;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public void take(Piece p) {
		hasPiece = true;
		piece = p;
	}
	
	public void free() {
		hasPiece = false;
		piece = null;
	}
}