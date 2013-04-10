package com.inspedio.core;

import java.util.Random;

import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

import com.inspedio.basic.InsState;
import com.inspedio.helper.InsCache;
import com.inspedio.helper.InsCamera;
import com.inspedio.helper.InsKeys;
import com.inspedio.helper.InsPointer;
import com.inspedio.helper.InsSave;
import com.inspedio.helper.InsSound;

/**
 * This class consist of important global variables and constants that is used on GameLoop<br>
 * also global method which can be use from any class such as switchState<br>
 * 
 * @author Hyude
 * @version 1.0
 */

public class InsGlobal{
	/**
	 * Pointer to <code>MIDlet</code> object
	 */
	public static MIDlet midlet;
	/**
	 * Pointer to <code>InsGame</code> object
	 */
	public static InsGame game;
	/**
	 * Pointer to <code>InsCanvas</code> object
	 */
	public static InsCanvas canvas;
	/**
	 * Pointer to <code>Graphic</code> object, useful for drawing manually
	 */
	public static Graphics graphic;
	/**
	 * Pointer to <code>InsKeys</code> object, used for detecting key event
	 */
	public static InsKeys keys;
	/**
	 * Pointer to <code>InsPointer</code> object, used for detecting key event
	 */
	public static InsPointer pointer;
	/**
	 * A reference to a <code>InsCamera</code> object.
	 */
	public static InsCamera camera;
	/**
	 * A reference to a <code>InsAssets</code> object.
	 */
	public static InsCache cache;
	/**
	 * <code>InsGlobal.saves</code> is a generic container for storing
	 * InsSave so you can access them whenever you want
	 */
	public static InsSave save;
	/**
	 * Randomizer for general random usage
	 */
	public static Random randomizer;
	/**
	 * Current BGM played
	 */
	public static InsSound bgm = null;
	/**
	 * True if the game is currently PAUSED
	 */
	public static boolean paused = false;
	/**
	 * True if the game is currently hidden
	 */
	public static boolean hidden;
	/**
	 * Ranged from 0 to 100, useful for implementing Loading State and general progress
	 */
	public static int loadProgress;
	/**
	 * How much cycle have passed
	 */
	public static long cycleCount;
	/**
	 * The width of screen in game pixels
	 */
	public static int screenWidth;
	/**
	 * The height of screen in game pixels
	 */
	public static int screenHeight;
	
	public static int level;
	public static int stage;
	public static int score;
	
	/**
	 * Whether device has touch Screen support
	 */
	public static boolean hasTouchScreen;
	
	/**
	 * Whether BGM muted or not
	 */
	public static boolean muteBGM;
	/**
	 * Whether SFX muted or not
	 */
	public static boolean muteSFX;
	/**
	 * Set this to TRUE to display FPS
	 */
	public static boolean displayFPS = false;
	/**
	 * Current Update per Second
	 */
	public static long currentUPS = 0;
	/**
	 * Current Frame per Second
	 */
	public static long currentFPS = 0;
	/**
	 * Current Update Time (time spent for updating)
	 */
	public static long currentUpdateTime = 0;
	/**
	 * Current Render Time (time spent for rendering)
	 */
	public static long currentRenderTime = 0;
	/**
	 * Current GameTime (in Second)
	 */
	public static long currentGameTime = 0;
	/**
	 * Current SleepTime (in miliseconds)
	 */
	public static long currentSleepTime = 0;
	/**
	 * Current FrameSkip
	 */
	public static long currentFrameSkip = 0;
	
	
	/**
	 * Initiate Global Variables
	 */
	public static void initGlobal()
	{
		paused = false;
		hidden = false;
		level = 0;
		stage = 0;
		score = 0;
		save = new InsSave();
		muteBGM = false;
		muteSFX = false;
		cycleCount = 0;
	}
	
	/**
	 * Request a reset of the current game state.
	 */
	public static void resetState()
	{
		InsGlobal.game.state.reset();
	}
	
