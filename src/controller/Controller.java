package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;

import regras.*;
import gui.*;

public class Controller {
	static CtrlRegras r;
	static String[] j_personagens; // array para conter nome dos personagens de cada jogador
	static boolean[] j_estados; // array para conter a boolean que indica se um jogador foi eliminado ou nao
	static int n_jogadores = 0;
	static int turno = 0;
	static int estado = 0; // controla situacao atual do jogo; 0 -> tela inicial, 1 -> tela de selecao de
							// personagens, 2-> tela de jogo

	static TelaPersonagens tela_perso = null;
	static TelaJogo tela_jogo = null;
	static TelaInicial tela_ini = null;

	public static void setConfig(int num_jogadores, ArrayList<String> personagens) {
		update_estado(2);
		r = CtrlRegras.getCtrlRegras(n_jogadores, personagens);
		n_jogadores = num_jogadores;
		turno = 0;

		j_personagens = new String[n_jogadores];
		j_estados = new boolean[n_jogadores];

		// armazenam informacoes dos jogadores marcados na Tela de selecao de
		// personagens
		j_personagens = r.getNomeJogadores();
		j_estados = r.getStatusJogadores();
		r.distribui_cartas();
	}

	// retorna nome do personagem do jogador que deveria jogar nesse turno
	// o tipo de retorno talvez tenha que mudar quando formos implementar a gui
	/* retorna o jogador da vez (ou o aviso de que o jogador foi eliminado) */
	public static String get_jogador_atual() {
		int id_j_atual = turno % n_jogadores;

		// se jogador pode jogar, retorna nome
		// se foi eliminado por uma acusao errada, retorna aviso
		j_estados = r.getStatusJogadores();

		if (j_estados[id_j_atual]) {
			for (int i = 1; i < j_estados.length; i++) {
				int id_prox = (id_j_atual + 1) % n_jogadores;

				if (!j_estados[id_prox]) {
					return j_personagens[id_prox];
				}

			}

			System.exit(0);
		}

		return j_personagens[id_j_atual];
	}

	/* seleciona um número randômico de 1 a 6 para indicar o valor do dado */
	public static int[] roll_dice() {
		Random random = new Random();
		return new int[] { random.nextInt(1, 7), random.nextInt(1, 7) };
	}

	/*
	 * Jogador j faz uma acusação sobre os dados do assassinato. Se a acusação
	 * estiver correta, j vence o jogo, caso contrário é eliminado
	 */
	public static boolean faz_acusacao(String j_personagem, String suspeito, String arma, String comodo) {
		if (r.verifica_acusacao(suspeito, arma, comodo)) {
			// main deve encerrar jogo atual
			return true;
		} else {
			r.set_jogador_eliminado(j_personagem);
			return false;
		}
	}

	/*
	 * Jogador j faz um palpite sobre os dados do assassinato e é verificado na mão
	 * dos demais jogadores se possuem alguma das cartas do palpite
	 */
	public static String faz_palpite(String j_personagem, String suspeito, String arma, String comodo) {
		String resultado = null;
		String j_investigado; // jogador cujas cartas serao analisadas
		int i = 0, k = 0;

		k = r.get_index_jog(j_personagem);

		for (i = k; i < j_personagens.length; i++) {
			j_investigado = j_personagens[i];
			resultado = r.verifica_palpite(j_investigado, suspeito, arma, comodo);

			if (resultado != null) {
				return resultado;
			}
		}

		for (i = 0; i < k; i++) {
			j_investigado = j_personagens[i];
			resultado = r.verifica_palpite(j_investigado, suspeito, arma, comodo);

			if (resultado != null) {
				return resultado;
			}
		}

		return null;
	}

	public static void prox_turno() {
		turno++;

		if (r.pode_usar_passagem()) {
			tela_jogo.habilita_passagem(true);
		} else {
			tela_jogo.habilita_passagem(false);
		}
	}

	public static boolean[][] get_notas_jog_atual() {
		return r.get_notas_jog_atual();
	}

	public static ArrayList<String> get_cartas_jog_atual() {
		return r.get_cartas_jog_atual();
	}

	public static int[][] encontra_movimentos(int x, int y, int n_mov) {
		return r.encontra_movimentos(x, y, n_mov);
	}

	public static void update_estado(int novo) {
		estado = novo;

		switch (estado) {
		case 1:
			tela_perso = new TelaPersonagens();
			break;
		case 2:
			tela_jogo = new TelaJogo();
			break;
		default:
			tela_ini = new TelaInicial();
			break;
		}
	}

	public static void salva_jogo() {
		JFileChooser fileChooser = new JFileChooser();
		FileWriter filewriter = null;
		File file;
		String path;
		
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			try {
				path = file.getPath() + ".txt";
				file = new File(path);
				filewriter = new FileWriter(file.getPath());
				
				filewriter.write(String.format("%d\n", n_jogadores));
				
				for (String s: j_personagens)
				{
					filewriter.write(String.format("%s |", s));
				}
				
				filewriter.write(String.format("\n"));
				
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				try {
					filewriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void le_jogo_salvo() {
		JFileChooser fileChooser = new JFileChooser();
		File file = null;
		String path, data;
		Scanner reader = null;

		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			try {
				path = file.getPath();
				file = new File(path);
				reader = new Scanner(file);

				while (reader.hasNextLine()) {
					data = reader.nextLine();
					System.out.println(data);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				reader.close();
			}
		}
	}

	public static void atualiza_cell_ocupada(int x, int y) {
		r.atualiza_cell_ocupada(x, y);
	}

	public static void registra(ObservadorIF o) {
		tela_jogo.add(o);
	}

	public static void main(String[] args) {
		tela_ini = new TelaInicial();

	}

}
