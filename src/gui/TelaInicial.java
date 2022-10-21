package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaInicial extends JFrame implements ActionListener{
  JRadioButton vetRButton[] = new JRadioButton[4];
  ButtonGroup bGroup = new ButtonGroup();
  public int num_jogadores;
  int i;

  public TelaInicial(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setLayout(new FlowLayout());
    this.setSize(1200, 700);
    this.setTitle("Escolha o numero de jogadores");

    for(i = 0; i <= 3; i++)
    {
      vetRButton[i] = new JRadioButton((i+3)+"");
      vetRButton[i].setFocusable(false);
      vetRButton[i].addActionListener(this);
      this.add(vetRButton[i]);
      bGroup.add(vetRButton[i]);
    }
    
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == vetRButton[0])
    {
      num_jogadores = 3; 
      System.out.println(num_jogadores+" jogadores (teste)"); 
    }
    else if(e.getSource() == vetRButton[1])
    {
      num_jogadores = 4;
      System.out.println(num_jogadores+" jogadores (teste)");
    }
    else if(e.getSource() == vetRButton[2]){
      num_jogadores = 5;
      System.out.println(num_jogadores+" jogadores (teste)");
    }
    else{
      num_jogadores = 6;
      System.out.println(num_jogadores+" jogadores (teste)");
    }
  }

  public int get_num_jogadores()
  {
    return num_jogadores;
  }
}
