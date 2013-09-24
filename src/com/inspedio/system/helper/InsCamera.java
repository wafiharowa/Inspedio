package com.inspedio.system.helper;

import com.inspedio.system.core.InsGlobal;
import com.inspedio.entity.InsBasic;
import com.inspedio.enums.HAlignment;
import com.inspedio.enums.VAlignment;

/**
 * This is the helper class for camera.<br>
 * It can be used to follow an object and makes that object in the middle of camera at anytime.<br>
 * You can set Camera Boundary so it won't go out of World Map
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsCamera extends InsBasic{

	public int leftBoundary;
	public int rightBoundary;
	public int upBoundary;
	public int downBoundary;
	
	/**
	 * The object which camera will follow and focus on. Can be null
	 */
	public InsBasic focusTarget;
	/**
	 * True if camera is in follow mode
	 */
	public boolean isFollow;
	
	
	public InsCamera(){
		this(0, 0, InsGlobal.screenWidth, InsGlobal.screenHeight);
	}
	
	public InsCamera(int X, int Y, int Width, int Height) {
		super(X, Y, Width, Height);
		this.align.setAlignment(HAlignment.LEFT, VAlignment.TOP);
	}

}
