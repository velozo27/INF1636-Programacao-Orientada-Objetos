/*Alunos:
 * Pedro Antônio Tibau Velozo - 1812013
 * Yuri Lemos - 1610893
 * Rafael Feliciano - 1521772
 * */

package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.*;

import Model.CtrlModel;
import Model.ObservadoIF;
import Model.ObservadorIF;

class FrameBanca extends JFrame implements ActionListener, ObservadorIF {
	public final int LARG_DEFAULT = 800;
	public final int ALT_DEFAULT = 600;

	PanelBanca panel;

	// Botoes banca
	JButton botaoEncerrar;
	JButton botaoSalvar;
	JButton botaoNovoRodada;

	// texto para nome dos jogadores
	JTextField caixaTexto;
	private String primeiraCartaPrimeiroJogadorNova;
	private Object primeiraCartaPrimeiroJogadorAntiga;
	private boolean rodadaJaAcabou = false;

	public FrameBanca() {
		this.panel = new PanelBanca();
		getContentPane().add(panel);

		// adicionando mouse listner no painel da banca
		this.addMouseListener(panel);

		this.setTitle("BlackJack");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(LARG_DEFAULT, ALT_DEFAULT);
		this.setVisible(true);
		this.setResizable(false);

		this.setVisible(true);
		this.setLayout(null);

		this.setLocationRelativeTo(null);

		/******* BANCA *******/

		botaoNovoRodada = new JButton();
		botaoNovoRodada.setBounds((int) (0.30 * LARG_DEFAULT), 30, 125, 50);
		botaoNovoRodada.setText("NOVA RODADA");
		botaoNovoRodada.addActionListener(this);

		botaoSalvar = new JButton();
		botaoSalvar.setBounds((int) (0.46 * LARG_DEFAULT), 30, 108, 50);
		botaoSalvar.setText("SALVAR");
		botaoSalvar.addActionListener(this);

		botaoEncerrar = new JButton();
		botaoEncerrar.setBounds((int) (0.6 * LARG_DEFAULT), 30, 100, 50);
		botaoEncerrar.setText("ENCERRAR");
		botaoEncerrar.addActionListener(this);

		this.add(botaoNovoRodada);
		this.add(botaoEncerrar);
		this.add(botaoSalvar);

		botaoSalvar.setEnabled(true);

		CtrlModel.getCtrlModel().addObservador(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botaoNovoRodada) {
			System.out.println("BOTAO NOVA RODADA APERTADO");
			CtrlModel.getCtrlModel().instanciaRodadaNova();
			this.repaint();
		}

		if (e.getSource() == botaoEncerrar) {
			System.out.println("BOTAO ENCERRAR APERTADO");
			System.exit(1);
		}

		if (e.getSource() == botaoSalvar) {
			System.out.println("BOTAO SALVAR APERTADO");
			try {
				CtrlModel.getCtrlModel().salvaJogo();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		botaoEncerrar.repaint();
		botaoSalvar.repaint();
		botaoNovoRodada.repaint();
	}

	@Override
	public void notificaObservadores(ObservadoIF o) {

		// com as informações recebidas da classe observada (CtrlModel), atualizo o
		// frame
		// e panel da banca

		String[][] infoDosJogadores = o.getInfoDoModel(); // V = {{[ts, jc, jd, 30, 460, 40, 1, PEDRO]}, {[ah, 8h, 19,
															// 480, 20, 1, MARIA]}}

		int vez = Integer.parseInt(infoDosJogadores[0][infoDosJogadores[0].length - 2]);

		this.panel.setVez(vez);

		int primeiraVez = this.panel.getPrimeiraVez();

		int numeroJogadores = infoDosJogadores.length - 1;

		int vezDaBanca = numeroJogadores; // pq vez 1o jogador = 0; vez 2o jogador = 1; ...

		int vezDaRodada = Integer.parseInt(infoDosJogadores[0][infoDosJogadores[0].length - 2]);

		String[] infoDoDealer = CtrlModel.getCtrlModel().getCartaInfoDealer();

		// vendo 1a carta do 1o jogador
		this.primeiraCartaPrimeiroJogadorNova = infoDosJogadores[0][0];

		if (!this.primeiraCartaPrimeiroJogadorNova.equals(this.primeiraCartaPrimeiroJogadorAntiga)) {
			this.rodadaJaAcabou = false;
		}

		this.primeiraCartaPrimeiroJogadorAntiga = infoDosJogadores[0][0];

		if (this.rodadaJaAcabou == false) {
			this.rodadaJaAcabou = true;
			this.panel.setPrimeiraVez(0);
		}

		panel.atualizaPanelBanca(infoDosJogadores);
	}

}