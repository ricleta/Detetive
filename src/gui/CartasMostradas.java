package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CartasMostradas extends JFrame{
  // Image[] suspeitos;
  // Image[] armas;
  // Image[] comodos;
  
  JPanel p;
  
  CartasMostradas()
  {
    this.setSize(700,700);
    this.setLayout(null);
    this.setTitle("Selecione os personagens do jogo");
    this.setVisible(true);
    

    
  }

  public void paint(Graphics g)
	{
		super.paint(g);

    // for(Image img: cartas){
		  // g.drawImage(cartas, 1000, 1000, this); 
    // }
	}


  
}