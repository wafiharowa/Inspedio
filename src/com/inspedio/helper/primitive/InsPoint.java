package com.inspedio.helper.primitive;

/**
 * Primitive Class for Point (Coordinate)
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsPoint {

	public int x;
	public int y;
	
	public InsPoint(){
		this(0, 0);
	}
	
	public InsPoint(int X, int Y){
		this.x = X;
		this.y = Y;
	}
}
