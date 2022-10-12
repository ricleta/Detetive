package regras;

class Jogador
{
  private int x;
  private int y;
  private String player;

  Jogador(int x, int y, String player) 
  {
    this.x = x;
    this.y = y;
    this.player = player;
  }

  int getX()
  {
    return x;
  }

  int getY()
  {
    return y;
  }

  String getPersonagem()
  {
    return player;
  }

  void setX(int x)
  {
    this.x = x;
  }

  void setY(int y)
  {
    this.y = y;
  }
}