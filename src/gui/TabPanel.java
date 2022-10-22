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
		
		g.drawImage(image, 0, 0, null);
	}
}
