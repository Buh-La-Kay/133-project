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
		this.speed=100;
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
	
	public void move(Point origin, int elapsedTime) {
		double theta=Math.toRadians(90-this.heading);
		
		double dist=this.speed*elapsedTime/1000;
		
		double deltaX=Math.cos(theta)*dist;
		double deltaY=Math.sin(theta)*dist;
		
		float newX=(float) (this.getLocation().getX()+deltaX);
		float newY=(float) (this.getLocation().getY()+deltaY);
		
		//edge of map handling
		if (newX>this.mapWidth-1+origin.getX()) {
			newX=this.mapWidth-1+origin.getX();
		}
		if(newY>this.mapHeight-1+origin.getY()) {
			newY=this.mapHeight-1+origin.getY();
		}
		if(newX<origin.getX()) {
			newX=origin.getX();
		}
		if(newY<origin.getY()) {
			newY=origin.getY();
		}
		
		//if edge of map, turn around
		if(newX==origin.getX() || newX==this.mapWidth-1+origin.getX() || newY==origin.getY() || newY==this.mapHeight-1+origin.getY()) {
			this.heading=this.heading+180;
		}
		
		Point newPoint=new Point(newX, newY);
		this.setLocation(newPoint);
	}
}
