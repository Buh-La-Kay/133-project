package com.mycompany.a1;

import com.codename1.charts.models.Point;

public class FoodStation extends Fixed {
	
	private int capacity;

	
	public FoodStation(int size, Point location, int color) {
		super(size, location, color);
		//capacity is proportional to size of station
		capacity=this.getSize();
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
}
