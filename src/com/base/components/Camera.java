package com.base.components;

import org.lwjgl.input.Keyboard;

import com.base.engine.core.Input;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Window;

public class Camera extends GameComponent
{
	
	public static final Vector3f yAxis = new Vector3f(0, 1, 0);
	private Matrix4f projection;
	
	public Camera(float fov, float aspect, float zNear, float zFar)
	{
		projection = new Matrix4f().initProjection(fov, aspect, zNear, zFar);
	}
	
	public Matrix4f getViewProjection()
	{
		Matrix4f camRotation = getTransform().getTransformedRotation().conjugate().toRotationMatrix();
		Vector3f cameraPos = getTransform().getTransformedPosition().mult(-1);
		Matrix4f camTranslation = new Matrix4f().initTranslation(cameraPos.getX(), cameraPos.getY(), cameraPos.getZ());
		
		return projection.mult(camRotation.mult(camTranslation));
	}
	
	@Override
	public void addToRenderingEngine(RenderingEngine renderingEngine)
	{
		renderingEngine.setMainCamera(this);
	}
	
	boolean mouseLocked = false;
	Vector2f centerPosition = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);
	
	public void input(float delta)
	{
		float sensitivity = 0.5f;
		float moveAmt = (float)(10 * delta);
		//float rotAmt = (float)(100 * Time.getDelta());
		if(Input.getKey(Keyboard.KEY_ESCAPE))
		{
			Input.setCursor(true);
			mouseLocked = false;
		}
		if(Input.getMouseDown(0))
		{
			Input.setMousePosition(centerPosition);
			Input.setCursor(false);
			mouseLocked = true;
		}
		
		if(Input.getKey(Keyboard.KEY_W))
			move(getTransform().getRotation().getForward(), moveAmt);
		if(Input.getKey(Keyboard.KEY_S))
			move(getTransform().getRotation().getForward(), -moveAmt);
		if(Input.getKey(Keyboard.KEY_A))
			move(getTransform().getRotation().getLeft(), moveAmt);
		if(Input.getKey(Keyboard.KEY_D))
			move(getTransform().getRotation().getRight(), moveAmt);
		
		if(mouseLocked)
		{
			Vector2f deltaPos = Input.getMousePosition().sub(centerPosition);
			boolean rotY = deltaPos.getY() != 0;
			boolean rotX = deltaPos.getX() != 0;
			
			if(rotY)
				getTransform().rotate(yAxis, (float)Math.toRadians(deltaPos.getX() * sensitivity));
			if(rotX)
				getTransform().rotate(getTransform().getRotation().getRight(), (float) Math.toRadians(-deltaPos.getY() * sensitivity));
			
			if(rotY || rotX)
				Input.setMousePosition(new Vector2f(Window.getWidth()/2, Window.getHeight()/2));
		}
		
	}
	
	public void move(Vector3f dir, float amt)
	{
		getTransform().setPosition(getTransform().getPosition().add(dir.mult(amt)));
	}
	/*
	public void rotateY(float angle)
	{
		Vector3f hAxis = yAxis.cross(forward).normalize();
		
		forward = forward.rotate(angle, yAxis).normalize();
		
		up = forward.cross(hAxis).normalize();
	}
	
	public void rotateX(float angle)
	{
		Vector3f hAxis = yAxis.cross(forward).normalize();
		
		forward = forward.rotate(angle, hAxis).normalize();
		
		up = forward.cross(hAxis).normalize();
	}
	
	public Vector3f getLeft()
	{
		return forward.cross(up).normalize();
	}
	
	public Vector3f getRight()
	{
		return up.cross(forward).normalize();
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f pos) {
		this.position = pos;
	}

	public Vector3f getForward() {
		return forward;
	}

	public void setForward(Vector3f forward) {
		this.forward = forward;
	}

	public Vector3f getUp() {
		return up;
	}

	public void setUp(Vector3f up) {
		this.up = up;
	}*/

}
