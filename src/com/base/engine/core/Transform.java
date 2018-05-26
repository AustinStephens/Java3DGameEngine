package com.base.engine.core;

public class Transform 
{
	private Transform parent;
	private Matrix4f parentMatrix;
	
	private Vector3f position;
	private Quaternion rotation;
	private Vector3f scale;
	
	private Vector3f oldPosition;
	private Quaternion oldRotation;
	private Vector3f oldScale;
	
	public Transform()
	{
		position = new Vector3f();
		rotation = new Quaternion(0, 0, 0, 1);
		scale = new Vector3f(1, 1, 1);
		
		oldPosition = new Vector3f(0,0,0);
		oldRotation = new Quaternion(0,0,0,0);
		oldScale = new Vector3f(0,0,0);
		//camera = new Camera();
		parentMatrix = new Matrix4f().initIdentity();
	}
	
	// METHODS
	public void update()
	{
		if(oldPosition != null)
		{
			oldPosition.set(position);
			oldRotation.set(rotation);
			oldScale.set(scale);
		}
		else
		{
			oldPosition = new Vector3f(0,0,0).set(position).add(1.0f);
			oldRotation = new Quaternion(0,0,0,0).set(rotation).mult(0.5f);
			oldScale = new Vector3f(0,0,0).set(scale).add(1.0f);
		}
	}
	
	public void rotate(Vector3f axis, float angle)
	{
		rotation = new Quaternion(axis, angle).mult(rotation).normalize();
	}	
	
	public boolean hasChanged()
	{
		if(parent != null && parent.hasChanged())
			return true;
		
		if(!position.equals(oldPosition))
			return true;
		if(!rotation.equals(oldRotation))
			return true;
		if(!scale.equals(oldScale))
			return true;
		
		return false;
	}
	
	public void lookAt(Vector3f point, Vector3f up)
	{
		rotation = getLookAtDirection(point, up);
	}

	// GETTERS
	public Quaternion getLookAtDirection(Vector3f point, Vector3f up)
	{
		return new Quaternion(new Matrix4f().initRotation(point.sub(position).normalize(), up));
	}
	
	public Matrix4f getTransformation()
	{
		Matrix4f newTranslation = new Matrix4f().initTranslation(position.getX(), position.getY(), position.getZ());
		Matrix4f newRotation = rotation.toRotationMatrix();//new Matrix4f().initRotation(rotation.getX(), rotation.getY(), rotation.getZ());
		Matrix4f newScale = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());
		
		return getParentMatrix().mult(newTranslation.mult(newRotation.mult(newScale)));
	}
	
	public Matrix4f getParentMatrix()
	{
		if(parent != null && parent.hasChanged())
			parentMatrix = parent.getTransformation();
		
		return parentMatrix;
	}
	
	public Vector3f getTransformedPosition()
	{
		return getParentMatrix().transform(position);
	}
	
	public Quaternion getTransformedRotation()
	{
		Quaternion parentRotation = new Quaternion(0,0,0,1);
		
		if(parent != null)
		{
			parentRotation = parent.getTransformedRotation();
		}
		
		return parentRotation.mult(rotation);
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public Quaternion getRotation() {
		return rotation;
	}
	
	public Vector3f getScale() {
		return scale;
	}

	public Transform getParent() {
		return parent;
	}
	
	// SETTERS
	public void setRotation(Quaternion rotation) {
		this.rotation = rotation;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

	public void setParent(Transform parent) {
		this.parent = parent;
	}

}
