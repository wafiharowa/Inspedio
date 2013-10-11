package com.inspedio.enums;

public class BorderLayout extends InsEnum{

	public static final BorderLayout INSIDE = new BorderLayout("INSIDE", 0);
	public static final BorderLayout CENTER = new BorderLayout("CENTER", 1);
	public static final BorderLayout OUTSIDE = new BorderLayout("OUTSIDE", 2);
	
	protected BorderLayout(String Name, int Value) {
		super(Name, Value);
	}

}
