package io.github.t2paradigmas.tabuleiro;

import io.github.t2paradigmas.blocos.Bloco;
import io.github.t2paradigmas.blocos.BlocoEspecial;
import io.github.t2paradigmas.utilitarios.Tuple;
import io.github.t2paradigmas.utilitarios.tipoBloco;

import java.util.ArrayList;
import java.util.Random;



public class Tabuleiro {

    private Integer brokenTerra;
    private Integer brokenPedregulho;
    private Integer brokenRocha;
    private Bloco[][] inGameMatrix;

    public Tabuleiro(int[][] matriz) {
        this.brokenTerra = 0;
        this.brokenPedregulho = 0;
        this.brokenRocha = 0;
        this.inGameMatrix = new Bloco[matriz.length][matriz[0].length];

    }

    public Integer getBrokenTerra() {
        return brokenTerra;
    }

    public void setBrokenTerra(Integer brokenTerra) {
        this.brokenTerra += brokenTerra;
    }

    public Integer getBrokenPedregulho() {
        return brokenPedregulho;
    }

    public void setBrokenPedregulho(Integer brokenPedregulho) {
        this.brokenPedregulho += brokenPedregulho;
    }

    public Integer getBrokenRocha() {
        return brokenRocha;
    }

    public void setBrokenRocha(Integer brokenRocha) {
        this.brokenRocha += brokenRocha;
    }

    public void setBrokenBlocoEspecial(Integer tipo){
        if(tipo == tipoBloco.TERRA.tipo){
            setBrokenTerra(1);
        }
        else if(tipo == tipoBloco.PEDREGULHO.tipo){
            setBrokenPedregulho(1);
        }
        else if(tipo == tipoBloco.ROCHA.tipo){
            setBrokenRocha(1);
        }
    }


    public Bloco[][] getInGameMatrix() {
        return inGameMatrix;
    }

    public void generateBlocos(int[][] matriz) {
        for(int linha = 0; linha < 9; linha++){
            for(int coluna = 0; coluna < 9; coluna++){
                if(matriz[linha][coluna] == tipoBloco.BASICO.tipo){
                    Random rand = new Random();
                    int n = rand.nextInt(5);
                    Bloco novo = new Bloco(linha, coluna, n);
//                    this.blocos.add(novo);
                    this.inGameMatrix[linha][coluna] = novo;
                }
                else if(matriz[linha][coluna] == tipoBloco.TERRA.tipo){
                    Bloco novo = new BlocoEspecial(linha, coluna, "terra");
//                    this.blocos.add(novo);
                    this.inGameMatrix[linha][coluna] = novo;
                }
                else if(matriz[linha][coluna] == tipoBloco.PEDREGULHO.tipo){
                    Bloco novo = new BlocoEspecial(linha, coluna, "pedregulho");
//                    this.blocos.add(novo);
                    this.inGameMatrix[linha][coluna] = novo;
                }
                else if(matriz[linha][coluna] == tipoBloco.ROCHA.tipo){
                    Bloco novo = new BlocoEspecial(linha, coluna, "rocha");
//                    this.blocos.add(novo);
                    this.inGameMatrix[linha][coluna] = novo;
                }
            }
        }
    }

