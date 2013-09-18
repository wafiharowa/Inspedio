package com.inspedio.system.helper.record;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.inspedio.enums.SaveDataType;

public class SaveDataObject {
	
	public SaveDataType type = null;
	public int nameLength;
	public int dataLength;
	public String name = null;
	
	private boolean boolData;
	private int intData;
	private float floatData;
	private String stringData;
	
	private boolean[] boolArrData = null;
	private int[] intArrData = null;
	private float[] floatArrData = null;
	private String[] stringArrData = null;
	
	public SaveDataObject(String Name){
		this.name = Name;
		this.nameLength = Name.length();
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
				case 1:
					this.intData = stream.readInt();
				case 2:
					this.floatData = stream.readFloat();
				case 3:
					this.stringData = stream.readUTF();
				case 4:
					this.boolArrData = new boolean[this.dataLength];
					for(int i = 0; i < this.dataLength; i++){
						this.boolArrData[i] = stream.readBoolean();
					}
				case 5:
					this.intArrData = new int[this.dataLength];
					for(int i = 0; i < this.dataLength; i++){
						this.intArrData[i] = stream.readInt();
					}
				case 6:
					this.floatArrData = new float[this.dataLength];
					for(int i = 0; i < this.dataLength; i++){
						this.floatArrData[i] = stream.readFloat();
					}
				case 7:
					this.stringArrData = new String[this.dataLength];
					for(int i = 0; i < this.dataLength; i++){
						this.stringArrData[i] = stream.readUTF();
					}
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
				case 1:
					stream.writeInt(this.intData);
				case 2:
					stream.writeFloat(this.floatData);
				case 3:
					stream.writeUTF(this.stringData);
				case 4:
					for(int i = 0; i < this.dataLength; i++){
						stream.writeBoolean(this.boolArrData[i]);
					}
				case 5:
					for(int i = 0; i < this.dataLength; i++){
						stream.writeInt(this.intArrData[i]);
					}
				case 6:
					for(int i = 0; i < this.dataLength; i++){
						stream.writeFloat(this.floatArrData[i]);
					}
				case 7:
					for(int i = 0; i < this.dataLength; i++){
						stream.writeUTF(this.stringArrData[i]);
					}
			
			}
		} catch (IOException e) {
			e.printStackTrace();
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
	
	public void setData(float data){
		this.floatData = data;
		this.type = SaveDataType.FLOAT;
		this.dataLength = 1;
	}
	
	public void setData(String data){
		this.stringData = data;
		this.type = SaveDataType.STRING;
		this.dataLength = data.length();
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
	
	public void setData(float[] data){
		this.floatArrData = data;
		this.type = SaveDataType.FLOAT_ARRAY;
		this.dataLength = data.length;
	}
	
	public void setData(String[] data){
		this.stringArrData = data;
		this.type = SaveDataType.STRING_ARRAY;
		this.dataLength = data.length;
	}
	
}
