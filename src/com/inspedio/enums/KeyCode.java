package com.inspedio.enums;

public class KeyCode extends InsEnum{

	public static final KeyCode LEFT = new KeyCode("LEFT", 0);
	public static final KeyCode RIGHT = new KeyCode("RIGHT", 1);
	public static final KeyCode UP = new KeyCode("UP", 2);
	public static final KeyCode DOWN = new KeyCode("DOWN", 3);
	public static final KeyCode FIRE = new KeyCode("FIRE", 4);
	public static final KeyCode GAME_A = new KeyCode("GAME_A", 5);
	public static final KeyCode GAME_B = new KeyCode("GAME_B", 6);
	public static final KeyCode GAME_C = new KeyCode("GAME_C", 7);;
	public static final KeyCode GAME_D = new KeyCode("GAME_D", 8);;
	
	protected KeyCode(String Name, int Value) {
		super(Name, Value);
	}

}
