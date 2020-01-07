import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JButton;

public class HighScoresScreen extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public HighScoresScreen() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("High scores");
		lblNewLabel.setBounds(116, 40, 170, 40);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 30));
		add(lblNewLabel);
		
		table = new JTable();
		table.setRowHeight(30);
		table.setBackground(UIManager.getColor("Panel.background"));
		table.setFocusable(false);
		table.setRowSelectionAllowed(false);
		table.setShowVerticalLines(false);
		table.setShowGrid(false);
		table.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
		table.setBounds(25, 120, 350, 550);
		add(table);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 20));
		btnNewButton.setBounds(160, 720, 90, 30);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().cards.show(Game.getInstance().cardPane, "menuScreen");
			}
		});
		add(btnNewButton);

	}
	
	public JTable getTable() {
		return table;
	}
}
