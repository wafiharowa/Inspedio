package com.inspedio.core;

import java.util.Random;

import javax.microedition.midlet.MIDlet;
import com.inspedio.basic.InsState;
import com.inspedio.helper.InsCache;
import com.inspedio.helper.InsCamera;
import com.inspedio.helper.InsKeys;
import com.inspedio.helper.InsPointer;
import com.inspedio.helper.InsSave;

/**
 * InsGame is the core of Inspedio Engine Core (It's a coreception!).<br>
 * It contains basic game loop, state switching, frame stabilizer, generic event handler and other important but messy things to keep your game running<br>
 * This created once, and it will handle itself.

 * <b>DO NOT</b> override this class, unless you know what you're doing.
 * Override <code>InsState</code> and <code>InsObject</code> to implement your game logic
 * 
 * @author Hyude
 */
public class InsGame implements Runnable {
	
	/**
	 * When Game is locked only for Portrait Mode
	 */
	public static final int LOCK_PORTRAIT = 0x01;
	/**
	 * When Game is locked only for Landscape Mode
	 */
	public static final int LOCK_LANDSCAPE = 0x10;
	
	//public static final int ENABLE_BOTH = 0x11;
	
	/**
	 * Current game state.
	 */
	public InsState state;
	/**
	 * Current game canvas.
	 */
	public InsCanvas canvas;
	/**
	 * Default Loader State
	 */
	public InsLoader loader;
	/**
	 * Save Load helper
	 */
	public InsSave saveload;
	/**
	 * Whether the game object's basic initialization has finished yet
	 */
	public boolean ready;
	/**
	 * Set this to false to force close the game
	 */
	public boolean running;	
	/**
	 * If a state change was requested, the new state object is stored here until we switch to it
	 */
	public InsState requestedState;
	/**
	 * Total number of milliseconds elapsed since the game start
	 */
	public long totalTime;
	/**
	 * Time stamp marking when game start
	 */
	public long beginTime;
	/**
	 * How much update you want each second. Standard = 25 fps
	 */
	public int fps;
	/**
	 * Milliseconds of time per frame of game loop. 25 FPS = 40ms
	 */
	protected int framePeriod;
	/**
	 * Maximum render skipped
	 */
	protected int maxframeSkip;
	/**
	 * how much second passed
	 */
	public long secondCount;
	/**
	 * time stamp of beginning of new second
	 */
	public long gameTimeMark;
	/**
	 * how much update have done
	 */
	public long updateCount;
	/**
	 * how much render have done
	 */
	public long renderCount;
	/**
	 * how much render Sleep time
	 */
	public long sleepCount;
	/**
	 * how much Frame skipped per cycle
	 */
	public long frameSkipCount;
	/**
	 * How much milliseconds used for update process
	 */
	public long updateTime;
	/**
	 * How much milliseconds used for render process
	 */
	public long renderTime;
	/**
	 * real time FPS
	 */
	public long realFPS;
	/**
	 * A flag for keeping track of whether a game reset was requested or not.
	 */
	public boolean requestedReset = false;;
	/**
	 * Marker for detecting LeftsoftKey Press
	 */
	public boolean leftSoftKeyPressed = false;
	/**
	 * Marker for detecting RightSoftKey Press
	 */
	public boolean rightSoftKeyPressed = false;
	/**
	 * Marker for detecting SwitchState request
	 */
	public boolean switchStateRequested = false;
	/**
	 * Whether SwitchState should use Loader
	 */
	public boolean switchStateUseLoader = false;
	
	
	/**
	 * Initiate InsGame, and setup InsCanvas, InsGlobal, and InsState
	 * 
	 * @param	Midlet			Reference to Main Class (extends midlet), useful for exit and command
	 * @param	InitialState	Initial State to begin game (usually MenuState, or IntroState)
	 * @param	FPS				Frame Per Second. How much time the game updated each second (standard = 30 update per second)
	 * @param	MaxFrameSkip	Maximum frame skip allowed. Standard = 5
	 * @param	Loader			<code>InsLoader</code> used for Assets loading when creating state
	 * @param	SaveLoad		<code>InsSave</code> used for save load data into RecordStore	
	 * @param	DisplayMode		Either PORTRAIT or LANDSCAPE
	 */
	public InsGame(MIDlet Midlet, InsState InitialState, int FPS, int MaxFrameSkip, InsLoader Loader, InsSave SaveLoad, int DisplayMode)
	{
		initGame(DisplayMode);
		InsGlobal.midlet = Midlet;
		InsGlobal.save = SaveLoad;
		this.requestedState = InitialState;
		this.loader = Loader;
		this.loader.create();
		
		this.fps = FPS;
		this.framePeriod = (int) (1000 / this.fps);
		
		this.totalTime = 0;
		
		this.maxframeSkip = MaxFrameSkip;

		this.requestedReset = false;
		this.leftSoftKeyPressed = false;
		this.rightSoftKeyPressed = false;
		this.ready = false;
		this.running = true;
		
		this.sleepCount = 0;
		this.secondCount = 0;
		this.gameTimeMark = System.currentTimeMillis();
		this.updateCount = 0;
		this.renderCount = 0;
		this.frameSkipCount = 0;
		this.realFPS = 0;
		
		this.updateTime = 0;
		this.renderTime = 0;
		
		this.switchState(false);
	}
	
	private void initGame(int Mode)
	{
		this.canvas = new InsCanvas(this, Mode);
		InsGlobal.game = this;
		InsGlobal.canvas = this.canvas;
		InsGlobal.graphic = this.canvas.graphic;
		InsGlobal.keys = new InsKeys();
		InsGlobal.pointer = new InsPointer();	
		InsGlobal.camera = new InsCamera();
		InsGlobal.cache = new InsCache();
		InsGlobal.randomizer = new Random(System.currentTimeMillis());
		InsGlobal.initGlobal();
	}
	
