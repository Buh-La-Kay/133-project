package com.mycompany.a1;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
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
	
	private void setSize(int size) {
		this.size = size;
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
	
	
	
	
	
}
