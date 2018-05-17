package com.base.engine.rendering;

import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;

public class Vertex {
	
	public static int SIZE = 8;

	private Vector3f position;
	private Vector2f textCoord;
	private Vector3f normal;

	public Vertex(Vector3f p)
	{
		this(p, new Vector2f());
	}
	
	public Vertex(Vector3f p, Vector2f tc)
	{
		this(p, tc, new Vector3f());
	}
	
	public Vertex(Vector3f p, Vector2f tc, Vector3f norm)
	{
		position = p;
		textCoord = tc;
		normal = norm;
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f pos) {
		this.position = pos;
	}

	public Vector2f getTextCoord() {
		return textCoord;
	}

	public void setTextCoord(Vector2f textCoord) {
		this.textCoord = textCoord;
	}

	public Vector3f getNormal() {
		return normal;
	}

	public void setNormal(Vector3f normal) {
		this.normal = normal;
	}
}
