package com.base.engine;

public class Vector2f {

	private float x;
	private float y;
	
	public Vector2f(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2f()
	{
		x = 0;
		y = 0;
	}
	
	public float length()
	{
		return (float)Math.sqrt((x * x) + (y * y));
	}
	
	public float dot(Vector2f other)
	{
		return (x * other.getX()) + (y * other.getY());
	}
	
	public Vector2f normalize()
	{
		float l = length();

		return new Vector2f(x/l, y/l);
	}
	
	public Vector2f rotate(float angle)
	{
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		
		return new Vector2f((float)(x * cos - y * sin), (float)(y * cos + x * sin));
	}
	
	public Vector2f add(Vector2f other)
	{
		return new Vector2f(x + other.getX(), y + other.getY());
	}
	
	public Vector2f add(float c)
	{
		return new Vector2f(x + c, y + c);
	}
	
	public Vector2f sub(Vector2f other)
	{
		return new Vector2f(x - other.getX(), y - other.getY());
	}
	
	public Vector2f sub(float c)
	{
		return new Vector2f(x - c, y - c);
	}
	
	public Vector2f mult(Vector2f other)
	{
		return new Vector2f(x * other.getX(), y * other.getY());
	}
	
	public Vector2f mult(float c)
	{
		return new Vector2f(x * c, y * c);
	}
	
	public Vector2f div(Vector2f other)
	{
		return new Vector2f(x / other.getX(), y / other.getY());
	}
	
	public Vector2f div(float c)
	{
		return new Vector2f(x / c, y / c);
	}
	
	public Vector2f abs()
	{
		return new Vector2f(Math.abs(x), Math.abs(y));
	}
	
	public String toString()
	{
		return "(" + x + "," + y + ")";
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
