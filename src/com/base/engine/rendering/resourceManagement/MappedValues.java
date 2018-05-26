package com.base.engine.rendering.resourceManagement;

import java.util.HashMap;

import com.base.engine.core.Vector3f;

public abstract class MappedValues 
{
	private HashMap<String, Vector3f> vectorHash;
	private HashMap<String, Float> floatHash;
	
	public MappedValues()
	{
		vectorHash = new HashMap<String, Vector3f>();
		floatHash = new HashMap<String, Float>();
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
