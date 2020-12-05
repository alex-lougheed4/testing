package client;

import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.*;


public class  Window {
    private long window;

    private int width,height;
    public Window(){
        setSize(640,480);
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

    public int getWidht(){ return width; }

    public int getHiehgt(){return height; }
}
