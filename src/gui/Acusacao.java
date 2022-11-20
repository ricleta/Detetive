package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.Controller;

class Acusacao extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String[] suspeitos = new String[7];
	String[] armas = new String[7];
	String[] comodos = new String[10];
	JComboBox<String> suspeitosBox;
	JComboBox<String> armasBox;
	JComboBox<String> comodosBox;
	JButton confirmar;
	private String[] acusacao = new String[3];
	private int cont_selected = 0;
	TelaJogo telaJogo;

	Acusacao(TelaJogo telaJogo) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setLayout(new FlowLayout());
		this.setSize(300, 300);
		
		this.telaJogo = telaJogo;
		
		confirmar = new JButton("Confirmar acusacao");
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

		/* tratamento relativo a comodos */
		comodos[0] = "Comodo";
		comodos[1] = "Biblioteca";
		comodos[2] = "Cozinha";
		comodos[3] = "Entrada";
		comodos[4] = "Escritorio";
		comodos[5] = "Jardim de Inverno";
		comodos[6] = "Sala de Estar";
		comodos[7] = "Sala de Jantar";
		comodos[8] = "Sala de Musica";
		comodos[9] = "Salao de Jogos";

		comodosBox = new JComboBox<String>(comodos);
		comodosBox.addActionListener(this);
		this.add(comodosBox);
	}

	public void actionPerformed(ActionEvent e) {
		/* verifica se já foi selecionado um suspeito, uma arma e um cômodo */
		if (e.getSource() == confirmar && cont_selected == 3) {
			String jog_atual = Controller.get_jogador_atual();
			
//			System.out.printf("S: %s\nA: %s\nC: %s\n", acusacao[0], acusacao[1], acusacao[2]); // testar se funcionou (sim)
			
			
			boolean acerto = Controller.faz_acusacao(jog_atual, acusacao[0], acusacao[1], acusacao[2]);
				
			if(acerto) {
//				JOptionPane.showMessageDialog(null, String.format("Parabéns %s, você venceu!", jog_atual));
				
				telaJogo.dispose();
				
				new TelaVitoria(jog_atual);
					
//				System.exit(0);
			}
			else {					
				JOptionPane.showMessageDialog(null, String.format("%s, você foi eliminado", jog_atual));
			}

			this.dispose();
		}

		/*
		 * selecionando as combobox, adiciona o elemento selecionado na respectiva
		 * posição do array de retorno. Se estiverem sendo selecionadas pela primeira
		 * vez incrementao contador
		 */
		else if (e.getSource() == suspeitosBox && !suspeitosBox.getSelectedItem().equals(suspeitos[0])) {
			if (acusacao[0] == null) {
				cont_selected++;
			}
			acusacao[0] = (String) suspeitosBox.getSelectedItem();
		}

		else if (e.getSource() == armasBox && !armasBox.getSelectedItem().equals(armas[0])) {
			if (acusacao[1] == null) {
				cont_selected++;
			}
			acusacao[1] = (String) armasBox.getSelectedItem();
		}

		else if (e.getSource() == comodosBox && !comodosBox.getSelectedItem().equals(comodos[0])) {
			if (acusacao[2] == null) {
				cont_selected++;
			}
			acusacao[2] = (String) comodosBox.getSelectedItem();
		}
	}
}