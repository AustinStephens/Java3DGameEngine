package com.base.engine;

import static org.lwjgl.opengl.GL20.glUniform1i;

public class PhongShader extends Shader 
{
	private static final int MAX_POINT_LIGHTS = 4;
	private static final int MAX_SPOT_LIGHTS = 4;
	private static final PhongShader instance = new PhongShader();
	
	public static PhongShader getInstance()
	{
		return instance;
	}
	
	private static Vector3f ambientLight = new Vector3f(0.1f, 0.1f,0.1f);
	private static DirectionalLight directionalLight = new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0),
													 new Vector3f(0,0,0));
	private static PointLight pointLights[] = new PointLight[] {};
	private static SpotLight spotLights[] = new SpotLight[] {};
	
	private PhongShader()
	{
		super();
		
		addVertexShaderFromFile("phongVertex.vs");
		addFragmentShaderFromFile("phongFragment.fs");
		
		compileShader();
		
		addUniform("transform");
		addUniform("transformProjected");
		addUniform("baseColor");
		addUniform("ambientLight");
		
		addUniform("specularIntensity");
		addUniform("specularPower");
		addUniform("eyePos");
		
		addUniform("directionalLight.base.color");
		addUniform("directionalLight.base.intensity");
		addUniform("directionalLight.direction");
		
		for(int i = 0; i < MAX_POINT_LIGHTS; ++i)
		{
			addUniform("pointLights[" + i +"].base.color");
			addUniform("pointLights[" + i +"].base.intensity");
			addUniform("pointLights[" + i +"].atten.constant");
			addUniform("pointLights[" + i +"].atten.linear");
			addUniform("pointLights[" + i +"].atten.exponent");
			addUniform("pointLights[" + i +"].position");
			addUniform("pointLights[" + i +"].range");
		}
		
		for(int i = 0; i < MAX_SPOT_LIGHTS; ++i)
		{
			addUniform("spotLights[" + i +"].pointLight.base.color");
			addUniform("spotLights[" + i +"].pointLight.base.intensity");
			addUniform("spotLights[" + i +"].pointLight.atten.constant");
			addUniform("spotLights[" + i +"].pointLight.atten.linear");
			addUniform("spotLights[" + i +"].pointLight.atten.exponent");
			addUniform("spotLights[" + i +"].pointLight.position");
			addUniform("spotLights[" + i +"].pointLight.range");
			addUniform("spotLights[" + i +"].direction");
			addUniform("spotLights[" + i +"].cutoff");
		}
	}
	
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projMatrix, Material mat)
	{
		if(mat.getTexture() != null)
			mat.getTexture().bind();
		else
			RenderUtil.unbindTextures();
		setUniform("transformProjected", projMatrix);
		setUniform("transform", worldMatrix);
		setUniform("baseColor", mat.getColor());
		
		setUniform("ambientLight", ambientLight);
		setUniform("directionalLight", directionalLight);
		for(int i = 0; i < pointLights.length; ++i)
			setUniform("pointLights[" + i +"]", pointLights[i]);
		
		for(int i = 0; i < spotLights.length; ++i)
			setUniform("spotLights[" + i +"]", spotLights[i]);
		
		setUniformf("specularIntensity", mat.getSpecularIntensity());
		setUniformf("specularPower", mat.getSpecularPower());
		
		setUniform("eyePos", Transform.getCamera().getPos());
	}

	public static Vector3f getAmbientLight() {
		return ambientLight;
	}

	public static void setAmbientLight(Vector3f ambientLight) {
		PhongShader.ambientLight = ambientLight;
	}
	
	public void setUniform(String uniformName, BaseLight bl)
	{
		setUniform(uniformName + ".color", bl.getColor());
		setUniformf(uniformName + ".intensity", bl.getIntensity());
	}
	
	public void setUniform(String uniformName, DirectionalLight dl)
	{
		setUniform(uniformName + ".base", dl.getBase());
		setUniform(uniformName + ".direction", dl.getDirection());
	}
	
	public void setUniform(String uniformName, PointLight pl)
	{
		setUniform(uniformName + ".base", pl.getBase());
		setUniformf(uniformName + ".atten.constant", pl.getAtten().getConstant());
		setUniformf(uniformName + ".atten.linear", pl.getAtten().getLinear());
		setUniformf(uniformName + ".atten.exponent", pl.getAtten().getExponent());
		setUniform(uniformName + ".position", pl.getPosition());
		setUniformf(uniformName + ".range", pl.getRange());
	}
	
	public void setUniform(String uniformName, SpotLight sl)
	{
		setUniform(uniformName + ".pointLight", sl.getPointLight());
		setUniform(uniformName + ".direction", sl.getDirection());
		setUniformf(uniformName + ".cutoff", sl.getCutoff());
	}

	public static DirectionalLight getDirectionalLight() {
		return directionalLight;
	}

	public static void setDirectionalLight(DirectionalLight directionLight) {
		PhongShader.directionalLight = directionLight;
	}
	
	public static void setPointLight(PointLight[] pls)
	{
		if(pls.length > MAX_POINT_LIGHTS)
		{
			System.err.println("Too many point lights.");
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		pointLights = pls;
	}
	
	public static void setSpotLight(SpotLight[] sls)
	{
		if(sls.length > MAX_POINT_LIGHTS)
		{
			System.err.println("Too many spot lights.");
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		spotLights = sls;
	}
}
