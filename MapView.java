package com.mycompany.a1;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;

public class MapView extends Container implements Observer{
	
	public MapView(GameWorld gw) {
		gw.addObserver(this);
	}
	
	public MapView() {
		//in slides, not sure if necessary
	}
	
	@Override
	public void update(Observable observable, Object data) {
		//code here to call the method in GameWorld that output the game object information to the console
		
	}
	
	

}
