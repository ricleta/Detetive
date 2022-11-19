package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controller.Controller;

public class TelaVitoria extends JFrame implements ActionListener {
	JLabel label;
	JButton novoJogo = new JButton("Novo jogo");
	JButton sair = new JButton("Sair");
	
	
	public TelaVitoria(String vencedor) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setLayout(null);
		this.setSize(450, 450);
		
		label = new JLabel(String.format("Parabéns, %s você venceu!", vencedor));
		label.setBounds(50, 20, 350, 40);
		this.add(label);
		
		novoJogo.setBounds(150, 80, 150, 40);
		novoJogo.addActionListener(this);
		this.add(novoJogo);

		sair.setBounds(150, 140, 150, 40);
		sair.addActionListener(this);
		this.add(sair);
	}
			
			

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == novoJogo) {
			new TelaPersonagens();
			
			this.dispose();
		}
		else if(e.getSource() == sair) {
			System.exit(0);
		}
		
	}
	
}