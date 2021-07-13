/*Alunos:
 * Pedro Antônio Tibau Velozo - 1812013
 * Yuri Lemos - 1610893
 * Rafael Feliciano - 1521772
 * */

package Model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class TestRodada {

	@Test
	void testRemoveJogadorDaRodada() {
		// inicio uma rodada com 4 jogadores e retiro 1
		Jogador p1 = new Jogador("p1", new Carta(1, 'P'), new Carta(1, 'P'));
		Jogador p2 = new Jogador("p2", new Carta(1, 'P'), new Carta(1, 'P'));
		Jogador p3 = new Jogador("p3", new Carta(1, 'P'), new Carta(1, 'P'));
		Jogador p4 = new Jogador("p4", new Carta(1, 'P'), new Carta(1, 'P'));

		Rodada rodada = new Rodada(p1, p2, p3, p4, new Dealer(new Carta(1, 'P'), new Carta(1, 'P')));

		int numeroJogadoresNaRodadaOriginal = rodada.getJogadoresNaRodada().length;

		rodada.removeJogadorDaRodada(p2);

		int numeroJogadoresNaRodadaDepois = rodada.getJogadoresNaRodada().length;

		assertNotEquals("Remocao de jogadores nao funcionou, o tamanho do array de jogadores permaneceu o mesmo",
				numeroJogadoresNaRodadaOriginal, numeroJogadoresNaRodadaDepois);
	}

	@Test
	void testRendicao() {
		// verifica se um jogador foi removido apos a rendicao

		Jogador p1 = new Jogador("p1", new Carta(1, 'P'), new Carta(1, 'P'));
		Jogador p2 = new Jogador("p2", new Carta(1, 'P'), new Carta(1, 'P'));
		Jogador p3 = new Jogador("p3", new Carta(1, 'P'), new Carta(1, 'P'));
		Jogador p4 = new Jogador("p4", new Carta(1, 'P'), new Carta(1, 'P'));

		Rodada rodada = new Rodada(p1, p2, p3, p4, new Dealer(new Carta(1, 'P'), new Carta(1, 'P')));

		int numeroJogadoresNaRodadaOriginal = rodada.getJogadoresNaRodada().length;

		rodada.rendicao(p2, 100);

		int numeroJogadoresNaRodadaDepois = rodada.getJogadoresNaRodada().length;

		assertNotEquals("Rendicao do jogador nao funcionou, o tamanho do array de jogadores permaneceu o mesmo",
				numeroJogadoresNaRodadaOriginal, numeroJogadoresNaRodadaDepois);
	}

	@Test
	void testVerificaVitoriaOrdinariaJogador() {
		// verifica se o jogador tem mais pontos que o dealer e <= 21
		Jogador jogador = new Jogador("p1", new Carta(10, 'P'), new Carta(10, 'C'));
		Dealer dealer = new Dealer(new Carta(10, 'P'), new Carta(8, 'C'));
		Rodada rodada = new Rodada(jogador, dealer);
		assertTrue("Jogador tem menos que o dealer ou mais de 21 pontos", rodada.verificaVitoriaOrdinaria(jogador));
	}

	@Test
	void testVerificaVitoriaOrdinariaDealer() {
		// verifica se o dealer tem mais pontos que o dealer e <= 21
		Jogador jogador = new Jogador("p1", new Carta(10, 'P'), new Carta(2, 'C'));
		Dealer dealer = new Dealer(new Carta(10, 'P'), new Carta(8, 'C'));
		Rodada rodada = new Rodada(jogador, dealer);
		assertFalse("Jogador tem mais que o dealer", rodada.verificaVitoriaOrdinaria(jogador));
	}

	@Test
	void testVerificaPush() {
		// verifica se empate
		Jogador jogador = new Jogador("p1", new Carta(8, 'P'), new Carta(9, 'C'));
		Dealer dealer = new Dealer(new Carta(9, 'P'), new Carta(8, 'C'));
		Rodada rodada = new Rodada(jogador, dealer);
		assertTrue("Nao deu empate", rodada.verificaPush(jogador));
	}

	@Test
	void testPushRemoveJogador() {
		// verifica se o jogador saiu da rodada
		Jogador jogador = new Jogador("p1", new Carta(10, 'P'), new Carta(8, 'C'));
		Dealer dealer = new Dealer(new Carta(10, 'P'), new Carta(8, 'C'));
		Rodada rodada = new Rodada(jogador, dealer);
		int numeroJogadoresAntes = rodada.getJogadoresNaRodada().length;
		rodada.push(jogador, 100);
		int numeroJogadoresDepois = rodada.getJogadoresNaRodada().length;
		assertNotEquals("O numero de jogadores antes e depois do push é igual", numeroJogadoresAntes,
				numeroJogadoresDepois);
	}

	@Test
	void testVerificaBlackJack() {
		// verifica se o jogador tem mais ponto que o dealer e pontos == 21
		Jogador jogador = new Jogador("p1", new Carta(10, 'P'), new Carta(1, 'C'));
		Dealer dealer = new Dealer(new Carta(9, 'P'), new Carta(8, 'C'));
		Rodada rodada = new Rodada(jogador, dealer);
		assertTrue("Jogador nao tem blackjack ou dealer tambem tem blackjack",
				rodada.verificaVitoriaBlackJack(jogador));
	}

	@Test
	void testVerificaQuebra() {
		// verifica se o jogador tem mais que 21 pontos
		Jogador jogador = new Jogador("p1", new Carta(10, 'P'), new Carta(1, 'C'));
		Dealer dealer = new Dealer(new Carta(9, 'P'), new Carta(8, 'C'));
		Rodada rodada = new Rodada(jogador, dealer);
		rodada.pedeHit(jogador); // jogador pede carta nova
		assertTrue("Jogador tem menos que 21 pontos", rodada.verificaQuebra(jogador));
	}

	@Test
	void testQuebraRemoveJogador() {
		// verifica se o jogador saiu da rodada
		Jogador jogador = new Jogador("p1", new Carta(10, 'P'), new Carta(1, 'C'));
		Dealer dealer = new Dealer(new Carta(9, 'P'), new Carta(8, 'C'));
		Rodada rodada = new Rodada(jogador, dealer);
		int numeroJogadoresAntes = rodada.getJogadoresNaRodada().length;
		rodada.pedeHit(jogador); // jogador pede carta nova
		rodada.quebra(jogador);
		int numeroJogadoresDepois = rodada.getJogadoresNaRodada().length;
		assertNotEquals("O numero de jogadores antes e depois da quebra é igual", numeroJogadoresAntes,
				numeroJogadoresDepois);
	}

}
