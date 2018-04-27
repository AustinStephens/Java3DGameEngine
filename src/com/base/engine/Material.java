package com.base.engine;

public class Material 
{
	private Texture texture;
	private Vector3f color;
	
	public Material(Texture t, Vector3f c)
	{
		texture = t;
		color = c;
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
}
