import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class Tetromino {
	private static final int MAX_Y = Game.BOARD_HEIGHT - 1;
	public static Boolean newCanCome = false;
	private static int cnt = 0;
	private int id;
	private int shape;
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	private int bottomCoordinateY;
	private int rightEdgeCoordinateX;
	private int leftEdgeCoordinateX;
	private Boolean created = false;
	
	public Tetromino() {
		id = cnt++;
		Random rand = new Random();
		shape = rand.nextInt(5);
		switch(shape) {
			case 0: created = create0(); break;
			case 1: created = create1(); break;
			case 2: created = create2(); break;
			case 3: created = create3(); break;
			default: created = create4(); break;
		}
		setBottomCoordinateY();
		newCanCome = false;
	}
	
	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	
	public Boolean isCreated() {
		return created;
	}
	
	public synchronized void rotate() {
		//a square cannot be rotated
		if(shape == 1)
			return;
		
		//get rotate axis coordinates, create arrays for new coordinates
		int middleX = pieces.get(1).getX();
		int middleY = pieces.get(1).getY();
		int newX[] = new int[4];
		int newY[] = new int[4];
		
		//Check if can be rotated
		for(int i = 0; i < pieces.size(); i++) {
			newX[i] = middleX + (pieces.get(i).getY() - middleY);
			newY[i] = middleY + (middleX - pieces.get(i).getX());
			if(newX[i] > 19 || newX[i] < 0|| newY[i] > 39 || newY[i] < 0 ||
			   (Game.fields[newX[i]][newY[i]].isTaken() && Game.fields[newX[i]][newY[i]].getPiece().getID() != pieces.get(i).getID()) ||
			   (bottomCoordinateY > 0 && pieces.get(i).getY() == bottomCoordinateY && Game.fields[pieces.get(i).getX()][bottomCoordinateY - 1].isTaken()) ||
			   bottomCoordinateY == 0)
				return;
		}
		
		//Rotate
		for(int i = 0; i < pieces.size(); i++) {
			Game.fields[pieces.get(i).getX()][pieces.get(i).getY()].free();
			pieces.get(i).setX(newX[i]);
			pieces.get(i).setY(newY[i]); 
			Game.fields[pieces.get(i).getX()][pieces.get(i).getY()].take(pieces.get(i));
		}
		
		setBottomCoordinateY();
	}

	private void setBottomCoordinateY() {
		int minY = 39;
		for(Piece piece: pieces) {
			if(piece.getY() < minY)
				minY = piece.getY();
		}
		bottomCoordinateY = minY;
	}
	
	private void setRightEdgeCoordinateX() {
		int maxX = 0;
		for(Piece piece: pieces) {
			if(piece.getX() > maxX)
				maxX = piece.getX();
		}
		rightEdgeCoordinateX = maxX;
	}
	
	private void setLeftEdgeCoordinateX() {
		int minX = 39;
		for(Piece piece: pieces) {
			if(piece.getX() < minX)
				minX = piece.getX();
		}
		leftEdgeCoordinateX = minX;
	}
	
	private Boolean create0() {
		int xCoordinates[] = {(Game.BOARD_WIDTH / 2) - 1, (Game.BOARD_WIDTH / 2), (Game.BOARD_WIDTH / 2) + 1,(Game.BOARD_WIDTH / 2) + 2};
		int yCoordinates[] = {MAX_Y, MAX_Y, MAX_Y, MAX_Y};
		Color color = Color.CYAN;
		for(int i = 0; i < 4; i++) {
			if(Game.fields[xCoordinates[i]][yCoordinates[i]].isTaken())
				return false;
		}
		Piece a = new Piece(xCoordinates[0], yCoordinates[0], color, this);
		Piece b = new Piece(xCoordinates[1], yCoordinates[1], color, this);
		Piece c = new Piece(xCoordinates[2], yCoordinates[2], color, this);
		Piece d = new Piece(xCoordinates[3], yCoordinates[3], color, this);
		pieces.add(a);
		pieces.add(b);
		pieces.add(c);
		pieces.add(d);
		return true;
	}
	
	private Boolean create1() {
		int xCoordinates[] = {(Game.BOARD_WIDTH / 2) + 1, (Game.BOARD_WIDTH / 2), (Game.BOARD_WIDTH / 2),(Game.BOARD_WIDTH / 2) + 1};
		int yCoordinates[] = {MAX_Y - 1, MAX_Y - 1, MAX_Y, MAX_Y};
		Color color = Color.ORANGE;
		for(int i = 0; i < 4; i++) {
			if(Game.fields[xCoordinates[i]][yCoordinates[i]].isTaken())
				return false;
		}
		Piece a = new Piece(xCoordinates[0], yCoordinates[0], color, this);
		Piece b = new Piece(xCoordinates[1], yCoordinates[1], color, this);
		Piece c = new Piece(xCoordinates[2], yCoordinates[2], color, this);
		Piece d = new Piece(xCoordinates[3], yCoordinates[3], color, this);
		pieces.add(a);
		pieces.add(b);
		pieces.add(c);
		pieces.add(d);
		return true;
	}
	
	private Boolean create2() {
		int xCoordinates[] = {(Game.BOARD_WIDTH / 2) - 1, (Game.BOARD_WIDTH / 2), (Game.BOARD_WIDTH / 2) + 1,(Game.BOARD_WIDTH / 2)};
		int yCoordinates[] = {MAX_Y, MAX_Y , MAX_Y, MAX_Y - 1};
		Color color = Color.MAGENTA;
		for(int i = 0; i < 4; i++) {
			if(Game.fields[xCoordinates[i]][yCoordinates[i]].isTaken())
				return false;
		}
		Piece a = new Piece(xCoordinates[0], yCoordinates[0], color, this);
		Piece b = new Piece(xCoordinates[1], yCoordinates[1], color, this);
		Piece c = new Piece(xCoordinates[2], yCoordinates[2], color, this);
		Piece d = new Piece(xCoordinates[3], yCoordinates[3], color, this);
		pieces.add(a);
		pieces.add(b);
		pieces.add(c);
		pieces.add(d);
		return true;
	}
	
	private Boolean create3() {
		int xCoordinates[] = {(Game.BOARD_WIDTH / 2) + 1, (Game.BOARD_WIDTH / 2), (Game.BOARD_WIDTH / 2),(Game.BOARD_WIDTH / 2)};
		int yCoordinates[] = {MAX_Y - 2, MAX_Y - 2, MAX_Y - 1, MAX_Y};
		Color color = Color.YELLOW;
		for(int i = 0; i < 4; i++) {
			if(Game.fields[xCoordinates[i]][yCoordinates[i]].isTaken())
				return false;
		}
		Piece a = new Piece(xCoordinates[0], yCoordinates[0], color, this);
		Piece b = new Piece(xCoordinates[1], yCoordinates[1], color, this);
		Piece c = new Piece(xCoordinates[2], yCoordinates[2], color, this);
		Piece d = new Piece(xCoordinates[3], yCoordinates[3], color, this);
		pieces.add(a);
		pieces.add(b);
		pieces.add(c);
		pieces.add(d);
		return true;
	}
	
	private Boolean create4() {
		int xCoordinates[] = {(Game.BOARD_WIDTH / 2) - 1, (Game.BOARD_WIDTH / 2), (Game.BOARD_WIDTH / 2), (Game.BOARD_WIDTH / 2) + 1};
		int yCoordinates[] = {MAX_Y - 1, MAX_Y - 1, MAX_Y, MAX_Y};
		Color color = Color.GREEN;
		for(int i = 0; i < 4; i++) {
			if(Game.fields[xCoordinates[i]][yCoordinates[i]].isTaken())
				return false;
		}
		Piece a = new Piece(xCoordinates[0], yCoordinates[0], color, this);
		Piece b = new Piece(xCoordinates[1], yCoordinates[1], color, this);
		Piece c = new Piece(xCoordinates[2], yCoordinates[2], color, this);
		Piece d = new Piece(xCoordinates[3], yCoordinates[3], color, this);
		pieces.add(a);
		pieces.add(b);
		pieces.add(c);
		pieces.add(d);
		return true;
	}

	private Boolean shouldStopDown() {
		Boolean stop = false;
		if(bottomCoordinateY == 0)
			stop = true;
		else {
			for(Piece p: pieces) {
				if(Game.fields[p.getX()][p.getY() - 1].isTaken() && (Game.fields[p.getX()][p.getY() - 1].getPiece().getID() != p.getID()))
					stop = true;
			}
		}
		return stop;
	}
	
	private Boolean shouldStopRight() {
		Boolean stop = false;
		if(rightEdgeCoordinateX == Game.BOARD_WIDTH - 1)
			stop = true;
		else {
			for(Piece p: pieces) {
				if(Game.fields[p.getX() + 1][p.getY()].isTaken() &&
				   Game.fields[p.getX() + 1][p.getY()].getPiece().getID() != p.getID()) {
					stop = true;
					break;
				}
			}
		}
		return stop;
	}
	
	private Boolean shouldStopLeft() {
		Boolean stop = false;
		if(leftEdgeCoordinateX == 0)
			stop = true;
		else {
			for(Piece p: pieces) {
				if(Game.fields[p.getX() - 1][p.getY()].isTaken() &&
				   Game.fields[p.getX() - 1][p.getY()].getPiece().getID() != p.getID()) {
					stop = true;
					break;
				}
			}
		}
		return stop;
	}
	
	public int getID() {
		return id;
	}
	
	public void fall() {
		if(!shouldStopDown()) {
			for (Piece piece: pieces) {
				Game.fields[piece.getX()][piece.getY()].free();	
			}
			for(Piece piece: pieces) {
				piece.moveDown();
			}
		}
		else {
			newCanCome = true;
		}
			
		setBottomCoordinateY();
	}
	
	public void moveRight() {
		if(!shouldStopRight()) {
			for (Piece piece: pieces) {
				Game.fields[piece.getX()][piece.getY()].free();
			}
			for(Piece piece: pieces) {
				piece.moveRight();
			}
		}
		setRightEdgeCoordinateX();
	}
	
	public void moveLeft() {
		if(!shouldStopLeft()) {
			for (Piece piece: pieces) {
				Game.fields[piece.getX()][piece.getY()].free();
			}
			for(Piece piece: pieces) {
				piece.moveLeft();
			}
		}
		setLeftEdgeCoordinateX();
	}
}