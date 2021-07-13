/*Alunos:
 * Pedro Antônio Tibau Velozo - 1812013
 * Yuri Lemos - 1610893
 * Rafael Feliciano - 1521772
 * */

package Model;

import java.util.Arrays;

class Jogador {
	private String name;
	private Carta[] cartas = new Carta[2];

	private int somaCartas;
	private int dinheiroJogador = 500;
	private int valorAposta = 0;

	public Jogador(String name, Carta carta1, Carta carta2) {
		this.setName(name);
		this.inicializaCartasNaMao(carta1, carta2);
	}

	public Jogador(String nome, Carta[] cartasJogador) {
		this.setName(name);
		this.cartas = cartasJogador;
	}

	// calcula pontuacao a partir das cartas na mao
	public void pegaPontos() {
		int soma = 0;

		for (int i = 0; i < cartas.length; i++) {
			// para corrigir K, Q, J
			if (cartas[i].getValor() >= 10)
				soma += 10;
			// define valor de A
			else if (soma <= 10 && cartas[i].getValor() == 1)
				soma += 11;
			else
				soma += cartas[i].getValor();
		}
		this.somaCartas = soma;
		System.out.println("somaCartas = " + somaCartas);
	}

	private void inicializaCartasNaMao(Carta carta1, Carta carta2) {
		this.cartas[0] = carta1;
		this.cartas[1] = carta2;
	}

	public void adicionaCartaNaMao(Carta carta) {
		// append carta novas
		if (cartas.length <= 5) {
			this.cartas = Arrays.copyOf(this.cartas, this.cartas.length + 1);
			this.cartas[this.cartas.length - 1] = carta;
		}
	}

	public int getsomaCartas() {
		// calcua a soma das cartas e retorna
		this.pegaPontos();
		return this.somaCartas;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Carta[] getCartas() {
		return this.cartas;
	}

	// funcao de auxilio para o view
	public String[] getCartasString(int vezDaRodada) {
		Carta[] cartas = this.getCartas();

		int tamVetor = cartas.length + 5;

		String[] infoCartasJogador = new String[tamVetor];
		// exemplo de como vai ficar o vetor acima:
		// infoCartasJogador = {carta1, carta2, ..., cartaN, somaPontosCartas,
		// somaCreditosJogador, valorAposta, vezDaRodada, NomeDoJogador}
		for (int i = 0; i < tamVetor - 5; i++) {

			String strCarta = converteValorCartaParaChar(cartas[i].getValor()) + cartas[i].getNaipe();

			infoCartasJogador[i] = strCarta;
		}
		infoCartasJogador[tamVetor - 5] = Integer.toString(this.getsomaCartas());
		infoCartasJogador[tamVetor - 4] = Integer.toString(this.getDinheiroJogador());
		infoCartasJogador[tamVetor - 3] = Integer.toString(this.getAposta());
		infoCartasJogador[tamVetor - 2] = Integer.toString(vezDaRodada);
		infoCartasJogador[tamVetor - 1] = this.getName();

		System.out.println("infoCartasJogador = " + Arrays.toString(infoCartasJogador));

		return infoCartasJogador;
	}

	public void atualizaDinheiro(int valor) {
		this.dinheiroJogador += valor;
	}

	// funcao auxiliar
	private String converteValorCartaParaChar(int valorCarta) {
		switch (valorCarta) {
		case 1:
			return "a";
		case 10:
			return "t";
		case 11:
			return "j";
		case 12:
			return "q";
		case 13:
			return "k";
		default:
			return Integer.toString(valorCarta);
		}
	}

	public void imprimeJogador() {
		System.out.println("Nome = " + name);
		System.out.println("Cartas:");
		for (Carta carta : cartas)
			carta.imprimeCarta();
	}

	public boolean tiraFicha(int valor) {
		if (this.dinheiroJogador - valor >= 0 && this.valorAposta + valor <= 100) {
			this.dinheiroJogador -= valor;
			this.atualizaAposta(valor);
			return true;
		}
		return false;
	}

	public void tiraFichaDouble(int valor) {
		if (this.dinheiroJogador - valor >= 0) {
			this.dinheiroJogador -= valor;
			this.atualizaAposta(valor);
		}
	}

	public void recebeFicha(int valor) {
		this.dinheiroJogador += valor;
	}

	public void atualizaAposta(int valor) {
		this.valorAposta += valor;
	}

	public int getDinheiroJogador() {
		return this.dinheiroJogador;
	}

	public int getAposta() {
		return this.valorAposta;
	}

	public void zeraValorAposta() {
		this.valorAposta = 0;
	}

	public void reiniciaCartasNaMao(Carta c1, Carta c2) {
		Carta[] cartasNovas = { c1, c2 };
		this.cartas = cartasNovas;
	}

	public void setAposta(int valorAposta) {
		this.valorAposta = valorAposta;
	}

	public void setDinheiro(int valor) {
		this.dinheiroJogador = valor;
	}

}
