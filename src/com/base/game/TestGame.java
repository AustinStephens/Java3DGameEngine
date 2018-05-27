package com.base.game;

import com.base.components.Camera;
import com.base.components.DirectionalLight;
import com.base.components.FreeLook;
import com.base.components.FreeMove;
import com.base.components.MeshRenderer;
import com.base.components.PointLight;
import com.base.components.SpotLight;
import com.base.engine.core.Game;
import com.base.engine.core.GameObject;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Quaternion;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.*;

public class TestGame extends Game
{
	public TestGame() { }
	
	public void init()
	{
		Mesh mesh = new Mesh("plane3.obj");

		Material material2 = new Material(new Texture("bricks.jpg"), 1, 8,
				new Texture("bricks_normal.jpg"), new Texture("bricks_disp.png"), 0.03f, -0.5f);

			Material material = new Material(new Texture("bricks2.jpg"), 1, 8,
					new Texture("bricks2_normal.jpg"), new Texture("bricks2_disp.jpg"), 0.04f, -1.0f);
		
		Mesh tempMesh = new Mesh("monkey3.obj");

		MeshRenderer meshRenderer = new MeshRenderer(mesh, material);

		GameObject planeObject = new GameObject();
		planeObject.addComponent(meshRenderer);
		planeObject.getTransform().getPosition().set(0, -1, 5);

		GameObject directionalLightObject = new GameObject();
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0,0,1), 0.4f);

		directionalLightObject.addComponent(directionalLight);

		GameObject pointLightObject = new GameObject();
		pointLightObject.addComponent(new PointLight(new Vector3f(0,1,0), 0.4f, new Attenuation(0,0,1)));

		SpotLight spotLight = new SpotLight(new Vector3f(0,1,1), 0.4f,
				new Attenuation(0,0,0.1f), 0.7f);

		GameObject spotLightObject = new GameObject();
		spotLightObject.addComponent(spotLight);

		spotLightObject.getTransform().getPosition().set(5, 0, 5);
		spotLightObject.getTransform().setRotation(new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(90.0f)));

		addObject(planeObject);
		addObject(directionalLightObject);
		addObject(pointLightObject);
		addObject(spotLightObject);

		GameObject testMesh3 = new GameObject().addComponent(new LookAtComponent()).addComponent(new MeshRenderer(tempMesh, material));
		addObject(new GameObject().addComponent(new FreeLook(0.5f)).addComponent(new FreeMove(10.0f))
				.addComponent(new Camera(new Matrix4f().initProjection((float) Math.toRadians(70.0f),
						(float) Window.getWidth() / (float) Window.getHeight(), 0.01f, 1000.0f))));

		addObject(testMesh3);

		testMesh3.getTransform().getPosition().set(5,5,5);
		
		addObject(new GameObject().addComponent(new MeshRenderer(new Mesh("monkey3.obj"), material2)));

		directionalLight.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float) Math.toRadians(-45)));
	}
}
