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
import com.inspedio.entity.InsShape;
import com.inspedio.entity.primitive.InsCallback;

public class ScaleBy extends InsAction{
	
	protected InsShape target;
	
	protected int scaleX;
	protected int scaleY;	
	protected int stepX;
	protected int stepY;

	protected ScaleBy(int FrameCount, int ScaleX, int ScaleY, InsCallback Callback) {
		super(FrameCount, Callback);
		this.scaleX = ScaleX;
		this.scaleY = ScaleY;
		this.stepX = this.scaleX / this.frameCount;
		this.stepY = this.scaleY / this.frameCount;
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
		this.target.addSize(X, Y);
		this.scaleX -= X;
		this.scaleY -= Y;
	}
	
	public static ScaleBy create(int FrameCount, int ScaleX, int ScaleY, InsCallback Callback){
		return new ScaleBy(FrameCount, ScaleX, ScaleY, Callback);
	}
}
