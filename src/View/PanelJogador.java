/*Alunos:
 * Pedro Antônio Tibau Velozo - 1812013
 * Yuri Lemos - 1610893
 * Rafael Feliciano - 1521772
 * */

package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Model.ObservadoIF;
import Model.ObservadorIF;

class PanelJogador extends JPanel implements ObservadorIF {
	public final int LARG_DEFAULT = 400;
	public final int ALT_DEFAULT = 400;

	private Image imagemCarta1, imagemCarta2, imagemCarta3 = null, imagemCarta4 = null, imagemCarta5 = null; // podemos
																												// ter
																												// no
																												// maximo
																												// 5
																												// cartas

	private String carta1JogadorArquivo, carta2JogadorArquivo, carta3JogadorArquivo, carta4JogadorArquivo,
			carta5JogadorArquivo;

	private String qtfFicha1, qtfFicha5, qtfFicha10, qtfFicha20, qtfFicha50, qtfFicha100;

	private String somaCreditos, valorAposta, nomeJogador, somaCartas;

	private boolean jogadorApertouDeal = false;

	private String primeiraCartaPrimeiroJogadorAntiga; // para verificar se uma nova rodada comecou e, se sim, mudar o
	// atributo rodadaJaAcabou para false
	private String primeiraCartaPrimeiroJogadorNova;

	private int vezRodada;
	
	private boolean jogoFoiCarregado = false;

