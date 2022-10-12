package regras;

import java.util.Random;

class Regras
{
  String personagem[] = {"Coronel_Mustard", "Srta. Scarlet", "Professor Plum", "Reverendo Green", "Sra. White", "Sra. Peacock"};

  static int roll_die(){
    Random random = new Random();
    
    return random.nextInt(1,7);
  }
}