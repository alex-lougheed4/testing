#version 330 core
out vec4 FragColor;
in vec2 tex_coords;

uniform sampler2D sampler;



void main(){
    FragColor = texture(sampler, tex_coords); //ground color gray
}