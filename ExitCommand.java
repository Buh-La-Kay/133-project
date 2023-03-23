package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand  extends Command{
	private GameWorld gw;
	
	public ExitCommand(GameWorld gw) {
		super("Exit");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		//code to exit and prompt yes/no dialogue box
		Boolean bOk=Dialog.show("Confirm Quit", "Are you sure you want to quit?", "Yes", "No");
		if(bOk) {
			Display.getInstance().exitApplication();
		}
	}
}
