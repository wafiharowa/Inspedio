package com.inspedio.system.helper.record;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.inspedio.enums.SaveDataType;

public class SaveDataObject {
	
	public SaveDataType type = null;
	public int nameLength = 0;
	public int dataLength = 0;
	public String name = "";
	
	private boolean boolData;
	private int intData;
	private double doubleData;
	private String stringData;
	private long longData;
	
	private boolean[] boolArrData = null;
	private int[] intArrData = null;
	private double[] doubleArrData = null;
	private String[] stringArrData = null;
	private long[] longArrData = null;
	
	public SaveDataObject(){
		
	}
	
	public SaveDataObject(String Name){
		this.name = Name;
		this.nameLength = Name.length();
	}
	
	public SaveDataObject(String Name, boolean Data){
		this(Name);
		this.setData(Data);
	}
	
	public SaveDataObject(String Name, int Data){
		this(Name);
		this.setData(Data);
	}
	
	public SaveDataObject(String Name, double Data){
		this(Name);
		this.setData(Data);
	}
	
	public SaveDataObject(String Name, String Data){
		this(Name);
		this.setData(Data);
	}
	
	public SaveDataObject(String Name, long Data){
		this(Name);
		this.setData(Data);
	}
	
	public SaveDataObject(String Name, boolean[] Data){
		this(Name);
		this.setData(Data);
	}
	
	public SaveDataObject(String Name, int[] Data){
		this(Name);
		this.setData(Data);
	}
	
	public SaveDataObject(String Name, double[] Data){
		this(Name);
		this.setData(Data);
	}
	
	public SaveDataObject(String Name, String[] Data){
		this(Name);
		this.setData(Data);
	}
	
	public SaveDataObject(String Name, long[] Data){
		this(Name);
		this.setData(Data);
	}
			
	public SaveDataObject(DataInputStream stream){
		this.read(stream);
	}
	
