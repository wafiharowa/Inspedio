package com.inspedio.actions;

import com.inspedio.entity.InsAction;
import com.inspedio.entity.basic.InsBasic;
import com.inspedio.entity.basic.shape.InsShape;
import com.inspedio.entity.primitive.InsCallback;

public class ScaleTo extends InsAction{

	protected ScaleTo(InsBasic Target, InsCallback Callback, int FrameCount) {
		super(Target, Callback, FrameCount);
	}

	public int act() {
		return 0;
	}

	public static ScaleBy create(InsShape Target, int ScaleX, int ScaleY, InsCallback Callback, int FrameCount,  int AnchorX, int AnchorY){
		return new ScaleBy(Target, ScaleX - Target.width, ScaleY - Target.height, Callback, FrameCount, AnchorX, AnchorY);
	}
}
