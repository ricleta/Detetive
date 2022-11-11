package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Controller;

public class TelaInicial extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton criarJogo, continuarJogo;
	int i;

	Image img_ini;
	JPanel p;

	public TelaInicial() {
		try {
			img_ini = ImageIO.read(new File("images/Backgrounds/capa.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLayout(null);
		this.setSize(1200, 700);

		this.setTitle("Tela inicial");

		criarJogo = new JButton("Novo jogo");
		continuarJogo = new JButton("Continuar jogo");

		this.add(criarJogo);
		criarJogo.addActionListener(this);
		criarJogo.setBounds(100, 100, 100, 100);
		
		this.add(continuarJogo);
		continuarJogo.addActionListener(this);
		continuarJogo.setBounds(300, 100, 100, 100);
		
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == criarJogo) 
		{
			Controller.update_estado(1);
			this.dispose();
		}
//    else if(e.getSource() == continuarJogo)
//    {	
//      System.out.println("Vai para jogo salvo");
//    }
	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(img_ini, 0, 0, this);
		
		criarJogo.repaint();
		continuarJogo.repaint();
	}

}
