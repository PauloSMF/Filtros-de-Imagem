import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Imagem {
	private BufferedImage imgOriginal = null; // variável para receber a imagem a ser aberta.
	private BufferedImage imgEditada = null; // variável para receber a imagem a ser salva após edições.

	public BufferedImage getimgOriginal() {
		return imgOriginal;
	}

	public void setimgOriginal(BufferedImage imgOriginal) {
		this.imgOriginal = imgOriginal;
	}

	public BufferedImage getimgEditada() {
		return imgEditada;
	}

	public void setimgEditada(BufferedImage imgEditada) {
		this.imgEditada = imgEditada;
	}

	public void abrirImg() throws IOException { // precisa de correções!
		final JFileChooser selecao = new JFileChooser(); // criando um seletor de arquivos.
		// definindo um filtro para extensão de arquivos que serão mostrado na tela de
		// seleção.
		FileFilter filtro = new FileNameExtensionFilter("bmp", "jpg", "jpeg", "png", "gif");
		selecao.setAcceptAllFileFilterUsed(false); // limitando extensões mostradas.
		selecao.addChoosableFileFilter(filtro); // definindo o filtro para mostrar apenas extensões de imagens.
		int returnVal = selecao.showOpenDialog(null); // abrindo a janela para a seleção de arquivos.

		if (returnVal == JFileChooser.APPROVE_OPTION) { // Caso a operação de seleção seja concluída com sucesso.
			File arquivoImagem = selecao.getSelectedFile(); // guardando o arquivo selecionado pelo usuário.
			imgOriginal = ImageIO.read(arquivoImagem); // salvando a imagem para a edição.
		} else
			return;
	}

	public void salvarImg() throws IOException {
		final JFileChooser selecao = new JFileChooser(); // criando um selecao de arquivos.
		int returnVal = selecao.showSaveDialog(null); // abrindo a janela para a imagem ser salva.

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File arquivoImagem = selecao.getSelectedFile();
			String nomeArquivo = arquivoImagem.toString();

			if (!nomeArquivo.endsWith(".jpg") && !nomeArquivo.endsWith(".jpeg") && !nomeArquivo.endsWith(".png")
					&& !nomeArquivo.endsWith(".gif")) {// Verificando se um extensão de imagem foi definid pelo usuário.
				nomeArquivo += ".jpg";
				arquivoImagem = new File(nomeArquivo);// Adicionando a extensão jpg ao arquivo.
			}

			ImageIO.write(imgEditada, "jpg", arquivoImagem); // salvando a imagem.
		} else
			return;
	}
}