package com.base.engine;

public class Vertex {
	
	public static int SIZE = 8;

	private Vector3f pos;
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
		pos = p;
		textCoord = tc;
		normal = norm;
	}
	
	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
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
