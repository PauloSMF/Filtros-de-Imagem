import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Filtro {
	protected BufferedImage img;
	protected int vermelho;
	protected int verde;
	protected int azul;

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage _img) { // Clonando a imagem recebida para que a original não sofra alterações.
		BufferedImage aux = new BufferedImage(_img.getWidth(), _img.getHeight(), _img.getType());
		Graphics g = aux.getGraphics();
		g.drawImage(_img, 0, 0, null);
		g.dispose();
		img = aux;
	}

	protected void guardarRGB(int x, int y) { // Guardando os valores de RGB
		Color pixel = new Color(img.getRGB(x, y));
		vermelho = pixel.getRed();
		verde = pixel.getGreen();
		azul = pixel.getBlue();
	}

	protected int obterBrilho(int x, int y) {// Calculando o brilho ou intesidade do pixel.
		guardarRGB(x, y);
		int brilho = (vermelho + verde + azul) / 3; // calculando a média entre os valores de RGB para conseguir
													// o brilho, ou intensidade.
		return brilho;
	}
}
