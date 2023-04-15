package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;



public class GameWorld extends Observable{
	private GameCollection gameCollection;
	private int numSpiders;
	private int numFoodStations;
	private int lives;
	private int clockTime;
	private boolean soundOn;
	private int height;
	private int width;
	private Point origin;
	
	public GameWorld() {
		this.numSpiders=1;
		this.numFoodStations=2;
		this.lives=3;
		this.clockTime=0;
		this.gameCollection=new GameCollection();
		this.soundOn=false;
	}
	
	public void init() {

		int flagCount=0;

		gameCollection.add(Ant.getAnt(this.height,this.width));
		
		gameCollection.add(new Flag(40, new Point(this.origin.getX()+this.getWidth()/4, this.origin.getY()+this.height/4), ColorUtil.BLUE, ++flagCount));
		gameCollection.add(new Flag(40, new Point(this.origin.getX()+this.getWidth()*3/4, this.origin.getY()+this.height/4), ColorUtil.BLUE, ++flagCount));
		gameCollection.add(new Flag(40, new Point(this.origin.getX()+this.getWidth()/4, this.origin.getY()+this.height*3/4), ColorUtil.BLUE, ++flagCount));
		gameCollection.add(new Flag(40, new Point(this.origin.getX()+this.getWidth()*3/4, this.origin.getY()+this.height*3/4), ColorUtil.BLUE, ++flagCount));
		

		
		gameCollection.add(new Spider(30, randLocation(), ColorUtil.BLACK, this.width, this.height));
		gameCollection.add(new Spider(30, randLocation(), ColorUtil.BLACK, this.width, this.height));
		
		gameCollection.add(new FoodStation(randFoodSize(), randLocation(), ColorUtil.GRAY));
		gameCollection.add(new FoodStation(randFoodSize(), randLocation(), ColorUtil.GRAY));

			
		this.setChanged();
		this.notifyObservers();
	}
	
	//rand helper functions
	public Point randLocation() {
		Random random=new Random();
		float floatX=(float)(random.nextInt(this.width));
		float floatY=(float)(random.nextInt(this.height));
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
					if(lives>0) {
						Dialog.show("You Died", "You have "+lives+" lives remaining. Use them wisely, the Queen is depending on you.", "Ok", null);
						gameCollection.clear();
						init();
					}
					else{
						Dialog.show("Game Over", "You Lose", "Ok", null);
						Display.getInstance().exitApplication();
					}
				}
			}
		}
		//check for collisions here?
		clockTime++;
		this.setChanged();
		this.notifyObservers();
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
		this.setChanged();
		this.notifyObservers();
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
		this.setChanged();
		this.notifyObservers();
	}
	
	public void left() {
		IIterator theElements=gameCollection.getIterator();
		while (theElements.hasNext()){
			GameObject go=theElements.getNext();
			if(go instanceof Ant) {
				((Movable) go).setHeading(((Movable) go).getHeading()-5);
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public void right() {
		IIterator theElements=gameCollection.getIterator();
		while (theElements.hasNext()){
			GameObject go=theElements.getNext();
			if(go instanceof Ant) {
				((Movable) go).setHeading(((Movable) go).getHeading()+5);
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public void food() {
		IIterator theElements=gameCollection.getIterator();
		IIterator theOtherElements=gameCollection.getIterator();
		while (theElements.hasNext()){
			GameObject go=theElements.getNext();
			if(go instanceof Ant) {
				while(theOtherElements.hasNext()) {
					GameObject go2=theOtherElements.getNext();
					if(go2 instanceof FoodStation && ((FoodStation) go2).getCapacity()>0) {
						((Ant) go).setFoodLevel(((Ant) go).getFoodLevel()+((FoodStation) go2).getCapacity());
						((FoodStation) go2).setCapacity(0);
						break;
					}
				}
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public void get() {
		IIterator theElements=gameCollection.getIterator();
		while (theElements.hasNext()){
			GameObject go=theElements.getNext();
			if(go instanceof Ant) {
				((Ant) go).setHealthLevel(((Ant) go).getHealthLevel()-1);
				//casting to int might cause problems
				((Movable) go).setSpeed((int) (((Movable) go).getSpeed()-(((Movable) go).getSpeed()*.1)));
				go.setColor(go.getColor()-15);
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public void flagCollision(int flagNum) {
		IIterator theElements=gameCollection.getIterator();
		while (theElements.hasNext()){
			GameObject go=theElements.getNext();
			if(go instanceof Ant) {
				if(flagNum-1==((Ant) go).getLastFlagReached()) {
					((Ant) go).setLastFlagReached(flagNum);
					if(((Ant) go).getLastFlagReached()==4) {
						Dialog.show("Winner!", "Game Over, You Win! Total Time: "+this.getClockTime(), "Ok", null);
						Display.getInstance().exitApplication();
					}
				}
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public void toggleSound() {
		if (this.soundOn){
			this.soundOn=false;
		}
		else {
			this.soundOn=true;
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public void about() {
		String output="Start to Finish\nCreated by Blake Danz for CSC 133\nVersion 2.0";
		Dialog.show("About", output, "Ok", null);
	}
	
	public void help() {
		String output="Here is a list of the available keybinds:\n'a': Accelerate your ant\n'b': Brake, slow down your ant\n'l': Turn ant 5 degrees to the left\n'r': Turn ant 5 degrees to the right\n'f': Collide with a given flag number\n't': Tick, progresses the game by 1 second";
		Dialog.show("Help", output, "Ok", null);
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
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int height) {
		this.height=height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setWidth(int width) {
		this.width=width;
	}
	
	public GameCollection getGameCollection() {
		return this.gameCollection;
	}

	public Point getOrigin() {
		return origin;
	}

	public void setOrigin(Point point) {
		this.origin = point;
	}
}
