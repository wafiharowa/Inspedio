package com.inspedio.system.helper;

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
}
