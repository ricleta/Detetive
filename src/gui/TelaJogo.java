package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Controller;

public class TelaJogo extends JFrame implements ActionListener, MouseListener 
{
	private static final long serialVersionUID = 1L;

	/* Constantes */
	private final int start_X = 50;
	private final int start_Y = 60;
	private final int step_X = 24;
	private final int step_Y = 24;
	private final int diametro = 20; // peões representados por círculos, logo a width e height sao iguais

	// array the tuplas para armanezar a posicao da celula onde o jogador esta de
	// acordo com a matriz que representa o tabuleiro no pacote regras
	private int[][] coord_multiplier = new int[6][];

	Image img_tabuleiro;
	Image img_dado1, img_dado2;
	JPanel p;
	JLabel jog_vez;
	JLabel joga_dado;
	
	JButton passagemSecreta = new JButton("Passagem Secreta");
	JButton proximo = new JButton("Proximo");
	JButton show_cards = new JButton("Mostrar cartas");
	JButton notepad = new JButton("Bloco de Notas");
	JButton palpite = new JButton("Palpite");
	JButton acusar = new JButton("Acusar");
	JButton save_game = new JButton("Salvar jogo");
	JButton dado = new JButton("Jogar Dados");

	boolean flag_end = false; // flag para indicar se o jogador vai poder finalizar a jogada e passar para o próximo jogador ou salvar o jogo
	String jog_atual = "Srta. Scarlet";
	int [][] coord_possiveis;
	int[] dados;
	int id_jog;

	public TelaJogo() {
		try {
			img_tabuleiro = ImageIO.read(new File("images/Tabuleiros/tabuleiro.jpg"));
			img_dado1 = ImageIO.read(new File(String.format("images/Tabuleiros/dado%d.jpg", 1)));
			img_dado2 = ImageIO.read(new File(String.format("images/Tabuleiros/dado%d.jpg", 1)));

		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLayout(null);
		this.setSize(1200, 700);
		this.setTitle("Jogo");

		jog_vez = new JLabel("Vez de: " + jog_atual); // adicionar String do Jogador da vez
		this.add(jog_vez);
		jog_vez.setBounds(1020, 20, 180, 50);

		joga_dado = new JLabel(String.format("%d Passo(s)", 0)); // adicionar soma dos dados aqui
		this.add(joga_dado);
		joga_dado.setBounds(1020, 510, 100, 20);

		this.add(passagemSecreta);
		passagemSecreta.setBounds(1020, 100, 150, 50);
		passagemSecreta.addActionListener(this);
	    passagemSecreta.setEnabled(false);

		this.add(proximo);
		proximo.setBounds(1020, 150, 150, 50);
		proximo.addActionListener(this);
//		proximo.setEnabled(flag_end);
		proximo.setEnabled(false); // so fica enabled no fim da jogada

		this.add(show_cards);
		show_cards.setBounds(1020, 200, 150, 50);
		show_cards.addActionListener(this);

		this.add(notepad);
		notepad.setBounds(1020, 250, 150, 50);
		notepad.addActionListener(this);

		this.add(palpite);
		palpite.setBounds(1020, 300, 150, 50);
		palpite.addActionListener(this);

		this.add(acusar);
		acusar.setBounds(1020, 350, 150, 50);
		acusar.addActionListener(this);

		this.add(save_game);
		save_game.setBounds(1020, 450, 150, 50);
		save_game.addActionListener(this);
//	    save_game.setEnabled(flag_end);
		save_game.setEnabled(false); // so fica enabled no fim da jogada

		this.add(dado);
		dado.setBounds(1020, 600, 150, 50);
		dado.addActionListener(this);

		ini_coord_jogadores();
		this.setVisible(true);
		this.addMouseListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == passagemSecreta) {

		} else if (e.getSource() == proximo) {
			Controller.prox_turno();
			jog_atual = Controller.get_jogador_atual();
			this.remove(jog_vez);

			jog_vez = new JLabel("Vez de: " + jog_atual);
			this.add(jog_vez);
			jog_vez.setBounds(1020, 20, 180, 50);
			
			dado.setEnabled(true);
			proximo.setEnabled(false);
		} else if (e.getSource() == show_cards) {

		} else if (e.getSource() == notepad) {
			boolean [][] notas = Controller.get_notas_jog_atual();
			
			// Nao mostra os marcados ainda, falta implementar metodo da CtrlRegras
			@SuppressWarnings("unused")
			Notas bloco = new Notas(notas[0], notas[1], notas[2]);
		} else if (e.getSource() == palpite) {
			@SuppressWarnings("unused")
			PalpiteAcusacao palpite = new PalpiteAcusacao("Palpite");
		} else if (e.getSource() == acusar) {
			@SuppressWarnings("unused")
			PalpiteAcusacao acusacao = new PalpiteAcusacao("Acusacao");
		} else if (e.getSource() == save_game) {

		} else if (e.getSource() == dado) {
			dados = Controller.roll_dice();
			id_jog = get_id_jog();
			
			dado.setEnabled(false);
			
			this.remove(joga_dado);
			joga_dado = new JLabel(String.format("%d Passo(s)", dados[0] + dados[1]));
			this.add(joga_dado);
			joga_dado.setBounds(1020, 510, 100, 20);

			try {
				img_dado1 = ImageIO.read(new File(String.format("images/Tabuleiros/dado%d.jpg", dados[0])));
				img_dado2 = ImageIO.read(new File(String.format("images/Tabuleiros/dado%d.jpg", dados[1])));
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
				System.exit(1);
			}
			
			// provavelmente vai sair devido observer
			coord_possiveis = Controller.encontra_movimentos(coord_multiplier[id_jog][0], coord_multiplier[id_jog][1], dados[0] + dados[1]);

			repaint();
		}
	}

