package com.base.engine.rendering.meshLoading;

public class OBJIndex 
{
	public int vertexIndex;
	public int texCoordIndex;
	public int normalIndex;
	
	@Override
	public boolean equals(Object obj)
	{
		OBJIndex index = (OBJIndex)obj;
		
		return vertexIndex == index.vertexIndex &&
				texCoordIndex == index.texCoordIndex &&
				normalIndex == index.normalIndex;
	}
	
	@Override
	public int hashCode()
	{
		final int BASE = 17;
		final int MULT = 31;
		
		int result = BASE;
		result = MULT * result + vertexIndex ;
		result = MULT * result + texCoordIndex;
		result = MULT * result + normalIndex;
		
		return result;
	}
}
