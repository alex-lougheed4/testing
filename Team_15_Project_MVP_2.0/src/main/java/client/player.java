package client;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

public class player {

    //Player Variable declarations
    ArrayList<Float> vertices = new ArrayList<Float>();//Vertices of cube client.player-model
    private double posX;
    private double posY;
    private String userName;
    private int userID;
    private boolean hasTag = false;
    private float movementMultiplier;
    Random rand = new Random();
    String playerModelColour;



    //Player Constructor (needs username passed in)
    public player(String userName) {
        //set random posX and posZ to random empty maze tiles
        setUserID();
        //Set username from variable in main
        userName = this.userName;

    }

    //Player Methods
    private boolean updateTagState() {
        return hasTag = !hasTag;
    }

    private void moveRight() {
        posX++; //Edit based upon base maze units
    }

    private void moveLeft() {
        posX--; //Edit based upon base maze units
    }

    private void moveUp() {
        posY++; //Edit based upon base maze units
    }

    private void moveDown() {
        posY--; //Edit based upon base maze units
    }

    public void calculateVertices(float posX, float posY) {

    }



    public void setPlayerModelColour(Color playerColour) {
        Color playerModelColour = playerColour;
        System.out.print(playerModelColour);
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID() { //May need to go on server-side
        //Create Unique userID (needs to be checked against list of used ID's
        int range = 999 - 100 + 1;
        userID = rand.nextInt(range) + 1;

    }

    public double getPlayerPosX() {
        return posX;
    }

    public double getPlayerPosY(){
        return posY;
    }







}
