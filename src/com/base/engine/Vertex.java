package com.base.engine;

public class Vertex {
	
	public static int SIZE = 5;

	private Vector3f pos;
	private Vector2f textCoord;

	public Vertex(Vector3f p)
	{
		this(p, new Vector2f(0,0));
	}
	
	public Vertex(Vector3f p, Vector2f tc)
	{
		pos = p;
		textCoord = tc;
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
}
