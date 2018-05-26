package com.base.engine.core;

public class Time {
	
	public static final long SECOND = 1000000000L;

	// GETTERS
	public static double getTime()
	{
		return (double)System.nanoTime() / (double)SECOND;
	}
}
