import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Display extends Canvas {
	
	private BufferStrategy buffer;
	private GraphicsEnvironment ge;
	private GraphicsDevice gd;
	private GraphicsConfiguration gc;
	private BufferedImage bi;
	private Graphics graphics = null;
	private Graphics2D g2d = null;
	private Color background = new Color(240, 240, 240);
	
	public Display() {
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    gd = ge.getDefaultScreenDevice();
	    gc = gd.getDefaultConfiguration();
	    bi = gc.createCompatibleImage(Game.BOARD_WIDTH_PIXEL, Game.BOARD_HEIGHT_PIXEL);
	    setSize(Game.BOARD_WIDTH_PIXEL, Game.BOARD_HEIGHT_PIXEL);
		setIgnoreRepaint(true);
	}
	
	public void drawBoard() {
		try {
			g2d = bi.createGraphics();
	        g2d.setColor( background );
	        g2d.fillRect( 0, 0, getWidth(), getHeight());
			
			for(int i = 0; i < Game.BOARD_HEIGHT; i++) {
				for(int j = 0; j < Game.BOARD_WIDTH; j++) {
					Field field = Game.fields[j][i];
					if(field.getPiece() != null) {
						drawPiece(field, g2d);
					}
				}
			}
			
			graphics = buffer.getDrawGraphics();
	        graphics.drawImage( bi, 0, 0, null );
	        if( !buffer.contentsLost() )
	        	buffer.show();
	        Thread.yield();
		} finally {
		  if( graphics != null ) 
	          graphics.dispose();
	        if( g2d != null ) 
	          g2d.dispose();
	    }
	}
	
	public void setBuffer(BufferStrategy bs) {
		buffer = bs;
	}
	
	private void drawPiece(Field field, Graphics2D g) {
		g.setColor(field.getPiece().getColor());
		g.fillRect(field.getPiece().getPixelCoordinateX(), field.getPiece().getPixelCoordinateY(), Game.PIECE_SIZE, Game.PIECE_SIZE);
		g.setColor(Color.BLACK);
		g.drawRect(field.getPiece().getPixelCoordinateX(), field.getPiece().getPixelCoordinateY(), Game.PIECE_SIZE, Game.PIECE_SIZE);
	}
}
