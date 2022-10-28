package regras;

import java.util.ArrayList;

class Movimento 
{
	Cell [][] tab;
	int altura;
	int largura;
	
	public Movimento(Cell[][] tab) 
	{
		this.tab = tab;
		
		altura = tab.length;
		largura = tab[0].length;
	}

	ArrayList<Cell> encontra_mov(ArrayList <Cell> origens, int n_mov) 
	{
		
		System.out.println("n_mov = " + n_mov);
		
		if (n_mov == 0)
		{
			return origens;
		}
		
		for (int i = 0; i < origens.size(); i++)
		{
			Cell atual = origens.get(i);
			
			int x_atual = atual.get_X();
			int y_atual = atual.get_Y();
	
			System.out.println(x_atual + "," + y_atual);
			
			int x_dir = atual.get_X() + 1;
			int x_esq = atual.get_X() - 1;
	
			int y_cima = atual.get_Y() - 1;
			int y_baixo = atual.get_Y() + 1;
	
			// checa celula acima
			if (y_atual > 0 && tab[y_cima][x_atual].get_estado() == 0) 
			{			
				if (!origens.contains(tab[y_cima][x_atual]))
				{
					origens.add(tab[y_cima][x_atual]);
				}
				
//				System.out.printf("\nx = %d, y = %d\n", x_atual, y_cima);
			}
	
			// checa celula abaixo
			if (y_atual < altura && tab[y_baixo][x_atual].get_estado() == 0) 
			{
				if (!origens.contains(tab[y_baixo][x_atual]))
				{
					origens.add(tab[y_baixo][x_atual]);
				}
				
//				System.out.printf("\nx = %d, y = %d\n", x_atual, y_baixo);		
			}
	
			// checa celula da direita
			if (x_atual < largura && tab[y_atual][x_dir].get_estado() == 0) 
			{
//				System.out.printf("\nx = %d, y = %d\n", x_dir, y_atual);
				
				if (!origens.contains(tab[y_atual][x_dir]))
				{
					origens.add(tab[y_atual][x_dir]);
				}
				
			}
	
			// checa celula da esquerda
			if (x_atual > 0 && tab[y_atual][x_esq].get_estado() == 0) 
			{
//			   System.out.printf("\nx = %d, y = %d\n", x_esq, y_atual);
			   
			   if (!origens.contains(tab[y_atual][x_esq]))
				{
					origens.add(tab[y_atual][x_esq]);
				}
				
			}
		}
		
		return encontra_mov(origens, n_mov -1);
	}
}
