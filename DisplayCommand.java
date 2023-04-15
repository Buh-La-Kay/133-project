package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class DisplayCommand  extends Command{
	private GameWorld gw;
	
	public DisplayCommand(GameWorld gw) {
		super("Display");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.display();
	}
}
