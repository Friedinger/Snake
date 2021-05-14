package org.friedinger.snake.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Rect extends JPanel {
	private static final long serialVersionUID = 1L;
	private int rectx = 0;
	private int recty = 0;
	private int rectc = 0;
	
	//Get parameters
	public Rect(int x, int y, int c) {
		rectx = x;
		recty = y;
		rectc = c;
	}
	
	//Create Square with color, method will be executed itself, because of the name "paint"
	public void paint(Graphics g) {
		switch (rectc) {
		case -1:
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1000, 800);
			break;
		case 0:
			g.setColor(Color.BLACK);
			g.fillRect(this.rectx, this.recty, 50, 50);
			break;
		case 1:
			g.setColor(Color.WHITE);
			g.fillRect(this.rectx, this.recty, 50, 50);
			break;
		case 2:
			g.setColor(Color.RED);
			g.fillRect(this.rectx, this.recty, 50, 50);
			break;
		}
	}
	
}