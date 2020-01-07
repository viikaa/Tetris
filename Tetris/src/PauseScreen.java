import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class PauseScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public PauseScreen() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PAUSE");
		lblNewLabel.setBounds(150, 330, 100, 30);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 30));
		add(lblNewLabel);

	}

}
