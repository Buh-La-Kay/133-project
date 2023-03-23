package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;



public class GameWorld extends Observable{
	private GameCollection gameCollection;
	private int numSpiders;
	private int numFoodStations;
	private int lives;
	private int clockTime;
	private boolean soundOn;
	
	public GameWorld() {
		this.numSpiders=2;
		this.numFoodStations=2;
		this.lives=3;
		this.clockTime=0;
		this.gameCollection=new GameCollection();
		this.soundOn=false;
	}
	
	public void init() {
		//hard coded first 4 flags and ant
		int flagCount=0;
		//change these values to be based on gameworld size
		gameCollection.add(new Flag(10, new Point(1,1), ColorUtil.BLUE, ++flagCount));
		gameCollection.add(new Flag(10, new Point(200,500), ColorUtil.BLUE, ++flagCount));
		gameCollection.add(new Flag(10, new Point(600,500), ColorUtil.BLUE, ++flagCount));
		gameCollection.add(new Flag(10, new Point(600,200), ColorUtil.BLUE, ++flagCount));
		
		gameCollection.add(Ant.getAnt());
		
		
		
		for (int i=0; i<numSpiders; i++) {
			gameCollection.add(new Spider(5, randLocation(), ColorUtil.BLACK));
		}
		
		for (int i=0; i<numFoodStations; i++) {
			gameCollection.add(new FoodStation(randFoodSize(), randLocation(), ColorUtil.GRAY));
			
		}
		
	}
	
	//rand helper functions
	public Point randLocation() {
		Random random=new Random();
		float floatX=(float)(random.nextInt(1000));
		float floatY=(float)(random.nextInt(1000));
		return new Point(floatX, floatY);
	}
	
	public int randFoodSize(){
		Random random=new Random();
		int size=random.nextInt(41)+10;
		return size;
	}
	
	
	//big 3 inputs
	public void map() {
		IIterator theElements=gameCollection.getIterator();
		while (theElements.hasNext()){
			GameObject go=theElements.getNext();
			System.out.println(go);
		}
	}
	
	public void tick() {
		IIterator theElements=gameCollection.getIterator();
		while (theElements.hasNext()){
			GameObject go=theElements.getNext();
			
			if(go instanceof Spider) {
				Random random=new Random();
				int turn=random.nextInt(2);
				if(turn==0) {
					turn=-5;
				}
				else {
					turn=5;
				}
				((Spider) go).setHeading(((Spider) go).getHeading()+turn);
			}
			
			if(go instanceof Movable) {
				((Movable) go).move();
			}
			
			if(go instanceof Ant) {
				((Ant) go).setFoodLevel(((Ant) go).getFoodLevel()-((Ant) go).getFoodConsumtionRate());
				if(((Ant) go).getFoodLevel()<=0 ||((Ant) go).getHealthLevel()<=0) {
					lives--;
					//resets ant stuff to starting levels
					((Ant) go).reset();
					System.out.println("You Died");
					if(lives==0) {
						System.out.println("Game Over");
						System.exit(0);
					}
					else {
						System.out.println("You have "+lives+" lives remaining. Use them wisely, the Queen is depending on you.");
						gameCollection.clear();
						init();
					}
					
				}
			}
		}
		clockTime++;
	}

	public void display() {
		IIterator theElements=gameCollection.getIterator();
		while (theElements.hasNext()){
			GameObject go=theElements.getNext();
			if(go instanceof Ant) {
				System.out.println("Lives remaining:"+lives+"\nTime Elapsed:"+clockTime+"\nHighest Flag Reached:"+((Ant) go).getLastFlagReached()+"\nCurrent Food Level:"+((Ant) go).getFoodLevel()+"\nCurrent Health:"+((Ant) go).getHealthLevel());				
			}
		}
	}

	
	//rest of inputs
	public void accelerate() {
		IIterator theElements=gameCollection.getIterator();
		while (theElements.hasNext()){
			GameObject go=theElements.getNext();
			if(go instanceof Ant) {
				int tempMaxSpeed=((Ant) go).getMaxSpeed()*((Ant) go).getHealthLevel()/10;
				if (((Movable) go).getSpeed()+5>=tempMaxSpeed) {
					((Movable) go).setSpeed(tempMaxSpeed);
				}
				else {
					((Movable) go).setSpeed(((Movable) go).getSpeed()+5);
				}
			}
		}
	}
	
