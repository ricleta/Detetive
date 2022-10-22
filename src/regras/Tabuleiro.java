package regras;

public class Tabuleiro 
{
	// imagem do tabuleiro 25 * 24 quadrados, alguns nao acessiveis
	  Cell [][] tab;
	  private final int altura = 26;
	  private final int largura = 26;
	  
	  Tabuleiro()
	  {
	    tab = new Cell[altura][largura];
	  }
	  
	  void set_area_inacessivel(int x_ini, int y_ini, int x_fim, int y_fim)
	  {
		  for(int i=x_ini; i <= x_fim; i++)
		  {
				for(int j=y_ini; j <= y_fim; j++)
				{
					tab[i][j] = new Cell(i, j, null, 1);
				}
			}
	  }
	  
	  void set_area_comodo(int x_ini, int y_ini, int x_fim, int y_fim, String comodo)
	  {
		  for(int i=x_ini; i <= x_fim; i++)
		  {
				for(int j=y_ini; j <= y_fim; j++)
				{
					tab[i][j] = new Cell(i, j, comodo, 1);
				}
			}
	  }
	  
	  void set_area_transitavel(int x_ini, int y_ini, int x_fim, int y_fim)
	  {
		  for(int i=x_ini; i <= x_fim; i++)
		  {
				for(int j=y_ini; j <= y_fim; j++)
				{
					tab[i][j] = new Cell(i, j, null, 0);
				}
			}
	  }
}
