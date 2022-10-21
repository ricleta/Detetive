package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaPersonagens  extends JFrame implements ActionListener{
	  JCheckBox[] boxes = new JCheckBox[6];
	  JButton iniciarJogo = new JButton("Iniciar jogo");
	  int i;
	  int n_jogadores = 0;

	  public TelaPersonagens(){
	    this.setSize(1200,700);
	    this.setLayout(null);
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
	      boxes[i].setBounds(1000, 200 + (50 * i), 200, 50);
	    }
	    
	    boxes[0].setSelected(true); 
	    boxes[0].setEnabled(false);

	    iniciarJogo.setBounds(1000, 500, 150, 50);
	    iniciarJogo.addActionListener(this);
	    this.add(iniciarJogo);
	  }

	  public void actionPerformed(ActionEvent e){
	    if(e.getSource() == iniciarJogo){
	      if(this.checkJogValidos() != -1)
        {
	      System.out.println(n_jogadores);
          TelaJogo t_jogo = new TelaJogo();
          this.dispose();
	      }
	      else{
	        System.out.println("N pode "+n_jogadores);
	      }
	    }
	  }

	  public int checkJogValidos(){
	    n_jogadores = 0;
	    
	    for(JCheckBox box : boxes){
	      if(box.isSelected())
	      {
	        n_jogadores++;
	      }
	    }

	    if(n_jogadores < 3){
	      return -1;
	    }
	    else
	    {
	      return n_jogadores;
	    }   
	  }
	}
