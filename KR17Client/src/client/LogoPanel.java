package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * A class which add logo part from API's GUI.
 * 
 * @param socket
 *            Established connection to the server application
 * 
 * @author Stefan Chuklev
 * 
 */
public class LogoPanel extends JPanel {

	private BufferedImage image;

	public LogoPanel() {

		Dimension size = getPreferredSize();
		size.width = 650;
		setPreferredSize(size);

		setLayout(new BorderLayout());

		setBorder(BorderFactory.createTitledBorder("Grades info"));

		setBackground(Color.decode("#ffffff"));

		try {
			image = ImageIO.read(new File("D:/BD/logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int x = (this.getWidth() - image.getWidth(null)) / 2;
		int y = (this.getHeight() - image.getHeight(null)) / 2;

		g.drawImage(image, x, y, null);
	}

}
