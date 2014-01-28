package com.inspedio.system;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.inspedio.entity.InsState;
import com.inspedio.enums.LogLevel;
import com.inspedio.enums.ScreenOrientation;
import com.inspedio.system.core.InsGame;
import com.inspedio.system.core.InsGlobal;
import com.inspedio.system.core.InsLoader;
import com.inspedio.system.defaults.DefaultLoader;
import com.inspedio.system.defaults.DefaultPause;
import com.inspedio.system.defaults.DefaultSaveLoad;
import com.inspedio.system.helper.InsLogger;
import com.inspedio.system.helper.InsPause;
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
	
	protected void init(InsState InitialState, ScreenOrientation Mode){
		this.init(InitialState, new DefaultLoader(), new DefaultSaveLoad(), Mode);
	}
	protected void init(InsState InitialState, InsLoader Loader, InsSave SaveLoad, ScreenOrientation Mode){
		this.init(InitialState, Loader, SaveLoad, Mode, 25, 3);
	}
	
	protected void init(InsState InitialState, InsLoader Loader, InsSave SaveLoad, ScreenOrientation Mode, int FPS, int MaxFrameSkip){
		this.init(InitialState, Loader, SaveLoad, new DefaultPause(), Mode, FPS, MaxFrameSkip);
	}
	
	protected void init(InsState InitialState, InsLoader Loader, InsSave SaveLoad, InsPause Pause, ScreenOrientation Mode, int FPS, int MaxFrameSkip){
		this.init(InitialState, Loader, SaveLoad, Pause, Mode, FPS, MaxFrameSkip, false);
	}
	
	protected void init(InsState InitialState, InsLoader Loader, InsSave SaveLoad, InsPause Pause, ScreenOrientation Mode, int FPS, int MaxFrameSkip, boolean enablePause){
		InsGame.init(this, InitialState, FPS, MaxFrameSkip, Loader, SaveLoad, Pause, Mode, enablePause);
	}
	
	
	protected void startApp() throws MIDletStateChangeException {
		game.start();
		Display display = Display.getDisplay(this);
		display.setCurrent(game.canvas);
		InsLogger.writeLog("Canvas Ready", LogLevel.PROCESS);
		InsLogger.writeLog("Application Started", LogLevel.SYSTEM);
	}
	
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
	}

	protected void pauseApp() {
		InsGlobal.pauseGame();
		InsLogger.writeLog("Game Paused", LogLevel.PROCESS);
	}
}
