package com.inspedio.system.helper;

import com.inspedio.system.helper.record.SaveManager;

public class InsSave {

	/**
	 * Name of RecordStore
	 */
	public String recordName;
	/**
	 * Current Version Name
	 */
	public String versionName;
	
	protected SaveManager manager;
	
	public InsSave(String RecordName, String VersionName){
		this.recordName = RecordName;
		this.versionName = VersionName;
		this.manager = new SaveManager(RecordName, VersionName);
	}
	
	public InsSave(String RecordName){
		this(RecordName, "1.0");
	}
	
	public InsSave(){
		this("Default", "1.0");
	}
	
	public void initData(){
		
	}
	
	public void save(){
		
	}
	
	public void load(){
		
	}
	
	
}
