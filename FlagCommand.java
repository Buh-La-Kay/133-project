package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class FlagCommand extends Command{
	private GameWorld gw;
	
	public FlagCommand(GameWorld gw) {
		super("Collide With Flag");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		int flagNum=0;
		Command cOk=new Command("Ok");
		Command cCancel=new Command("Cancel");
		Command[] cmds=new Command[] {cOk, cCancel};
		TextField myTextField=new TextField();
		Command c=Dialog.show("Enter the number of the flag you have collided with(1-4)", myTextField, cmds);
		try {
			flagNum=Integer.parseInt(myTextField.getText());
			if(flagNum<5 && flagNum>0) {
				gw.flagCollision(flagNum);
			}
			else {
				Dialog.show("Invalid Flag Number", "Try again with a number between 1 and 4", "Ok", null);
			}
			
		}
		catch(NumberFormatException e) {
			Dialog.show("Invalid Entry", "Try again with a number between 1 and 4", "Ok", null);
		}
	}

}
