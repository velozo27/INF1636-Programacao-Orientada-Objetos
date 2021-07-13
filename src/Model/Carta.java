/*Alunos:
 * Pedro Antônio Tibau Velozo - 1812013
 * Yuri Lemos - 1610893
 * Rafael Feliciano - 1521772
 * */

package Model;

class Carta {
	private int valor;
	private char naipe;

	public Carta(int valor, char naipe) {
		this.valor = valor;
		this.naipe = naipe;
	}

	public int getValor() {
		return this.valor;
	}

	public char getNaipe() {
		return this.naipe;
	}

	public void imprimeCarta() {
		System.out.println("Valor = " + valor + "; Naipe = " + naipe);
	}

}