	public void mouseClicked(java.awt.event.MouseEvent e) {
		// pegar célula:
		int x_frame = e.getX();
		int y_frame = e.getY();
		int x_cell;
		int y_cell;

		/* pegar posição da célula do tabuleiro */
		x_cell = (x_frame - start_X) / step_X;
		y_cell = (y_frame - start_Y) / step_Y;

		if (checa_coord_valida(x_cell, y_cell)) 
		{
//			System.out.printf("Cell: x = %d\ty = %d\n", x_cell, y_cell);

//			System.out.printf("%s: x = %d\ty = %d\t n_mov = %d\n", jog_atual, coord_multiplier[id_jog][0], coord_multiplier[id_jog][1], dados[0] + dados[1]);
			muda_pos_jog(x_cell, y_cell);
			
			// so muda aqui se jogador nao entrar num comodo
			proximo.setEnabled(true);
			
			repaint();
		}
	}
	
	boolean checa_coord_valida(int x, int y)
	{
	
		if ((x >= 0 && x <= 23) && (y >= 0 && y <= 24) && coord_possiveis != null)
		{
			for (int [] i :coord_possiveis)
			{
				if (x == i[0] && y ==i[1])
				{
					coord_possiveis = null;
					return true;
				}
			}
		}
		
		return false;
	}

	void muda_pos_jog(int x, int y) 
	{
		int id = get_id_jog();
		
		coord_multiplier[id][0] = x;
		coord_multiplier[id][1] = y;
		
		// provavelmente vai sair devido observer
		Controller.atualiza_cell_ocupada(x, y);
	}

	int get_id_jog() 
	{
		switch (jog_atual) {
		case "Srta. Scarlet":
			return 0;
		case "Coronel Mustard":
			return 1;
		case "Professor Plum":
			return 2;
		case "Reverendo Green":
			return 3;
		case "Sra. White":
			return 4;
		case "Sra. Peacock":
			return 5;	
		default:
			return -1;
		}
	}
	
