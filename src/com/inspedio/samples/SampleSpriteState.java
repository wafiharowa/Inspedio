package com.inspedio.samples;

import com.inspedio.actions.Delay;
import com.inspedio.actions.MoveBy;
import com.inspedio.actions.MoveTo;
import com.inspedio.entity.InsState;
import com.inspedio.entity.sprite.InsAnimatedSprite;
import com.inspedio.enums.KeyCode;
import com.inspedio.system.core.InsGlobal;
import com.inspedio.system.helper.InsKeys;
import com.inspedio.system.helper.InsUtil;

public class SampleSpriteState extends InsState{

	InsAnimatedSprite sprite;
	
	public void create(){
		sprite = new InsAnimatedSprite("/com/inspedio/sample/sprite.png", InsGlobal.screenWidth / 2, InsGlobal.screenHeight / 2, 32, 48);
		
		int frameDelay = 5;
		
		sprite.addAnimation("DOWN", new int[]{0, 1, 2, 3}, frameDelay);
		sprite.addAnimation("LEFT", new int[]{4, 5, 6, 7}, frameDelay);
		sprite.addAnimation("RIGHT", new int[]{8, 9, 10, 11, frameDelay});
		sprite.addAnimation("UP", new int[]{12, 13, 14, 15}, frameDelay);
		sprite.addAnimation("IDLE", new int[]{0});
		
		this.add(sprite);
	}
	
	public boolean onPointerPressed(int X, int Y) {
		this.sprite.setAction(MoveTo.create(this.sprite, 25, X, Y, null), true);
		
		int deltaX = X - sprite.position.x;
		int deltaY = Y - sprite.position.y;
		if(InsUtil.Absolute(deltaX) >= InsUtil.Absolute(deltaY)){
			if(deltaX > 0) {sprite.playAnimation("RIGHT");}
			else {sprite.playAnimation("LEFT");}
		} else {
			if(deltaY > 0) {sprite.playAnimation("DOWN");}
			else {sprite.playAnimation("UP");}
		}
		
		return super.onPointerPressed(X, Y);
	}
	
	public void handleKeyEvent(InsKeys key)
	{
		
		if(key.keyPressed(KeyCode.UP)){
			sprite.playAnimation("UP");
			sprite.setAction(MoveBy.create(25, 0, -100, null));
		}
		
		if(key.keyPressed(KeyCode.DOWN)){
			sprite.playAnimation("DOWN");
			sprite.setAction(MoveBy.create(25, 0, 100, null));
		}
		
		if(key.keyPressed(KeyCode.LEFT)){
			sprite.playAnimation("LEFT");
			sprite.combineAction(MoveBy.create(25, -100, 0, null));
		}
		
		if(key.keyPressed(KeyCode.RIGHT)){
			sprite.playAnimation("RIGHT");
			sprite.combineAction(MoveBy.create(25, 100, 0, null));
		}
		
		if(sprite.setAction(Delay.create(0))){
			sprite.playAnimation("IDLE");
		}
	}
}
