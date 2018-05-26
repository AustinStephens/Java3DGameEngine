package com.base.components;

import org.lwjgl.input.Keyboard;

import com.base.engine.core.Input;
import com.base.engine.core.Vector3f;

public class FreeMove extends GameComponent
{
	private float speed;
	private int forwardKey;
	private int backKey;
	private int leftKey;
	private int rightKey;

	public FreeMove(float speed)
	{
		this(speed, Keyboard.KEY_W, Keyboard.KEY_S, Keyboard.KEY_A, Keyboard.KEY_D);
	}

	public FreeMove(float speed, int forwardKey, int backKey, int leftKey, int rightKey)
	{
		this.speed = speed;
		this.forwardKey = forwardKey;
		this.backKey = backKey;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
	}
	
	// METHODS
	@Override
	public void input(float delta)
	{

		float moveAmt = speed * delta;
		
		if(Input.getKey(forwardKey))
			move(getTransform().getRotation().getForward(), moveAmt);
		if(Input.getKey(backKey))
			move(getTransform().getRotation().getForward(), -moveAmt);
		if(Input.getKey(leftKey))
			move(getTransform().getRotation().getLeft(), moveAmt);
		if(Input.getKey(rightKey))
			move(getTransform().getRotation().getRight(), moveAmt);
		
	}
	
	public void move(Vector3f dir, float amt)
	{
		getTransform().setPosition(getTransform().getPosition().add(dir.mult(amt)));
	}
}
