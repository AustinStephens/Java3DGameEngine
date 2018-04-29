package com.base.engine;

public class Game {
	
	private Mesh mesh;
	private Shader shader;
	private Material material;
	private Transform transform;
	private Camera camera;
	
	PointLight pl1 = new PointLight(new BaseLight(new Vector3f(1,0.5f,0), 0.8f), new Attenuation(0,0,1), new Vector3f(-2,0,5f), 6);
	PointLight pl2 = new PointLight(new BaseLight(new Vector3f(0,0.5f,1), 0.8f), new Attenuation(0,0,1), new Vector3f(2,0,7f), 6);
	
	SpotLight sl1 = new SpotLight(new PointLight(new BaseLight(new Vector3f(0,1,1), 0.8f), new Attenuation(0,0,.1f), new Vector3f(-2,0,5f), 30),
								  new Vector3f(1,1,1), 0.7f);
	public Game()
	{
		shader = PhongShader.getInstance();	
		camera = new Camera();
		material = new Material(new Texture("test.png"), new Vector3f(1, 1, 1), 1, 8);
		transform = new Transform();
		//texture = ResourceLoader.loadTexture("test.png");
		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;
		
		Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
											new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
											new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
											new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};
		
		int indices[] = { 0, 1, 2,
					      2, 1, 3};
		
		mesh = new Mesh(vertices, indices, true);
		
		Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000f);
		Transform.setCamera(camera);
		
		PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.1f),
													 new Vector3f(1,1,1)));
		
		/*PointLight pl1 = new PointLight(new BaseLight(new Vector3f(1,0,0), 0.8f),
										new Attenuation(0,0,1),
										new Vector3f(-1,0,3f));
		PointLight pl2 = new PointLight(new BaseLight(new Vector3f(0,0,1), 0.8f),
										new Attenuation(0,0,1),
										new Vector3f(1,0,3f));*/
		
		PhongShader.setPointLight(new PointLight[] {pl1, pl2});
		PhongShader.setSpotLight(new SpotLight[] {sl1});
	}
	
	public void input()
	{
		camera.input();
	}
	float temp = 0.0f;
	
	public void update()
	{
		temp += Time.getDelta();
		transform.setTranslation(0, -1, 5);
		
		pl1.setPosition(new Vector3f(3,0,8.0f * (float)(Math.sin(temp) + 1.0/2.0) + 10));
		pl2.setPosition(new Vector3f(7,0,8.0f * (float)(Math.cos(temp) + 1.0/2.0) + 10));
		
		sl1.getPointLight().setPosition(camera.getPos());
		sl1.setDirection(camera.getForward());
		//transform.setRotation(0,sin * 180, 0);
		//transform.setScale(.5f, .5f, .5f);
	}
	
	public void render()
	{
		RenderUtil.setClearColor(Transform.getCamera().getPos().div(2048f).abs());
		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}
}
  