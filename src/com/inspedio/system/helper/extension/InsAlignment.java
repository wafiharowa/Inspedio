package com.inspedio.system.helper.extension;

import com.inspedio.enums.HAlignment;
import com.inspedio.enums.VAlignment;

public class InsAlignment {

	public VAlignment vertical;
	public HAlignment horizontal;
	
	public InsAlignment(){
		this.setAlignment(HAlignment.CENTER, VAlignment.MIDDLE);
	}
	
	public InsAlignment(HAlignment hor, VAlignment ver){
		this.setAlignment(hor, ver);
	}
	
	public void setAlignment(HAlignment hor, VAlignment ver){
		this.vertical = ver;
		this.horizontal = hor;
	}
	
	public int getAnchorValue(){
		return (this.vertical.anchor | this.horizontal.anchor);
	}
	
	public static int getAnchorValue(InsAlignment align){
		if(align != null){
			return align.getAnchorValue();
		}
		return 0;
	}
}
