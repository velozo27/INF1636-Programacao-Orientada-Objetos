package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Scanner;

public class CtrlModel implements ObservadoIF {

	private static Rodada rodada = null;

	private static CtrlModel ctrl = null;

	private Baralho baralho = new Baralho();
	private static Dealer dealer = null;

	private static Jogador jogador1 = null;
	private static Jogador jogador2 = null;
	private static Jogador jogador3 = null;
	private static Jogador jogador4 = null;

	/** Coisas do Observer **/
	private ArrayList<ObservadorIF> observadoresLst = new ArrayList<ObservadorIF>();

	private CtrlModel() {

	}

	public static CtrlModel getCtrlModel() {
		if (ctrl == null)
			ctrl = new CtrlModel();
		return ctrl;
	}

	/** INSATNCIA DO DEALER **/
	public void instaciaDealer() {
		if (dealer == null) {
			if (baralho.foiDistribuido10PorCento()) {
				baralho.embaralhar();
			}
			baralho.embaralhar();

			Carta c1 = baralho.retiraCarta();
			Carta c2 = baralho.retiraCarta();
			Dealer dealer = new Dealer(c1, c2);
			CtrlModel.dealer = dealer;
		}
	}

	public void instaciaDealer(String[] cartas) {
		Carta[] cartasDealer = new Carta[0];
		for (String carta : cartas) {
			// numero < 10 ou q, j, k
			if (carta.length() == 2) {
				if (Character.isDigit(carta.charAt(0))) {
					int valorCarta = Character.getNumericValue(carta.charAt(0));
					char naipeCarta = carta.charAt(1);
					Carta cartaNova = new Carta(valorCarta, naipeCarta);
					cartasDealer = Arrays.copyOf(cartasDealer, cartasDealer.length + 1);
					cartasDealer[cartasDealer.length - 1] = cartaNova;
				} else {
					// carta eh q, j, k
					int valorCarta = this.converteNomeCartaParaValor(carta.charAt(0));
					char naipeCarta = carta.charAt(1);
					Carta cartaNova = new Carta(valorCarta, naipeCarta);
					cartasDealer = Arrays.copyOf(cartasDealer, cartasDealer.length + 1);
					cartasDealer[cartasDealer.length - 1] = cartaNova;
				}
			} else if (carta.length() == 3) {
				// unico caso eh carta ser 10
				char naipeCarta = carta.charAt(carta.length() - 1);
				Carta cartaNova = new Carta(10, naipeCarta);
				cartasDealer = Arrays.copyOf(cartasDealer, cartasDealer.length + 1);
				cartasDealer[cartasDealer.length - 1] = cartaNova;
			}
		}
		for (Carta carta : cartasDealer)
			carta.imprimeCarta();
		Dealer dealerNovo = new Dealer(cartasDealer);
		CtrlModel.dealer = dealerNovo;
	}

	private int converteNomeCartaParaValor(char nomeCarta) {
		switch (nomeCarta) {
		case 'j':
			return 11;
		case 'q':
			return 12;
		case 'k':
			return 13;
		case 'a':
			return 1;
		case 't':
			return 10;
		}
		return -1; // nao chega aqui
	}

	public String[] getCartaInfoDealer() {
		return CtrlModel.dealer.getCartasString();
	}

	/** INSTANCIAS DOS JOGADORES **/
	public void instaciaJogador1(String nome) {
		if (jogador1 == null) {
			Carta c1 = baralho.retiraCarta();
			Carta c2 = baralho.retiraCarta();
			Jogador jogador1 = new Jogador(nome, c1, c2);
			CtrlModel.jogador1 = jogador1;
		}
	}

