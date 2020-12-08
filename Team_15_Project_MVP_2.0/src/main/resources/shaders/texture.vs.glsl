#version 330 core

layout(location = 0) in vec3 a_Position;
layout(location = 1) in vec2 a_TexCoord;

//uniform view projection + transform would be here too
uniform mat4 transform;

out vec2 v_TexCoord;

void main()
{
    v_TexCoord = a_TexCoord;
    gl_Position = transform * vec4(a_Position, 1.0) ; //viewProjection * transform * vec4(a_Pos, 1.0) if using camera + transform
}