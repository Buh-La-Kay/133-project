package com.mycompany.a3;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;



public class Game extends Form implements Runnable{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;

	
	public Game() {
		this.setLayout(new BorderLayout());
		gw=new GameWorld();
		
		//register observers
		mv=new MapView(gw);
		sv= new ScoreView(gw);
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		
		//commands
		AboutCommand aboutCmd=new AboutCommand(gw);
		AccelerateCommand accelerateCmd=new AccelerateCommand(gw);
		BrakeCommand brakeCmd=new BrakeCommand(gw);
		DisplayCommand displayCmd=new DisplayCommand(gw);
		ExitCommand exitCmd=new ExitCommand(gw);
		FlagCommand flagCmd=new FlagCommand(gw);
		FoodCommand foodCmd=new FoodCommand(gw);
		GetCommand getCmd=new GetCommand(gw);
		HelpCommand helpCmd=new HelpCommand(gw);
		LeftCommand leftCmd=new LeftCommand(gw);
		MapCommand mapCmd=new MapCommand(gw);
		RightCommand rightCmd=new RightCommand(gw);
		SoundCommand soundCmd=new SoundCommand(gw);
		TickCommand tickCmd=new TickCommand(gw);
		
		
		//title stuff
		Toolbar myToolbar=new Toolbar();
		setToolbar(myToolbar);
		myToolbar.setTitle("StartToFinish Game");
		myToolbar.addCommandToRightBar(helpCmd);
		
		
		//side menu stuff
		myToolbar.addCommandToSideMenu(accelerateCmd);
		myToolbar.addCommandToSideMenu(aboutCmd);
		myToolbar.addCommandToSideMenu(exitCmd);
		CheckBox soundCheckBox=new CheckBox("Sound");
		soundCheckBox.setCommand(soundCmd);
		myToolbar.addComponentToSideMenu(soundCheckBox);
		
		
		//center stuff
		mv.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(255, 0, 0)));
		this.add(BorderLayout.CENTER, mv);
		
		
		//top stuff
		sv.setLayout(new GridLayout(1,6)); 
		this.add(BorderLayout.NORTH, sv);
		
		
		//left stuff
		Container leftContainer=new Container();
		leftContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setPadding(Component.TOP, 50);
		leftContainer.getAllStyles().setBgTransparency(255);
		leftContainer.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.GRAY));
		Button accelerateButton=new MyButton(accelerateCmd);
		Button leftButton=new MyButton(leftCmd);
		leftContainer.add(accelerateButton);
		leftContainer.add(leftButton);
		this.add(BorderLayout.WEST, leftContainer);
		
		
		//right stuff 
		Container rightContainer=new Container();
		rightContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getAllStyles().setPadding(Component.TOP, 50);
		rightContainer.getAllStyles().setBgTransparency(255);
		rightContainer.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.GRAY));
		Button brakeButton=new MyButton(brakeCmd);
		Button rightButton=new MyButton(rightCmd);
		rightContainer.add(brakeButton);
		rightContainer.add(rightButton);
		this.add(BorderLayout.EAST, rightContainer);
		
		
		//south stuff
		Container southContainer=new Container();
		southContainer.setLayout(new FlowLayout(Component.CENTER));
		southContainer.getAllStyles().setBgTransparency(255);
		southContainer.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		southContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.GRAY));
		Button flagButton=new MyButton(flagCmd);
		Button getButton=new MyButton(getCmd);
		Button foodButton=new MyButton(foodCmd);
		Button tickButton=new MyButton(tickCmd);
		southContainer.add(flagButton);
		southContainer.add(getButton);
		southContainer.add(foodButton);
		southContainer.add(tickButton);
		this.add(BorderLayout.SOUTH, southContainer);
		
		
		//keybinds
		this.addKeyListener('a', accelerateCmd);
		this.addKeyListener('b', brakeCmd);
		this.addKeyListener('l', leftCmd);
		this.addKeyListener('r', rightCmd);
		this.addKeyListener('f', foodCmd);
		this.addKeyListener('g', getCmd);
		this.addKeyListener('t', tickCmd);
		this.addKeyListener('m', mapCmd);

		
		//timer stuff
		UITimer timer=new UITimer(this);
		timer.schedule(20, true, this);
		
		
		this.show();
		gw.setHeight(mv.getHeight());
		gw.setWidth(mv.getWidth());
		gw.setOrigin(new Point (mv.getX(),mv.getY()));
		gw.init();
		
		

		

		
		
		
	}


	@Override
	public void run() {
		gw.tick();
		
	}
}
