package chaos;

import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author David
 */
public class ImgPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon imagen;
	URL url;

	public ImgPanel(URL url) {
		super();
		this.url = url;
	}

	public void paint(Graphics g) {
		Dimension size = getSize();
		if (url != null) {
			imagen = new ImageIcon(url);
			if (imagen.getImage() != null) {
				g.drawImage(imagen.getImage(), 0, 0, size.width, size.height, null);
				setOpaque(false);
				super.paint(g);
			}
		}
	}
}