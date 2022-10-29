package controller;

import regras.*;

import gui.*;

public class Main {

	public static void main(String[] args) 
	{
//		TelaInicial ti = new TelaInicial();
		
//		TelaJogo tj = new TelaJogo();
		
		Tabuleiro tab = new Tabuleiro();
			
		tab.print();
		
		Cell cell = new Cell(2, 7, null, 0);
		
		for (Cell a: tab.encontra_movimentos(cell, 10))
		{
			System.out.println(a.get_X() + ", " + a.get_Y());	
		}
	}
}