package regras;

class Tabuleiro 
{
  // imagem do tabuleiro 25 * 24 quadrados, alguns nao acessiveis
  Cell[][] tab;
  private final int altura = 25;
  private final int largura = 24;
  
  public Tabuleiro() 
  {
    tab = new Cell[altura][largura];

    /* Separando áreas dos comodos */
    this.set_area_comodo(0, 0, 5, 5, "Cozinha");
    this.set_area_comodo(1, 6, 5, 6, "Cozinha");
    tab[6][4].set_estado(2);

    this.set_area_comodo(0, 9, 4, 9, "Sala de Jantar");
    this.set_area_comodo(0, 10, 7, 15, "Sala de Jantar");
    tab[12][7].set_estado(2);
    tab[15][6].set_estado(2);

    this.set_area_comodo(0, 19, 6, 23, "Sala de Estar");
    this.set_area_comodo(0, 24, 5, 24, "Sala de Estar");
    tab[19][6].set_estado(2);

    this.set_area_comodo(10, 1, 13, 1, "Sala de Musica");
    this.set_area_comodo(8, 2, 15, 7, "Sala de Musica");
    tab[5][8].set_estado(2);
    tab[7][9].set_estado(2);
    tab[5][15].set_estado(2);
    tab[7][14].set_estado(2);

    this.set_area_comodo(9, 18, 14, 23, "Entrada");
    this.set_area_comodo(10, 24, 13, 24, "Entrada");
    tab[18][11].set_estado(2);
    tab[18][12].set_estado(2);
    tab[20][14].set_estado(2);

    this.set_area_comodo(18, 1, 23, 4, "Jardim de Inverno");
    this.set_area_comodo(19, 5, 22, 5, "Jardim de Inverno");
    tab[5][19].set_estado(2);

    this.set_area_comodo(18, 8, 23, 12, "Salao de Jogos");
    tab[9][18].set_estado(2);
    tab[12][22].set_estado(2);

    this.set_area_comodo(18, 14, 22, 14, "Biblioteca");
    this.set_area_comodo(17, 15, 23, 17, "Biblioteca");
    this.set_area_comodo(18, 18, 22, 18, "Biblioteca");
    tab[16][17].set_estado(2);
    tab[14][20].set_estado(2);

    this.set_area_comodo(17, 21, 23, 23, "Escritorio");
    this.set_area_comodo(18, 24, 23, 24, "Escritorio");
    tab[21][17].set_estado(2);

    /* separando áreas inacessíveis */
    set_area_inacessivel(10, 10, 14, 16); // area central do mapa
    set_area_inacessivel(0, 6, 0, 6);
    set_area_inacessivel(0, 8, 0, 8);
    set_area_inacessivel(14, 24, 15, 24);
    set_area_inacessivel(17, 24, 17, 24);
    set_area_inacessivel(23, 13, 23, 14);
    set_area_inacessivel(6, 0, 8, 0);
    set_area_inacessivel(6, 1, 6, 1);
    set_area_inacessivel(10, 0, 13, 0);
    set_area_inacessivel(15, 0, 23, 0);
    set_area_inacessivel(17, 1, 17, 1);

    // entrada coronel mustard
    set_area_inacessivel(0, 16, 0, 16);
    set_area_inacessivel(0, 18, 0, 18);

    // entrada professor plum
    set_area_inacessivel(23, 18, 23, 18);
    set_area_inacessivel(23, 20, 23, 20);

    // entrada Peacock
    set_area_inacessivel(23, 5, 23, 5);
    set_area_inacessivel(23, 7, 23, 7);

    // entrada Srta Scarlet
    set_area_inacessivel(6, 24, 6, 24);
    set_area_inacessivel(8, 24, 9, 24);

    /* separando áreas dos corredores */
    set_transitavel();
  }

  void set_area_inacessivel(int x_ini, int y_ini, int x_fim, int y_fim)
  {
    for (int i = y_ini; i <= y_fim; i++) 
    {
      for (int j = x_ini; j <= x_fim; j++) 
      {
        tab[i][j] = new Cell(j, i, null, 1);
      }
    }
  }

  void set_area_comodo(int x_ini, int y_ini, int x_fim, int y_fim, String comodo) 
  {
    for (int i = y_ini; i <= y_fim; i++) 
    {
      for (int j = x_ini; j <= x_fim; j++) 
      {
        tab[i][j] = new Cell(j, i, comodo, 1);
      }
    }
  }

  // chamar isso aqui dps de settar areas n transitaveis e settar comodos -> areas
  // q n tiverem alocadas vao ser os corredores/demais areas q pode passar ->
  // menos trabalhoso do q chamar o metodo de cima 400 vzs
  void set_transitavel() 
  {
    for (int i = 0; i < altura; i++) 
    {
      for (int j = 0; j < largura; j++) 
      {
        if (tab[i][j] == null) 
        {
          tab[i][j] = new Cell(j, i, null, 0);
        }
      }
    }
  }
  
  // retirar dps
  void print() 
  {
    for (int i = 0; i < altura; i++) 
    {
      for (int j = 0; j < largura; j++) 
      {
        tab[i][j].print();
      }

      System.out.print("\n");
    }
  }
}
