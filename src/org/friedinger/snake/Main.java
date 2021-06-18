/* 

Snake by Friedinger
Version 1.3
https://friedinger.org/snake/

*/

package org.friedinger.snake;

import org.friedinger.snake.gui.Gui;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		new Gui(); //GUI
		new Game(); //Game engine
	}
	
}