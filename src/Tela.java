import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Tela extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Action action = new SwingAction();
	private final JLayeredPane layeredPane = new JLayeredPane();// O programa conta com três diferentes telas.
	private JTextField txtFiltros;
	private JTextField txtPrevia;
	private JTextField txtOriginal;
	private JTextField txtEditada;
	private BufferedImage imgAuxiliar;// Auxiliar para guardar as imagens editadas, para que guarde antes do usuário
										// aceitar as alterações.
	private JTextField txtFiltrosDeImagens;
	private JTextField txtSelecioneAMscara;
	private JTextField txtEscolhaOTamando;
	private JTextField textField1x1;
	private JTextField textField1x2;
	private JTextField textField1x3;
	private JTextField textField2x1;
	private JTextField textField2x2;
	private JTextField textField2x3;
	private JTextField textField3x1;
	private JTextField textField3x2;
	private JTextField textField3x3;
	private int tamanhoMatriz = 3;// Variável para checar o tamanho da matriz personalizável.
	private JTextField textField1x4;
	private JTextField textField1x5;
	private JTextField textField2x4;
	private JTextField textField2x5;
	private JTextField textField3x4;
	private JTextField textField3x5;
	private JTextField textField4x1;
	private JTextField textField5x1;
	private JTextField textField4x2;
	private JTextField textField5x2;
	private JTextField textField4x3;
	private JTextField textField5x3;
	private JTextField textField4x4;
	private JTextField textField5x4;
	private JTextField textField5x5;
	private JTextField textField4x5;
	private JTextField txtEscolhaOMtodo;
	private JTextField txtSelecioneOLimite;
	private boolean SolOuLim; // Variável em que é true caso o botão de solarização seja pressionado e false
								// caso o de limiarização seja pressionado.

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void TrocarTela(JPanel panel) {// Função que realiza a troca entre as telas do programa.
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.revalidate();
		layeredPane.repaint();
	}

	private void pintarLabel(BufferedImage imagem, JLabel label) {// Função para pintar labels com imagens.
		if (imagem != null) {
			ImageIcon icon = new ImageIcon(imagem);
			Image img = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT);
			label.setIcon(new ImageIcon(img));
		}
	}

	private double[][] criarMatriz(int tamanho) {// Função para criar a matriz personalizável.
		if (tamanho == 3) {
			double[][] kernel = {
					{ Double.parseDouble(textField1x1.getText()), Double.parseDouble(textField1x2.getText()),
							Double.parseDouble(textField1x3.getText()) },
					{ Double.parseDouble(textField2x1.getText()), Double.parseDouble(textField2x2.getText()),
							Double.parseDouble(textField2x3.getText()) },
					{ Double.parseDouble(textField3x1.getText()), Double.parseDouble(textField3x2.getText()),
							Double.parseDouble(textField3x3.getText()) } };

			return kernel;
		} else {
			double[][] kernel = {
					{ Double.parseDouble(textField1x1.getText()), Double.parseDouble(textField1x2.getText()),
							Double.parseDouble(textField1x3.getText()), Double.parseDouble(textField1x4.getText()),
							Double.parseDouble(textField1x5.getText()) },
					{ Double.parseDouble(textField2x1.getText()), Double.parseDouble(textField2x2.getText()),
							Double.parseDouble(textField2x3.getText()), Double.parseDouble(textField2x4.getText()),
							Double.parseDouble(textField2x5.getText()) },
					{ Double.parseDouble(textField3x1.getText()), Double.parseDouble(textField3x2.getText()),
							Double.parseDouble(textField3x3.getText()), Double.parseDouble(textField3x5.getText()),
							Double.parseDouble(textField3x5.getText()) },
					{ Double.parseDouble(textField4x1.getText()), Double.parseDouble(textField4x2.getText()),
							Double.parseDouble(textField4x3.getText()), Double.parseDouble(textField4x4.getText()),
							Double.parseDouble(textField4x5.getText()) },
					{ Double.parseDouble(textField5x1.getText()), Double.parseDouble(textField5x2.getText()),
							Double.parseDouble(textField5x3.getText()), Double.parseDouble(textField5x4.getText()),
							Double.parseDouble(textField5x5.getText()) } };

			return kernel;

		}
	}

	private double[][] transporKernel(double[][] kernel) {// Transpor matriz.
		double[][] transposta = new double[kernel[0].length][kernel.length];

		for (int i = 0; i < kernel.length; i++)
			for (int j = 0; j < kernel[0].length; j++)
				transposta[j][i] = kernel[i][j];

		return transposta;
	}

	/**
	 * Create the frame.
	 */
	public Tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(492, 500);// Definindo um tamanho para a tela do programa.
		setResizable(false);// Travando o tamanho do JFrame.
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		layeredPane.setBounds(0, 0, 484, 461);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		// Instaciando as telas.
		JPanel panel = new JPanel();// Tela principal.
		layeredPane.add(panel, "name_994002341818444");
		panel.setLayout(null);

		JPanel panelAuxiliar = new JPanel();// Tela auxiliar.
		layeredPane.add(panelAuxiliar, "name_994014984773359");
		panelAuxiliar.setLayout(null);

		JPanel panelMatriz = new JPanel();
		layeredPane.add(panelMatriz, "name_1029260449473753");
		panelMatriz.setLayout(null);

		// Instanciando as classes operadas.
		Imagem imagem = new Imagem();
		FiltroPixelAPixel fpixel = new FiltroPixelAPixel();
		FiltroConvolucao fconvolucao = new FiltroConvolucao();
		// Instanciando os botões.
		JButton btnAbrirImagem = new JButton("Abrir Imagem");
		JButton btnSalvar = new JButton("Salvar Imagem");
		JButton btnMostrar2 = new JButton("Mostrar\r\n");// Botão para mostrar imagem original presente na tela
		// principal.
		JButton btnFechar = new JButton("Fechar");
		JButton btnPersonalizar = new JButton("Personalizar");
		JButton btnTonsDeCinza = new JButton("Tons de Cinza");// Botão para aplicação do filtro de tons de cinza.
		JButton btnInversor = new JButton("Inverter Cores");// Botão para aplicação do filtro inversor.
		JButton btnSolarizacao = new JButton("Solariza\u00E7\u00E3o");// Botão para aplicação do filtro de solarização.
		JButton btnLimiarizacao = new JButton("Limiariza\u00E7\u00E3o");// Botão para aplicação do filtro de
		JButton btnBordas = new JButton("Bordas\r\n");// Filtragem de bordas.
		JButton btnDesfoque = new JButton("Desfoque");// Filtragem de desfoque.
		JButton btnAfiar = new JButton("Afiar");
		JButton btnRelevo = new JButton("Relevo");
		// Botões presentes na tela auxiliar.
		JLabel prevEditada = new JLabel();
		JLabel prevOriginal = new JLabel();
		JButton btnAceitar = new JButton("Aceitar");// Caso o usuário tenha gostado da última edição, poderá guardá-la.
		JButton btnDesfazer = new JButton("Desfazer/Voltar");// Caso o usuário não tenha gostado da última edição feita,
		// lhe é dado a opção de não salvá-la.
		JButton btnMostrar = new JButton("Mostrar");// Abrir a imagem em tamanho original.
		// Label que mostra uma prévia das imagens originais e editadas, comparando-as
		// lado a lado.
		// Botões da tela de edição da matriz de convolução.
		JButton btnTres = new JButton("3x3");
		JButton btnCinco = new JButton("5x5");
		JLabel label = new JLabel();

		// Definição do grupo dos botões Abrir, Salvar,Mostrar2 e Fechar.
		btnAbrirImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagem.abrirImg();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				imagem.setimgEditada(imagem.getimgOriginal());
				imgAuxiliar = imagem.getimgEditada();

				// Habilitando os botões de filtro, para que funcionem somente no caso de ter
				// uma imagem aberta, caso contrário, possíves erros ocorrerão.
				if (imagem.getimgOriginal() != null) {
					btnTonsDeCinza.setEnabled(true);
					btnInversor.setEnabled(true);
					btnSolarizacao.setEnabled(true);
					btnLimiarizacao.setEnabled(true);
					btnMostrar2.setEnabled(true);
					btnSalvar.setEnabled(true);
					btnBordas.setEnabled(true);
					btnDesfoque.setEnabled(true);
					btnAfiar.setEnabled(true);
					btnRelevo.setEnabled(true);
					btnPersonalizar.setEnabled(true);
				}
			}
		});
		btnAbrirImagem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAbrirImagem.setBounds(10, 50, 148, 50);
		panel.add(btnAbrirImagem);

		btnMostrar2.setEnabled(false);
		btnMostrar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Abrindo a imagem em tamanho original.
				ImageDisplay mostrar = new ImageDisplay();
				mostrar.setImage(imagem.getimgEditada());
			}
		});
		btnMostrar2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMostrar2.setBounds(168, 50, 148, 50);
		panel.add(btnMostrar2);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Salvando a imagem.
				try {
					imagem.salvarImg();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSalvar.setEnabled(false);
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSalvar.setBounds(326, 50, 148, 50);
		panel.add(btnSalvar);

		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnFechar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnFechar.setBounds(168, 355, 148, 50);
		panel.add(btnFechar);

		btnPersonalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Como o mesmo botão não pode ocupar diferentes telas ao mesmo tempo, é
				// removida da tela auxiliar e adicionada a tela de edição de matriz sempre que
				// o botão personalizar é pressionado.
				panelAuxiliar.remove(btnAceitar);
				panelAuxiliar.remove(btnDesfazer);
				panelAuxiliar.remove(btnMostrar);
				panelMatriz.add(btnAceitar);
				panelMatriz.add(btnDesfazer);
				panelMatriz.add(btnMostrar);

				pintarLabel(imgAuxiliar, label);
				TrocarTela(panelMatriz);
			}
		});
		btnPersonalizar.setEnabled(false);
		btnPersonalizar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPersonalizar.setBounds(168, 294, 148, 50);
		panel.add(btnPersonalizar);
		// Fim da definição do grupo.

		// Definição do grupo dos botões de filtragem pixel a pixel.
		btnTonsDeCinza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Definindo a filtragem a ser feita quando este botão é pressionado.
				fpixel.setImg(imagem.getimgEditada());
				fpixel.tonsDeCinza();
				imgAuxiliar = fpixel.getImg();

				pintarLabel(imgAuxiliar, prevEditada);
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
				TrocarTela(panelAuxiliar);
			}
		});
		btnTonsDeCinza.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTonsDeCinza.setEnabled(false);
		btnTonsDeCinza.setBounds(10, 172, 148, 50);
		panel.add(btnTonsDeCinza);

		btnInversor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Definindo a filtragem a ser feita quando este botão é pressionado.
				fpixel.setImg(imagem.getimgEditada());
				fpixel.inversor();
				imgAuxiliar = fpixel.getImg();

				pintarLabel(imgAuxiliar, prevEditada);
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
				TrocarTela(panelAuxiliar);
			}
		});
		btnInversor.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnInversor.setEnabled(false);
		btnInversor.setBounds(10, 233, 148, 50);
		panel.add(btnInversor);

		JButton btn75 = new JButton("75");
		btn75.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fpixel.setImg(imagem.getimgEditada());

				if (SolOuLim == true)
					fpixel.solarizacao(75);
				else
					fpixel.limiarizacao(75);

				imgAuxiliar = fpixel.getImg();
				pintarLabel(imgAuxiliar, prevEditada);
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
			}
		});
		btn75.setEnabled(false);
		btn75.setVisible(false);
		btn75.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn75.setBounds(10, 339, 148, 50);
		panelAuxiliar.add(btn75);

		JButton btn150 = new JButton("150");
		btn150.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fpixel.setImg(imagem.getimgEditada());

				if (SolOuLim == true)
					fpixel.solarizacao(150);
				else
					fpixel.limiarizacao(150);

				imgAuxiliar = fpixel.getImg();
				pintarLabel(imgAuxiliar, prevEditada);
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
			}
		});
		btn150.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn150.setEnabled(false);
		btn150.setVisible(false);
		btn150.setBounds(168, 339, 148, 50);
		panelAuxiliar.add(btn150);

		JButton btn225 = new JButton("225");
		btn225.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fpixel.setImg(imagem.getimgEditada());

				if (SolOuLim == true)
					fpixel.solarizacao(255);
				else
					fpixel.limiarizacao(255);

				imgAuxiliar = fpixel.getImg();
				pintarLabel(imgAuxiliar, prevEditada);
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
			}
		});
		btn225.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn225.setEnabled(false);
		btn225.setVisible(false);
		btn225.setBounds(324, 339, 148, 50);
		panelAuxiliar.add(btn225);

		btnSolarizacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Definindo a filtragem a ser feita quando este botão é pressionado.
				fpixel.setImg(imagem.getimgEditada());
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
				pintarLabel(imgAuxiliar, prevEditada);

				txtSelecioneOLimite.setVisible(true);
				btn75.setEnabled(true);
				btn150.setEnabled(true);
				btn225.setEnabled(true);
				btn75.setVisible(true);
				btn150.setVisible(true);
				btn225.setVisible(true);
				SolOuLim = true;
				TrocarTela(panelAuxiliar);
			}
		});
		btnSolarizacao.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSolarizacao.setEnabled(false);
		btnSolarizacao.setBounds(10, 294, 148, 50);
		panel.add(btnSolarizacao);

		btnLimiarizacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Definindo a filtragem a ser feita quando este botão é pressionado.
				fpixel.setImg(imagem.getimgEditada());
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
				pintarLabel(imgAuxiliar, prevEditada);

				txtSelecioneOLimite.setVisible(true);
				btn75.setEnabled(true);
				btn150.setEnabled(true);
				btn225.setEnabled(true);
				btn75.setVisible(true);
				btn150.setVisible(true);
				btn225.setVisible(true);
				SolOuLim = false;
				TrocarTela(panelAuxiliar);
			}
		});
		btnLimiarizacao.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLimiarizacao.setEnabled(false);
		btnLimiarizacao.setBounds(10, 355, 148, 50);
		panel.add(btnLimiarizacao);
		// Fim da definição do grupo.

		// Definição do grupo dos botões de filtragem por convolução.
		JButton btnPrewitt = new JButton("Prewitt");// Aplicar a máscara de Prewitt.
		btnPrewitt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Máscara para a detecção de bordas de Prewitt nos eixos x e y.
				final double[][] prewittX = { { -1, 0, 1 }, { 1, 0, -1 }, { -1, 0, 1 } };
				final double[][] prewittY = { { 1, 1, 1 }, { 0, 0, 0 }, { -1, -1, -1 } };

				fconvolucao.setImg(imagem.getimgEditada());
				fconvolucao.ConvMagnitude(prewittX, prewittY);
				imgAuxiliar = fconvolucao.getImg();

				pintarLabel(imgAuxiliar, prevEditada);
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
			}
		});
		btnPrewitt.setEnabled(false);
		btnPrewitt.setVisible(false);
		btnPrewitt.setForeground(Color.BLACK);
		btnPrewitt.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPrewitt.setBounds(10, 339, 148, 50);
		panelAuxiliar.add(btnPrewitt);

		JButton btnSobel = new JButton("Sobel");// Aplicar a máscara de Sobel.
		btnSobel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Máscara para a detecção de bordas de Sobel nos eixos x e y.
				final double[][] sobelX = { { -1, 0, 1 }, { 2, 0, -2 }, { -1, 0, 1 } };
				final double[][] sobelY = { { 1, 2, 1 }, { 0, 0, 0 }, { -1, -2, -1 } };

				fconvolucao.setImg(imagem.getimgEditada());
				fconvolucao.ConvMagnitude(sobelX, sobelY);
				imgAuxiliar = fconvolucao.getImg();

				pintarLabel(imgAuxiliar, prevEditada);
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
			}
		});
		btnSobel.setEnabled(false);
		btnSobel.setVisible(false);
		btnSobel.setForeground(Color.BLACK);
		btnSobel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSobel.setBounds(324, 339, 148, 50);
		panelAuxiliar.add(btnSobel);

		btnBordas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Definindo a filtragem a ser feita quando este botão é pressionado.
				// Ativando os botões de escolha de máscaras de bordas.
				btnPrewitt.setVisible(true);
				btnPrewitt.setEnabled(true);
				btnSobel.setVisible(true);
				btnSobel.setEnabled(true);
				// Ativando os textos.
				txtSelecioneAMscara.setVisible(true);
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
				pintarLabel(imgAuxiliar, prevEditada);
				// Trocando tela exibida.
				TrocarTela(panelAuxiliar);
			}
		});
		btnBordas.setEnabled(false);
		btnBordas.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBordas.setBounds(326, 172, 148, 50);
		panel.add(btnBordas);

		JButton btnKDesfoque = new JButton("Desfoque Suave\r\n");// Aplicar a máscara de desfoque.
		btnKDesfoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final double[][] desfoque = { { 0, 0, 1, 0, 0 }, { 0, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1 },
						{ 0, 1, 1, 1, 0 }, { 0, 0, 1, 0, 0 } }; // Máscara usada para o desfoque.

				fconvolucao.setImg(imagem.getimgEditada());
				fconvolucao.ConvNormal(desfoque, 13);
				imgAuxiliar = fconvolucao.getImg();

				pintarLabel(imgAuxiliar, prevEditada);
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
			}
		});
		btnKDesfoque.setEnabled(false);
		btnKDesfoque.setVisible(false);
		btnKDesfoque.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnKDesfoque.setBounds(10, 339, 148, 50);
		panelAuxiliar.add(btnKDesfoque);

		JButton btnDesfoqueGauss = new JButton("Desfoque Gaussiano\r\n");// Aplicar a máscara de desfoque Gaussiano.
		btnDesfoqueGauss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final double[][] gaussiano = { { 0.077847, 0.123317, 0.077847 }, { 0.123317, 0.195346, 0.123317 },
						{ 0.077847, 0.123317, 0.077847 } };// Máscara com o grau de aproximação mais exato para o
															// desfoque
															// gaussiano.

				fconvolucao.setImg(imagem.getimgEditada());
				fconvolucao.ConvNormal(gaussiano, 1);
				imgAuxiliar = fconvolucao.getImg();

				pintarLabel(imgAuxiliar, prevEditada);
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
			}
		});
		btnDesfoqueGauss.setEnabled(false);
		btnDesfoqueGauss.setVisible(false);
		btnDesfoqueGauss.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDesfoqueGauss.setBounds(168, 339, 148, 50);
		panelAuxiliar.add(btnDesfoqueGauss);

		JButton btnDesfoqued = new JButton("Desfoque 1D\r\n");// Aplicar a máscara de desfoque em uma dimensão.
		btnDesfoqued.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Máscara usada para o desfoque em uma direção, dando a impressão de movimento.
				final double[][] desfoque1D = { { 1, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0 },
						{ 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0 },
						{ 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0, 0, 1 } };

				fconvolucao.setImg(imagem.getimgEditada());
				fconvolucao.ConvNormal(desfoque1D, 7);
				imgAuxiliar = fconvolucao.getImg();

				pintarLabel(imgAuxiliar, prevEditada);
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
			}
		});
		btnDesfoqued.setEnabled(false);
		btnDesfoqued.setVisible(false);
		btnDesfoqued.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDesfoqued.setBounds(324, 339, 148, 50);
		panelAuxiliar.add(btnDesfoqued);

		btnDesfoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Definindo a filtragem a ser feita quando este botão é pressionado.
				// Ativando os botões das máscaras de desfoque.
				btnKDesfoque.setVisible(true);
				btnKDesfoque.setEnabled(true);
				btnDesfoqueGauss.setVisible(true);
				btnDesfoqueGauss.setEnabled(true);
				btnDesfoqued.setVisible(true);
				btnDesfoqued.setEnabled(true);
				// Ativando os textos.
				txtSelecioneAMscara.setVisible(true);
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
				pintarLabel(imgAuxiliar, prevEditada);
				// Trocando tela exibida.
				TrocarTela(panelAuxiliar);
			}
		});
		btnDesfoque.setEnabled(false);
		btnDesfoque.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDesfoque.setBounds(326, 233, 148, 50);
		panel.add(btnDesfoque);

		btnAfiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Definindo a filtragem a ser feita quando este botão é pressionado.
				// Máscara usada para destacar as bordas da imagem.
				final double[][] afiar = { { -1, -1, -1 }, { -1, 9, -1 }, { -1, -1, -1 } };

				fconvolucao.setImg(imagem.getimgEditada());
				fconvolucao.ConvNormal(afiar, 1);
				imgAuxiliar = fconvolucao.getImg();

				pintarLabel(imgAuxiliar, prevEditada);
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
				TrocarTela(panelAuxiliar);
			}
		});
		btnAfiar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAfiar.setEnabled(false);
		btnAfiar.setBounds(326, 294, 148, 50);
		panel.add(btnAfiar);

		btnRelevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Definindo a filtragem a ser feita quando este botão é pressionado.
				// Máscara usada para dar a impressão de relevo na imagem.
				final double[][] relevo = { { -2, -1, 0 }, { -1, 1, 1 }, { 0, 1, 2 } };

				fconvolucao.setImg(imagem.getimgEditada());
				fconvolucao.ConvNormal(relevo, 1);
				imgAuxiliar = fconvolucao.getImg();

				pintarLabel(imgAuxiliar, prevEditada);
				pintarLabel(imagem.getimgOriginal(), prevOriginal);
				TrocarTela(panelAuxiliar);
			}
		});
		btnRelevo.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRelevo.setEnabled(false);
		btnRelevo.setBounds(326, 355, 148, 50);
		panel.add(btnRelevo);
		// Fim da definição do grupo.

		// Definindo o grupo dos botões de Aceitar, Desfazer e Mostrar.
		btnAceitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Salvando a imagem após as edições.
				imagem.setimgEditada(imgAuxiliar);
				// Destivando os botões de escolha da máscara.
				btnPrewitt.setEnabled(false);
				btnPrewitt.setVisible(false);
				btnSobel.setEnabled(false);
				btnSobel.setVisible(false);
				btnKDesfoque.setEnabled(false);
				btnKDesfoque.setVisible(false);
				btnDesfoqueGauss.setEnabled(false);
				btnDesfoqueGauss.setVisible(false);
				btnDesfoqued.setEnabled(false);
				btnDesfoqued.setVisible(false);
				btn75.setEnabled(false);
				btn75.setVisible(false);
				btn150.setEnabled(false);
				btn150.setVisible(false);
				btn225.setEnabled(false);
				btn225.setVisible(false);
				// Desativando os textos.
				txtSelecioneAMscara.setVisible(false);
				txtSelecioneOLimite.setVisible(false);
				// Alternando para a tela principal.
				// Como o mesmo botão não pode ocupar diferentes telas ao mesmo tempo, é
				// removido da tela de matriz e adicionado a tela auxiliar quando pressionado.
				panelMatriz.remove(btnAceitar);
				panelMatriz.remove(btnDesfazer);
				panelMatriz.remove(btnMostrar);
				panelAuxiliar.add(btnAceitar);
				panelAuxiliar.add(btnDesfazer);
				panelAuxiliar.add(btnMostrar);

				TrocarTela(panel);
			}
		});
		btnAceitar.setForeground(Color.GREEN);
		btnAceitar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAceitar.setToolTipText("");
		btnAceitar.setBounds(324, 400, 148, 50);
		panelAuxiliar.add(btnAceitar);

		btnDesfazer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Destivando os botões de escolha da máscara.
				btnPrewitt.setEnabled(false);
				btnPrewitt.setVisible(false);
				btnSobel.setEnabled(false);
				btnSobel.setVisible(false);
				btnKDesfoque.setEnabled(false);
				btnKDesfoque.setVisible(false);
				btnDesfoqueGauss.setEnabled(false);
				btnDesfoqueGauss.setVisible(false);
				btnDesfoqued.setEnabled(false);
				btnDesfoqued.setVisible(false);
				btn75.setEnabled(false);
				btn75.setVisible(false);
				btn150.setEnabled(false);
				btn150.setVisible(false);
				btn225.setEnabled(false);
				btn225.setVisible(false);
				// Desativando os textos.
				txtSelecioneAMscara.setVisible(false);
				txtSelecioneOLimite.setVisible(false);

				imgAuxiliar = imagem.getimgEditada();

				// Alternando para a tela principal.
				// Como o mesmo botão não pode ocupar diferentes telas ao mesmo tempo, é
				// removido da tela de matriz e adicionado a tela auxiliar quando pressionado.
				panelMatriz.remove(btnAceitar);
				panelMatriz.remove(btnDesfazer);
				panelMatriz.remove(btnMostrar);
				panelAuxiliar.add(btnAceitar);
				panelAuxiliar.add(btnDesfazer);
				panelAuxiliar.add(btnMostrar);
				TrocarTela(panel);
			}
		});
		btnDesfazer.setForeground(Color.RED);
		btnDesfazer.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnDesfazer.setBounds(10, 400, 148, 50);
		panelAuxiliar.add(btnDesfazer);

		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageDisplay mostrar = new ImageDisplay();
				mostrar.setImage(imgAuxiliar);
			}
		});
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMostrar.setBounds(168, 400, 148, 50);
		panelAuxiliar.add(btnMostrar);
		// Fim da definição do grupo.

		// Definição do grupo dos textos que aparecem em ambas telas.
		txtFiltrosDeImagens = new JTextField();// Apenas um título.
		txtFiltrosDeImagens.setHorizontalAlignment(SwingConstants.CENTER);
		txtFiltrosDeImagens.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtFiltrosDeImagens.setText("Filtros de Imagens");
		txtFiltrosDeImagens.setEditable(false);
		txtFiltrosDeImagens.setBounds(10, 11, 464, 28);
		panel.add(txtFiltrosDeImagens);
		txtFiltrosDeImagens.setColumns(10);

		txtPrevia = new JTextField();// Apenas um título.
		txtPrevia.setEditable(false);
		txtPrevia.setBounds(10, 11, 464, 50);
		txtPrevia.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrevia.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtPrevia.setText("Pr\u00E9via");
		txtPrevia.setColumns(10);
		panelAuxiliar.add(txtPrevia);

		txtOriginal = new JTextField();// Apenas um título.
		txtOriginal.setEditable(false);
		txtOriginal.setHorizontalAlignment(SwingConstants.CENTER);
		txtOriginal.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtOriginal.setText("Original");
		txtOriginal.setBounds(10, 72, 232, 58);
		panelAuxiliar.add(txtOriginal);
		txtOriginal.setColumns(10);

		txtEditada = new JTextField();// Apenas um título.
		txtEditada.setEditable(false);
		txtEditada.setText("Editada");
		txtEditada.setHorizontalAlignment(SwingConstants.CENTER);
		txtEditada.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtEditada.setColumns(10);
		txtEditada.setBounds(242, 72, 232, 58);
		panelAuxiliar.add(txtEditada);

		txtFiltros = new JTextField();// Apenas um título para o menu.
		txtFiltros.setHorizontalAlignment(SwingConstants.CENTER);
		txtFiltros.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtFiltros.setText("Filtros");
		txtFiltros.setEditable(false);
		txtFiltros.setBounds(10, 111, 464, 50);
		panel.add(txtFiltros);
		txtFiltros.setColumns(10);

		txtEscolhaOMtodo = new JTextField();
		txtEscolhaOMtodo.setText("Escolha o m\u00E9todo da convolu\u00E7\u00E3o a ser aplicado:");
		txtEscolhaOMtodo.setHorizontalAlignment(SwingConstants.CENTER);
		txtEscolhaOMtodo.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtEscolhaOMtodo.setEditable(false);
		txtEscolhaOMtodo.setColumns(10);
		txtEscolhaOMtodo.setBounds(10, 302, 464, 26);
		panelMatriz.add(txtEscolhaOMtodo);

		txtEscolhaOTamando = new JTextField();
		txtEscolhaOTamando.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtEscolhaOTamando.setText("Escolha o tamando da matriz e insira os dados:");
		txtEscolhaOTamando.setHorizontalAlignment(SwingConstants.CENTER);
		txtEscolhaOTamando.setEditable(false);
		txtEscolhaOTamando.setBounds(10, 11, 464, 50);
		panelMatriz.add(txtEscolhaOTamando);
		txtEscolhaOTamando.setColumns(10);

		txtSelecioneAMscara = new JTextField();
		txtSelecioneAMscara.setHorizontalAlignment(SwingConstants.CENTER);
		txtSelecioneAMscara.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSelecioneAMscara.setText("Selecione a m\u00E1scara a ser usada:");
		txtSelecioneAMscara.setVisible(false);
		txtSelecioneAMscara.setEditable(false);
		txtSelecioneAMscara.setBounds(10, 302, 464, 26);
		panelAuxiliar.add(txtSelecioneAMscara);
		txtSelecioneAMscara.setColumns(10);

		txtSelecioneOLimite = new JTextField();
		txtSelecioneOLimite.setHorizontalAlignment(SwingConstants.CENTER);
		txtSelecioneOLimite.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSelecioneOLimite.setEditable(false);
		txtSelecioneOLimite.setVisible(false);
		txtSelecioneOLimite.setText("Selecione o limite para aplica\u00E7\u00E3o do filtro:");
		txtSelecioneOLimite.setBounds(10, 302, 462, 26);
		panelAuxiliar.add(txtSelecioneOLimite);
		txtSelecioneOLimite.setColumns(10);
		// Fim da definição do grupo.

		// Definindo o grupo das Labels de prévia.
		prevOriginal.setBounds(10, 141, 232, 150);
		prevOriginal.setBorder(BorderFactory.createLineBorder(Color.gray, 2));// Definindo bordas coloridas para a
																				// label.
		panelAuxiliar.add(prevOriginal);

		prevEditada.setBounds(240, 141, 232, 150);
		prevEditada.setBorder(BorderFactory.createLineBorder(Color.gray, 2));// Definindo bordas coloridas para a label.
		panelAuxiliar.add(prevEditada);
		// Fim da definição do grupo.

		// Definindo as operações da tela de edição de matriz.
		btnTres.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTres.setBounds(10, 72, 148, 50);
		panelMatriz.add(btnTres);

		btnCinco.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCinco.setBounds(326, 72, 148, 50);
		panelMatriz.add(btnCinco);

		label.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		label.setBounds(242, 133, 232, 150);
		panelMatriz.add(label);

		textField1x1 = new JTextField();
		textField1x1.setBounds(10, 133, 20, 20);
		panelMatriz.add(textField1x1);
		textField1x1.setColumns(10);

		textField1x2 = new JTextField();
		textField1x2.setColumns(10);
		textField1x2.setBounds(40, 133, 20, 20);
		panelMatriz.add(textField1x2);

		textField1x3 = new JTextField();
		textField1x3.setColumns(10);
		textField1x3.setBounds(70, 133, 20, 20);
		panelMatriz.add(textField1x3);

		textField2x1 = new JTextField();
		textField2x1.setColumns(10);
		textField2x1.setBounds(10, 164, 20, 20);
		panelMatriz.add(textField2x1);

		textField2x2 = new JTextField();
		textField2x2.setColumns(10);
		textField2x2.setBounds(40, 164, 20, 20);
		panelMatriz.add(textField2x2);

		textField2x3 = new JTextField();
		textField2x3.setColumns(10);
		textField2x3.setBounds(70, 164, 20, 20);
		panelMatriz.add(textField2x3);

		textField3x1 = new JTextField();
		textField3x1.setColumns(10);
		textField3x1.setBounds(10, 195, 20, 20);
		panelMatriz.add(textField3x1);

		textField3x2 = new JTextField();
		textField3x2.setColumns(10);
		textField3x2.setBounds(40, 195, 20, 20);
		panelMatriz.add(textField3x2);

		textField3x3 = new JTextField();
		textField3x3.setColumns(10);
		textField3x3.setBounds(70, 195, 20, 20);
		panelMatriz.add(textField3x3);

		textField1x4 = new JTextField();
		textField1x4.setEditable(false);
		textField1x4.setColumns(10);
		textField1x4.setBounds(100, 133, 20, 20);
		panelMatriz.add(textField1x4);

		textField1x5 = new JTextField();
		textField1x5.setEditable(false);
		textField1x5.setColumns(10);
		textField1x5.setBounds(130, 133, 20, 20);
		panelMatriz.add(textField1x5);

		textField2x4 = new JTextField();
		textField2x4.setEditable(false);
		textField2x4.setColumns(10);
		textField2x4.setBounds(100, 164, 20, 20);
		panelMatriz.add(textField2x4);

		textField2x5 = new JTextField();
		textField2x5.setEditable(false);
		textField2x5.setColumns(10);
		textField2x5.setBounds(130, 164, 20, 20);
		panelMatriz.add(textField2x5);

		textField3x4 = new JTextField();
		textField3x4.setEditable(false);
		textField3x4.setColumns(10);
		textField3x4.setBounds(100, 195, 20, 20);
		panelMatriz.add(textField3x4);

		textField3x5 = new JTextField();
		textField3x5.setEditable(false);
		textField3x5.setColumns(10);
		textField3x5.setBounds(130, 195, 20, 20);
		panelMatriz.add(textField3x5);

		textField4x1 = new JTextField();
		textField4x1.setEditable(false);
		textField4x1.setColumns(10);
		textField4x1.setBounds(10, 226, 20, 20);
		panelMatriz.add(textField4x1);

		textField5x1 = new JTextField();
		textField5x1.setEditable(false);
		textField5x1.setColumns(10);
		textField5x1.setBounds(10, 257, 20, 20);
		panelMatriz.add(textField5x1);

		textField4x2 = new JTextField();
		textField4x2.setEditable(false);
		textField4x2.setColumns(10);
		textField4x2.setBounds(40, 226, 20, 20);
		panelMatriz.add(textField4x2);

		textField5x2 = new JTextField();
		textField5x2.setEditable(false);
		textField5x2.setColumns(10);
		textField5x2.setBounds(40, 257, 20, 20);
		panelMatriz.add(textField5x2);

		textField4x3 = new JTextField();
		textField4x3.setEditable(false);
		textField4x3.setColumns(10);
		textField4x3.setBounds(70, 226, 20, 20);
		panelMatriz.add(textField4x3);

		textField5x3 = new JTextField();
		textField5x3.setEditable(false);
		textField5x3.setColumns(10);
		textField5x3.setBounds(70, 257, 20, 20);
		panelMatriz.add(textField5x3);

		textField4x4 = new JTextField();
		textField4x4.setEditable(false);
		textField4x4.setColumns(10);
		textField4x4.setBounds(100, 226, 20, 20);
		panelMatriz.add(textField4x4);

		textField5x4 = new JTextField();
		textField5x4.setEditable(false);
		textField5x4.setColumns(10);
		textField5x4.setBounds(100, 257, 20, 20);
		panelMatriz.add(textField5x4);

		textField5x5 = new JTextField();
		textField5x5.setEditable(false);
		textField5x5.setColumns(10);
		textField5x5.setBounds(130, 257, 20, 20);
		panelMatriz.add(textField5x5);

		textField4x5 = new JTextField();
		textField4x5.setEditable(false);
		textField4x5.setColumns(10);
		textField4x5.setBounds(130, 226, 20, 20);
		panelMatriz.add(textField4x5);

		btnTres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tamanhoMatriz = 3;

				// Destivando as caixas de texto para matriz 5x5.
				textField1x4.setEditable(false);
				textField1x5.setEditable(false);
				textField2x4.setEditable(false);
				textField2x5.setEditable(false);
				textField3x4.setEditable(false);
				textField3x5.setEditable(false);
				textField5x5.setEditable(false);
				textField4x1.setEditable(false);
				textField4x2.setEditable(false);
				textField4x3.setEditable(false);
				textField4x4.setEditable(false);
				textField4x5.setEditable(false);
				textField5x1.setEditable(false);
				textField5x2.setEditable(false);
				textField5x3.setEditable(false);
				textField5x4.setEditable(false);
			}
		});
		btnCinco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tamanhoMatriz = 5;

				// Ativando as caixas de texto para matriz 5x5.
				textField1x4.setEditable(true);
				textField1x5.setEditable(true);
				textField2x4.setEditable(true);
				textField2x5.setEditable(true);
				textField3x4.setEditable(true);
				textField3x5.setEditable(true);
				textField5x5.setEditable(true);
				textField4x1.setEditable(true);
				textField4x2.setEditable(true);
				textField4x3.setEditable(true);
				textField4x4.setEditable(true);
				textField4x5.setEditable(true);
				textField5x1.setEditable(true);
				textField5x2.setEditable(true);
				textField5x3.setEditable(true);
				textField5x4.setEditable(true);
			}
		});

		JButton btnNormal = new JButton("Normal\r\n");
		btnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double[][] kernel = criarMatriz(tamanhoMatriz);

				fconvolucao.setImg(imagem.getimgEditada());
				fconvolucao.ConvNormal(kernel, 1);
				imgAuxiliar = fconvolucao.getImg();

				pintarLabel(imgAuxiliar, label);
			}
		});
		btnNormal.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNormal.setBounds(10, 339, 148, 50);
		panelMatriz.add(btnNormal);

		JButton btnMagnitude = new JButton("Magnitude");
		btnMagnitude.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double[][] kernelX = criarMatriz(tamanhoMatriz);
				double[][] kernelY = transporKernel(kernelX);

				fconvolucao.setImg(imagem.getimgEditada());
				fconvolucao.ConvMagnitude(kernelX, kernelY);
				imgAuxiliar = fconvolucao.getImg();

				pintarLabel(imgAuxiliar, label);
			}
		});
		btnMagnitude.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMagnitude.setBounds(326, 339, 148, 50);
		panelMatriz.add(btnMagnitude);
		// Fim da definição do grupo.
	}

	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
