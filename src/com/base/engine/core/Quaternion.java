package com.base.engine.core;

public class Quaternion {

	private float x;
	private float y;
	private float z;
	private float w;
	
	public Quaternion()
	{
		this(0,0,0,1);
	}
	
	public Quaternion(float x_, float y_, float z_, float w_)
	{
		x = x_;
		y = y_;
		z = z_;
		w = w_;
	}
	
	public Quaternion(Vector3f axis, float angle)
	{
		float sinHalfAngle = (float)Math.sin(angle / 2);
		float cosHalfAngle = (float)Math.cos(angle / 2);
		
		this.x = axis.getX() * sinHalfAngle;
		this.y = axis.getY() * sinHalfAngle;
		this.z = axis.getZ() * sinHalfAngle;
		this.w = cosHalfAngle;
	}
	
	public float length()
	{
		return (float)Math.sqrt(x*x + y*y + z*z + w*w);
	}
	
	public Quaternion normalize()
	{
		float l = length();
		
		return new Quaternion(x / l, y / l, z / l, w / l);
	}
	
	public Quaternion conjugate()
	{
		return new Quaternion(-x, -y, -z, w);
	}
	
	public Quaternion mult(float r)
	{
		return new Quaternion(x * r, y * r, z * r, w * r);
	}
	
	public Quaternion mult(Quaternion r)
	{
		float w_ = w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
		float x_ = x * r.getW() + w * r.getX() + y * r.getZ() - z * r.getY();
		float y_ = y * r.getW() + w * r.getY() + z * r.getX() - x * r.getZ();
		float z_ = z * r.getW() + w * r.getZ() + x * r.getY() - y * r.getX();
		
		return new Quaternion(x_, y_, z_, w_);
	}
	
	public Quaternion mult(Vector3f r)
	{
		float w_ = -x * r.getX() - y * r.getY() - z * r.getZ();
		float x_ = w * r.getX() + y * r.getZ() - z * r.getY();
		float y_ = w * r.getY() + z * r.getX() - x * r.getZ();
		float z_ = w * r.getZ() + x * r.getY() - y * r.getX();
		
		return new Quaternion(x_, y_, z_, w_);
	}
	
	public Matrix4f toRotationMatrix()
	{
		Vector3f fwd = new Vector3f(2.0f * (x*z - w*y), 2.0f * (y*z + w*x), 1.0f - 2.0f * (x*x + y*y));
		Vector3f up = new Vector3f(2.0f * (x*y + w*z), 1.0f - 2.0f * (x*x + z*z), 2.0f * (y*z - w*x));
		Vector3f right = new Vector3f(1.0f - 2.0f * (y*y + z*z), 2.0f * (x*y - w*z), 2.0f * (x*z + w*y));
		
		return new Matrix4f().initRotation(fwd, up, right);
	}

	public Vector3f getForward()
	{
		//return new Vector3f(2.0f * (x*z - w*y), 2.0f * (y*z + w*x), 1.0f - 2.0f * (x*x + y*y));
		return new Vector3f(0,0,1).rotate(this);
	}

	public Vector3f getBack()
	{
		//return new Vector3f(-2.0f * (x*z - w*y), -2.0f * (y*z + w*x), -(1.0f - 2.0f * (x*x + y*y)));
		return new Vector3f(0,0,-1).rotate(this);
	}

	public Vector3f getUp()
	{
		//return new Vector3f(2.0f * (x*y + w*z), 1.0f - 2.0f * (x*x + z*z), 2.0f * (y*z - w*x));
		return new Vector3f(0,1,0).rotate(this);
	}

	public Vector3f getDown()
	{
		//return new Vector3f(-2.0f * (x*y + w*z), -(1.0f - 2.0f * (x*x + z*z)), -2.0f * (y*z - w*x));
		return new Vector3f(0,-1,0).rotate(this);
	}

	public Vector3f getRight()
	{
		//return new Vector3f(1.0f - 2.0f * (y*y + z*z), 2.0f * (x*y - w*z), 2.0f * (x*z + w*y));
		return new Vector3f(1,0,0).rotate(this);
	}

	public Vector3f getLeft()
	{
		//return new Vector3f(-(1.0f - 2.0f * (y*y + z*z)), -2.0f * (x*y - w*z), -2.0f * (x*z + w*y));
		return new Vector3f(-1,0,0).rotate(this);
	}
	
	public boolean equals(Quaternion other)
	{
		return x == other.getX() && y == other.getY() && z == other.getZ() && w == other.getW();
	}
	
	public Quaternion set(float x, float y, float z, float w)
		{ this.x = x; this.y = y; this.z = z; this.w = w; return this; }
	
	public Quaternion set(Quaternion r)
		{set(r.getX(), r.getY(), r.getZ(), r.getW()); return this; }
	
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

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}
}
