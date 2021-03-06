package com.base.components;

import org.lwjgl.input.Keyboard;

import com.base.engine.core.Input;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Window;

public class FreeLook extends GameComponent
{
	public static final Vector3f yAxis = new Vector3f(0, 1, 0);
	
	private float sensitivity;
	private boolean mouseLocked = false;
	private int unlockMouseKey;
	
	public FreeLook(float s)
	{
		this(s, Keyboard.KEY_ESCAPE);
	}
	
	public FreeLook(float s, int unlock)
	{
		sensitivity = s;
		unlockMouseKey = unlock;
	}
	
	// METHODS
	@Override
	public void input(float delta)
	{
		Vector2f centerPosition = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);
		
		
		if(Input.getKey(unlockMouseKey))
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
}
