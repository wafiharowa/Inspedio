package com.inspedio.enums;

public class AudioType extends InsEnum{

	public static final AudioType BGM = new AudioType("BGM", -1);
	public static final AudioType SFX = new AudioType("SFX", 1);

	protected AudioType(String Name, int Value) {
		super(Name, Value);
	}

}
