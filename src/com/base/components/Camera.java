package com.base.components;

import com.base.engine.core.CoreEngine;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Vector3f;

public class Camera extends GameComponent
{
	private Matrix4f projection;
	
	public Camera(float fov, float aspect, float zNear, float zFar)
	{
		projection = new Matrix4f().initProjection(fov, aspect, zNear, zFar);
	}
	
	// METHODS
	public Matrix4f getViewProjection()
	{
		Matrix4f camRotation = getTransform().getTransformedRotation().conjugate().toRotationMatrix();
		Vector3f cameraPos = getTransform().getTransformedPosition().mult(-1);
		Matrix4f camTranslation = new Matrix4f().initTranslation(cameraPos.getX(), cameraPos.getY(), cameraPos.getZ());
		
		return projection.mult(camRotation.mult(camTranslation));
	}
	
	@Override
	public void addToEngine(CoreEngine engine)
	{
		engine.getRenderingEngine().setMainCamera(this);
	}
}
