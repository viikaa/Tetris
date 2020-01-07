import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class GameOverScreen extends JPanel {
	private JTextField nameTextField;

	/**
	 * Create the panel.
	 */
	public GameOverScreen() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Game Over");
		lblNewLabel.setBounds(115, 320, 170, 40);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 30));
		add(lblNewLabel);
		
		nameTextField = new JTextField();
		nameTextField.setFocusTraversalKeysEnabled(false);
		nameTextField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		nameTextField.setBackground(UIManager.getColor("Panel.background"));
		nameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		nameTextField.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 22));
		nameTextField.setBounds(10, 390, 380, 30);
		add(nameTextField);
		
		nameTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save and quit");
		btnNewButton.setRequestFocusEnabled(false);
		btnNewButton.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 20));
		btnNewButton.setBounds(95, 440, 210, 40);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().save(nameTextField.getText());
			}
		});
		add(btnNewButton);
		
	}
}
