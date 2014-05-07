package client;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Child class of JTable which makes its instance tables to be non editable.
 * <p>
 * 
 * @author Stefan Chuklev
 * 
 */
public class GradesJTable extends JTable {

	public GradesJTable() {
		super();
		super.setEnabled(false);
	}

	public GradesJTable(int numRows, int numColumns) {
		super(numRows, numColumns);
		super.setEnabled(false);
	}

	public GradesJTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		super.setEnabled(false);
	}

	public GradesJTable(TableModel dm, TableColumnModel cm,
			ListSelectionModel sm) {
		super(dm, cm, sm);
		super.setEnabled(false);
	}

	public GradesJTable(TableModel dm, TableColumnModel cm) {
		super(dm, cm);
		super.setEnabled(false);
	}

	public GradesJTable(TableModel dm) {
		super(dm);
		super.setEnabled(false);
	}

	public GradesJTable(Vector rowData, Vector columnNames) {
		super(rowData, columnNames);
		super.setEnabled(false);
	}

}
