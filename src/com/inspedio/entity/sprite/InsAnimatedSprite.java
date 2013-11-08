package com.inspedio.entity.sprite;

import java.util.Vector;

import com.inspedio.entity.InsSprite;
import com.inspedio.entity.primitive.InsCallback;
import com.inspedio.system.core.InsGlobal;
import com.inspedio.system.helper.extension.InsAnim;

/**
 * This is <code>InsObject</code> with ability to do animation.
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsAnimatedSprite extends InsSprite{

	/**
	 * List of animation stored in this object
	 */
	protected Vector animations;
	/**
	 * Which animation currently played
	 */
	public InsAnim currentAnimation;	
	
	public InsAnimatedSprite(String spritePath)
	{
		super(spritePath);
		this.initAnimation();
	}
	
	public InsAnimatedSprite(String spritePath, int X, int Y)
	{
		super(spritePath, X, Y);
		this.initAnimation();
	}
	
	public InsAnimatedSprite(String spritePath, int X, int Y, int Width, int Height)
	{
		super(spritePath, X, Y, Width, Height);
		this.initAnimation();
	}
		
	private void initAnimation()
	{
		this.animations = new Vector();
		this.currentAnimation = null;
		
	}
	
	public void destroy()
	{
		this.animations = null;
		this.currentAnimation = null;
	}
	
	/**
	 * Pre-update is called right before <code>update()</code> on each object in the game loop.
	 */
	public void preUpdate()
	{
		if(!InsGlobal.paused)
		{
			this.nextFrame();
		}
	}
	
	public boolean addAnimation(String Name, int[] FrameSequence)
	{
		return this.addAnimation(Name, FrameSequence, 0, null);
	}
	
	public boolean addAnimation(String Name, int[] FrameSequence, int FrameDelay)
	{
		return this.addAnimation(Name, FrameSequence, FrameDelay, null);
	}
	
	/**
	 * Add animation to animation list. 
	 * If there is already animation with that name, it become failed
	 * 
	 * @param	Name			Name of Animation
	 * @param	FrameSequence	Array of Integer which represent Frame Sequence (Frame start with index 0)
	 * @param	Callback		Callback Method to be played everytime animation ends. You can use null if you don't need callback 
	 * 
	 * @return true if success, false otherwise
	 */
	public boolean addAnimation(String Name, int[] FrameSequence, int FrameDelay, InsCallback Callback)
	{
		int idx = this.checkAnimation(Name);
		if(idx == -1)
		{
			this.animations.addElement(new InsAnim(Name, FrameSequence, FrameDelay, Callback));
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Find animation with given name, return -1 if not found
	 * 
	 * @return	index of animation found
	 */
	public int checkAnimation(String Name)
	{
		int foundIdx = -1;
		for(int i = 0; i < this.animations.size(); i++)
		{
			InsAnim a = (InsAnim) this.animations.elementAt(i);
			if(a.name.equals(Name))
			{
				foundIdx = i;
				break;
			}
		}
		return foundIdx;
	}
	
	/**
	 * Play Animation from <code>animations</code>.
	 * If animation cannot be found or animation already played, do nothing
	 * 
	 * @return	true if animation exist and not currently played, false otherwise
	 */
	public boolean playAnimation(String Name)
	{
		int idx = this.checkAnimation(Name);
		if(idx != -1)
		{
			if(this.currentAnimation == null)
			{
				this.currentAnimation = (InsAnim) this.animations.elementAt(idx);
				this.currentAnimation.currentFrame = 0;
				this.setFrame(this.currentAnimation.getFrame());
				return true;
			}
			else
			{
				if(this.currentAnimation.name.equals(Name))
				{
					return false;
				}
				else
				{
					this.currentAnimation = (InsAnim) this.animations.elementAt(idx);
					this.currentAnimation.currentFrame = 0;
					this.setFrame(this.currentAnimation.getFrame());
					return true;
				}
			}
		}
		else
		{
			return false;
		}
	}
	
	public boolean stopAnimation(String Name){
		if(this.currentAnimation != null)	
		{
			if(this.currentAnimation.name.equals(Name))
			{
				this.currentAnimation = null;
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * Set frame to next frame in animation sequence
	 */
	public void nextFrame()
	{
		if(this.currentAnimation != null)
		{
			this.setFrame(this.currentAnimation.nextFrame());
		}
	}
}
