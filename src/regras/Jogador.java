package regras;

import java.util.*;
import java.awt.*;

class Jogador
{
  /**** atributos ****/
  private int x;
  private int y;
  private String personagem;
  private Color cor;
  private ArrayList <String> cartas = new ArrayList<String>();  
  private boolean eliminado;

  /**** construtor e métodos ****/
  
  // Selecao de nome feita de uma lista na gui, não pode ser inválida
  Jogador(String personagem) 
  {
    this.personagem = personagem;
    this.eliminado = false;
    this.setInicio(personagem);
  }

  /* retorna a posição do jogador no eixo x */
  int getX()
  {
    return x;
  }

  /* retorna a posição do jogador no eixo y */
  int getY()
  {
    return y;
  }

  /* retorna o jogador */
  String getPersonagem()
  {
    return personagem;
  }

  /* retorna a cor do jogador */
  Color getCor()
  {
    return cor;
  }

  /* retorna a situação do jogador (se foi eliminado ou não) */
  boolean getEliminado()
  {
    return eliminado;
  }

  /* muda a posição do jogador no eixo x */
  void setX(int x)
  {
    this.x = x;
  }

  /* muda a posição do jogador no eixo y */
  void setY(int y)
  {
    this.y = y;
  }

  /* setta a cor do jogador dependendo do personagem */
  void setInicio(String personagem)
  {
    if(personagem.equals("Srta. Scarlet"))
    {
      cor = Color.RED;
      x = 24;
      y = 7;
    }
    else if(personagem.equals("Coronel Mustard"))
    {
      cor = Color.YELLOW;
      x = 17;
      y = 0;
    }
    else if(personagem.equals("Professor Plum"))
    {
      cor = new Color(102, 0 ,153); // cor roxo em RGB
      x = 19;
      y = 23;
    }
    else if(personagem.equals("Reverendo Green"))
    {
      cor = Color.GREEN;
      x = 0;
      y = 14;
    }
    else if(personagem.equals("Sra. White"))
    {
      cor = Color.WHITE;
      x = 0;
      y = 9;
    }
    else if(personagem.equals("Sra. Peacock"))
    {
      cor = Color.BLUE;
      x = 6;
      y = 23;
    }
  }

  /* elimina o jogador do jogo */
  void setEliminado()
  {
    eliminado = true;
  }

  /* adiciona uma carta na mão do jogador */
  void add_carta(String carta)
  {
    cartas.add(carta);
  }

  /* retorna a lista de cartas que o jogador possui */
  ArrayList <String> get_cartas()
  {
    return cartas;
  }
}