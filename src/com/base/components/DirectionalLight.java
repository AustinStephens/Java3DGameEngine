package com.base.components;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.ForwardDirectional;

public class DirectionalLight extends BaseLight
{
	
	public DirectionalLight(Vector3f c, float i)
	{
		super(c, i);
		setShader(ForwardDirectional.getInstance());
	}

	public Vector3f getDirection() {
		return getTransform().getTransformedRotation().getForward();
	}
}
