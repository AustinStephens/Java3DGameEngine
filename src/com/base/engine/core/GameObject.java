package com.base.engine.core;

import java.util.ArrayList;

import com.base.components.GameComponent;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

public class GameObject 
{
	private ArrayList<GameObject> children;
	private ArrayList<GameComponent> components;
	private Transform transform;
	private CoreEngine engine;
	
	public GameObject()
	{
		children = new ArrayList<GameObject>();
		components = new ArrayList<GameComponent>();
		transform = new Transform();
		engine = null;
	}
	
	// METHODS
	public GameObject addChild(GameObject child)
	{
		children.add(child);
		child.setEngine(engine);
		child.getTransform().setParent(transform);
		
		return this;
	}
	
	public GameObject addComponent(GameComponent comp)
	{
		components.add(comp);
		comp.setParent(this);
		
		return this;
	}
	
	public void inputAll(float delta)
	{
		input(delta);
		
		for(GameObject child : children)
			child.inputAll(delta);
	}
	
	public void updateAll(float delta)
	{
		update(delta);
		
		for(GameObject child : children)
			child.updateAll(delta);
	}
	
	public void renderAll(Shader shader, RenderingEngine renderingEngine)
	{
		render(shader, renderingEngine);
		
		for(GameObject child : children)
			child.renderAll(shader, renderingEngine);
	}
	
	public void input(float delta)
	{
		transform.update();
		for(GameComponent component : components)
			component.input(delta);
	}
	
	public void update(float delta)
	{
		for(GameComponent component : components)
			component.update(delta);
	}
	
	public void render(Shader shader, RenderingEngine renderingEngine)
	{
		for(GameComponent component : components)
			component.render(shader, renderingEngine);
	}
	
	// GETTERS
	public ArrayList<GameObject> getAllAttached()
	{
		ArrayList<GameObject> result = new ArrayList<GameObject>();
		
		for(GameObject child : children)
			result.addAll(child.getAllAttached());
		
		result.add(this);
		return result;
	}
	public Transform getTransform()
	{
		return transform;
	}
	
	// SETTERS
	public void setEngine(CoreEngine engine)
	{
		if(this.engine != engine)
		{
			this.engine = engine;
			for(GameComponent component : components)
				component.addToEngine(engine);
			
			for(GameObject child : children)
				child.setEngine(engine);
		}	
	}
}
