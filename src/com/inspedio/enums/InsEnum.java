package com.inspedio.enums;

public class InsEnum {
	protected int enumValue;
	protected String enumName;
	
	protected InsEnum(String Name, int Value){
		this.enumName = Name;
		this.enumValue = Value;
	}
	
	public String toString(){
		return this.enumName;
	}
	
	public int getValue(){
		return this.enumValue;
	}
}
