/*Alunos:
 * Pedro Antônio Tibau Velozo - 1812013
 * Yuri Lemos - 1610893
 * Rafael Feliciano - 1521772
 * */

package Model;

import java.util.Random;

class Baralho {
	private Carta[] baralho = { new Carta(1, 's'), new Carta(2, 's'), new Carta(3, 's'), new Carta(4, 's'),
			new Carta(5, 's'), new Carta(6, 's'), new Carta(7, 's'), new Carta(8, 's'), new Carta(9, 's'),
			new Carta(10, 's'), new Carta(11, 's'), new Carta(12, 's'), new Carta(13, 's'), new Carta(1, 'h'),
			new Carta(2, 'h'), new Carta(3, 'h'), new Carta(4, 'h'), new Carta(5, 'h'), new Carta(6, 'h'),
			new Carta(7, 'h'), new Carta(8, 'h'), new Carta(9, 'h'), new Carta(10, 'h'), new Carta(11, 'h'),
			new Carta(12, 'h'), new Carta(13, 'h'), new Carta(1, 'c'), new Carta(2, 'c'), new Carta(3, 'c'),
			new Carta(4, 'c'), new Carta(5, 'c'), new Carta(6, 'c'), new Carta(7, 'c'), new Carta(8, 'c'),
			new Carta(9, 'c'), new Carta(10, 'c'), new Carta(11, 'c'), new Carta(12, 'c'), new Carta(13, 'c'),
			new Carta(1, 'd'), new Carta(2, 'd'), new Carta(3, 'd'), new Carta(4, 'd'), new Carta(5, 'd'),
			new Carta(6, 'd'), new Carta(7, 'd'), new Carta(8, 'd'), new Carta(9, 'd'), new Carta(10, 'd'),
			new Carta(11, 'd'), new Carta(12, 'd'), new Carta(13, 'd'), new Carta(1, 's'), new Carta(2, 's'),
			new Carta(3, 's'), new Carta(4, 's'), new Carta(5, 's'), new Carta(6, 's'), new Carta(7, 's'),
			new Carta(8, 's'), new Carta(9, 's'), new Carta(10, 's'), new Carta(11, 's'), new Carta(12, 's'),
			new Carta(13, 's'), new Carta(1, 'h'), new Carta(2, 'h'), new Carta(3, 'h'), new Carta(4, 'h'),
			new Carta(5, 'h'), new Carta(6, 'h'), new Carta(7, 'h'), new Carta(8, 'h'), new Carta(9, 'h'),
			new Carta(10, 'h'), new Carta(11, 'h'), new Carta(12, 'h'), new Carta(13, 'h'), new Carta(1, 'c'),
			new Carta(2, 'c'), new Carta(3, 'c'), new Carta(4, 'c'), new Carta(5, 'c'), new Carta(6, 'c'),
			new Carta(7, 'c'), new Carta(8, 'c'), new Carta(9, 'c'), new Carta(10, 'c'), new Carta(11, 'c'),
			new Carta(12, 'c'), new Carta(13, 'c'), new Carta(1, 'd'), new Carta(2, 'd'), new Carta(3, 'd'),
			new Carta(4, 'd'), new Carta(5, 'd'), new Carta(6, 'd'), new Carta(7, 'd'), new Carta(8, 'd'),
			new Carta(9, 'd'), new Carta(10, 'd'), new Carta(11, 'd'), new Carta(12, 'd'), new Carta(13, 'd'),
			new Carta(1, 's'), new Carta(2, 's'), new Carta(3, 's'), new Carta(4, 's'), new Carta(5, 's'),
			new Carta(6, 's'), new Carta(7, 's'), new Carta(8, 's'), new Carta(9, 's'), new Carta(10, 's'),
			new Carta(11, 's'), new Carta(12, 's'), new Carta(13, 's'), new Carta(1, 'h'), new Carta(2, 'h'),
			new Carta(3, 'h'), new Carta(4, 'h'), new Carta(5, 'h'), new Carta(6, 'h'), new Carta(7, 'h'),
			new Carta(8, 'h'), new Carta(9, 'h'), new Carta(10, 'h'), new Carta(11, 'h'), new Carta(12, 'h'),
			new Carta(13, 'h'), new Carta(1, 'c'), new Carta(2, 'c'), new Carta(3, 'c'), new Carta(4, 'c'),
			new Carta(5, 'c'), new Carta(6, 'c'), new Carta(7, 'c'), new Carta(8, 'c'), new Carta(9, 'c'),
			new Carta(10, 'c'), new Carta(11, 'c'), new Carta(12, 'c'), new Carta(13, 'c'), new Carta(1, 'd'),
			new Carta(2, 'd'), new Carta(3, 'd'), new Carta(4, 'd'), new Carta(5, 'd'), new Carta(6, 'd'),
			new Carta(7, 'd'), new Carta(8, 'd'), new Carta(9, 'd'), new Carta(10, 'd'), new Carta(11, 'd'),
			new Carta(12, 'd'), new Carta(13, 'd'), new Carta(1, 's'), new Carta(2, 's'), new Carta(3, 's'),
			new Carta(4, 's'), new Carta(5, 's'), new Carta(6, 's'), new Carta(7, 's'), new Carta(8, 's'),
			new Carta(9, 's'), new Carta(10, 's'), new Carta(11, 's'), new Carta(12, 's'), new Carta(13, 's'),
			new Carta(1, 'h'), new Carta(2, 'h'), new Carta(3, 'h'), new Carta(4, 'h'), new Carta(5, 'h'),
			new Carta(6, 'h'), new Carta(7, 'h'), new Carta(8, 'h'), new Carta(9, 'h'), new Carta(10, 'h'),
			new Carta(11, 'h'), new Carta(12, 'h'), new Carta(13, 'h'), new Carta(1, 'c'), new Carta(2, 'c'),
			new Carta(3, 'c'), new Carta(4, 'c'), new Carta(5, 'c'), new Carta(6, 'c'), new Carta(7, 'c'),
			new Carta(8, 'c'), new Carta(9, 'c'), new Carta(10, 'c'), new Carta(11, 'c'), new Carta(12, 'c'),
			new Carta(13, 'c'), new Carta(1, 'd'), new Carta(2, 'd'), new Carta(3, 'd'), new Carta(4, 'd'),
			new Carta(5, 'd'), new Carta(6, 'd'), new Carta(7, 'd'), new Carta(8, 'd'), new Carta(9, 'd'),
			new Carta(10, 'd'), new Carta(11, 'd'), new Carta(12, 'd'), new Carta(13, 'd') };

