package edu.up.cs301.pig;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

public class PigSmartComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigSmartComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        PigGameState gameState = new PigGameState((PigGameState)info);

        if (gameState.getPlayerTurn() != playerNum) {
            return;
        } else {
            int myScore;
            int oppScore;
            int total;
            sleep(2000);

            //get info about the game
            if (playerNum == 0) {
                myScore = gameState.getP1Score();
                oppScore = gameState.getP2Score();
            } else {
                oppScore = gameState.getP1Score();
                myScore = gameState.getP2Score();
            }
            total = gameState.getRunningTotal();

            if (total + myScore >= 50) {
                game.sendAction(new PigHoldAction(this));
            } else {
                if (total < 7) {
                    game.sendAction(new PigRollAction(this));
                } else {
                    Random rand = new Random();
                    if (rand.nextInt(4) == 0) {//risk factor is set to 0.25 'riskiness'
                        game.sendAction(new PigRollAction(this));
                    } else {
                        game.sendAction(new PigHoldAction(this));
                    }
                }
            }
        }


    }//receiveInfo

}