	public void instaciaJogador1(String nome, String[] cartas) { // cartas = ["10d", "kc"] por exemplo
		Carta[] cartasJogador = new Carta[0];
		System.out.println("parametro nome = " + nome);
		for (String carta : cartas) {
			// numero < 10 ou q, j, k
			if (carta.length() == 2) {
				if (Character.isDigit(carta.charAt(0))) {
					int valorCarta = Character.getNumericValue(carta.charAt(0));
					char naipeCarta = carta.charAt(1);
					Carta cartaNova = new Carta(valorCarta, naipeCarta);
					cartasJogador = Arrays.copyOf(cartasJogador, cartasJogador.length + 1);
					cartasJogador[cartasJogador.length - 1] = cartaNova;
				} else {
					// carta eh q, j, k, a
					int valorCarta = this.converteNomeCartaParaValor(carta.charAt(0));
					char naipeCarta = carta.charAt(1);
					Carta cartaNova = new Carta(valorCarta, naipeCarta);
					cartasJogador = Arrays.copyOf(cartasJogador, cartasJogador.length + 1);
					cartasJogador[cartasJogador.length - 1] = cartaNova;
				}
			} else if (carta.length() == 3) {
				// unico caso eh carta ser 10
				char naipeCarta = carta.charAt(carta.length() - 1);
				Carta cartaNova = new Carta(10, naipeCarta);
				cartasJogador = Arrays.copyOf(cartasJogador, cartasJogador.length + 1);
				cartasJogador[cartasJogador.length - 1] = cartaNova;
			}
		}
		for (Carta carta : cartasJogador)
			carta.imprimeCarta();
		Jogador jogador1Novo = new Jogador(nome, cartasJogador);
		CtrlModel.jogador1 = jogador1Novo;
		CtrlModel.jogador1.setName(nome);
		CtrlModel.jogador1.imprimeJogador();
	}

	public String[] getCartaInfoJogador1() {
		return CtrlModel.jogador1.getCartasString(CtrlModel.rodada.getVez());
	}

	public void instaciaJogador2(String nome) {
		if (jogador2 == null) {
			Carta c1 = baralho.retiraCarta();
			Carta c2 = baralho.retiraCarta();
			Jogador jogador2 = new Jogador(nome, c1, c2);
			CtrlModel.jogador2 = jogador2;
		}
	}

	public void instaciaJogador2(String nome, String[] cartas) { // cartas = ["10d", "kc"] por exemplo
		Carta[] cartasJogador = new Carta[0];
		System.out.println("parametro nome = " + nome);
		for (String carta : cartas) {
			// numero < 10 ou q, j, k
			if (carta.length() == 2) {
				if (Character.isDigit(carta.charAt(0))) {
					int valorCarta = Character.getNumericValue(carta.charAt(0));
					char naipeCarta = carta.charAt(1);
					Carta cartaNova = new Carta(valorCarta, naipeCarta);
					cartasJogador = Arrays.copyOf(cartasJogador, cartasJogador.length + 1);
					cartasJogador[cartasJogador.length - 1] = cartaNova;
				} else {
					// carta eh q, j, k
					int valorCarta = this.converteNomeCartaParaValor(carta.charAt(0));
					char naipeCarta = carta.charAt(1);
					Carta cartaNova = new Carta(valorCarta, naipeCarta);
					cartasJogador = Arrays.copyOf(cartasJogador, cartasJogador.length + 1);
					cartasJogador[cartasJogador.length - 1] = cartaNova;
				}
			} else if (carta.length() == 3) {
				// unico caso eh carta ser 10
				char naipeCarta = carta.charAt(carta.length() - 1);
				Carta cartaNova = new Carta(10, naipeCarta);
				cartasJogador = Arrays.copyOf(cartasJogador, cartasJogador.length + 1);
				cartasJogador[cartasJogador.length - 1] = cartaNova;
			}
		}
		for (Carta carta : cartasJogador)
			carta.imprimeCarta();
		Jogador jogador2Novo = new Jogador(nome, cartasJogador);
		CtrlModel.jogador2 = jogador2Novo;
		CtrlModel.jogador2.setName(nome);
		CtrlModel.jogador2.imprimeJogador();
	}

	public String[] getCartaInfoJogador2() {
		return CtrlModel.jogador2.getCartasString(CtrlModel.rodada.getVez());
	}

