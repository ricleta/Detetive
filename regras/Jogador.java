package regras;

import java.util.*;

class Jogador
{
  private int x;
  private int y;
  private String suspeito;
  private ArrayList <String> cartas = new ArrayList<String>();  
  private boolean eliminado;
  
  // Selecao de nome feita de uma lista na gui, não pode ser inválida
  Jogador(int x, int y, String suspeito) 
  {
    this.x = x;
    this.y = y;
    this.suspeito = suspeito;
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

  String getsuspeito()
  {
    return suspeito;
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

  String[] get_cartas()
  {
    return cartas;
  }

  // void movimentar(){
    // int dado;

    // dado = roll_die();

    // System.out.println("Resultado do dado: "+dado); ////// passar isso p interface gráfica 

    //TODO 
  // }
}