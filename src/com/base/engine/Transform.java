package com.base.engine;

public class Transform {
	
	private static Camera camera;
	
	private static float zNear;
	private static float zFar;
	private static float width;
	private static float height;
	private static float fov;
	
	private Vector3f translation;
	private Vector3f rotation;
	private Vector3f scale;
	
	public Transform()
	{
		translation = new Vector3f();
		rotation = new Vector3f();
		scale = new Vector3f(1, 1, 1);
		//camera = new Camera();
	}
	
	public Matrix4f getTransformation()
	{
		Matrix4f newTranslation = new Matrix4f().initTranslation(translation.getX(), translation.getY(), translation.getZ());
		Matrix4f newRotation = new Matrix4f().initRotation(rotation.getX(), rotation.getY(), rotation.getZ());
		Matrix4f newScale = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());
		
		return newTranslation.mult(newRotation.mult(newScale));
	}
	
	public Matrix4f getProjectedTransformation()
	{
		Matrix4f transMatrix = getTransformation();
		Matrix4f projMatrix = new Matrix4f().initProjection(fov, width, height, zNear, zFar);
		Matrix4f camRotation = new Matrix4f().initCamera(camera.getForward(), camera.getUp());
		Matrix4f camTranslation = new Matrix4f().initTranslation(-camera.getPos().getX(), -camera.getPos().getY(), -camera.getPos().getZ());
		
		return projMatrix.mult(camRotation.mult(camTranslation.mult(transMatrix)));
	}
	
	public Vector3f getTranslation() {
		return translation;
	}

	public Vector3f getRotation() {
		return rotation;
	}
	
	
	public static void setProjection(float f, float w, float h, float zn, float zf)
	{
		fov = f;
		width = w;
		height = h;
		zNear = zn;
		zFar = zf;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public void setTranslation(Vector3f translation) {
		this.translation = translation;
	}
	
	public void setTranslation(float x, float y, float z) {
		this.translation = new Vector3f(x, y, z);
	}
	
	public void setRotation(float x, float y, float z)
	{
		rotation = new Vector3f(x,y,z);
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	
	public void setScale(float x, float y, float z) {
		this.scale = new Vector3f(x, y, z);
	}

	public static Camera getCamera() {
		return camera;
	}

	public static void setCamera(Camera camera) {
		Transform.camera = camera;
	}
	

}
