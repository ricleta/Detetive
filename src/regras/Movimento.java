package regras;

import java.util.ArrayList;

class Movimento {
	Cell[][] tab;
	int altura;
	int largura;
	ArrayList<Cell> visitados = new ArrayList<Cell>();

	Movimento(Cell[][] tab) {
		this.tab = tab;

		altura = tab.length;
		largura = tab[0].length;
	}

	ArrayList<Cell> encontra_mov(ArrayList<Cell> origens, int n_mov) {
//		System.out.println("n_mov = " + n_mov);

		if (n_mov == 0) {
			return origens;
		}

		ArrayList<Cell> aux = new ArrayList<Cell>();
		ArrayList<Cell> aux2 = new ArrayList<Cell>(origens);

		visitados.addAll(origens);
		origens.clear();

		for (int i = 0; i < aux2.size(); i++) {
			Cell atual = aux2.get(i);

			int x_atual = atual.get_X();
			int y_atual = atual.get_Y();

//			System.out.println("atual = " + x_atual + "," + y_atual);

			aux.addAll(verifica_tipo_0(x_atual, y_atual));
			aux.addAll(verifica_tipo_2(x_atual, y_atual));
		}

		for (Cell c : aux) {
			if (!origens.contains(c) && !visitados.contains(c)) {
				origens.add(c);
			}
		}

		return encontra_mov(origens, n_mov - 1);
	}

	ArrayList<Cell> verifica_tipo_0(int x_atual, int y_atual) {
		int x_dir = x_atual + 1;
		int x_esq = x_atual - 1;

		int y_cima = y_atual - 1;
		int y_baixo = y_atual + 1;

		ArrayList<Cell> resultado = new ArrayList<Cell>();

		// checa celula acima
		if (y_atual > 0 && tab[y_cima][x_atual].get_estado() == 0) {
			if (!resultado.contains(tab[y_cima][x_atual])) {
				resultado.add(tab[y_cima][x_atual]);
			}

//			System.out.printf("Cima: y = %d, x = %d\n\n", y_cima, x_atual);
		}

		// checa celula abaixo
		if (y_atual < altura - 1 && tab[y_baixo][x_atual].get_estado() == 0) {
			if (!resultado.contains(tab[y_baixo][x_atual])) {
				resultado.add(tab[y_baixo][x_atual]);
			}

//			System.out.printf("Baixo: y = %d, x = %d\n\n", y_baixo, x_atual);		
		}

		// checa celula da direita
		if (x_atual < largura - 1 && tab[y_atual][x_dir].get_estado() == 0) {
//			System.out.printf("Dir: y = %d, x = %d\n\n", y_atual, x_dir);

			if (!resultado.contains(tab[y_atual][x_dir])) {
				resultado.add(tab[y_atual][x_dir]);
			}
		}

		// checa celula da esquerda
		if (x_atual > 0 && tab[y_atual][x_esq].get_estado() == 0) {
//		   System.out.printf("Esq: y = %d, x = %d\n\n", y_atual, x_esq);

			if (!resultado.contains(tab[y_atual][x_esq])) {
				resultado.add(tab[y_atual][x_esq]);
			}
		}

		return resultado;
	}

	ArrayList<Cell> verifica_tipo_2(int x_atual, int y_atual) {
		int x_dir = x_atual + 1;
		int x_esq = x_atual - 1;

		int y_cima = y_atual - 1;
		int y_baixo = y_atual + 1;

		ArrayList<Cell> resultado = new ArrayList<Cell>();

		// checa celula acima
		if (y_atual > 0 && tab[y_cima][x_atual].get_estado() == 2) {
			if (!resultado.contains(tab[y_cima][x_atual])) {
				resultado.add(tab[y_cima][x_atual]);
			}

//			System.out.printf("Cima: y = %d, x = %d\n\n", y_cima, x_atual);
		}

		// checa celula abaixo
		if (y_atual < altura - 1 && tab[y_baixo][x_atual].get_estado() == 2) {
			if (!resultado.contains(tab[y_baixo][x_atual])) {
				resultado.add(tab[y_baixo][x_atual]);
			}

//			System.out.printf("Baixo: y = %d, x = %d\n\n", y_baixo, x_atual);		
		}

		// checa celula da direita
		if (x_atual < largura - 1 && tab[y_atual][x_dir].get_estado() == 2) {
//			System.out.printf("Dir: y = %d, x = %d\n\n", y_atual, x_dir);

			if (!resultado.contains(tab[y_atual][x_dir])) {
				resultado.add(tab[y_atual][x_dir]);
			}
		}

		// checa celula da esquerda
		if (x_atual > 0 && tab[y_atual][x_esq].get_estado() == 2) {
//		   System.out.printf("Esq: y = %d, x = %d\n\n", y_atual, x_esq);

			if (!resultado.contains(tab[y_atual][x_esq])) {
				resultado.add(tab[y_atual][x_esq]);
			}
		}

		return resultado;
	}

	int[] entra_comodo(Cell atual, String personagem) {
		String comodo = atual.get_comodo();
		int baixo = 0; 
		int direita = 0; 
		int x = 0;
		int y = 0;
		int[] coordenadas_dentro_comodo = new int[2];

		switch (personagem) {
		case "Srta. Scarlet":
			baixo = 0;
			direita = 0;
			break;
		case "Coronel Mustard":
			baixo = 0;
			direita = 1;
			break;
		case "Professor Plum":
			baixo = 0;
			direita = 2;
			break;
		case "Reverendo Green":
			baixo = 1;
			direita = 0;
			break;
		case "Sra. White":
			baixo = 1;
			direita = 1;
			break;
		case "Sra. Peacock":
			baixo = 1;
			direita = 2;
			break;
		default:
			return null;
		}

		switch (comodo) {
		case "Biblioteca":
			x = 19;
			y = 15;
			break;

		case "Cozinha":
			x = 2;
			y = 2;
			break;

		case "Entrada":
			x = 10;
			y = 20;
			break;

		case "Escritorio":
			x = 19;
			y = 22;
			break;

		case "Jardim de Inverno":
			x = 19;
			y = 2;
			break;

		case "Sala de Estar":
			x = 1;
			y = 21;
			break;

		case "Sala de Jantar":
			x = 2;
			y = 12;
			break;

		case "Sala de Musica":
			x = 10;
			y = 4;
			break;

		case "Salao de Jogos":
			x = 20;
			y = 10;
			break;
			
		default:
			return null;
		}

		x += direita;
		y += baixo;

		coordenadas_dentro_comodo[0] = x;
		coordenadas_dentro_comodo[1] = y;

		return coordenadas_dentro_comodo;
	}
}