package com.base.engine.rendering;

import java.util.HashMap;
import com.base.engine.rendering.resourceManagement.MappedValues;

public class Material extends MappedValues
{
	private HashMap<String, Texture> textureHash;
	

	public Material()
	{
		super();
		textureHash = new HashMap<String, Texture>();
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
	
	
}
