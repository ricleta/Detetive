package gui;

import javax.swing.*;

class Notas extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JCheckBox[] suspeitos = new JCheckBox[6];
	JCheckBox[] armas = new JCheckBox[6];
	JCheckBox[] comodos = new JCheckBox[9];
	JLabel labelSuspeitos = new JLabel("Suspeitos");
	JLabel labelArmas = new JLabel("Armas");
	JLabel labelComodos = new JLabel("Comodos");
	int i;

	/*
	 * construtor recebe 3 vetores de booleanos indicando quais checkboxes já devem
	 * iniciar marcadas
	 */
	Notas(boolean[] notas, boolean[] notas2, boolean[] notas3) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("Minhas notas");
		this.setSize(450, 450);

		labelSuspeitos.setBounds(10, 10, 100, 20);
		this.add(labelSuspeitos);
		labelArmas.setBounds(155, 10, 100, 20);
		this.add(labelArmas);
		labelComodos.setBounds(300, 10, 100, 20);
		this.add(labelComodos);

		suspeitos[0] = new JCheckBox("Srta. Scarlet");
		suspeitos[1] = new JCheckBox("Coronel Mustard");
		suspeitos[2] = new JCheckBox("Professor Plum");
		suspeitos[3] = new JCheckBox("Reverendo Green");
		suspeitos[4] = new JCheckBox("Sra. White");
		suspeitos[5] = new JCheckBox("Sra. Peacock");

		armas[0] = new JCheckBox("Corda");
		armas[1] = new JCheckBox("Cano de Chumbo");
		armas[2] = new JCheckBox("Faca");
		armas[3] = new JCheckBox("Chave Inglesa");
		armas[4] = new JCheckBox("Castical");
		armas[5] = new JCheckBox("Revolver");

		comodos[0] = new JCheckBox("Biblioteca");
		comodos[1] = new JCheckBox("Cozinha");
		comodos[2] = new JCheckBox("Entrada");
		comodos[3] = new JCheckBox("Escritorio");
		comodos[4] = new JCheckBox("Jardim de Inverno");
		comodos[5] = new JCheckBox("Sala de Estar");
		comodos[6] = new JCheckBox("Sala de Jantar");
		comodos[7] = new JCheckBox("Sala de Musica");
		comodos[8] = new JCheckBox("Salao de Jogos");

		for (i = 0; i < 6; i++) {
			this.add(suspeitos[i]);
			suspeitos[i].setBounds(5, 30 + i * 40, 140, 35);
			suspeitos[i].setSelected(notas[i]);
			suspeitos[i].setEnabled(false);

			this.add(armas[i]);
			armas[i].setBounds(150, 30 + i * 40, 140, 35);
			armas[i].setSelected(notas2[i]);
			armas[i].setEnabled(false);

			this.add(comodos[i]);
			comodos[i].setBounds(295, 30 + i * 40, 160, 35);
			comodos[i].setSelected(notas3[i]);
			comodos[i].setEnabled(false);
		}

		for (i = 6; i < 9; i++) {
			this.add(comodos[i]);
			comodos[i].setBounds(295, 30 + i * 40, 160, 35);
			comodos[i].setSelected(notas3[i]);
			comodos[i].setEnabled(false);
		}

	}
}