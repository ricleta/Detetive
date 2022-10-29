package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PalpiteAcusacao extends JFrame implements ActionListener{ 
  String[] suspeitos = new String[6];
  String[] armas = new String[6];
  String[] comodos = new String[9];
  JComboBox suspeitosBox;
  JComboBox armasBox;
  JComboBox comodosBox;
  JButton confirmar; 
  JLabel label = new JLabel("Selecione um suspeito, uma arma e um comodo para fazer o palpite");
  private String[] palpite = new String[3];
  private int cont_selected = 0;

  /* construtor recebe uma String que indica se será Palpite ou Acusação */
  PalpiteAcusacao(String tipo){
    this.setVisible(true);
    this.pack();
    this.setLayout(new FlowLayout());
    this.setTitle(tipo);
    this.setSize(300, 300);
    
    label.setBounds(10, 10, 200, 200); //// corrigir isso daqui
    this.add(label);

    confirmar = new JButton("Confirmar "+tipo);
    confirmar.setBounds(50, 150, 100, 50);
    confirmar.addActionListener(this);
    this.add(confirmar);

    /* tratamento relativo a suspeitos */
    suspeitos[0] = "Srta. Scarlet";
    suspeitos[1] = "Coronel Mustard";
    suspeitos[2] = "Professor Plum";
    suspeitos[3] = "Reverendo Green";
    suspeitos[4] = "Sra. White";
    suspeitos[5] = "Sra. Peacock"; 

    suspeitosBox = new JComboBox(suspeitos);
    suspeitosBox.addActionListener(this);
    this.add(suspeitosBox);

    /* tratamento relativo a armas */
    armas[0] = "Corda";
    armas[1] = "Cano de Chumbo";
    armas[2] = "Faca";
    armas[3] = "Chave Inglesa";
    armas[4] = "Castical";
    armas[5] = "Revolver"; 

    armasBox = new JComboBox(armas);
    armasBox.addActionListener(this);
    this.add(armasBox);

    /* tratamento relativo a comodos */
    comodos[0] = "Biblioteca";
    comodos[1] = "Cozinha";
    comodos[2] = "Entrada";
    comodos[3] = "Escritorio";
    comodos[4] = "Jardim de Inverno";
    comodos[5] = "Sala de Estar"; 
    comodos[6] = "Sala de Jantar";     
    comodos[7] = "Sala de Jogos"; 
    comodos[8] = "Sala de Musica";
    
    comodosBox = new JComboBox(comodos);
    comodosBox.addActionListener(this);
    this.add(comodosBox);
  }

  public void actionPerformed(ActionEvent e) {
    /* verifica se já foi selecionado um suspeito, uma arma e um cômodo */
    if(e.getSource() == confirmar && cont_selected == 3){
      System.out.printf("S: %s\nA: %s\nC: %s\n", palpite[0], palpite[1], palpite[2]); // testar se funcionou (sim)
      
      /////// vai retornar o palpite/acusacao com get_retorno()
    }
      
    /* selecionando as combobox, adiciona o elemento selecionado na respectiva posição do array de retorno. Se estiverem sendo selecionadas pela primeira vez incrementao contador */
    else if(e.getSource() == suspeitosBox){
      if(palpite[0] == null){
        cont_selected++;
      }
      palpite[0] = (String)suspeitosBox.getSelectedItem();
    }

    else if(e.getSource() == armasBox){
      if(palpite[1] == null){
        cont_selected++;
      }
      palpite[1] = (String)armasBox.getSelectedItem();
    }

    else if(e.getSource() == comodosBox){
      if(palpite[2] == null){
        cont_selected++;
      }
      palpite[2] = (String)comodosBox.getSelectedItem();
    }
  }

  public String[] get_retorno(){
    return palpite;
  }  
}