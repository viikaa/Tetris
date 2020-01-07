import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {
	
	@Test
	public void testGetInstance() {
		Game testGame = Game.getInstance();
		Assert.assertNotNull(testGame);
	}
	
	@Test
	public void testSetUp() {
		String[] args = {""};
		Game.main(args);
		Assert.assertNotNull(Game.getInstance().mainFrame);
		Assert.assertNotNull(Game.getInstance().cardPane);
		Assert.assertNotNull(Game.getInstance().menuScreen);
		Assert.assertNotNull(Game.getInstance().gameOverScreen);
		Assert.assertNotNull(Game.getInstance().gameBoard);
		Assert.assertNotNull(Game.getInstance().highScoresScreen);
	}
	
}
