#version 330 core

in vec2 textureOut;

out vec4 colorOut;

uniform sampler2D textureImg;

void main()
{
    colorOut = texture(textureImg, textureOut);
}