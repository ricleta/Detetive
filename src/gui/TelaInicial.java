package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaInicial extends JFrame implements ActionListener{
  JButton criarJogo, continuarJogo;
  int i;

  public TelaInicial()
  {
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.setVisible(true);
    this.setLayout(null);
    this.setSize(1200, 700);
    
    this.setTitle("Tela inicial");

    criarJogo = new JButton("Novo jogo");
    continuarJogo = new JButton("Continuar jogo");

    criarJogo.setBounds(100,100,100,100); // mudar isso aqui 
    criarJogo.addActionListener(this);
    this.add(criarJogo);
    
    continuarJogo.setBounds(300,100,100,100); // mudar isso aqui 
    continuarJogo.addActionListener(this);
    this.add(continuarJogo);
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == criarJogo)
    {
      // System.out.println("Vai pra tela personagens");  
      TelaPersonagens tela_perso = new TelaPersonagens();

      this.dispose();
    }
    else if(e.getSource() == continuarJogo)
    {
      System.out.println("Vai para jogo salvo");
    }
  }

}