	/**
	 * This method invoke thread and calling run. Call this method from your main
	 */
	public void start()
	{	
		// Start thread
		Thread t = new Thread(this);
		t.start();
		
		System.out.println("Thread Started");
	}
	
	/**
	 * This is game loop, which executed every frames
	 * To makes performance stable, the game loop implemented using
	 * constant game speed with maximum FPS paradigm
	 * It means, it will update X times per second constantly, while render whenever there are enough time
	 * So the rendering can be less depends on how heavy the update are
	 * 
	 * Note : You can set <code>exit</code> to force close the game anytime
	 */
	public void run() {
		long sleepTime;
		long elapsedTime = 0;
		int quotaTime = this.framePeriod;	// Time allocated in current cycle
		int diffTime = 0;					// Positive if process on time. Negative when late
		int frameSkipped = 0;
		this.beginTime = System.currentTimeMillis();	
		
		/*
		while(this.running){
			try{
					this.beginTime = System.currentTimeMillis();
					
					if(quotaTime > 0){
						this.update();
						this.render();
						this.nextCycle();
					}
						
					elapsedTime = System.currentTimeMillis() - this.beginTime;
					quotaTime += this.framePeriod - diffTime;
					
					
					
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
		
		while(this.running)
		{
			try
			{
				this.beginTime = System.currentTimeMillis();
				if(frameSkipped == 0)
				{
					this.update();
					this.render();
					this.nextCycle();
				}
				else
				{
					if(frameSkipped > 1)
					{
						this.frameSkipCount += frameSkipped;
					}
					this.render();
					this.nextCycle();
				}
				
				
				elapsedTime = System.currentTimeMillis() - this.beginTime;
				sleepTime = this.framePeriod - elapsedTime;
				
				if(sleepTime > 0)
				{
					// if sleeptime > 0, mean we have time to rest
					// sleep thread is good to save battery
					try
					{
						this.sleepCount += sleepTime;
						Thread.sleep(sleepTime);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				
				frameSkipped = 0;
				// if sleeptime < 0, means the cycle took too much time. We need to catch up
				while((sleepTime < 0) &&(frameSkipped < this.maxframeSkip))
				{
					// update without rendering
					this.update();
					frameSkipped++;
					sleepTime += this.framePeriod;
					this.nextCycle();
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Game update involving in updateInput from user, and calling update for current game state
	 */
	public void update()
	{
		try
		{
			long curtime = System.currentTimeMillis();
			this.updateKeyState();
			this.updatePointerState();
			if(!this.state.deleted)
			{
				if(this.switchStateRequested)
				{
					this.switchState(this.switchStateUseLoader);
					this.switchStateRequested = false;
					this.switchStateUseLoader = false;
				}
				if(this.leftSoftKeyPressed)
				{
					this.state.onLeftSoftKey();
					this.leftSoftKeyPressed = false;
				}
				if(this.rightSoftKeyPressed)
				{
					this.state.onRightSoftKey();
					this.rightSoftKeyPressed = false;
				}
				this.state.preUpdate();
				this.state.update();
				this.state.postUpdate();
			}
			this.updateCount++;
			this.updateTime += (System.currentTimeMillis() - curtime);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Update KeyEvent State (Pressed, Released, etc)
	 */
	public void updateKeyState()
	{
		InsGlobal.keys.updateKeyState(InsGlobal.canvas.getKeyStates());
	}
	
	/**
	 * Update PointerEvent State (Pressed, Released, etc)
	 */
	public void updatePointerState()
	{
		InsGlobal.pointer.updatePointerState();
	}
	
	/**
	 * rendering all needed graphic for current gamestate
	 */
	public void render()
	{
		try
		{
			long curtime = System.currentTimeMillis();
			this.canvas.clearScreen();
			if(!this.state.deleted)
			{
				this.state.draw();
			}
			if(InsGlobal.displayFPS)
			{
				this.canvas.drawFPS();
			}
			this.canvas.flushGraphics();
			this.renderCount++;
			this.renderTime += (System.currentTimeMillis() - curtime);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Switch state into given state.
	 * If useLoader is TRUE, didn't create the state (let the loader create it)
	 */
	public void switchState(boolean useLoader)
	{
		if(useLoader)
		{
			this.state.destroy();
			this.state = null;
			this.loader.init();
			this.state = this.loader;
		}
		else
		{
			this.requestedState.create();
			this.requestedState.finishCreate();
			this.state = this.requestedState;
			this.requestedState = null;
		}
	}
	
	/**
	 * Attempt to go to next cycle
	 * Calculating scheduled time, realFPS, etc
	 * 
	 * Displaying current UPS, FPS, and GameTime
	 */
	protected void nextCycle()
	{
		InsGlobal.cycleCount++;
		long curtime = System.currentTimeMillis();
		
		if((curtime - this.gameTimeMark) >= 1000)
		{
			this.gameTimeMark += 1000;
			this.secondCount++;
			
			InsGlobal.currentUPS = this.updateCount;
			InsGlobal.currentFPS = this.renderCount;
			InsGlobal.currentUpdateTime = this.updateTime;
			InsGlobal.currentRenderTime = this.renderTime;
			InsGlobal.currentGameTime = this.secondCount;
			InsGlobal.currentSleepTime = this.sleepCount;
			InsGlobal.currentFrameSkip = this.frameSkipCount;
			
			this.updateCount = 0;
			this.renderCount = 0;
			this.updateTime = 0;
			this.renderTime = 0;
			this.sleepCount = 0;
			this.frameSkipCount = 0;
		}
	}
	
	
}
