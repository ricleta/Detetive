package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.Controller;

class Palpite extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String[] suspeitos = new String[7];
	String[] armas = new String[7];
	JComboBox<String> suspeitosBox;
	JComboBox<String> armasBox;
	JButton confirmar;
	private String[] palpite = new String[3];
	String comodo;
	private int cont_selected = 0;
	TelaJogo telaJogo;

	Palpite(TelaJogo telaJogo, String comodo) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setLayout(new FlowLayout());
		this.setTitle("Palpite");
		this.setSize(300, 300);
		
		this.telaJogo = telaJogo;
		this.comodo = comodo;
		
		confirmar = new JButton("Confirmar palpite");
		confirmar.setBounds(50, 150, 100, 50);
		confirmar.addActionListener(this);
		this.add(confirmar);

		/* tratamento relativo a suspeitos */
		suspeitos[0] = "Suspeito";
		suspeitos[1] = "Srta. Scarlet";
		suspeitos[2] = "Coronel Mustard";
		suspeitos[3] = "Professor Plum";
		suspeitos[4] = "Reverendo Green";
		suspeitos[5] = "Sra. White";
		suspeitos[6] = "Sra. Peacock";

		suspeitosBox = new JComboBox<String>(suspeitos);
		suspeitosBox.addActionListener(this);
		this.add(suspeitosBox);

		/* tratamento relativo a armas */
		armas[0] = "Arma";
		armas[1] = "Corda";
		armas[2] = "Cano de Chumbo";
		armas[3] = "Faca";
		armas[4] = "Chave Inglesa";
		armas[5] = "Castical";
		armas[6] = "Revolver";

		armasBox = new JComboBox<String>(armas);
		armasBox.addActionListener(this);
		this.add(armasBox);
	}

	public void actionPerformed(ActionEvent e) {
		/* verifica se já foi selecionado um suspeito, uma arma e um cômodo */
		if (e.getSource() == confirmar && cont_selected == 2) {
			String jog_atual = Controller.get_jogador_atual();
			
			System.out.printf("S: %s\nA: %s\nC: %s\n", palpite[0], palpite[1], comodo); // testar se funcionou (sim)
			
			String result_palpite = Controller.faz_palpite(jog_atual, palpite[0], palpite[1], comodo);
				
			if(result_palpite == null) {
				JOptionPane.showMessageDialog(null, "Seu palpite não foi refutado");
			}
			else {
				JOptionPane.showMessageDialog(null, String.format("Seu palpite foi refutado, a carta %s foi encontrada", result_palpite));
				telaJogo.set_result_palpite(result_palpite);
			}
								

			this.dispose();
		}

		/*
		 * selecionando as combobox, adiciona o elemento selecionado na respectiva
		 * posição do array de retorno. Se estiverem sendo selecionadas pela primeira
		 * vez incrementao contador
		 */
		else if (e.getSource() == suspeitosBox && !suspeitosBox.getSelectedItem().equals(suspeitos[0])) {
			if (palpite[0] == null) {
				cont_selected++;
			}
			palpite[0] = (String) suspeitosBox.getSelectedItem();
		}

		else if (e.getSource() == armasBox && !armasBox.getSelectedItem().equals(armas[0])) {
			if (palpite[1] == null) {
				cont_selected++;
			}
			palpite[1] = (String) armasBox.getSelectedItem();
		}

	}
}