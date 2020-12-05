#version 330 core

uniform vec4 u_Color = vec4(1.0, 1.0, 1.0, 1.0);

out vec4 FragColor;

void main()
{
    FragColor = u_Color;
}