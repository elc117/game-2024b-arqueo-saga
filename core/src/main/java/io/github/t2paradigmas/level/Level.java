package io.github.t2paradigmas.level;

import io.github.t2paradigmas.tabuleiro.Tabuleiro;

public class Level {
    private Integer levelNumber;
    private Integer numSwap;
    private boolean isPlayed;
    private Integer numTerra;
    private Integer numPedregulho;
    private Integer numRocha;
    private Integer score;
    private Tabuleiro tabuleiro;
    private int[][] matriz;

    public Level(Integer levelNumber, Integer numSwap, Integer numTerra, Integer numPedregulho, Integer numRocha, int[][] matriz) {
        this.matriz = matriz;
        this.levelNumber = levelNumber;
        this.numSwap = numSwap;
        this.numTerra = numTerra;
        this.numPedregulho = numPedregulho;
        this.numRocha = numRocha;
        this.score = 0;
        this.isPlayed = false;
        this.tabuleiro = new Tabuleiro(matriz);
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public Integer getLevelNumber() {
        return levelNumber;
    }
}
