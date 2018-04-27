package com.base.engine;

public class BasicShader extends Shader
{
	private static final BasicShader instance = new BasicShader();
	
	public static BasicShader getInstance()
	{
		return instance;
	}
	
	private BasicShader()
	{
		super();
		
		addVertexShader(ResourceLoader.loadShader("basicVertex.vs.txt"));
		addFragmentShader(ResourceLoader.loadShader("basicFragment.fs.txt"));
		
		compileShader();
		
		addUniform("transform");
		addUniform("color");
	}
	
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projMatrix, Material mat)
	{
		if(mat.getTexture() != null)
			mat.getTexture().bind();
		else
			RenderUtil.unbindTextures();
		setUniform("transform", projMatrix);
		setUniform("color", mat.getColor());
	}

}
