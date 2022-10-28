package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class TelaJogo extends JFrame implements ActionListener
{
  Image img_tabuleiro;
  JPanel p;
  
  JButton passagemSecreta = new JButton("Passagem Secreta");
  JButton proximo = new JButton("Proximo");
  JButton show_cards = new JButton("Mostrar cartas");
  JButton notepad = new JButton("Bloco de Notas");
  JButton palpite = new JButton("Palpite");
  JButton acusar = new JButton("Acusar");	  
  JButton save_game = new JButton("Salvar jogo");
  JButton dado = new JButton("Jogar Dados");

  public TelaJogo()
  {
	  try 
	  {
		  img_tabuleiro = ImageIO.read(new File("images/board.jpg"));
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
	  this.setTitle("Jogo");
	    
	  p = new TabPanel(img_tabuleiro);
	  p.setBounds(100, 0, 700, 700);
	  p.setBackground(Color.white);
	  this.getContentPane().add(p);
	  
	  this.add(passagemSecreta);
	  passagemSecreta.setBounds(1020,100,150,50);
	  passagemSecreta.addActionListener(this);
	  
	  this.add(proximo);
	  proximo.setBounds(1020,150,150,50);
	  proximo.addActionListener(this);
	    
	  this.add(show_cards);
	  show_cards.setBounds(1020,200,150,50);
	  show_cards.addActionListener(this);
	    
	  this.add(notepad);
	  notepad.setBounds(1020,250,150,50);
	  notepad.addActionListener(this);
	    
	  this.add(palpite);
  	palpite.setBounds(1020,300,150,50);
  	palpite.addActionListener(this);
	    
	  this.add(acusar);
	  acusar.setBounds(1020,350,150,50);
	  acusar.addActionListener(this);
	    
	  this.add(save_game);
	  save_game.setBounds(1020,450,150,50);
	  save_game.addActionListener(this);
	    
	  this.add(dado);  
	  dado.setBounds(1020,600,150,50);
	  dado.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e){
    if(e.getSource() == passagemSecreta)
    {
      
    }
    else if(e.getSource() == proximo)
    {
      
    }
    else if(e.getSource() == show_cards)
    {
      
    }
    else if(e.getSource() == notepad)
    {
      Notas bloco = new Notas();
      
    }
    else if(e.getSource() == palpite)
    {
      ////// tem q verificar se o jogador ta dentro do quarto pra poder fzr o palpite
     PalpiteAcusacao palpite = new PalpiteAcusacao("Palpite");

      ///// botar as paradas de palpite
    }
    else if(e.getSource() == acusar)
    {
     PalpiteAcusacao acusacao = new PalpiteAcusacao("Acusacao");

      ///// botar as paradas de acusacao
    }
    else if(e.getSource() == save_game)
    {
      
    }
    else if(e.getSource() == dado)
    {
      ///// rodar os dados, mudar a imagem dependendo dos resultados
    }
  }
  
  public void paint(Graphics g)
	{
		super.paint(g);
		
		g.drawImage(img_tabuleiro, 1000, 1000, this);
	}
}