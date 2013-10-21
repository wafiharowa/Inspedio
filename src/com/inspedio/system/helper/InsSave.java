package com.inspedio.system.helper;

import com.inspedio.system.helper.record.SaveManager;

/**
 * InsSave is a class for handling Save/Load mechanism.<br>
 */
public abstract class InsSave extends SaveManager{
	
	public InsSave(String Name, String Version, boolean autoLoad){
		super(Name, Version);
		if(autoLoad)load();
	}
	
	public InsSave(String RecordName){
		this(RecordName, "1.0", true);
	}
		
	/**
	 * This is where you put your Game Data before calling save() method.
	 */
	public abstract void initData();
	
}
