package com.inspedio.enums;

import javax.microedition.lcdui.game.Sprite;

public class TransformType extends InsEnum{

	public static final TransformType NONE = new TransformType("NONE", Sprite.TRANS_NONE);
	public static final TransformType ROTATED_90 = new TransformType("ROTATED_90", Sprite.TRANS_ROT90);
	public static final TransformType ROTATED_180 = new TransformType("ROTATED_180", Sprite.TRANS_ROT180);
	public static final TransformType ROTATED_270 = new TransformType("ROTATED_270", Sprite.TRANS_ROT270);
	public static final TransformType MIRROR = new TransformType("MIRROR", Sprite.TRANS_MIRROR);	
	public static final TransformType MIRROR_ROTATED_90 = new TransformType("MIRROR_ROTATED_90", Sprite.TRANS_MIRROR_ROT90);
	public static final TransformType MIRROR_ROTATED_180 = new TransformType("MIRROR_ROTATED_180", Sprite.TRANS_MIRROR_ROT180);
	public static final TransformType MIRROR_ROTATED_270 = new TransformType("MIRROR_ROTATED_270", Sprite.TRANS_MIRROR_ROT270);
	
	protected TransformType(String Name, int Value) {
		super(Name, Value);
	}

	public static int getTransformValue(TransformType trans){
		if(trans != null){
			return trans.getValue();
		}
		return TransformType.NONE.getValue();
	}
	
	public static TransformType getMirror(TransformType Transform){
		if(Transform == TransformType.NONE){
			return TransformType.MIRROR;
		} else if(Transform == TransformType.ROTATED_90){
			return TransformType.MIRROR_ROTATED_90;
		} else if(Transform == TransformType.ROTATED_180){
			return TransformType.MIRROR_ROTATED_180;
		} else if(Transform == TransformType.ROTATED_270){
			return TransformType.MIRROR_ROTATED_270;
		} else if(Transform == TransformType.MIRROR){
			return TransformType.NONE;
		} else if(Transform == TransformType.MIRROR_ROTATED_90){
			return TransformType.ROTATED_90;
		} else if(Transform == TransformType.MIRROR_ROTATED_180){
			return TransformType.MIRROR_ROTATED_90;
		} else if(Transform == TransformType.ROTATED_90){
			return TransformType.MIRROR_ROTATED_90;
		}
		
		return TransformType.NONE;
	}
}
