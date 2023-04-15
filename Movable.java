package com.mycompany.a3;
import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject{
	private int heading;
	private int speed;
	private int mapWidth;
	private int mapHeight;
	
	
	public Movable(int size, Point location, int color, int mapHeight, int mapWidth) {
		super(size, location, color);
		this.heading=0;
		this.speed=50;
		this.mapHeight=mapHeight;
		this.mapWidth=mapWidth;
	}

	public int getHeading() {
		return heading;
	}
	
	public void setHeading(int heading) {
		this.heading = heading;
		if(this.heading>360) {
			this.heading=this.heading-360;
		}
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public String toString() {
		return (super.toString()+" heading="+this.heading+" speed="+this.speed);
	}
	
	public void move() {
		double theta=Math.toRadians(90-this.heading);
		double deltaX=Math.cos(theta)*this.speed;
		double deltaY=Math.sin(theta)*this.speed;
		
		float newX=(float) (this.getLocation().getX()+deltaX);
		float newY=(float) (this.getLocation().getY()+deltaY);
		
		//edge of map handling
		if (newX>this.mapWidth-1) {
			newX=this.mapWidth-1;
		}
		if(newY>this.mapHeight-1) {
			newY=this.mapHeight-1;
		}
		if(newX<0) {
			newX=0;
		}
		if(newY<0) {
			newY=0;
		}
		
		//if edge of map, turn around
		if(newX==0 || newX==this.mapWidth-1 || newY==0 || newY==this.mapHeight-1) {
			this.heading=this.heading+180;
		}
		
		Point newPoint=new Point(newX, newY);
		this.setLocation(newPoint);
		//TODO add checks for edge of map
	}
}