	public void brake() {
		IIterator theElements=gameCollection.getIterator();
		while (theElements.hasNext()){
			GameObject go=theElements.getNext();
			if(go instanceof Ant) {
				if(((Movable) go).getSpeed()-5<=0) {
					((Movable) go).setSpeed(0);
				}
				else {
					((Movable) go).setSpeed(((Movable) go).getSpeed()-5);
				}
			}
		}
	}
	
	public void left() {
		IIterator theElements=gameCollection.getIterator();
		while (theElements.hasNext()){
			GameObject go=theElements.getNext();
			if(go instanceof Ant) {
				((Movable) go).setHeading(((Movable) go).getHeading()-5);
			}
		}
	}
	
	public void right() {
		IIterator theElements=gameCollection.getIterator();
		while (theElements.hasNext()){
			GameObject go=theElements.getNext();
			if(go instanceof Ant) {
				((Movable) go).setHeading(((Movable) go).getHeading()+5);
			}
		}
	}
	
	//need to change implementation to user selecting
	public void food() {
		IIterator theElements=gameCollection.getIterator();
		while (theElements.hasNext()){
			GameObject go=theElements.getNext();
			/*if(go instanceof Ant) {
				for(int j=0; j<gameList.size(); j++) {
					if(gameList.get(j) instanceof FoodStation && ((FoodStation) gameList.get(j)).getCapacity()>0) {
						((Ant) gameList.get(i)).setFoodLevel(((Ant) gameList.get(i)).getFoodLevel()+((FoodStation) gameList.get(j)).getCapacity());
						((FoodStation) gameList.get(j)).setCapacity(0);
						break;
					}
				}
			}*/
		}
	}
	
	public void get() {
		IIterator theElements=gameCollection.getIterator();
		while (theElements.hasNext()){
			GameObject go=theElements.getNext();
			if(go instanceof Ant) {
				((Ant) go).setHealthLevel(((Ant) go).getHealthLevel()-1);
				//casting to int might cause problems
				((Movable) go).setSpeed((int) (((Movable) go).getSpeed()-(((Movable) go).getSpeed()*.1)));
				if(((Ant) go).getHealthLevel()<=4) {
					((GameObject) go).setColor(ColorUtil.MAGENTA);
				}
				else if(((Ant) go).getHealthLevel()<=7) {
					((GameObject) go).setColor(ColorUtil.YELLOW);
				}
			}
		}
	}
	
	public void flagCollision(int flagNum) {
		//use static method Dialogue.show() to create a dialogue box that allows
		//the user to enter the number on a text field located on the dialogue box
		//("titled form in CN1" slide of Gui basics chapter in lecutre notes
		//handle invalid inputs
		IIterator theElements=gameCollection.getIterator();
		while (theElements.hasNext()){
			GameObject go=theElements.getNext();
			if(go instanceof Ant) {
				if(flagNum-1==((Ant) go).getLastFlagReached()) {
					((Ant) go).setLastFlagReached(flagNum);
				}
			}
		}
	}
	
	public void toggleSound() {
		if (this.soundOn){
			this.soundOn=false;
		}
		else {
			this.soundOn=true;
		}
	}
	
	public void exit() {
		
	}
	
	public void about() {
		String output="Start to Finish\nCreated by Blake Danz for CSC 133\nVersion 2.0";
		Dialog.show("About", output);
	}
	
	public void help() {
		String output="Here is a list of the available keybinds and the actions they perform:\n'a': Accelerate your ant\n'b': Brake, slow down your ant\n'l': Turn ant 5 degrees to the left\n'r': Turn ant 5 degrees to the right\n'f': Collide with a given flag number\n't': Tick, progresses the game by 1 second";
		Dialog.show("Help", output);
	}
	
	
	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getClockTime() {
		return clockTime;
	}

	public void setClockTime(int clockTime) {
		this.clockTime = clockTime;
	}
	
	public String getSound() {
		if (this.soundOn){
			return "ON";
		}
		else {
			return "OFF";
		}
	}
	
	public GameCollection getGameCollection() {
		return this.gameCollection;
	}
}
