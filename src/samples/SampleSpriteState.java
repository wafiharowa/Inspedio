package samples;

import com.inspedio.actions.MoveTo;
import com.inspedio.entity.InsAnimatedSprite;
import com.inspedio.entity.InsState;
import com.inspedio.system.core.InsGlobal;

public class SampleSpriteState extends InsState{

	InsAnimatedSprite sprite;
	
	public void create(){
		sprite = new InsAnimatedSprite("sample/sprite/sprite.png", InsGlobal.screenWidth / 2, InsGlobal.screenHeight / 2, 32, 48);
		sprite.addAnimation("DOWN", new int[]{0, 1, 2, 3});
		sprite.addAnimation("LEFT", new int[]{4, 5, 6, 7});
		sprite.addAnimation("RIGHT", new int[]{8, 9, 10, 11});
		sprite.addAnimation("UP", new int[]{12, 13, 14, 15});
		
		this.add(sprite);
	}
	
	public boolean onPointerPressed(int X, int Y) {
		this.sprite.setAction(MoveTo.create(this.sprite, X, Y, 10, null));
		return super.onPointerPressed(X, Y);
	}
}
