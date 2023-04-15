package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Ant extends Movable implements ISteerable{
	private int maxSpeed;
	private int foodLevel;
	private int foodConsumtionRate;
	private int healthLevel;
	private int lastFlagReached;
	private static Ant isAnt;
	
	private Ant(int size, Point location, int color, int mapHeight, int mapWidth) {
		super(size, location, color, mapHeight, mapWidth);
		this.maxSpeed=50;
		this.foodLevel=10;
		this.foodConsumtionRate=1;
		this.healthLevel=10;
		this.lastFlagReached=1; 
	}
	
	//singleton solution
	public static Ant getAnt(int mapHeight, int mapWidth) {
		if (isAnt==null) {
			//hard coded initial location to flag 1
			
			isAnt=new Ant(25, new Point(200,200), ColorUtil.GREEN, mapHeight, mapWidth);
		}
		return isAnt;
	}
	
	public int getMaxSpeed() {
		return maxSpeed;
	}
	
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	public int getFoodLevel() {
		return foodLevel;
	}
	
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}
	
	public int getFoodConsumtionRate() {
		return foodConsumtionRate;
	}
	
	public void setFoodConsumtionRate(int foodConsumtionRate) {
		this.foodConsumtionRate = foodConsumtionRate;
	}
	
	public int getHealthLevel() {
		return this.healthLevel;
	}
	
	public void setHealthLevel(int healthLevel) {
		this.healthLevel=healthLevel;
	}
	
	public int getLastFlagReached() {
		return lastFlagReached;
	}
	
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	
	public void steer(int ammount) {
		this.setHeading(this.getHeading()+ammount);
	}
	
	public String toString() {
		return ("Ant: "+super.toString()+" maxSpeed="+this.maxSpeed+" foodLevel:"+this.foodLevel+" foodConsumptionRate:"+this.foodConsumtionRate+" healthLevel:"+this.healthLevel+" lastFlagReached:"+this.lastFlagReached);
	}
	
	public void reset() {
		this.maxSpeed=50;
		this.foodLevel=10;
		this.healthLevel=10;
		this.lastFlagReached=1;
		this.setLocation(new Point(1,1));
		this.setColor(ColorUtil.GREEN);
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int radius=this.getSize()/2;
		g.fillArc( (int) this.getLocation().getX()-radius, (int) this.getLocation().getY()-radius, 2*radius, 2*radius, 0, 360);
		g.setColor(this.getColor());
		
		
	}
}
