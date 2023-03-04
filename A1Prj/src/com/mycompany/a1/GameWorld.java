package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;



public class GameWorld {
	private ArrayList <GameObject> gameList;
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
		this.gameList=new ArrayList <GameObject>();
		this.soundOn=true;
	}
	
	public void init() {
		//hard coded first 4 flags and ant
		int flagCount=0;
		gameList.add(new Flag(10, new Point(1,1), ColorUtil.BLUE, ++flagCount));
		gameList.add(new Flag(10, new Point(200,500), ColorUtil.BLUE, ++flagCount));
		gameList.add(new Flag(10, new Point(600,500), ColorUtil.BLUE, ++flagCount));
		gameList.add(new Flag(10, new Point(600,200), ColorUtil.BLUE, ++flagCount));
		gameList.add(new Ant(5, new Point(1,1), ColorUtil.GREEN));
		
		//singleton ant solution, ran into problems with reinstantiating on reset
		/*gameList.add(Ant.getAnt());
		gameList.get(1).setLocation(gameList.get(0).getLocation());
		*/
		
		for (int i=0; i<numSpiders; i++) {
			gameList.add(new Spider(5, randLocation(), ColorUtil.BLACK));
		}
		
		for (int i=0; i<numFoodStations; i++) {
			gameList.add(new FoodStation(randFoodSize(), randLocation(), ColorUtil.GRAY));
			
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
		for (int i=0; i<gameList.size(); i++) {
			System.out.println(gameList.get(i).toString());
		}
	}
	
	public void tick() {
		for (int i=0; i<gameList.size(); i++) {
			
			if(gameList.get(i) instanceof Spider) {
				Random random=new Random();
				int turn=random.nextInt(2);
				if(turn==0) {
					turn=-5;
				}
				else {
					turn=5;
				}
				((Spider) gameList.get(i)).setHeading(((Spider) gameList.get(i)).getHeading()+turn);
			}
			
			if(gameList.get(i) instanceof Movable) {
				((Movable) gameList.get(i)).move();
			}
			
			if(gameList.get(i) instanceof Ant) {
				((Ant) gameList.get(i)).setFoodLevel(((Ant) gameList.get(i)).getFoodLevel()-((Ant) gameList.get(i)).getFoodConsumtionRate());
				if(((Ant) gameList.get(i)).getFoodLevel()<=0 ||((Ant) gameList.get(i)).getHealthLevel()<=0) {
					lives--;
					System.out.println("You Died");
					if(lives==0) {
						System.out.println("Game Over");
						System.exit(0);
					}
					else {
						System.out.println("You have "+lives+" lives remaining. Use them wisely, the Queen is depending on you.");
						gameList.clear();
						init();
					}
					
				}
			}
		}
		clockTime++;
	}

	public void display() {
		for(int i=0; i<gameList.size(); i++) {
			if(gameList.get(i) instanceof Ant) {
				System.out.println("Lives remaining:"+lives+"\nTime Elapsed:"+clockTime+"\nHighest Flag Reached:"+((Ant) gameList.get(i)).getLastFlagReached()+"\nCurrent Food Level:"+((Ant) gameList.get(i)).getFoodLevel()+"\nCurrent Health:"+((Ant) gameList.get(i)).getHealthLevel());
			}
		}

	}

	
	//rest of inputs
	public void accelerate() {
		for(int i=0; i<gameList.size(); i++) {
			if(gameList.get(i) instanceof Ant) {
				int tempMaxSpeed=((Ant) gameList.get(i)).getMaxSpeed()*((Ant) gameList.get(i)).getHealthLevel()/10;
				if (((Movable) gameList.get(i)).getSpeed()+5>=tempMaxSpeed) {
					((Movable) gameList.get(i)).setSpeed(tempMaxSpeed);
				}
				else {
					((Movable) gameList.get(i)).setSpeed(((Movable) gameList.get(i)).getSpeed()+5);
				}
			}
		}
	}
	
	public void brake() {
		for(int i=0; i<gameList.size(); i++) {
			if(gameList.get(i) instanceof Ant) {
				if(((Movable) gameList.get(i)).getSpeed()-5<=0) {
					((Movable) gameList.get(i)).setSpeed(0);
				}
				else {
					((Movable) gameList.get(i)).setSpeed(((Movable) gameList.get(i)).getSpeed()-5);
				}
			}
		}
	}
	
	public void left() {
		for(int i=0; i<gameList.size(); i++) {
			if(gameList.get(i) instanceof Ant) {
				((Movable) gameList.get(i)).setHeading(((Movable) gameList.get(i)).getHeading()-5);
			}
		}
	}
	
	public void right() {
		for(int i=0; i<gameList.size(); i++) {
			if(gameList.get(i) instanceof Ant) {
				((Movable) gameList.get(i)).setHeading(((Movable) gameList.get(i)).getHeading()+5);
			}
		}
	}
	
	//implementation right now: find first station in arraylist with capacity>0 and feed ant
	public void food() {
		for(int i=0; i<gameList.size(); i++) {
			if(gameList.get(i) instanceof Ant) {
				for(int j=0; j<gameList.size(); j++) {
					if(gameList.get(j) instanceof FoodStation && ((FoodStation) gameList.get(j)).getCapacity()>0) {
						((Ant) gameList.get(i)).setFoodLevel(((Ant) gameList.get(i)).getFoodLevel()+((FoodStation) gameList.get(j)).getCapacity());
						((FoodStation) gameList.get(j)).setCapacity(0);
						break;
					}
				}
			}
		}
	}
	
	public void get() {
		for(int i=0; i<gameList.size(); i++) {
			if(gameList.get(i) instanceof Ant) {
				((Ant) gameList.get(i)).setHealthLevel(((Ant) gameList.get(i)).getHealthLevel()-1);
				//casting to int might cause problems
				((Movable) gameList.get(i)).setSpeed((int) (((Movable) gameList.get(i)).getSpeed()-(((Movable) gameList.get(i)).getSpeed()*.1)));
				if(((Ant) gameList.get(i)).getHealthLevel()<=4) {
					gameList.get(i).setColor(ColorUtil.MAGENTA);
				}
				else if(((Ant) gameList.get(i)).getHealthLevel()<=7) {
					gameList.get(i).setColor(ColorUtil.YELLOW);
				}
			}
		}
	}
	
	public void flagCollision(int flagNum) {
		for(int i=0; i<gameList.size(); i++) {
			if(gameList.get(i) instanceof Ant) {
				if(flagNum-1==((Ant) gameList.get(i)).getLastFlagReached()) {
					((Ant) gameList.get(i)).setLastFlagReached(flagNum);
				}
			}
		}
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
	
	public ArrayList<GameObject> getGameList() {
		return this.gameList;
	}
}
