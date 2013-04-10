package com.inspedio.basic.ui;

import com.inspedio.basic.InsBasic;
import com.inspedio.helper.primitive.InsCallback;

public class InsButton extends InsBasic{

	/**
	 * Callback event when Button Clicked
	 */
	protected InsCallback onPressedCallback;
	/**
	 * Callback event when Button Released
	 */
	protected InsCallback onReleasedCallback;
	/**
	 * Set this to FALSE to let object behind it to get Event. Defaulted to false
	 */
	public boolean onPressedReturn = true;
	/**
	 * Set this to FALSE to let object behind it to get Event. Defaulted to false
	 */
	public boolean onReleasedReturn = true;
	
	/**
	 * Button Caption
	 */
	protected String caption;
	
	public InsButton(int X, int Y, int Width, int Height) {
		super(X, Y, Width, Height);
	}
	
	public boolean onTouchPressed(){
		if(onPressedCallback != null){
			this.onPressedCallback.call();
			return onPressedReturn;
		}
		return false;
	}
	
	public boolean onTouchReleased(){
		if(onReleasedCallback != null){
			this.onReleasedCallback.call();
			return onReleasedReturn;
		}
		return false;
	}

	/**
	 * Set Callback to handle Click Event
	 */
	public void setClickedCallback(InsCallback c){
		this.onPressedCallback = c;
	}
	
	/**
	 * Set Callback to handle Released Event
	 */
	public void setReleasedCallback(InsCallback c){
		this.onReleasedCallback = c;
	}
}
