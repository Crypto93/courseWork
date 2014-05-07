package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 * Class which creates tabs containing tables with subjects and grades, defined
 * by semester in to the grades information panel.
 * <p>
 * 
 * @author Stefan Chuklev
 * 
 */
public class GradesInfo extends JPanel {

	JTabbedPane tabbedPane;

	public GradesInfo(String[][] gradesData, int numberOfSemesters) {

		Dimension size = getPreferredSize();
		size.width = 650;
		setPreferredSize(size);

		setLayout(new BorderLayout());

		setBorder(BorderFactory.createTitledBorder("Информация за оценки"));

		setBackground(Color.decode("#ffffff"));

		// creating components
		tabbedPane = new JTabbedPane();
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		gradesDispatcher(gradesData, numberOfSemesters);

	}

	/**
	 * The method get the two dimensional array with all the information about
	 * student's grades and process it into smaller arrays defined by the
	 * semester.
	 * 
	 * @param array
	 *            with information for all the subjects from student's
	 *            curriculum.
	 * @param number
	 *            of semesters (depends from the type of the student)
	 * 
	 * @return void
	 * 
	 */
	public void gradesDispatcher(String[][] gradesData, int numberOfSemesters) {

		int[] semLength = new int[15];

		// check the number of subjects from a certain semester
		for (int i = 0; i < semLength.length; i++) {
			for (int j = 0; j < gradesData.length; j++) {
				if (Integer.parseInt(gradesData[j][1]) == i + 1) {
					semLength[i]++;
				}
			}

		}

		for (int sem = 1; sem <= numberOfSemesters; sem++) {
			int temprow = 0;
			String[][] temp = new String[semLength[sem - 1]][2];
			for (int row = 0; row < gradesData.length; row++) {
				if (sem == Integer.parseInt(gradesData[row][1])) {

					if (Integer.parseInt(gradesData[row][2]) == 0) {
						temp[temprow][0] = gradesData[row][0];
						temp[temprow][1] = "-";
					} else {
						temp[temprow][0] = gradesData[row][0];
						temp[temprow][1] = gradesData[row][2];
					}

					temprow++;

				}
			}

			addNewTab(temp, sem);
		}
	}

	/**
	 * Add tab for a certain semester.
	 * 
	 * @param temp
	 *            array processed by gradesDispatcher method.
	 * @param the
	 *            number of a certain semester
	 * 
	 * @return void
	 * 
	 */
	public void addNewTab(String[][] gradesInfo, int sem) {
		GradesTableModel tableModel = new GradesTableModel(gradesInfo);
		JTable table = new GradesJTable(tableModel);

		tabbedPane.addTab("Семестър " + sem, table);

		add(tabbedPane);
	}
}
