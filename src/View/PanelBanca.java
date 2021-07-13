/*Alunos:
 * Pedro Antônio Tibau Velozo - 1812013
 * Yuri Lemos - 1610893
 * Rafael Feliciano - 1521772
 * */

package View;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

import Model.CtrlModel;

class PanelBanca extends JPanel implements MouseListener {
	public final int LARG_DEFAULT = 800;
	public final int ALT_DEFAULT = 600;

	private Image imagemMesa, imagem1dol, imagem10dol, imagem100dol, imagem20dol, imagem5dol, imagem50dol,
			imagemCarta1Arquivo, imagemCarta2Arquivo, imagemCarta2Escondida, imagemCarta3Arquivo = null,
			imagemCarta4Arquivo = null, imagemCarta5Arquivo = null;

	private boolean ehVezDaBanca = false;

	private boolean rodadaJaAcabou = false; // TODO: MUDAR ISSO QUANDO CLICAR EM NOVA RODADA

	private String primeiraCartaPrimeiroJogadorAntiga; // para verificar se uma nova rodada comecou e, se sim, mudar o
														// atributo rodadaJaAcabou para false
	private String primeiraCartaPrimeiroJogadorNova;

	private boolean jogadorNaoFezNada;

	private int primeiraVez = 0;

	private int vez;
	
