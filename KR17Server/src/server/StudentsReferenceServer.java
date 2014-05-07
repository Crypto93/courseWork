package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A class representing a multithread server for monitoring of students' status.
 * <p>
 * 
 * @author Stefan Chuklev
 * 
 */

public class StudentsReferenceServer {

	public static void main(String[] args) {

		MainFrame mainFrame = new MainFrame("Student's Reference Server");

		threadLoader();
	}

	public static void threadLoader() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(1212);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			try {
				Socket socket = serverSocket.accept();
				ServerThread thread = new ServerThread(socket);
				thread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
