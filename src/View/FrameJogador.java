/*Alunos:
 * Pedro Antônio Tibau Velozo - 1812013
 * Yuri Lemos - 1610893
 * Rafael Feliciano - 1521772
 * */

package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Model.CtrlModel;
import Model.ObservadoIF;
import Model.ObservadorIF;

class FrameJogador extends JFrame implements ActionListener, ObservadorIF {
	public final int LARG_DEFAULT = 400;
	public final int ALT_DEFAULT = 400;

	private PanelJogador panelJogador;

	private JButton botaoDouble;
	private JButton botaoSplit;
	private JButton botaoStand;
	private JButton botaoDeal;
	private JButton botaoHit;
	private JButton botaoSurrender;

	private String nomeJogador;

	// infoJogador tem as cartas e a pontuacao do jogador no formato  {carta1, carta2, ..., cartaN, somaPontosCartas,
	// somaCreditosJogador, valorAposta, vezDaRodada, NomeDoJogador}
	public FrameJogador(String[] infoJogador) {
		panelJogador = new PanelJogador(infoJogador);
		getContentPane().add(panelJogador);

		this.setTitle("Janela Jogador");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(LARG_DEFAULT, ALT_DEFAULT);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);

		botaoSurrender = new JButton();
		botaoSurrender.setBounds(LARG_DEFAULT * 52 / 100, ALT_DEFAULT * 79 / 100, LARG_DEFAULT * 24 / 100,
				ALT_DEFAULT * 9 / 100);
		botaoSurrender.setText("Surrender");
		botaoSurrender.addActionListener(this);
		botaoSurrender.setEnabled(false);

		botaoHit = new JButton();
		botaoHit.setBounds(LARG_DEFAULT * 6 / 100, ALT_DEFAULT * 68 / 100, LARG_DEFAULT * 21 / 100,
				ALT_DEFAULT * 9 / 100);
		botaoHit.setText("Hit");
		botaoHit.addActionListener(this);
		botaoHit.setEnabled(false);

		botaoDeal = new JButton();
		botaoDeal.setBounds(LARG_DEFAULT * 6 / 100, ALT_DEFAULT * 79 / 100, LARG_DEFAULT * 21 / 100,
				ALT_DEFAULT * 9 / 100);
		botaoDeal.setText("Deal");
		botaoDeal.addActionListener(this);
		botaoDeal.setEnabled(false);

		botaoDouble = new JButton();
		botaoDouble.setBounds(LARG_DEFAULT * 28 / 100, ALT_DEFAULT * 68 / 100, LARG_DEFAULT * 19 / 100,
				ALT_DEFAULT * 9 / 100);
		botaoDouble.setText("Double");
		botaoDouble.addActionListener(this);
		botaoDouble.setEnabled(false);

		botaoSplit = new JButton();
		botaoSplit.setBounds(LARG_DEFAULT * 48 / 100, ALT_DEFAULT * 68 / 100, LARG_DEFAULT * 19 / 100,
				ALT_DEFAULT * 9 / 100);
		botaoSplit.setText("Split");
		botaoSplit.addActionListener(this);
		botaoSplit.setEnabled(false);

		botaoStand = new JButton();
		botaoStand.setBounds(LARG_DEFAULT * 68 / 100, ALT_DEFAULT * 68 / 100, LARG_DEFAULT * 19 / 100,
				ALT_DEFAULT * 9 / 100);
		botaoStand.setText("Stand");
		botaoStand.addActionListener(this);
		botaoStand.setEnabled(false);

		this.add(botaoDouble);
		this.add(botaoSplit);
		this.add(botaoStand);
		this.add(botaoDeal);
		this.add(botaoSurrender);
		this.add(botaoHit);

		nomeJogador = infoJogador[infoJogador.length - 1]; // o nome do jogador é o ultimo elemento do array infoJogador

