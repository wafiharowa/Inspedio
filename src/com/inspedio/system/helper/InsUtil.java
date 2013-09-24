package com.inspedio.system.helper;

import com.inspedio.entity.InsBasic;
import com.inspedio.enums.CollisionType;

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
}
