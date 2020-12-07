package client;

import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.*;


public class  Window {
    private long window;



    private int width = 640,height = 480;
    public Window(){
        setSize(width,height);
    }

    public void createWindow(){

         window = glfwCreateWindow(width,height,"TagTastic Maze!",0,0 ); //glfwGetPrimaryMonitor() for fullscreen

         if(window==0){
             GLFWErrorCallback.createPrint(System.err);
             throw new IllegalStateException("Failed to create window");
         }

         glfwShowWindow(window);

         glfwMakeContextCurrent(window);

    }

    public boolean shouldClose(){
        return glfwWindowShouldClose(window)!= false;
    }

    public void swapBuffers(){
        glfwSwapBuffers(window);
    }

    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void setProjection(){

    }


    public int getWidth(){ return width; }

    public int getHeight(){return height; }

    public long returnWindow(){return window; }
}
