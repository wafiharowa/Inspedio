package com.inspedio.system.helper;

import com.inspedio.entity.primitive.InsCallback;
import com.inspedio.system.core.InsGlobal;

/**
 * This class is used for doing lightweight loading <br>
 * This class can load small resources without changing screen to Loader
 */
public class InsHiddenLoader implements Runnable{

	/**
	 * Pointer to Thread
	 */
	Thread thread;
	
	InsCallback callback;
	
	public InsHiddenLoader(InsCallback C)
	{
		this.callback = C;
	}
	
	public void start()
	{
		this.thread  = new Thread(this);
		this.thread.start();
	}
	
	public void run()
	{
		InsGlobal.cache.clearCache();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.callback.call();
	}
}
