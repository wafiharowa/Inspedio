package com.inspedio.samples;

import com.inspedio.entity.InsState;
import com.inspedio.entity.actions.MoveBy;
import com.inspedio.entity.actions.MoveTo;
import com.inspedio.entity.primitive.InsCallback;
import com.inspedio.entity.sprite.InsAnimatedSprite;
import com.inspedio.entity.ui.InsButton;
import com.inspedio.enums.FontSize;
import com.inspedio.enums.FontStyle;
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
		sprite.addAnimation("RIGHT", new int[]{8, 9, 10, 11}, frameDelay);
		sprite.addAnimation("UP", new int[]{12, 13, 14, 15}, frameDelay);
		sprite.addAnimation("IDLE", new int[]{0});
		
		this.add(sprite);
		
		InsButton back = new InsButton(30, InsGlobal.screenHeight - 20, 60, 40, "BACK", 0xFFFFFF);
		back.setBorder(0, 3);
		back.setCaption("BACK", 0, FontSize.LARGE, FontStyle.BOLD);
		back.setClickedCallback(new InsCallback() {
			public void call() {
				InsGlobal.switchState(new SampleButtonState(), false);
			}
		});
		
		this.add(back);
	}
	
	public void onLeftSoftKey()
	{
		InsGlobal.switchState(new SampleButtonState(), false);
	}
	
	public boolean onPointerPressed(int X, int Y) {
		this.sprite.setAction(MoveTo.create(this.sprite, 25, X, Y, null));
		
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
			sprite.setAction(MoveBy.create(10, 0, -100, null));
		}
		
		if(key.keyPressed(KeyCode.DOWN)){
			sprite.playAnimation("DOWN");
			sprite.setAction(MoveBy.create(10, 0, 100, null));
		}
		
		if(key.keyPressed(KeyCode.LEFT)){
			sprite.playAnimation("LEFT");
			sprite.setAction(MoveBy.create(10, -100, 0, null));
		}
		
		if(key.keyPressed(KeyCode.RIGHT)){
			sprite.playAnimation("RIGHT");
			sprite.setAction(MoveBy.create(10, 100, 0, null));
		}
		
		if(sprite.action == null){
			sprite.playAnimation("IDLE");
		}
	}
}
