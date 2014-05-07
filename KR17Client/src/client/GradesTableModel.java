package client;

import javax.swing.table.DefaultTableModel;

/**
 * Child class of Default table model which creates a new TableModel with
 * specified constructor columns "Subject" and "Grade" and two dimensional array
 * as an information carrier.
 * <p>
 * 
 * @param data
 *            String[][] array which contains list of subject and grades
 *            <p>
 * @author Stefan Chuklev
 * 
 */
public class GradesTableModel extends DefaultTableModel {

	public GradesTableModel() {
		super();
	}

	public GradesTableModel(Object[][] data) {
		super(data, new String[] { "Subject", "Grade" });
	}

}
