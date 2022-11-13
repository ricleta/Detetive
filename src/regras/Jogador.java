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
  boolean[] checked_suspeitos = new boolean[6]; 
  boolean[] checked_armas = new boolean[6];
  boolean[] checked_comodos = new boolean[9];

  /**** construtor e métodos ****/
  // Selecao de nome feita de uma lista na gui, não pode ser inválida
  Jogador(String personagem) 
  {
    this.personagem = personagem;
    this.eliminado = false;
    this.inicializa_checkeds();
    ini_coord_jogadores();
  }
  
  void ini_coord_jogadores() 
  {
	  switch (personagem)
	  {
	  	case "Srta. Scarlet":
	  		x = 7;
	  		y = 24;
	  		break;
	  	case "Coronel Mustard":
	  		x = 0;
	  		y = 17;
	  		break;
	  	case "Professor Plum":
	  		x = 23;
	  		y = 19;
	  		break;
	  	case "Reverendo Green":
	  		x = 14;
	  		y = 0;
	  		break;
	  	case "Sra. White":
	  		x = 9;
	  		y = 0;
	  		break;
	  	case "Sra. Peacok":
	  		x = 23;
	  		y = 6;
	  		break;
	  }
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

  private void inicializa_checkeds(){
    int i;

    for(i = 0; i < 6; i++){
      checked_suspeitos[i] = false;
      checked_armas[i] = false;
      checked_comodos[i] = false;
    }
    for(i = 6; i < 9; i++){
      checked_comodos[i] = false;
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