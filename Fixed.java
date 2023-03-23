package com.mycompany.a1;

import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject {
	
	public Fixed(int size, Point location, int color) {
		super(size, location, color);
	}

	public void setLocation(Point location) {
		//locations cannot be changed on fixed objects
	}
	
	public String toString() {
		return super.toString();
	}
}
