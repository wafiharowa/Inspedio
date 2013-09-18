package com.inspedio.system.helper.record;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Vector;

import javax.microedition.rms.RecordStore;

public class SaveManager {

	protected String recordName;
	protected String recordVersion;
	protected Vector dataList;
	
	public SaveManager(String RecordName){
		this(RecordName, "1.0");
	}
	
	public SaveManager(String RecordName, String RecordVersion){
		this.recordName = RecordName;
		this.recordVersion = RecordVersion;
	}
	
	public String getVersion(){
		return this.recordVersion;
	}
	
	public String getName(){
		return this.recordName;
	}
	
	public void save(){
		try
		{
			RecordStore.deleteRecordStore(recordName);
			RecordStore recordStore = RecordStore.openRecordStore(recordName, true, RecordStore.AUTHMODE_PRIVATE, true);
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream dataStream = new DataOutputStream(byteStream);
			
			SaveDataObject obj;
			for(int i = 0; i < dataList.size(); i++)
			{
				obj = (SaveDataObject) dataList.elementAt(i);
				if((obj != null))
				{
					obj.write(dataStream);
				}
			}
			
			byte[] data = byteStream.toByteArray();
			dataStream.close();
			recordStore.addRecord(data, 0, data.length);
			recordStore.closeRecordStore();
			
			System.out.println("Saving GameData Success");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void load(){
		
	}
	
	public void delete(){
		
	}
	
	
}
