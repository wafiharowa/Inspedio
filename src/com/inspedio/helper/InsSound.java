package com.inspedio.helper;

import java.io.InputStream;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

import com.inspedio.core.InsGlobal;

/**
 * This is a helper class for Playing Sound (BGM and SFX).<br>
 * While J2ME supports .midi, .wav, and .mp3, it is highly recommended to use only .midi for optimization purpose.
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsSound {

	public static final int BGM = -1;
	public static final int SFX = 1;
	
	public static final String AUDIO_MIDI = "audio/midi";
	public static final String AUDIO_WAV = "audio/x-wav";
	public static final String AUDIO_MP3 = "audio/mpeg";
	
	
	/**
	 * The file path to audio files
	 */
	public String filepath;
	/**
	 * Encoding type of audio
	 */
	public String encoding;
	/**
	 * Whether it is a BGM or SFX
	 */
	public int type;
	/**
	 * reference to audio class
	 */
	public Player player;
	
	/**
	 * Construct a new <code>InsSound</code> object with given audio and encoding type
	 * 
	 *  @param	audioPath		The path to audio file
	 *  @param	audioEncoding	Encoding Type of audio
	 *  @param	audioType		Whether it is BGM or SFX. -1 for BGM, 1 for SFX
	 */
	public InsSound(String audioPath, String audioEncoding, int audioType)
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
	      this.player = Manager.createPlayer(in, this.encoding);
	      this.player.setLoopCount(this.type);
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
	}
	
	/**
	 * Start the player
	 */
	public void start()
	{
		try
		{
			if(this.type == BGM)
			{
				if(!InsGlobal.muteBGM)
				{
					this.player.start();
				}
			}
			else if(this.type == SFX)
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
