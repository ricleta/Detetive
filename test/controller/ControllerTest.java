package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class ControllerTest {

	String[] aux_suspeitos = new String[] { "Srta. Scarlet", "Coronel Mustard", "Professor Plum", "Reverendo Green",
			"Sra. White", "Sra. Peacock" };
	String[] aux_armas = new String[] { "Corda", "Cano de Chumbo", "Faca", "Chave Inglesa", "Castical", "Revolver" };
	String[] aux_comodos = new String[] { "Biblioteca", "Cozinha", "Entrada", "Escritorio", "Jardim de Inverno",
			"Sala de Estar", "Sala de Jantar", "Sala de Musica", "Salao de Jogos" };

	@Test
	public void testSetConfig() {
		ArrayList<String> jogadores = new ArrayList<String>();
		jogadores.add("Srta. Scarlet");
		jogadores.add("Coronel Mustard");
		jogadores.add("Professor Plum");

		Controller.setConfig(3, jogadores);

		for (int i = 0; i < jogadores.size(); i++) {
			assertEquals(Controller.j_personagens[i], jogadores.get(i));
		}
	}

	@Test
	public void testGet_jogador_atual() {
		ArrayList<String> jogadores = new ArrayList<String>();
		jogadores.add("Srta. Scarlet");
		jogadores.add("Coronel Mustard");
		jogadores.add("Professor Plum");

		Controller.setConfig(3, jogadores);

		assertEquals(Controller.get_jogador_atual(), jogadores.get(0));
		Controller.prox_turno();

		assertEquals(Controller.get_jogador_atual(), jogadores.get(1));
		Controller.prox_turno();

		assertEquals(Controller.get_jogador_atual(), jogadores.get(2));
		Controller.prox_turno();

		assertEquals(Controller.get_jogador_atual(), jogadores.get(0));
		Controller.prox_turno();

		assertEquals(Controller.get_jogador_atual(), jogadores.get(1));
		Controller.prox_turno();

		assertEquals(Controller.get_jogador_atual(), jogadores.get(2));
	}

	@Test
	public void testRoll_dice() {
		int[] dado = Controller.roll_dice();
		int res = dado[0] + dado[1];

		assertTrue(res > 0 && res < 13);
	}

	// testa verifica_acusao tabmbem
	@Test
	public void testFaz_acusacao() {
		ArrayList<String> jogadores = new ArrayList<String>();
		boolean[] eliminados;
		String env_aux[] = new String []{"Coronel Mustard", "Corda", "Sala de Estar"};
		String aux = "Suspeito que nao existe";
		
		jogadores.add("Srta. Scarlet");
		jogadores.add("Coronel Mustard");
		jogadores.add("Professor Plum");
		jogadores.add("Reverendo Green");

		AuxController.setConfig(4, jogadores);

		AuxController.ctrl_r.r.distribui(env_aux[0], env_aux[1], env_aux[2]);

		// testa acusao certa
		assertTrue(AuxController.faz_acusacao(jogadores.get(0), env_aux[0], env_aux[1], env_aux[2]));

		// testa acusacao errada, com o suspeito errado
		assertFalse(AuxController.faz_acusacao(jogadores.get(1), aux, env_aux[1], env_aux[2]));

		// testa acusacao errada, com a arma errada
		assertFalse(AuxController.faz_acusacao(jogadores.get(2), env_aux[0], aux, env_aux[2]));

		// testa acusacao errada, com o comodo errado
		assertFalse(AuxController.faz_acusacao(jogadores.get(3), env_aux[0], env_aux[1], aux));

		// verifica se jogadores que fizeram acusacao errada, foram eliminados
		eliminados = AuxController.ctrl_r.getStatusJogadores();
		
		assertTrue(!eliminados[0]);
		assertTrue(eliminados[1]);
		assertTrue(eliminados[2]);
		assertTrue(eliminados[3]);
	}

	@Test
	public void testFaz_palpite() {
		ArrayList<String> jogadores = new ArrayList<String>();
		String env_aux[] = new String []{"Coronel Mustard", "Corda", "Sala de Estar"};
		
		jogadores.add("Srta. Scarlet");
		jogadores.add("Coronel Mustard");
		jogadores.add("Professor Plum");

		AuxController.setConfig(3, jogadores);

		AuxController.ctrl_r.r.hard_code_envelope(env_aux[0], env_aux[1], env_aux[2]);
		AuxController.ctrl_r.r.hard_code_carta_jog(0, new String[]{"Salao de Jogos", "Srta. Scarlet", "Faca", "Reverendo Green", "Chave Inglesa", "Revolver"});
		AuxController.ctrl_r.r.hard_code_carta_jog(1, new String[]{"Escritorio", "Castical", "Sra. White", "Sala de Musica", "Cano de Chumbo", "Cozinha"});
		AuxController.ctrl_r.r.hard_code_carta_jog(2, new String[]{"Professor Plum", "Sra. Peacock", "Jardim de Inverno", "Entrada", "Sala de Jantar", "Biblioteca"});
		
		String[] res = new String[4];
				
		// palpite certeiro, todos estao no envelope
		res[0] = AuxController.faz_palpite(AuxController.j_personagens[0], env_aux[0], env_aux[1], env_aux[2]);

		// palpite incorreto de suspeito
		res[1] = AuxController.faz_palpite(AuxController.j_personagens[1], "Professor Plum", env_aux[1], env_aux[2]);

		// palpite incorreto de arma
		res[2] = AuxController.faz_palpite(AuxController.j_personagens[2], env_aux[0], "Faca", env_aux[2]);

		// palpite incorreto de comodo
		res[3] = AuxController.faz_palpite(AuxController.j_personagens[2], env_aux[0], env_aux[1], "Salao de Jogos");

		// testa acusacao certa
		assertTrue(res[0].equals(env_aux[0]) || res[0].equals(env_aux[1]) || res[0].equals(env_aux[2]));

		// testa acusacao errada, com o suspeito errado
		assertTrue(!res[1].equals(env_aux[0]) || !res[1].equals(env_aux[1]) || !res[1].equals(env_aux[2]));

		// testa acusacao errada, com a arma errada
		assertTrue(!res[2].equals(env_aux[0]) || !res[2].equals(env_aux[1]) || !res[2].equals(env_aux[2]));

		// testa acusacao errada, com o comodo errado
		assertTrue(!res[3].equals(env_aux[0]) || !res[3].equals(env_aux[1]) || !res[3].equals(env_aux[2]));
	}

	@Test
	public void testProx_turno() {
		ArrayList<String> jogadores = new ArrayList<String>();
		jogadores.add("Srta. Scarlet");
		jogadores.add("Coronel Mustard");
		jogadores.add("Professor Plum");

		Controller.setConfig(3, jogadores);

		for (int i = 0; i < 10; i++) {
			assertTrue(Controller.turno == i);

			Controller.prox_turno();
		}
	}

	@Test
	public void testGet_notas_jog_atual() {
		ArrayList<String> jogadores = new ArrayList<String>();
		jogadores.add("Srta. Scarlet");
		jogadores.add("Coronel Mustard");
		jogadores.add("Professor Plum");

		AuxController.setConfig(3, jogadores);
		AuxController.setConfigController(3, jogadores);

		boolean[][] result = AuxController.get_notas_jog_atual();

		ArrayList<String> cartas = AuxController.get_cartas_jog_atual();

		for (String carta : cartas) {
			for (int j = 0; j < result.length; j++) {
				for (int i = 0; i < result[j].length; i++) {
					if (j == 0) {
						if (carta.equals(aux_suspeitos[i])) {
							assertTrue(result[j][i]);
						}
					} else if (j == 1) {
						if (carta.equals(aux_armas[i])) {
							assertTrue(result[j][i]);
						}
					} else {
						if (carta.equals(aux_comodos[i])) {
							assertTrue(result[j][i]);
						}
					}
				}
			}
		}
	}

	@Test
	public void testGet_cartas_jog_atual() {
		ArrayList<String> jogadores = new ArrayList<String>();
		jogadores.add("Srta. Scarlet");
		jogadores.add("Coronel Mustard");
		jogadores.add("Professor Plum");

		AuxController.setConfig(3, jogadores);

		ArrayList<String> cartas = AuxController.get_cartas_jog_atual();

		for (String carta : cartas) {
			assertTrue(Arrays.asList(aux_suspeitos).contains(carta) || Arrays.asList(aux_armas).contains(carta)
					|| Arrays.asList(aux_comodos).contains(carta));
		}
	}

//	@Test
//	public void testEncontra_movimentos() 
//	{
//		Arrays.asList(aux_suspeitos).contains(carta)
//	}
//
	@Test
	public void testUpdate_estado() {
		ArrayList<String> jogadores = new ArrayList<String>();
		jogadores.add("Srta. Scarlet");
		jogadores.add("Coronel Mustard");
		jogadores.add("Professor Plum");
		
		Controller.setConfig(3, jogadores);

		// Como set config eh chamado no arquivo de testes, no estado sempre eh atualizado para 2
		// Contudo, ja que a tela inicial aparece quando a main eh chamada, temos certeza que o estado eh atualizado de 0 para 2
		assertEquals(Controller.estado, 2);
	}
//
//	@Test
//	public void testAtualiza_cell_ocupada() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testRegistra() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testMain() {
//		fail("Not yet implemented"); // TODO
//	}

}
