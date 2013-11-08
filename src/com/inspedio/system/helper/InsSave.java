package com.inspedio.system.helper;

import com.inspedio.system.helper.record.SaveManager;

/**
 * InsSave is a class for handling Save/Load mechanism.<br>
 */
public abstract class InsSave extends SaveManager{
	
	public boolean autoLoad;
	public boolean autoClear;
	
	/**
	 * @param	AutoLoad	Whether this will be automatically loaded when starting game
	 * @param	AutoClear	Whether data will be destroyed after save and load (to preserve memory)	
	 */
	public InsSave(String Name, String Version, boolean AutoLoad, boolean AutoClear){
		super(Name, Version);
		this.autoLoad = AutoLoad;
		this.autoClear = AutoClear;
	}
	
	/**
	 * Equivalent to InsSave(RecordName, "1.0", true, true)
	 */
	public InsSave(String RecordName){
		this(RecordName, "1.0", true, true);
	}
		
	/**
	 * Implement this method by Putting your Data into SaveObject.<br>
	 * This method will automatically called before calling save().
	 */
	public abstract void assignData();
	
	/**
	 * Implement this method by Retrieving SaveObject into your Data.<br>
	 * This method will automatically called after calling load().
	 */
	public abstract void retrieveData();
	
	
	public void save(){
		if(this.autoClear){this.clear();}
		this.assignData();
		super.save();
		if(this.autoClear){this.clear();}
	}
	
	public boolean load(){
		if(this.autoClear){this.clear();}
		boolean success = super.load();
		if(success){
			this.retrieveData();
			if(this.autoClear){this.clear();}
		}
		return success;
	}
	
}
