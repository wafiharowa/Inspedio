package com.inspedio.system.core;

import java.util.Random;

import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

import com.inspedio.entity.InsState;
import com.inspedio.entity.primitive.InsSound;
import com.inspedio.enums.AudioEncode;
import com.inspedio.enums.AudioType;
import com.inspedio.enums.InputType;
import com.inspedio.enums.ScreenOrientation;
import com.inspedio.system.helper.InsCache;
import com.inspedio.system.helper.InsCamera;
import com.inspedio.system.helper.InsKeys;
import com.inspedio.system.helper.InsPause;
import com.inspedio.system.helper.InsPointer;
import com.inspedio.system.helper.InsSave;
import com.inspedio.system.helper.InsStats;

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
	 * A reference to a <code>InsAssets</code> instance.
	 */
	public static InsCache cache;
	/**
	 * A reference to a <code>InsStats</code> instance
	 */
	public static InsStats stats;
	/**
	 * <code>InsGlobal.saves</code> is a generic container for storing
	 * InsSave so you can access them whenever you want
	 */
	public static InsSave save;
	/**
	 * A reference to a <code>InsPause</code> instance
	 */
	public static InsPause pause;
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
	/**
	 * The middle X point of screen
	 */
	public static int middleX;
	/**
	 * The middle Y point of screen
	 */
	public static int middleY;
	/**
	 * The Orientation of SCreen, either LANDSCAPE or PORTRAIT
	 */
	public static ScreenOrientation screenOrientation;
	
	public static boolean isScreenRotated;
	
	
	public static int level;
	public static int stage;
	public static int score;
	
	/**
	 * Whether device has touch Screen support
	 */
	public static boolean hasTouchScreen;
	/**
	 * Whether device use touch or keypad
	 */
	public static InputType inputType;
	
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
	public static boolean displayFPS = true;
	/**
	 * Set this to FALSE to disable Global pause Behavior
	 */
	public static boolean enablePause = true;
	/**
	 * DO NOT Change this Value unless you have direct permission
	 */
	public static boolean useLogo = true;
	/**
	 * Set this to TRUE to enable Payment from Tequila
	 */
	public static boolean enablePaymentTequila = false;
	/**
	 * Marker for when Payment take application focus
	 */
	public static boolean onFocusPayment = false;	
	/**
	 * Set this to change FPS Display Color
	 */
	public static int FPS_COLOR = InsCanvas.COLOR_BLACK;
	/**
	 * Set this to change Background Color
	 */
	public static int BG_COLOR = InsCanvas.COLOR_WHITE;
	
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
	public static boolean setAudio(String audioPath, AudioEncode audioEncoding, AudioType audioType)
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
				InsGlobal.unsetAudio();
				InsGlobal.bgm = new InsSound(audioPath, audioEncoding, audioType);
				return true;
			}
		}
	}
	
	/**
	 * Stop and destroy current BGM
	 */
	public static void unsetAudio()
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
	public static void startAudio()
	{
		if(InsGlobal.bgm != null)
		{
			InsGlobal.bgm.start();
		}
	}
	
	/**
	 * Start current BGM
	 */
	public static void stopAudio()
	{
		if(InsGlobal.bgm != null)
		{
			InsGlobal.bgm.stop();
		}
	}
	
	/**
	 * Reset current BGM
	 */
	public static void resetAudio()
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
		InsGlobal.stopAudio();
		InsGlobal.pauseGame();
	}
	
	/**
	 * Show and Resume the Game. Automatically called if application becomes visible after paused
	 */
	public static void showGame()
	{
		InsGlobal.hidden = false;
		InsGlobal.startAudio();
	}
	
	/**
	 * Pause the Game.
	 */
	public static void pauseGame()
	{
		if(enablePause){
			InsGlobal.paused = true;
		}
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
	
	public static void exitGame(){
		InsGlobal.midlet.notifyDestroyed();
	}
	
	/**
	 * Action executed when LeftSoftKey Pressed
	 */
	public static void onLeftSoftKeyPressed()
	{
		InsKeys.leftSoftKeyPressed = true;
	}
	
	/**
	 * Action executed when RightSoftKey Pressed
	 */
	public static void onRightSoftKeyPressed()
	{
		InsKeys.rightSoftKeyPressed = true;
	}
	
}