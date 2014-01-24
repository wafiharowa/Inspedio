package com.inspedio.entity.ui;

import com.inspedio.entity.InsShape;
import com.inspedio.entity.primitive.InsCallback;
import com.inspedio.entity.primitive.InsPoint;
import com.inspedio.system.helper.InsUtil;

public class InsSwipe extends InsShape{

	protected InsPoint startPoint;
	protected InsPoint endPoint;
	
	/**
	 * Callback event when UI Swiped to Left
	 */
	protected InsCallback onSwipeLeft;
	/**
	 * Callback event when UI Swiped to Right
	 */
	protected InsCallback onSwipeRight;
	/**
	 * Callback event when UI Swiped to Up
	 */
	protected InsCallback onSwipeUp;
	/**
	 * Callback event when UI Swiped to Down
	 */
	protected InsCallback onSwipeDown;
	
	/**
	 * Set this to FALSE to let object behind it to get Event. Defaulted to false
	 */
	public boolean onSwipedReturn = false;
	
	/**
	 * How much X distance user have to swipe to consider it swiped
	 */
	public int  toleranceX = 40;
	/**
	 * How much Y distance user have to swipe to consider it swiped
	 */
	public int  toleranceY = 40;
	
	public InsSwipe(){
		this(0, 0, 0, 0);
	}
	
	public InsSwipe(int X, int Y, int Width, int Height){
		super(X, Y, Width, Height);
		this.startPoint = new InsPoint(X, Y);
		this.endPoint = new InsPoint(X, Y);
	}
	
	public void destroy(){
		super.destroy();
		this.startPoint = null;
		this.endPoint = null;
		this.onSwipeDown = null;
		this.onSwipeLeft = null;
		this.onSwipeRight = null;
		this.onSwipeUp = null;
	}
	
	/**
	 * Set Callback to handle Swipe Left Event
	 */
	public void setSwipedLeftCallback(InsCallback c){
		this.onSwipeLeft = c;
	}
	
	/**
	 * Set Callback to handle Swipe Right Event
	 */
	public void setSwipedRightCallback(InsCallback c){
		this.onSwipeRight = c;
	}
	
	/**
	 * Set Callback to handle Swipe Up Event
	 */
	public void setSwipedUpCallback(InsCallback c){
		this.onSwipeUp = c;
	}
	
	/**
	 * Set Callback to handle Swipe Down Event
	 */
	public void setSwipedDownCallback(InsCallback c){
		this.onSwipeDown = c;
	}
	
	/**
	 * Do not override this unless you want to specifically access coordinate touched
	 */
	public boolean onPointerPressed(int X, int Y) {	
		if(this.isOverlap(X, Y)){
			this.startPoint.x = X;
			this.startPoint.y = Y;
		}
		return false;
	}

	public boolean onPointerReleased(int X, int Y) {
		if(this.isOverlap(X, Y)){
			this.endPoint.x = X;
			this.endPoint.y = Y;
			return evaluate();
		}
		return false;
	}
	
	/**
	 * Swipe Event checked Prioritize X over Y
	 */
	public boolean evaluate(){
		int dX = endPoint.x - startPoint.x;
		
		if(dX >= toleranceX){
			if(this.onSwipeRight != null){
				this.onSwipeRight.call();
				return onSwipedReturn;
			}
		} else if(dX <= -toleranceX){
			if(this.onSwipeLeft != null){
				this.onSwipeLeft.call();
				return onSwipedReturn;
			}
		}
		
		int dY = endPoint.y - startPoint.y;
		
		if(dY >= toleranceY){
			if(this.onSwipeDown != null){
				this.onSwipeDown.call();
				return onSwipedReturn;
			}
		} else if(dY <= -toleranceY){
			if(this.onSwipeUp != null){
				this.onSwipeUp.call();
				return onSwipedReturn;
			}
		}
		
		return false;
	}
	
	/**
	 * Set Tolerance Value (minimum distance swiped to consider valid)
	 */
	public void setTolerance(int ToleranceX, int ToleranceY){
		this.toleranceX = InsUtil.Absolute(ToleranceX);
		this.toleranceY = InsUtil.Absolute(ToleranceY);
	}
}
