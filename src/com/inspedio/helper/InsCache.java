package com.inspedio.helper;

import java.util.Vector;

import com.inspedio.basic.InsImage;
import com.inspedio.basic.InsSprite;

/**
 * This is helper class for caching assets
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsCache {

	public Vector spriteList;
	public Vector soundList;
	public Vector imageList;
	
	/**
	 * Construct InsAssets
	 */
	public InsCache()
	{
		this.spriteList = new Vector();
		this.soundList = new Vector();
		this.imageList = new Vector();
	}
	
	/**
	 * Remove all Cache
	 */
	public void clearCache()
	{
		this.spriteList.removeAllElements();
		this.soundList.removeAllElements();
		this.imageList.removeAllElements();
	}
	
	/**
	 * Add Sprite to SpriteList.
	 * If there are already Sprite with same path, width, and height, return that Sprite
	 * if there isn't any, create new Sprite, add it to list and return it
	 * 
	 * @param	imagePath		Path to SpriteSheet Image
	 * @param	frameWidth		The Width of each frame in this sprite
	 * @param	frameHeight		The Height of each frame in this sprite
	 * 
	 * @return	The Sprite with given imagePath, frameWidth, and frameHeight
	 */
	public InsSprite getSprite(String imagePath, int frameWidth, int frameHeight)
	{
		InsSprite s = null;
		try
		{
			int idx = this.checkSprite(imagePath, frameWidth, frameHeight);
			if(idx == -1)
			{
				s = new InsSprite(imagePath, frameWidth, frameHeight);
				this.spriteList.addElement(s);
			}
			else
			{
				s = (InsSprite) this.spriteList.elementAt(idx);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return s;
	}
	
	/**
	 * Delete Sprite from SpriteList.
	 * If there are already Sprite with same path, width, and height, return that Sprite
	 * if there isn't any, create new Sprite, add it to list and return it
	 * 
	 * @param	imagePath		Path to SpriteSheet Image
	 * @param	frameWidth		The Width of each frame in this sprite
	 * @param	frameHeight		The Height of each frame in this sprite
	 * 
	 * @return	TRUE if Sprite is found and deleted, FALSE otherwise
	 */
	public boolean deleteSprite(String imagePath, int frameWidth, int frameHeight)
	{
		boolean deleted = false;
		try
		{
			int idx = this.checkSprite(imagePath, frameWidth, frameHeight);
			if(idx == -1)
			{
				deleted = false;
			}
			else
			{
				this.spriteList.removeElementAt(idx);
				deleted = true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return deleted;
	}
	
	/**
	 * Check whether the sprite with given attributes are already created in SpriteList
	 * 
	 * @param	imagePath		Path to SpriteSheet Image
	 * @param	frameWidth		The Width of each frame in this sprite
	 * @param	frameHeight		The Height of each frame in this sprite
	 * 
	 * @return	Index of Sprite found, or -1 if nothing found
	 */
	protected int checkSprite(String imagePath, int frameWidth, int frameHeight)
	{
		int foundIdx = -1;
		for(int i = 0; i < this.spriteList.size(); i++)
		{
			InsSprite s = (InsSprite) this.spriteList.elementAt(i);
			if(s.filePath.equals(imagePath) && (s.frameWidth == frameWidth) && (s.frameHeight == frameHeight))
			{
				foundIdx = i;
				break;
			}
		}
		
		return foundIdx;
	}
	
	/**
	 * Add Sound to SoundList.
	 * If there are already Sound with same path, encode and type, return that Sound
	 * if there isn't any, create new Sound, add it to list and return it
	 * 
	 * @param	audioPath		The path to audio file
	 * @param	audioEncoding	Encoding Type of audio
	 * @param	audioType		Whether it is BGM or SFX. -1 for BGM, 1 for SFX
	 * 
	 * @return	The Sound with given parameters
	 */
	public InsSound getSound(String audioPath, String audioEncoding, int audioType)
	{
		InsSound s = null;
		try
		{
			int idx = this.checkSound(audioPath, audioEncoding, audioType);
			if(idx == -1)
			{
				s = new InsSound(audioPath, audioEncoding, audioType);
				this.soundList.addElement(s);
			}
			else
			{
				s = (InsSound) this.soundList.elementAt(idx);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return s;
	}
	
	/**
	 * Delete Sound from SoundList.
	 * 
	 * @param	audioPath		The path to audio file
	 * @param	audioEncoding	Encoding Type of audio
	 * @param	audioType		Whether it is BGM or SFX. -1 for BGM, 1 for SFX
	 * 
	 * @return	TRUE if Sound is found and deleted, FALSE otherwise
	 */
	public boolean deleteSound(String audioPath, String audioEncoding, int audioType)
	{
		boolean deleted = false;
		try
		{
			int idx = this.checkSound(audioPath, audioEncoding, audioType);
			if(idx == -1)
			{
				deleted = false;
			}
			else
			{
				this.soundList.removeElementAt(idx);
				deleted = true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return deleted;
	}
	
	/**
	 * Check whether the sound with given attributes are already created in SoundList
	 * 
	 * @param	audioPath		The path to audio file
	 * @param	audioEncoding	Encoding Type of audio
	 * @param	audioType		Whether it is BGM or SFX. -1 for BGM, 1 for SFX
	 * 
	 * @return	Index of Sound found, or -1 if nothing found
	 */
	protected int checkSound(String audioPath, String audioEncoding, int audioType)
	{
		int foundIdx = -1;
		for(int i = 0; i < this.soundList.size(); i++)
		{
			InsSound s = (InsSound) this.soundList.elementAt(i);
			if(s.filepath.equals(audioPath) && s.encoding.equals(audioEncoding) && (s.type == audioType))
			{
				foundIdx = i;
				break;
			}
		}
		
		return foundIdx;
	}
	
	/**
	 * Add Image to ImageList.
	 * If there are already Image with same path, return that Image
	 * if there isn't any, create new Image, add it to list and return it
	 * 
	 * @param	imagePath		Path to Image
	 * 
	 * @return	The Image with given imagePath
	 */
	public InsImage getImage(String imagePath)
	{
		InsImage i = null;
		try
		{
			int idx = this.checkImage(imagePath);
			if(idx == -1)
			{
				i = new InsImage(imagePath);
				this.imageList.addElement(i);
			}
			else
			{
				i = (InsImage) this.imageList.elementAt(idx);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return i;
	}
	
	/**
	 * Delete Image from ImageList.
	 * 
	 * @param	imagePath		Path to Image
	 * 
	 * @return	TRUE if Image is found and deleted, FALSE otherwise
	 */
	public boolean deleteImage(String imagePath)
	{
		boolean deleted = false;
		try
		{
			int idx = this.checkImage(imagePath);
			if(idx == -1)
			{
				deleted = false;
			}
			else
			{
				this.imageList.removeElementAt(idx);
				deleted = true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return deleted;
	}
	
	/**
	 * Check whether the image with given attributes are already created in ImageList
	 * 
	 * @param	imagePath		Path to Image
	 * 
	 * @return	Index of Image found, or -1 if nothing found
	 */
	protected int checkImage(String imagePath)
	{
		int foundIdx = -1;
		for(int i = 0; i < this.imageList.size(); i++)
		{
			InsImage img = (InsImage) this.imageList.elementAt(i);
			if(img.filepath.equals(imagePath))
			{
				foundIdx = i;
				break;
			}
		}
		
		return foundIdx;
	}
}
