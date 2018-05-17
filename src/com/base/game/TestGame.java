package com.base.game;

import com.base.components.Camera;
import com.base.components.DirectionalLight;
import com.base.components.MeshRenderer;
import com.base.components.PointLight;
import com.base.components.SpotLight;
import com.base.engine.core.Game;
import com.base.engine.core.GameObject;
import com.base.engine.core.Quaternion;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.*;

public class TestGame extends Game
{
	public TestGame()
	{
	}
	
	public void init()
	{
		
		Material material = new Material();//Material material = new Material(new Texture("test.png"), new Vector3f(1, 1, 1), 1, 8);
		material.addTexture("diffuse", new Texture("test.png"));
		material.addFloat("specularIntensity", 1);
		material.addFloat("specularPower", 8);
		
		Material material2 = new Material();//Material material = new Material(new Texture("test.png"), new Vector3f(1, 1, 1), 1, 8);
		material2.addTexture("diffuse", new Texture("test.png"));
		material2.addFloat("specularIntensity", 4);
		material2.addFloat("specularPower", 10);
		
		Mesh tempMesh = new Mesh("monkey3.obj");
		
		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;
		
		Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
											new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
											new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
											new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};
		
		int indices[] = { 0, 1, 2,
					      2, 1, 3};
		
		Vertex[] vertices2 = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth/ 10, 0.0f, -fieldDepth/ 10), new Vector2f(0.0f, 0.0f)),
				new Vertex( new Vector3f(-fieldWidth/ 10, 0.0f, fieldDepth/ 10 * 3), new Vector2f(0.0f, 1.0f)),
				new Vertex( new Vector3f(fieldWidth/ 10 * 3, 0.0f, -fieldDepth/ 10), new Vector2f(1.0f, 0.0f)),
				new Vertex( new Vector3f(fieldWidth/ 10 * 3, 0.0f, fieldDepth/ 10 * 3), new Vector2f(1.0f, 1.0f))};
				
		int indices2[] = { 0, 1, 2,
								   2, 1, 3};
				
		Mesh mesh2 = new Mesh(vertices2, indices2, true);
		
		Mesh mesh = new Mesh(vertices, indices, true);
		
		MeshRenderer meshRenderer = new MeshRenderer(mesh, material);
		GameObject planeObject = new GameObject();
		planeObject.addComponent(meshRenderer);
		planeObject.getTransform().getPosition().set(0, -1, 5);
		
		GameObject directionalLightObj = new GameObject();
		DirectionalLight dl = new DirectionalLight(new Vector3f(0,0,1), 0.4f);
		
		directionalLightObj.addComponent(dl);
		
				
		GameObject pointLightObj = new GameObject();
		pointLightObj.addComponent(new PointLight(new Vector3f(0,1,0), 0.4f, new Vector3f(0,0,1)));
		
		SpotLight spl = new SpotLight(new Vector3f(0,1,1), 0.4f,
									new Vector3f(0,0,0.1f),
									0.7f);
		
		GameObject spotLightObj = new GameObject();
		spotLightObj.addComponent(spl);	
		
		spotLightObj.getTransform().getPosition().set(5,0,5);
		spotLightObj.getTransform().setRotation(new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(90.0f)));
		
		addObject(planeObject);
		addObject(directionalLightObj);
		addObject(pointLightObj);
		addObject(spotLightObj);
		
		addObject(new GameObject().addComponent(new Camera((float)Math.toRadians(70), (float)Window.getWidth()/(float)Window.getHeight(), 0.1f, 1000)));
		
		GameObject testMesh1 = new GameObject().addComponent(new MeshRenderer(mesh2, material));
		GameObject testMesh2 = new GameObject().addComponent(new MeshRenderer(mesh2, material));
		GameObject testMesh3 = new GameObject().addComponent(new MeshRenderer(tempMesh, material));
		
		testMesh1.getTransform().getPosition().set(0, 2, 0);
		testMesh1.getTransform().setRotation(new Quaternion(new Vector3f(0,1,0), 0.4f));
		testMesh2.getTransform().getPosition().set(0, 0, 5);
				
		testMesh1.addChild(testMesh2);
		//testMesh2.addChild(new GameObject().addComponent(new Camera((float)Math.toRadians(70), (float)Window.getWidth()/(float)Window.getHeight(), 0.1f, 1000)));

		addObject(testMesh1);
		addObject(testMesh3);
		
		testMesh3.getTransform().getPosition().set(5,5,5);
		testMesh3.getTransform().setRotation(new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(70.0f)));
		
		addObject(new GameObject().addComponent(new MeshRenderer(new Mesh("monkey3.obj"), material2)));
		
		dl.getTransform().setRotation(new Quaternion(new Vector3f(1,0,0), (float)Math.toRadians(-45)));
		
	}
}
