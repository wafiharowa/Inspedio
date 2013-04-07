package sample;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import sample.whackmole.WhackState;

import com.inspedio.core.InsGame;
import com.inspedio.core.InsLoader;
import com.inspedio.helper.InsSave;

public class Main extends MIDlet{
	
	private static final int FPS = 25;
	private static final int MaxFrameSkip = 3;
	
	protected InsGame game;

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		try
		{
			this.game = new InsGame(this, new WhackState(), FPS, MaxFrameSkip, new InsLoader(), new InsSave());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void pauseApp() {
		
	}

	protected void startApp() throws MIDletStateChangeException {
		
	}

}
