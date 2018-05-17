package com.base.engine.rendering;

import java.util.HashMap;

import com.base.engine.core.Vector3f;

public class Material 
{
	private HashMap<String, Texture> textureHash;
	private HashMap<String, Vector3f> vectorHash;
	private HashMap<String, Float> floatHash;

	public Material()
	{
		textureHash = new HashMap<String, Texture>();
		vectorHash = new HashMap<String, Vector3f>();
		floatHash = new HashMap<String, Float>();
	}
	
	public void addTexture(String name, Texture texture)
	{
		textureHash.put(name, texture);
	}
	
	public Texture getTexture(String name)
	{
		Texture result = textureHash.get(name);
		if(result != null)
				return result;
		
		return new Texture("test.png");
	}
	
	public void addVector(String name, Vector3f v)
	{
		vectorHash.put(name, v);
	}
	
	public Vector3f getVector(String name)
	{
		Vector3f result = vectorHash.get(name);
		if(result != null)
			return result;
		
		return new Vector3f(0,0,0);
	}
	
	public void addFloat(String name, float texture)
	{
		floatHash.put(name, texture);
	}
	
	public float getFloat(String name)
	{
		Float result = floatHash.get(name);
		if(result != null)
			return result;
		
		return 0;
	}
}
