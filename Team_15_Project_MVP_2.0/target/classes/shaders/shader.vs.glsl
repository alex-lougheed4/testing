#version 330 core
layout (location = 0) in vec3 vertices;
layout (location = 1) in vec2 textures;
out vec2 tex_coords;


uniform mat4 projection;
uniform mat4 worldMatrix;

void main(){
    tex_coords = textures;
    gl_Position = projection * worldMatrix *  vec4(vertices, 1);


}
