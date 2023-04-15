package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Flag extends Fixed{
	
	private int sequenceNumber;
	
	
	public Flag(int size, Point location, int color, int sequenceNumber) {
		super(size, location, color);
		this.sequenceNumber=sequenceNumber;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public static void setSequenceNumber(int sequenceNumber) {
		sequenceNumber = sequenceNumber;
	}
	
	public void setColor() {
		//colors of flags cannot be changed
	}
	
	public String toString() { 
		return ("Flag: "+super.toString()+" seqNum="+this.sequenceNumber);
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int[] xPoints=new int[3];
		int[] yPoints=new int[3];
		
		//top point
		xPoints[0]=(int) (this.getLocation().getX());
		yPoints[0]=(int) (this.getLocation().getY())+this.getSize()/2;
		
		//bot left point
		xPoints[1]=(int) (this.getLocation().getX())-this.getSize()/2;
		yPoints[1]=(int) (this.getLocation().getY())-this.getSize()/2;
		
		//bot right point
		xPoints[2]=(int) (this.getLocation().getX())+this.getSize()/2;
		yPoints[2]=(int) (this.getLocation().getY())-this.getSize()/2;
		
		g.fillPolygon(xPoints, yPoints, 3);
		g.setColor(this.getColor());
		g.drawString(Integer.toString(this.sequenceNumber), (int) (this.getLocation().getX()), (int) (this.getLocation().getY()));
	}
}
