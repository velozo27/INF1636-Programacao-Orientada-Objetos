/*Alunos:
 * Pedro Antônio Tibau Velozo - 1812013
 * Yuri Lemos - 1610893
 * Rafael Feliciano - 1521772
 * */

package Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class TestBaralho {

	@Test
	void testEmbaralhaFunciona() {
		// crio 2 baralhos, embaralho 1 deles e verifico se eles sao diferentes
		Baralho baralho = new Baralho();
		Baralho baralhoEmbaralhado = new Baralho();
		baralhoEmbaralhado.embaralhar();
		assertNotSame("Baralhos dados são iguais.", baralho, baralhoEmbaralhado);
	}

	@Test
	void testRetiraCarta() {
		// verifica se o tamanho do baralho diminui se uma carta for retirada
		Baralho baralho = new Baralho();
		int baralhoTamanhoAntes = baralho.getNumeroDeCartas();
		baralho.retiraCarta();
		int baralhoTamanhoDepois = baralho.getNumeroDeCartas();
		assertNotSame("Número de cartas iguas, carta não foi retirada com sucesso", baralhoTamanhoAntes,
				baralhoTamanhoDepois);
	}

	@Test
	void testBaralhoCriadoCorretamente() {
		// verifica se o baralho possui 208 cartas. Numero equivalente a 4 baralhos de
		// 52 cartas
		assertEquals("Baralho não possui 208 cartas", new Baralho().getNumeroDeCartas(), 208);
	}

	@Test
	void testFoiDistribuidoMaisDe10PorCento() {
		// tiro 30 cartas do baralho e vejo se a funcao foiDistribuido10PorCento()
		// retorna true
		Baralho baralho = new Baralho();
		for (int i = 0; i < 30; i++)
			baralho.retiraCarta();
		assertTrue("baralho.foiDistribuido10PorCento() falhou", baralho.foiDistribuido10PorCento());
	}
}
