package regras;

class Cell 
{
  private int x;
  private int y;
 
  // 0 -> disponivel para se mover
  // 1 -> fora do mapa ou dentro de comodo
  // 2 -> porta de comodo
  // 3 -> ocupada por player, temporariamente indisponivel
  private int tipo;
  private String comodo; // se celula em comodo, especifica qual
  private String jogador; // se celula ocupada, especifica qual jogador ocupa (atraves do personagem)
  String comodo_passagem; // se serve como passagem secreta, informa para qual comodo leva

  Cell(int x, int y, String comodo, int tipo)
  {
    this.x = x;
    this.y = y;
    this.tipo = tipo;
    this.comodo = comodo;

    if(comodo.equals("Cozinha"))
    {
      comodo_passagem = "Escritorio";
    }
    else if(comodo.equals("Escritorio"))
    {
      comodo_passagem = "Cozinha";
    }
    else if(comodo.equals("Jardim de Inverno"))
    {
      comodo_passagem = "Sala de Estar";
    }
    else if(comodo.equals("Sala de Estar"))
    {
      comodo_passagem = "Jardim de Inverno";
    }
    else
    {
      comodo_passagem = null; 
    }
  }

  int get_X(){
    return x;
  }
  
  int get_Y(){
    return y;
  }
  
  int get_estado(){
    return tipo;
  }

  String get_comodo(){
    return comodo;
  }

  String get_jogador(){
    return jogador;
  }

  String get_passagem(){
    return comodo_passagem;
  }

  void set_estado(int estado)
  {
    tipo = estado;
  }

  void set_jogador(String jogador)
  {
    this.jogador = jogador;
  }
  
  
}