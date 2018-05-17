package com.base.components;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.ForwardSpot;

public class SpotLight extends PointLight
{
	private float cutoff;
	
	public SpotLight(Vector3f c, float i, Vector3f atten, float cutoff) {
		super(c, i, atten);
		this.cutoff = cutoff;
		
		setShader(ForwardSpot.getInstance());
	}
	
	public Vector3f getDirection() {
		return getTransform().getTransformedRotation().getForward();
	}
	
	public float getCutoff() {
		return cutoff;
	}
	
	public void setCutoff(float cutoff) {
		this.cutoff = cutoff;
	}
}
