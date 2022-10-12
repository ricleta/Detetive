package regras;

import java.util.Random;

class Regras
{
  String personagem[] = {"Coronel_Mustard", "Srta. Scarlet", "Professor Plum", "Reverendo Green", "Sra. White", "Sra. Peacock"};

  String armas[] = {"Corda", "Cano de Chumbo", "Faca", "Chave Inglesa", "Castiçal", "Revólver"}; 

  String comodo[] = {"Banheiro", "Escritório", "Sala de Estar", "Sala de jogos", "Garagem", "Quarto", "Sala de Estar", "Cozinha", "Pátio"};

  // 0 -> personagem, 1 -> arma, 2-> comodo
  String envelope[] = new String[3]; 

  private int n_jogadores;

  public Regras(int n)
  {
    n_jogadores = n;
  }
  
  int roll_die()
  {
    Random random = new Random();
    
    return random.nextInt(1,7);
  }

  void set_envelope()
  {
    Random r = new Random();
    
    envelope[0] = personagem[r.nextInt(0, 6)];
    envelope[1] = armas[r.nextInt(0, 6)]; 
    envelope[2] = comodo[r.nextInt(0, 9)];
  }

  void embaralhador()
  {
    Random r = new Random();
    String cartas[] = new String[18]; // 5 + 5 + 8 = 18, nao considera envelope
    int i = 0;
    
    for(; i < 5; i++)
    {
      cartas[i] = personagem[i]; // indice 0 ate 4
      cartas[i+5] = armas[i]; // indice 5 ate 10
    }

    for(i = 11; i < 18; i++)
    {
      cartas[i] = comodo[i  - 11];    
    }
  }
}