	// infoJogador tem as cartas e a pontuacao do jogador no formato {carta1, ...,
	// cartaN, somaCartas, nomeJogador}
	PanelJogador(String[] infoJogador) {

		this.setPreferredSize(new Dimension(LARG_DEFAULT, ALT_DEFAULT));

		carta1JogadorArquivo = "src/Imagens/" + infoJogador[0] + ".gif";
		carta2JogadorArquivo = "src/Imagens/" + infoJogador[1] + ".gif";

		// System.out.println("carta1JogadorArquivo = " + carta1JogadorArquivo);
		// System.out.println("carta2JogadorArquivo = " + carta2JogadorArquivo);

		try {
			imagemCarta1 = ImageIO.read(new File(carta1JogadorArquivo));
			imagemCarta2 = ImageIO.read(new File(carta2JogadorArquivo));

		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

		System.out.println("infoJgoador = " + Arrays.toString(infoJogador));
		somaCartas = infoJogador[infoJogador.length - 5];

		somaCreditos = infoJogador[infoJogador.length - 4];

		valorAposta = infoJogador[infoJogador.length - 3];

		nomeJogador = infoJogador[infoJogador.length - 1];

		vezRodada = Integer.parseInt(infoJogador[infoJogador.length - 2]);

		this.repaint();
	}

	// panel para quando a partida for carrega, nesse caso vou se a vez do jogador
	// do panel
	// já passou, se sim, desenho as informações dele
	public PanelJogador(String[] infoJogador, String[] cartasJogador) {
		// TODO Auto-generated constructor stub
		
		jogoFoiCarregado = true;

		int numeroCartas = cartasJogador.length;

		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA");

		System.out.println("numeroCartas = " + numeroCartas);
		switch (numeroCartas) {

		case 2:

			// tem 3 cartas o jogador
			carta1JogadorArquivo = "src/Imagens/" + infoJogador[0] + ".gif";
			carta2JogadorArquivo = "src/Imagens/" + infoJogador[1] + ".gif";


			try {
				imagemCarta1 = ImageIO.read(new File(carta1JogadorArquivo));
				imagemCarta2 = ImageIO.read(new File(carta2JogadorArquivo));

			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}

			// atualizando o valor da aposta e soma creditos e quantidade de fichas
			somaCartas = infoJogador[3];
			somaCreditos = infoJogador[4];
			valorAposta = infoJogador[5];

			break;

		case 3:
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
			
			// tem 4 cartas o jogador
			carta1JogadorArquivo = "src/Imagens/" + infoJogador[0] + ".gif";
			carta2JogadorArquivo = "src/Imagens/" + infoJogador[1] + ".gif";
			carta3JogadorArquivo = "src/Imagens/" + infoJogador[2] + ".gif";

			try {

				imagemCarta1 = ImageIO.read(new File(carta1JogadorArquivo));

				imagemCarta2 = ImageIO.read(new File(carta2JogadorArquivo));

				imagemCarta3 = ImageIO.read(new File(carta3JogadorArquivo));

			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}

			// atualizando o valor da aposta e soma creditos
			somaCartas = infoJogador[4];
			somaCreditos = infoJogador[5];
			valorAposta = infoJogador[6];
			
			break;

		case 4:
			// tem 5 cartas o jogador
			carta1JogadorArquivo = "src/Imagens/" + infoJogador[0] + ".gif";
			carta2JogadorArquivo = "src/Imagens/" + infoJogador[1] + ".gif";
			carta3JogadorArquivo = "src/Imagens/" + infoJogador[2] + ".gif";
			carta4JogadorArquivo = "src/Imagens/" + infoJogador[3] + ".gif";

			try {
				imagemCarta1 = ImageIO.read(new File(carta1JogadorArquivo));
				imagemCarta2 = ImageIO.read(new File(carta2JogadorArquivo));
				imagemCarta3 = ImageIO.read(new File(carta3JogadorArquivo));
				imagemCarta4 = ImageIO.read(new File(carta4JogadorArquivo));

			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}

			// atualizando o valor da aposta e soma creditos
			somaCartas = infoJogador[5];
			somaCreditos = infoJogador[6];
			valorAposta = infoJogador[7];

			break;

		default:
			// tem 2 cartas o jogador
			carta1JogadorArquivo = "src/Imagens/" + infoJogador[0] + ".gif";
			carta2JogadorArquivo = "src/Imagens/" + infoJogador[1] + ".gif";

			try {
				imagemCarta1 = ImageIO.read(new File(carta1JogadorArquivo));
				imagemCarta2 = ImageIO.read(new File(carta2JogadorArquivo));

			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}

			imagemCarta3 = null;
			imagemCarta4 = null;
			imagemCarta5 = null;

			// atualizando o valor da aposta e soma creditos
			somaCartas = infoJogador[2];
			somaCreditos = infoJogador[3];
			valorAposta = infoJogador[4];
		}

		this.setPreferredSize(new Dimension(LARG_DEFAULT, ALT_DEFAULT));

		carta1JogadorArquivo = "src/Imagens/" + infoJogador[0] + ".gif";
		carta2JogadorArquivo = "src/Imagens/" + infoJogador[1] + ".gif";

		// System.out.println("carta1JogadorArquivo = " + carta1JogadorArquivo);
		// System.out.println("carta2JogadorArquivo = " + carta2JogadorArquivo);

		try {
			imagemCarta1 = ImageIO.read(new File(carta1JogadorArquivo));
			imagemCarta2 = ImageIO.read(new File(carta2JogadorArquivo));

		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

		System.out.println("infoJgoador = " + Arrays.toString(infoJogador));
		somaCartas = infoJogador[infoJogador.length - 5];

		somaCreditos = infoJogador[infoJogador.length - 4];

		valorAposta = infoJogador[infoJogador.length - 3];

		nomeJogador = infoJogador[infoJogador.length - 1];

		vezRodada = Integer.parseInt(infoJogador[infoJogador.length - 2]);

		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// System.out.println("paintComponent do Jogador rodando");

		Graphics2D g2D = (Graphics2D) g;

		// cartas jogador
		if (this.jogadorApertouDeal) {
			g2D.drawImage(imagemCarta1, LARG_DEFAULT * 30 / 100, ALT_DEFAULT * 40 / 100, null);
			g2D.drawImage(imagemCarta2, LARG_DEFAULT * 35 / 100, ALT_DEFAULT * 40 / 100, null); // TODO VER QUANDO
																								// MOSTRAR
																								// ESSA CARTA
		}
		
		if (this.jogoFoiCarregado) {
			g2D.drawImage(imagemCarta1, LARG_DEFAULT * 30 / 100, ALT_DEFAULT * 40 / 100, null);
			g2D.drawImage(imagemCarta2, LARG_DEFAULT * 35 / 100, ALT_DEFAULT * 40 / 100, null);

			
			g2D.setFont(new Font("Verdana", Font.BOLD, 15));
			g2D.drawString("Soma Créditos: " + somaCreditos, LARG_DEFAULT * 5 / 100, ALT_DEFAULT * 16 / 100);
			g2D.drawString("Soma Cartas: " + somaCartas, LARG_DEFAULT * 5 / 100, ALT_DEFAULT * 34 / 100);
		}

		// desenhando as cartas 3, 4 e 5 se o jogador tiver
		if (imagemCarta3 != null) {
			g2D.drawImage(imagemCarta3, LARG_DEFAULT * 40 / 100, ALT_DEFAULT * 40 / 100, null);
		}
		if (imagemCarta4 != null) {
			g2D.drawImage(imagemCarta4, LARG_DEFAULT * 45 / 100, ALT_DEFAULT * 40 / 100, null);
		}
		if (imagemCarta5 != null) {
			g2D.drawImage(imagemCarta5, LARG_DEFAULT * 50 / 100, ALT_DEFAULT * 40 / 100, null);
		}

		// total de creditos do jogador
		g2D.setFont(new Font("Verdana", Font.BOLD, 15));
		g2D.drawString("Soma Créditos: " + somaCreditos, LARG_DEFAULT * 5 / 100, ALT_DEFAULT * 16 / 100);

		// valor da aposta do jogador
		g2D.drawString("Valor da Aposta: " + valorAposta, LARG_DEFAULT * 5 / 100, ALT_DEFAULT * 25 / 100);

		// soma das cartas
		if (this.jogadorApertouDeal) {
			g2D.drawString("Soma Cartas: " + somaCartas, LARG_DEFAULT * 5 / 100, ALT_DEFAULT * 34 / 100);
		}

		// nome do jogador
		g2D.setFont(new Font("Verdana", Font.BOLD, 18));
		g2D.drawString("Janela do(a) " + nomeJogador, 100, 25);

	}

	public void dealApertado() {
		this.jogadorApertouDeal = true;
		this.repaint();
	}

	@Override
	public void notificaObservadores(ObservadoIF o) {
		// String[][] teste = o.getInfoDoModel();
	}

	public void setInfoTodosOsJogadores(String[][] arrDeStrings) {
	}

	public void atualizaPanelJogador(String[][] infoDosJogadores) {

		// Exemplo
		// String[][] infoDosJogadores = {{"1c", "5d", 16, "Pedro"}, {{"1c", "5d",
		// "10h", 16, "Bruna"},
		// {{"1c", "5d", "10h", 16, "Camila"}}

		// Tenho que descobrir qual dos arrays representa o jogador da janela,
		// para isso tenho que comparar os nomes do array

		for (String[] infoJogaodr : infoDosJogadores)
			System.out.println("\n\ninfoJgoador = " + Arrays.toString(infoJogaodr));

		int indexDoArrDoJogadorDessePanel = retornaIndexDoArrQueRepresentaJogadorDoPanel(infoDosJogadores);

		if (indexDoArrDoJogadorDessePanel == this.vezRodada) {
			this.repaint();
		}

		String ArrDoJogadorDessePanel[] = infoDosJogadores[indexDoArrDoJogadorDessePanel];

		int indexUltimaCartanNoArr = getIndexUltimaCartaNoArr(ArrDoJogadorDessePanel);

		this.primeiraCartaPrimeiroJogadorNova = infoDosJogadores[0][0];

		// vendo 1a carta do 1o jogador
		if (!this.primeiraCartaPrimeiroJogadorNova.equals(this.primeiraCartaPrimeiroJogadorAntiga)) {
			this.jogadorApertouDeal = false;
		}

		this.primeiraCartaPrimeiroJogadorAntiga = infoDosJogadores[0][0];

		switch (indexUltimaCartanNoArr - 1) {

		case 2:
			// tem 3 cartas o jogador
			carta1JogadorArquivo = "src/Imagens/" + ArrDoJogadorDessePanel[0] + ".gif";
			carta2JogadorArquivo = "src/Imagens/" + ArrDoJogadorDessePanel[1] + ".gif";
			carta3JogadorArquivo = "src/Imagens/" + ArrDoJogadorDessePanel[2] + ".gif";

			try {
				imagemCarta1 = ImageIO.read(new File(carta1JogadorArquivo));
				imagemCarta2 = ImageIO.read(new File(carta2JogadorArquivo));
				imagemCarta3 = ImageIO.read(new File(carta3JogadorArquivo));

			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}

			// atualizando o valor da aposta e soma creditos e quantidade de fichas
			somaCartas = ArrDoJogadorDessePanel[3];
			somaCreditos = ArrDoJogadorDessePanel[4];
			valorAposta = ArrDoJogadorDessePanel[5];

			break;

		case 3:
			// tem 4 cartas o jogador
			carta1JogadorArquivo = "src/Imagens/" + ArrDoJogadorDessePanel[0] + ".gif";
			carta2JogadorArquivo = "src/Imagens/" + ArrDoJogadorDessePanel[1] + ".gif";
			carta3JogadorArquivo = "src/Imagens/" + ArrDoJogadorDessePanel[2] + ".gif";
			carta4JogadorArquivo = "src/Imagens/" + ArrDoJogadorDessePanel[3] + ".gif";

			try {
				imagemCarta1 = ImageIO.read(new File(carta1JogadorArquivo));
				imagemCarta2 = ImageIO.read(new File(carta2JogadorArquivo));
				imagemCarta3 = ImageIO.read(new File(carta3JogadorArquivo));
				imagemCarta4 = ImageIO.read(new File(carta4JogadorArquivo));

			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}

			// atualizando o valor da aposta e soma creditos
			somaCartas = ArrDoJogadorDessePanel[4];
			somaCreditos = ArrDoJogadorDessePanel[5];
			valorAposta = ArrDoJogadorDessePanel[6];

			break;

		case 4:
			// tem 5 cartas o jogador
			carta1JogadorArquivo = "src/Imagens/" + ArrDoJogadorDessePanel[0] + ".gif";
			carta2JogadorArquivo = "src/Imagens/" + ArrDoJogadorDessePanel[1] + ".gif";
			carta3JogadorArquivo = "src/Imagens/" + ArrDoJogadorDessePanel[2] + ".gif";
			carta4JogadorArquivo = "src/Imagens/" + ArrDoJogadorDessePanel[3] + ".gif";
			carta5JogadorArquivo = "src/Imagens/" + ArrDoJogadorDessePanel[4] + ".gif";

			try {
				imagemCarta1 = ImageIO.read(new File(carta1JogadorArquivo));
				imagemCarta2 = ImageIO.read(new File(carta2JogadorArquivo));
				imagemCarta3 = ImageIO.read(new File(carta3JogadorArquivo));
				imagemCarta4 = ImageIO.read(new File(carta4JogadorArquivo));
				imagemCarta5 = ImageIO.read(new File(carta5JogadorArquivo));

			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}

			// atualizando o valor da aposta e soma creditos
			somaCartas = ArrDoJogadorDessePanel[5];
			somaCreditos = ArrDoJogadorDessePanel[6];
			valorAposta = ArrDoJogadorDessePanel[7];

			break;

		default:
			// tem 2 cartas o jogador
			carta1JogadorArquivo = "src/Imagens/" + ArrDoJogadorDessePanel[0] + ".gif";
			carta2JogadorArquivo = "src/Imagens/" + ArrDoJogadorDessePanel[1] + ".gif";

			try {
				imagemCarta1 = ImageIO.read(new File(carta1JogadorArquivo));
				imagemCarta2 = ImageIO.read(new File(carta2JogadorArquivo));

			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}

			imagemCarta3 = null;
			imagemCarta4 = null;
			imagemCarta5 = null;

			// atualizando o valor da aposta e soma creditos
			somaCartas = ArrDoJogadorDessePanel[2];
			somaCreditos = ArrDoJogadorDessePanel[3];
			valorAposta = ArrDoJogadorDessePanel[4];
		}

		// redesenho o painel
		this.repaint();
	}

	// funcao auxiliar
	private int getIndexUltimaCartaNoArr(String[] arr) { // Ex: {"1d, 10h, 2c, 10, 29, PEdor"} -> 2
		for (int index = 0; index < arr.length; index++) {
			// verifico se o ultimo char eh uma letra
			String ultimaLetra = arr[index].substring(arr[index].length() - 1);
			if (!ultimaLetra.equals("s") && !ultimaLetra.equals("h") && !ultimaLetra.equals("c")
					&& !ultimaLetra.equals("d")) {
				return index;
			}
		}
		return -1; // nunca chega aqui
	}

	private int retornaIndexDoArrQueRepresentaJogadorDoPanel(String[][] infoDosJogadores) {
		// Procuro qual elemento do vetor de vetores de String tem o nome do Jogador do
		// Panel
		for (int i = 0; i < infoDosJogadores.length; i++) {
			if (infoDosJogadores[i][infoDosJogadores[i].length - 1].equals(nomeJogador)) {
				System.out.println("i = " + i);
				return i;
			}
		}
		return -1; // nunca chega aqui
	}
}
