/*
 * Bismillahirrahmanirrahim
 * In the Name of Allah, The Most Gracious, The Ever Merciful
 */

package com.inspedio.system.helper;


/**
 * InsStats is a helper class for calculating Performance
 */
public class InsStats {

	private static InsStats instance = null;
	
	/**
	 * time stamp of beginning of new second
	 */
	public long gameTimeMark;
	/**
	 * how much second passed
	 */
	public long timeCount;
	/**
	 * how much update have been done
	 */
	public long updateCount;
	/**
	 * how much render have been done
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
	 * Current Update per Second
	 */
	public long currentUPS = 0;
	/**
	 * Current Frame per Second
	 */
	public long currentFPS = 0;
	/**
	 * Current Update Time (time spent for updating)
	 */
	public long currentUpdateTime = 0;
	/**
	 * Current Render Time (time spent for rendering)
	 */
	public long currentRenderTime = 0;
	/**
	 * Current GameTime (in Second)
	 */
	public long currentGameTime = 0;
	/**
	 * Current SleepTime (in miliseconds)
	 */
	public long currentSleepTime = 0;
	/**
	 * Current FrameSkip
	 */
	public long currentFrameSkip = 0;
	
	private InsStats(){
		this.timeCount = 0;
		this.gameTimeMark = System.currentTimeMillis();
		this.realFPS = 0;
		this.resetStats();
	}
	
	public static InsStats getInstance(){
		if(instance == null){
			instance = new InsStats();
		}
		return instance;
	}
	
	public void resetStats(){
		this.updateCount = 0;
		this.renderCount = 0;
		this.updateTime = 0;
		this.renderTime = 0;
		this.sleepCount = 0;
		this.frameSkipCount = 0;
	}
	
	public void calculateStats(){
		this.currentUPS = this.updateCount;
		this.currentFPS = this.renderCount;
		this.currentUpdateTime = this.updateTime;
		this.currentRenderTime = this.renderTime;
		this.currentGameTime = this.timeCount;
		this.currentSleepTime = this.sleepCount;
		this.currentFrameSkip = this.frameSkipCount;
	}
}
