package client;

import org.joml.Vector3f;

public class playerCamera {

    private final Vector3f position;
    private final Vector3f rotation;

    public playerCamera(float playerPosX, float playerPosY){
        //Set playerCamera
        position = new Vector3f(playerPosX,playerPosX,2.0f);
        rotation = new Vector3f(0, 0, 0);
    }

    public void updatePlayerCameraPosition(float playerX, float playerY){
        position.x = playerX;
        position.y = playerY;
    }




}
