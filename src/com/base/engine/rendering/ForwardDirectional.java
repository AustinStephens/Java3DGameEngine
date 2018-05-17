package com.base.engine.rendering;

import com.base.components.BaseLight;
import com.base.components.DirectionalLight;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;

public class ForwardDirectional extends Shader
{
	private static final ForwardDirectional instance = new ForwardDirectional();
	
	public static ForwardDirectional getInstance()
	{
		return instance;
	}
	
	private ForwardDirectional()
	{
		super();
		
		addVertexShaderFromFile("forward-directional.vs");
		addFragmentShaderFromFile("forward-directional.fs");
		
		setAttribLocation("position", 0);
		setAttribLocation("textCoord", 1);
		setAttribLocation("normal", 2);
		
		compileShader();
		
		addUniform("MVP");
		addUniform("model");
		addUniform("specularIntensity");
		addUniform("specularPower");
		addUniform("eyePos");
		
		addUniform("directionalLight.base.color");
		addUniform("directionalLight.base.intensity");
		addUniform("directionalLight.direction");
	}
	
	public void updateUniforms(Transform transform, Material mat, RenderingEngine renderingEngine)
	{
		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f projMatrix = renderingEngine.getMainCamera().getViewProjection().mult(worldMatrix);
		
		mat.getTexture("diffuse").bind();

		setUniform("MVP", projMatrix);
		setUniform("model", worldMatrix);
		
		setUniformf("specularIntensity", mat.getFloat("specularIntensity"));
		setUniformf("specularPower", mat.getFloat("specularPower"));
		
		setUniform("eyePos", renderingEngine.getMainCamera().getTransform().getTransformedPosition());
		
		setUniformDirectional("directionalLight", (DirectionalLight)renderingEngine.getActiveLight());
	}
	
	public void setUniformBase(String uniformName, BaseLight bl)
	{
		setUniform(uniformName + ".color", bl.getColor());
		setUniformf(uniformName + ".intensity", bl.getIntensity());
	}
	
	public void setUniformDirectional(String uniformName, DirectionalLight dl)
	{
		setUniformBase(uniformName + ".base", dl);
		setUniform(uniformName + ".direction", dl.getDirection());
	}
}
