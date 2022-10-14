package regras;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class JogadorTest {

	@Test
	void testJogador() {
		Jogador a = new Jogador(0 , 0, "Coronel Mustard");
		assertEquals(0, a.getX());
		assertEquals(0, a.getY());
		assertEquals("Coronel Mustard", a.getPersonagem());			
	}

	@Test
	void testGetX() 
	{
		Jogador a = new Jogador(1 , 2, "Coronel Mustard");
		assertEquals(1, a.getX());
	}

	@Test
	void testGetY() {
		Jogador a = new Jogador(1 , 2, "Coronel Mustard");
		assertEquals(2, a.getY());
	}

	@Test
	void testGetPersonagem() {
		Jogador a = new Jogador(1 , 2, "Coronel Mustard");
		assertEquals("Coronel Mustard", a.getPersonagem());
	}
	
	@Test
	void testGetEliminado() {
		Jogador a = new Jogador(1 , 2, "Coronel Mustard");
		assertEquals(false, a.getEliminado());
	}

	@Test
	void testSetEliminado() {
		Jogador a = new Jogador(1 , 2, "Coronel Mustard");
		
		a.setEliminado();
		
		assertEquals(true, a.getEliminado());
	}
	
	@Test
	void testSetX() {
		Jogador a = new Jogador(1 , 2, "Coronel Mustard");
		
		a.setX(0);
		
		assertEquals(0, a.getX());
	}

	@Test
	void testSetY() {
		Jogador a = new Jogador(1 , 2, "Coronel Mustard");
		
		a.setY(0);
		
		assertEquals(0, a.getY());		
	}
	
	@Test
	void testAddCarta() {
		Jogador a = new Jogador(1 , 2, "Coronel Mustard");
		
		a.add_carta("Faca");
		
		ArrayList <String> lista = new ArrayList<String>();
		
		lista.add("Faca");
		
		assertEquals(lista, a.get_cartas());
	}
}
