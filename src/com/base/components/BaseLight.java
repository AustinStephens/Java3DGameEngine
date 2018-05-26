package com.base.components;

import com.base.engine.core.CoreEngine;
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
	
	// METHODS
	@Override
	public void addToEngine(CoreEngine engine) 
	{
		engine.getRenderingEngine().addLight(this);
	}
	
	public void addToRenderingEngine(RenderingEngine renderingEngine)
	{
		renderingEngine.addLight(this);
	}
	
	// GETTERS
	public Shader getShader() {
		return shader;
	}
	
	public Vector3f getColor() {
		return color;
	}
	
	public float getIntensity() {
		return intensity;
	}
	
	// SETTERS
	public void setShader(Shader s) {
		shader = s;
	}
	
	public void setColor(Vector3f color) {
		this.color = color;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}
}
