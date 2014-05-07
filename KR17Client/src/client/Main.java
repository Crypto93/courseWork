package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * A class representing a client application for monitoring of students' status.
 * <p>
 * 
 * @author Stefan Chuklev
 * 
 */
public class Main {

	public static void main(String[] args) {

		Socket socket;
		try {
			socket = new Socket("localhost", 1212);
			Scanner read = new Scanner(socket.getInputStream());
			PrintStream ps = new PrintStream(socket.getOutputStream());

			ps.println("Client is connected at port: " + socket.getPort());

			MainFrameLayout frame = new MainFrameLayout("Student's references",
					socket);

			DataProcessing dataProcessing = new DataProcessing(socket);

			boolean status = false;

			while (!status) {

				status = dataProcessing.logIn();

				if (status == true) {
					frame.successfullLogIn(dataProcessing.studentData,
							dataProcessing.gradesData, dataProcessing.semesters);

				} else {
					Errors.logInError();
				}
			}

		} catch (IOException e) {
			Errors.dbError();
		}

	}

}
