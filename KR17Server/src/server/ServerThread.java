package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class representing a server thread which provide connection to the database
 * for the clients and process their requests.
 * 
 * @param socket
 *            Established connection to the client application
 * 
 * @author Stefan Chuklev
 * 
 */

public class ServerThread extends Thread {

	private Socket mSocket;
	private PrintStream ps;
	private Scanner scan;
	private Statement stmt;

	private int option;
	private String username;
	private String password;
	private int stud_id;
	private int student_type;

	private final String DB_URL = "jdbc:mysql://localhost:3306/";
	private final String DB_NAME = "kr17_test";
	private final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_USER = "student";
	private final String DB_PASS = "student123";
	private final String DB_ENCODING = "?characterEncoding=UTF-8";

	public ServerThread(Socket socket) throws IOException {
		this.mSocket = socket;
		ps = new PrintStream(mSocket.getOutputStream());
		scan = new Scanner(mSocket.getInputStream());

		try {
			Class.forName(DB_DRIVER).newInstance();
			Connection conn = DriverManager.getConnection(DB_URL + DB_NAME
					+ DB_ENCODING, DB_USER, DB_PASS);

			stmt = conn.createStatement();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			distributor();
		}
	}

	/**
	 * This method waits requests from the client program, process the data and
	 * according to the content distributes it to the other methods.
	 * 
	 * @return void
	 * 
	 */

	private void distributor() {
		String distributingData = null;

		distributingData = scan.nextLine();

		Pattern pattern = Pattern.compile("(\\S+)[#]+(\\S+)[$]+(\\S+)");
		Matcher matcher = pattern.matcher(distributingData);

		while (matcher.find()) {
			option = Integer.parseInt(matcher.group(1));
			username = matcher.group(2);
			password = matcher.group(3);
		}

		switch (option) {
		case 1:
			logIn(username, password);
			break;

		case 2:
			changePassword(username, password);
			break;

		default:
			break;
		}

	}

	/**
	 * This method check if the username and password match some of the database
	 * records.
	 * <p>
	 * 
	 * @param username
	 * @param password
	 * @return void
	 * 
	 */
	private void logIn(String username, String password) {

		try {
			ResultSet res = stmt
					.executeQuery("SELECT stud_id FROM users WHERE username='"
							+ username + "' AND password='" + password + "'");

			while (res.next()) {
				stud_id = res.getInt(1);
			}

			if (stud_id == 0) {
				ps.println("error#");
			} else {
				studentInfo();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method fetch the information for the student with specified id from
	 * database and sends it to the client in form of specially formated string.
	 * <p>
	 * 
	 * @return void
	 * 
	 */
	private void studentInfo() {

		StringBuilder sb = new StringBuilder();

		ResultSet res;
		try {
			res = stmt
					.executeQuery("SELECT first_name, last_name, fnum, EGN, uni_group, major_name, semester, type "
							+ "FROM students LEFT JOIN majors ON major=major_id WHERE id='"
							+ stud_id + "'");
			while (res.next()) {
				for (int i = 1; i <= 8; i++) {
					sb.append(res.getString(i) + "#");
					if (i == 8) {
						student_type = Integer.parseInt(res.getString(i));
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ps.println(sb.toString());
		ps.flush();

		tableInfo();
	}

	/**
	 * This method fetch the information for the student's subjects and grades,
	 * put them into a specifically formated string and send them to the client.
	 * <p>
	 * 
	 * @return void
	 * 
	 */
	private void tableInfo() {

		StringBuilder sb = new StringBuilder();
		ResultSet res;

		try {
			int temp = 0;

			if (student_type == 0) {
				temp = 9;
			} else if (student_type == 1) {
				temp = 4;
			}

			for (int i = 1; i < temp; i++) {
				res = stmt
						.executeQuery("select t1.sub_name, t1.sem, t2.grade FROM "
								+ "(SELECT subjects.sub_id as ID, subject_name as sub_name, semester as sem FROM curriculum "
								+ "LEFT JOIN subjects ON curriculum.sub_id=subjects.sub_id "
								+ "WHERE curriculum.major_id=(SELECT major FROM students WHERE id="
								+ stud_id
								+ ")) AS t1 "
								+ "LEFT JOIN "
								+ "(SELECT subjects.sub_id as ID, subject_name as sub_name, grade FROM grades "
								+ "LEFT JOIN subjects ON grades.subject_id=subjects.sub_id WHERE student_id="
								+ stud_id
								+ ") AS t2"
								+ " ON t1.ID=t2.ID where t1.sem=" + i);

				while (res.next()) {
					sb.append(res.getString(1) + "#" + res.getString(2) + "#"
							+ res.getByte(3) + "@");
				}
			}

			ps.println(sb.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method update username and password information for the student with
	 * specified ID into users table from the database.
	 * <p>
	 * 
	 * @param username
	 * @param password
	 * @return void
	 * 
	 */
	private void changePassword(String username, String password) {
		try {
			stmt.executeUpdate("UPDATE users SET username ='" + username
					+ "' ,password ='" + password + "' WHERE stud_id='"
					+ stud_id + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
