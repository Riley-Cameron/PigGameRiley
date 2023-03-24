package edu.up.cs301.pig;
import java.util.Random;

import edu.up.cs301.game.infoMsg.GameState;
public class PigGameState extends GameState {
    private int playerTurn;
    private int p1Score;
    private int p2Score;
    private int runningTotal;
    private int dieValue;

    //ctor
    public PigGameState() {
        playerTurn = 0;
        p1Score = 0;
        p2Score = 0;
        runningTotal = 0;
        dieValue = 1;
    }

    //copy ctor
    public PigGameState(PigGameState pgs) {
        playerTurn = pgs.getPlayerTurn();
        p1Score = pgs.getP1Score();
        p2Score = pgs.getP2Score();
        runningTotal = pgs.getRunningTotal();
        dieValue = pgs.getDieValue();
    }

    public void rollDice() {
        Random gen = new Random();
        dieValue = gen.nextInt(6) + 1;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getP1Score() {
        return p1Score;
    }

    public void setP1Score(int p1Score) {
        this.p1Score = p1Score;
    }

    public int getP2Score() {
        return p2Score;
    }

    public void setP2Score(int p2Score) {
        this.p2Score = p2Score;
    }

    public int getRunningTotal() {
        return runningTotal;
    }

    public void setRunningTotal(int runningTotal) {
        this.runningTotal = runningTotal;
    }

    public int getDieValue() {
        return dieValue;
    }

    public void setDieValue(int dieValue) {
        this.dieValue = dieValue;
    }




}
