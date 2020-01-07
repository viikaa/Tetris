import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class HighScoreData extends AbstractTableModel {
	
	private ArrayList<HighScore> highScores = new ArrayList<HighScore>();
	
	public void add(HighScore hs) {
		highScores.add(hs);
	}

	@Override
	public int getRowCount() {
		return highScores.size();
	}
	@Override
	public int getColumnCount() {
		return 2;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0)
			return highScores.get(rowIndex).getName();
		if(columnIndex == 1)
			return highScores.get(rowIndex).getScore();
		return null;
	}
}
