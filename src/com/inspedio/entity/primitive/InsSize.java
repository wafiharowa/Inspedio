package com.inspedio.entity.primitive;

public class InsSize {

	public int width;
	public int height;
	
	public InsSize(){
		this(0,0);
	}
	
	public InsSize(int Width, int Height){
		this.setSize(Width, Height);
	}
	
	public void setSize(int Width, int Height){
		this.width = Width;
		this.height = Height;
	}
	
	public void addSize(int Width, int Height){
		this.width += Width;
		this.height += Height;
	}
}
