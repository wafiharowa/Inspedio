package com.inspedio.system.helper;

import java.io.InputStream;
import java.util.Vector;

import com.inspedio.entity.InsBasic;
import com.inspedio.enums.CollisionType;
import com.inspedio.system.core.InsGlobal;

public class InsUtil {

	
	public static int Max(int a, int b){
		return (a > b ? a : b);
	}
	
	public static int Min(int a, int b){
		return (a < b ? a : b);
	}
	
	public static int Absolute(int a){
		return (a > 0 ? a : -a);
	}
	
	public static int Exp2(int a){
		return (a * a);
	}
	
	public static int Floor(double a){
		return (int) Math.floor(a);
	}
	
	public static int Ceil(double a){
		return (int) Math.ceil(a);
	}
	
	/**
	 * Floor if Value < 0.5, Ceil if Value >= 0.5
	 */
	public static int Round(double a){
		int tmp = ((int) (a * 100)) % 100;
		if(tmp < 50){
			return Floor(a);
		} else {
			return Ceil(a);
		}
	}
	
	public static double Distance(int X1, int X2, int Y1, int Y2){
		return Math.sqrt(Exp2(X1-X2) + Exp2(Y1-Y2));
	}
	
	public static boolean isCollide(InsBasic obj1, InsBasic obj2, CollisionType Type){
		if(Type == CollisionType.BOX){
			return isCollideBox(obj1, obj2);
		} else if (Type == CollisionType.SPHERE){
			return isCollideSphere(obj1, obj2);
		}
		return false;
	}
	
	public static boolean isCollideBox(InsBasic obj1, InsBasic obj2){
		int X1 = obj1.getMiddleX();
		int Y1 = obj1.getMiddleY();
		int X2 = obj2.getMiddleX();
		int Y2 = obj2.getMiddleY();
		
		return (InsUtil.Absolute(X1 - X2) <= ((obj1.size.width + obj2.size.width) / 2) && InsUtil.Absolute(Y1 - Y2) <= ((obj1.size.height + obj2.size.height) / 2)) ;
	}
	
	public static boolean isCollideSphere(InsBasic obj1, InsBasic obj2){
		int X1 = obj1.getMiddleX();
		int Y1 = obj1.getMiddleY();
		int X2 = obj2.getMiddleX();
		int Y2 = obj2.getMiddleY();
		
		return (InsUtil.Distance(X1, Y1, X2, Y2) <= ((InsUtil.Max(obj1.size.width, obj1.size.height) + InsUtil.Max(obj2.size.width, obj2.size.height)) / 2));
	}
	
	/**
	 * Read External File and combine it into String
	 * 
	 * @param	filepath	External File path
	 * 
	 * @return	File Content in String
	 */
	public static String readFile(String filepath)
	{
		InputStream reader = InsGlobal.game.getClass().getResourceAsStream(filepath);
		StringBuffer sb = new StringBuffer();
		try
		{
			int chars = 0;
			while ((chars = reader.read()) != -1)
			{
				sb.append((char) chars);
			}
			reader.close();
			return sb.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/**
	 * Split String into several substring with given separator
	 * Separator must not be an empty String
	 * 
	 * @return		Array of splitted String
	 */
	public static String[] splitString(String inputString, String separator)
	{
		String str = inputString;
		int index = str.indexOf(separator);
		Vector nodes = new Vector();
		
		while (index >= 0)
		{
			nodes.addElement(str.substring(0, index));
			str = str.substring(index + separator.length());
			index = str.indexOf(separator);
		}
		
		// get the last node
		nodes.addElement(str);
		
		return InsUtil.vectorToStringArray(nodes);
	}
	
	/**
	 * Convert Vector into array of String
	 * 
	 * @return		Array of String
	 */
	public static String[] vectorToStringArray(Vector nodes)
	{
		String[] result = new String[0];
		if(nodes.size() > 0)
		{
			result = new String[nodes.size()];
			for(int i = 0; i < nodes.size(); i++)
			{
				result[i] = (String) nodes.elementAt(i);
			}
		}
		
		return result;
	}
	
	/**
	 * Combine several substring into one arge String
	 * 
	 * @return	combined String
	 */
	public static String combineString(String[] strArr, String glue)
	{
		String retval = "";
		
		for(int i = 0; i < strArr.length; i++)
		{
			retval += strArr[i];
			if(i < (strArr.length - 1))
			{	retval += glue;	}
		}
		
		return retval;
	}
	
	/**
	 * Remove whitespace from String
	 * 
	 * @return	trimmed String
	 */
	public static String trimString(String mystr)
	{
		String[] splitter = InsUtil.splitString(mystr, " ");
		mystr = InsUtil.combineString(splitter, "");
		
		return mystr;
	}
	
	/**
	 * Remove special character from String
	 * 
	 * @return	Formatted String
	 */
	public static String formatString(String mystr)
	{
		String[] splitter = InsUtil.splitString(mystr, "\n");
		mystr = InsUtil.combineString(splitter, "");
		
		splitter = InsUtil.splitString(mystr, "\r");
		mystr = InsUtil.combineString(splitter, "");
		
		return mystr;
	}
	
}
