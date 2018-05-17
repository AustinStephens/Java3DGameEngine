#version 120

varying vec2 textCoord0;
varying vec3 normal0;
varying vec3 worldPos;

struct BaseLight
{
	vec3 color;
	float intensity;
};

struct Attenuation
{
	float constant;
	float linear;
	float exponent;
};

struct PointLight
{
	BaseLight base;
	Attenuation atten;
	vec3 position;
	float range;
};

uniform vec3 eyePos;
uniform sampler2D diffuse;

uniform float specularIntensity;
uniform float specularPower;

uniform PointLight pointLight;

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

vec4 calcPointLight(PointLight pl, vec3 normal)
{
	vec3 lightDirection = worldPos - pl.position;
	float distanceToPoint = length(lightDirection);
	
	if(distanceToPoint > pl.range)
		return vec4(0,0,0,0);
		
	lightDirection = normalize(lightDirection);
	
	vec4 color = calcLight(pl.base, lightDirection, normal);
	
	float attenuation = pl.atten.constant + pl.atten.linear * distanceToPoint + 
						pl.atten.exponent * distanceToPoint * distanceToPoint + 0.0001;
						
	return color / attenuation;
}

void main()
{		
	gl_FragColor = texture2D(diffuse, textCoord0.xy) * calcPointLight(pointLight, normalize(normal0));
}
