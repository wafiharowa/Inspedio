package com.inspedio.actions;

/**
 * Scale an Object (Enlarge or Shrink) by given size (in pixel).<br>
 * You can define Anchor Point during scaling. <br>
 * Anchor X can be LEFT, CENTER, RIGHT.<br>
 * Anchor Y can be TOP, CENTER, BOTTOM.<br>
 * 
 * @author Hyude
 * @version 1.0
 */
import com.inspedio.entity.InsAction;
import com.inspedio.entity.basic.InsShape;
import com.inspedio.entity.primitive.InsCallback;

public class ScaleBy extends InsAction{
	
	public static final int LEFT = 0;
	public static final int CENTER = 1;
	public static final int RIGHT = 2;
	
	public static final int TOP = 0;
	public static final int BOTTOM = 2;
	
	protected InsShape target;
	
	protected int scaleX;
	protected int scaleY;	
	protected int stepX;
	protected int stepY;
	
	protected int anchorX;
	protected int anchorY;

	protected ScaleBy(InsShape Target, int ScaleX, int ScaleY, InsCallback Callback, int FrameCount, int AnchorX, int AnchorY) {
		super(Target, Callback, FrameCount);
		this.scaleX = ScaleX;
		this.scaleY = ScaleY;
		this.stepX = this.scaleX / this.frameCount;
		this.stepY = this.scaleY / this.frameCount;
		this.anchorX = AnchorX;
		this.anchorY = AnchorY;
	}

	public int act() {
		if(frameCount > 0){
			this.scale(stepX, stepY);
			frameCount--;
		} else if(frameCount == 0){
			this.scale(scaleX, scaleY);
			finishAction();
		}
		return frameCount;
	}

	private void scale(int X, int Y){
		this.target.size.width += X;
		this.scaleX -= X;
		this.target.position.x -= (X * anchorX) / 2;
		
		this.target.size.height += Y;
		this.scaleY -= Y;
		this.target.position.y -= (Y * anchorY) / 2;
	}
	
	public static ScaleBy create(InsShape Target, int ScaleX, int ScaleY, InsCallback Callback, int FrameCount,  int AnchorX, int AnchorY){
		return new ScaleBy(Target, ScaleX, ScaleY, Callback, FrameCount, AnchorX, AnchorY);
	}
}