	PanelBanca() {
		// imagemMesa = new ImageIcon("TesteIMG.png").getImage();
		this.setPreferredSize(new Dimension(LARG_DEFAULT, ALT_DEFAULT));

		// DEALER
		CtrlModel.getCtrlModel().instaciaDealer();
		String[] cartasDealer = CtrlModel.getCtrlModel().getCartaInfoDealer();
		// System.out.println(Arrays.toString(cartasDealer));

		String carta1DealerArquivo = "src/Imagens/" + cartasDealer[0] + ".gif";
		String carta2DealerArquivo = "src/Imagens/" + cartasDealer[1] + ".gif";

		// System.out.println(carta1DealerArquivo);
		// System.out.println(carta2DealerArquivo);

		try {
			imagemCarta1Arquivo = ImageIO.read(new File(carta1DealerArquivo));
			imagemCarta2Arquivo = ImageIO.read(new File(carta2DealerArquivo));
			imagemCarta2Escondida = ImageIO.read(new File("src/Imagens/deck1.gif"));
			// TODO CARTA ESCONDIDA

			imagemMesa = ImageIO.read(new File("src/Imagens/blackjackBKG.png"));

			imagem1dol = ImageIO.read(new File("src/Imagens/ficha 1$.png"));
			imagem10dol = ImageIO.read(new File("src/Imagens/ficha 10$.png"));
			imagem100dol = ImageIO.read(new File("src/Imagens/ficha 100$.png"));
			imagem20dol = ImageIO.read(new File("src/Imagens/ficha 20$.png"));
			imagem5dol = ImageIO.read(new File("src/Imagens/ficha 5$.png"));
			imagem50dol = ImageIO.read(new File("src/Imagens/ficha 50$.png"));

		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	/** PARTE DO OBSERVER **/
	public void atualizaPanelBanca(String[][] infoDosJogadores) {

		int numeroJogadores = infoDosJogadores.length - 1;

		System.out.println("\n\ninfoDosJogadores.length - 1 = " + (infoDosJogadores.length - 1) + "\n\n");

		int vezDaBanca = numeroJogadores; // pq vez 1o jogador = 0; vez 2o jogador = 1; ...

		int vezDaRodada = Integer.parseInt(infoDosJogadores[0][infoDosJogadores[0].length - 2]);

		String[] infoDoDealer = CtrlModel.getCtrlModel().getCartaInfoDealer();

		System.out.println("VETOR DO DEALER = " + Arrays.toString(infoDoDealer));

		// vendo 1a carta do 1o jogador
		this.primeiraCartaPrimeiroJogadorNova = infoDosJogadores[0][0];

		if (!this.primeiraCartaPrimeiroJogadorNova.equals(this.primeiraCartaPrimeiroJogadorAntiga)) {
			this.rodadaJaAcabou = false;
		}

		this.primeiraCartaPrimeiroJogadorAntiga = infoDosJogadores[0][0];

		if (vezDaBanca == vezDaRodada) {
			// mostro a carta escondida
			this.ehVezDaBanca = true;

			// TODO: MUDAR A LOGICA DO DEALER AQUI (compra de cartas)
			// verifco quantas cartas a banca pode comprar
			infoDoDealer = CtrlModel.getCtrlModel().getCartaInfoDealer(); // EX: info = {"4d", "5c", "9"};

			System.out.println("VETOR DO DEALER = " + Arrays.toString(infoDoDealer));

			int somaCartasDealer = Integer.parseInt(infoDoDealer[infoDoDealer.length - 1]);

			System.out.println("somaCartasDealer = " + somaCartasDealer);

			// dealer compra cartas ate ter no minimio 17 na soma
			while (somaCartasDealer < 17) {
				CtrlModel.getCtrlModel().dealerPedeCarta();
				infoDoDealer = CtrlModel.getCtrlModel().getCartaInfoDealer();
				System.out.println("while VETOR DO DEALER = " + Arrays.toString(infoDoDealer));

				somaCartasDealer = Integer.parseInt(infoDoDealer[infoDoDealer.length - 1]);
			}

			// logica para o dealer não ser tão simples
//			int somaPositiva = 0;
//			int somaNegativa = 0;
//
//			for (int i = 0; i < infoDosJogadores.length - 1; i++) {
//				String[] infoJogador = infoDosJogadores[i];
//				int somaCartasJogador = Integer.parseInt(infoJogador[infoJogador.length - 5]);
//				System.out.println("somaCartasJogador = " + somaCartasJogador);
//				System.out.println("somaCartasDealer= " + somaCartasDealer);
//				int valorApostaJogador = Integer.parseInt(infoJogador[infoJogador.length - 3]);
//				System.out.println("valorApostaJogador= " + valorApostaJogador);
//				if (somaCartasJogador > somaCartasDealer && somaCartasJogador <= 21) {
//					somaNegativa += valorApostaJogador;
//				} else {
//					somaPositiva += valorApostaJogador;
//				}
//			}
//			
//			System.out.println("somaPositiva = " + somaPositiva);
//			System.out.println("somaNegativa = " + somaNegativa);
//			
//			// se tem mais mais jogadores ganhado do que perdendo do dealer
//			if (somaNegativa > somaPositiva && (somaCartasDealer == 17 || somaCartasDealer == 18)) {
//				CtrlModel.getCtrlModel().dealerPedeCarta();
//			}
//
//			// dealer ta perdendo para todos os jogadores, então pode comprar carta
//			if (somaPositiva == 0 && somaCartasDealer < 21) {
//				CtrlModel.getCtrlModel().dealerPedeCarta();
//			}
			int numeroDeCartasDoDealer = infoDoDealer.length - 1;

			switch (numeroDeCartasDoDealer) {
			case 2:
				try {
					imagemCarta1Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[0] + ".gif"));
					if (this.ehVezDaBanca) {
						imagemCarta2Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[1] + ".gif"));
					} else {
						imagemCarta2Arquivo = ImageIO.read(new File("src/Imagens/deck1.gif"));
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
					System.exit(1);
				}
				this.repaint();
				break;

			case 3:
				try {
					imagemCarta1Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[0] + ".gif"));
					if (this.ehVezDaBanca) {
						imagemCarta2Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[1] + ".gif"));
					} else {
						imagemCarta2Arquivo = ImageIO.read(new File("src/Imagens/deck1.gif"));
					}
					imagemCarta3Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[2] + ".gif"));
				} catch (IOException e) {
					System.out.println(e.getMessage());
					System.exit(1);
				}
				this.repaint();
				break;
			case 4:
				try {
					imagemCarta1Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[0] + ".gif"));
					if (this.ehVezDaBanca) {
						imagemCarta2Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[1] + ".gif"));
					} else {
						imagemCarta2Arquivo = ImageIO.read(new File("src/Imagens/deck1.gif"));
					}
					imagemCarta3Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[2] + ".gif"));
					imagemCarta4Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[3] + ".gif"));
				} catch (IOException e) {
					System.out.println(e.getMessage());
					System.exit(1);
				}
				this.repaint();
				break;
			case 5:
				try {
					imagemCarta1Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[0] + ".gif"));
					if (this.ehVezDaBanca) {
						imagemCarta2Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[1] + ".gif"));
					} else {
						imagemCarta2Arquivo = ImageIO.read(new File("src/Imagens/deck1.gif"));
					}
					imagemCarta3Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[2] + ".gif"));
					imagemCarta4Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[3] + ".gif"));
					imagemCarta5Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[4] + ".gif"));
				} catch (IOException e) {
					System.out.println(e.getMessage());
					System.exit(1);
				}
				this.repaint();
				break;
			default:
				break;
			}

			infoDoDealer = CtrlModel.getCtrlModel().getCartaInfoDealer();

			System.out.println("AAAAAAAAAAAAAAA numeroJogadores = " + numeroJogadores);

			if (this.rodadaJaAcabou == false) {
				this.rodadaJaAcabou = true;
				CtrlModel.getCtrlModel().fimDaRodada(infoDosJogadores, infoDoDealer);
			}
		} else {
			this.ehVezDaBanca = false;
		}

		this.repaint();

		// TODO: ver quando o jogador nao pode apostar mais, ou seja, nao pode mais dar
		// fichas
		// Ideia: olhar numero de cartas na mao dele e, se for > 2, ele nao pode mais
		// apostar

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g;

		g2D.drawImage(imagemMesa, 0, 0, LARG_DEFAULT, ALT_DEFAULT, this);

		// System.out.println("imagemCarta1Arquivo = " +
		// imagemCarta1Arquivo.toString());

		// String[] infoDoDealer = CtrlModel.getCtrlModel().getCartaInfoDealer();

		this.recarregaImagens();

		g2D.drawImage(imagemCarta1Arquivo, LARG_DEFAULT * 25 / 100, ALT_DEFAULT * 18 / 100, null);
		g2D.drawImage(imagemCarta2Escondida, LARG_DEFAULT * 35 / 100, ALT_DEFAULT * 18 / 100, null);

		if (this.ehVezDaBanca) {
			g2D.drawImage(imagemCarta2Arquivo, LARG_DEFAULT * 35 / 100, ALT_DEFAULT * 18 / 100, null);
		} else {
			// g2D.drawImage(imagemCarta2Escondida, LARG_DEFAULT * 35 / 100, ALT_DEFAULT *
			// 18 / 100, null);
		}

		if (this.imagemCarta3Arquivo != null) {
			g2D.drawImage(imagemCarta3Arquivo, LARG_DEFAULT * 45 / 100, ALT_DEFAULT * 18 / 100, null);
		}

		if (this.imagemCarta4Arquivo != null) {
			g2D.drawImage(imagemCarta4Arquivo, LARG_DEFAULT * 55 / 100, ALT_DEFAULT * 18 / 100, null);
		}

		if (this.imagemCarta5Arquivo != null) {
			g2D.drawImage(imagemCarta5Arquivo, LARG_DEFAULT * 65 / 100, ALT_DEFAULT * 18 / 100, null);
		}

		g2D.drawImage(imagem1dol, LARG_DEFAULT * 25 / 100, ALT_DEFAULT * 815 / 1000, null);
		g2D.drawImage(imagem5dol, LARG_DEFAULT * 34 / 100, ALT_DEFAULT * 815 / 1000, null);
		g2D.drawImage(imagem10dol, LARG_DEFAULT * 43 / 100, ALT_DEFAULT * 815 / 1000, null);
		g2D.drawImage(imagem20dol, LARG_DEFAULT * 52 / 100, ALT_DEFAULT * 815 / 1000, null);
		g2D.drawImage(imagem50dol, LARG_DEFAULT * 61 / 100, ALT_DEFAULT * 815 / 1000, null);
		g2D.drawImage(imagem100dol, LARG_DEFAULT * 70 / 100, ALT_DEFAULT * 815 / 1000, null);
	}

	public void recarregaImagens() {
		String[] infoDoDealer = CtrlModel.getCtrlModel().getCartaInfoDealer();

		int numeroDeCartasDoDealer = infoDoDealer.length - 1;

		switch (numeroDeCartasDoDealer) {
		case 2:
			try {
				imagemCarta1Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[0] + ".gif"));
				imagemCarta2Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[1] + ".gif"));
				imagemCarta3Arquivo = null;
				imagemCarta4Arquivo = null;
				imagemCarta5Arquivo = null;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
			break;

		case 3:
			try {
				imagemCarta1Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[0] + ".gif"));
				imagemCarta2Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[1] + ".gif"));
				imagemCarta3Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[2] + ".gif"));
				imagemCarta4Arquivo = null;
				imagemCarta5Arquivo = null;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
			break;
		case 4:
			try {
				imagemCarta1Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[0] + ".gif"));
				imagemCarta2Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[1] + ".gif"));
				imagemCarta3Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[2] + ".gif"));
				imagemCarta4Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[3] + ".gif"));
				imagemCarta5Arquivo = null;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
			break;
		case 5:
			try {
				imagemCarta1Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[0] + ".gif"));
				imagemCarta2Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[1] + ".gif"));
				imagemCarta3Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[2] + ".gif"));
				imagemCarta4Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[3] + ".gif"));
				imagemCarta5Arquivo = ImageIO.read(new File("src/Imagens/" + infoDoDealer[4] + ".gif"));
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
			;
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();

		System.out.println("x = " + x);
		System.out.println("y = " + y);
		System.out.println();

		if ((x >= LARG_DEFAULT * 25 / 100 && x <= LARG_DEFAULT * 33 / 100)
				&& (y >= ALT_DEFAULT * 86 / 100 && y <= ALT_DEFAULT * 97 / 100)) {
			System.out.println("FICHA $1 CLICKADA");
			CtrlModel.getCtrlModel().clickFicha1();

			if (this.primeiraVez == this.vez) {
				this.primeiraVez++;
			}
		}

		if ((x >= LARG_DEFAULT * 34 / 100 && x <= LARG_DEFAULT * 425 / 1000)
				&& (y >= ALT_DEFAULT * 86 / 100 && y <= ALT_DEFAULT * 97 / 100)) {
			System.out.println("FICHA $5 CLICKADA");
			CtrlModel.getCtrlModel().clickFicha5();

			if (this.primeiraVez == this.vez) {
				this.primeiraVez++;
			}
		}

		if ((x >= LARG_DEFAULT * 43 / 100 && x <= LARG_DEFAULT * 52 / 100)
				&& (y >= ALT_DEFAULT * 86 / 100 && y <= ALT_DEFAULT * 97 / 100)) {
			System.out.println("FICHA $10 CLICKADA");
			CtrlModel.getCtrlModel().clickFicha10();

			if (this.primeiraVez == this.vez) {
				this.primeiraVez++;
			}
		}

		if ((x >= LARG_DEFAULT * 52 / 100 && x <= LARG_DEFAULT * 61 / 100)
				&& (y >= ALT_DEFAULT * 86 / 100 && y <= ALT_DEFAULT * 97 / 100)) {
			System.out.println("FICHA $20 CLICKADA");
			CtrlModel.getCtrlModel().clickFicha20();

			if (this.primeiraVez == this.vez) {
				System.out.println("\n\nENTREI AQUI = \n\n" + this.primeiraVez + this.vez);
				this.primeiraVez++;
			}
		}

		if ((x >= LARG_DEFAULT * 61 / 100 && x <= LARG_DEFAULT * 71 / 100)
				&& (y >= ALT_DEFAULT * 86 / 100 && y <= ALT_DEFAULT * 97 / 100)) {
			System.out.println("FICHA $50 CLICKADA");
			CtrlModel.getCtrlModel().clickFicha50();

			if (this.primeiraVez == this.vez) {
				this.primeiraVez++;
			}
		}

		if ((x >= LARG_DEFAULT * 70 / 100 && x <= LARG_DEFAULT * 81 / 100)
				&& (y >= ALT_DEFAULT * 86 / 100 && y <= ALT_DEFAULT * 97 / 100)) {
			System.out.println("FICHA $100 CLICKADA");
			CtrlModel.getCtrlModel().clickFicha100();

			if (this.primeiraVez == this.vez) {
				this.primeiraVez++;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public boolean isJogadorNaoFezNada() {
		return jogadorNaoFezNada;
	}

	public void setJogadorNaoFezNada(boolean jogadorNaoFezNada) {
		this.jogadorNaoFezNada = jogadorNaoFezNada;
	}

	public void setVez(int vez) {
		this.vez = vez;
	}

	public int getPrimeiraVez() {
		return this.primeiraVez;
	}

	public void setPrimeiraVez(int i) {
		this.primeiraVez = i;
	}
}
