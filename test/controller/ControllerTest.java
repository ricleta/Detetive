package controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class ControllerTest {

	private String suspeitos[] = {"Srta. Scarlet", "Coronel Mustard", "Professor Plum", "Reverendo Green", "Sra. White", "Sra. Peacock"};

	@Test
	public void testController() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGet_jogador_atual() 
	{
		Controller c[] = new Controller[4];
		
		for (int i = 0; i < c.length; i++)
		{
			int n_jog = 3 + i;
			c[i] = new Controller(n_jog);
			
			for (int j = 1; j < n_jog; j++)
			{
				c[i].r.add_jogador(suspeitos[i]);
			}
			
			for(int cont = 0; cont < n_jog; cont++)
			{
				assertTrue(c[i].get_jogador_atual(cont).equals(c[i].j_personagens[i]));
				assertFalse(c[i].get_jogador_atual(cont).equals("Jessica"));
			}
		}
	}
	
	@Test
	public void testRoll_die() 
	{
		Controller c = new Controller(3);
		
		int dado = c.roll_die();
		
		assertTrue(dado > 0 && dado < 7);
	}
	

	// testa verifica_acusao tabmbem
		@Test
		public void testFaz_acusacao() 
		{
			Controller c = new Controller(4);
			
			for (int i = 1; i < 4; i++)
			{
				c.r.add_jogador(suspeitos[i]);
			}
			
			c.r.distribui();
			
			String env_aux[] = c.r.get_envelope();
			String [] personagens= c.r.getNomeJogadores();
			boolean [] eliminados = c.r.getStatusJogadores();
 			String aux = "Suspeito que nao existe";
			
			// testa acusao certa
			assertTrue(c.faz_acusacao(personagens[0], env_aux[0], env_aux[1], env_aux[2]));
			
			// testa acusacao errada, com o suspeito errado
			assertFalse(c.faz_acusacao(personagens[1], aux, env_aux[1], env_aux[2]));
			
			// testa acusacao errada, com a arma errada
			assertFalse(c.faz_acusacao(personagens[2], env_aux[0], aux, env_aux[2]));
			
			// testa acusacao errada, com o comodo errado
			assertFalse(c.faz_acusacao(personagens[3], env_aux[0], env_aux[1], aux));
			
			// verifica se jogadores que fizeram acusacao errada, forma eliminados
			assertTrue(eliminados[1] && eliminados[2] && eliminados[3]);
		}
		
		@Test
		public void testFaz_palpite() 
		{
			AuxController c = new AuxController(4);
			
			for (int i = 1; i < 4; i++)
			{
				c.r.add_jogador(suspeitos[i]);
			}
			
			c.r.distribui("Coronel Mustard", "Corda", "Sala de Estar");
			
			String env_aux[] = {"Coronel Mustard", "Corda", "Sala de Estar"};
		
			String []res = new String[4];
			
			// palpite certeiro, todos estao no envelope
			res[0] = c.faz_palpite(c.j_personagens[0], env_aux[0], env_aux[1], env_aux[2]);
			
			// palpite incorreto de suspeito
			res[1] = c.faz_palpite(c.j_personagens[1], "Professor Plum", env_aux[1], env_aux[2]);
			
			// palpite incorreto de arma
			res[2] = c.faz_palpite(c.j_personagens[2], env_aux[0], "Faca", env_aux[2]);
			
			// palpite incorreto de comodo
			res[3] = c.faz_palpite(c.j_personagens[3], env_aux[0], env_aux[1], "Garagem");
			
			// testa acusacao certa
			assertEquals(res[0], "Palpite nao foi refutado");
			
			// testa acusacao errada, com o suspeito errado
			assertEquals(res[1], "Professor Plum");
			
			// testa acusacao errada, com a arma errada
			assertEquals(res[2], "Faca");
			
			// testa acusacao errada, com o comodo errado
			assertEquals(res[3], "Garagem");
		}

}
