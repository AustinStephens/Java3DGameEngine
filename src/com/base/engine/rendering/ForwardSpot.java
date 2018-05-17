package com.base.engine.rendering;

import com.base.components.BaseLight;
import com.base.components.PointLight;
import com.base.components.SpotLight;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;

public class ForwardSpot extends Shader
{
	private static final ForwardSpot instance = new ForwardSpot();
	
	public static ForwardSpot getInstance()
	{
		return instance;
	}
	
	private ForwardSpot()
	{
		super();
		
		addVertexShaderFromFile("forward-spot.vs");
		addFragmentShaderFromFile("forward-spot.fs");
		
		setAttribLocation("position", 0);
		setAttribLocation("textCoord", 1);
		setAttribLocation("normal", 2);
		
		compileShader();
		
		addUniform("MVP");
		addUniform("model");
		addUniform("specularIntensity");
		addUniform("specularPower");
		addUniform("eyePos");
		
		addUniform("spotLight.pointLight.base.color");
		addUniform("spotLight.pointLight.base.intensity");
		addUniform("spotLight.pointLight.atten.constant");
		addUniform("spotLight.pointLight.atten.linear");
		addUniform("spotLight.pointLight.atten.exponent");
		addUniform("spotLight.pointLight.position");
		addUniform("spotLight.pointLight.range");
		addUniform("spotLight.direction");
		addUniform("spotLight.cutoff");
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
		
		setUniformSpot("spotLight", (SpotLight)renderingEngine.getActiveLight());
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
	
	public void setUniformSpot(String uniformName, SpotLight sl)
	{
		setUniformPoint(uniformName + ".pointLight", sl);
		setUniform(uniformName + ".direction", sl.getDirection());
		setUniformf(uniformName + ".cutoff", sl.getCutoff());
	}
}
