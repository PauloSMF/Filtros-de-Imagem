import java.awt.Color;

public class FiltroConvolucao extends Filtro {
	private int checarValor(double valor) { // Caso o valor do pixel n�o esteja entre 0 e 255 ap�s as opera��es
											// realizadas.
		if (valor < 0)
			return 0;
		else if (valor > 255)
			return 255;
		else
			return (int) valor;
	}

	// Convolu��o nos componentes RGB.
	private double Convolucao(double[][] kernel, int x, int y, double novoValor, int opcao) {
		for (int i = 0; i < kernel.length; i++) {// Posicionando a m�scara.
			for (int j = 0; j < kernel.length; j++) {
				guardarRGB(x - (kernel.length / 2) + i, y - (kernel.length / 2) + j);
				// Realizando a convolu��o dada pela sobreposi��o da matriz de convolu��o com a
				// imagem e
				// realizando um somat�rio entre a multiplica��o dos elementos sobrepostos,
				// alterando os valores de RGB.
				if (opcao == 1)
					novoValor += vermelho * kernel[i][j];
				else if (opcao == 2)
					novoValor += verde * kernel[i][j];
				else
					novoValor += azul * kernel[i][j];
			}
		}
		return novoValor;
	}

	public void ConvMagnitude(double kernelX[][], double kernelY[][]) {// M�todo que inclui toda opera��o de detec��o de
		// bordas.
		for (int x = (kernelX.length / 2); x < img.getWidth() - (kernelX.length / 2); x++) { // Percorrendo a imagem.
			for (int y = (kernelX.length / 2); y < img.getHeight() - (kernelX.length / 2); y++) {
				double brilhoX = 0, brilhoY = 0, auxiliar = 0;
				// Obtendo o brilho ou intensidade do pixel para o c�lculo da magnitude.
				brilhoX = (Convolucao(kernelX, x, y, auxiliar, 1) + Convolucao(kernelX, x, y, auxiliar, 2)
						+ Convolucao(kernelX, x, y, auxiliar, 3)) / 3;
				brilhoY = (Convolucao(kernelY, x, y, auxiliar, 1) + Convolucao(kernelY, x, y, auxiliar, 2)
						+ Convolucao(kernelY, x, y, auxiliar, 3)) / 3;

				// Obtendo a magnitude do valor obtido na convolu��o, dado pela raiz da soma dos
				// quadrados dos valores no eixo x e y.
				int magnitude = checarValor((int) Math.sqrt(brilhoX * brilhoX + brilhoY * brilhoY));
				Color novaCor = new Color(magnitude, magnitude, magnitude);
				// Definindo o novo valor do pixel em termos da magnitude.
				img.setRGB(x - (kernelX.length / 2), y - (kernelX.length / 2), novaCor.getRGB());
			}
		}
	}

	public void ConvNormal(double kernel[][], double fator) {// M�todo para a realiza��o do desfoque.
		// Nesta opera��o, geralmente o kernel � dividido por um fator para que o
		// resultado seja obtido como esperado.
		for (int x = (kernel.length / 2); x < img.getWidth() - (kernel.length / 2); x++) { // Percorrendo a imagem.
			for (int y = (kernel.length / 2); y < img.getHeight() - (kernel.length / 2); y++) {
				double auxiliar = 0;
				// Realizando a convolu��o e gerando o novo valor para o pixel.
				Color novaCor = new Color(checarValor(Convolucao(kernel, x, y, auxiliar, 1) / fator),
						checarValor(Convolucao(kernel, x, y, auxiliar, 2) / fator),
						checarValor(Convolucao(kernel, x, y, auxiliar, 3) / fator));
				// Definindo o novo valor do pixel.
				img.setRGB(x - (kernel.length / 2), y - (kernel.length / 2), novaCor.getRGB());
			}
		}
	}
}
