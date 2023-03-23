package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class FlagCommand extends Command{
	private GameWorld gw;
	
	public FlagCommand(GameWorld gw) {
		super("Collide With Flag");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		//TODO refactor flag collision with numbers
		gw.flagCollision(0);
	}

}
