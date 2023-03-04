package com.mycompany.a1;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

//TODO finish exit yes no prompt

public class Game extends Form{
	private GameWorld gw;
	private boolean xLast;

	
	public Game() {
		gw=new GameWorld();
		gw.init();
		play();
		xLast=false;
	}
	
	private void play() {
		//code that goes here is in appendix
		Label myLabel=new Label("Enter a command:");
		this.addComponent(myLabel);
		final TextField myTextField=new TextField();
		this.addComponent(myTextField);
		this.show();
		
		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String sCommand=myTextField.getText().toString();
				myTextField.clear();
				if(sCommand.length()!=0) {
					switch (sCommand.charAt(0)) {
					case 'a':
						gw.accelerate();
						xLast=false;
						break;
					case 'b':
						gw.brake();
						xLast=false;
						break;
					case 'l':
						gw.left();
						xLast=false;
						break;
					case 'r':
						gw.right();
						xLast=false;
						break;
					case 'f':
						gw.food();
						xLast=false;
						break;
					case 'g':
						gw.get();
						xLast=false;
						break;
					case 't':
						gw.tick();
						xLast=false;
						break;
					case 'd':
						gw.display();
						xLast=false;
						break;
					case 'm':
						gw.map();
						xLast=false;
						break;
					case 'x':
						xLast=true;
						System.out.println("Are you sure you want to exit? (enter 'y' or 'n'):");
						break;
					case 'y':
						if (xLast) {
							System.exit(0);
						}
					case 'n':
						xLast=false;
						System.out.println("Game continuing");
					case '1':
						gw.flagCollision(1);
						xLast=false;
						break;
					case '2':
						gw.flagCollision(2);
						xLast=false;
						break;
					case '3':
						gw.flagCollision(3);
						xLast=false;
						break;
					case '4':
						gw.flagCollision(4);
						xLast=false;
						break;
					}
					
					
					
				}
			}
		
		});
		
	}
	
}