	// private Carta[] baralho = new Carta[208];

	// private Carta cartaAuxiliar;

	public Baralho() {

	}

//  OBS: tentamos implementar o baralho de forma mais eficiente porém
//  isso gerava muitos erros em outras partes do código
//	public Baralho() {
//		int indexBaralho = 0;
//
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 13; j++) {
//				for (int k = 0; k < 4; k++) {
//					if (k == 0)
//						cartaAuxiliar = new Carta(j, 'c');
//					else if (k == 1)
//						cartaAuxiliar = new Carta(j, 'd');
//					else if (k == 2)
//						cartaAuxiliar = new Carta(j, 'h');
//					else
//						cartaAuxiliar = new Carta(j, 's');
//					baralho[indexBaralho] = cartaAuxiliar;
//					indexBaralho++;
//				}
//			}
//		}
//
//	}

	public void embaralhar() {
		int n = this.baralho.length;
		Random random = new Random();
		random.nextInt();
		for (int i = 0; i < n; i++) {
			int change = i + random.nextInt(n - i);
			troca(this.baralho, i, change);
		}
	}

	// funcao auxiliar
	private void troca(Carta[] baralho2, int i, int change) {
		Carta helper = baralho2[i];
		baralho2[i] = baralho2[change];
		baralho2[change] = helper;
	}

	public Carta retiraCarta() {
		Carta cartaRetirada = this.baralho[0];
		this.baralho = this.remove(this.baralho, 0);
		return cartaRetirada;
	}

	// remove carta do baralho
	public Carta[] remove(Carta[] arr, int index) {
		if (arr == null || index < 0 || index >= arr.length) {
			return arr;
		}
		Carta[] vetor = new Carta[arr.length - 1];
		for (int i = 0, k = 0; i < arr.length; i++) {
			if (i == index) {
				continue;
			}
			vetor[k++] = arr[i];
		}
		return vetor;
	}

	public boolean foiDistribuido10PorCento() {
		return this.baralho.length <= 187; // 187 = 210 * 0.90
	}

	public int getNumeroDeCartas() {
		return this.baralho.length;
	}

}
