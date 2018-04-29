package com.base.engine;

public class PointLight 
{	
	private BaseLight base;
	private Attenuation atten;
	private Vector3f position;
	private float range;
	
	public PointLight(BaseLight bl, Attenuation at, Vector3f pos, float r)
	{
		base = bl;
		atten = at;
		position = pos;
		range = r;
	}
	public BaseLight getBase() {
		return base;
	}
	public void setBase(BaseLight base) {
		this.base = base;
	}
	public Attenuation getAtten() {
		return atten;
	}
	public void setAtten(Attenuation atten) {
		this.atten = atten;
	}
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public float getRange() {
		return range;
	}
	public void setRange(float range) {
		this.range = range;
	}
}
