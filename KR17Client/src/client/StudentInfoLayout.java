package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class creates and generate a swing panel with student information fetched
 * from the database.
 * 
 * @param infoArray
 *            array which contains the student's information from the database
 * @param socket
 *            Established connection to the server application
 * 
 * @author Stefan Chuklev
 * 
 */
public class StudentInfoLayout extends JPanel implements ActionListener {

	private GridBagConstraints gc;
	Socket socket;

	public StudentInfoLayout(String[] infoArray, Socket socket) {

		this.socket = socket;

		Dimension size = getPreferredSize();
		size.width = 450;
		setPreferredSize(size);

		setBorder(BorderFactory.createTitledBorder("Информация за студента"));

		setBackground(Color.decode("#9fda93"));

		// setting the layout
		setLayout(new GridBagLayout());

		gc = new GridBagConstraints();
		// creating components
		JLabel firstNameLabel = new JLabel("Собствено име: ");
		JLabel firstName = new InfoLabels(infoArray[0]);
		JLabel lastNameLabel = new JLabel("Фамилия: ");
		JLabel lastName = new InfoLabels(infoArray[1]);
		JLabel fnumLabel = new JLabel("Факултетен номер: ");
		JLabel fnum = new InfoLabels(infoArray[2]);
		JLabel egnLabel = new JLabel("ЕГН: ");
		JLabel egn = new InfoLabels(infoArray[3]);
		JLabel uniGroupLabel = new JLabel("Група: ");
		JLabel uniGroup = new InfoLabels(infoArray[4]);
		JLabel majorLabel = new JLabel("Специалност: ");
		JLabel major = new InfoLabels(infoArray[5]);
		JLabel semesterLabel = new JLabel("Семестър: ");
		JLabel semester = new InfoLabels(infoArray[6]);
		JLabel studentTypeLabel = new JLabel("Степен на обучение: ");
		JLabel studentType = new InfoLabels(infoArray[7]);

		JButton editButton = new JButton("Редактирай профила");
		editButton.addActionListener(this);

		major.setBorder(BorderFactory.createSoftBevelBorder(2));

		// // first column ////
		gc.weightx = 1;
		gc.weighty = 1;

		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 0;
		gc.gridy = 0;
		add(firstNameLabel, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		add(lastNameLabel, gc);

		gc.gridx = 0;
		gc.gridy = 2;
		add(egnLabel, gc);

		gc.gridx = 0;
		gc.gridy = 3;
		add(fnumLabel, gc);

		gc.gridx = 0;
		gc.gridy = 4;
		add(uniGroupLabel, gc);

		gc.gridx = 0;
		gc.gridy = 5;
		add(majorLabel, gc);

		gc.gridx = 0;
		gc.gridy = 6;
		add(semesterLabel, gc);

		gc.gridx = 0;
		gc.gridy = 7;
		add(studentTypeLabel, gc);

		// // second column ////
		gc.weightx = 1;
		gc.weighty = 1;

		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 1;
		gc.gridy = 0;
		add(firstName, gc);

		gc.gridx = 1;
		gc.gridy = 1;
		add(lastName, gc);

		gc.gridx = 1;
		gc.gridy = 2;
		add(egn, gc);

		gc.gridx = 1;
		gc.gridy = 3;
		add(fnum, gc);

		gc.gridx = 1;
		gc.gridy = 4;
		add(uniGroup, gc);

		gc.gridx = 1;
		gc.gridy = 5;
		add(major, gc);

		gc.gridx = 1;
		gc.gridy = 6;
		add(semester, gc);

		gc.gridx = 1;
		gc.gridy = 7;
		add(studentType, gc);

		// // last row ////
		gc.weighty = 16;
		gc.anchor = GridBagConstraints.LAST_LINE_END;

		gc.gridx = 0;
		gc.gridy = 8;
		add(editButton, gc);

		gc.anchor = GridBagConstraints.LAST_LINE_START;

		gc.gridx = 1;
		gc.gridy = 8;
	}

	/**
	 * Action listener which load the Edit Profile panel.
	 * 
	 * @return void
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		removeAll();
		EditPanel edit = new EditPanel(socket);

		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 0;
		gc.gridy = 0;
		add(edit, gc);
	}

}
