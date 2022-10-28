package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CartasMostradas extends JFrame{

  JPanel p;
  
  CartasMostradas()
  {
    this.setSize(500,500);
    this.setLayout(null);
    this.setTitle("Selecione os personagens do jogo");
    this.setVisible(true);
    



    
  }

  public void paint(Graphics g)
	{
		super.paint(g);
		
		// g.drawImage(img_tabuleiro, 1000, 1000, this); //// mudar pra permitir uma imagem generica ou sla
	}


  
}