package com.inspedio.actions;

import com.inspedio.entity.InsAction;
import com.inspedio.entity.InsShape;
import com.inspedio.entity.primitive.InsCallback;

public class ScaleTo extends InsAction{

	protected ScaleTo(int FrameCount, InsCallback Callback) {
		super(FrameCount, Callback);
	}

	public int act() {
		return 0;
	}

	public static ScaleBy create(InsShape Target, int FrameCount, int ScaleX, int ScaleY, InsCallback Callback){
		return new ScaleBy(FrameCount, ScaleX - Target.size.width, ScaleY - Target.size.height, Callback);
	}
}