    public boolean isSwapPossible(Integer l1, Integer c1, Integer l2, Integer c2) {

        if(inGameMatrix[l1][c1].getCor() == null || inGameMatrix[l2][c2].getCor() == null || inGameMatrix[l1][c1].getCor().equals(inGameMatrix[l2][c2].getCor())){
            return false;
        }

        if(l1.equals(l2)){ //se a coluna que vai ser trocada

            //testa se o bloco 2 vai formar trinca na coluna do bloco 1
            int coluna = c1 + 1;
            while(coluna < inGameMatrix[0].length && (inGameMatrix[l1][coluna].getCor().equals(inGameMatrix[l2][c2].getCor()))){
                coluna++;
            }
            if(coluna - c1 >=3){
                return true;
            }

            coluna = c1 - 1;
            while(coluna >= 0 && (inGameMatrix[l1][coluna].getCor().equals(inGameMatrix[l2][c2].getCor()))){
                coluna--;
            }

            if(c1 - coluna >=3)
                return true;

            //testa se o bloco 1 vai formar trinca na coluna do bloco 2
            coluna = c2 + 1;
            while(coluna < inGameMatrix[0].length && (inGameMatrix[l2][coluna].getCor().equals(inGameMatrix[l1][c1].getCor()))){
                coluna++;
            }
            if(coluna - c2 >=3){
                return true;
            }

            coluna = c2 - 1;
            while(coluna >= 0 && (inGameMatrix[l2][coluna].getCor().equals(inGameMatrix[l1][c1].getCor()))){
                coluna--;
            }

            if(c2 - coluna >=3)
                return true;

        }
        if(c1.equals(c2)){ //se a linha que vai ser trocada
            //testa se o bloco 2 vai formar trinca na linha do bloco 1
            int linha = c1 + 1;
            while(linha < inGameMatrix.length && (inGameMatrix[linha][c1].getCor().equals(inGameMatrix[l2][c2].getCor()))){
                linha++;
            }
            if(linha - c1 >=3){
                return true;
            }

            linha = c1 - 1;
            while(linha >= 0 && (inGameMatrix[linha][c1].getCor().equals(inGameMatrix[l2][c2].getCor()))){
                linha--;
            }

            if(c1 - linha >=3)
                return true;

            //testa se o bloco 1 vai formar trinca na linha do bloco 2
            linha = c2 + 1;
            while(linha < inGameMatrix.length && (inGameMatrix[linha][c2].getCor().equals(inGameMatrix[l1][c1].getCor()))){
                linha++;
            }
            if(linha - c2 >=3){
                return true;
            }

            linha = c2 - 1;
            while(linha >= 0 && (inGameMatrix[linha][c2].getCor().equals(inGameMatrix[l1][c1].getCor()))){
                linha--;
            }

            return c2 - linha >= 3;
        }

        return false;
    }

    private int getCont(Integer l, Integer c, int cont, int i, int j, ArrayList<Tuple> toBreak) {
        if(inGameMatrix[l][c].getCor().equals(inGameMatrix[l+j][c+i].getCor())  && inGameMatrix[l][c] != null){
            cont++;
            if(toBreak != null && cont >=2){
                toBreak.add(new Tuple(l+j, c+i));
            }
            if(l+j >= 0 && l+j < inGameMatrix.length && c+i >= 0 && c+i < inGameMatrix[l].length){
                cont = getCont(l+j, c+i, cont, i, j, toBreak);
            }
        }
        return cont;
    }

    public int swapTiles(Integer l1, Integer c1, Integer l2, Integer c2) {
        int n = inGameMatrix[l1][c1].getCor();
        inGameMatrix[l1][c1].setCor(inGameMatrix[l2][c2].getCor());
        inGameMatrix[l2][c2].setCor(n);
        return findMatches();
    }

    public int findMatches(){
        int contColuna = 0;
        int contLinha = 0;
        ArrayList<Tuple> toBreak = new ArrayList<>();

        for(int linha = 0; linha < 9; linha++){
            for(int coluna = 0; coluna < 9; coluna++){
                contColuna = getCont(linha, coluna, contColuna, 1, 0, toBreak);
                contLinha = getCont(linha, coluna, contLinha, 0, 1, toBreak);
                if(contColuna >= 2){
                    Tuple lastTuple = toBreak.get(toBreak.size()-1);
                    for(int i = lastTuple.coluna; i != coluna; i--){
                        Tuple nova = new Tuple(linha, i);
                        if(!toBreak.contains(nova)){
                            toBreak.add(nova);
                        }
                    }
                }

                if(contLinha >= 2){
                    Tuple lastTuple = toBreak.get(toBreak.size()-1);
                    for(int j = lastTuple.linha; j != linha; j--){
                        Tuple nova = new Tuple(j, coluna);
                        if(!toBreak.contains(nova)){
                            toBreak.add(nova);
                        }
                    }
                }
            }
        }

        return breakMatches(toBreak);
    }

