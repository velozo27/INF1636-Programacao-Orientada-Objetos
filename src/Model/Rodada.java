/*Alunos:
 * Pedro Antônio Tibau Velozo - 1812013
 * Yuri Lemos - 1610893
 * Rafael Feliciano - 1521772
 * */

package Model;

import java.util.Arrays;

class Rodada {
	private Jogador[] jogadoresNaRodada;
	private Jogador[] jogadoresRetiradosDaRodada = {};
	private Dealer dealer;
	private int vez = 0; // 0 -> jogador1; 1 -> jogador2; 2 -> jogador3; 3 -> jogador4
	private Baralho baralho = new Baralho();

	public Rodada(Jogador jogador1, Jogador jogador2, Jogador jogador3, Jogador jogador4, Dealer dealer) {
		this.dealer = dealer;
		this.jogadoresNaRodada = new Jogador[4];
		this.jogadoresNaRodada[0] = jogador1;
		this.jogadoresNaRodada[1] = jogador2;
		this.jogadoresNaRodada[2] = jogador3;
		this.jogadoresNaRodada[3] = jogador4;
	}

	public Rodada(Jogador jogador1, Jogador jogador2, Jogador jogador3, Dealer dealer) {
		this.dealer = dealer;
		this.jogadoresNaRodada = new Jogador[3];
		this.jogadoresNaRodada[0] = jogador1;
		this.jogadoresNaRodada[1] = jogador2;
		this.jogadoresNaRodada[2] = jogador3;
	}

	public Rodada(Jogador jogador1, Jogador jogador2, Dealer dealer) {
		this.dealer = dealer;
		this.jogadoresNaRodada = new Jogador[2];
		this.jogadoresNaRodada[0] = jogador1;
		this.jogadoresNaRodada[1] = jogador2;
	}

	public Rodada(Jogador jogador1, Dealer dealer) {
		this.dealer = dealer;
		this.jogadoresNaRodada = new Jogador[1];
		this.jogadoresNaRodada[0] = jogador1;
	}

	// para remover jogadores que sairao ou passarao de 21
	public void removeJogadorDaRodada(Jogador jogador) {
		for (int i = 0; i < this.jogadoresNaRodada.length; i++) {
			if (this.jogadoresNaRodada[i].equals(jogador)) {
				this.jogadoresNaRodada = Rodada.remove(this.jogadoresNaRodada, i); // remove da rodada

				// adiciona o jogador retirado na lista de jogadores retirados
				this.jogadoresRetiradosDaRodada = Arrays.copyOf(this.jogadoresRetiradosDaRodada,
						this.jogadoresRetiradosDaRodada.length + 1);
				this.jogadoresRetiradosDaRodada[this.jogadoresRetiradosDaRodada.length - 1] = jogador;

				// System.out.println("Jogador " + (i + 1) + " Removido");
			}
		}
	}

	// funcao auxiliar
	public static Jogador[] remove(Jogador[] arr, int index) {
		if (arr == null || index < 0 || index >= arr.length) {
			return arr;
		}
		Jogador[] anotherArray = new Jogador[arr.length - 1];
		for (int i = 0, k = 0; i < arr.length; i++) {
			if (i == index) {
				continue;
			}
			anotherArray[k++] = arr[i];
		}
		return anotherArray;
	}

	// printa informacoes da rodada
	public void exibeRodada() {
		System.out.println("\nJogadores na Rodada:");
		for (Jogador jogador : this.jogadoresNaRodada) {
			System.out.println("\t" + jogador.getName());
		}
		System.out.println("Jogador da Vez: " + jogadoresNaRodada[vez].getName() + "\n");
	}

	public void rendicao(Jogador jogador, int valorAposta) {
		// na rendicao o jogador recebe metade do valor de sua aposta
		// alem disso o jogador eh removido da rodada

		int valorRendicao = valorAposta / 2;

		// retorna vetor com as fichas a serem devolvidas para o jogador
		// int[] valorEmFichas =
		// jogador.getDinheiro().divideValorEmFichas(valorRendicao);

		// retorna as fichas para o jogador
		// jogador.getDinheiro().recebeFicha(valorEmFichas);

		jogador.atualizaDinheiro(valorRendicao);

		this.removeJogadorDaRodada(jogador);

		// muda a vez
		this.mudaVez();
	}

