package regras;

import java.util.*;

class Regras
{
  /* atributos */
  private String suspeitos[] = {"Srta. Scarlet", "Coronel Mustard", "Professor Plum", "Reverendo Green", "Sra. White", "Sra. Peacock"};

  private String armas[] = {"Corda", "Cano de Chumbo", "Faca", "Chave Inglesa", "Castical", "Revolver"}; 

  private String comodos[] = {"Banheiro", "Escritorio", "Sala de Estar", "Sala de jogos", "Garagem", "Quarto", "Sala de Estudos", "Cozinha", "Patio"};

  // 0 -> suspeito, 1 -> arma, 2-> comodo
  private String envelope[] = new String[3];

  // array de cartas que não estão no envelope
  // tam = 5 + 5 + 8 = 18, nao considera envelope
  private ArrayList <String> cartas = new ArrayList<String>();  
  ArrayList <Jogador> jogadores; 

  /* construtor e métodos */
  
  public Regras(int num_jogadores)
  {
    jogadores = new ArrayList <Jogador>();

    // Srta scarlet e sempre a primeira a jogar, então deve sempre existir numa partida
    jogadores.add(new Jogador(0, 0, suspeitos[0]));
  }

  public void add_jogador(String personagem)
  {
    jogadores.add(new Jogador(0, 0, personagem));
  }

  // retorna nome do personagem do jogador que deveria jogar nesse turno
  // o tipo de retorno talvez tenha que mudar quando formos implementar a gui
  public String get_jogador_atual(int turno_atual)
  {
    Jogador j_atual = jogadores.get(turno_atual % jogadores.size());

    // se jogador pode jogar, retorna nome
    // se foi eliminado por uma acusao errada, retorna aviso
    return (!j_atual.getEliminado()) ? j_atual.getPersonagem() : "Jogador Eliminado"; 
  }
  
  int roll_die()
  {
    Random random = new Random();
    
    return random.nextInt(1,7);
  }

  void set_envelope()
  {
    Random r = new Random();
    
    envelope[0] = suspeitos[r.nextInt(0, 6)];
    envelope[1] = armas[r.nextInt(0, 6)]; 
    envelope[2] = comodos[r.nextInt(0, 9)];
  }
  
  // usado para testar set_envelope, por envelope ser private
  String [] get_envelope()
  {
	  return envelope;
  }

  /* método para embarlhar as cartas sem incluir as cartas do envelope */
  void embaralhador()
  {
    int i = 0;
    
    for(i = 0; i < 6; i++)
    {
      if(!(suspeitos[i].equals(envelope[0])))
      {
        cartas.add(suspeitos[i]);
      }
      
      if(!(armas[i].equals(envelope[1])))
      {    
        cartas.add(armas[i]);
      }
    }
    
    for(i = 0; i < 9; i++)
    {
      if(!(comodos[i].equals(envelope[2])))
      {    
        cartas.add(comodos[i]);
      }
    }
    
    Collections.shuffle(cartas);
  }
  
//usado para testar embaralhador, por cartas ser private 
  ArrayList <String> get_cartas()
  {
	  return cartas;
  }

  /* método para distribuir as 18 cartas entre N jogadores */
  void distribui()
  {
	  this.set_envelope();
	  this.embaralhador();
	
    int sobra = cartas.size() % jogadores.size();
    int quociente = (cartas.size() - sobra) / jogadores.size();
 
    if (sobra == 0)
    {
      for (Jogador jogador: jogadores)
      {
        for (int j = 0; j < quociente; j++)
        {
          jogador.add_carta(cartas.get(0));
          cartas.remove(0);
        }
      }
    }
    else
    {
      for (Jogador jogador: jogadores)
      {
        for (int j = 0; j < quociente; j++)
        {
          jogador.add_carta(cartas.get(0));
          cartas.remove(0);
        }

        if (sobra > 0)
        {
          jogador.add_carta(cartas.get(0));
          cartas.remove(0);
          sobra--;
        }
      }
    }
  }

  boolean verifica_acusacao(String suspeito, String arma, String comodo)
  {
    if(envelope[0].equals(suspeito))
    {
      if(envelope[1].equals(arma))
      {
        if(envelope[2].equals(comodo))
        {
          return true;
        }
      }
    }
    
    return false;
  }

  boolean faz_acusacao(Jogador j, String suspeito, String arma, String comodo)
  {    
    if (verifica_acusacao(suspeito, arma, comodo))
    {
      // main deve encerrar jogo atual
      return true;
    }
    else
    {
      j.setEliminado();
      return false;
    }
  }

  String verifica_palpite(Jogador prox_jog, String suspeito, String arma, String comodo)
  {
    for(String carta : prox_jog.get_cartas())
    {
      if(carta.equals(suspeito))
      {
        return suspeito;
      }
      
      if (carta.equals(arma))
      {
        return arma;
      }
      
      if (carta.equals(comodo))
      {
        return comodo;
      }
    }

    return null; 
  }

  String faz_palpite(Jogador j, String suspeito, String arma, String comodo)
  {
    String resultado = null;

    for(Jogador jog: jogadores) 
    {
      if(!jog.equals(j))
      {
        resultado = verifica_palpite(jog, suspeito, arma, comodo);
      }
    }

    if(resultado == null)
    {
      // return value temporario, depende da interface
      return "Palpite nao foi refutado";
    }
    
    return resultado;
  }
}