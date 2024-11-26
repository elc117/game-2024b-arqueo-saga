package io.github.t2paradigmas.tabuleiro;

import io.github.t2paradigmas.blocos.Bloco;

import java.util.ArrayList;
import java.util.Random;

enum tipoBloco{
    BASICO(0), TERRA(1), PEDREGULHO(2), ROCHA(3);

    public int tipo;
    tipoBloco(int i) {
        tipo = i;
    }
}

public class Tabuleiro {

    private Integer brokenTerra;
    private Integer brokenPedregulho;
    private Integer brokenRocha;
    private ArrayList<Bloco> blocos;

    public Tabuleiro(int[][] matriz) {
        this.brokenTerra = 0;
        this.brokenPedregulho = 0;
        this.brokenRocha = 0;
        this.blocos = new ArrayList<>();
        generateBlocos(matriz);
    }

    public Integer getBrokenTerra() {
        return brokenTerra;
    }

    public void setBrokenTerra(Integer brokenTerra) {
        this.brokenTerra = brokenTerra;
    }

    public Integer getBrokenPedregulho() {
        return brokenPedregulho;
    }

    public void setBrokenPedregulho(Integer brokenPedregulho) {
        this.brokenPedregulho = brokenPedregulho;
    }

    public Integer getBrokenRocha() {
        return brokenRocha;
    }

    public void setBrokenRocha(Integer brokenRocha) {
        this.brokenRocha = brokenRocha;
    }

    public ArrayList<Bloco> getBlocos() {
        return blocos;
    }

    private void generateBlocos(int[][] matriz) {
        for(int linha = 0; linha < 9; linha++){
            for(int coluna = 0; coluna < 9; coluna++){
                if(matriz[linha][coluna] == tipoBloco.BASICO.tipo){
                    Random rand = new Random();
                    int n = rand.nextInt(5) + 1;
                    this.blocos.add(new Bloco(linha, coluna, n));
                }
                else if(matriz[linha][coluna] == tipoBloco.TERRA.tipo){
                    this.blocos.add(new Bloco(linha, coluna, "terra"));
                }
                else if(matriz[linha][coluna] == tipoBloco.PEDREGULHO.tipo){
                    this.blocos.add(new Bloco(linha, coluna, "pedregulho"));
                }
                else if(matriz[linha][coluna] == tipoBloco.ROCHA.tipo){
                    this.blocos.add(new Bloco(linha, coluna, "rocha"));
                }
            }
        }
    }
}
