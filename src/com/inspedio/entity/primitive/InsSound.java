package com.inspedio.entity.primitive;

import java.io.InputStream;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

import com.inspedio.enums.AudioEncode;
import com.inspedio.enums.AudioType;
import com.inspedio.system.core.InsGlobal;

/**
 * This class represent audio resource(BGM and SFX).<br>
 * While J2ME supports .midi, .wav, and .mp3, it is highly recommended to use only .midi for optimization purpose.
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsSound {

	/**
	 * The file path to audio files
	 */
	public String filepath;
	/**
	 * Encoding type of audio
	 */
	public AudioEncode encoding;
	/**
	 * Whether it is a BGM or SFX
	 */
	public AudioType type;
	/**
	 * reference to audio class
	 */
	public Player player;
	
	/**
	 * Construct a new <code>InsSound</code> object with given audio and encoding type
	 * 
	 *  @param	audioPath		The path to audio file
	 *  @param	audioEncoding	Encoding Type of audio
	 *  @param	audioType		Whether it is BGM or SFX.
	 */
	public InsSound(String audioPath, AudioEncode audioEncoding, AudioType audioType)
	{
		this.filepath = audioPath;
		this.encoding = audioEncoding;
		this.type = audioType;
		this.init();
	}
	
	public void init()
	{
		try
		{
	      InputStream in = getClass().getResourceAsStream(this.filepath);
	      this.player = Manager.createPlayer(in, this.encoding.toString());
	      this.player.setLoopCount(this.type.getValue());
	      this.player.realize();
	      this.player.prefetch();
	      
	      in.close();
	      in = null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void destroy()
	{
		this.player.deallocate();
		this.player.close();
		this.player = null;
		this.type = null;
		this.encoding = null;
		this.filepath = null;
	}
	
	/**
	 * Start the player
	 */
	public void start()
	{
		try
		{
			if(this.type == AudioType.BGM)
			{
				if(!InsGlobal.muteBGM)
				{
					this.player.start();
				}
			}
			else if(this.type == AudioType.SFX)
			{
				if(!InsGlobal.muteSFX)
				{
					this.player.start();
				}
			}
		}
		catch (MediaException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Stop the player
	 */
	public void stop()
	{
		try
		{
			this.player.stop();
		}
		catch (MediaException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Reset the player
	 */
	public void reset()
	{
		try
		{
			this.player.setMediaTime(-1);
		}
		catch (MediaException e)
		{
			e.printStackTrace();
		}
	}
}
