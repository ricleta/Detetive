package regras;

import java.util.*;

class Jogador
{
  private int x;
  private int y;
  private String personagem;
  private ArrayList <String> cartas = new ArrayList<String>();  
  private boolean eliminado;
  
  // Selecao de nome feita de uma lista na gui, não pode ser inválida
  Jogador(int x, int y, String personagem) 
  {
    this.x = x;
    this.y = y;
    this.personagem = personagem;
    this.eliminado = false;
  }

  int getX()
  {
    return x;
  }

  int getY()
  {
    return y;
  }

  String getPersonagem()
  {
    return personagem;
  }

  boolean getEliminado()
  {
    return eliminado;
  }

  void setEliminado()
  {
    eliminado = true;
  }
  
  void setX(int x)
  {
    this.x = x;
  }

  void setY(int y)
  {
    this.y = y;
  }

  void add_carta(String carta)
  {
    cartas.add(carta);
  }

  ArrayList <String> get_cartas()
  {
    return cartas;
  }
}