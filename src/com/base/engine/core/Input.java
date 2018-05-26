package com.base.engine.core;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input {
	
	public static final int NUM_KEYCODES = 256;
	public static final int NUM_MOUSE = 5;
	
	private static ArrayList<Integer> currentKeys = new ArrayList<Integer>();
	private static ArrayList<Integer> prevKeys = new ArrayList<Integer>();
	
	private static ArrayList<Integer> currentMouse = new ArrayList<Integer>();
	private static ArrayList<Integer> prevMouse = new ArrayList<Integer>();
	
	
	// METHODS
	public static void update()
	{
		prevKeys.clear();
		prevKeys.addAll(currentKeys);
		
		currentKeys.clear();
		
		for(int i = 0; i< NUM_KEYCODES; ++i)
		{
			if(getKey(i))
				currentKeys.add(i);
		}
		
		prevMouse.clear();
		prevMouse.addAll(currentMouse);
		
		currentMouse.clear();
		
		for(int i = 0; i< NUM_MOUSE; ++i)
		{
			if(getMouse(i))
				currentMouse.add(i);
		}
	}
	
	// GETTERS
	public static boolean getKey(int keyCode)
	{
		return Keyboard.isKeyDown(keyCode);
	}
	
	public static boolean getKeyDown(int keyCode)
	{
		return currentKeys.contains(keyCode) && !prevKeys.contains(keyCode);
	}
	
	public static boolean getKeyUp(int keyCode)
	{
		return !currentKeys.contains(keyCode) && prevKeys.contains(keyCode);
	}
	
	public static boolean getMouse(int mouseButton)
	{
		return Mouse.isButtonDown(mouseButton);
	}
	
	public static boolean getMouseDown(int mouseButton)
	{
		return currentMouse.contains(mouseButton) && !prevMouse.contains(mouseButton);
	}
	
	public static boolean getMouseUp(int mouseButton)
	{
		return !currentMouse.contains(mouseButton) && prevMouse.contains(mouseButton);
	}
	
	public static Vector2f getMousePosition()
	{
		return new Vector2f(Mouse.getX(), Mouse.getY());
	}
	
	// SETTERS
	public static void setMousePosition(Vector2f pos)
	{
		Mouse.setCursorPosition((int)pos.getX(), (int)pos.getY());
	}
	
	public static void setCursor(boolean enabled)
	{
		Mouse.setGrabbed(!enabled);
	}
}