	public void vitoriaOrdinaria(Jogador jogador, int valorAposta) {
		// na vitoria ordinaria o jogador recebe 2 vezes o valor de sua aposta

		if (verificaVitoriaOrdinaria(jogador)) {

			int valorVitoriaOrdinaria = valorAposta * 2;

			// retorna vetor com as fichas a serem devolvidas para o jogador
			// int[] valorEmFichas =
			// jogador.getDinheiro().divideValorEmFichas(valorVitoriaOrdinaria);

			// retorna as fichas para o jogador
			// jogador.getDinheiro().recebeFicha(valorEmFichas);

			jogador.atualizaDinheiro(valorVitoriaOrdinaria);

			this.removeJogadorDaRodada(jogador);
		}
	}

	public boolean jogadorTemBlackJack(Jogador jogador) {
		// verifica se tem A e carta de valor 10, ou seja,
		// a soma das 2 primeiras cartas da 21
		// return jogador.getCartas()[0].getValor() + jogador.getCartas()[1].getValor()
		// == 21;
		return jogador.getsomaCartas() == 21;
	}

	public boolean dealerTemBlackJack() {
		// verifica se tem A e carta de valor 10, ou seja,
		// a soma das 2 primeiras cartas da 21
		return this.dealer.getCartas()[0].getValor() + this.dealer.getCartas()[1].getValor() == 21;
	}

	public void vitoriaBlackJack(Jogador jogador, int valorAposta) {
		if (this.jogadorTemBlackJack(jogador)) {
			// tenho q verificar se o dealer tbm tem black jack, se sim, push
			if (this.dealerTemBlackJack()) {
				// nesse caso, o jogador e dealer tem blackjack => push
				jogador.atualizaDinheiro(valorAposta);
				this.mudaVez();
				return;
			}

			int valorVitoriaBlackJack = (valorAposta * 5) / 2;

			jogador.atualizaDinheiro(valorVitoriaBlackJack);

			// quem ganha blackjack ta ganhando da vitoria por blackjack e ordinaria,
			// vamos tirar ordinaria

			int valorCorrecao = valorAposta * 2;

			jogador.atualizaDinheiro(-valorCorrecao);

			this.mudaVez();

			// this.removeJogadorDaRodada(jogador);
		}
	}

	public boolean verificaPush(Jogador jogador) {
		// retorna true se o dealer e o jogador tem a mesma quantidade de pontos
		// if (!this.dealerTemBlackJack()) {
		return jogador.getsomaCartas() == this.dealer.getsomaCartas();
		// }
		// return false;
	}

	public boolean verificaVitoriaOrdinaria(Jogador jogador) {
		// retorna true se o jogador tem mais pontos que o dealer e sua pontuacao eh <=
		// 21

		int somaCartas = jogador.getsomaCartas();

		// verifico se o jogador nao quebrou e o dealer quebrou
		if (somaCartas <= 21 && this.dealer.getsomaCartas() > 21) {
			return true;
		}

		return (somaCartas > this.dealer.getsomaCartas()) && (somaCartas <= 21) && (this.dealer.getsomaCartas() <= 21);
	}

	public boolean verificaVitoriaBlackJack(Jogador jogador) {
		// retorna true se o jogador tem mais pontos que o dealer e sua pontuacao eh ==
		// 21
		int somaCartas = jogador.getsomaCartas();
		return (somaCartas > this.dealer.getsomaCartas()) && (somaCartas == 21);
	}

	public boolean verificaQuebra(Jogador jogador) {
		// retorna true se o jogador tem mais de 21 pontos
		return jogador.getsomaCartas() > 21;
	}

	public void quebra(Jogador jogador) {
		// removo o jogador da rodada
		if (this.verificaQuebra(jogador)) {
			System.out.println("JOGADOR " + jogador.getName() + " QUEBROU");
			this.mudaVez();
		}
	}

	public void push(Jogador jogador, int valorAposta) {
		// Ninguém ganha; ninguém perde
		// jogador recebe de volta o mesmo valor de sua aposta
		if (this.verificaPush(jogador)) {
			jogador.atualizaDinheiro(valorAposta);
			this.removeJogadorDaRodada(jogador);
		}
	}

	// controla a vez dos jogadores
	public void mudaVez() {
		if (this.vez == this.jogadoresNaRodada.length)
			this.vez = 0;
		else
			this.vez++;
	}

	public void fazDouble(Jogador jogador, int valorAposta) {
		// se o jogador tem 2 cartas e pede double
		// ele recebe mais 1 carta e dobra sua aposta (se tem o dinheiro disponivel)
		if (this.verificaCartasJogadorTem(jogador) == 2 && jogador.getDinheiroJogador() >= 2 * valorAposta) {
			Carta cartaNova = this.baralho.retiraCarta();
			jogador.adicionaCartaNaMao(cartaNova);

			// retirando as fichas do jogador
			jogador.tiraFichaDouble(valorAposta);

			// muda a vez
			this.mudaVez();
		}
	}

