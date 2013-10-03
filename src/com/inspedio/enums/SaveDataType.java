package com.inspedio.enums;

public class SaveDataType extends InsEnum{

	public static final SaveDataType BOOLEAN = new SaveDataType("BOOLEAN", 0);
	public static final SaveDataType INTEGER = new SaveDataType("INTEGER", 1);
	public static final SaveDataType FLOAT = new SaveDataType("FLOAT", 2);
	public static final SaveDataType STRING = new SaveDataType("STRING", 3);
	
	public static final SaveDataType BOOLEAN_ARRAY = new SaveDataType("BOOLEAN_ARRAY", 4);
	public static final SaveDataType INTEGER_ARRAY = new SaveDataType("INTEGER_ARRAY", 5);
	public static final SaveDataType FLOAT_ARRAY = new SaveDataType("FLOAT_ARRAY", 6);
	public static final SaveDataType STRING_ARRAY = new SaveDataType("STRING_ARRAY", 7);
	
	protected SaveDataType(String Name, int Value) {
		super(Name, Value);
	}
	
	public static SaveDataType getType(short Value){
		switch (Value){
			case 0:
				return BOOLEAN;
			case 1:
				return INTEGER;
			case 2:
				return FLOAT;
			case 3:
				return STRING;
			case 4:
				return BOOLEAN_ARRAY;
			case 5:
				return INTEGER_ARRAY;
			case 6:
				return FLOAT_ARRAY;
			case 7:
				return STRING_ARRAY;
			default:
				return null;
		}
	}
	
}
