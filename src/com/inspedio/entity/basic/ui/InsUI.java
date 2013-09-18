package com.inspedio.entity.basic.ui;

import com.inspedio.entity.InsBasic;

/**
 * This is the basic of all UI element. You should extend this to implement your own UI.
 * You can attach an action to this (Scale, Move, Fade-in/Fade-Out)
 * 
 * @author Hyude
 * @version 1.0
 */
public abstract class InsUI extends InsBasic{

	public InsUI(){
		this(0, 0, 0, 0);
	}
	
	
	public InsUI(int X, int Y, int Width, int Height){
		super(X, Y, Width, Height);
		this.calibratePosition();
	}
	
	protected void calibratePosition(){
		
	}

}
