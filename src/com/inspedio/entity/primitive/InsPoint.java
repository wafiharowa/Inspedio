package com.inspedio.entity.primitive;

public class InsPoint {

	public int x;
	public int y;
	
	public InsPoint(){
		this(0,0);
	}
	
	public InsPoint(int X, int Y){
		this.setPoint(X, Y);
	}
	
	public void setPoint(int X, int Y){
		this.x = X;
		this.y = Y;
	}
	
	public void addPoint(int X, int Y){
		this.x += X;
		this.y += Y;
	}
}
