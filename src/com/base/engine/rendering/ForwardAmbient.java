package com.base.engine.rendering;

import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;

public class ForwardAmbient extends Shader
{
	private static final ForwardAmbient instance = new ForwardAmbient();
	
	public static ForwardAmbient getInstance()
	{
		return instance;
	}
	
	private ForwardAmbient()
	{
		super();
		
		addVertexShaderFromFile("forward-ambient.vs");
		addFragmentShaderFromFile("forward-ambient.fs");
		
		setAttribLocation("position", 0);
		setAttribLocation("textCoord", 1);
		
		compileShader();
		
		addUniform("MVP");
		addUniform("ambientIntensity");
	}
	
	public void updateUniforms(Transform transform, Material mat, RenderingEngine renderingEngine)
	{
		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f projMatrix = renderingEngine.getMainCamera().getViewProjection().mult(worldMatrix);
		
		mat.getTexture("diffuse").bind();

		setUniform("MVP", projMatrix);
		setUniform("ambientIntensity", renderingEngine.getAmbientLight());
	}
}
