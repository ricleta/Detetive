package regras;

class AuxRegras extends Regras{

	public AuxRegras(int num_jogadores) {
		super(num_jogadores);
	}

	private void hard_code_envelope(String suspeito, String arma, String comodo)
	{
		envelope[0] = suspeito;
		envelope[1] = arma;
		envelope[2] = comodo;
	}
	
	/* método para distribuir as 18 cartas entre N jogadores */
	  void distribui(String suspeito, String arma, String comodo)
	  {
		this.hard_code_envelope(suspeito, arma, comodo);
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

}
