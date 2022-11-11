package regras;

import java.util.ArrayList;

class Movimento 
{
	Cell [][] tab;
	int altura;
	int largura;
	ArrayList <Cell> visitados = new ArrayList <Cell>();
	
	Movimento(Cell[][] tab) 
	{
		this.tab = tab;
		
		altura = tab.length;
		largura = tab[0].length;
	}

	ArrayList<Cell> encontra_mov(ArrayList <Cell> origens, int n_mov) 
	{
//		System.out.println("n_mov = " + n_mov);
		
		if (n_mov == 0)
		{	
			return origens;
		}
	
		ArrayList <Cell> aux = new ArrayList <Cell>();
		ArrayList <Cell> aux2 = new ArrayList <Cell>(origens);
		
		visitados.addAll(origens);
		origens.clear();
		
		for (int i = 0; i < aux2.size(); i++)
		{
			Cell atual = aux2.get(i);
			
			int x_atual = atual.get_X();
			int y_atual = atual.get_Y();
	
//			System.out.println("atual = " + x_atual + "," + y_atual);
			
			aux.addAll(verifica_tipo_0(x_atual, y_atual));
			aux.addAll(verifica_tipo_2(x_atual, y_atual));
		}
		
		for (Cell c: aux)
		{
			if(!origens.contains(c) && !visitados.contains(c))
			{
				origens.add(c);	
			}
		}
		
		
		return encontra_mov(origens, n_mov -1);
	}
	
	ArrayList <Cell> verifica_tipo_0(int x_atual, int y_atual)
	{
		int x_dir = x_atual + 1;
		int x_esq = x_atual - 1;

		int y_cima = y_atual - 1;
		int y_baixo = y_atual + 1;

		ArrayList <Cell> resultado = new ArrayList <Cell>();
		
		// checa celula acima
		if (y_atual > 0 && tab[y_cima][x_atual].get_estado() == 0) 
		{			
			if (!resultado.contains(tab[y_cima][x_atual]))
			{
				resultado.add(tab[y_cima][x_atual]);
			}
			
//			System.out.printf("Cima: y = %d, x = %d\n\n", y_cima, x_atual);
		}

		// checa celula abaixo
		if (y_atual < altura - 1 && tab[y_baixo][x_atual].get_estado() == 0) 
		{
			if (!resultado.contains(tab[y_baixo][x_atual]))
			{
				resultado.add(tab[y_baixo][x_atual]);
			}
			
//			System.out.printf("Baixo: y = %d, x = %d\n\n", y_baixo, x_atual);		
		}

		// checa celula da direita
		if (x_atual < largura - 1 && tab[y_atual][x_dir].get_estado() == 0) 
		{
//			System.out.printf("Dir: y = %d, x = %d\n\n", y_atual, x_dir);
			
			if (!resultado.contains(tab[y_atual][x_dir]))
			{
				resultado.add(tab[y_atual][x_dir]);
			}
		}

		// checa celula da esquerda
		if (x_atual > 0 && tab[y_atual][x_esq].get_estado() == 0) 
		{
//		   System.out.printf("Esq: y = %d, x = %d\n\n", y_atual, x_esq);
		   
		  if (!resultado.contains(tab[y_atual][x_esq]))
			{
				resultado.add(tab[y_atual][x_esq]);
			}
		}
		
		return resultado;
	}
	
	ArrayList <Cell> verifica_tipo_2(int x_atual, int y_atual)
	{
		int x_dir = x_atual + 1;
		int x_esq = x_atual - 1;

		int y_cima = y_atual - 1;
		int y_baixo = y_atual + 1;

		ArrayList <Cell> resultado = new ArrayList <Cell>();
		
		// checa celula acima
		if (y_atual > 0 && tab[y_cima][x_atual].get_estado() == 2) 
		{			
			if (!resultado.contains(tab[y_cima][x_atual]))
			{
				resultado.add(tab[y_cima][x_atual]);
			}
			
//			System.out.printf("Cima: y = %d, x = %d\n\n", y_cima, x_atual);
		}

		// checa celula abaixo
		if (y_atual < altura - 1 && tab[y_baixo][x_atual].get_estado() == 2) 
		{
			if (!resultado.contains(tab[y_baixo][x_atual]))
			{
				resultado.add(tab[y_baixo][x_atual]);
			}
			
//			System.out.printf("Baixo: y = %d, x = %d\n\n", y_baixo, x_atual);		
		}

		// checa celula da direita
		if (x_atual < largura - 1 && tab[y_atual][x_dir].get_estado() == 2) 
		{
//			System.out.printf("Dir: y = %d, x = %d\n\n", y_atual, x_dir);
			
			if (!resultado.contains(tab[y_atual][x_dir]))
			{
				resultado.add(tab[y_atual][x_dir]);
			}
		}

		// checa celula da esquerda
		if (x_atual > 0 && tab[y_atual][x_esq].get_estado() == 2) 
		{
//		   System.out.printf("Esq: y = %d, x = %d\n\n", y_atual, x_esq);
		   
		  if (!resultado.contains(tab[y_atual][x_esq]))
			{
				resultado.add(tab[y_atual][x_esq]);
			}
		}
		
		return resultado;
	}
}