import client.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class serverSideLauncher {
    private static boolean gameRunning = false;
    public static ArrayList<player> players = new ArrayList<player>();
    public static player newPlayer;
    public static List<String> possiblePlayerColors = new ArrayList<>(List.of("RED","BLUE", "CYAN" ,"GREEN", "MAGENTA" , "PINK", "BROWN", "ORANGE"));
    public static List<String> takenPlayerColorList = null;
    private static Long startGameTime;




    public static void main(String args[]){
        int numberOfPlayers = 0; //set to number of connections of players to servers

        for (int i = 0; i<numberOfPlayers;i++){
            String newUsername = ""; //get username from client number i
            newPlayer = new player(newUsername);
            newPlayer.setUserID();
            int ID = newPlayer.getUserID();
            players.set(i, newPlayer); //add current client.player to client.player arraylist using username
        }
        startGameTime = System.currentTimeMillis();



        gameRunning = true;
        while (gameRunning){ //game loop
            if(System.currentTimeMillis() - startGameTime >= 60000){ //timer, needs checking
                gameRunning = !gameRunning;
            }



        }

    }

    public String getPlayerColor(){ //check logic to make sure it doesn't end in a runtime error
        String playerColor = "";
        //Assigns a color in possiblePlayerColors and not in takenPlayerColorList
        Random rand = new Random();
        int randomColorNumber = rand.nextInt(8);
        if(!(takenPlayerColorList.contains(possiblePlayerColors.get(randomColorNumber)))){ //need to create a contains method to check if the element is in the other list or manually check here
            playerColor = possiblePlayerColors.get(randomColorNumber);
            return playerColor;
        }
        else {
            return getPlayerColor();
        }
    }

    public void setup(){

    }




}
