package com.base.components;

import com.base.engine.core.CoreEngine;
import com.base.engine.core.GameObject;
import com.base.engine.core.Transform;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

public abstract class GameComponent 
{
	private GameObject parent;
	
	// METHODS
	public void input(float delta) {}
	public void update(float delta) {}
	
	public void render(Shader shader, RenderingEngine renderingEngine) {}
	public void addToEngine(CoreEngine engine) {}
	
	// GETTERS
	public Transform getTransform()
	{
		return parent.getTransform();
	}
	
	// SETTERS
	public void setParent(GameObject p)
	{
		parent = p;
	}
}
