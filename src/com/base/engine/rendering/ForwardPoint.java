package com.base.engine.rendering;

import com.base.components.BaseLight;
import com.base.components.PointLight;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;

public class ForwardPoint extends Shader
{
	private static final ForwardPoint instance = new ForwardPoint();
	
	public static ForwardPoint getInstance()
	{
		return instance;
	}
	
	private ForwardPoint()
	{
		super();
		
		addVertexShaderFromFile("forward-point.vs");
		addFragmentShaderFromFile("forward-point.fs");
		
		setAttribLocation("position", 0);
		setAttribLocation("textCoord", 1);
		setAttribLocation("normal", 2);
		
		compileShader();
		
		addUniform("MVP");
		addUniform("model");
		addUniform("specularIntensity");
		addUniform("specularPower");
		addUniform("eyePos");
		
		addUniform("pointLight.base.color");
		addUniform("pointLight.base.intensity");
		addUniform("pointLight.atten.constant");
		addUniform("pointLight.atten.linear");
		addUniform("pointLight.atten.exponent");
		addUniform("pointLight.position");
		addUniform("pointLight.range");
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
		
		setUniformPoint("pointLight", (PointLight)renderingEngine.getActiveLight());
	}
	
	public void setUniformBase(String uniformName, BaseLight bl)
	{
		setUniform(uniformName + ".color", bl.getColor());
		setUniformf(uniformName + ".intensity", bl.getIntensity());
	}
	
	public void setUniformPoint(String uniformName, PointLight pl)
	{
		setUniformBase(uniformName + ".base", pl);
		setUniformf(uniformName + ".atten.constant", pl.getConstant());
		setUniformf(uniformName + ".atten.linear", pl.getLinear());
		setUniformf(uniformName + ".atten.exponent", pl.getExponent());
		setUniform(uniformName + ".position", pl.getTransform().getTransformedPosition());
		setUniformf(uniformName + ".range", pl.getRange());
	}
}