	public void read(DataInputStream stream){
		try {
			this.type = SaveDataType.getType(stream.readShort());
			this.nameLength = stream.readInt();
			this.dataLength = stream.readInt();
			this.name = stream.readUTF();
			
			switch(this.type.getValue()){
				case 0:
					this.boolData = stream.readBoolean();
					break;
				case 1:
					this.boolArrData = new boolean[this.dataLength];
					for(int i = 0; i < this.dataLength; i++){
						this.boolArrData[i] = stream.readBoolean();
					}
					break;
				case 2:
					this.intData = stream.readInt();
					break;
				case 3:
					this.intArrData = new int[this.dataLength];
					for(int i = 0; i < this.dataLength; i++){
						this.intArrData[i] = stream.readInt();
					}
					break;
				case 4:
					this.doubleData = stream.readDouble();
					break;
				case 5:
					this.doubleArrData = new double[this.dataLength];
					for(int i = 0; i < this.dataLength; i++){
						this.doubleArrData[i] = stream.readDouble();
					}
					break;
				case 6:
					this.stringData = stream.readUTF();
					break;
				case 7:
					this.stringArrData = new String[this.dataLength];
					for(int i = 0; i < this.dataLength; i++){
						this.stringArrData[i] = stream.readUTF();
					}
					break;
				case 8:
					this.longData = stream.readLong();
					break;
				case 9:
					this.longArrData = new long[this.dataLength];
					for(int i = 0; i < this.dataLength; i++){
						this.longArrData[i] = stream.readLong();
					}
					break;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(DataOutputStream stream){
		try {
			
			stream.writeShort(this.type.getValue());
			stream.writeInt(this.nameLength);
			stream.writeInt(this.dataLength);
			stream.writeUTF(this.name);
			
			switch(this.type.getValue()){
				case 0:
					stream.writeBoolean(this.boolData);
					break;
				case 1:
					for(int i = 0; i < this.dataLength; i++){
						stream.writeBoolean(this.boolArrData[i]);
					}
					break;
				case 2:
					stream.writeInt(this.intData);
					break;
				case 3:
					for(int i = 0; i < this.dataLength; i++){
						stream.writeInt(this.intArrData[i]);
					}
					break;
				case 4:
					stream.writeDouble(this.doubleData);
					break;
				case 5:
					for(int i = 0; i < this.dataLength; i++){
						stream.writeDouble(this.doubleArrData[i]);
					}
					break;
				case 6:
					stream.writeUTF(this.stringData);
					break;
				case 7:
					for(int i = 0; i < this.dataLength; i++){
						stream.writeUTF(this.stringArrData[i]);
					}
					break;
				case 8:
					stream.writeLong(this.longData);
					break;
				case 9:
					for(int i = 0; i < this.dataLength; i++){
						stream.writeLong(this.longArrData[i]);
					}
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setData(SaveDataObject obj){
		if(obj.type == SaveDataType.BOOLEAN){
			this.setData(obj.boolData);
		} else if(obj.type == SaveDataType.INTEGER){
			this.setData(obj.intData);
		} else if(obj.type == SaveDataType.DOUBLE){
			this.setData(obj.doubleData);
		} else if(obj.type == SaveDataType.STRING){
			this.setData(obj.stringData);
		} else if(obj.type == SaveDataType.LONG){
			this.setData(obj.longData);
		} else if(obj.type == SaveDataType.BOOLEAN_ARRAY){
			this.setData(obj.boolArrData);
		} else if(obj.type == SaveDataType.INTEGER_ARRAY){
			this.setData(obj.intArrData);
		} else if(obj.type == SaveDataType.DOUBLE_ARRAY){
			this.setData(obj.doubleArrData);
		} else if(obj.type == SaveDataType.STRING_ARRAY){
			this.setData(obj.stringArrData);
		} else if(obj.type == SaveDataType.LONG_ARRAY){
			this.setData(obj.longArrData);
		} 
	}
	
	public void setData(boolean data){
		this.boolData = data;
		this.type = SaveDataType.BOOLEAN;
		this.dataLength = 1;
	}
	
	public void setData(int data){
		this.intData = data;
		this.type = SaveDataType.INTEGER;
		this.dataLength = 1;
	}
	
	public void setData(double data){
		this.doubleData = data;
		this.type = SaveDataType.DOUBLE;
		this.dataLength = 1;
	}
	
	public void setData(String data){
		this.stringData = data;
		this.type = SaveDataType.STRING;
		this.dataLength = 1;
	}
	
	public void setData(long data){
		this.longData = data;
		this.type = SaveDataType.LONG;
		this.dataLength = 1;
	}
	
	public void setData(boolean[] data){
		this.boolArrData = data;
		this.type = SaveDataType.BOOLEAN_ARRAY;
		this.dataLength = data.length;
	}
	
	public void setData(int[] data){
		this.intArrData = data;
		this.type = SaveDataType.INTEGER_ARRAY;
		this.dataLength = data.length;
	}
	
	public void setData(double[] data){
		this.doubleArrData = data;
		this.type = SaveDataType.DOUBLE_ARRAY;
		this.dataLength = data.length;
	}
	
	public void setData(String[] data){
		this.stringArrData = data;
		this.type = SaveDataType.STRING_ARRAY;
		this.dataLength = data.length;
	}
	
	public void setData(long[] data){
		this.longArrData = data;
		this.type = SaveDataType.LONG_ARRAY;
		this.dataLength = data.length;
	}
	
	public boolean GetDataBoolean(){
		try{
			if(this.type == SaveDataType.BOOLEAN){
				return this.boolData;
			} else {
				throw new Exception("Record Data is in different format");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int GetDataInteger(){
		try{
			if(this.type == SaveDataType.INTEGER){
				return this.intData;
			} else {
				throw new Exception("Record Data is in different format");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public double GetDataDouble(){
		try{
			if(this.type == SaveDataType.DOUBLE){
				return this.doubleData;
			} else {
				throw new Exception("Record Data is in different format");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public long GetDataLong(){
		try{
			if(this.type == SaveDataType.LONG){
				return this.longData;
			} else {
				throw new Exception("Record Data is in different format");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String GetDataString(){
		try{
			if(this.type == SaveDataType.STRING){
				return this.stringData;
			} else {
				throw new Exception("Record Data is in different format");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public boolean[] GetDataBooleanArray(){
		try{
			if(this.type == SaveDataType.BOOLEAN_ARRAY){
				return this.boolArrData;
			} else {
				throw new Exception("Record Data is in different format");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int[] GetDataIntegerArray(){
		try{
			if(this.type == SaveDataType.INTEGER_ARRAY){
				return this.intArrData;
			} else {
				throw new Exception("Record Data is in different format");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public double[] GetDataDoubleArray(){
		try{
			if(this.type == SaveDataType.DOUBLE_ARRAY){
				return this.doubleArrData;
			} else {
				throw new Exception("Record Data is in different format");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] GetDataStringArray(){
		try{
			if(this.type == SaveDataType.STRING_ARRAY){
				return this.stringArrData;
			} else {
				throw new Exception("Record Data is in different format");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public long[] GetDataLongArray(){
		try{
			if(this.type == SaveDataType.LONG_ARRAY){
				return this.longArrData;
			} else {
				throw new Exception("Record Data is in different format");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
