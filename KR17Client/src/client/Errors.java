package client;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Class Errors contain the possible exceptions that can occur in the clients
 * API.
 * 
 * @param socket
 *            Established connection to the server application
 * 
 * @author Stefan Chuklev
 * 
 */
public class Errors extends Exception {

	/**
	 * Reports a problem with connection to the server.
	 * 
	 * @return void
	 * 
	 */
	public static void dbError() {
		JOptionPane.showMessageDialog(new JFrame(),
				"Unable to connect datababase", "Database Error",
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Reports a problem with LogIn information.
	 * 
	 * @return void
	 * 
	 */
	public static void logInError() {
		JOptionPane.showMessageDialog(new JFrame(),
				"Wrong username or password", "LogIn Error",
				JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Reports that user's password have been changed.
	 * 
	 * @return void
	 * 
	 */
	public static void passwordChanged() {
		JOptionPane.showMessageDialog(new JFrame(),
				"Паролата и потребителското ви име бяха сменени успешно.",
				"Password Change", JOptionPane.INFORMATION_MESSAGE);
	}

}
