package com.inspedio.samples;


import com.inspedio.enums.ScreenOrientation;
import com.inspedio.system.core.InsMain;
import com.inspedio.system.helper.InsLoader;
import com.inspedio.system.helper.InsSave;

public class MainSample extends InsMain{

	protected void init() {
		this.init(new SampleButtonState(), new InsLoader(), new InsSave("Runner"), ScreenOrientation.PORTRAIT);
	}

	

}
