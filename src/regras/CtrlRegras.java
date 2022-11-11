package regras;

import java.util.ArrayList;

public class CtrlRegras 
{
	private static CtrlRegras ctrl = null;
	Regras r;
	Tabuleiro t;
	
	private CtrlRegras(int n_jogadores, ArrayList <String> personagens)
	{
		r = new Regras(n_jogadores, personagens);
		t = new Tabuleiro();
	}
	
	public static CtrlRegras getCtrlRegras(int n_jogadores, ArrayList <String> personagens)
	{
		if (ctrl == null)
		{
			ctrl = new CtrlRegras(n_jogadores, personagens);
		}
		
		return ctrl;
	}
	
	
/* Metodos de Regras que devem ser acessados por outro pacote*/
	public String [] getNomeJogadores() 
	{
		return r.getNomeJogadores();
	}
	  
	public boolean [] getStatusJogadores() 
	{
		return r.getStatusJogadores();
	}
	    
	public void set_jogador_eliminado(String personagem) 
	{
		r.set_jogador_eliminado(personagem);
	}
	
	public boolean verifica_acusacao(String suspeito, String arma, String comodo)
	{
		return r.verifica_acusacao(suspeito, arma, comodo);
	}
	
	public String verifica_palpite(String persona_prox_jog, String suspeito, String arma, String comodo)
	{
		return r.verifica_palpite(persona_prox_jog, suspeito, arma, comodo);
	}
	
	// n_mov -> numero de casas que o jogador pode andar, de acordo com o valor do
	  // dado
	public int [][] encontra_movimentos(int x, int y, int n_mov)
	  {
		Movimento m = new Movimento(t.tab);
		ArrayList <Cell> cells = new ArrayList <Cell>();
		int [][]coord_possiveis;
		
		Cell atual = t.tab[y][x];
		
			
		cells.add(atual);
		cells = m.encontra_mov(cells, n_mov);
		
		coord_possiveis = new int[cells.size()][2];
		
		for (int i = 0; i < cells.size(); i++)
		{
			coord_possiveis[i][0] = cells.get(i).get_X();
			coord_possiveis[i][1] = cells.get(i).get_Y();
		}
		
		return coord_possiveis;
	  }
	
	public boolean [][] get_notas_jog_atual()
	{
		// TODO
		return null;
	}
	
	public void atualiza_cell_ocupada(int x, int y)
	{
		int estado_atual = t.tab[y][x].get_estado();
		
		if (estado_atual == 0)
		{
			t.tab[y][x].set_estado(3);
		}
		else if (estado_atual == 3)
		{
			t.tab[y][x].set_estado(0);
		}
	}
}
