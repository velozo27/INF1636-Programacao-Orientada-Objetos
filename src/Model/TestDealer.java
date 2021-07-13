/*Alunos:
 * Pedro Antônio Tibau Velozo - 1812013
 * Yuri Lemos - 1610893
 * Rafael Feliciano - 1521772
 * */

package Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestDealer {

	@Test
	void testPegaPontosCartasNormais() {
		// somo os pontos de um jogador e comparo com o valor esperado
		Carta carta1 = new Carta(7, 'C');
		Carta carta2 = new Carta(9, 'P');
		Dealer dealer = new Dealer(carta1, carta2);
		assertEquals("Soma das cartas deu errado.", 7 + 9, dealer.getsomaCartas());
	}

	@Test
	void testPegaPontosCartasNaoNumeros() {
		// somo os pontos de um jogador e comparo com o valor esperado
		Carta carta1 = new Carta(4, 'C');
		Carta carta2 = new Carta(12, 'P'); // Dama de Paus
		Dealer dealer = new Dealer(carta1, carta2);
		dealer.adicionaCartaNaMao(new Carta(13, 'O')); // adiciona Rei de Ouros
		assertEquals("Soma das cartas deu errado.", 24, dealer.getsomaCartas());
	}

	@Test
	void testAdicionaCartaNaMao() {
		Dealer dealer = new Dealer(new Carta(1, 'C'), new Carta(10, 'O'));
		for (int i = 0; i < 10; i++)
			dealer.adicionaCartaNaMao(new Carta(5, 'P'));
		// apos o for eh para o jogador ter 12 cartas na mao

		assertSame("As cartas nao foram adicionadas de maneira correta", dealer.getCartas().length, 12);
	}
	
	@Test
	void testAtingiu17Pontos() {
		Carta carta1 = new Carta(7, 'C');
		Carta carta2 = new Carta(12, 'P'); // Dama de Paus
		Dealer dealer = new Dealer(carta1, carta2);
		assertTrue("Dealer tem menos de 17 pontos", dealer.atingiu17Pontos());
	}

}
