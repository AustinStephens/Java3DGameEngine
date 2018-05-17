#version 120

attribute vec3 position;
attribute vec2 textCoord;
attribute vec3 normal;

varying vec2 textCoord0;
varying vec3 normal0;
varying vec3 worldPos;

uniform mat4 model;
uniform mat4 MVP;

void main()
{
	gl_Position =  MVP * vec4(position, 1.0);
	textCoord0 = textCoord;
	
	normal0 = (model * vec4(normal, 0.0)).xyz;
	worldPos = (model * vec4(position, 1.0)).xyz;
}