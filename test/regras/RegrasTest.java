package regras;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class RegrasTest {
	
	private String suspeitos[] = {"Srta. Scarlet", "Coronel Mustard", "Professor Plum", "Reverendo Green", "Sra. White", "Sra. Peacock"};

	private String armas[] = {"Corda", "Cano de Chumbo", "Faca", "Chave Inglesa", "Castical", "Revolver"}; 

	private String comodos[] = {"Biblioteca", "Cozinha", "Entrada", "Escritorio", "Jardim de Inverno", "Sala de Estar", "Sala de Jantar", "Sala de Musica", "Salao de Jogos"};

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
}