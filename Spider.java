package com.mycompany.a1;

import com.codename1.charts.models.Point;

public class Spider extends Movable{
	
	public Spider(int size, Point location, int color) {
		super(size, location, color);
	}

	public String toString() {
		return ("Spider: "+super.toString());
	}
}
