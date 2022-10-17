package regras;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class RegrasTest {
	
	private String suspeitos[] = {"Srta. Scarlet", "Coronel Mustard", "Professor Plum", "Reverendo Green", "Sra. White", "Sra. Peacock"};

	private String armas[] = {"Corda", "Cano de Chumbo", "Faca", "Chave Inglesa", "Castical", "Revolver"}; 

	private String comodos[] = {"Banheiro", "Escritorio", "Sala de Estar", "Sala de jogos", "Garagem", "Quarto", "Sala de Estudos", "Cozinha", "Patio"};

	private ArrayList<String> l_suspeitos = new ArrayList<String>(Arrays.asList(suspeitos));
	private ArrayList<String> l_armas = new ArrayList<String>(Arrays.asList(armas));
	private ArrayList<String> l_comodos = new ArrayList<String>(Arrays.asList(comodos));
	
	// tambem testa a funcao add_jogador
	@Test
	public void testRegras() {
		Regras r = new Regras(3);
		
		for (int i = 1; i < 3; i++)
		{
			r.add_jogador(suspeitos[i]);
		}
		
		assertEquals(3, r.jogadores.size());
		
		for (Jogador j: r.jogadores)
		{
			assertTrue(l_suspeitos.contains(j.getPersonagem()));
		}
	}
	
	@Test
	public void testGet_jogador_atual() 
	{
		Regras r[] = new Regras[4];
		
		for (int i = 0; i < r.length; i++)
		{
			int n_jog = 3 + i;
			r[i] = new Regras(n_jog);
			
			for (int j = 1; j < n_jog; j++)
			{
				r[i].add_jogador(suspeitos[i]);
			}
			
			for(int cont = 0; cont < n_jog; cont++)
			{
				assertTrue(r[i].get_jogador_atual(cont).equals(r[i].jogadores.get(cont % 18).getPersonagem()));
				assertFalse(r[i].get_jogador_atual(cont).equals("Jessica"));
			}
		}
	}
	
	@Test
	public void testRoll_die() 
	{
		Regras r = new Regras(3);
		
		int dado = r.roll_die();
		
		assertTrue(dado > 0 && dado < 7);
	}

	@Test
	public void testSet_envelope() {
		Regras r = new Regras(3);
			
		r.set_envelope();
		
		assertTrue(l_suspeitos.contains(r.get_envelope()[0]));
		assertTrue(l_armas.contains(r.get_envelope()[1]));
		assertTrue(l_comodos.contains(r.get_envelope()[2]));
	}

	@Test
	public void testEmbaralhador() {
		Regras r = new Regras(3);
		
		r.set_envelope();
		r.embaralhador();
		
		ArrayList<String> cartas = r.get_cartas();
		
		assertFalse(cartas.contains(r.get_envelope()[0]));
		assertFalse(cartas.contains(r.get_envelope()[1]));
		assertFalse(cartas.contains(r.get_envelope()[2]));
		assertEquals(cartas.size(), 18);
	}

	@Test
	public void testDistribui() {
		Regras r[] = new Regras[4];
		
		for (int i = 0; i < r.length; i++)
		{
			int n_jog = 3 + i;
			r[i] = new Regras(n_jog);
			
			for (int j = 1; j < n_jog; j++)
			{
				r[i].add_jogador(suspeitos[i]);
			}
			
			r[i].distribui();
		}
		
		
		
		for (Jogador j: r[0].jogadores)
		{
			assertEquals(6, j.get_cartas().size());
		}
		
		for (int i = 0; i < r[1].jogadores.size(); i++)
		{
			if (i < 2)
			{
				assertEquals(5, r[1].jogadores.get(i).get_cartas().size());
			}
			else
			{
				assertEquals(4, r[1].jogadores.get(i).get_cartas().size());
			}
		}
		
		for (int i = 0; i < r[2].jogadores.size(); i++)
		{
			if (i < 3)
			{
				assertEquals(4, r[2].jogadores.get(i).get_cartas().size());
			}
			else
			{
				assertEquals(3, r[2].jogadores.get(i).get_cartas().size());
			}
		}
		
		
		for (Jogador j: r[3].jogadores)
		{
			assertEquals(3, j.get_cartas().size());
		}
		
	}

	// testa verifica_acusao tabmbem
	@Test
	public void testFaz_acusacao() 
	{
		Regras r = new Regras(4);
		
		for (int i = 1; i < 4; i++)
		{
			r.add_jogador(suspeitos[i]);
		}
		
		r.distribui();
		
		String env_aux[] = r.get_envelope();
		String aux = "Suspeito que nao existe";
		
		// testa acusao certa
		assertTrue(r.faz_acusacao(r.jogadores.get(0), env_aux[0], env_aux[1], env_aux[2]));
		
		// testa acusacao errada, com o suspeito errado
		assertFalse(r.faz_acusacao(r.jogadores.get(1), aux, env_aux[1], env_aux[2]));
		
		// testa acusacao errada, com a arma errada
		assertFalse(r.faz_acusacao(r.jogadores.get(2), env_aux[0], aux, env_aux[2]));
		
		// testa acusacao errada, com o comodo errado
		assertFalse(r.faz_acusacao(r.jogadores.get(3), env_aux[0], env_aux[1], aux));
		
		// verifica se jogadores que fizeram acusacao errada, forma eliminados
		assertTrue(r.jogadores.get(1).getEliminado() && r.jogadores.get(2).getEliminado() && r.jogadores.get(3).getEliminado());
	}
	
	@Test
	public void testFaz_palpite() 
	{
		AuxRegras r = new AuxRegras(4);
		
		for (int i = 1; i < 4; i++)
		{
			r.add_jogador(suspeitos[i]);
		}
		
		r.distribui("Coronel Mustard", "Corda", "Sala de Estar");
		
		String env_aux[] = {"Coronel Mustard", "Corda", "Sala de Estar"};
	
		String []res = new String[4];
		
		// palpite certeiro, todos estao no envelope
		res[0] = r.faz_palpite(r.jogadores.get(0), env_aux[0], env_aux[1], env_aux[2]);
		
		// palpite incorreto de suspeito
		res[1] = r.faz_palpite(r.jogadores.get(1), "Professor Plum", env_aux[1], env_aux[2]);
		
		// palpite incorreto de arma
		res[2] = r.faz_palpite(r.jogadores.get(2), env_aux[0], "Faca", env_aux[2]);
		
		// palpite incorreto de comodo
		res[3] = r.faz_palpite(r.jogadores.get(3), env_aux[0], env_aux[1], "Garagem");
		
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