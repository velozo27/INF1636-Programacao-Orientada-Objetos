/*Alunos:
 * Pedro Antônio Tibau Velozo - 1812013
 * Yuri Lemos - 1610893
 * Rafael Feliciano - 1521772
 * */


package Model;

public interface ObservadoIF {
	public void addObservador(ObservadorIF o);

	public void removeObservador(ObservadorIF o);

	public void atualizaObservadores();

	public String[][] getInfoDoModel();
}
