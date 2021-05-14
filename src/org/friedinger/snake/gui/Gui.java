package org.friedinger.snake.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.friedinger.snake.Game;

public class Gui extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	public static JFrame gui;
	
	//Create GUI
	public Gui() {
		gui = new JFrame("Snake");
		gui.setSize(1016, 839);
		gui.setResizable(false);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.getContentPane().add(new Rect(0,0,-1));
		//ImageIcon guiicon = new ImageIcon("com/github/friedinger/snake/resources/icon.png");
		//gui.setIconImage(guiicon.getImage());
		gui.addKeyListener(this);
		gui.setVisible(true);
		System.out.println(gui.getWidth());
		System.out.println(gui.getHeight());
	}
	
	//Get pressed key
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()) {
			case 'w':
				Game.w();
				break;
			case 'a':
				Game.a();
				break;
			case 's':
				Game.s();
				break;
			case 'd':
				Game.d();
				break;
			case ' ':
				Game.space();
				break;
		}
	}
	
	//Create Square: x-position, y-position, color (-1 fill all black, 0 black square, 1 white square, 2 red square)
	public static void rect(int x, int y, int c) { 
		gui.getContentPane().add(new Rect(x*50, y*50, c));
		gui.setVisible(true);
	}
	
	//Not needed, but necessary methods
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
}