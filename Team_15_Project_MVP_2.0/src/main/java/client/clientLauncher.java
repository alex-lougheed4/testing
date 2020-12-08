package client;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLUtil;



import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class clientLauncher {
    static String playerUsername;
    static player thisPlayer;
    static int numberOfNearbyPlayers; //increases per client.player in x radius
    /**
     * Field of View in Radians
     */
    private static final float FOV = (float) Math.toRadians(60.0f);

    private static final float Z_NEAR = 0.01f;

    private static final float Z_FAR = 1000.f;

    private static Matrix4f projectionMatrix;

    private static Transformation transformation;






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

        float aspectRatio = (float) win.getWidth() / win.getHeight();
        projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio,
                Z_NEAR, Z_FAR);




        playerModel thisPlayerModel = new playerModel(texShader, tex, playerModelVertices,tex_coords); //creates new playermodel

        GameItem gameItem = new GameItem(thisPlayerModel); //creates new gameItem of playerModel

        GameItem[] gameItems = new GameItem[]{
            gameItem
        };


        Shader flatColorShader = new Shader("flatcolor");
        Ground ground = new Ground(flatColorShader, groundvertices);


        //float c[] = {1,0,0,0};

        transformation = new Transformation();



        //players = serverLauncher.getPlayerArray(); //needs go take data from server
        //thisPlayer = players.get(1); //Change for each client.player so that each client.player is assigned one of the client.player objects in the array

        while (!win.shouldClose() ) { //loops continuously while the game is running in the window
            glfwPollEvents();

            glClearColor(255, 255,255,0);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            shader.setUniform("projection",projectionMatrix);
            shader.setUniform("transform",thisPlayerModel.getTransform());


            //clientLauncher.render(shader,gameItems);


            ground.render();

            thisPlayerModel.render();




            glfwPollEvents();
            win.swapBuffers();

            //Upwards Movement

            if (glfwGetKey(win.returnWindow(), GLFW_KEY_W) == GL_TRUE) {
                System.out.print("test1");
                //increase thisPlayerPosY
                Vector3f temp = new Vector3f(0f,thisPlayerPosY+0.01f,0f);
                thisPlayerModel.setPosition(temp);

            }


            //Downwards movement
            if (glfwGetKey(win.returnWindow(), GLFW_KEY_S) == GL_TRUE) {
                System.out.print("test2");
                //Decrease thisPlayerPosY



            }

            //Left Movement
            if (glfwGetKey(win.returnWindow(), GLFW_KEY_A) == GL_TRUE) {
                System.out.print("test3");
                //Decease thisPlayerPosX

            }

            //Right Movement
            if (glfwGetKey(win.returnWindow(), GLFW_KEY_D) == GL_TRUE) {
                System.out.print("test4");
                //Increase thisPlayerPosX

            }


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

     /**   public static void checkForPlayerInput(long window, float posX, float posY) {

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
      **/



        public static void render(Shader shader, GameItem[] gameItems){
            for(GameItem gameItem : gameItems) {
                // Set world matrix for this item
                Matrix4f worldMatrix =
                        transformation.getWorldMatrix(
                                gameItem.getPosition(),
                                gameItem.getRotation(),
                                gameItem.getScale());
                shader.setUniform("worldMatrix", worldMatrix);
                // Render the mes for this game item
                gameItem.getPlayerModel().render();
            }
        }







}

