package regras;

import java.util.ArrayList;

import controller.Controller;

public class CtrlRegras implements ObservadorIF {
	private static CtrlRegras ctrl = null;
	Regras r;
	Tabuleiro t;
	Movimento m;
	Jogador jog_atual;

	private CtrlRegras(int n_jogadores, ArrayList<String> personagens) {
		r = new Regras(n_jogadores, personagens);
		t = new Tabuleiro();
		m = new Movimento(t.tab);
	}

	public static CtrlRegras getCtrlRegras(int n_jogadores, ArrayList<String> personagens) {
		if (ctrl == null) {
			ctrl = new CtrlRegras(n_jogadores, personagens);
			Controller.registra(ctrl);
		}

		return ctrl;
	}

	/* Metodos de Regras que devem ser acessados por outro pacote */
	public String[] getNomeJogadores() {
		return r.getNomeJogadores();
	}

	public boolean[] getStatusJogadores() {
		return r.getStatusJogadores();
	}

	public void set_jogador_eliminado(String personagem) {
		r.set_jogador_eliminado(personagem);
	}

	public boolean verifica_acusacao(String suspeito, String arma, String comodo) {
		return r.verifica_acusacao(suspeito, arma, comodo);
	}

	public String verifica_palpite(String persona_prox_jog, String suspeito, String arma, String comodo) {
		return r.verifica_palpite(persona_prox_jog, suspeito, arma, comodo);
	}

	// n_mov -> numero de casas que o jogador pode andar, de acordo com o valor do
	// dado
	public int[][] encontra_movimentos(int x, int y, int n_mov) {
		ArrayList<Cell> cells = new ArrayList<Cell>();
		int[][] coord_possiveis;

		Cell atual = t.tab[y][x];

		cells.add(atual);
		cells = m.encontra_mov(cells, n_mov);

		coord_possiveis = new int[cells.size()][2];

		for (int i = 0; i < cells.size(); i++) {
			coord_possiveis[i][0] = cells.get(i).get_X();
			coord_possiveis[i][1] = cells.get(i).get_Y();
		}

		return coord_possiveis;
	}

	private void get_jog_atual()
	{
		String personagem = Controller.get_jogador_atual();
		
		for (Jogador j : r.jogadores) {
			if (j.getPersonagem().equals(personagem)) {
				jog_atual = j;
				break;
			}
		}
	}
	
	public boolean[][] get_notas_jog_atual() 
	{
		int i;

		/* iterar pela lista até achar o jogador atual para poder retornar suas notas */
		get_jog_atual();
		
		for (String carta : jog_atual.get_cartas()) {
			for (i = 0; i < 6; i++) {
				if (carta.equals(r.suspeitos[i])) {
					jog_atual.checked_suspeitos[i] = true;
				} else if (carta.equals(r.armas[i])) {
					jog_atual.checked_armas[i] = true;
				} else if (carta.equals(r.comodos[i])) {
					jog_atual.checked_comodos[i] = true;
				}
			}

			for (; i < 9; i++) {
				if (carta.equals(r.comodos[i])) {
					jog_atual.checked_comodos[i] = true;
				}
			}
		}

		boolean[][] notas_atual = new boolean[3][];

		notas_atual[0] = jog_atual.checked_suspeitos;
		notas_atual[1] = jog_atual.checked_armas;
		notas_atual[2] = jog_atual.checked_comodos;

		return notas_atual;
	}

	public void distribui_cartas() {
		r.distribui();
	}

	public ArrayList<String> get_cartas_jog_atual() {
		get_jog_atual();

		return jog_atual.get_cartas();
	}

	public void atualiza_cell_ocupada(int x, int y) {
		int estado_atual = t.tab[y][x].get_estado();

		if (estado_atual == 0) {
			t.tab[y][x].set_estado(3);
		} else if (estado_atual == 3) {
			t.tab[y][x].set_estado(0);
		}
	}

	@Override
	public void notify_dado_jogado(ObservadoIF ob) 
	{
		int [] coord_token = ob.get_pos_token();
		int [] dados = ob.get_valor_dados();
		
		get_jog_atual();
		
		jog_atual.setX(coord_token[0]);
		jog_atual.setY(coord_token[1]);
		
		ob.set_coord_possiveis(encontra_movimentos(jog_atual.getX(), jog_atual.getY(), dados[0] + dados[1]));
	}
	
	@Override
	public void notify_jogador_moveu(ObservadoIF ob)
	{
		int [] coord_token = ob.get_pos_token();	
		
		Cell atual = t.tab[coord_token[1]][coord_token[0]];
		
		if (atual.get_estado() == 2)
		{
			coord_token = m.entra_comodo(atual, jog_atual.getPersonagem());
			
			jog_atual.setX(coord_token[0]);
			jog_atual.setY(coord_token[1]);
			
			ob.muda_pos_jog(jog_atual.getX(), jog_atual.getY());
			return;
		}
		
		jog_atual.setX(coord_token[0]);
		jog_atual.setY(coord_token[1]);
		
		ob.muda_pos_jog(jog_atual.getX(), jog_atual.getY());
		
		Controller.atualiza_cell_ocupada(coord_token[0], coord_token[1]);
	}
}
