package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

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
}
