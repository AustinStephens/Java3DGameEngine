package com.base.engine;

public class Material 
{
	private Texture texture;
	private Vector3f color;
	private float specularIntensity;
	private float specularPower;
	
	public Material(Texture t)
	{
		this(t, new Vector3f(1,1,1));
	}
	
	public Material(Texture t, Vector3f c)
	{
		this(t, c, 2, 32);
	}
	
	public Material(Texture t, Vector3f c, float spi, float spp)
	{
		texture = t;
		color = c;
		specularIntensity = spi;
		specularPower = spp;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public float getSpecularIntensity() {
		return specularIntensity;
	}

	public void setSpecularIntensity(float specularIntensity) {
		this.specularIntensity = specularIntensity;
	}

	public float getSpecularPower() {
		return specularPower;
	}

	public void setSpecularPower(float specularExponent) {
		this.specularPower = specularExponent;
	}
}
