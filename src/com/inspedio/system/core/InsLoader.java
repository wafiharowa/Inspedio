package com.inspedio.system.core;


import com.inspedio.entity.InsState;

/**
 * InsLoader handles Loading State progressively.<br>
 * 
 * @author Hyude
 * @version 1.0
 */
public abstract class InsLoader extends InsState implements Runnable{

	/**
	 * Pointer to Thread
	 */
	public Thread thread;
	/**
	 * Current Progress displayed.<br>
	 * It is used to makes progress smoothly increase and not necessarily the actual progress.
	 */
	public int currentProgress;
	/**
	 * How much increment maximum per frame (so it animates smoothly when progressing) 
	 */
	public int maxIncrement;
	
	public InsLoader()
	{
		super();
	}
	
	public void init(){
		this.currentProgress = 0;
		this.maxIncrement = 5;
		InsGlobal.loadProgress = 0;
		this.progressChanged(this.currentProgress);
	}
	
	public void start()
	{
		this.currentProgress = 0;
		InsGlobal.loadProgress = 0;
		InsGlobal.keys.resetKeyState();
		this.thread  = new Thread(this);
		this.thread.start();
	}
	
	public void update()
	{
		super.update();
		if(this.currentProgress == 100)
		{
			this.progressChanged(this.currentProgress);
			this.finishLoad();
			InsGlobal.loadProgress = 0;
		}
		else
		{
			int incr = Math.max((InsGlobal.loadProgress - this.currentProgress), 0);
			if(incr > 0)
			{
				this.currentProgress += Math.min(this.maxIncrement, incr);
			}
			this.progressChanged(this.currentProgress);
		}
	}
	
	/**
	 * Changes loading depend on current progress
	 */
	public abstract void progressChanged(int CurrentProgress);
	
	public void finishLoad()
	{
		InsGlobal.game.state = null;
		InsGlobal.game.state = InsGlobal.game.requestedState;
		InsGlobal.game.state.finishCreate();
		InsGlobal.game.requestedState = null;
	}

	public void run()
	{
		InsGlobal.loadProgress = 20;
		InsGlobal.cache.clearCache();
		System.gc();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		InsGlobal.game.requestedState.create();
		InsGlobal.game.requestedState.finishCreate();
	}
	
}
