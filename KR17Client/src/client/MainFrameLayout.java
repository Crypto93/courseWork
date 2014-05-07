package client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

/**
 * This creates and adds all the elements to the Main frame of the API's GUI..
 * 
 * @param title
 *            the title of the main window
 * @param socket
 *            Established connection to the server application
 * 
 * @author Stefan Chuklev
 * 
 */
public class MainFrameLayout extends JFrame {

	StudentInfoLayout studentPanel;
	GradesInfo gradesInfo;
	LoginDetailLayout logInPanel;
	LogoPanel logoPanel;

	Socket socket;

	Container c;

	public MainFrameLayout(String title, Socket socket) throws IOException {

		super(title);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage("D:/BD/logo.png");
		setIconImage(img);

		// thread that periodically repaint and validate GUI's elements
		Thread repainter = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					repaint();
					validate();
					try {
						Thread.sleep(5);
					} catch (InterruptedException ignored) {
					}
				}
			}
		});

		repainter.setPriority(Thread.MIN_PRIORITY);
		repainter.start();

		this.socket = socket;

		setSize(1350, 600);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// / setting main layout
		setLayout(new BorderLayout());

		// / creating content pane
		c = getContentPane();

		logInPanel = new LoginDetailLayout(socket);
		logoPanel = new LogoPanel();

		c.add(logInPanel, BorderLayout.WEST);
		c.add(logoPanel, BorderLayout.CENTER);

	}

	/**
	 * The method remove all the existing panels in the API's GUI and creates
	 * new ones with student information from the database.
	 * 
	 * @return void
	 * 
	 */
	public void successfullLogIn(String[] infoArray, String[][] gradesData,
			int numberOfsemesters) {
		this.studentPanel = new StudentInfoLayout(infoArray, socket);
		this.gradesInfo = new GradesInfo(gradesData, numberOfsemesters);
		this.c.removeAll();
		this.c.add(this.studentPanel, BorderLayout.WEST);
		this.c.add(this.gradesInfo, BorderLayout.CENTER);
	}

}
