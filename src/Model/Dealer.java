/*Alunos:
 * Pedro Antônio Tibau Velozo - 1812013
 * Yuri Lemos - 1610893
 * Rafael Feliciano - 1521772
 * */

package Model;

import java.util.Arrays;

class Dealer {
	private Carta[] cartas = new Carta[2];
	private int somaCartas;

	public Dealer(Carta carta1, Carta carta2) {
		this.inicializaCartasNaMao(carta1, carta2);
	}

	public Dealer(Carta[] cartasDealer) {
		this.cartas = cartasDealer;
	}

	private void inicializaCartasNaMao(Carta carta1, Carta carta2) {
		this.cartas[0] = carta1;
		this.cartas[1] = carta2;
	}

	public void adicionaCartaNaMao(Carta carta) {
		// append carta novas
		this.cartas = Arrays.copyOf(this.cartas, this.cartas.length + 1);
		this.cartas[this.cartas.length - 1] = carta;
	}

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
	}

	public int getsomaCartas() {
		// calcua a soma das cartas e retorna
		this.pegaPontos();
		return this.somaCartas;
	}

	public Carta[] getCartas() {
		return this.cartas;
	}

	public boolean atingiu17Pontos() {
		return this.getsomaCartas() >= 17;
	}

	public boolean temBlackJack() {
		// verifica se tem A e carta de valor 10
		return (this.cartas[0].getValor() == 1 && this.cartas[1].getValor() == 10)
				|| (this.cartas[0].getValor() == 10 && this.cartas[1].getValor() == 1);
	}

	// funcao de auxilio para o view
	public String[] getCartasString() {
		Carta[] cartas = this.getCartas();

		int tamVetor = cartas.length + 1;
		String[] retorno = new String[tamVetor]; // = {"4d", "5c", "9"};
		for (int i = 0; i < tamVetor - 1; i++) {

			String strCarta = converteValorCartaParaChar(cartas[i].getValor()) + cartas[i].getNaipe();

			retorno[i] = strCarta;
		}
		retorno[cartas.length] = Integer.toString(this.getsomaCartas());

		return retorno;
	}

	// funcao auxiliar
	public String converteValorCartaParaChar(int valorCarta) {
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

	public void reiniciaCartasNaMao(Carta c1, Carta c2) {
		Carta[] cartasNovas = { c1, c2 };
		this.cartas = cartasNovas;
	}

}
