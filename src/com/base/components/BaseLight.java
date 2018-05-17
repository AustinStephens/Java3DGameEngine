package com.base.components;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

public class BaseLight extends GameComponent
{
	private Vector3f color;
	private float intensity;
	private Shader shader;
	
	public BaseLight(Vector3f c, float i)
	{
		color = c;
		intensity = i;
	}
	
	public void setShader(Shader s)
	{
		shader = s;
	}
	
	public Shader getShader()
	{
		return shader;
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public float getIntensity() {
		return intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}
	
	public void addToRenderingEngine(RenderingEngine renderingEngine)
	{
		renderingEngine.addLight(this);
	}
}
