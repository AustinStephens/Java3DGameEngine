package com.base.engine;

public class MainComponent 
{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Java 3D Engine";
	public static final double FRAME_CAP = 5000.0;
	
	private boolean isRunning;
	private Game game;
	
	public MainComponent()
	{
		isRunning = false;
		game = new Game();
	}
	
	public void start()
	{
		if(isRunning)
			return;
		run();
	}
	
	public void stop()
	{
		if(!isRunning)
			return;
		
		isRunning = false;
	}
	
	private void run()
	{
		isRunning = true;
		
		// FRAME / DELTA TIME WORK
		int frames = 0;
		long frameCounter = 0;
		
		final double frameTime = 1.0 / FRAME_CAP;
		
		long lastTime = Time.getTime();
		double unprocessedTime = 0;
		
		while(isRunning)
		{
			boolean render = false;
			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime / (double)Time.SECOND;
			frameCounter += passedTime;
			
			while(unprocessedTime > frameTime)
			{
				// DOES SOME WEIRD FRAME THINGS
				render = true;
				unprocessedTime -= frameTime;
				
				// CHECKS IF CLOSED
				
				if(Window.isCloseRequested()) 
					stop();
				
				Time.setDelta(frameTime);
				
				// GAME UPDATES
				Input.update();
				game.input();
				game.update();
				
				// COUNTS FPS
				if(frameCounter >= Time.SECOND)
				{
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			
			if(render)
			{
				render();
				frames++;
			}
			else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				
		}
		
		cleanup();
	}
	
	private void render()
	{
		game.render();
		Window.render();
	}
	
	private void cleanup()
	{
		Window.dispose();
	}

	
	public static void main(String[] args)
	{
		Window.createWindow(WIDTH, HEIGHT, TITLE);
		
		MainComponent game = new MainComponent();
		game.start();
	}
}
