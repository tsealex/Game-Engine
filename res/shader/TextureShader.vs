#version 330 core

in vec2 position;
in vec2 texture;

out vec2 textureOut;

uniform vec4 data;


void main()
{
    textureOut = texture;
	// Optimization 
    gl_Position = vec4(position.x * data[0] - data[1] * position.y + data[2], position.y * data[0] + position.x * data[1] + data[3], 0, 1);
}

// data[0] = cos(rot) * scale, [1] = sin(rot) * scale, [2] = x, [3] = y