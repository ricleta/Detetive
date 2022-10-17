package regras;

import java.util.*;

class Jogador
{
  /**** atributos ****/
  private int x;
  private int y;
  private String personagem;
  private ArrayList <String> cartas = new ArrayList<String>();  
  private boolean eliminado;

  /**** construtor e métodos ****/
  
  // Selecao de nome feita de uma lista na gui, não pode ser inválida
  Jogador(int x, int y, String personagem) 
  {
    this.x = x;
    this.y = y;
    this.personagem = personagem;
    this.eliminado = false;
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

  /* retorna o jogador (nome/cor) */
  String getPersonagem()
  {
    return personagem;
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