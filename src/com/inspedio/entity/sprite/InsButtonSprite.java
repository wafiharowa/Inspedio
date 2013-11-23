package com.inspedio.entity.sprite;

import com.inspedio.entity.InsSprite;
import com.inspedio.entity.primitive.InsCallback;

public class InsButtonSprite extends InsSprite{

	/**
	 * Callback event when Button Clicked
	 */
	protected InsCallback onPressedCallback;
	/**
	 * Callback event when Button Released
	 */
	protected InsCallback onReleasedCallback;
	/**
	 * Callback event when Button Dragged
	 */
	protected InsCallback onDraggedCallback;
	/**
	 * Callback event when Button Hold
	 */
	protected InsCallback onHoldCallback;
	/**
	 * Set this to FALSE to let object behind it to get Event. Defaulted to true
	 */
	public boolean onPressedReturn = true;
	/**
	 * Set this to FALSE to let object behind it to get Event. Defaulted to true
	 */
	public boolean onReleasedReturn = true;
	/**
	 * Set this to FALSE to let object behind it to get Event. Defaulted to true
	 */
	public boolean onDraggedReturn = true;
	/**
	 * Set this to FALSE to let object behind it to get Event. Defaulted to true
	 */
	public boolean onHoldReturn = true;
	
	public InsButtonSprite(String spritePath, int X, int Y, int Width, int Height) {
		super(spritePath, X, Y, Width, Height);
		
	}
	
	public InsButtonSprite(String spritePath, int X, int Y){
		super(spritePath, X, Y);
	}
	
	public InsButtonSprite(String spritePath){
		super(spritePath);
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
	
	public boolean onTouchDragged(){
		if(onDraggedCallback != null){
			this.onDraggedCallback.call();
			return onDraggedReturn;
		}
		return false;
	}
	
	public boolean onTouchHold(){
		if(onHoldCallback != null){
			this.onHoldCallback.call();
			return onHoldReturn;
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
	
	/**
	 * Set Callback to handle Dragged Event
	 */
	public void setDraggedCallback(InsCallback c){
		this.onDraggedCallback = c;
	}
	
	/**
	 * Set Callback to handle Hold Event
	 */
	public void setHoldCallback(InsCallback c){
		this.onHoldCallback = c;
	}
	
	public void click(){
		if(onPressedCallback != null){
			this.onPressedCallback.call();
		}
	}
	

}
