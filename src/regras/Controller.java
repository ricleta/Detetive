package regras;

import java.util.ArrayList;
import java.util.Random;

public class Controller
{
	Regras r;
	ArrayList <Jogador> jogadores;
	
	public Controller(int n_jogadores)
	{
		r = new Regras(n_jogadores);
		jogadores = r.getJogadores();
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
	
	/* Jogador j faz uma acusação sobre os dados do assassinato. Se a acusação estiver correta, j vence o jogo, caso contrário é eliminado */
	boolean faz_acusacao(Jogador j, String suspeito, String arma, String comodo)
	{    
		if (r.verifica_acusacao(suspeito, arma, comodo))
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
	      resultado = r.verifica_palpite(jog, suspeito, arma, comodo);
	      
	      if (resultado != null)
	      {
	    	  return resultado;
	      }
	    }

	    for(i = 0; i < k; i++)
	    {
	      jog = jogadores.get(i);
	      resultado = r.verifica_palpite(jog, suspeito, arma, comodo);
	      
	      if (resultado != null)
	      {
	    	  return resultado;
	      }
	    }

	   
	    // return value temporario, depende da interface
	    return "Palpite nao foi refutado";
	  }
}
