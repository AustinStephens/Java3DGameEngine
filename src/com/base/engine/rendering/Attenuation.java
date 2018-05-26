package com.base.engine.rendering;

import com.base.engine.core.Vector3f;

public class Attenuation extends Vector3f
{

	public Attenuation(float c, float l, float e) {
		super(c,l,e);
	}
	
	// GETTERS
	public float getConstant()
	{
		return getX();
	}
	
	public float getLinear()
	{
		return getY();
	}
	
	public float getExponent()
	{
		return getZ();
	}
}
