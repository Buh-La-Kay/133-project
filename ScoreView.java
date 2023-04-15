package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Label;

public class ScoreView extends Container implements Observer{
	private Label timeLabel;
	private Label lifeLabel;
	private Label flagLabel;
	private Label foodLabel;
	private Label healthLabel;
	private Label soundLabel;
	
	
	public ScoreView(GameWorld gw) {
		gw.addObserver(this);
		this.timeLabel=new Label("Time: "+gw.getClockTime());
		this.lifeLabel=new Label("Lives Left: "+gw.getLives());
		
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
		this.flagLabel=new Label("Last Flag Reached "+lastFlag);
		this.foodLabel=new Label("Food Level: "+food);
		this.healthLabel=new Label("Health Level: "+health);
		this.soundLabel=new Label("Sound: "+gw.getSound());
		
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
		this.timeLabel.setText("Time: "+((GameWorld) observable).getClockTime());
		this.lifeLabel.setText("Lives Left: "+ ((GameWorld) observable).getLives());
		IIterator theElements=((GameWorld) observable).getGameCollection().getIterator();
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
		
		this.flagLabel.setText("Last Flag Reached: "+lastFlag);
		this.foodLabel.setText("Food Level: "+ food);
		this.healthLabel.setText("Health Level: "+ health);
		this.soundLabel.setText("Sound: "+((GameWorld) observable).getSound());
		
	}
	

}
