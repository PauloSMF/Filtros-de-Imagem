import java.awt.Color;

public class FiltroPixelAPixel extends Filtro {

	// Filtro que realiza a operação de inverter as cores em uma imagem,
	// simplesmente imagem negativa.
	public void inversor() {
		int largura = img.getWidth(); // Guardando a largura e a altura de uma imagem para ser varrida posteriormente.
		int altura = img.getHeight();

		for (int x = 0; x < largura; x++) { // varrendo os píxels da imagem.
			for (int y = 0; y < altura; y++) {
				guardarRGB(x, y);
				vermelho = 255 - vermelho;
				verde = 255 - verde;
				azul = 255 - azul;
				Color novaCor = new Color(vermelho, verde, azul);
				img.setRGB(x, y, novaCor.getRGB()); // mudando a cor do píxel de coordenadas x,y.
			}
		}
	}

	// No filtro de solarização caso os valores de um dos componentes RGB de um
	// pixel é maior que um determinado limite, este valor é invertido.
	public void solarizacao(int limite) {
		int largura = img.getWidth(); // Guardando a largura e a altura de uma imagem para ser varrida posteriormente.
		int altura = img.getHeight();

		for (int x = 0; x < largura; x++) { // varrendo os píxels da imagem.
			for (int y = 0; y < altura; y++) {
				guardarRGB(x, y);
				if (vermelho >= limite)
					vermelho = 255 - vermelho;
				if (verde >= limite)
					verde = 255 - verde;
				if (azul >= limite)
					azul = 255 - azul;

				Color novaCor = new Color(vermelho, verde, azul);
				img.setRGB(x, y, novaCor.getRGB()); // mudando a cor do píxel de coordenadas x,y.
			}
		}
	}

	// Filtro que realiza a operação de limiarização, basicamente caso o valor do
	// pixel é menor que o limite será substituido por 0, do contrário, por 255.
	public void limiarizacao(int limite) {
		int largura = img.getWidth(); // Guardando a largura e a altura de uma imagem para ser varrida posteriormente.
		int altura = img.getHeight();

		for (int x = 0; x < largura; x++) { // varrendo os píxels da imagem.
			for (int y = 0; y < altura; y++) {
				int brilho = obterBrilho(x, y);

				if (brilho >= limite) {
					// mudando a cor do píxel de coordenadas x,y para branco caso a intensidade seja
					// maior que o limite.
					Color novaCor = new Color(255, 255, 255);
					img.setRGB(x, y, novaCor.getRGB());
				} else
					// mudando a cor do píxel de coordenadas x,y para preto caso a intensidade seja
					// menor que o limite.
					img.setRGB(x, y, 0);
			}
		}
	}

	public void tonsDeCinza() { // Filtro que realiza a operação de colocar imagens em tons de cinza.
		int largura = img.getWidth(); // Guardando a largura e a altura de uma imagem para ser varrida posteriormente.
		int altura = img.getHeight();

		for (int x = 0; x < largura; x++) { // varrendo os píxels da imagem.
			for (int y = 0; y < altura; y++) {
				int brilho = obterBrilho(x, y);
				Color novaCor = new Color(brilho, brilho, brilho);
				img.setRGB(x, y, novaCor.getRGB()); // mudando a cor do píxel de coordenadas x,y.
			}
		}
	}
}
