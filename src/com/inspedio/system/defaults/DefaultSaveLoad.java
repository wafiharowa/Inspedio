package com.inspedio.system.defaults;

import com.inspedio.enums.LogLevel;
import com.inspedio.system.helper.InsLogger;
import com.inspedio.system.helper.InsSave;
import com.inspedio.system.helper.record.SaveDataObject;

public class DefaultSaveLoad extends InsSave{

	public static String _AUTHOR = "Wafi Harowa";
	
	public DefaultSaveLoad() {
		super("Inspedio");
	}
	
	public DefaultSaveLoad(String RecordName) {
		super(RecordName);
	}

	public void assignData() {
		this.addData(new SaveDataObject("Inspedio Author", _AUTHOR));
		InsLogger.writeLog("Assign Author Save Data", LogLevel.PROCESS);
	}

	public void retrieveData() {
		if(isDataExist("Inspedio Author")){_AUTHOR = this.getData("Inspedio Author").GetDataString();}
		InsLogger.writeLog("Retrieve Author Save Data", LogLevel.PROCESS);
		
	}

}
