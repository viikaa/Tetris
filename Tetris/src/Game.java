import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Dimension;

public class Game extends Thread{
	
	static final int BOARD_WIDTH_PIXEL = 400;
	static final int BOARD_HEIGHT_PIXEL = 800;
	static final int PIECE_SIZE = 20;
	static final int BOARD_WIDTH = BOARD_WIDTH_PIXEL / PIECE_SIZE;
	static final int BOARD_HEIGHT = BOARD_HEIGHT_PIXEL / PIECE_SIZE;	
//	static final int PIECES_IN_ROW = BOARD_WIDTH / PIECE_SIZE;
	static Field[][] fields = new Field[BOARD_WIDTH][BOARD_HEIGHT];
	static Display gameBoard;
	static ArrayList<Tetromino> tetrominos = new ArrayList<Tetromino>();
	static int tetrominoCount = 0;
	static JFrame mainFrame;
	static Game single_instance;
	int sleepTime = 150;
	int score = 0;
	Boolean faster = false;
	CardLayout cards = new CardLayout();
	JPanel cardPane;
	JPanel menuScreen;
	JPanel gameOverScreen;
	JPanel highScoresScreen;
	JLabel scoreLabel;
	
	public static Game getInstance() { 
        if (single_instance == null) 
            single_instance = new Game(); 
        return single_instance; 
    }
	
	public void run() {
		Game.getInstance().play();
		Game.getInstance().gameOver();
	}
	
	private void setUp() {
		createFieldsOfBoard();	
		initializeScreens();
		addController();
		setUpCardLayout();
		setUpMainFrame();	
		setUpBufferStrategy();
	}

	private void setUpBufferStrategy() {
		gameBoard.createBufferStrategy(3);
		gameBoard.setBuffer(gameBoard.getBufferStrategy());
	}

	private void setUpMainFrame() {
		mainFrame.add(cardPane);	
		mainFrame.setSize(new Dimension(BOARD_WIDTH_PIXEL, BOARD_HEIGHT_PIXEL));
		mainFrame.setMinimumSize(new Dimension(BOARD_WIDTH_PIXEL, BOARD_HEIGHT_PIXEL));
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	private void setUpCardLayout() {
		cardPane.setLayout(cards);
		cardPane.add(menuScreen, "menuScreen");
		cardPane.add(gameBoard, "gameBoard");
		cardPane.add(gameOverScreen, "gameOverScreen");
		cardPane.add(highScoresScreen, "highScoresScreen");
	}

	private void addController() {
		TetrominoController controller = new TetrominoController();
		gameBoard.addKeyListener(controller);
	}

	private void createFieldsOfBoard() {
		for(int i = 0; i < BOARD_HEIGHT; i++) {
			for(int j = 0; j < BOARD_WIDTH; j++) {
				fields[j][i] = new Field(j, i);
			}
		}
	}

	private void initializeScreens() {
		mainFrame = new JFrame("Tetris");
		mainFrame.setUndecorated(true);
		cardPane = new JPanel();
		menuScreen = new MenuScreen();
		gameOverScreen = new GameOverScreen();
		highScoresScreen = new HighScoresScreen();
		gameBoard = new Display();	
	}
	
	private void play() {	
		gameBoard.requestFocus();
		
		//First tetromino
		tetrominos.add(new Tetromino());
		
		//Game	
		Boolean game = true;
		
		while(game) {
				//Delete row if full
				Game.getInstance().deleteRow();
				//int actualSleepTime = Game.getInstance().sleepTime; //Changes, when "down" key is pressed
				//Configuring speed of falling with sleep
				try {
					if(Game.getInstance().faster)
						Thread.sleep(50);
					else
						Thread.sleep(Game.getInstance().sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				//Falling, or creating new tetromino
				if(Tetromino.newCanCome) {
					Tetromino nextTetromino = new Tetromino();
					tetrominos.add(nextTetromino);
					if(!nextTetromino.isCreated()) {
						game = false;
					}
					tetrominoCount ++;
					if(tetrominoCount >0 && tetrominoCount % 10 == 0)
						Game.getInstance().speedUp();
				}
				else
					tetrominos.get(tetrominoCount).fall();
				
				gameBoard.drawBoard();
		}
	}
	
	private void deleteRow() {
		for(int i = 0; i < BOARD_HEIGHT; i++) {
			Boolean rowIsFull = true;
			for(int j = 0; j < BOARD_WIDTH; j++) {
				if(!fields[j][i].isTaken())
					rowIsFull = false;
			}
			if(rowIsFull) {
				for(int l = 0; l < BOARD_WIDTH; l++) {
					fields[l][i].free();
				}
				
				for(int k = i + 1; k < BOARD_HEIGHT; k++) {
					for(int l = 0; l < BOARD_WIDTH; l++) {
						if(fields[l][k].isTaken()) {
							fields[l][k].getPiece().moveDown();
							fields[l][k].free();
						}
					}
				}
				Game.getInstance().score++;
				Game.getInstance().scoreLabel.setText(Integer.toString(Game.getInstance().score));
			}
		}
	}

	private void speedUp() {
		Game.getInstance().sleepTime = calculateNewSleepTime();
	}

	private int calculateNewSleepTime() {
		return Math.max( (int) (Game.getInstance().sleepTime * 0.8), 50);
	}
	
	private void gameOver() {
		Game.getInstance().cards.show(Game.getInstance().cardPane, "gameOverScreen");
	}
	
	public HighScoreData load() {
		HighScoreData highScoreData = new HighScoreData();
		try {
			FileInputStream fis = new FileInputStream("highScores");
			ObjectInputStream ois = new ObjectInputStream(fis);
			highScoreData = (HighScoreData) ois.readObject();
			ois.close();
		}catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return highScoreData;
	}
	
	public void save(String name) {
		HighScoreData highScoreData = Game.getInstance().load();
		highScoreData.add(new HighScore(name, Game.getInstance().score));
		try {
			FileOutputStream fos = new FileOutputStream("highScores");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(highScoreData);
			oos.close();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String args[]) {
		Game game = getInstance();
		game.setUp();
	}
}