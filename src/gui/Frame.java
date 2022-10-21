package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Frame extends JFrame implements ActionListener {
  JRadioButton vetRButton[] = new JRadioButton[4];
  ButtonGroup bGroup = new ButtonGroup();
  int num_jogadores;
  int i;

  Frame(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());

    for(i = 0; i <= 3; i++){
      vetRButton[i] = new JRadioButton((i+3)+"");
      vetRButton[i].setFocusable(false);
      vetRButton[i].addActionListener(this);
      this.add(vetRButton[i]);
      bGroup.add(vetRButton[i]);
    }

    this.setVisible(true);
    this.pack();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == vetRButton[0]){
      num_jogadores = 3; 
    }
    else if(e.getSource() == vetRButton[1]){
      num_jogadores = 4;
    }
    else if(e.getSource() == vetRButton[2]){
      num_jogadores = 5;
    }
    else{
      num_jogadores = 6;
    }
  }

  int get_num_jogadores(){
    return num_jogadores;
  }
}