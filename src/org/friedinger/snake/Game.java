package org.friedinger.snake;

import org.friedinger.snake.gui.Gui;
import java.util.ArrayList;

public class Game {
	
	//Program variables
	@SuppressWarnings("rawtypes")
	private ArrayList x = new ArrayList();
	@SuppressWarnings("rawtypes")
	private ArrayList y = new ArrayList();
	private int length;
	private int time = 1;
	private static char direction; //w=Up, a=Left, s=Down, d=Right
	private int foodx;
	private int foody;
	private boolean foodexist;
	
	//Changeable variables
	private char startdirection = 'd'; //w=Up, a=Left, s=Down, d=Right
	private int startx = 0; //Range 0 to 19
	private int starty = 0; //Range 0 to 15
	private int startlength = 1;
	private int speed = 500; //Lower equals faster
	
	//Start game and loop
	@SuppressWarnings("unchecked")
	public Game() throws InterruptedException {
		Thread.sleep(1000);
		x.add(startx);
		y.add(starty);
		direction = startdirection;
		length = startlength;
		while (true) {
			System.out.println("Game");
			move(); //Get next coordinates
			food(); //Spawn food
			Gui.rect((Integer)x.get(time), (Integer)y.get(time), 1); //Print new snake parts
			Gui.rect((Integer)x.get(time-length), (Integer)y.get(time-length), 0); //Remove old snake parts
			time++;
			Thread.sleep(speed); //Wait
		}
	}
	
	//Get next coordinates
	@SuppressWarnings("unchecked")
	public void move() {
		switch (direction) {
			case 'w':
				x.add((Integer)x.get(time-1));
				y.add((Integer)y.get(time-1) - 1);
				break;
			case 'a':
				x.add((Integer)x.get(time-1) - 1);
				y.add((Integer)y.get(time-1));
				break;
			case 's':
				x.add((Integer)x.get(time-1));
				y.add((Integer)y.get(time-1) + 1);
				break;
			case 'd':
				x.add((Integer)x.get(time-1) + 1);
				y.add((Integer)y.get(time-1));
				break;
		}
		touch(); //Check on coalition 
		System.out.println((Integer)x.get(time));
		System.out.println((Integer)y.get(time));
	}
	
	//Check on coalition
	private void touch() {
		//Coalition with wall right
		if ((Integer)x.get(time) >= 20) {
			System.exit(0);
		}
		//Coalition with wall left
		if ((Integer)x.get(time) < 0) {
			System.exit(0);
		}
		//Coalition with wall bottom
		if ((Integer)y.get(time) >= 16) {
			System.exit(0);
		}
		//Coalition with wall top
		if ((Integer)y.get(time) < 0) {
			System.exit(0);
		}
		//Coalition with food
		if ((Integer)x.get(time) == foodx && (Integer)y.get(time) == foody) {
			foodexist = false;
			length++;
			food();
		}
		//Coalition with snake
		for (int i = 0; i < length; i++) {
			if ((Integer)x.get(time) == (Integer)x.get(time - length + i) && (Integer)y.get(time) == (Integer)y.get(time - length + i)) {
				System.exit(0);
			}
		}
	}
	
	//Spawn food
	private void food() {
		if (foodexist == false) {
			foodx = (int)(Math.random() * 20);
			foody= (int)(Math.random() * 16);
			System.out.print("foodx: ");
			System.out.println(foodx);
			System.out.print("foody: ");
			System.out.println(foody);
			Gui.rect(foodx, foody, 2);
			foodexist = true;
		}
	}
	
	//Convert input to direction
	public static void w() {
		System.out.println("W");
		direction = 'w';
	}
	
	//Convert input to direction
	public static void a() {
		System.out.println("A");
		direction = 'a';
	}

	//Convert input to direction
	public static void s() {
		System.out.println("S");
		direction = 's';
	}

	//Convert input to direction
	public static void d() {
		System.out.println("D");
		direction = 'd';
	}

	//Convert input to ...
	public static void space() {
		System.out.println("Space");
	}
	
}