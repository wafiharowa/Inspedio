package com.inspedio.actions;

import com.inspedio.entity.InsAction;
import com.inspedio.entity.primitive.InsCallback;

public class Forever extends InsAction{

	protected Forever(int FrameCount, InsCallback Callback) {
		super(FrameCount, Callback);
	}

	public int act() {
		return 0;
	}

}
