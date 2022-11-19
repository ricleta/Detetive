package regras;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;

public class JogadorTest {

	@Test
	public void testJogador() {
		Jogador a = new Jogador("Coronel Mustard");
		assertEquals(0, a.getX());
		assertEquals(0, a.getY());
		assertEquals("Coronel Mustard", a.getPersonagem());			
	}

	@Test
	public void testGetX() 
	{
		Jogador a = new Jogador("Coronel Mustard");
		assertEquals(1, a.getX());
	}

	@Test
	public void testGetY() {
		Jogador a = new Jogador("Coronel Mustard");
		assertEquals(2, a.getY());
	}

	@Test
	public void testGetPersonagem() {
		Jogador a = new Jogador("Coronel Mustard");
		assertEquals("Coronel Mustard", a.getPersonagem());
	}
	
	@Test
	public void testGetEliminado() {
		Jogador a = new Jogador("Coronel Mustard");
		assertEquals(false, a.getEliminado());
	}

	@Test
	public void testSetEliminado() {
		Jogador a = new Jogador("Coronel Mustard");
		
		a.setEliminado();
		
		assertEquals(true, a.getEliminado());
	}
	
	@Test
	public void testSetX() {
		Jogador a = new Jogador("Coronel Mustard");
		
		a.setX(0);
		
		assertEquals(0, a.getX());
	}

	@Test
	public void testSetY() {
		Jogador a = new Jogador("Coronel Mustard");
		
		a.setY(0);
		
		assertEquals(0, a.getY());		
	}
	
	@Test
	public void testAddCarta() {
		Jogador a = new Jogador("Coronel Mustard");
		
		a.add_carta("Faca");
		
		ArrayList <String> lista = new ArrayList<String>();
		
		lista.add("Faca");
		
		assertEquals(lista, a.get_cartas());
	}
}
