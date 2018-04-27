package com.base.engine;

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
	
	public float length()
	{
		return (float)Math.sqrt((x * x) + (y * y) + (z * z));
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
		float sinHalfAngle = (float)Math.sin(Math.toRadians(angle / 2));
		float cosHalfAngle = (float)Math.cos(Math.toRadians(angle / 2));
		
		float rx = axis.getX() * sinHalfAngle;
		float ry = axis.getY() * sinHalfAngle;
		float rz = axis.getZ() * sinHalfAngle;
		float rw = cosHalfAngle;
		
		Quaternion rotation = new Quaternion(rx, ry, rz, rw);
		Quaternion conj = rotation.conjugate();
		Quaternion w = rotation.mult(this).mult(conj);
		
		x = w.getX();
		y = w.getY();
		z = w.getZ();
		
		return new Vector3f(w.getX(), w.getY(), w.getZ());
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
	
	public String toString()
	{
		return "(" + x + "," + y + "," + z + ")";
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

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	

}
