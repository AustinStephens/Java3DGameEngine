package com.base.engine.core;

public class Vector3f {

	private float x;
	private float y;
	private float z;
	
	public Vector3f(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f()
	{
		x = 0;
		y = 0;
		z = 0;
	}
	
	// METHODS
	public float length()
	{
		return (float)Math.sqrt((x * x) + (y * y) + (z * z));
	}
	
	public float max()
	{
		return Math.max(x, Math.max(y,z));
	}
	
	
	public float dot(Vector3f other)
	{
		return (x * other.getX()) + (y * other.getY() + (z * other.getZ()));
	}
	
	public Vector3f cross(Vector3f other)
	{
		float x_ = y * other.getZ() - z * other.getY();
		float y_ = z * other.getX() - x * other.getZ();
		float z_ = x * other.getY() - y * other.getX();
		
		return new Vector3f(x_, y_, z_);
	}
	
	public Vector3f normalize()
	{
		float l = length();

		return new Vector3f(x/l, y/l, z/l);
	}
	
	public Vector3f rotate(float angle, Vector3f axis)
	{
		float sinAngle = (float)Math.sin(-angle);
		float cosAngle = (float)Math.cos(-angle);
		
		return this.cross(axis.mult(sinAngle)).add(           //Rotation on local X
				(this.mult(cosAngle)).add(                     //Rotation on local Z
				 axis.mult(this.dot(axis.mult(1 - cosAngle))))); //Rotation on local Y

	}
	
	public Vector3f rotate(Quaternion rotation)
	{
		Quaternion conjugate = rotation.conjugate();

		Quaternion w = rotation.mult(this).mult(conjugate);

		return new Vector3f(w.getX(), w.getY(), w.getZ());
	}
	
	public Vector3f interpolate(Vector3f dest, float factor)
	{
		return dest.sub(this).mult(factor).add(this);
	}
	
	public Vector3f add(Vector3f other)
	{
		return new Vector3f(x + other.getX(), y + other.getY(), z + other.getZ());
	}
	
	public Vector3f add(float c)
	{
		return new Vector3f(x + c, y + c, z + c);
	}
	
	public Vector3f sub(Vector3f other)
	{
		return new Vector3f(x - other.getX(), y - other.getY(),  z - other.getZ());
	}
	
	public Vector3f sub(float c)
	{
		return new Vector3f(x - c, y - c, z - c);
	}
	
	public Vector3f mult(Vector3f other)
	{
		return new Vector3f(x * other.getX(), y * other.getY(), z * other.getZ());
	}
	
	public Vector3f mult(float c)
	{
		return new Vector3f(x * c, y * c, z * c);
	}
	
	public Vector3f div(Vector3f other)
	{
		return new Vector3f(x / other.getX(), y / other.getY(),  z / other.getZ());
	}
	
	public Vector3f div(float c)
	{
		return new Vector3f(x / c, y / c, z / c);
	}
	
	public Vector3f abs()
	{
		return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
	}
	
	public boolean equals(Vector3f other)
	{
		return x == other.getX() && y == other.getY() && z == other.getZ();
	}
	
	public String toString()
	{
		return "(" + x + "," + y + "," + z + ")";
	}
	
	// SWIZZLES
	public Vector2f getXY() { return new Vector2f(x,y); }
	public Vector2f getYZ() { return new Vector2f(y,z); }
	public Vector2f getXZ() { return new Vector2f(x,z); }
	public Vector2f getYX() { return new Vector2f(y,x); }
	public Vector2f getZY() { return new Vector2f(z,y); }
	public Vector2f getZX() { return new Vector2f(z,x); }
	
	// GETTERS
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}
	
	// SETTERS
	public Vector3f set(float x, float y, float z)
		{ this.x = x; this.y = y; this.z = z; return this; }
	public Vector3f set(Vector3f r)
		{ set(r.getX(), r.getY(), r.getZ()); return this; }

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setZ(float z) {
		this.z = z;
	}
}