	public void instaciaJogador3(String nome) {
		if (jogador3 == null) {
			Carta c1 = baralho.retiraCarta();
			Carta c2 = baralho.retiraCarta();
			Jogador jogador3 = new Jogador(nome, c1, c2);
			CtrlModel.jogador3 = jogador3;
		}
	}

	public void instaciaJogador3(String nome, String[] cartas) { // cartas = ["10d", "kc"] por exemplo
		Carta[] cartasJogador = new Carta[0];
		System.out.println("parametro nome = " + nome);
		for (String carta : cartas) {
			// numero < 10 ou q, j, k
			if (carta.length() == 2) {
				if (Character.isDigit(carta.charAt(0))) {
					int valorCarta = Character.getNumericValue(carta.charAt(0));
					char naipeCarta = carta.charAt(1);
					Carta cartaNova = new Carta(valorCarta, naipeCarta);
					cartasJogador = Arrays.copyOf(cartasJogador, cartasJogador.length + 1);
					cartasJogador[cartasJogador.length - 1] = cartaNova;
				} else {
					// carta eh q, j, k
					int valorCarta = this.converteNomeCartaParaValor(carta.charAt(0));
					char naipeCarta = carta.charAt(1);
					Carta cartaNova = new Carta(valorCarta, naipeCarta);
					cartasJogador = Arrays.copyOf(cartasJogador, cartasJogador.length + 1);
					cartasJogador[cartasJogador.length - 1] = cartaNova;
				}
			} else if (carta.length() == 3) {
				// unico caso eh carta ser 10
				char naipeCarta = carta.charAt(carta.length() - 1);
				Carta cartaNova = new Carta(10, naipeCarta);
				cartasJogador = Arrays.copyOf(cartasJogador, cartasJogador.length + 1);
				cartasJogador[cartasJogador.length - 1] = cartaNova;
			}
		}
		for (Carta carta : cartasJogador)
			carta.imprimeCarta();
		Jogador jogador3Novo = new Jogador(nome, cartasJogador);
		CtrlModel.jogador3 = jogador3Novo;
		CtrlModel.jogador3.setName(nome);
		CtrlModel.jogador3.imprimeJogador();
	}

	public String[] getCartaInfoJogador3() {
		return CtrlModel.jogador3.getCartasString(CtrlModel.rodada.getVez());
	}

	public void instaciaJogador4(String nome) {
		if (jogador4 == null) {
			Carta c1 = baralho.retiraCarta();
			Carta c2 = baralho.retiraCarta();
			Jogador jogador4 = new Jogador(nome, c1, c2);
			CtrlModel.jogador4 = jogador4;
		}
	}

	public void instaciaJogador4(String nome, String[] cartas) { // cartas = ["10d", "kc"] por exemplo
		Carta[] cartasJogador = new Carta[0];
		System.out.println("parametro nome = " + nome);
		for (String carta : cartas) {
			// numero < 10 ou q, j, k
			if (carta.length() == 2) {
				if (Character.isDigit(carta.charAt(0))) {
					int valorCarta = Character.getNumericValue(carta.charAt(0));
					char naipeCarta = carta.charAt(1);
					Carta cartaNova = new Carta(valorCarta, naipeCarta);
					cartasJogador = Arrays.copyOf(cartasJogador, cartasJogador.length + 1);
					cartasJogador[cartasJogador.length - 1] = cartaNova;
				} else {
					// carta eh q, j, k
					int valorCarta = this.converteNomeCartaParaValor(carta.charAt(0));
					char naipeCarta = carta.charAt(1);
					Carta cartaNova = new Carta(valorCarta, naipeCarta);
					cartasJogador = Arrays.copyOf(cartasJogador, cartasJogador.length + 1);
					cartasJogador[cartasJogador.length - 1] = cartaNova;
				}
			} else if (carta.length() == 3) {
				// unico caso eh carta ser 10
				char naipeCarta = carta.charAt(carta.length() - 1);
				Carta cartaNova = new Carta(10, naipeCarta);
				cartasJogador = Arrays.copyOf(cartasJogador, cartasJogador.length + 1);
				cartasJogador[cartasJogador.length - 1] = cartaNova;
			}
		}
		for (Carta carta : cartasJogador)
			carta.imprimeCarta();
		Jogador jogador4Novo = new Jogador(nome, cartasJogador);
		CtrlModel.jogador4 = jogador4Novo;
		CtrlModel.jogador4.setName(nome);
		CtrlModel.jogador4.imprimeJogador();
	}

