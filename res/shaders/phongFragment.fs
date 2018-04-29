#version 330

const int MAX_POINT_LIGHTS = 4;
const int MAX_SPOT_LIGHTS = 4;

in vec2 textCoord0;
in vec3 normal0;
in vec3 worldPos;

out vec4 fragColor;

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

struct SpotLight
{
	PointLight pointLight;
	vec3 direction;
	float cutoff;
};

uniform vec3 baseColor;
uniform vec3 eyePos;
uniform vec3 ambientLight;
uniform sampler2D sampler;
uniform DirectionalLight directionalLight;
uniform PointLight pointLights[MAX_POINT_LIGHTS];
uniform SpotLight spotLights[MAX_SPOT_LIGHTS];

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

vec4 calcSpotLight(SpotLight sl, vec3 normal)
{
	vec3 dir = normalize(worldPos - sl.pointLight.position);
	float spotFactor = dot(dir, sl.direction);
	vec4 color = vec4(0,0,0,0);
	
	if(spotFactor > sl.cutoff)
	{
		color = calcPointLight(sl.pointLight, normal) *
				(1.0 - (1.0 - spotFactor)/(1.0 - sl.cutoff));
	}
	
	return color;
}

void main()
{
	vec4 totalLight = vec4(ambientLight, 1);
	vec4 color = vec4(baseColor, 1);
	vec4 textureColor = texture(sampler, textCoord0.xy);

	if(textureColor != vec4(0,0,0,0))
		color *= textureColor;
		
	vec3 normal = normalize(normal0);
	totalLight += calcDirectionLight(directionalLight, normal);
	
	for(int i = 0; i < MAX_POINT_LIGHTS; ++i)
	{
		if(pointLights[i].base.intensity > 0)
			totalLight += calcPointLight(pointLights[i], normal);
	}
	
	for(int i = 0; i < MAX_SPOT_LIGHTS; ++i)
	{
		if(spotLights[i].pointLight.base.intensity > 0)
			totalLight += calcSpotLight(spotLights[i], normal);
	}
		
	gl_FragColor = color * totalLight;
}
