package client;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;


public class playerModel {

    private final Shader shader;
    private final Texture texture;

    private int vaoId;
    private int vboId;
    private int draw_count;
    private int t_id;




    //Class for ground object
    public playerModel(Shader shader, Texture texture, float[] vertices, float[] tex_coords){
        this.shader = shader;
        this.texture = texture;
        draw_count = vertices.length;
//Initial setup for some model, all of this only needs to be done once

        FloatBuffer playerVertexbuffer = BufferUtils.createFloatBuffer(vertices.length);
        playerVertexbuffer.put(vertices);
        playerVertexbuffer.flip();

        FloatBuffer playerTexCoordBuffer = BufferUtils.createFloatBuffer(tex_coords.length);
        playerTexCoordBuffer.put(tex_coords);
        playerTexCoordBuffer.flip();





        this.vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);


        this.vboId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);



        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, playerVertexbuffer, GL15.GL_STATIC_DRAW);


        glVertexAttribPointer(0,3, GL_FLOAT,false,3 * Float.BYTES,0); //accesses attribute
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        t_id =glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER,t_id);




        glBufferData(GL_ARRAY_BUFFER,playerTexCoordBuffer, GL15.GL_STATIC_DRAW);
        glVertexAttribPointer(1,2, GL_FLOAT,false,2 * Float.BYTES,0);
        glEnableVertexAttribArray(1);
        glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);



        glBindVertexArray(0);

        shader.bind();
        shader.setUniform("u_Texture", 0); //texture slot 0

        shader.unbind();

    }

    public void render(){ //draws vbo player WIP

        shader.bind();
        texture.bind(0);

        glBindVertexArray(vaoId);



        glDrawArrays(GL_TRIANGLES, 0, draw_count);

        glBindVertexArray(0);

        texture.unbind();
        shader.unbind();



    }


}
