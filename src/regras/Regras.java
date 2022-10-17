package regras;

import java.util.*;

public class Regras 
{
      /**** atributos ****/
	  private String suspeitos[] = {"Srta. Scarlet", "Coronel Mustard", "Professor Plum", "Reverendo Green", "Sra. White", "Sra. Peacock"};

	  private String armas[] = {"Corda", "Cano de Chumbo", "Faca", "Chave Inglesa", "Castical", "Revolver"}; 

	  private String comodos[] = {"Banheiro", "Escritorio", "Sala de Estar", "Sala de jogos", "Garagem", "Quarto", "Sala de Estudos", "Cozinha", "Patio"};

	  // 0 -> suspeito, 1 -> arma, 2-> comodo
	  protected String envelope[] = new String[3];

	  // array de cartas que não estão no envelope
	  // tam = 5 + 5 + 8 = 18, nao considera envelope
	  protected ArrayList <String> cartas = new ArrayList<String>();  
	  ArrayList <Jogador> jogadores; 

	  /**** construtor e métodos ****/
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
	  /* retorna o jogador da vez (ou o aviso de que o jogador foi eliminado) */
	  public String get_jogador_atual(int turno_atual)
	  {
	    Jogador j_atual = jogadores.get(turno_atual % jogadores.size());

	    // se jogador pode jogar, retorna nome
	    // se foi eliminado por uma acusao errada, retorna aviso
	    return (!j_atual.getEliminado()) ? j_atual.getPersonagem() : "Jogador Eliminado"; 
	  }

	  /* seleciona um número randômico de 1 a 6 para indicar o valor do dado */
	  int roll_die()
	  {
	    Random random = new Random();
	    
	    return random.nextInt(1,7);
	  }

	  /* 3 cartas, 1 de cada tipo selecionadas randomicamente para o envelope */
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

	  /* Jogador j faz uma acusação sobre os dados do assassinato. Se a acusação estiver correta, j vence o jogo, caso contrário é eliminado */
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
	  
	  /* verifica se a acusação feita pelo jogador está correta comparando as cartas do envelope com as informações da acusação */
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
	  
	  /* Jogador j faz um palpite sobre os dados do assassinato e é verificado na mão dos demais jogadores se possuem alguma das cartas do palpite */
	  String faz_palpite(Jogador j, String suspeito, String arma, String comodo)
	  {
	    String resultado = null;
	    Jogador jog; // jogador que vai ser acusado do palpite
	    int i, k;

	    k = jogadores.indexOf(j);
	    
	    for(i = k; i < jogadores.size(); i++)
	    {
	      jog = jogadores.get(i);
	      resultado = verifica_palpite(jog, suspeito, arma, comodo);
	      
	      if (resultado != null)
	      {
	    	  return resultado;
	      }
	    }

	    for(i = 0; i < k; i++)
	    {
	      jog = jogadores.get(i);
	      resultado = verifica_palpite(jog, suspeito, arma, comodo);
	      
	      if (resultado != null)
	      {
	    	  return resultado;
	      }
	    }

	   
	    // return value temporario, depende da interface
	    return "Palpite nao foi refutado";
	  }
	  
	  /* verifica se alguma das cartas do palpite está na mão do jogador. Se estiver, o palpite está errado */
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
}
