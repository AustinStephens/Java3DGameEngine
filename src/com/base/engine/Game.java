package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Game {

	public Game()
	{
		
	}
	
	public void input()
	{
		if(Input.getKeyDown(Keyboard.KEY_UP))
			System.out.println("up");
		if(Input.getKeyUp(Keyboard.KEY_DOWN))
			System.out.println("down");
		
		if(Input.getMouseDown(1))
			System.out.println("right down at " + Input.getMousePosition().toString());
		if(Input.getMouseUp(1))
			System.out.println("right up");
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		
	}
}
  