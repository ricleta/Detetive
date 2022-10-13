package regras;

import java.util.*;

class Regras
{
  private String suspeito[] = {"Srta. Scarlet", "Coronel Mustard", "Professor Plum", "Reverendo Green", "Sra. White", "Sra. Peacock"};

  private String armas[] = {"Corda", "Cano de Chumbo", "Faca", "Chave Inglesa", "Castiçal", "Revólver"}; 

  private String comodo[] = {"Banheiro", "Escritório", "Sala de Estar", "Sala de jogos", "Garagem", "Quarto", "Sala de Estar", "Cozinha", "Pátio"};

  // 0 -> suspeito, 1 -> arma, 2-> comodo
  private String envelope[] = new String[3];

  // array de cartas que não estão no envelope
  // tam = 5 + 5 + 8 = 18, nao considera envelope
  private ArrayList <String> cartas = new ArrayList<String>();  
  Jogador jogadores[]; 

  public Regras(int num_jogadores)
  {
    jogadores = new Jogador[num_jogadores];

    // Srta scarlet e sempre a primeira a jogar, então deve sempre existir numa partida
    jogadores[0] = new Jogador(0, 0, suspeito[1]);
  }
  
  int roll_die()
  {
    Random random = new Random();
    
    return random.nextInt(1,7);
  }

  void set_envelope()
  {
    Random r = new Random();
    
    envelope[0] = suspeito[r.nextInt(0, 6)];
    envelope[1] = armas[r.nextInt(0, 6)]; 
    envelope[2] = comodo[r.nextInt(0, 9)];
  }

  void embaralhador()
  {
    Random r = new Random();
    int i = 0;
    
    for(i = 0; i < 6; i++)
    {
      if(!(suspeito[i].equals(envelope[0])))
      {
        cartas.add(suspeito[i]);
      }
    }
    
    for(i = 0; i < 6; i++)
    {
      if(!(armas[i].equals(envelope[1])))
      {    
        cartas.add(armas[i]);
      }
    }
    
    for(i = 0; i < 9; i++)
    {
      if(!(comodo[i].equals(envelope[2])))
      {    
        cartas.add(comodo[i]);
      }
    }

    Collections.shuffle(cartas);
    //// P embaralhar lista é Collections.shuffle(cartas)
  }
  
  void distribui()
  {
    int sobra = cartas.size() % jogadores.length;
    int quociente = (cartas.size() - sobra) / jogadores.length;
 
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

  void faz_acusacao(Jogador j, String suspeito, String arma, String comodo)
  {    
    if (verifica_acusacao(suspeito, arma, comodo))
    {
      // encerrar jogo
    }
    else
    {
      j.setEliminado();
    }
  }
    
}