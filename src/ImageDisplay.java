import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A simple display for images using JFrame.
 * 
 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
 */

public class ImageDisplay extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Panel panel = new Panel(this);
	private String title = "";

	ImageDisplay() {
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void setTitle(final String title) {
		this.title = title;
		super.setTitle(title);
	}

	public void appendTitle(final String title) {
		super.setTitle(this.title + title);
	}

	public void setImage(BufferedImage img) {
		panel.setImage(img);
		this.setVisible(true);
	}

	public class Panel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private BufferedImage image = null;
		private ImageDisplay frame = null;

		Panel(ImageDisplay frame) {
			this.frame = frame;
		}

		public void setImage(BufferedImage img) {
			this.image = img;
			this.frame.appendTitle(" (" + img.getWidth() + " x " + img.getHeight() + ")");
			if (this.getGraphics() != null)
				this.paintComponent(this.getGraphics());
			frame.setSize(image.getWidth(), image.getHeight());
			this.setVisible(true);
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			float rX = (frame.getWidth() - 18) / (float) image.getWidth(),
					rY = (frame.getHeight() - 40) / (float) image.getHeight();
			g2.scale(rX, rY);
			super.paintComponent(g2);
			g2.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters
		}
	}

}