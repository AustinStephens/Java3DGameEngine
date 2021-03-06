package com.base.engine.rendering;

import java.util.HashMap;
import com.base.engine.rendering.resourceManagement.MappedValues;

public class Material extends MappedValues
{
	private HashMap<String, Texture> textureHash;
	

	public Material(Texture diffuse, float specularIntensity, float specularPower, Texture normal,
            Texture dispMap, float dispMapScale, float dispMapOffset)
	{
	super();
	textureHash = new HashMap<String, Texture>();
	addTexture("diffuse", diffuse);
	addFloat("specularIntensity", specularIntensity);
	addFloat("specularPower", specularPower);
	addTexture("normalMap", normal);
	addTexture("dispMap", dispMap);
	
	float baseBias = dispMapScale/2.0f;
	addFloat("dispMapScale", dispMapScale);
	addFloat("dispMapBias", -baseBias + baseBias * dispMapOffset);
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
