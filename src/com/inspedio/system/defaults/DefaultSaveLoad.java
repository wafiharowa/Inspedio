package com.inspedio.system.defaults;

import com.inspedio.enums.LogLevel;
import com.inspedio.system.helper.InsLogger;
import com.inspedio.system.helper.InsSave;
import com.inspedio.system.helper.record.SaveDataObject;

public class DefaultSaveLoad extends InsSave{

	public static String _AUTHOR = "Wafi Harowa";
	public static String _SECRET = "I am spiderman";
	
	
	public DefaultSaveLoad() {
		super("Inspedio");
	}
	
	public DefaultSaveLoad(String RecordName) {
		super(RecordName);
	}

	public void assignData() {
		this.addData(new SaveDataObject("Inspedio Author", _AUTHOR));
		this.addData(new SaveDataObject("Inspedio Secret", _SECRET));		
		InsLogger.writeLog("Assign Author Save Data", LogLevel.PROCESS);
	}

	public void retrieveData() {
		if(isDataExist("Inspedio Author")){_AUTHOR = this.getData("Inspedio Author").GetDataString();}
		if(isDataExist("Inspedio Secret")){_SECRET = this.getData("Inspedio Secret").GetDataString();}
		
		InsLogger.writeLog("Retrieve Author Data, AUTHOR = " + _AUTHOR, LogLevel.PROCESS);
		InsLogger.writeLog("Retrieve Secre Data, SECRET = " + _SECRET, LogLevel.PROCESS);
		
	}

}
