package client;

import java.awt.Font;

import javax.swing.JLabel;

/**
 * Child class of JLabel with specified text stylization. (Font 12px, Itallic)
 * <p>
 * 
 * @author Stefan Chuklev
 * 
 */
public class InfoLabels extends JLabel {

	@Override
	public void setFont(Font font) {
		super.setFont(new Font(null, 3, 12));
	}

	public InfoLabels(String text) {
		super(text);
	}

}
