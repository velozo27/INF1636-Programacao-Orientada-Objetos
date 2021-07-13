/*Alunos:
 * Pedro Antônio Tibau Velozo - 1812013
 * Yuri Lemos - 1610893
 * Rafael Feliciano - 1521772
 * */

package Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.jupiter.api.Test;

class TestJogador {

	@Test
	void testPegaPontosCartasNormais() {
		// somo os pontos de um jogador e comparo com o valor esperado
		Carta carta1 = new Carta(7, 'C');
		Carta carta2 = new Carta(9, 'P');
		Jogador jogador = new Jogador("p1", carta1, carta2);
		assertEquals("Soma das cartas deu errado.", 7 + 9, jogador.getsomaCartas());
	}

	@Test
	void testPegaPontosCartasNaoNumeros() {
		// somo os pontos de um jogador e comparo com o valor esperado
		Carta carta1 = new Carta(4, 'C');
		Carta carta2 = new Carta(12, 'P'); // Dama de Paus
		Jogador jogador = new Jogador("p1", carta1, carta2);
		jogador.adicionaCartaNaMao(new Carta(13, 'O')); // adiciona Rei de Ouros
		assertEquals("Soma das cartas deu errado.", 24, jogador.getsomaCartas());
	}

	@Test
	void testAdicionaCartaNaMao() {
		Jogador jogador = new Jogador("p1", new Carta(1, 'C'), new Carta(10, 'O'));
		for (int i = 0; i < 10; i++)
			jogador.adicionaCartaNaMao(new Carta(5, 'P'));
		// apos o for eh para o jogador ter 12 cartas na mao

		assertSame("As cartas nao foram adicionadas de maneira correta", jogador.getCartas().length, 12);
	}

}
