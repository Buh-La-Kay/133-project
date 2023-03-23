package com.mycompany.a1;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Label;

public class ScoreView extends Container implements Observer{

	public ScoreView(GameWorld gw) {
		gw.addObserver(this);
		//add labels here
		Label timeLabel=new Label("Time: "+gw.getClockTime());
		Label lifeLabel=new Label("Lives Left: "+gw.getLives());
		
		//iterator to get ant information
		IIterator theElements=gw.getGameCollection().getIterator();
		int lastFlag=0;
		int food=0;
		int health=0;
		while (theElements.hasNext()) {
			GameObject go=theElements.getNext();
			if(go instanceof Ant) {
				lastFlag=((Ant) go).getLastFlagReached();
				food=((Ant) go).getFoodLevel();
				health=((Ant) go).getHealthLevel();
			}
		}
		Label flagLabel=new Label("Last Flag Reached "+lastFlag);
		Label foodLabel=new Label("Food Level: "+food);
		Label healthLabel=new Label("Health Level: "+health);
		Label soundLabel=new Label("Sound: "+gw.getSound());
		
		this.add(timeLabel);
		this.add(lifeLabel);
		this.add(flagLabel);
		this.add(foodLabel);
		this.add(healthLabel);
		this.add(soundLabel);
	}
	
	public ScoreView() {
		
	}
	
	@Override
	public void update(Observable observable, Object data) {
		//update labels from the game/ant state data
		
	}
	

}
