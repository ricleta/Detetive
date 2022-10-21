package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheckBox extends JFrame implements ItemListener, ActionListener{
  JCheckBox[] boxes = new JCheckBox[6];
  JButton iniciarJogo = new JButton("Iniciar jogo");
  int i;
  int contJog = 0;

  public CheckBox(){
    // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //????
    this.setSize(1200,700);
    this.setLayout(new FlowLayout());
    this.setTitle("Selecione os personagens do jogo");
    this.setVisible(true);
    
    boxes[0] = new JCheckBox("Srta. Scarlet");
    boxes[1] = new JCheckBox("Coronel Mustard");
    boxes[2] = new JCheckBox("Professor Plum");
    boxes[3] = new JCheckBox("Reverendo Green");
    boxes[4] = new JCheckBox("Sra. White");
    boxes[5] = new JCheckBox("Sra. Peacock");    

    for(i = 0; i < 6; i++){
      this.add(boxes[i]);
    }

    this.add(iniciarJogo);
  }

  @Override
  public void itemStateChanged(ItemEvent ie){
    if(ie.getStateChange() == ie.SELECTED){
      contJog++;
    }
    else if(ie.getStateChange() == ie.DESELECTED){
      contJog--;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e){
    if(e.getSource() == iniciarJogo){
      if(this.checkJogValidos() != -1){
        System.out.println("Arrochaaa");
        // manda para outra tela 
      }
      else{
        System.out.println("N pode");
      }
    }
  }

  public int checkJogValidos(){
    if(contJog < 3 || !(boxes[0].isSelected())){
      return -1; // inválido
    }
    else{
      return contJog;
    }
  }
  
}