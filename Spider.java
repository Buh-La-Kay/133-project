package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Spider extends Movable{
	
	public Spider(int size, Point location, int color, int mapHeight, int mapWidth) {
		super(size, location, color, mapHeight, mapWidth);
	}
	
	public void setColor() {
		
	}

	public String toString() {
		return ("Spider: "+super.toString());
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int[] xPoints=new int[3];
		int[] yPoints=new int[3];
		
		//top point
		xPoints[0]=(int) this.getLocation().getX();
		yPoints[0]=(int) this.getLocation().getY()+this.getSize()/2;
		
		//bot left point
		xPoints[1]=(int) this.getLocation().getX()-this.getSize()/2;
		yPoints[1]=(int) this.getLocation().getY()-this.getSize()/2;
		
		//bot right point
		xPoints[2]=(int) this.getLocation().getX()+this.getSize()/2;
		yPoints[2]=(int) this.getLocation().getY()-this.getSize()/2;
		
		g.drawPolygon(xPoints, yPoints, 3);
		g.setColor(this.getColor());
		System.out.println(this.getColor());
		
	}
}
