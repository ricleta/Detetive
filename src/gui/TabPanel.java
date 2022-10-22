// usado para exibir imagem, nao funciona

package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class TabPanel extends JPanel
{
	private Image image;
	
	TabPanel(Image img)
	{
		image = img;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawImage(image, 1000, 1000, null);
	}
}
