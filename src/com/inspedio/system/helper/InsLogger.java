package com.inspedio.system.helper;

import com.inspedio.enums.LogLevel;

public class InsLogger {
	
	protected static int logLevel = 1;

	public static void writeLog(String Message, LogLevel level){
		if(level.getValue() <= logLevel){
			System.out.println(Message);
		}	
	}
	
	public static void writeLog(String Message){
		writeLog(Message, LogLevel.INFO);
	}
	
	public static void setLogLevel(LogLevel level){
		logLevel = level.getValue();
	}
}