		/** OBSERVER **/
		CtrlModel.getCtrlModel().addObservador(this);

	}

	public FrameJogador() {

	}

	public FrameJogador(String[] infoJogador, String[] cartasJogador) {

		panelJogador = new PanelJogador(infoJogador, cartasJogador);
		getContentPane().add(panelJogador);

		this.setTitle("Janela Jogador");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(LARG_DEFAULT, ALT_DEFAULT);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);

		botaoSurrender = new JButton();
		botaoSurrender.setBounds(LARG_DEFAULT * 52 / 100, ALT_DEFAULT * 79 / 100, LARG_DEFAULT * 24 / 100,
				ALT_DEFAULT * 9 / 100);
		botaoSurrender.setText("Surrender");
		botaoSurrender.addActionListener(this);
		botaoSurrender.setEnabled(false);

		botaoHit = new JButton();
		botaoHit.setBounds(LARG_DEFAULT * 6 / 100, ALT_DEFAULT * 68 / 100, LARG_DEFAULT * 21 / 100,
				ALT_DEFAULT * 9 / 100);
		botaoHit.setText("Hit");
		botaoHit.addActionListener(this);
		botaoHit.setEnabled(false);

		botaoDeal = new JButton();
		botaoDeal.setBounds(LARG_DEFAULT * 6 / 100, ALT_DEFAULT * 79 / 100, LARG_DEFAULT * 21 / 100,
				ALT_DEFAULT * 9 / 100);
		botaoDeal.setText("Deal");
		botaoDeal.addActionListener(this);
		botaoDeal.setEnabled(false);

		botaoDouble = new JButton();
		botaoDouble.setBounds(LARG_DEFAULT * 28 / 100, ALT_DEFAULT * 68 / 100, LARG_DEFAULT * 19 / 100,
				ALT_DEFAULT * 9 / 100);
		botaoDouble.setText("Double");
		botaoDouble.addActionListener(this);
		botaoDouble.setEnabled(false);

		botaoSplit = new JButton();
		botaoSplit.setBounds(LARG_DEFAULT * 48 / 100, ALT_DEFAULT * 68 / 100, LARG_DEFAULT * 19 / 100,
				ALT_DEFAULT * 9 / 100);
		botaoSplit.setText("Split");
		botaoSplit.addActionListener(this);
		botaoSplit.setEnabled(false);

		botaoStand = new JButton();
		botaoStand.setBounds(LARG_DEFAULT * 68 / 100, ALT_DEFAULT * 68 / 100, LARG_DEFAULT * 19 / 100,
				ALT_DEFAULT * 9 / 100);
		botaoStand.setText("Stand");
		botaoStand.addActionListener(this);
		botaoStand.setEnabled(false);

		this.add(botaoDouble);
		this.add(botaoSplit);
		this.add(botaoStand);
		this.add(botaoDeal);
		this.add(botaoSurrender);
		this.add(botaoHit);

		nomeJogador = infoJogador[infoJogador.length - 1]; // o nome do jogador é o ultimo elemento do array infoJogador

		/** OBSERVER **/
		CtrlModel.getCtrlModel().addObservador(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botaoDouble) {
			System.out.println("BOTAO DOUBLE APERTADO");
			CtrlModel.getCtrlModel().jogadorPedeDouble(nomeJogador);
		}
		if (e.getSource() == botaoSplit) {
			System.out.println("BOTAO SPLIT APERTADO");
		}
		if (e.getSource() == botaoStand) {
			System.out.println("BOTAO STAND APERTADO");
			CtrlModel.getCtrlModel().jogadorPedeStand(nomeJogador);
		}
		if (e.getSource() == botaoDeal) {
			System.out.println("BOTAO DEAL APERTADO");
			botaoDouble.setEnabled(true);
			botaoSplit.setEnabled(true);
			botaoStand.setEnabled(true);
			botaoHit.setEnabled(true);
			botaoSurrender.setEnabled(true);
			this.panelJogador.dealApertado();

			// tenho que verificar blackjack na mao do jogador aqui, assim que ele aperta Deal
			CtrlModel.getCtrlModel().verificaJogadorTemBlackJack(nomeJogador);
		}
		if (e.getSource() == botaoHit) {
			System.out.println("BOTAO HIT APERTADO");
			CtrlModel.getCtrlModel().jogadorPedeHit(nomeJogador);
		}
		if (e.getSource() == botaoSurrender) {
			System.out.println("BOTAO SURRENDER APERTADO");
			//CtrlModel.getCtrlModel().jogadorPedeSurrender(nomeJogador);
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		botaoDeal.repaint();
		botaoDouble.repaint();
		botaoSplit.repaint();
		botaoStand.repaint();
		botaoSurrender.repaint();
		botaoHit.repaint();
	}

	/** PARTE DO OBSERVADOR **/
	@Override
	public void notificaObservadores(ObservadoIF o) {

		// com as informações recebidas da classe observada (CtrlModel), atualizo o
		// frame
		// e panel dos jogadores
		
		String[][] infoDosJogadores = o.getInfoDoModel();

		panelJogador.atualizaPanelJogador(infoDosJogadores);

		/** PARA ATUALIZAR OS BOTOES DOS JOGADORES **/
		int indexDoArrDoJogadorDessePanel = retornaIndexDoArrQueRepresentaJogadorDoPanel(infoDosJogadores);

		String ArrDoJogadorDessePanel[] = infoDosJogadores[indexDoArrDoJogadorDessePanel];

		int valorAposta = Integer.parseInt(ArrDoJogadorDessePanel[ArrDoJogadorDessePanel.length - 3]);

		if (valorAposta >= 20) {
			botaoDeal.setEnabled(true);
		}

		botaoDeal.repaint();
		botaoDouble.repaint();
		botaoSplit.repaint();
		botaoStand.repaint();
		botaoSurrender.repaint();
		botaoHit.repaint();
	}

	/* funcoes auxiliares */
	private int retornaIndexDoArrQueRepresentaJogadorDoPanel(String[][] infoDosJogadores) {
		// Procuro qual elemento do vetor de vetores de String tem o nome do Jogador do
		// Panel
		for (int i = 0; i < infoDosJogadores.length; i++) {
			if (infoDosJogadores[i][infoDosJogadores[i].length - 1].equals(nomeJogador)) {
				System.out.println("i = " + i);
				return i;
			}
		}
		return -1; // nunca chega aqui
	}

}