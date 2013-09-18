package com.inspedio.system.helper;


import javax.microedition.lcdui.Graphics;

import com.inspedio.entity.InsState;
import com.inspedio.system.core.InsGlobal;

/**
 * InsLoader handles Loading State progressively.<br>
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsLoader extends InsState implements Runnable{

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
	
	public void create()
	{
		this.currentProgress = 0;
		this.maxIncrement = 5;
		InsGlobal.loadProgress = 0;
		this.showProgress();
	}
	
	public void init()
	{
		this.currentProgress = 0;
		InsGlobal.loadProgress = 0;
		InsGlobal.keys.resetKeyState();
		this.thread  = new Thread(this);
		this.thread.start();
	}
	
	public void preUpdate()
	{
		super.preUpdate();
	}
	
	public void update()
	{
		super.update();
		if(this.currentProgress == 100)
		{
			this.showProgress();
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
			this.showProgress();
		}
	}
	
	public void showProgress()
	{
		
	}
	
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
		//InsGlobal.cache.clearCache();
		System.gc();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		InsGlobal.game.requestedState.create();
	}
	
	public void draw()
	{
		InsGlobal.graphic.setColor(0x000000);
		InsGlobal.graphic.drawRect(0, 0, InsGlobal.screenWidth, InsGlobal.screenHeight);
		InsGlobal.graphic.setColor(0xFFFFFF);
		InsGlobal.graphic.drawString(currentProgress + " %", InsGlobal.screenWidth / 2, InsGlobal.screenHeight / 2, Graphics.BASELINE | Graphics.HCENTER);
	}

	
}