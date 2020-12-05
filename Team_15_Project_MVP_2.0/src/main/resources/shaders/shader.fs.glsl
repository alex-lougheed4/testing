#version 330 core
out vec4 FragColor;

uniform sampler2D sampler;

varying vec2 tex_coords;

void main(){
    gl_FragColor = texture2D(sampler, tex_coords); //ground color gray
}