package sample.runningman;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.inspedio.core.InsGame;
import com.inspedio.core.InsLoader;
import com.inspedio.helper.InsSave;

public class Main extends MIDlet{

	private static final int FPS = 25;
	private static final int MaxFrameSkip = 3;
	
	protected InsGame game;
	
	public Main()
	{
		try
		{
			this.game = new InsGame(this, new MenuState(), FPS, MaxFrameSkip, new InsLoader(), new InsSave("Runner"), InsGame.LOCK_PORTRAIT);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
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
		
	}

	

}
