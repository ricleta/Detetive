/////////////////////REFAZER

package controller;

import java.util.Arrays;
import java.util.Random;
import regras.*;

public class Controller
{
	Regras r;
	String [] j_personagens; // array para conter nome dos personagens de cada jogador
	boolean [] j_estados; // array para conter a boolean que indica se um jogador foi eliminado ou nao
	int n_jogadores = 0;
	
	public Controller(int n_jogadores)
	{
		r = new Regras(n_jogadores);
		this.n_jogadores = n_jogadores;
		
		j_personagens = new String[n_jogadores];
		j_estados = new boolean[n_jogadores];
		
		j_personagens = r.getNomeJogadores();
		j_estados = r.getStatusJogadores();
	}
	
	
	// retorna nome do personagem do jogador que deveria jogar nesse turno
	// o tipo de retorno talvez tenha que mudar quando formos implementar a gui
	/* retorna o jogador da vez (ou o aviso de que o jogador foi eliminado) */
	public String get_jogador_atual(int turno_atual)
	{
		int id_j_atual = turno_atual % n_jogadores;

		// se jogador pode jogar, retorna nome
		// se foi eliminado por uma acusao errada, retorna aviso
		return (!j_estados[id_j_atual]) ? j_personagens[id_j_atual] : "Jogador Eliminado"; 
	}

	/* seleciona um número randômico de 1 a 6 para indicar o valor do dado */
	int roll_die()
	{
		Random random = new Random();
		    
		return random.nextInt(1,7) + random.nextInt(1,7);
	}
	
	/* Jogador j faz uma acusação sobre os dados do assassinato. Se a acusação estiver correta, j vence o jogo, caso contrário é eliminado */
	boolean faz_acusacao(String j_personagem, String suspeito, String arma, String comodo)
	{    
		if (r.verifica_acusacao(suspeito, arma, comodo))
	    {
	      // main deve encerrar jogo atual
	      return true;
	    }
	    else
	    {
	      r.set_jogador_eliminado(j_personagem);
	      return false;
	    }
	  }
	
	/* Jogador j faz um palpite sobre os dados do assassinato e é verificado na mão dos demais jogadores se possuem alguma das cartas do palpite */
	String faz_palpite(String j_personagem, String suspeito, String arma, String comodo)
	  {
	    String resultado = null;
	    String j_investigado; // jogador cujas cartas serao analisadas
	    int i, k;

	    k = Arrays.binarySearch(j_personagens, j_personagem);
	    
	    for(i = k; i < j_personagens.length; i++)
	    {
	      j_investigado = j_personagens[i];
	      resultado = r.verifica_palpite(j_investigado, suspeito, arma, comodo);
	      
	      if (resultado != null)
	      {
	    	  return resultado;
	      }
	    }

	    for(i = 0; i < k; i++)
	    {
	      j_investigado = j_personagens[i];
	      resultado = r.verifica_palpite(j_investigado, suspeito, arma, comodo);
	      
	      if (resultado != null)
	      {
	    	  return resultado;
	      }
	    }

	   
	    // return value temporario, depende da interface
	    return "Palpite nao foi refutado";
	  }
}
