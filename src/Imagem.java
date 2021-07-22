import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Imagem {
	private BufferedImage imgOriginal = null; // vari�vel para receber a imagem a ser aberta.
	private BufferedImage imgEditada = null; // vari�vel para receber a imagem a ser salva ap�s edi��es.

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

	public void abrirImg() throws IOException { // precisa de corre��es!
		final JFileChooser selecao = new JFileChooser(); // criando um seletor de arquivos.
		// definindo um filtro para extens�o de arquivos que ser�o mostrado na tela de
		// sele��o.
		FileFilter filtro = new FileNameExtensionFilter("bmp", "jpg", "jpeg", "png", "gif");
		selecao.setAcceptAllFileFilterUsed(false); // limitando extens�es mostradas.
		selecao.addChoosableFileFilter(filtro); // definindo o filtro para mostrar apenas extens�es de imagens.
		int returnVal = selecao.showOpenDialog(null); // abrindo a janela para a sele��o de arquivos.

		if (returnVal == JFileChooser.APPROVE_OPTION) { // Caso a opera��o de sele��o seja conclu�da com sucesso.
			File arquivoImagem = selecao.getSelectedFile(); // guardando o arquivo selecionado pelo usu�rio.
			imgOriginal = ImageIO.read(arquivoImagem); // salvando a imagem para a edi��o.
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
					&& !nomeArquivo.endsWith(".gif")) {// Verificando se um extens�o de imagem foi definid pelo usu�rio.
				nomeArquivo += ".jpg";
				arquivoImagem = new File(nomeArquivo);// Adicionando a extens�o jpg ao arquivo.
			}

			ImageIO.write(imgEditada, "jpg", arquivoImagem); // salvando a imagem.
		} else
			return;
	}
}