	public String[] getCartaInfoJogador4() {
		return CtrlModel.jogador4.getCartasString(CtrlModel.rodada.getVez());
	}

	/** INSTÂNCIAS DA RODADA **/
	public void instanciaRodada1Jogadores() {
		rodada = new Rodada(CtrlModel.jogador1, dealer);
		rodada.getBaralho().embaralhar();
	}

	public void instanciaRodada2Jogadores() {
		rodada = new Rodada(CtrlModel.jogador1, CtrlModel.jogador2, dealer);
		rodada.getBaralho().embaralhar();
	}

	public void instanciaRodada3Jogadores() {
		rodada = new Rodada(CtrlModel.jogador1, CtrlModel.jogador2, CtrlModel.jogador3, dealer);
		rodada.getBaralho().embaralhar();
	}

	public void instanciaRodada4Jogadores() {
		rodada = new Rodada(CtrlModel.jogador1, CtrlModel.jogador2, CtrlModel.jogador3, CtrlModel.jogador4, dealer);
		rodada.getBaralho().embaralhar();
	}

	public void instanciaRodadaNova() {
		// retiro todos os jogadores da rodada para instanciar uma rodada nova e reseta
		// o dealer
		CtrlModel.rodada.retiraTodosJogadoresDaRodada();

		CtrlModel.rodada.resetaJogadores();

		CtrlModel.rodada.resetaDealer();

		int NumJogadoresNaRodada = CtrlModel.rodada.getJogadoresRetiradosDaRodada().length;

		System.out.println("NumJogadoresNaRodada = " + NumJogadoresNaRodada);

		switch (NumJogadoresNaRodada) {
		case 1:
			this.instanciaRodada1Jogadores();
			break;
		case 2:
			this.instanciaRodada2Jogadores();
			break;
		case 3:
			this.instanciaRodada3Jogadores();
			break;
		case 4:
			this.instanciaRodada4Jogadores();
			break;
		default:
			break;
		}
		this.atualizaObservadores();
	}

	/** POSSIVEIS AÇÕES DO JOGO **/
	public void jogadorPedeStand(String nomeJogador) {
		if (rodada.verficaJogadorDaVezJogando(nomeJogador)) {
			rodada.exibeRodada();
			rodada.pedeStand();
		} else {
			System.out.println("NÃO É JOGADOR DA VEZ");
		}
		atualizaObservadores();
	}

	public void jogadorPedeHit(String nomeJogador) {
		if (rodada.verficaJogadorDaVezJogando(nomeJogador)) {
			// se for a vez do jogador
			Jogador jogadorDaVez = rodada.retornaJogadorPeloNome(nomeJogador);
			if (!rodada.verificaQuebra(jogadorDaVez)) {
				// se o jogador nao quebrou posso dar HIT
				rodada.getBaralho().embaralhar();

				rodada.pedeHit(jogadorDaVez);
				jogadorDaVez.imprimeJogador();

				// verifica se o jogador quebrou depois de pegar uma carta nova
				if (rodada.verificaQuebra(jogadorDaVez)) {
					rodada.quebra(jogadorDaVez);
				}
			}
			atualizaObservadores(); // notifica os observadores
		}
	}

	public void jogadorPedeDouble(String nomeJogador) {
		if (rodada.verficaJogadorDaVezJogando(nomeJogador)) {
			// se for a vez do jogador
			Jogador jogadorDaVez = rodada.retornaJogadorPeloNome(nomeJogador);
			CtrlModel.rodada.fazDouble(jogadorDaVez, jogadorDaVez.getAposta());
		}
		atualizaObservadores(); // notifica os observadores
	}

