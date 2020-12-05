package client;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

import java.nio.FloatBuffer;

import org.joml.Vector4f;


import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Ground {

    private final Shader shader;

    private int draw_count;
    private int vaoId;
    private int vboId;

    //Class for ground object

    public Ground(Shader shader, float[] vertices)
    {
        this.shader = shader;
        draw_count = vertices.length / 3;

        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
        buffer.put(vertices);
        buffer.flip();

        this.vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        this.vboId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);

        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * Float.BYTES, 0);

        glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        glEnableVertexAttribArray(0);

        glBindVertexArray(0);

    }

    public void render(){ //draws vbo ground

        shader.bind();
        shader.setUniform4f("u_Color", new Vector4f(.7f, .7f, .7f, 1.0f));



        glBindVertexArray(vaoId);

        glDrawArrays(GL_TRIANGLES, 0, draw_count);

        glBindVertexArray(0);

        shader.unbind();





    }


}