    private int breakMatches(ArrayList<Tuple> toBreak){
        for(Tuple tuple : toBreak){
            inGameMatrix[tuple.linha][tuple.coluna].setCor(-1);
            inGameMatrix[tuple.linha][tuple.coluna].setBloco(null);
            if(tuple.coluna > 0){
                if(inGameMatrix[tuple.linha][tuple.coluna-1].getCor() == null){
                    ((BlocoEspecial)inGameMatrix[tuple.linha][tuple.coluna-1]).setHits(1);
                    if(((BlocoEspecial)inGameMatrix[tuple.linha][tuple.coluna-1]).getHits().equals(0)){
                        setBrokenBlocoEspecial(inGameMatrix[tuple.linha][tuple.coluna-1].getCor());
                        inGameMatrix[tuple.linha][tuple.coluna-1].setCor(-1);
                        inGameMatrix[tuple.linha][tuple.coluna -1].setBloco(null);
                        Tuple nova = new Tuple(tuple.linha, tuple.coluna-1);
                        toBreak.add(nova);
                    }
                }
            }
            if(tuple.coluna < 8){
                if(inGameMatrix[tuple.linha][tuple.coluna+1].getCor() == null){
                    ((BlocoEspecial)inGameMatrix[tuple.linha][tuple.coluna+1]).setHits(1);
                    if(((BlocoEspecial)inGameMatrix[tuple.linha][tuple.coluna+1]).getHits().equals(0)){
                        setBrokenBlocoEspecial(inGameMatrix[tuple.linha][tuple.coluna+1].getCor());
                        inGameMatrix[tuple.linha][tuple.coluna+1].setCor(-1);
                        inGameMatrix[tuple.linha][tuple.coluna+1].setBloco(null);

                        Tuple nova = new Tuple( tuple.linha,tuple.coluna+1);
                        toBreak.add(nova);
                    }
                }
            }
            if(tuple.linha > 0){
                if(inGameMatrix[tuple.linha-1][tuple.coluna].getCor() == null){
                    ((BlocoEspecial)inGameMatrix[tuple.linha-1][tuple.coluna]).setHits(1);
                    if(((BlocoEspecial)inGameMatrix[tuple.linha-1][tuple.coluna]).getHits().equals(0)){
                        setBrokenBlocoEspecial(inGameMatrix[tuple.linha-1][tuple.coluna].getCor());

                        inGameMatrix[tuple.linha-1][tuple.coluna].setCor(-1);
                        inGameMatrix[tuple.linha-1][tuple.coluna].setBloco(null);

                        Tuple nova = new Tuple(tuple.linha-1,tuple.coluna);
                        toBreak.add(nova);
                    }
                }
            }
            if(tuple.linha < 8){
                if(inGameMatrix[tuple.linha+1][tuple.coluna].getCor() == null){
                    ((BlocoEspecial)inGameMatrix[tuple.linha+1][tuple.coluna]).setHits(1);
                    if(((BlocoEspecial)inGameMatrix[tuple.linha+1][tuple.coluna]).getHits().equals(0)){
                        setBrokenBlocoEspecial(inGameMatrix[tuple.linha+1][tuple.coluna].getCor());

                        inGameMatrix[tuple.linha+1][tuple.coluna].setCor(-1);
                        inGameMatrix[tuple.linha+1][tuple.coluna].setBloco(null);

                        Tuple nova = new Tuple(tuple.linha+1,tuple.coluna);
                        toBreak.add(nova);
                    }
                }
            }
        }

        return toBreak.size() + refillTiles();
    }

    public int refillTiles(){
        for(int linha = inGameMatrix.length - 1; linha >= 0; linha--){
            for(int coluna = inGameMatrix[0].length - 1; coluna >= 0; coluna--){
                if(inGameMatrix[linha][coluna].getCor() == -1){
                    Tuple descendFrom = getTopTile(linha, coluna);
                    if(descendFrom != null){
                        Bloco onTop = inGameMatrix[descendFrom.linha][descendFrom.coluna];
                        inGameMatrix[linha][coluna] = null;
                        inGameMatrix[linha][coluna] = onTop;
                    }
                    else{ //cria um novo bloco se não tiver nenhum em cima
                        Random rand = new Random();
                        int n = rand.nextInt(5);
                        Bloco novo = new Bloco(linha, coluna, n);
                        this.inGameMatrix[linha][coluna] = novo;
                    }
                }
            }
        }

        return findMatches();
    }

    private Tuple getTopTile(Integer linha, Integer coluna){
        if(linha > 0){ // se não tiver chegado no topo do tabuleiro
            if(inGameMatrix[linha][coluna].getCor().equals(-1)){
                return getTopTile(linha-1, coluna); //se o bloco atual está quebrado, recursivamente busca um bloco inteiro
            }
            else //se o bloco atual estiver inteiro, retorna sua posição
                return new Tuple(linha, coluna);
        }
        else //se estiver no topo do tabuleiro, retorna nulo
            return null;
    }

}
