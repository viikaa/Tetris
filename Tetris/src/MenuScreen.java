import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MenuScreen extends JPanel {

	public MenuScreen() {
		setMaximumSize(new Dimension(400, 800));
		setMinimumSize(new Dimension(400, 800));
		setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 20));
		btnStart.setLocation(120, 280);
		btnStart.setSize(new Dimension(160, 60));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().cards.show(Game.getInstance().cardPane, "gameBoard");
				JFrame scoreFrame = new JFrame();
				scoreFrame.setUndecorated(true);
				Game.getInstance().scoreLabel = new JLabel();
				Game.getInstance().scoreLabel.setText(Integer.toString(Game.getInstance().score));
				Game.getInstance().scoreLabel.setHorizontalTextPosition(JLabel.CENTER);
				Game.getInstance().scoreLabel.setFont(Game.getInstance().scoreLabel.getFont().deriveFont(80.0f));
				scoreFrame.getContentPane().add(Game.getInstance().scoreLabel);
				scoreFrame.pack();
				scoreFrame.setResizable(false);	
				scoreFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				scoreFrame.setLocation(Game.getInstance().mainFrame.getX() + Game.getInstance().mainFrame.getWidth(), Game.getInstance().mainFrame.getY());
				scoreFrame.setVisible(true);
				Game.getInstance().start();
			}
		});
		add(btnStart);
		
		JButton btnHighscores = new JButton("Highscores");
		btnHighscores.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 20));
		btnHighscores.setSize(new Dimension(162, 60));
		btnHighscores.setBounds(120, 380, 160, 60);
		btnHighscores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HighScoresScreen hs = (HighScoresScreen) (Game.getInstance().highScoresScreen);
				hs.getTable().setModel(Game.getInstance().load());
				Game.getInstance().cards.show(Game.getInstance().cardPane, "highScoresScreen");
			}
		});
		add(btnHighscores);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 20));
		btnExit.setSize(new Dimension(162, 60));
		btnExit.setBounds(120, 480, 160, 60);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(btnExit);
		
		JLabel lblTetris = new JLabel("Tetris");
		lblTetris.setHorizontalAlignment(SwingConstants.CENTER);
		lblTetris.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 40));
		lblTetris.setBounds(120, 100, 160, 100);
		add(lblTetris);

	}
}