	/**
	 * Like hitting the reset button on a game console, this will re-launch the game as if it just started.
	 */
	public static void resetGame()
	{
		InsGlobal.game.requestedReset = true;
	}
	
	/**
	 * Switch from the current game state to the one specified here.<br>
	 * set useLoader to TRUE if you want to switch into heavy assets state/<br>
	 * If you want to use your own loader, you can change <code>InsGlobal.loader</code> or define it during constructing <code>InsGame</code><br>
	 * 
	 */
	public static void switchState(InsState State, boolean useLoader)
	{
		InsGlobal.game.requestedState = State;
		InsGlobal.game.switchStateRequested = true;
		InsGlobal.game.switchStateUseLoader = useLoader;
	}
	
	/**
	 * Set BGM to Current Sound. If BGM already set, nothing is done
	 * 
	 * @return TRUe if BGM succesfully set, FALSE if BGM already set, or unable to set
	 */
	public static boolean setBGM(String audioPath, String audioEncoding, int audioType)
	{
		if(InsGlobal.bgm == null)
		{
			InsGlobal.bgm = new InsSound(audioPath, audioEncoding, audioType);
			return true;
		}
		else
		{
			if(InsGlobal.bgm.filepath.equals(audioPath))
			{
				return false;
			}
			else
			{
				InsGlobal.unsetBGM();
				InsGlobal.bgm = new InsSound(audioPath, audioEncoding, audioType);
				return true;
			}
		}
	}
	
	/**
	 * Stop and destroy current BGM
	 */
	public static void unsetBGM()
	{
		if(InsGlobal.bgm != null)
		{
			InsGlobal.bgm.stop();
			InsGlobal.bgm.destroy();
			InsGlobal.bgm = null;
		}
	}
	
	/**
	 * Start current BGM
	 */
	public static void startBGM()
	{
		if(InsGlobal.bgm != null)
		{
			InsGlobal.bgm.start();
		}
	}
	
	/**
	 * Start current BGM
	 */
	public static void stopBGM()
	{
		if(InsGlobal.bgm != null)
		{
			InsGlobal.bgm.stop();
		}
	}
	
	/**
	 * Reset current BGM
	 */
	public static void resetBGM()
	{
		if(InsGlobal.bgm != null)
		{
			InsGlobal.bgm.reset();
		}
	}
	
	/**
	 * Called by <code>InsGame</code> to set up <code>InsGlobal</code> during <code>InsGame</code>'s constructor.
	 */
	public static void init(InsGame Game,int Width,int Height)
	{
		InsGlobal.game = Game;
		InsGlobal.screenWidth = Width;
		InsGlobal.screenHeight = Height;	
	}
	
	/**
	 * Called whenever the game is reset, doesn't have to do quite as much work as the basic initialization stuff.
	 */
	public static void reset()
	{
		InsGlobal.initGlobal();
	}
	
	/**
	 * Pause and Hide the Game. Automatically called if application becomes hidden
	 */
	public static void hideGame()
	{
		InsGlobal.hidden = true;
		InsGlobal.stopBGM();
		InsGlobal.pauseGame();
	}
	
	/**
	 * Show and Resume the Game. Automatically called if application becomes visible after paused
	 */
	public static void showGame()
	{
		InsGlobal.hidden = false;
		InsGlobal.startBGM();
	}
	
	/**
	 * Pause the Game.
	 */
	public static void pauseGame()
	{
		InsGlobal.paused = true;
		InsGlobal.game.state.pause();
	}
	
	/**
	 * Resume the Game.
	 */
	public static void resumeGame()
	{
		InsGlobal.paused = false;
		InsGlobal.game.state.resume();
	}
	
	/**
	 * Action executed when LeftSoftKey Pressed
	 */
	public static void onLeftSoftKey()
	{
		InsGlobal.game.leftSoftKeyPressed = true;
	}
	
	/**
	 * Action executed when RightSoftKey Pressed
	 */
	public static void onRightSoftKey()
	{
		InsGlobal.game.rightSoftKeyPressed = true;
	}
	
}