	public void jogadorPedeSurrender(String nomeJogador) {
		if (rodada.verficaJogadorDaVezJogando(nomeJogador)) {
			// se for a vez do jogador
			Jogador jogadorDaVez = rodada.retornaJogadorPeloNome(nomeJogador);
			CtrlModel.rodada.rendicao(jogadorDaVez, jogadorDaVez.getAposta());
		}
		System.out.println("JOGADORES REMOVIDOS[] = " + CtrlModel.rodada.getJogadoresRetiradosDaRodada()[0].getName());
		atualizaObservadores(); // notifica os observadores
	}

	public void dealerPedeCarta() {
		CtrlModel.rodada.DealerPedeCarta();
	}

	public void fimDaRodada(String[][] infoDosJogadores, String[] infoDoDealer) {

		System.out.println("\nFIM DA RODADA");
		for (int i = 0; i < infoDosJogadores.length - 1; i++) {
			String[] jogador = infoDosJogadores[i];
			System.out.println("info do jogador " + jogador[jogador.length - 1] + " = " + Arrays.toString(jogador));
		}
		System.out.println("F = " + Arrays.toString(infoDoDealer));
		System.out.println();

		CtrlModel.rodada.comparaMaoJogadoresDealer(infoDosJogadores, infoDoDealer);

		this.atualizaObservadores();
	}

	public void verificaJogadorTemBlackJack(String nomeJogador) {
		if (rodada.verficaJogadorDaVezJogando(nomeJogador)) {
			// se for a vez do jogador
			Jogador jogadorDaVez = rodada.retornaJogadorPeloNome(nomeJogador);
			CtrlModel.rodada.vitoriaBlackJack(jogadorDaVez, jogadorDaVez.getAposta());
		}
		// System.out.println("JOGADORES REMOVIDOS[] = " +
		// CtrlModel.rodada.getJogadoresRetiradosDaRodada()[0].getName());
		atualizaObservadores(); // notifica os observadores
	}

	/** PARTE DOS CLICKS **/
	public void clickFicha1() {
		int valorAposta = 1;
		if (rodada.getVez() == 0) {
			CtrlModel.jogador1.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 1 && CtrlModel.jogador2 != null) {
			CtrlModel.jogador2.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 2 && CtrlModel.jogador3 != null) {
			CtrlModel.jogador3.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 3 && CtrlModel.jogador4 != null) {
			CtrlModel.jogador4.tiraFicha(valorAposta);

		}
		atualizaObservadores();
	}

	public void clickFicha5() {
		int valorAposta = 5;
		if (rodada.getVez() == 0) {
			CtrlModel.jogador1.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 1 && CtrlModel.jogador2 != null) {
			CtrlModel.jogador2.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 2 && CtrlModel.jogador3 != null) {
			CtrlModel.jogador3.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 3 && CtrlModel.jogador4 != null) {
			CtrlModel.jogador4.tiraFicha(valorAposta);

		}
		atualizaObservadores();
	}

	public void clickFicha10() {
		int valorAposta = 10;
		if (rodada.getVez() == 0) {
			CtrlModel.jogador1.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 1 && CtrlModel.jogador2 != null) {
			CtrlModel.jogador2.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 2 && CtrlModel.jogador3 != null) {
			CtrlModel.jogador3.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 3 && CtrlModel.jogador4 != null) {
			CtrlModel.jogador4.tiraFicha(valorAposta);

		}
		atualizaObservadores();
	}

	public void clickFicha20() {
		int valorAposta = 20;
		if (rodada.getVez() == 0) {
			CtrlModel.jogador1.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 1 && CtrlModel.jogador2 != null) {
			CtrlModel.jogador2.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 2 && CtrlModel.jogador3 != null) {
			CtrlModel.jogador3.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 3 && CtrlModel.jogador4 != null) {
			CtrlModel.jogador4.tiraFicha(valorAposta);

		}
		atualizaObservadores();
	}

