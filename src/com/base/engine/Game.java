package com.base.engine;

public class Game {
	
	private Mesh mesh;
	private Shader shader;
	private Material material;
	private Transform transform;
	private Camera camera;

	public Game()
	{
		mesh = ResourceLoader.loadMesh("object.obj");//new Mesh();
		shader = BasicShader.getInstance();	
		camera = new Camera();
		material = new Material(ResourceLoader.loadTexture("test.png"), new Vector3f(0, 1, 1));
		//texture = ResourceLoader.loadTexture("test.png");
		
		Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000f);
		Transform.setCamera(camera);
		transform = new Transform();
	}
	
	public void input()
	{
		camera.input();
	}
	float temp = 0.0f;
	
	public void update()
	{
		temp += Time.getDelta();
		float sin = (float)Math.sin(temp);
		transform.setTranslation(sin / 3, 0, 2);
		
		transform.setRotation(sin * 180,sin * 180, 0);
		transform.setScale(.5f, .5f, .5f);
		
	}
	
	public void render()
	{
		RenderUtil.setClearColor(Transform.getCamera().getPos().div(2048f).abs());
		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}
}
  