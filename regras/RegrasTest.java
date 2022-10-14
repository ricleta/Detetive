package regras;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class RegrasTest {

	@Test
	void testRegras() {
		Regras r = new Regras(3);
		
		assertEquals(3, r.jogadores.length);
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
		String suspeitos[] = {"Srta. Scarlet", "Coronel Mustard", "Professor Plum", "Reverendo Green", "Sra. White", "Sra. Peacock"};

		String armas[] = {"Corda", "Cano de Chumbo", "Faca", "Chave Inglesa", "Castiçal", "Revólver"}; 

		String comodos[] = {"Banheiro", "Escritório", "Sala de Estar", "Sala de jogos", "Garagem", "Quarto", "Sala de Estar", "Cozinha", "Pátio"};
		
		r.set_envelope();
		
		ArrayList<String> l_suspeitos = new ArrayList<String>(Arrays.asList(suspeitos));
		ArrayList<String> l_armas = new ArrayList<String>(Arrays.asList(armas));
		ArrayList<String> l_comodos = new ArrayList<String>(Arrays.asList(comodos));
		
		assertTrue(l_suspeitos.contains(r.get_envelope()[0]));
		assertTrue(l_armas.contains(r.get_envelope()[1]));
		assertTrue(l_comodos.contains(r.get_envelope()[2]));
	}

	@Test
	void testEmbaralhador() {
		Regras r = new Regras(3);
		
		r.set_envelope();
		
		ArrayList<String> cartas = r.get_cartas();
		
		assertTrue(!cartas.contains(r.get_envelope()[0]));
		assertTrue(!cartas.contains(r.get_envelope()[1]));
		assertTrue(!cartas.contains(r.get_envelope()[2]));
	}

	@Test
	void testDistribui() {
		// Mudar jogadores para arraylist e criar funcao para adicionar jogadores
		Regras r3 = new Regras(3);
		Regras r4 = new Regras(4);
		Regras r5 = new Regras(5);
		Regras r6 = new Regras(6);
		
		r3.distribui();
		r4.distribui();
		r5.distribui();
		r6.distribui();
		
		for (Jogador j: r3.jogadores)
		{
			assertEquals(6, j.get_cartas().size());
		}
		
		for (int i = 0; i < r4.jogadores.length; i++)
		{
			if (i < 2)
			{
				assertEquals(5, r4.jogadores[i].get_cartas().size());
			}
			else
			{
				assertEquals(4, r4.jogadores[i].get_cartas().size());
			}
		}
		
		for (int i = 0; i < r5.jogadores.length; i++)
		{
			if (i < 3)
			{
				assertEquals(4, r5.jogadores[i].get_cartas().size());
			}
			else
			{
				assertEquals(3, r5.jogadores[i].get_cartas().size());
			}
		}
		
		
		for (Jogador j: r6.jogadores)
		{
			assertEquals(3, j.get_cartas().size());
		}
		
	}

	@Test
	void testVerifica_acusacao() {
		fail("Not yet implemented");
	}

}
