package com.inspedio.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.microedition.rms.RecordStore;

public class InsSave {

	/**
	 * Name of Record to save/load data
	 */
	public String recordName;
	/**
	 * Container of all saved data with Integer type
	 */
	public int[] dataInt;
	/**
	 * Container of all saved data with String type
	 */
	public String[] dataString;
	/**
	 * Container of all saved data with Float type
	 */
	public float[] dataFloat;
	/**
	 * Container of all saved data with Boolean type
	 */
	public boolean[] dataBoolean;
	/**
	 * The Length of Integer Array of game data
	 */
	public int intCount;
	/**
	 * The Length of String Array of game data
	 */
	public int stringCount;
	/**
	 * The Length of Float Array of game data
	 */
	public int floatCount;
	/**
	 * The Length of Boolean Array of game data
	 */
	public int booleanCount;
	
	/**
	 * Construct <code>InsSave</code> with default name
	 */
	public InsSave()
	{
		this("Default");
	}
	
	/**
	 * Construct <code>InsSave</code> with given name
	 */
	public InsSave(String recordName)
	{
		this.intCount = 0;
		this.stringCount = 0;
		this.floatCount = 0;
		this.booleanCount = 0;
		this.initData();
	}
	
	/**
	 * Create Save Load helper class
	 * 
	 * @param	IntCount		The Length of IntArray of game data
	 * @param	StringCount		The Length of StringArray of game data
	 * @param	FloatCount		The Length of FloatArray of game data
	 * @param	BooleanCount	The Length of BooleanArray of game data
	 */
	public void create(int IntCount, int StringCount, int FloatCount, int BooleanCount)
	{
		this.intCount = IntCount;
		this.stringCount = StringCount;
		this.floatCount = FloatCount;
		this.booleanCount = BooleanCount;
		this.initData();
	}
	
	/**
	 * Set Game Data
	 * 
	 * @param	IntArray		IntArray of game data
	 * @param	StringArray		StringArray of game data
	 * @param	FloatArray		FloatArray of game data
	 * @param	BooleanArray	BooleanArray of game data
	 */
	public void setData(int[] IntArray, String[] StringArray, float[] FloatArray, boolean[] BooleanArray)
	{
		this.dataInt = IntArray;
		this.dataString = StringArray;
		this.dataFloat = FloatArray;
		this.dataBoolean = BooleanArray;
		
		this.intCount = this.dataInt.length;
		this.stringCount = this.dataString.length;
		this.floatCount = this.dataFloat.length;
		this.booleanCount = this.dataBoolean.length;
	}
	
	/**
	 * Clear Data
	 */
	public void initData()
	{
		this.dataInt = new int[this.intCount];
		this.dataString = new String[this.stringCount];
		this.dataFloat = new float[this.floatCount];
		this.dataBoolean = new boolean[this.booleanCount];
	}
	
	public void save()
	{
		this.save(recordName);
	}
	
	/**
	 * Save the Game Data into recordstore.
	 * You have to fill gamedata before saving, or it will do nothing
	 * The saved data have header which represents length of each data list
	 */
	public void save(String recordName)
	{
		try
		{
			RecordStore.deleteRecordStore(recordName);
			RecordStore recordStore = RecordStore.openRecordStore(recordName, true, RecordStore.AUTHMODE_PRIVATE, true);
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream dataStream = new DataOutputStream(byteStream);
			
			// Write Header
			dataStream.writeInt(this.dataInt.length);
			dataStream.writeInt(this.dataString.length);
			dataStream.writeInt(this.dataFloat.length);
			dataStream.writeInt(this.dataBoolean.length);
			
			int i = 0;
			// Write Data for Integer List
			for(i = 0; i < this.intCount; i++)
			{
				dataStream.writeInt(this.dataInt[i]);
			}
			// Write Data for String List
			for(i = 0; i < this.stringCount; i++)
			{
				dataStream.writeUTF(this.dataString[i]);
			}
			// Write Data for Float List
			for(i = 0; i < this.floatCount; i++)
			{
				dataStream.writeFloat(this.dataFloat[i]);
			}
			// Write Data for Boolean List
			for(i = 0; i < this.booleanCount; i++)
			{
				dataStream.writeBoolean(this.dataBoolean[i]);
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
	
	
	public void load()
	{
		this.load(recordName);
	}
	
	/**
	 * Load the Game Data from recordstore.
	 * The Loaded data can then accessed in respective data List
	 */
	public void load(String recordName)
	{
		try
		{
			RecordStore recordStore = RecordStore.openRecordStore(recordName, true, RecordStore.AUTHMODE_PRIVATE, true);
			int data_count = recordStore.getNumRecords();
			
			if(data_count > 0)
			{
				byte[] count = recordStore.getRecord(data_count);
				
				ByteArrayInputStream byteStream = new ByteArrayInputStream(count);
				DataInputStream dataStream = new DataInputStream(byteStream);
				
				// Read the Header first
				this.intCount = dataStream.readInt();
				this.stringCount = dataStream.readInt();
				this.floatCount = dataStream.readInt();
				this.booleanCount = dataStream.readInt();
				
				this.initData();
				
				int i = 0;
				// Read Data for Integer List
				for(i = 0; i < this.intCount; i++)
				{
					this.dataInt[i] = dataStream.readInt();
				}
				// Read Data for String List
				for(i = 0; i < this.stringCount; i++)
				{
					this.dataString[i] = dataStream.readUTF();
				}
				// Read Data for Float List
				for(i = 0; i < this.floatCount; i++)
				{
					this.dataFloat[i] = dataStream.readFloat();
				}
				// Read Data for Boolean List
				for(i = 0; i < this.booleanCount; i++)
				{
					this.dataBoolean[i] = dataStream.readBoolean();
				}
				dataStream.close();
			
				System.out.println("Load Game Data Success");
			}
			
			recordStore.closeRecordStore();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
