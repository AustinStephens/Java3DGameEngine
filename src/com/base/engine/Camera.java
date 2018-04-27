package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Camera {
	
	public static final Vector3f yAxis = new Vector3f(0, 1, 0);
	
	private Vector3f pos;
	private Vector3f forward;
	private Vector3f up;
	
	public Camera()
	{
		this(new Vector3f(), new Vector3f(0,0,1), new Vector3f(0, 1, 0));
	}
	
	public Camera(Vector3f p, Vector3f fwd, Vector3f u)
	{
		pos = p;
		forward = fwd.normalize();
		up = u.normalize();
	}
	
	boolean mouseLocked = false;
	Vector2f centerPosition = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);
	
	public void input()
	{
		float sensitivity = 0.5f;
		float moveAmt = (float)(10 * Time.getDelta());
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
			move(getForward(), moveAmt);
		if(Input.getKey(Keyboard.KEY_S))
			move(getForward(), -moveAmt);
		if(Input.getKey(Keyboard.KEY_A))
			move(getLeft(), moveAmt);
		if(Input.getKey(Keyboard.KEY_D))
			move(getRight(), moveAmt);
		
		if(mouseLocked)
		{
			Vector2f deltaPos = Input.getMousePosition().sub(centerPosition);
			boolean rotY = deltaPos.getY() != 0;
			boolean rotX = deltaPos.getX() != 0;
			
			if(rotY)
				rotateY(deltaPos.getX() * sensitivity);
			if(rotX)
				rotateX(-deltaPos.getY() * sensitivity);
			
			if(rotY || rotX)
				Input.setMousePosition(new Vector2f(Window.getWidth()/2, Window.getHeight()/2));
		}
		
	}
	
	public void move(Vector3f dir, float amt)
	{
		pos = pos.add(dir.mult(amt));
	}
	
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

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
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
	}

}
