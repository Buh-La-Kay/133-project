package com.mycompany.a1;
import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject{
	
	public Movable(int size, Point location, int color) {
		super(size, location, color);
		heading=0;
		speed=10;
	}

	private int heading;
	private int speed;
	
	
	
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
		
		/*double deltaX=Math.cos(Math.toRadians(90-heading)*this.getSpeed());
		double deltaY=Math.sin(Math.toRadians(90-heading)*this.getSpeed());
		float newX=(float) (this.getLocation().getX()+deltaX);
		float newY=(float) (this.getLocation().getY()+deltaY);*/
		
		//edge of map handling
		if (newX>999) {
			newX=999;
		}
		if(newY>999) {
			newY=999;
		}
		if(newX<0) {
			newX=0;
		}
		if(newY<0) {
			newY=0;
		}
		
		//if edge of map, turn around
		if(newX==0 || newX==999 || newY==0 || newY==999) {
			this.heading=this.heading+180;
		}
		
		Point newPoint=new Point(newX, newY);
		this.setLocation(newPoint);
		//TODO add checks for edge of map
	}
}
