package gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

class CartasMostradas extends JFrame {

	private static final long serialVersionUID = 1L;
	JLabel label_cartas = new JLabel("Minhas Cartas: ");
	ArrayList<Image> img_cartas = new ArrayList<Image>();

	/* construtor recebe o ArrayList com o nome das cartas do jogador */
	CartasMostradas(ArrayList<String> minhas_cartas) {
		this.setSize(700, 700);
		this.setLayout(null);
		this.setTitle("Selecione os personagens do jogo");
		this.setVisible(true);

		label_cartas.setBounds(300, 25, 100, 20);
		this.add(label_cartas);

		for (String carta : minhas_cartas) {
			try {
				Image a = // mudar pra adicionar na lista
						ImageIO.read(new File(CartasMostradas.get_img_path(carta)));
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
	}

	/* método auxiliar para pegar o path da imagem dependendo do nome da carta */
	static private String get_img_path(String nome_carta) {
		switch (nome_carta) {
		/* casos da carta ser um suspeito */
		case "Srta. Scarlet":
			return "images/Suspeitos/Scarlet.png";
		case "Coronel Mustard":
			return "images/Suspeitos/Mustard.png";
		case "Professor Plum":
			return "images/Suspeitos/Plum.png";
		case "Reverendo Green":
			return "images/Suspeitos/Green.png";
		case "Sra. White":
			return "images/Suspeitos/White.png";
		case "Sra. Peacock":
			return "images/Suspeitos/Peacock.png";

		/* casos da carta ser uma arma */
		case "Corda":
			return "images/Armas/Corda.png";
		case "Cano de Chumbo":
			return "images/Armas/Cano.png";
		case "Faca":
			return "images/Armas/Faca.png";
		case "Chave Inglesa":
			return "images/Armas/ChaveInglesa.png";
		case "Castical":
			return "images/Armas/Castical.png";
		case "Revolver":
			return "images/Armas/Revolver.png";

		/* casos da carta ser um cômodo */
		case "Biblioteca":
			return "images/Armas/Biblioteca.png";
		case "Cozinha":
			return "images/Armas/Cozinha.png";
		case "Entrada":
			return "images/Armas/Entrada.png";
		case "Escritorio":
			return "images/Armas/Escritorio.png";
		case "Jardim de Inverno":
			return "images/Armas/Castical.png";
		case "Sala de Estar":
			return "images/Armas/SalaDeEstar.png";
		case "Sala de Jantar":
			return "images/Armas/SalaDeJantar.png";
		case "Sala de Musica":
			return "images/Armas/SalaDeMusica.png";
		case "Salao de Jogos":
			return "images/Armas/SalaoDeJogos.png";
		default:
			return "";
		}
	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

		int cont = 0;

		for (Image img : img_cartas) {
			g2d.drawImage(img, 20 + 100 * (cont % 3), 50 + 200 * (cont / 3), img.getWidth(null), img.getHeight(null),
					this);

			cont++;
		}

	}

}