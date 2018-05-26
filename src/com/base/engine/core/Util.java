package com.base.engine.core;

import java.nio.FloatBuffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

import com.base.engine.rendering.Vertex;

public class Util {
	
	// BUFFER METHODS
	public static FloatBuffer createFloatBuffer(int size)
	{
		return BufferUtils.createFloatBuffer(size);
	}
	
	public static IntBuffer createIntBuffer(int size)
	{
		return BufferUtils.createIntBuffer(size);
	}
	
	public static ByteBuffer createByteBuffer(int size)
	{
		return BufferUtils.createByteBuffer(size);
	}
	
	public static IntBuffer createFlippedBuffer(int... values)
	{
		IntBuffer buffer = createIntBuffer(values.length);

		buffer.put(values);
		buffer.flip();
		
		return buffer;
	}

	public static FloatBuffer createFlippedBuffer(Vertex[] vertices)
	{
		FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);

		for(int i = 0; i < vertices.length; ++i)
		{
			buffer.put(vertices[i].getPosition().getX());
			buffer.put(vertices[i].getPosition().getY());
			buffer.put(vertices[i].getPosition().getZ());
			buffer.put(vertices[i].getTextCoord().getX());
			buffer.put(vertices[i].getTextCoord().getY());
			buffer.put(vertices[i].getNormal().getX());
			buffer.put(vertices[i].getNormal().getY());
			buffer.put(vertices[i].getNormal().getZ());
			buffer.put(vertices[i].getTangent().getX());
			buffer.put(vertices[i].getTangent().getY());
			buffer.put(vertices[i].getTangent().getZ());
		}
		
		buffer.flip();
		return buffer;
	}
	
	public static FloatBuffer createFlippedBuffer(Matrix4f value)
	{
		FloatBuffer buffer = createFloatBuffer(16);
		
		for(int i = 0; i < 4; ++i) 
		{
			for(int j = 0; j < 4; ++j)
			{
				buffer.put(value.get(i, j));
			}
		}
		
		buffer.flip();
		return buffer;
	}
	
	// OTHER METHODS
	public static String[] removeEmptyStrings(String[] data)
	{
		ArrayList<String> result = new ArrayList<String>();
		
		for(int i = 0; i < data.length; ++i)
		{
			if(!data[i].equals("")) result.add(data[i]);
		}
		
		String[] res = new String[result.size()];
		result.toArray(res);
		
		return res;
	}
	
	public static int[] toIntArray(Integer[] data)
	{
		int[] result = new int[data.length];
		for(int i = 0; i < data.length; ++i)
		{
			result[i]  = data[i].intValue();
		}
		
		return result;
	}
}
