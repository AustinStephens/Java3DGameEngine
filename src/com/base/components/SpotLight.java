package com.base.components;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Attenuation;
import com.base.engine.rendering.Shader;

public class SpotLight extends PointLight
{
	private float cutoff;
	
	public SpotLight(Vector3f c, float i, Attenuation atten, float cutoff) {
		super(c, i, atten);
		this.cutoff = cutoff;
		
		setShader(new Shader("forward-spot"));
	}
	
	// GETTERS
	public Vector3f getDirection() {
		return getTransform().getTransformedRotation().getForward();
	}
	
	public float getCutoff() {
		return cutoff;
	}
	
	// SETTERS
	public void setCutoff(float cutoff) {
		this.cutoff = cutoff;
	}
}
