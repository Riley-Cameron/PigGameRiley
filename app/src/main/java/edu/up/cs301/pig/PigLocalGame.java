package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    private PigGameState gameState;

    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        gameState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if (playerIdx == gameState.getPlayerTurn()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if (action instanceof PigHoldAction) {
            //add running total to the score of the player who's turn it is
            if (gameState.getPlayerTurn() == 0) {
                gameState.setP1Score(gameState.getP1Score() + gameState.getRunningTotal());
            } else if (gameState.getPlayerTurn() == 1) {
                gameState.setP2Score(gameState.getP2Score() + gameState.getRunningTotal());
            }

            //clear running total
            gameState.setRunningTotal(0);

            //change the player turn
            if (players.length > 1) {
                if (gameState.getPlayerTurn() == 0) {
                    gameState.setPlayerTurn(1);
                } else {
                    gameState.setPlayerTurn(0);
                }
            }

            return true;
        } else if (action instanceof PigRollAction) {
            gameState.rollDice();
            if (gameState.getDieValue() != 1) {
                gameState.setRunningTotal(gameState.getRunningTotal() + gameState.getDieValue());
            } else {
                gameState.setRunningTotal(0);

                if (players.length > 1) {
                    if (gameState.getPlayerTurn() == 0) {
                        gameState.setPlayerTurn(1);
                    } else {
                        gameState.setPlayerTurn(0);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new PigGameState(gameState));
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        if (gameState.getP1Score() >= 50) {
            return "Player one won with a score of " + gameState.getP1Score() + "!";
        } else if (gameState.getP2Score() >= 50) {
            return "Player two won with a score of " + gameState.getP2Score() + "!";
        } else {
            return null;
        }
    }

}// class PigLocalGame
