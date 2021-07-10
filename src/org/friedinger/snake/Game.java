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
	private int speed;
	private static char direction; //w=Up, a=Left, s=Down, d=Right
	private int foodx;
	private int foody;
	private boolean foodexist;
	private int time = 1;
	private int startlength = 1;

	
	//Changeable variables
	private char startdirection = 'd'; //w=Up, a=Left, s=Down, d=Right
	private int startx = 0; //Range 0 to 19
	private int starty = 0; //Range 0 to 15
	private int startspeed = 500; //Start time between steps
	private int speedchange = 20; //Decrease of the speed per collected food
	
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
			speed = startspeed - speedchange * (length - 1);
			System.out.println("Speed: " + speed);
			Gui.gui.setTitle("Snake | Score: " + length);
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
		touch(); //Check on collision
		System.out.println((Integer)x.get(time));
		System.out.println((Integer)y.get(time));
	}
	
	//Check on collision
	private void touch() {
		//Collision with wall right
		if ((Integer)x.get(time) >= 20) {
			System.out.println("Exit wall right");
			System.out.println("Length:" + length);
			System.exit(0);
		}
		//Collision with wall left
		if ((Integer)x.get(time) < 0) {
			System.out.println("Exit wall left");
			System.out.println("Length:" + length);
			System.exit(0);
		}
		//Collision with wall bottom
		if ((Integer)y.get(time) >= 16) {
			System.out.println("Exit wall bottom");
			System.out.println("Length:" + length);
			System.exit(0);
		}
		//Collision with wall top
		if ((Integer)y.get(time) < 0) {
			System.out.println("Exit wall top");
			System.out.println("Length: " + length);
			System.exit(0);
		}
		//Collision with food
		if ((Integer)x.get(time) == foodx && (Integer)y.get(time) == foody) {
			System.out.println("Food collected");
			foodexist = false;
			length++;
			food();
		}
		//Collision with snake
		for (int i = 0; i < length; i++) {
			if ((Integer)x.get(time) == (Integer)x.get(time - length + i) && (Integer)y.get(time) == (Integer)y.get(time - length + i)) {
				System.out.println("Exit collision with snake");
				System.exit(0);
			}
		}
	}
	
	//Spawn food
	private void food() {
		if (foodexist == false) {
			foodx = (int)(Math.random() * 20);
			foody = (int)(Math.random() * 16);
			for (int i = 0; i <= length; i++) {
				if(foodx == (Integer)x.get(time - length + i) && foody == (Integer)y.get(time - length + i)) {
					food();
					i = length + 1;
					System.out.println("Respawn Food");
				} else if (i == length){
					System.out.print("foodx: ");
					System.out.println(foodx);
					System.out.print("foody: ");
					System.out.println(foody);
					Gui.rect(foodx, foody, 2);
					foodexist = true;
				}
			}
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