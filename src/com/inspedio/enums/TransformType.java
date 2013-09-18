package com.inspedio.enums;

import javax.microedition.lcdui.game.Sprite;

public class TransformType extends InsEnum{

	public static TransformType NONE = new TransformType("NONE", Sprite.TRANS_NONE);
	public static TransformType ROTATED_90 = new TransformType("ROTATED_90", Sprite.TRANS_ROT90);
	public static TransformType ROTATED_180 = new TransformType("ROTATED_180", Sprite.TRANS_ROT180);
	public static TransformType ROTTED_270 = new TransformType("ROTATED_270", Sprite.TRANS_ROT270);
	public static TransformType MIRROR = new TransformType("MIRROR", Sprite.TRANS_MIRROR);	
	public static TransformType MIRROR_ROTATED_90 = new TransformType("MIRROR_ROTATED_90", Sprite.TRANS_MIRROR_ROT90);
	public static TransformType MIRROR_ROTATED_180 = new TransformType("MIRROR_ROTATED_180", Sprite.TRANS_MIRROR_ROT180);
	public static TransformType MIRROR_ROTATED_270 = new TransformType("MIRROR_ROTATED_270", Sprite.TRANS_MIRROR_ROT270);
	
	protected TransformType(String Name, int Value) {
		super(Name, Value);
	}

	public static int getTransformValue(TransformType trans){
		if(trans != null){
			return trans.getValue();
		}
		return TransformType.NONE.getValue();
	}
}
