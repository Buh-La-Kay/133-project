package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject implements IDrawable, ICollider{
	private int size;
	private Point location;
	private int color;
	
	public GameObject(int size,Point location,int color) {
		this.size=size;
		this.location=location;
		this.color=color;
	}
	
	public int getSize() {
		return size;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public String toString() {
		double x=this.getLocation().getX();
		double y=this.getLocation().getY();
		x=Math.round(x*10)/10;
		y=Math.round(y*10)/10;
		return ("loc="+x+", "+y+" color=["+ColorUtil.red(this.getColor())+","+ColorUtil.green(this.getColor())+","+ColorUtil.blue(this.getColor())+"] size="+this.getSize());
	}
	
	public boolean collidesWith(GameObject otherObject) {
		boolean collision=false;
		float r1=this.location.getX()+this.size/2;
		float l1=this.location.getX()-this.size/2;
		float t1=this.location.getY()+this.size/2;
		float b1=this.location.getY()-this.size/2;
		
		float r2=otherObject.getLocation().getX()+this.size/2;
		float l2=otherObject.getLocation().getX()-this.size/2;
		float t2=otherObject.getLocation().getY()+this.size/2;
		float b2=otherObject.getLocation().getY()-this.size/2;
		
		if(r1<l2 || l1>r2 || t2<b1 || t1<b2) {
			//collision not possible if any of these constraints are true
		}
		else{
			collision=true;
		}
		
		return collision;
	}
	
	public void handleCollision(GameObject otherObject, GameWorld gw) {
		
	}
	

	
	
	
	
	
}
