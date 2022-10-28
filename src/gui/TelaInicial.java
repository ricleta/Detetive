package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaInicial extends JFrame implements ActionListener{
  JButton criarJogo, continuarJogo;
  int i;

  Image img_ini;
  JPanel p;

  public TelaInicial()
  {
    try 
	  {
		  img_ini = ImageIO.read(new File("images/capa.jpg"));
	  } 
	  catch(IOException e)
	  {
		 System.out.println(e.getMessage());
		 System.exit(1);
	  }
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

  public void paint(Graphics g)
	{
		super.paint(g);
		
		g.drawImage(img_tabuleiro, 1000, 1000, this);
	}

}
