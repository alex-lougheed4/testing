package client;

import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLUtil;



import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class clientLauncher {
    static String playerUsername;
    static player thisPlayer;
    static int numberOfNearbyPlayers; //increases per client.player in x radius



    public static void main(String args[]) {
        // add interface to input username
        float thisPlayerPosX = 0f;
        float thisPlayerPosY = 0f;

        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialize glfw");
        }
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);


        Window win = new Window();
        win.createWindow();


        GL.createCapabilities(); //allows manipulation of data on the window

        float tex_coords[] = new float[]{
                0f, 1f,
                1f, 1f,
                1f, 0f,
                0f, 0f,
                1f, 0f,
                0f, 1f
        };

        float playerModelVertices[] = new float[]{ //needs to be taken from the server
                thisPlayerPosX - 0.025f, thisPlayerPosY + 0.025f, 0.5f, //TOP LEFT
                thisPlayerPosX + 0.025f, thisPlayerPosY + 0.025f,0.5f, //TOP RIGHT
                thisPlayerPosX + 0.025f, thisPlayerPosY - 0.025f,0.5f, //BOTTOM RIGHT

                thisPlayerPosX - 0.025f,thisPlayerPosY - 0.025f, 0.5f, //BOTTOM LEFT
                thisPlayerPosX + 0.025f,thisPlayerPosY - 0.025f,0.5f, //BOTTOM RIGHT
                thisPlayerPosX - 0.025f,thisPlayerPosY +0.025f,0.5f, //TOP LEFT

        };


        float groundvertices[] = new float[]{
                -0.5f,0.5f,0, //TOP LEFT
                0.5f,0.5f,0, //TOP RIGHT
                0.5f,-0.5f,0, //BOTTOM RIGHT

                -0.5f,-0.5f,0,  //BOTTOM LEFT
                0.5f,-0.5f,0,  //BOTTOM RIGHT
                -0.5f,0.5f,0,  //TOP LEFT
        };

        GLUtil.setupDebugMessageCallback();
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        Texture tex = new Texture("/redPlayerTexture.png");
        Shader texShader = new Shader("texture");
        Shader shader = new Shader("shader");

        Matrix4f projection = new Matrix4f().ortho2D(-640/2,640/2,480/2,-480/2);


        playerModel thisPlayerModel = new playerModel(texShader, tex, playerModelVertices,tex_coords);

        Shader flatColorShader = new Shader("flatcolor");
        Ground ground = new Ground(flatColorShader, groundvertices);


        float c[] = {1,0,0,0};


        //players = serverLauncher.getPlayerArray(); //needs go take data from server
        //thisPlayer = players.get(1); //Change for each client.player so that each client.player is assigned one of the client.player objects in the array

        while (!win.shouldClose() ) { //loops continuously while the game is running in the window
            glfwPollEvents();

            glClearColor(255, 255,255,0);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            Shader.setUniform("projection",projection);

            ground.render();

            thisPlayerModel.render();

            glfwPollEvents();
            win.swapBuffers();


            //checkForPlayerInput(win,thisPlayerPosX,thisPlayerPosY);

        }


        glfwTerminate();
    }

        public static void setup(){

        }

        // checks for nearby players to see if they need to be drawn for the client (fog fo war)
        public void checkForNearbyPlayers(){

        }

        public String returnUsername(){
        return playerUsername;
        }

        public static void checkForPlayerInput(long window, float posX, float posY) {

            //Upward movement
            if (glfwGetKey(window, GLFW_KEY_W) == GL_TRUE) {
                System.out.print("test");
                //increase thisPlayerPosY
                posY += 0.01;

            }


                //Downwards movement
            if (glfwGetKey(window, GLFW_KEY_S) == GL_TRUE) {
                //Decrease thisPlayerPosY
                posY -= 0.01;
            }

                //Left Movement
            if (glfwGetKey(window, GLFW_KEY_A) == GL_TRUE) {
                //Decease thisPlayerPosX
                posX -= 0.01;
            }

                //Right Movement
            if (glfwGetKey(window, GLFW_KEY_D) == GL_TRUE) {
                //Increase thisPlayerPosX
                posX += 0.01;
            }

        }




}

