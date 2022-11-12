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
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(700, 700);
		this.setLayout(null);
		this.setTitle("Cartas");
		this.setVisible(true);

		label_cartas.setBounds(300, 25, 100, 20);
		this.add(label_cartas);

		for (String carta : minhas_cartas) {
			try {
				img_cartas.add(ImageIO.read(new File(get_img_path(carta))));
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
		
		repaint();
	}

	/* método auxiliar para pegar o path da imagem dependendo do nome da carta */
	private String get_img_path(String nome_carta) {
		switch (nome_carta) {
		/* casos da carta ser um suspeito */
		case "Srta. Scarlet":
			return "images/Suspeitos/Scarlet.jpg";
		case "Coronel Mustard":
			return "images/Suspeitos/Mustard.jpg";
		case "Professor Plum":
			return "images/Suspeitos/Plum.jpg";
		case "Reverendo Green":
			return "images/Suspeitos/Green.jpg";
		case "Sra. White":
			return "images/Suspeitos/White.jpg";
		case "Sra. Peacock":
			return "images/Suspeitos/Peacock.jpg";

		/* casos da carta ser uma arma */
		case "Corda":
			return "images/Armas/Corda.jpg";
		case "Cano de Chumbo":
			return "images/Armas/Cano.jpg";
		case "Faca":
			return "images/Armas/Faca.jpg";
		case "Chave Inglesa":
			return "images/Armas/ChaveInglesa.jpg";
		case "Castical":
			return "images/Armas/Castical.jpg";
		case "Revolver":
			return "images/Armas/Revolver.jpg";

		/* casos da carta ser um cômodo */
		case "Biblioteca":
			return "images/Comodos/Biblioteca.jpg";
		case "Cozinha":
			return "images/Comodos/Cozinha.jpg";
		case "Entrada":
			return "images/Comodos/Entrada.jpg";
		case "Escritorio":
			return "images/Comodos/Escritorio.jpg";
		case "Jardim de Inverno":
			return "images/Comodos/JardimInverno.jpg";
		case "Sala de Estar":
			return "images/Comodos/SalaDeEstar.jpg";
		case "Sala de Jantar":
			return "images/Comodos/SalaDeJantar.jpg";
		case "Sala de Musica":
			return "images/Comodos/SalaDeMusica.jpg";
		case "Salao de Jogos":
			return "images/Comodos/SalaoDeJogos.jpg";
		default:
			return "";
		}
	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

		int cont = 0;

		for (Image img : img_cartas) 
		{
			g2d.drawImage(img, 20 + 200 * (cont % 3), 50 + 300 * (cont / 3), img.getWidth(this) - 100, img.getHeight(this) - 200,
					this);

			cont++;
		}

	}

}