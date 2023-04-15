package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class FoodCommand  extends Command{
	private GameWorld gw;
	
	public FoodCommand(GameWorld gw) {
		super("Collide With Food Station");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.food();
	}
}
