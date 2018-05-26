package com.base.components;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Attenuation;
import com.base.engine.rendering.Shader;

public class PointLight extends BaseLight
{	
	private static final int COLOR_DEPTH = 256;
	private Attenuation attenuation;
	private float range;
	
	public PointLight(Vector3f cl, float i, Attenuation atten)
	{
		super(cl, i);
		attenuation = atten;
		
		float a = attenuation.getExponent();
		float b = attenuation.getLinear();
		float c = attenuation.getConstant() - (COLOR_DEPTH * getIntensity() * getColor().max());
		
		range = (float)(-b + Math.sqrt(b * b - 4 * a * c) / (2*a));
		
		setShader(new Shader("forward-point"));
	}

	// GETTERS
	public float getRange() {
		return range;
	}
	
	public Attenuation getAttenuation() {
		return attenuation;
	}
	
	// SETTERS
	public void setRange(float range) {
		this.range = range;
	}
	
	
}
