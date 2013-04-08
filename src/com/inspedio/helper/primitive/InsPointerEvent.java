package com.inspedio.helper.primitive;

public class InsPointerEvent extends InsPoint{

	public static int PRESSED = 0;
	public static int RELEASED = 1;
	public static int DRAGGED = 2;
	
	/**
	 * Whether event indicate pressed, released, or dragged
	 */
	public int type;
	
	public InsPointerEvent(int X, int Y, int Type){
		this.x = X;
		this.y = Y;
		this.type = Type;
	}
}