	public void clickFicha50() {
		int valorAposta = 50;
		if (rodada.getVez() == 0) {
			CtrlModel.jogador1.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 1 && CtrlModel.jogador2 != null) {
			CtrlModel.jogador2.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 2 && CtrlModel.jogador3 != null) {
			CtrlModel.jogador3.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 3 && CtrlModel.jogador4 != null) {
			CtrlModel.jogador4.tiraFicha(valorAposta);

		}
		atualizaObservadores();
	}

	public void clickFicha100() {
		int valorAposta = 100;
		if (rodada.getVez() == 0) {
			CtrlModel.jogador1.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 1 && CtrlModel.jogador2 != null) {
			CtrlModel.jogador2.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 2 && CtrlModel.jogador3 != null) {
			CtrlModel.jogador3.tiraFicha(valorAposta);

		} else if (rodada.getVez() == 3 && CtrlModel.jogador4 != null) {
			CtrlModel.jogador4.tiraFicha(valorAposta);
		}
		atualizaObservadores();
	}

	/** PARTE DO OBSERVADO **/
	@Override
	public void addObservador(ObservadorIF o) {
		// adiciona observador o na lista de observadores
		if (!observadoresLst.contains(o))
			observadoresLst.add(o);
		System.out.println("NUMERO DE OBSERVERS = " + observadoresLst.size());
	}

	@Override
	public void removeObservador(ObservadorIF o) {
		// remove observador o na lista de observadores
		observadoresLst.remove(o);
	}

	@Override
	public String[][] getInfoDoModel() {
		// retorna uma lista com todas as informacoes todos os jogadores da rodada
		// No view vamos atualizar as janelas de acordo com o nome dos jogadores na
		// lista
		// ex:
		// saida = {{"1c", "5d", 16, "Pedro"}, {{"1c", "5d", "10h", 16, "Bruna"},
		// {{"1c", "5d", "10h", 16, "Camila"}}
		int numeroObservers = observadoresLst.size();
		String[][] infoTodosOsJogadores = new String[numeroObservers][];

		// olhando quantos jogadores tem no jogo, já que numeroObservers = numero de
		// jogadores
		switch (numeroObservers) {

		case 2:
			infoTodosOsJogadores[0] = jogador1.getCartasString(CtrlModel.rodada.getVez());
			break;

		case 3:
			infoTodosOsJogadores[0] = jogador1.getCartasString(CtrlModel.rodada.getVez());
			infoTodosOsJogadores[1] = jogador2.getCartasString(CtrlModel.rodada.getVez());
			break;

		case 4:
			infoTodosOsJogadores[0] = jogador1.getCartasString(CtrlModel.rodada.getVez());
			infoTodosOsJogadores[1] = jogador2.getCartasString(CtrlModel.rodada.getVez());
			infoTodosOsJogadores[2] = jogador3.getCartasString(CtrlModel.rodada.getVez());
			break;

		case 5:
			infoTodosOsJogadores[0] = jogador1.getCartasString(CtrlModel.rodada.getVez());
			infoTodosOsJogadores[1] = jogador2.getCartasString(CtrlModel.rodada.getVez());
			infoTodosOsJogadores[2] = jogador3.getCartasString(CtrlModel.rodada.getVez());
			infoTodosOsJogadores[3] = jogador4.getCartasString(CtrlModel.rodada.getVez());
			break;

		default:
			break;
		}
		for (String[] infoJogadorEspecifico : infoTodosOsJogadores) {
			System.out.println("infoTodosOsJogadores = " + Arrays.toString(infoJogadorEspecifico));
		}

		return infoTodosOsJogadores;
	}

	@Override
	public void atualizaObservadores() {
		ListIterator<ObservadorIF> iteradorLst = observadoresLst.listIterator();

		while (iteradorLst.hasNext()) {
			iteradorLst.next().notificaObservadores(this);
		}

	}

