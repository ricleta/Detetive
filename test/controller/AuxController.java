package controller;

import java.util.ArrayList;

import regras.*;

class AuxController extends Controller {

	static AuxCtrlRegras ctrl_r;
	
	public static void setConfig(int num_jogadores, ArrayList<String> personagens) 
	{
		update_estado(2);
		ctrl_r = new AuxCtrlRegras(n_jogadores, personagens);
		n_jogadores = num_jogadores;
		turno = 0;

		j_personagens = new String[n_jogadores];
		j_estados = new boolean[n_jogadores];

		// armazenam informacoes dos jogadores marcados na Tela de selecao de
		// personagens
		j_personagens = ctrl_r.getNomeJogadores();
		j_estados = ctrl_r.getStatusJogadores();
//		ctrl_r.distribui_cartas();
	}
	
	public static void setConfigController(int num_jogadores, ArrayList<String> personagens) 
	{
		Controller.setConfig(num_jogadores, personagens);
	}
	
	public static boolean faz_acusacao(String j_personagem, String suspeito, String arma, String comodo) {
		if (ctrl_r.verifica_acusacao(suspeito, arma, comodo)) {
			// main deve encerrar jogo atual
			return true;
		} else {
			ctrl_r.set_jogador_eliminado(j_personagem);
			return false;
		}
	}
}
