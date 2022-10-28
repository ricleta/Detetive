package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PalpiteAcusacao extends JFrame implements ActionListener{
  JRadioButton[] suspeitos = new JRadioButton[6];
  JRadioButton[] armas = new JRadioButton[6];
  JRadioButton[] comodos = new JRadioButton[9];
  ButtonGroup suspeitosGroup = new ButtonGroup();
  ButtonGroup armasGroup = new ButtonGroup();
  ButtonGroup comodosGroup = new ButtonGroup();
  String[] returns = new String[3]; //// n sei como q retornaria os palpites, ver isso daqui
  int i;

  PalpiteAcusacao(String tipo){
    this.setVisible(true);
    this.pack();
    this.setLayout(new FlowLayout());
    this.setTitle(tipo);
    this.setSize(450, 450);

    suspeitos[0] = new JRadioButton("Srta. Scarlet");
    suspeitos[1] = new JRadioButton("Coronel Mustard");
    suspeitos[2] = new JRadioButton("Professor Plum");
    suspeitos[3] = new JRadioButton("Reverendo Green");
    suspeitos[4] = new JRadioButton("Sra. White");
    suspeitos[5] = new JRadioButton("Sra. Peacock"); 

    armas[0] = new JRadioButton("Corda");
    armas[1] = new JRadioButton("Cano de Chumbo");
    armas[2] = new JRadioButton("Faca");
    armas[3] = new JRadioButton("Chave Inglesa");
    armas[4] = new JRadioButton("Castical");
    armas[5] = new JRadioButton("Revolver"); 

    comodos[0] = new JRadioButton("Biblioteca");
    comodos[1] = new JRadioButton("Cozinha");
    comodos[2] = new JRadioButton("Entrada");
    comodos[3] = new JRadioButton("Escritorio");
    comodos[4] = new JRadioButton("Jardim de Inverno");
    comodos[5] = new JRadioButton("Sala de Estar"); 
    comodos[6] = new JRadioButton("Sala de Jantar");     
    comodos[7] = new JRadioButton("Sala de Jogos"); 
    comodos[8] = new JRadioButton("Sala de Musica"); 

    for(i = 0; i < 6; i++){
      suspeitos[i].setFocusable(false);
      suspeitos[i].addActionListener(this);
      // suspeitos[i].setBounds(5, 30+i*40, 140, 35);
      this.add(suspeitos[i]);
      suspeitosGroup.add(suspeitos[i]);

      armas[i].setFocusable(false);
      armas[i].addActionListener(this);
      // armas[i].setBounds(150, 30+i*40, 140, 35);
      this.add(armas[i]);
      armasGroup.add(armas[i]);

      comodos[i].setFocusable(false);
      comodos[i].addActionListener(this);
      // comodos[i].setBounds(295, 30+i*40, 160, 35);
      this.add(comodos[i]);
      comodosGroup.add(comodos[i]);
    }

    for(i = 6; i < 9; i++){
      comodos[i].setFocusable(false);
      comodos[i].addActionListener(this);
      // comodos[i].setBounds(295, 30+i*40, 160, 35);
      this.add(comodos[i]);
      comodosGroup.add(comodos[i]);
    }    
  }

  public void actionPerformed(ActionEvent e) {
    // TODO
  }  
}