/*Alunos:
 * Pedro Antônio Tibau Velozo - 1812013
 * Yuri Lemos - 1610893
 * Rafael Feliciano - 1521772
 * */

package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Arrays;

import javax.swing.*;

import Model.CtrlModel;

class FirstScreen extends JFrame implements ActionListener {

	private static JFrame frameNomeJogadores = new JFrame();
	private JButton botaoComecarJogo = new JButton("Começar Jogo");
	private JTextField textInput = new JTextField();

	private JButton botaoCarregarJogo = new JButton("Carregar Jogo");

	private int numeroJogadores;

	// java - get screen size using the Toolkit class
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	// the screen height
	private int screenHeight = screenSize.height;
	private int screenWidth = screenSize.width;

	FrameJogador jogador1;
	FrameJogador jogador2;
	FrameJogador jogador3;
	FrameJogador jogador4;

	String[] cartasJogador1;
	String[] cartasJogador2;
	String[] cartasJogador3;
	String[] cartasJogador4;

	public FirstScreen() {
		frameNomeJogadores.setLayout(new GridLayout(2, 1, 0, 0));
		frameNomeJogadores.setLocation(512, 250);
		frameNomeJogadores.setTitle("Insira o nome dos jogadores (separdos por 1 espaço)");
		frameNomeJogadores.setSize(500, 125);
		// Configurar os botoes
		botaoComecarJogo.addActionListener(this);
		botaoCarregarJogo.addActionListener(this);

		// Setar as bordas dos textos e botoes e tamanho
		textInput.setPreferredSize(new Dimension(50, 50));

		// Ordem dos "blocos" dentro da tela de inicio

		frameNomeJogadores.add(textInput, BorderLayout.NORTH);
		frameNomeJogadores.add(botaoCarregarJogo, BorderLayout.EAST);
		frameNomeJogadores.add(botaoComecarJogo, BorderLayout.SOUTH);

		// Setar ele visivel
		frameNomeJogadores.setResizable(false);
		frameNomeJogadores.setVisible(true);
		frameNomeJogadores.setLocationRelativeTo(null);
		frameNomeJogadores.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		/*
		 * quando clica em comecar o jogo depois de escolher os nomes, a janela dos
		 * nomes fecha e são abertas as janelas da banca e dos jogadores
		 */
		if (e.getSource() == botaoComecarJogo) {
			// ao clicar em "Começar Jogo" analizamos quantos nomes foram passados e com
			// base inicializamos a rodada
			// e mostramos as janelas da banca e dos jogadores

			String[] listaJogadoresNomes = textInput.getText().split(" ");

			this.numeroJogadores = listaJogadoresNomes.length;

			if (this.numeroJogadores > 4 || listaJogadoresNomes[0] == " ") {
				JOptionPane.showMessageDialog(null, "Numero de Jogadores errado", "ERRO",
						JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			this.frameNomeJogadores.setVisible(false);
			FrameBanca frameBanca = new FrameBanca();

			/** INSTANCIA DEALER **/
			CtrlModel.getCtrlModel().instaciaDealer();

			String[] cartasDealer = CtrlModel.getCtrlModel().getCartaInfoDealer();

			System.out.println("cartasDealer = " + Arrays.toString(cartasDealer));

			/** JANELAS DOS JOGADORES **/
			int numeroJogadores = listaJogadoresNomes.length;

			FrameJogador jogador1;
			FrameJogador jogador2;
			FrameJogador jogador3;
			FrameJogador jogador4;

			String[] cartasJogador1;
			String[] cartasJogador2;
			String[] cartasJogador3;
			String[] cartasJogador4;

			// o numero de jogadores muda a forma que inicializamos o jogo
			switch (numeroJogadores) {
			case 1:
				CtrlModel.getCtrlModel().instaciaJogador1(listaJogadoresNomes[0]);

				CtrlModel.getCtrlModel().instanciaRodada1Jogadores();

				cartasJogador1 = CtrlModel.getCtrlModel().getCartaInfoJogador1();

				jogador1 = new FrameJogador(cartasJogador1);
				jogador1.setLocation(0, 0);
				break;

			case 2:
				CtrlModel.getCtrlModel().instaciaJogador1(listaJogadoresNomes[0]);

				CtrlModel.getCtrlModel().instaciaJogador2(listaJogadoresNomes[1]);

				CtrlModel.getCtrlModel().instanciaRodada2Jogadores();

				cartasJogador1 = CtrlModel.getCtrlModel().getCartaInfoJogador1();
				cartasJogador2 = CtrlModel.getCtrlModel().getCartaInfoJogador2();

				jogador1 = new FrameJogador(cartasJogador1);
				jogador1.setLocation(0, 0);
				jogador2 = new FrameJogador(cartasJogador2);
				jogador2.setLocation(0, screenHeight - 500);
				break;

			case 3:
				CtrlModel.getCtrlModel().instaciaJogador1(listaJogadoresNomes[0]);

				CtrlModel.getCtrlModel().instaciaJogador2(listaJogadoresNomes[1]);

				CtrlModel.getCtrlModel().instaciaJogador3(listaJogadoresNomes[2]);

				CtrlModel.getCtrlModel().instanciaRodada3Jogadores();

				cartasJogador1 = CtrlModel.getCtrlModel().getCartaInfoJogador1();
				cartasJogador2 = CtrlModel.getCtrlModel().getCartaInfoJogador2();
				cartasJogador3 = CtrlModel.getCtrlModel().getCartaInfoJogador3();

				jogador1 = new FrameJogador(cartasJogador1);
				jogador1.setLocation(0, 0);
				jogador2 = new FrameJogador(cartasJogador2);
				jogador2.setLocation(0, screenHeight - 500);
				jogador3 = new FrameJogador(cartasJogador3);
				jogador3.setLocation(screenWidth - 500, 0);
				break;

			case 4:
				CtrlModel.getCtrlModel().instaciaJogador1(listaJogadoresNomes[0]);

				CtrlModel.getCtrlModel().instaciaJogador2(listaJogadoresNomes[1]);

				CtrlModel.getCtrlModel().instaciaJogador3(listaJogadoresNomes[2]);

				CtrlModel.getCtrlModel().instaciaJogador4(listaJogadoresNomes[3]);

				CtrlModel.getCtrlModel().instanciaRodada4Jogadores();

				cartasJogador1 = CtrlModel.getCtrlModel().getCartaInfoJogador1();
				cartasJogador2 = CtrlModel.getCtrlModel().getCartaInfoJogador2();
				cartasJogador3 = CtrlModel.getCtrlModel().getCartaInfoJogador3();
				cartasJogador4 = CtrlModel.getCtrlModel().getCartaInfoJogador4();

				jogador1 = new FrameJogador(cartasJogador1);
				jogador1.setLocation(0, 0);
				jogador2 = new FrameJogador(cartasJogador2);
				jogador2.setLocation(0, screenHeight - 500);
				jogador3 = new FrameJogador(cartasJogador3);
				jogador3.setLocation(screenWidth - 500, 0);
				jogador4 = new FrameJogador(cartasJogador4);
				jogador4.setLocation(screenWidth - 500, screenHeight - 500);
				break;

			default:
				break;
			}
		}
		if (e.getSource() == botaoCarregarJogo) {
			// ao clicar em "Carregar Jogo" lemos um arquivo .txt com as informações da
			// rodada salvada,
			// e com essas informações determinamos o que compoõe cada jogador como, suas
			// cartas, valor
			// de aposta, soma creditos, ... . O mesmo é feito para o Dealer. Assim
			// conseguimos
			// iniciar uma partida do momento em que foi parada.
			System.out.println("Carregar Jogo Apertado");
			try {
				String[] DinheiroENomeJogadores = CtrlModel.getCtrlModel().carregaPartida();
				// Exemplo do vetor acima
				// DinheiroENomeJogadores = [1, 2, InicioInfoDealer, 2, qh, td, 20,
				// FimInfoDealer, InicioInfoJogadores, 3, ts, jc, jd, 460, 40, PEDRO, 2, ah, 8h,
				// 500, 0, MARIA, FimInfoJogadores]

				System.out.println("INFO DO ARQUIVO = " + Arrays.toString(DinheiroENomeJogadores));

				int vezRodada = Integer.parseInt(DinheiroENomeJogadores[0]);

				int numeroJogadores = Integer.parseInt(DinheiroENomeJogadores[1]);

				int numeroCartasDealer = Integer.parseInt(DinheiroENomeJogadores[3]);

				System.out.println("numeroCartasDealer = " + numeroCartasDealer);
				/** PEGANDO AS CARTAS DO DEALER **/
				String[] cartasDealer = new String[0];
				int indexCartasDealer = 0;
				int posInicialCartasDealer = 4;
				while (numeroCartasDealer > 0) {
					String carta = DinheiroENomeJogadores[posInicialCartasDealer];
					cartasDealer = Arrays.copyOf(cartasDealer, cartasDealer.length + 1);
					cartasDealer[cartasDealer.length - 1] = carta;
					posInicialCartasDealer++; // incremento posInicialCartasDealer
					numeroCartasDealer--;
				}
				System.out.println("cartasDealer = " + Arrays.toString(cartasDealer));

				/** INSTANCIA DEALER **/

				CtrlModel.getCtrlModel().instaciaDealer(cartasDealer);

				/** JANELAS DOS JOGADORES **/

				FrameJogador jogador1;
				FrameJogador jogador2;
				FrameJogador jogador3;
				FrameJogador jogador4;

				String[] cartasJogador1;
				String[] cartasJogador2;
				String[] cartasJogador3;
				String[] cartasJogador4;

				/** PEGANDO INFO DOS JOGADORES **/
				int posAtual = posInicialCartasDealer;
				int posInicialJogadores = posAtual + 3;
				System.out.println("posInicialJogadores = " + posInicialJogadores);

				this.frameNomeJogadores.setVisible(false);
				FrameBanca frameBanca = new FrameBanca();

				int numeroCartasJogador1, numeroCartasJogador2, numeroCartasJogador3, numeroCartasJogador4;
				int pos1aCarta;
				int dinheiroJogador1, dinheiroJogador2, dinheiroJogador3, dinheiroJogador4;
				int apostaJogador1, apostaJogador2, apostaJogador3, apostaJogador4;
				String nomeJogador1, nomeJogador2, nomeJogador3, nomeJogador4;
				String[] infoJogador1, infoJogador2, infoJogador3, infoJogador4;

				// o numero de jogadores muda a forma que inicializamos o jogo
				switch (numeroJogadores) {
				case 1:
					numeroCartasJogador1 = Integer.parseInt(DinheiroENomeJogadores[posInicialJogadores]);
					pos1aCarta = posInicialJogadores + 1;
					cartasJogador1 = this.retornaCartasJogador(DinheiroENomeJogadores, pos1aCarta,
							numeroCartasJogador1);
					nomeJogador1 = DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador1 + 1];
					System.out.println("nomeJogador1 = " + nomeJogador1);
					dinheiroJogador1 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador1]);
					CtrlModel.getCtrlModel().instaciaJogador1(nomeJogador1, cartasJogador1);
					CtrlModel.getCtrlModel().setDinheiroJogador1(dinheiroJogador1);

					CtrlModel.getCtrlModel().instanciaRodada1Jogadores();

					CtrlModel.getCtrlModel().setVezRodada(vezRodada);

					infoJogador1 = CtrlModel.getCtrlModel().getCartaInfoJogador1();

					jogador1 = new FrameJogador(infoJogador1, cartasJogador1);
					jogador1.setLocation(0, 0);
					break;

				case 2:
					numeroCartasJogador1 = Integer.parseInt(DinheiroENomeJogadores[posInicialJogadores]);
					pos1aCarta = posInicialJogadores + 1;
					cartasJogador1 = this.retornaCartasJogador(DinheiroENomeJogadores, pos1aCarta,
							numeroCartasJogador1);
					nomeJogador1 = DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador1 + 2];
					System.out.println("nomeJogador1 = " + nomeJogador1);
					dinheiroJogador1 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador1]);
					apostaJogador1 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador1 + 1]);
					CtrlModel.getCtrlModel().instaciaJogador1(nomeJogador1, cartasJogador1);
					CtrlModel.getCtrlModel().setDinheiroJogador1(dinheiroJogador1);
					CtrlModel.getCtrlModel().setApostaJogador1(apostaJogador1);

					numeroCartasJogador2 = Integer
							.parseInt(DinheiroENomeJogadores[posInicialJogadores + numeroCartasJogador1 + 4]);
					pos1aCarta = posInicialJogadores + numeroCartasJogador1 + 5;
					cartasJogador2 = this.retornaCartasJogador(DinheiroENomeJogadores, pos1aCarta,
							numeroCartasJogador2);
					nomeJogador2 = DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador2 + 2];
					System.out.println("nomeJogador2 = " + nomeJogador2);
					dinheiroJogador2 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador2]);
					apostaJogador2 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador2 + 1]);
					System.out.println("dinheiroJogador2 = " + dinheiroJogador2);

					System.out.println("cartasJogador2 = " + cartasJogador2);
					CtrlModel.getCtrlModel().instaciaJogador2(nomeJogador2, cartasJogador2);
					CtrlModel.getCtrlModel().setDinheiroJogador2(dinheiroJogador2);
					CtrlModel.getCtrlModel().setApostaJogador2(apostaJogador2);

					CtrlModel.getCtrlModel().instanciaRodada2Jogadores();

					CtrlModel.getCtrlModel().setVezRodada(vezRodada);

					infoJogador1 = CtrlModel.getCtrlModel().getCartaInfoJogador1();
					infoJogador2 = CtrlModel.getCtrlModel().getCartaInfoJogador2();

					jogador1 = new FrameJogador(infoJogador1, cartasJogador1); // cartasJogador1 representa que tenho
																				// que desenhar na tela as informacoes
																				// do jogadores assim que começar
					jogador1.setLocation(0, 0);
					jogador2 = new FrameJogador(infoJogador2, cartasJogador2);
					jogador2.setLocation(0, screenHeight - 500);
					break;

				case 3:
					numeroCartasJogador1 = Integer.parseInt(DinheiroENomeJogadores[posInicialJogadores]);
					pos1aCarta = posInicialJogadores + 1;
					cartasJogador1 = this.retornaCartasJogador(DinheiroENomeJogadores, pos1aCarta,
							numeroCartasJogador1);
					nomeJogador1 = DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador1 + 2];
					System.out.println("nomeJogador1 = " + nomeJogador1);
					dinheiroJogador1 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador1]);
					apostaJogador1 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador1 + 1]);
					CtrlModel.getCtrlModel().instaciaJogador1(nomeJogador1, cartasJogador1);
					CtrlModel.getCtrlModel().setDinheiroJogador1(dinheiroJogador1);
					CtrlModel.getCtrlModel().setApostaJogador1(apostaJogador1);

					numeroCartasJogador2 = Integer
							.parseInt(DinheiroENomeJogadores[posInicialJogadores + numeroCartasJogador1 + 4]);
					pos1aCarta = posInicialJogadores + numeroCartasJogador1 + 5;
					cartasJogador2 = this.retornaCartasJogador(DinheiroENomeJogadores, pos1aCarta,
							numeroCartasJogador2);
					nomeJogador2 = DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador2 + 2];
					System.out.println("nomeJogador2 = " + nomeJogador2);
					dinheiroJogador2 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador2]);
					apostaJogador2 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador2 + 1]);
					System.out.println("dinheiroJogador2 = " + dinheiroJogador2);

					System.out.println("cartasJogador2 = " + cartasJogador2);
					CtrlModel.getCtrlModel().instaciaJogador2(nomeJogador2, cartasJogador2);
					CtrlModel.getCtrlModel().setDinheiroJogador2(dinheiroJogador2);
					CtrlModel.getCtrlModel().setApostaJogador2(apostaJogador2);

					System.out.println("posInicialJogadores = " + posInicialJogadores);

					numeroCartasJogador3 = Integer.parseInt(DinheiroENomeJogadores[posInicialJogadores
							+ numeroCartasJogador1 + 4 + numeroCartasJogador2 + 4]);
					System.out.println("posInicialJogadores + numeroCartasJogador2 + 5 = "
							+ DinheiroENomeJogadores[posInicialJogadores + numeroCartasJogador1 + 4
									+ numeroCartasJogador2 + 4]);
					System.out.println("numeroCartasJogador3 = " + numeroCartasJogador3);
					pos1aCarta = posInicialJogadores + numeroCartasJogador1 + 4 + numeroCartasJogador2 + 4 + 1;
					System.out.println("pos1aCarta = " + pos1aCarta);
					cartasJogador3 = this.retornaCartasJogador(DinheiroENomeJogadores, pos1aCarta,
							numeroCartasJogador3);
					System.out.println("cartasJogador3 = " + Arrays.toString(cartasJogador3));
					nomeJogador3 = DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador3 + 2];
					System.out.println("nomeJogador3 = " + nomeJogador3);
					dinheiroJogador3 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador3]);
					apostaJogador3 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador3 + 1]);
					System.out.println("dinheiroJogador3 = " + dinheiroJogador3);
					System.out.println("apostaJogador3 = " + apostaJogador3);

					System.out.println("cartasJogador3 = " + cartasJogador3);
					CtrlModel.getCtrlModel().instaciaJogador3(nomeJogador3, cartasJogador3);
					CtrlModel.getCtrlModel().setDinheiroJogador3(dinheiroJogador3);
					CtrlModel.getCtrlModel().setApostaJogador3(apostaJogador3);

					CtrlModel.getCtrlModel().instanciaRodada3Jogadores();
					CtrlModel.getCtrlModel().setVezRodada(vezRodada);

					infoJogador1 = CtrlModel.getCtrlModel().getCartaInfoJogador1();
					infoJogador2 = CtrlModel.getCtrlModel().getCartaInfoJogador2();
					infoJogador3 = CtrlModel.getCtrlModel().getCartaInfoJogador3();

					jogador1 = new FrameJogador(infoJogador1, cartasJogador1);
					jogador1.setLocation(0, 0);
					jogador2 = new FrameJogador(infoJogador2, cartasJogador2);
					jogador2.setLocation(0, screenHeight - 500);
					jogador3 = new FrameJogador(infoJogador3, cartasJogador3);
					jogador3.setLocation(screenWidth - 500, 0);
					break;

				case 4:
					numeroCartasJogador1 = Integer.parseInt(DinheiroENomeJogadores[posInicialJogadores]);
					pos1aCarta = posInicialJogadores + 1;
					cartasJogador1 = this.retornaCartasJogador(DinheiroENomeJogadores, pos1aCarta,
							numeroCartasJogador1);
					nomeJogador1 = DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador1 + 2];
					System.out.println("nomeJogador1 = " + nomeJogador1);
					dinheiroJogador1 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador1]);
					apostaJogador1 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador1 + 1]);
					CtrlModel.getCtrlModel().instaciaJogador1(nomeJogador1, cartasJogador1);
					CtrlModel.getCtrlModel().setDinheiroJogador1(dinheiroJogador1);
					CtrlModel.getCtrlModel().setApostaJogador1(apostaJogador1);

					numeroCartasJogador2 = Integer
							.parseInt(DinheiroENomeJogadores[posInicialJogadores + numeroCartasJogador1 + 4]);
					pos1aCarta = posInicialJogadores + numeroCartasJogador1 + 5;
					cartasJogador2 = this.retornaCartasJogador(DinheiroENomeJogadores, pos1aCarta,
							numeroCartasJogador2);
					nomeJogador2 = DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador2 + 2];
					System.out.println("nomeJogador2 = " + nomeJogador2);
					dinheiroJogador2 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador2]);
					apostaJogador2 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador2 + 1]);
					System.out.println("dinheiroJogador2 = " + dinheiroJogador2);

					System.out.println("cartasJogador2 = " + cartasJogador2);
					CtrlModel.getCtrlModel().instaciaJogador2(nomeJogador2, cartasJogador2);
					CtrlModel.getCtrlModel().setDinheiroJogador2(dinheiroJogador2);
					CtrlModel.getCtrlModel().setApostaJogador2(apostaJogador2);

					System.out.println("posInicialJogadores = " + posInicialJogadores);
					numeroCartasJogador3 = Integer.parseInt(DinheiroENomeJogadores[posInicialJogadores
							+ numeroCartasJogador1 + 4 + numeroCartasJogador2 + 4]);
					System.out.println("posInicialJogadores + numeroCartasJogador2 + 5 = "
							+ DinheiroENomeJogadores[posInicialJogadores + numeroCartasJogador2 + 5
									+ numeroCartasJogador2 + 4]);
					pos1aCarta = posInicialJogadores + numeroCartasJogador1 + 4 + numeroCartasJogador2 + 4 + 1;
					cartasJogador3 = this.retornaCartasJogador(DinheiroENomeJogadores, pos1aCarta,
							numeroCartasJogador3);
					nomeJogador3 = DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador3 + 2];
					System.out.println("nomeJogador3 = " + nomeJogador3);
					dinheiroJogador3 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador3]);
					apostaJogador3 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador3 + 1]);
					System.out.println("dinheiroJogador3 = " + dinheiroJogador3);

					System.out.println("cartasJogador3 = " + cartasJogador3);
					CtrlModel.getCtrlModel().instaciaJogador3(nomeJogador3, cartasJogador3);
					CtrlModel.getCtrlModel().setDinheiroJogador3(dinheiroJogador3);
					CtrlModel.getCtrlModel().setApostaJogador3(apostaJogador3);

					numeroCartasJogador4 = Integer.parseInt(DinheiroENomeJogadores[posInicialJogadores
							+ numeroCartasJogador1 + 4 + numeroCartasJogador2 + 4 + numeroCartasJogador3 + 4]);
					pos1aCarta = posInicialJogadores + numeroCartasJogador1 + 4 + numeroCartasJogador2 + 4
							+ numeroCartasJogador3 + 4 + 1;
					cartasJogador4 = this.retornaCartasJogador(DinheiroENomeJogadores, pos1aCarta,
							numeroCartasJogador4);
					nomeJogador4 = DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador4 + 2];
					System.out.println("nomeJogador4 = " + nomeJogador4);
					dinheiroJogador4 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador4]);
					apostaJogador4 = Integer.parseInt(DinheiroENomeJogadores[pos1aCarta + numeroCartasJogador4 + 1]);
					System.out.println("dinheiroJogador4 = " + dinheiroJogador4);

					System.out.println("cartasJogador4 = " + cartasJogador4);
					CtrlModel.getCtrlModel().instaciaJogador4(nomeJogador4, cartasJogador4);
					CtrlModel.getCtrlModel().setDinheiroJogador4(dinheiroJogador4);
					CtrlModel.getCtrlModel().setApostaJogador4(apostaJogador4);

					CtrlModel.getCtrlModel().instanciaRodada4Jogadores();
					CtrlModel.getCtrlModel().setVezRodada(vezRodada);

					infoJogador1 = CtrlModel.getCtrlModel().getCartaInfoJogador1();
					infoJogador2 = CtrlModel.getCtrlModel().getCartaInfoJogador2();
					infoJogador3 = CtrlModel.getCtrlModel().getCartaInfoJogador3();
					infoJogador4 = CtrlModel.getCtrlModel().getCartaInfoJogador4();

					jogador1 = new FrameJogador(infoJogador1, cartasJogador1);
					jogador1.setLocation(0, 0);
					jogador2 = new FrameJogador(infoJogador2, cartasJogador2);
					jogador2.setLocation(0, screenHeight - 500);
					jogador3 = new FrameJogador(infoJogador3, cartasJogador3);
					jogador3.setLocation(screenWidth - 500, 0);
					jogador4 = new FrameJogador(infoJogador4, cartasJogador4);
					jogador4.setLocation(screenWidth - 500, screenHeight - 500);
					break;

				default:
					break;
				}

			} catch (FileNotFoundException e1) {
				System.out.println("ERRO AQUI 387");
				e1.printStackTrace();
			}
		}
	}

	private String[] retornaCartasJogador(String[] vetor, int pos1aCarta, int numeroCartasJogador) {
		System.out.println();
		System.out.println(Arrays.toString(vetor));
		System.out.println(pos1aCarta);
		System.out.println(numeroCartasJogador);
		System.out.println();

		String[] cartasJogador = new String[0];
		int posInicialCartasJogador = pos1aCarta;
		while (numeroCartasJogador > 0) {
			String carta = vetor[posInicialCartasJogador];
			cartasJogador = Arrays.copyOf(cartasJogador, cartasJogador.length + 1);
			cartasJogador[cartasJogador.length - 1] = carta;
			posInicialCartasJogador++; // incremento posInicialCartasDealer
			numeroCartasJogador--;
		}
		System.out.println("cartasJogador = " + Arrays.toString(cartasJogador));
		return cartasJogador;
	}
}
