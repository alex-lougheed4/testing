import java.util.Random;

public class powerUps {
    private Random rn = new Random();


    public powerUps() {

    }

    private int determinePowerUp() {
        int randomPowerUpValue = rn.nextInt(10); //Change value to number of powerups in game
        return randomPowerUpValue;
    }

    private void choosePowerUp(int randomPowerUpValue) { //Add each statement for powerups if more added
        if (randomPowerUpValue == 0) {
            //call first powerup
        }
        else if (randomPowerUpValue == 1) {
            //call second powerup
        }
        // no else needed as each case is accounted for
    }

    //first power up method, speed boost take in multiplier from client.player class


}
