package com.base.engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

import java.util.ArrayList;

import com.base.components.BaseLight;
import com.base.components.Camera;
import com.base.engine.core.GameObject;
import com.base.engine.core.Vector3f;

public class RenderingEngine 
{
	private Camera mainCamera;
	private Vector3f ambientLight;
	// more permanent structures
	private ArrayList<BaseLight> lights;
	private BaseLight activeLight;
	
	public RenderingEngine()
	{	
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		
		glEnable(GL_DEPTH_CLAMP);
		glEnable(GL_TEXTURE_2D);
		
		//mainCamera = new Camera((float)Math.toRadians(70), (float)Window.getWidth()/(float)Window.getHeight(), 0.1f, 1000);
		ambientLight = new Vector3f(0.1f, 0.1f, 0.1f);
		lights = new ArrayList<BaseLight>();
	}
	
	public Vector3f getAmbientLight()
	{
		return ambientLight;
	}
	
	public void render(GameObject gameObject)
	{
		clearScreen();
		lights.clear();
		
		gameObject.addToRenderingEngine(this);
		Shader fwdAmbient = ForwardAmbient.getInstance();
		
		gameObject.render(fwdAmbient, this);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE);
		glDepthMask(false);
		glDepthFunc(GL_EQUAL);
		
		for(BaseLight light: lights)
		{
			activeLight = light;
			//TODO : replace active light
			gameObject.render(light.getShader(), this);
		}
		
		
		glDepthFunc(GL_LESS);
		glDepthMask(true);
		glDisable(GL_BLEND);
	}
	
	private static void clearScreen()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	private static void setTextures(boolean enabled)
	{
		if(enabled)
			glEnable(GL_TEXTURE_2D);
		else
			glDisable(GL_TEXTURE_2D);
	}
	
	private static void unbindTextures()
	{
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	private static void setClearColor(Vector3f color)
	{
		glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
	}
	
	public static String getOpenGLVersion()
	{
		return glGetString(GL_VERSION);
	}
	
	public void addLight(BaseLight light)
	{
		lights.add(light);
	}
	
	public BaseLight getActiveLight()
	{
		return activeLight;
	}

	public Camera getMainCamera() {
		return mainCamera;
	}

	public void setMainCamera(Camera mainCamera) {
		this.mainCamera = mainCamera;
	}
}
