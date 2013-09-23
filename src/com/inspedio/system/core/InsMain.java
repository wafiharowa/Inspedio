package com.inspedio.system.core;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.inspedio.entity.InsState;
import com.inspedio.enums.ScreenOrientation;
import com.inspedio.system.helper.InsLoader;
import com.inspedio.system.helper.InsSave;


public abstract class InsMain extends MIDlet{

	protected InsGame game;
	
	public InsMain()
	{
		try
		{
			this.init();
			this.game = InsGame.getInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Implement this by calling init(InitialState, Loader, SaveLoad, Mode) with desired value
	 */
	protected abstract void init();
	
	protected void init(InsState InitialState, InsLoader Loader, InsSave SaveLoad, ScreenOrientation Mode, int FPS, int MaxFrameSkip){
		InsGame.init(this, InitialState, FPS, MaxFrameSkip, Loader, SaveLoad, Mode);
	}
	
	protected void init(InsState InitialState, InsLoader Loader, InsSave SaveLoad, ScreenOrientation Mode){
		this.init(InitialState, Loader, SaveLoad, Mode, 25, 3);
	}
	
	protected void startApp() throws MIDletStateChangeException {
		game.start();
		Display display = Display.getDisplay(this);
		display.setCurrent(game.canvas);
		System.out.println("Application Started");
	}
	
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
	}

	protected void pauseApp() {
		InsGlobal.pauseGame();
	}
}
