package com.github.friedinger.snake;

import com.github.friedinger.snake.gui.Gui;
import java.util.ArrayList;

public class Game {
	
	//Program variables
	@SuppressWarnings("rawtypes")
	ArrayList x = new ArrayList();
	@SuppressWarnings("rawtypes")
	ArrayList y = new ArrayList();
	int length;
	int time = 1;
	static char direction; //w=Up, a=Left, s=Down, d=Right
	int foodx;
	int foody;
	boolean foodexist;
	
	//Changeable variables
	char startdirection = 'd'; //w=Up, a=Left, s=Down, d=Right
	int startx = 0; //Range 0 to 19
	int starty = 0; //Range 0 to 15
	int startlength = 1; //Range 1++
	int speed = 500; //Lower equals faster
	
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
	public void touch() {
		//Wall right
		if ((Integer)x.get(time) >= 20) {
			System.exit(0);
		}
		//Wall left
		if ((Integer)x.get(time) < 0) {
			System.exit(0);
		}
		//Wall bottom
		if ((Integer)y.get(time) >= 16) {
			System.exit(0);
		}
		//Wall top
		if ((Integer)y.get(time) < 0) {
			System.exit(0);
		}
		//Coalition with food
		if ((Integer)x.get(time) == foodx && (Integer)y.get(time) == foody) {
			foodexist = false;
			length++;
			food();
		}
	}
	
	//Spawn food
	public void food() {
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