package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This creates and adds all the elements to the Main frame of the server API's
 * GUI..
 * 
 * @author Stefan Chuklev
 * 
 */
public class MainFrame extends JFrame implements ActionListener {

	private JButton stopButton;
	private JLabel label;

	private BufferedImage image;

	public MainFrame(String title) {
		
		super(title);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage("D:/BD/logo.png");
		setIconImage(img);

		setSize(600, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		setLayout(new BorderLayout());

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

		stopButton = new JButton("Stop server");
		stopButton.addActionListener(this);

		label = new JLabel();
		label.setText("<html><center><font color='green' size='10'>Student Reference Server is started !</font></center></html>");

		add(label, BorderLayout.CENTER);
		add(stopButton, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
