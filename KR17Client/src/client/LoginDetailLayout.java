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
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Class creates and generate a swing panel with student information fetched
 * from the database.
 * 
 * @param socket
 *            Established connection to the server application
 * 
 * @author Stefan Chuklev
 * 
 */
public class LoginDetailLayout extends JPanel implements ActionListener {

	Socket socket;

	JTextField usernameField;
	JPasswordField passwordField;

	Scanner read;
	PrintStream ps;

	public LoginDetailLayout(Socket socket) throws IOException {

		this.socket = socket;
		read = new Scanner(this.socket.getInputStream());
		ps = new PrintStream(socket.getOutputStream());

		Dimension size = getPreferredSize();
		size.width = 450;
		setPreferredSize(size);

		setBorder(BorderFactory.createTitledBorder("Log In information"));

		setBackground(Color.decode("#9fda93"));

		// creating components
		JLabel usernameLabel = new JLabel("Username: ");
		JLabel passwordLabel = new JLabel("Password: ");

		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);

		JButton loginButton = new JButton("Log in");

		loginButton.addActionListener(this);

		// / setting the layout
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// /// first column /////
		gc.anchor = GridBagConstraints.LINE_END;
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.gridy = 0;
		add(usernameLabel, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		add(passwordLabel, gc);

		gc.gridx = 0;
		gc.gridy = 2;
		add(loginButton, gc);

		// // second column ////
		gc.anchor = GridBagConstraints.LINE_START;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 1;
		gc.gridy = 0;
		add(usernameField, gc);

		gc.gridx = 1;
		gc.gridy = 1;
		add(passwordField, gc);

		// // last row ////
		gc.weighty = 10;

		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 2;
		add(loginButton, gc);

	}

	/**
	 * Action listener which send the user's LogIn information to the server.
	 * 
	 * @return void
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ps.println("1#" + usernameField.getText() + "$"
				+ new String(passwordField.getPassword()));
		ps.flush();
	}

}
