package regras;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class RegrasTest {
	
	private String suspeitos[] = {"Srta. Scarlet", "Coronel Mustard", "Professor Plum", "Reverendo Green", "Sra. White", "Sra. Peacock"};

	private String armas[] = {"Corda", "Cano de Chumbo", "Faca", "Chave Inglesa", "Castical", "Revolver"}; 

	private String comodos[] = {"Banheiro", "Escritorio", "Sala de Estar", "Sala de jogos", "Garagem", "Quarto", "Sala de Estudos", "Cozinha", "Patio"};

	ArrayList<String> l_suspeitos = new ArrayList<String>(Arrays.asList(suspeitos));
	ArrayList<String> l_armas = new ArrayList<String>(Arrays.asList(armas));
	ArrayList<String> l_comodos = new ArrayList<String>(Arrays.asList(comodos));
	
	@Test
	void testRegras() {
		Regras r = new Regras(3);
		
		for (int i = 1; i < 3; i++)
		{
			r.add_jogador(suspeitos[i]);
		}
		
		assertEquals(3, r.jogadores.size());
	}

	@Test
	void testRoll_die() {
		Regras r = new Regras(3);
		
		int dado = r.roll_die();
		
		assertTrue(dado > 0 && dado < 7);
	}

	@Test
	void testSet_envelope() {
		Regras r = new Regras(3);
			
		r.set_envelope();
		
		assertTrue(l_suspeitos.contains(r.get_envelope()[0]));
		assertTrue(l_armas.contains(r.get_envelope()[1]));
		assertTrue(l_comodos.contains(r.get_envelope()[2]));
	}

	@Test
	void testEmbaralhador() {
		Regras r = new Regras(3);
		
		r.set_envelope();
		r.embaralhador();
		
		ArrayList<String> cartas = r.get_cartas();
		
		assertTrue(!cartas.contains(r.get_envelope()[0]));
		assertTrue(!cartas.contains(r.get_envelope()[1]));
		assertTrue(!cartas.contains(r.get_envelope()[2]));
		assertEquals(cartas.size(), 18);
	}

	@Test
	void testDistribui() {
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

	@Test
	void testVerifica_acusacao() 
	{
		fail("Not yet implemented");
	}

}
