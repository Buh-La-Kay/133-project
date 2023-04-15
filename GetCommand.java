package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class GetCommand  extends Command{
	private GameWorld gw;
	
	public GetCommand(GameWorld gw) {
		super("Collide With Spider");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.get();
	}
}
