package com.inspedio.core;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

import com.inspedio.helper.primitive.InsPoint;

/**
 * <code>InsCanvas</code> is how engine render its graphic.<br>
 * It also function as an input detector.
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsCanvas extends GameCanvas{

	public static final int PORTRAIT = 0;
	public static final int LANDSCAPE = 1;
	
	public static final int COLOR_BLACK = 0x000000;
	public static final int COLOR_RED = 0xFF0000;
	public static final int COLOR_WHITE = 0xFFFFFF;
	public static final int COLOR_YELLOW = 0xCCCC33;
	
	public static int FPS_COLOR = COLOR_BLACK;
	
	
	public static final Font defaultFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
	
	public InsGame game;
	public Graphics graphic;
	public int deviceWidth;
	public int deviceHeight;
	public int deviceOrientation;
	
	/**
	 * Construct new InsCanvas object
	 */
	protected InsCanvas(InsGame Game) {
		super(false);
		this.setFullScreenMode(true);
		this.game = Game;
		this.graphic = getGraphics();
		this.setScreenSize(getWidth(), getHeight());
	}
	
	protected void sizeChanged(int width, int height)
	{
		this.setScreenSize(width, height);
	}
	
	protected void setScreenSize(int width, int height)
	{
		InsGlobal.screenWidth = this.deviceWidth = width;
		InsGlobal.screenHeight = this.deviceHeight = height;
		
		System.out.println("Screen Width : " + InsGlobal.screenWidth);
		System.out.println("Screen Height : " + InsGlobal.screenHeight);
	}
	
	protected InsPoint transformPoint(int X, int Y){
		InsPoint p = new InsPoint();
		
		return p;
	}
	
	/**
	 * Clear Screen. Self explanatory
	 */
	public void clearScreen()
	{
		this.graphic.setColor(COLOR_BLACK);
		this.graphic.fillRect(0, 0, this.deviceWidth, this.deviceHeight);
	}
	
	/**
	 * Draw current FPS into screen. Used only for debugging 
	 */
	public void drawFPS()
	{
		int offset = 15;
		this.graphic.setColor(FPS_COLOR);
		this.graphic.setFont(InsCanvas.defaultFont);
		this.graphic.drawString("FPS : " + InsGlobal.currentUPS + " / " + InsGlobal.currentFPS + " / " + InsGlobal.currentFrameSkip, 0, deviceHeight - (4 * offset), (Graphics.TOP|Graphics.LEFT));
		this.graphic.drawString("Process : " + InsGlobal.currentUpdateTime + " / " + InsGlobal.currentRenderTime, 0, deviceHeight - (3 * offset), (Graphics.TOP|Graphics.LEFT));
		this.graphic.drawString("Sleep : " + InsGlobal.currentSleepTime, 0, deviceHeight - (2 * offset), (Graphics.TOP|Graphics.LEFT));
		this.graphic.drawString("Time : " + InsGlobal.currentGameTime, 0, deviceHeight - offset, (Graphics.TOP|Graphics.LEFT));
	}
	
	protected void hideNotify()
	{
		InsGlobal.hideGame();
	}
	
	protected void showNotify()
	{
		InsGlobal.showGame();
	}
	
	protected void keyPressed(int keyCode)
	{
		super.keyPressed(keyCode);
		switch (keyCode)
		{
			case -6:
				InsGlobal.onLeftSoftKey();
				break;
			case -7:
				InsGlobal.onRightSoftKey();
				break;
		}
	}

	
	

}
