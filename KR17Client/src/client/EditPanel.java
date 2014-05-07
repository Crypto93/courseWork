package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * A class which creates the Edit Profile part from API's GUI.
 * 
 * @param socket
 *            Established connection to the server application
 * 
 * @author Stefan Chuklev
 * 
 */
public class EditPanel extends JPanel implements ActionListener {

	PrintStream ps;

	JTextField userField;
	JPasswordField passField;
	JPasswordField passCheckField;
	GridBagConstraints gc;
	JLabel check = new JLabel();

	public EditPanel(Socket socket) {

		try {
			ps = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		Dimension size = getPreferredSize();
		size.width = 300;
		size.height = 200;
		setPreferredSize(size);

		setBorder(BorderFactory.createTitledBorder("Редактиране на профил"));

		setBackground(Color.decode("#c8ddf2"));

		// setting the layout
		setLayout(new GridBagLayout());

		gc = new GridBagConstraints();

		JLabel user = new JLabel("Ново потребителско име: ");
		JLabel pass = new JLabel("Нова парола: ");
		JLabel passCheckser = new JLabel("Повторете паролата: ");

		userField = new JTextField(10);
		passField = new JPasswordField(10);
		passCheckField = new JPasswordField(10);

		JButton confirm = new JButton("Потвърди");
		confirm.addActionListener(this);

		// // first column ////
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 0;
		gc.gridy = 0;
		add(user, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		add(pass, gc);

		gc.gridx = 0;
		gc.gridy = 2;
		add(passCheckser, gc);

		// // second column ////
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 0;
		add(userField, gc);

		gc.gridx = 1;
		gc.gridy = 1;
		add(passField, gc);

		gc.gridx = 1;
		gc.gridy = 2;
		add(passCheckField, gc);

		// // last row ////
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 3;
		add(confirm, gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 0;
		gc.gridy = 3;
		add(check, gc);

	}

	/**
	 * This action listener validate the input of username and password
	 * (username>5 symbols and passwords have to match each other) and sends it
	 * to the server into a specially formated string.
	 * 
	 * @return void
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (java.util.Arrays.equals(passField.getPassword(),
				passCheckField.getPassword())
				&& userField.getText().length() > 5) {
			System.out.println("2#" + userField.getText()
					+ new String(passField.getPassword()));
			ps.println("2#" + userField.getText() + "$"
					+ new String(passField.getPassword()));
			ps.flush();

			Errors.passwordChanged();

		} else {
			check.setText("");
			check.setText("<html><font color='red'>невалидни данни</font></html>");
		}

	}
}