	/* Falsa implementação necessária devido à interface MouseListener */
	public void mousePressed(java.awt.event.MouseEvent e) {
	}

	public void mouseReleased(java.awt.event.MouseEvent e) {
	}

	public void mouseEntered(java.awt.event.MouseEvent e) {
	}

	public void mouseExited(java.awt.event.MouseEvent e) {
	}

	void ini_coord_jogadores() {
		coord_multiplier[0] = new int[] { 7, 24 }; // ini scarlet
		coord_multiplier[1] = new int[] { 0, 17 }; // ini mustarda
		coord_multiplier[2] = new int[] { 23, 19 }; // ini plum
		coord_multiplier[3] = new int[] { 14, 0 }; // ini green
		coord_multiplier[4] = new int[] { 9, 0 }; // ini white
		coord_multiplier[5] = new int[] { 23, 6 }; // ini peacocks
	}

	public void paint(Graphics g) {
		// Tabuleiro[0][0] == (50, 60)
		// para se mover no eixo x, andar de 24 em 24 unidades
		// para se mover no eixo y, andar de 24 em 24 unidades

		// "Srta. Scarlet" -> (7, 24)
		// "Coronel Mustard" -> (0, 17)
		// "Professor Plum" -> (23, 19)
		// "Reverendo Green" -> (14, 0)
		// "Sra. White" -> (9, 0)
		// "Sra. Peacock" -> (23, 6)

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(img_tabuleiro, 0, 10, img_tabuleiro.getWidth(null) - 30, img_tabuleiro.getHeight(null) - 30,
				this);

		g2d.drawImage(img_dado1, 1020, 560, img_dado1.getWidth(null) - 40, img_dado1.getHeight(null) - 40, this);
		g2d.drawImage(img_dado2, 1120, 560, img_dado2.getWidth(null) - 40, img_dado2.getHeight(null) - 40, this);

		/* pintando os jogadores */
		Ellipse2D scarlet = new Ellipse2D.Double(start_X + coord_multiplier[0][0] * step_X,
				start_Y + coord_multiplier[0][1] * step_Y, diametro, diametro);
		g2d.setPaint(Color.RED);
		g2d.fill(scarlet);

		Ellipse2D mostarda = new Ellipse2D.Double(start_X + coord_multiplier[1][0] * step_X,
				start_Y + coord_multiplier[1][1] * step_Y, diametro, diametro);
		g2d.setPaint(new Color(254, 242, 0));
		g2d.fill(mostarda);

		Ellipse2D plum = new Ellipse2D.Double(start_X + coord_multiplier[2][0] * step_X,
				start_Y + coord_multiplier[2][1] * step_Y, diametro, diametro);
		g2d.setPaint(new Color(97, 17, 68));
		g2d.fill(plum);

		Ellipse2D green = new Ellipse2D.Double(start_X + coord_multiplier[3][0] * step_X,
				start_Y + coord_multiplier[3][1] * step_Y, diametro, diametro);
		g2d.setPaint(Color.GREEN);
		g2d.fill(green);

		Ellipse2D white = new Ellipse2D.Double(start_X + coord_multiplier[4][0] * step_X,
				start_Y + coord_multiplier[4][1] * step_Y, diametro, diametro);
		g2d.setPaint(Color.LIGHT_GRAY);
		g2d.fill(white);

		Ellipse2D peacock = new Ellipse2D.Double(start_X + coord_multiplier[5][0] * step_X,
				start_Y + coord_multiplier[5][1] * step_Y, diametro, diametro);
		g2d.setPaint(new Color(2, 80, 163));
		g2d.fill(peacock);
		
		if (coord_possiveis != null)
		{
			g2d.setPaint(Color.ORANGE);
			
			for (int [] i: coord_possiveis)
			{
				g2d.fillRect(start_X + i[0] * step_X, start_Y + i[1] * step_Y, 17, 17);
			}
		}
	}
}