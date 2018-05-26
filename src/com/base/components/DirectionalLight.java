package com.base.components;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Shader;

public class DirectionalLight extends BaseLight
{
	
	public DirectionalLight(Vector3f c, float i)
	{
		super(c, i);
		setShader(new Shader("forward-directional"));
	}

	// GETTERS
	public Vector3f getDirection() {
		return getTransform().getTransformedRotation().getForward();
	}
}
