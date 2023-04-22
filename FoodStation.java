package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class FoodStation extends Fixed {
	
	private int capacity;

	
	public FoodStation(int size, Point location, int color) {
		super(size, location, color);
		//capacity is proportional to size of station
		capacity=this.getSize()*10;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String toString() {
		return ("FoodStation: "+super.toString()+" capacity="+this.capacity);
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		g.fillRect((int) this.getLocation().getX()-this.getSize()/2 ,(int) this.getLocation().getY()+this.getSize()/2, this.getSize(), this.getSize() );
		g.setColor(ColorUtil.BLACK);
		g.drawString(Integer.toString(this.getCapacity()), (int) this.getLocation().getX(), (int) this.getLocation().getY());
	}

	@Override
	public void handleCollision(GameObject otherObject, GameWorld gw) {
		// TODO Auto-generated method stub
		
	}
}