	/**
	 * PARTE DO SALVAMENTO DO JOGO
	 * 
	 * @throws IOException
	 **/
	public void salvaJogo() throws IOException {
		// Insere informações sobre o jogo em um arquivo .txt
		FileReader inputStream = null;
		FileWriter outputStream = null;
		try {
			outputStream = new FileWriter("src/Model/JogoSalvo.txt");

			String[] infoDoDealer = this.getCartaInfoDealer();
			String[][] infoDosJogadores = this.getInfoDoModel();

			// 1a linha é a VEZ
			String vez = infoDosJogadores[0][infoDosJogadores[0].length - 2];
			outputStream.write(vez + "\n");

			// 2 linha é o NUMERO DE JOGADORES
			int numeroJogadoresNaRoadada = infoDosJogadores.length - 1;
			outputStream.write(numeroJogadoresNaRoadada + "\n");

			outputStream.write("InicioInfoDealer\n");

			System.out.println("infoDoDealer = " + Arrays.toString(infoDoDealer));

			int indexUltimaCartaDoDelaer = this.getIndexUltimaCartaNoArr(infoDoDealer);
			outputStream.write(indexUltimaCartaDoDelaer + " ");

			for (String info : infoDoDealer) {
				outputStream.write(info + " ");
			}
			outputStream.write("\nFimInfoDealer\n");

			outputStream.write("InicioInfoJogadores\n");

			for (String[] jogador : infoDosJogadores) {
				if (jogador != null) {

					int indexUltimaCartaDoJogador = this.getIndexUltimaCartaNoArr(jogador);
					outputStream.write(indexUltimaCartaDoJogador + " ");

					for (int i = 0; i < indexUltimaCartaDoJogador; i++) {
						String carta = jogador[i];
						outputStream.write(carta + " ");
					}

					String dinheiroJogador = jogador[jogador.length - 4];

					String apostaJogador = jogador[jogador.length - 3];

					String nomeJogador = jogador[jogador.length - 1];

					outputStream.write(dinheiroJogador + " ");
					outputStream.write(apostaJogador + " ");
					outputStream.write(nomeJogador + " ");

					outputStream.write("\n");
				}
			}
			outputStream.write("\nFimInfoJogadores\n");
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public String[] carregaPartida() throws FileNotFoundException {
		// retorna as informações que estavam salvadas no .txt em um array
		Scanner s = null;

		String[] infoRodada = {};

		try {
			s = new Scanner(new BufferedReader(new FileReader("src/Model/JogoSalvo.txt")));
			while (s.hasNext()) {
				infoRodada = Arrays.copyOf(infoRodada, infoRodada.length + 1);
				infoRodada[infoRodada.length - 1] = s.next();
			}

		} finally {
			if (s != null)
				s.close();
		}

		return infoRodada;

	}

	public void setDinheiroJogador1(int valor) {
		CtrlModel.jogador1.setDinheiro(valor);
	}

	public void setDinheiroJogador2(int valor) {
		CtrlModel.jogador2.setDinheiro(valor);
	}

	public void setDinheiroJogador3(int valor) {
		CtrlModel.jogador3.setDinheiro(valor);
	}

	public void setDinheiroJogador4(int valor) {
		CtrlModel.jogador4.setDinheiro(valor);
	}

	public void setApostaJogador1(int aposta) {
		CtrlModel.jogador1.setAposta(aposta);
		;
	}

	public void setApostaJogador2(int aposta) {
		CtrlModel.jogador2.setAposta(aposta);
		;
	}

	public void setApostaJogador3(int aposta) {
		CtrlModel.jogador3.setAposta(aposta);
		;
	}

	public void setApostaJogador4(int aposta) {
		CtrlModel.jogador4.setAposta(aposta);
		;
	}

	public void setCartasDealer(Carta[] cartas) {
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

	public void setVezRodada(int vez) {
		CtrlModel.rodada.setVez(vez);
	}
}
