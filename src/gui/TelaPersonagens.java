package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

import javax.imageio.ImageIO;
import controller.Controller;

public class TelaPersonagens extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JCheckBox[] boxes = new JCheckBox[6];
	JButton iniciarJogo = new JButton("Iniciar jogo");
	ArrayList<String> jog_selecionados;
	int i;
	int n_jogadores = 0;

  Image img_personagens;

	public TelaPersonagens() {
    try {
			img_personagens = ImageIO.read(new File("images/Backgrounds/EscolhaPersonagens.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
    
		this.setSize(1200, 700);
		this.setLayout(null);
		this.setTitle("Selecione os personagens do jogo");
		this.setVisible(true);

		boxes[0] = new JCheckBox("Srta. Scarlet");
		boxes[0].setBackground(Color.RED);
		boxes[1] = new JCheckBox("Coronel Mustard");
		boxes[1].setBackground(Color.YELLOW);		
		boxes[2] = new JCheckBox("Professor Plum");
		boxes[2].setBackground(new Color(97, 17, 68));
		boxes[2].setForeground(Color.WHITE);
		boxes[3] = new JCheckBox("Reverendo Green");
		boxes[3].setBackground(Color.GREEN);
		boxes[4] = new JCheckBox("Sra. White");
		boxes[4].setBackground(Color.LIGHT_GRAY);
		boxes[5] = new JCheckBox("Sra. Peacock");
		boxes[5].setBackground(Color.BLUE);
		boxes[5].setForeground(Color.WHITE);

		for (i = 0; i < 6; i++) {
			this.add(boxes[i]);
			boxes[i].setBounds(1000, 400 + (33 * i), 150, 30);
		}

		/* faz com que Srta. Scarlet seja obrigatoriamente uma das jogadoras */
		boxes[0].setSelected(true);
		boxes[0].setEnabled(false);

		iniciarJogo.setBounds(1000, 600, 150, 50);
		iniciarJogo.addActionListener(this);
		this.add(iniciarJogo);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == iniciarJogo) {
			if (this.checkJogValidos() != -1) 
			{
				Controller.setConfig(n_jogadores, jog_selecionados);
				this.dispose();
			}
		}
	}

	/*
	 * verifica o número de boxes selecionadas para averiguar se é possível iniciar
	 * o jogo
	 */
	public int checkJogValidos() {
		n_jogadores = 0;
		jog_selecionados = new ArrayList<String>();

		for (JCheckBox box : boxes) {
			if (box.isSelected()) {
				n_jogadores++;
				jog_selecionados.add(box.getText());
			}
		}

		if (n_jogadores < 3) {
			return -1;
		} else {
			return n_jogadores;
		}
	}

  public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(img_personagens, 0, 20, 1200, 680, this);
		
		for(JCheckBox box: boxes) {
			box.repaint();
		}
		
		iniciarJogo.repaint();
	}
}
