package regras;

import java.util.ArrayList;

public class AuxCtrlRegras extends CtrlRegras 
{
	public AuxRegras r;
	public Tabuleiro t;
	public Movimento m;
	public Jogador jog_atual;

	public AuxCtrlRegras(int n_jogadores, ArrayList<String> personagens) 
	{
		super(n_jogadores, personagens);
		r = new AuxRegras(n_jogadores, personagens);
	}
	
	public boolean verifica_acusacao(String suspeito, String arma, String comodo) {
		return r.verifica_acusacao(suspeito, arma, comodo);
	}
	
	public boolean[] getStatusJogadores() 
	{
		return r.getStatusJogadores();
	}

	public void set_jogador_eliminado(String personagem) {
		r.set_jogador_eliminado(personagem);
	}
}
