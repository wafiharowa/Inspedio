package com.inspedio.entity.basic.ui;

import javax.microedition.lcdui.Graphics;

import com.inspedio.entity.basic.InsText;
import com.inspedio.entity.basic.shape.InsRect;
import com.inspedio.entity.primitive.InsCallback;

public class InsButton extends InsRect{
	
	/**
	 * Callback event when Button Clicked
	 */
	protected InsCallback onPressedCallback;
	/**
	 * Callback event when Button Released
	 */
	protected InsCallback onReleasedCallback;
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
	public boolean onHoldReturn = true;
	
	/**
	 * Button Caption
	 */
	public InsText caption;
	
	/**
	 * Instantiate a new Button
	 */
	public InsButton(int X, int Y, int Width, int Height, String Caption, int Color) {
		super(X, Y, Width, Height);
		this.fillColor = Color;
		this.caption = new InsText();
		//this.caption.setValue(Caption, X, Y, Graphics.HCENTER | Graphics.BASELINE, true);
		this.setMiddlePoint(X, Y);
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
	 * Set Callback to handle Hold Event
	 */
	public void setHoldCallback(InsCallback c){
		this.onHoldCallback = c;
	}
	
	public void draw(Graphics g){
		this.caption.draw(g);
	}
	
	public void setText(String Text){
		this.caption.text = Text;
	}
	
	public void setTextColor(int Color){
		this.caption.color = Color;
	}
	
	/**
	 * Use this to set Button position.
	 */
	public void setMiddlePoint(int X, int Y){
		super.setMiddlePoint(X, Y);
		this.caption.setPosition(X, Y);
	}
	
}
