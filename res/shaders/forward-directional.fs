#version 120

varying vec2 textCoord0;
varying vec3 normal0;
varying vec3 worldPos;

struct BaseLight
{
	vec3 color;
	float intensity;
};

struct DirectionalLight
{
	BaseLight base;
	vec3 direction;
};

uniform vec3 eyePos;
uniform sampler2D diffuse;
uniform DirectionalLight directionalLight;

uniform float specularIntensity;
uniform float specularPower;

vec4 calcLight(BaseLight base, vec3 direction, vec3 normal)
{
	float diffuseFactor = dot(normal, -direction);
	
	vec4 diffuseColor = vec4(0,0,0,0);
	vec4 specularColor = vec4(0,0,0,0);
	
	if(diffuseFactor > 0)
	{
		diffuseColor = vec4(base.color, 1.0) * base.intensity * diffuseFactor;
		
		vec3 directionToEye = normalize(eyePos - worldPos);
		vec3 reflectDirection = normalize(reflect(direction, normal));
		
		float specularFactor = dot(directionToEye, reflectDirection);
		specularFactor = pow(specularFactor, specularPower);
		
		if(specularFactor > 0)
		{
			specularColor = vec4(base.color, 1.0) * specularIntensity * specularFactor;
		}		
	}
	
	return diffuseColor + specularColor;
}

vec4 calcDirectionLight(DirectionalLight dirLight, vec3 normal)
{
	return calcLight(dirLight.base, -dirLight.direction, normal);
}

void main()
{		
	gl_FragColor = texture2D(diffuse, textCoord0.xy) * calcDirectionLight(directionalLight, normalize(normal0));
}
