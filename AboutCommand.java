package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand  extends Command{
	private GameWorld gw;
	
	public AboutCommand(GameWorld gw) {
		super("About");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.about();
	}
}
