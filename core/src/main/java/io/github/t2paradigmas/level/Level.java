package io.github.t2paradigmas.level;

import io.github.t2paradigmas.Main;
import io.github.t2paradigmas.tabuleiro.Tabuleiro;

public class Level {
    private final Integer levelNumber;
    private final Integer numSwap;
    private boolean isPlayed;
    private final Integer numTerra;
    private final Integer numPedregulho;
    private final Integer numRocha;
    private Integer score;
    private final Tabuleiro tabuleiro;
    private final int[][] matriz;
    private Integer record;

    public Level(Integer levelNumber, Integer numSwap, Integer numTerra, Integer numPedregulho, Integer numRocha, int[][] matriz) {
        this.matriz = matriz;
        this.levelNumber = levelNumber;
        this.numSwap = numSwap;
        this.numTerra = numTerra;
        this.numPedregulho = numPedregulho;
        this.numRocha = numRocha;
        this.score = 0;
        this.isPlayed = false;
        this.tabuleiro = new Tabuleiro(matriz, numSwap);
        this.record = 0;

    }
    public int[][] getMatriz() {
        return matriz;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    public Integer getLevelNumber() {
        return levelNumber;
    }

    public Integer getScore(){
        return score;
    }

    public void setScore(Integer score) {
        this.score += score;
    }

    public Integer getNumterra() {
        return numTerra;
    }

    public Integer getNumPedregulho() {
        return numPedregulho;
    }

    public Integer getNumRocha() {
        return numRocha;
    }

    public void setRecord(Main game) {
        if(score>record) {
            this.record = score;
            game.addScore(score);
        }
    }

    public void resetScore(){
        this.score = 0;
        tabuleiro.resetTabuleiro(numSwap);
    }

}
