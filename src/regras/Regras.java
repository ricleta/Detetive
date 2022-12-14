package regras;

import java.util.*;

class Regras {
	/**** atributos ****/
	String suspeitos[] = { "Srta. Scarlet", "Coronel Mustard", "Professor Plum", "Reverendo Green", "Sra. White",
			"Sra. Peacock" };

	String armas[] = { "Corda", "Cano de Chumbo", "Faca", "Chave Inglesa", "Castical", "Revolver" };

	String comodos[] = { "Biblioteca", "Cozinha", "Entrada", "Escritorio", "Jardim de Inverno", "Sala de Estar",
			"Sala de Jantar", "Sala de Musica", "Salao de Jogos" };

	// 0 -> suspeito, 1 -> arma, 2-> comodo
	protected String envelope[] = new String[3];

	// array de cartas que não estão no envelope
	// tam = 5 + 5 + 8 = 18, nao considera envelope
	protected ArrayList<String> cartas = new ArrayList<String>();
	ArrayList<Jogador> jogadores;

	/**** construtor e métodos ****/
	Regras(int num_jogadores, ArrayList<String> personagens) {
		jogadores = new ArrayList<Jogador>();

		for (String s : personagens) {
			jogadores.add(new Jogador(s));
		}
	}

	// mudar teste
	String[] getNomeJogadores() {
		String[] nomes = new String[jogadores.size()];
		for (int i = 0; i < jogadores.size(); i++) {
			nomes[i] = jogadores.get(i).getPersonagem();
		}

		return nomes;
	}

	// criar teste
	boolean[] getStatusJogadores() {
		boolean[] estados = new boolean[jogadores.size()];
		for (int i = 0; i < jogadores.size(); i++) {
			estados[i] = jogadores.get(i).getEliminado();
		}

		return estados;
	}

	void set_jogador_eliminado(String personagem) {
		for (Jogador j : jogadores) {
			if (j.getPersonagem().equals(personagem)) {
				j.setEliminado();
			}
		}
	}

	/* 3 cartas, 1 de cada tipo selecionadas randomicamente para o envelope */
	void set_envelope() {
		Random r = new Random();

		envelope[0] = suspeitos[r.nextInt(0, 6)];
		envelope[1] = armas[r.nextInt(0, 6)];
		envelope[2] = comodos[r.nextInt(0, 9)];
		
		System.out.println("Envelope = " + envelope[0] + " " + envelope[1] + " " + envelope[2]);
	}

	// usado para testar set_envelope, por envelope ser private
	public String[] get_envelope() {
		return envelope;
	}

	/* método para embarlhar as cartas sem incluir as cartas do envelope */
	void embaralhador() {
		int i = 0;

		for (i = 0; i < 6; i++) {
			if (!(suspeitos[i].equals(envelope[0]))) {
				cartas.add(suspeitos[i]);
			}

			if (!(armas[i].equals(envelope[1]))) {
				cartas.add(armas[i]);
			}
		}

		for (i = 0; i < 9; i++) {
			if (!(comodos[i].equals(envelope[2]))) {
				cartas.add(comodos[i]);
			}
		}

		Collections.shuffle(cartas);
	}

	// usado para testar embaralhador, por cartas ser private
	ArrayList<String> get_cartas() {
		return cartas;
	}

	/* método para distribuir as 18 cartas entre N jogadores */
	public void distribui() {
		this.set_envelope();
		this.embaralhador();

		int sobra = cartas.size() % jogadores.size();
		int quociente = (cartas.size() - sobra) / jogadores.size();

		if (sobra == 0) {
			for (Jogador jogador : jogadores) {
				for (int j = 0; j < quociente; j++) {
					jogador.add_carta(cartas.get(0));
					cartas.remove(0);
				}
			}
		} else {
			for (Jogador jogador : jogadores) {
				for (int j = 0; j < quociente; j++) {
					jogador.add_carta(cartas.get(0));
					cartas.remove(0);
				}

				if (sobra > 0) {
					jogador.add_carta(cartas.get(0));
					cartas.remove(0);
					sobra--;
				}
			}
		}
	}

	/*
	 * verifica se a acusação feita pelo jogador está correta comparando as cartas
	 * do envelope com as informações da acusação
	 */
	boolean verifica_acusacao(String suspeito, String arma, String comodo) {
		if (envelope[0].equals(suspeito)) {
			if (envelope[1].equals(arma)) {
				if (envelope[2].equals(comodo)) {
					return true;
				}
			}
		}

		return false;
	}

	Jogador encontra_jogador(String personagem) {
		for (Jogador j : jogadores) {
			if (j.getPersonagem().equals(personagem)) {
				return j;
			}
		}

		return null;
	}

	/*
	 * verifica se alguma das cartas do palpite está na mão do jogador. Se estiver,
	 * o palpite está errado
	 */
	String verifica_palpite(String persona_prox_jog, String suspeito, String arma, String comodo) {

		Jogador prox_jog = encontra_jogador(persona_prox_jog);

		for (String carta : prox_jog.get_cartas()) 
		{
			if (carta.equals(suspeito)) 
			{
				return suspeito;
			}

			if (carta.equals(arma)) 
			{
				return arma;
			}

			if (carta.equals(comodo)) 
			{
				return comodo;
			}
		}

		return null;
	}

	/* retorna o índice do jogador na lista */
	int get_index_jog(String personagem) {
		for (Jogador j : jogadores) {
			if (j.getPersonagem().equals(personagem)) {
				return jogadores.indexOf(j);
			}
		}

		return -1;
	}
}
