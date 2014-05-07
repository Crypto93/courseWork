package client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class which accept information sent by the server, process it in
 * appropriate way and distribute it to the other application classes.
 * <p>
 * 
 * @param socket
 *           the connection socket to the server.
 * 
 * @author Stefan Chuklev
 * 
 */

public class DataProcessing {

	Socket socket;
	Scanner scan;

	String data;
	String[] studentData = new String[8];
	String[][] gradesData;

	int semesters;

	public DataProcessing(Socket socket) throws IOException {
		this.socket = socket;
		scan = new Scanner(socket.getInputStream());
	}
	

	/**
	 * This method wait response from the server if username and password match
	 * one of the database records.
	 * 
	 * @return If true student is logged in, else log in error
	 * 
	 */
	public boolean logIn() {

		data = scan.nextLine();

		if (data.equals("error#")) {
			return false;
		}

		fetching(data);
		return true;

	}

	/**
	 * Get specially formated string with student's data and process it to an
	 * array, check the type of the student and get a specially formated string
	 * with student's subjects and grades and also turns it into an array.
	 * 
	 * @param information
	 *            that has been collected from the server
	 * @return If true student is logged in, else log in error
	 * 
	 */
	public void fetching(String data) {

		int i = 0;
		for (String string : data.split("#")) {
			studentData[i] = string;
			i++;
		}

		// Check the type of the student and determine the number of semesters
		// which is used for the the length of gradesData array.
		if (Integer.parseInt(studentData[7]) == 0) {
			studentData[7] = "Бакалавър";
			semesters = 8;
		} else if (Integer.parseInt(studentData[7]) == 1) {
			studentData[7] = "Магистър";
			semesters = 3;
		}

		data = scan.nextLine();

		// Counting the number of subjects for every single semester
		Pattern pattern = Pattern.compile("[@]");
		Matcher matcher = pattern.matcher(data);

		int counter = 0;

		while (matcher.find()) {
			counter++;
		}

		// Processing the grades data and put it into two dimensional array.
		gradesData = new String[counter][3];

		Pattern p = Pattern.compile("(?=[^@])(.+?)#(\\d)#(\\d)(?=@)");
		Matcher m = p.matcher(data);

		counter = 0;

		while (m.find()) {
			if (m.groupCount() == 3) {
				gradesData[counter][0] = m.group(1);
				gradesData[counter][1] = m.group(2);
				gradesData[counter][2] = m.group(3);

				counter++;
			}
		}
	}

}
