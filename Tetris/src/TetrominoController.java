import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class TetrominoController extends KeyAdapter{
	
	public void keyPressed(KeyEvent ke) {
		int keyCode = ke.getKeyCode();
		
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			Game.tetrominos.get(Game.tetrominoCount).moveLeft();
			Game.gameBoard.drawBoard();
			break;
		case KeyEvent.VK_RIGHT:
			Game.tetrominos.get(Game.tetrominoCount).moveRight();
			Game.gameBoard.drawBoard();
			break;
		case KeyEvent.VK_DOWN:
			Game.getInstance().faster = true;
		}
	}
	
	public void keyReleased(KeyEvent ke) {
		switch(ke.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			Game.getInstance().faster = false;
			break;
		case KeyEvent.VK_SPACE:
			Game.tetrominos.get(Game.tetrominoCount).rotate();
		}
	}
}