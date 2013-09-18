package com.inspedio.entity.primitive;

import com.inspedio.enums.HAlignment;
import com.inspedio.enums.VAlignment;

public interface Scalable {

	public void ScaleBy(int X, int Y, HAlignment horizontalAnchor, VAlignment verticalAnchor);
}
