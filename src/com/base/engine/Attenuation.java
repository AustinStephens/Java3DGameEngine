package com.base.engine;

public class Attenuation 
{
	private float constant;
	private float linear;
	private float exponent;
	
	public Attenuation(float c, float l, float e)
	{
		constant = c;
		linear = l;
		exponent = e;
	}
	
	public float getConstant() {
		return constant;
	}
	public void setConstant(float constant) {
		this.constant = constant;
	}
	public float getLinear() {
		return linear;
	}
	public void setLinear(float linear) {
		this.linear = linear;
	}
	public float getExponent() {
		return exponent;
	}
	public void setExponent(float exponent) {
		this.exponent = exponent;
	}
}