	public void pedeStand() {
		// ao pedir stand apenas muda a vez
		this.mudaVez();
	}

	public void pedeHit(Jogador jogador) {
		// jogador recebe outra carta
		Carta cartaNova = this.baralho.retiraCarta();
		jogador.adicionaCartaNaMao(cartaNova);
	}

	private int verificaCartasJogadorTem(Jogador jogador) {
		// retorna numero de cartas na mao do jogador
		return jogador.getCartas().length;
	}

	public boolean verificaApostaValida(int valorAposta) {
		return (20 <= valorAposta && valorAposta <= 100);
	}

	public Jogador[] getJogadoresNaRodada() {
		return this.jogadoresNaRodada;
	}

	public boolean verficaJogadorDaVezJogando(String nomeJogador) {
		// 0 -> jogador1; 1 -> jogador2; 2 -> jogador3; 3 -> jogador4
		System.out.println("vez = " + vez);

		if (vez == buscaJogadorNaListaJogadores(nomeJogador)) {
			return true;
		}
		// caso o ultimo jogador tenha jogado, a vez volta para o primeiro
		if (vez == jogadoresNaRodada.length - 1
				&& nomeJogador == jogadoresNaRodada[jogadoresNaRodada.length - 1].getName()) {
			return true;
		}
		System.out.println("NÃO É SUA VEZ!");
		return false;
	}

	private int buscaJogadorNaListaJogadores(String nomeJogador) {
		for (int index = 0; index < jogadoresNaRodada.length; index++) {
			if (nomeJogador == jogadoresNaRodada[index].getName())
				return index;
		}
		return -1; // jogador não encontrado
	}

	public Jogador retornaJogadorPeloNome(String nomeJogador) {

		System.out.println("jogadoresNaRodada = " + Arrays.toString(this.jogadoresNaRodada));

		// retorna a instancia do jogador com o nome passado como parametro
		for (Jogador jogador : jogadoresNaRodada) {
			if (nomeJogador == jogador.getName())
				return jogador;
		}
		return null;
	}

	public int getVez() {
		return vez;
	}

	public Jogador[] getJogadoresRetiradosDaRodada() {
		return jogadoresRetiradosDaRodada;
	}

	public void retiraTodosJogadoresDaRodada() {
		System.out.println("\n\n\n CHEGEUI AQUI 326\n\n\n");

		System.out.println("jogadoresNaRodada = " + Arrays.toString(this.jogadoresNaRodada));

		for (Jogador jogador : this.jogadoresNaRodada) {
			this.removeJogadorDaRodada(jogador);
			System.out.println("Jogador " + jogador.getName() + " Removido");
		}
	}

	public void resetaJogadores() {
		// funcao para quando uma nova rodada for iniciada
		// preciso zerar o valor da aposta,
		// descartar as cartas na mao e pegar 2 cartas novas

		this.baralho = new Baralho();
		this.baralho.embaralhar();

		for (Jogador jogador : this.jogadoresRetiradosDaRodada) {
			jogador.zeraValorAposta();
			Carta cartaNova1 = baralho.retiraCarta();
			Carta cartaNova2 = baralho.retiraCarta();
			jogador.reiniciaCartasNaMao(cartaNova1, cartaNova2);
		}
	}

	public void resetaDealer() {
		Carta cartaNova1 = baralho.retiraCarta();
		Carta cartaNova2 = baralho.retiraCarta();
		this.dealer.reiniciaCartasNaMao(cartaNova1, cartaNova2);
	}

	public void DealerPedeCarta() {
		Carta cartaNova = this.baralho.retiraCarta();
		this.dealer.adicionaCartaNaMao(cartaNova);
	}

	public void comparaMaoJogadoresDealer(String[][] infoDosJogadores, String[] infoDoDealer) {
		for (int i = 0; i < infoDosJogadores.length - 1; i++) {
			String[] jogadorVet = infoDosJogadores[i];
			String nomeJogador = jogadorVet[jogadorVet.length - 1];
			Jogador jogadorAnalisado = this.retornaJogadorPeloNome(nomeJogador);

			System.out.println("\n\nNOME DO JOGADOR = " + nomeJogador + "\n\n");

			// verificacoes do jogo
			this.vitoriaOrdinaria(jogadorAnalisado, jogadorAnalisado.getAposta());
			this.push(jogadorAnalisado, jogadorAnalisado.getAposta());

			// zera aposta do jogador
			jogadorAnalisado.setAposta(0);
		}
	}

	public Baralho getBaralho() {
		return this.baralho;
	}

	public void setVez(int vez) {
		this.vez = vez;
	}
}