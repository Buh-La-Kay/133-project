package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

public class MapView extends Container implements Observer{
	private GameWorld gw;
	public MapView(GameWorld gw) {
		gw.addObserver(this);
		this.gw=gw;
	}
	
	public MapView() {
		//in slides, not sure if necessary
	}
	
	@Override
	public void update(Observable observable, Object data) {
		this.repaint();
		((GameWorld) observable).map();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		IIterator theElements=gw.getGameCollection().getIterator();
		Point p=new Point(this.getParent().getX(), this.getParent().getY());
		while (theElements.hasNext()) {
			GameObject go=theElements.getNext();
			go.draw(g, p);
		}
	}
	
	

}
