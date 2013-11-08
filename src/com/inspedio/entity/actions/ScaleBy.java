package com.inspedio.entity.actions;

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
import com.inspedio.system.helper.InsUtil;

public class ScaleBy extends InsAction{
	
	protected int scaleX;
	protected int scaleY;
	
	protected int remainingX;
	protected int remainingY;
	
	protected double stepX;
	protected double stepY;
	
	protected double offsetX;
	protected double offsetY;

	protected ScaleBy(int FrameCount, int ScaleX, int ScaleY, InsCallback Callback) {
		super(FrameCount, Callback);
		this.scaleX = ScaleX;
		this.scaleY = ScaleY;
		this.stepX = (double) this.scaleX / (double) FrameCount;
		this.stepY = (double) this.scaleY / (double) FrameCount;
		this.reset();
	}
	
	public static ScaleBy create(int FrameCount, int ScaleX, int ScaleY, InsCallback Callback){
		return new ScaleBy(FrameCount, ScaleX, ScaleY, Callback);
	}
	
	public static ScaleBy create(int FrameCount, int ScaleX, int ScaleY){
		return create(FrameCount, ScaleX, ScaleY, null);
	}

	public int act() {
		if(remainingCount > 0){
			this.scale(stepX, stepY);
			remainingCount--;
		} else if(remainingCount == 0){
			this.scale(remainingX, remainingY);
			finishAction();
		}
		return remainingCount;
	}

	private void scale(double X, double Y){
		((InsShape) this.target).addSize((int) X, (int)Y);
		this.offsetX += X - (int) X;
		this.offsetY += Y - (int) Y;
		this.remainingX -= X;
		this.remainingY -= Y;
		if(InsUtil.Absolute((int) offsetX) >= 1){
			((InsShape) this.target).addSize((int) this.offsetX, 0);
			this.offsetX -= (int) this.offsetX;
		}
		if(InsUtil.Absolute((int) offsetY) >= 1){
			((InsShape) this.target).addSize(0, (int) this.offsetY);
			this.offsetY -= (int) this.offsetY;
		}
	}
	
	public void reset(){
		super.reset();
		this.remainingX = this.scaleX;
		this.remainingY = this.scaleY;
		this.offsetX = 0;
		this.offsetY = 0;
	}
	
	
}
