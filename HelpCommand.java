package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand  extends Command{
	private GameWorld gw;
	
	public HelpCommand(GameWorld gw) {
		super("Help");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.help();
	}
}
