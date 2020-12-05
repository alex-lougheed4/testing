package client;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.stb.STBImage.stbi_load_from_memory;


import org.lwjgl.BufferUtils;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.nio.ByteBuffer;
import java.io.ByteArrayInputStream;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;



import javax.imageio.ImageIO;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

public class Texture {
    private int id;
    private int width;
    private int height;

    public Texture(String filename) {

        try{
            ByteBuffer image;
            int width, height;

            try (MemoryStack stack = MemoryStack.stackPush()) {

                IntBuffer w    = stack.mallocInt(1);
                IntBuffer h    = stack.mallocInt(1);
                IntBuffer comp = stack.mallocInt(1);

                byte[] bytes = Texture.class.getResourceAsStream(filename).readAllBytes();
                ByteBuffer imageBuffer = ByteBuffer.allocateDirect(bytes.length).order(ByteOrder.nativeOrder());
                imageBuffer.put(bytes);
                imageBuffer.flip();

                if (!STBImage.stbi_info_from_memory(imageBuffer, w, h, comp)) {
                    throw new RuntimeException("Failed to read image information: " + STBImage.stbi_failure_reason());
                } else {
                    System.out.println("OK with reason: " + STBImage.stbi_failure_reason());
                }
                System.out.println("Image width: " + w.get(0));
                System.out.println("Image height: " + h.get(0));
                System.out.println("Image components: " + comp.get(0));
                System.out.println("Image HDR: " + STBImage.stbi_is_hdr_from_memory(imageBuffer));

                image = stbi_load_from_memory(imageBuffer, w, h, comp, 0);
                if (image == null) {
                    throw new RuntimeException("Failed to load image: " + STBImage.stbi_failure_reason());
                }

                width = w.get();
                height = h.get();
            }


            id = glGenTextures();

            glBindTexture(GL_TEXTURE_2D,id);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

            glTexImage2D(GL_TEXTURE_2D,0,GL_RGBA8, width, height ,0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            glBindTexture(GL_TEXTURE_2D,0);


        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void bind(int sampler){
        if (sampler >= 0 && sampler < 32)
        {
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D,id);
        }
    }
    public void unbind()
    {
        glBindTexture(GL_TEXTURE_2D,0);
    }

    public void destroy()
    {
        glDeleteTextures(id);
    }

}
