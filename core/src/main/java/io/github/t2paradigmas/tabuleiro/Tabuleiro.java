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

    public void setBrokenBlocoEspecial(String tipo){
        if(tipo.equals("terra")){
            setBrokenTerra(1);
        }
        else if(tipo.equals("pedregulho")){
            setBrokenPedregulho(1);
        }
        else if(tipo.equals("rocha")){
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
        int found = findMatches(true);
        while(found > 0)
            found = findMatches(true);

        for(int linha = 0; linha < 9; linha++){
            for(int coluna = 0; coluna < 9; coluna++){
                inGameMatrix[linha][coluna].setPreviousPos(inGameMatrix[linha][coluna].getCurrentPos());
            }
        }


    }

    private boolean areNeighbours(Integer l1, Integer c1, Integer l2, Integer c2) {
        return( Math.abs(l1 - l2) <= 1 && Math.abs(c1 - c2) <= 1 );
    }

    public boolean isSwapPossible(Integer l1, Integer c1, Integer l2, Integer c2) {
//        System.out.println("   " + l1 + ", " + c1 + ", linha2 " + l2 + ", " + c2);
        if(inGameMatrix[l1][c1].getCor() == null || inGameMatrix[l2][c2].getCor() == null || inGameMatrix[l1][c1].getCor().equals(inGameMatrix[l2][c2].getCor())
            || !areNeighbours(l1, c1, l2, c2)) {
            System.out.println("primeiro if" + inGameMatrix[l1][c1].getCor() + inGameMatrix[l2][c2].getCor() );
            return false;
        }

        int cont = 0;
        //testa se o bloco 2 vai formar trinca na linha do bloco 1
        int coluna = c1 + 1;
        while(coluna < inGameMatrix[0].length && inGameMatrix[l1][coluna].getCor() != null ){
            if((inGameMatrix[l1][coluna].getCor().equals(inGameMatrix[l2][c2].getCor()))){
                if(!l1.equals(l2) || coluna != c2){
                    System.out.println("while bloco 2 na linha do bloco 1 de c1 pra direita" + coluna);
                    cont++;
                }
                coluna++;
            }
            else
                coluna = 9;
        }
        if(cont>=2){
            return true;
        }

        coluna = c1 - 1;
        while(coluna >= 0 && inGameMatrix[l1][coluna].getCor() != null){
            if(inGameMatrix[l1][coluna].getCor().equals(inGameMatrix[l2][c2].getCor())){
                if(!l1.equals(l2) || coluna !=c2){
                    System.out.println("while bloco 2 na linha do bloco 1 de c1 pra esquerda" + coluna + " " + c2);
                    cont++;
                }
                coluna--;
            }
            else
                coluna = -1;
        }

        if(cont>=2)
            return true;

        //testa se o bloco 1 vai formar trinca na linha do bloco 2
        cont = 0;
        coluna = c2 + 1;
        while(coluna < inGameMatrix[0].length && inGameMatrix[l2][coluna].getCor() != null){
            if (inGameMatrix[l2][coluna].getCor().equals(inGameMatrix[l1][c1].getCor())){
                if(!l1.equals(l2) || coluna != c1){
                    System.out.println("while bloco 1 na linha do bloco 2 de c2 pra direita" + coluna);
                    cont++;
                }
                coluna++;
            }
            else
                coluna = 9;

        }
        if(cont>=2){
            return true;
        }

        coluna = c2 - 1;
        while(coluna >= 0 && inGameMatrix[l2][coluna].getCor() != null){
            if(inGameMatrix[l2][coluna].getCor().equals(inGameMatrix[l1][c1].getCor())){
                if(!l1.equals(l2) || coluna != c1){
                    System.out.println("while bloco 1 na linha do bloco 2 de c2 pra esquerda" + coluna);
                    cont++;
                }
                coluna--;

            }
            else
                coluna = -1;

        }

        if(cont>=2)
            return true;

        //testa se o bloco 2 vai formar trinca na coluna do bloco 1
        cont = 0;
        int linha = l1 + 1;
        while(linha < inGameMatrix.length && inGameMatrix[linha][c1].getCor() != null){
            if(inGameMatrix[linha][c1].getCor().equals(inGameMatrix[l2][c2].getCor())){
                if(!c1.equals(c2) || linha != l2){
                    System.out.println("while bloco 2 na coluna do bloco 1 de l1 pra baixo" + linha);
                    cont++;
                }
                linha++;
            }
            else
                linha = 9;
        }
        if(cont>=2){
            return true;
        }

        linha = l1 - 1;
        while(linha >= 0 && inGameMatrix[linha][c1].getCor() != null){
            if(inGameMatrix[linha][c1].getCor().equals(inGameMatrix[l2][c2].getCor())){
                if(!c1.equals(c2) || linha != l2){
                    System.out.println("while bloco 2 na coluna do bloco 1 de l1 pra cima" + linha);
                    cont++;
                }
                linha--;
            }
            else
                linha = -1;
        }

        if(cont>=2)
            return true;

        //testa se o bloco 1 vai formar trinca na coluna do bloco 2
        cont=0;
        linha = l2 + 1;
        while(linha < inGameMatrix.length && inGameMatrix[linha][c2].getCor() != null){
            if(inGameMatrix[linha][c2].getCor().equals(inGameMatrix[l1][c1].getCor())){
                if(!c1.equals(c2) || linha != l1){
                    System.out.println("while bloco 1 na coluna do bloco 2 de l2 pra baixo" + linha);
                    cont++;
                }
                linha++;
            }
            else
                linha = 9;
        }
        if(cont>=2){
            return true;
        }

        linha = l2 - 1;
        while(linha >= 0 && inGameMatrix[linha][c2].getCor() != null){
            if(inGameMatrix[linha][c2].getCor().equals(inGameMatrix[l1][c1].getCor())){
                if(!c1.equals(c2) || linha != l1){
                    System.out.println("while bloco 1 na coluna do bloco 2 de l2 pra cima" + linha);
                    cont++;
                }
                linha--;
            }
            else
                linha = -1;
        }

        return cont>=2;
    }

    public int swapTiles(Integer l1, Integer c1, Integer l2, Integer c2) {
        Bloco n = inGameMatrix[l1][c1];
        n.setCurrentPos(new Tuple(l2, c2));
        inGameMatrix[l2][c2].setCurrentPos((new Tuple(l1,c1)));
        inGameMatrix[l1][c1] = inGameMatrix[l2][c2];
        inGameMatrix[l2][c2] = n;
        return findMatches(false);
    }

    public int findMatches(boolean generating){

        ArrayList<Tuple> toBreak = new ArrayList<>();

        for(int linha = 0; linha < 9; linha++){
            for(int coluna = 0; coluna < 9; coluna++){
                if(inGameMatrix[linha][coluna].getCor() != null && coluna < inGameMatrix[0].length-1){
                    int i = 1;
                    int cont = 0;
                    boolean addCurrent = true;
                    Tuple current = new Tuple(linha, coluna);
                    while((coluna+i)<inGameMatrix[0].length
                        && inGameMatrix[linha][coluna+i].getCor() != null
                        && inGameMatrix[linha][coluna+i].getCor().equals(inGameMatrix[linha][coluna].getCor())){
                        cont++;
                        if(cont>=2){
                            Tuple nova = new Tuple(linha, coluna+i);
                            Tuple previous = new Tuple(linha, coluna+i-1);
                            boolean add = true;
                            boolean addPrevious = true;
                            for(Tuple tuple : toBreak){
                                if (tuple.equals(nova)) {
                                    add = false;
                                }
                                if (tuple.equals(previous)) {
                                    addPrevious = false;
                                }
                                if((tuple.equals(current)|| previous.equals(current))){
                                    addCurrent = false;
                                }
                            }
                            if(add){
                                toBreak.add(nova);
                            }
                            if(addPrevious){
                                toBreak.add(previous);
                            }

                        }
                        i++;

                    }
                    if(cont>=2 && addCurrent){
                        toBreak.add(current);
                    }
                }

                //encontrar matches pra baixo
                if(inGameMatrix[linha][coluna].getCor() != null && linha < inGameMatrix.length - 1){
                    int i = 1;
                    int cont = 0;
                    boolean addCurrent = true;
                    Tuple current = new Tuple(linha, coluna);
                    while((linha+i)< inGameMatrix.length
                        && inGameMatrix[linha+i][coluna].getCor() != null
                        && inGameMatrix[linha+i][coluna].getCor().equals(inGameMatrix[linha][coluna].getCor())){
                        cont++;
                        if(cont>=2){
                            Tuple nova = new Tuple(linha+i, coluna);
                            Tuple previous = new Tuple(linha+i-1, coluna);
                            boolean add = true;
                            boolean addPrevious = true;
                            for(Tuple tuple : toBreak){
                                if (tuple.equals(nova)) {
                                    add = false;

                                }
                                if (tuple.equals(previous)) {
                                    addPrevious = false;
                                }
                                if(tuple.equals(current) || previous.equals(current)){
                                    addCurrent = false;
                                }
                            }
                            if(add){
                                toBreak.add(nova);
                            }
                            if(addPrevious){
                                toBreak.add(previous);
                            }
                        }
                        i++;

                    }
                    if(cont>=2 && addCurrent){
                        toBreak.add(current);
                    }
                }

            }
        }

        return breakMatches(toBreak, generating);
    }

    private int breakMatches(ArrayList<Tuple> toBreak, boolean generating){
        ArrayList<Tuple> novos = new ArrayList<>();
        for(Tuple tuple : toBreak){
            if(!generating) {
                if (tuple.coluna > 0) {
                    if (inGameMatrix[tuple.linha][tuple.coluna - 1].getCor() == null) {
                        ((BlocoEspecial) inGameMatrix[tuple.linha][tuple.coluna - 1]).setHits(1);
                        if (((BlocoEspecial) inGameMatrix[tuple.linha][tuple.coluna - 1]).getHits() < 1) {
                            setBrokenBlocoEspecial(((BlocoEspecial) inGameMatrix[tuple.linha][tuple.coluna - 1]).getTipo());
                            inGameMatrix[tuple.linha][tuple.coluna - 1].setCor(-1);
                            inGameMatrix[tuple.linha][tuple.coluna - 1].setBloco(null);
                            Tuple nova = new Tuple(tuple.linha, tuple.coluna - 1);
                            novos.add(nova);
                        }
                    }
                }
                if (tuple.coluna < 8) {
                    if (inGameMatrix[tuple.linha][tuple.coluna + 1].getCor() == null) {
                        ((BlocoEspecial) inGameMatrix[tuple.linha][tuple.coluna + 1]).setHits(1);
                        if (((BlocoEspecial) inGameMatrix[tuple.linha][tuple.coluna + 1]).getHits() < 1) {
                            setBrokenBlocoEspecial(((BlocoEspecial) inGameMatrix[tuple.linha][tuple.coluna + 1]).getTipo());
                            inGameMatrix[tuple.linha][tuple.coluna + 1].setCor(-1);
                            inGameMatrix[tuple.linha][tuple.coluna + 1].setBloco(null);

                            Tuple nova = new Tuple(tuple.linha, tuple.coluna + 1);
                            novos.add(nova);
                        }
                    }
                }
                if (tuple.linha > 0) {
                    if (inGameMatrix[tuple.linha - 1][tuple.coluna].getCor() == null) {
                        ((BlocoEspecial) inGameMatrix[tuple.linha - 1][tuple.coluna]).setHits(1);
                        if (((BlocoEspecial) inGameMatrix[tuple.linha - 1][tuple.coluna]).getHits() < 1) {
                            setBrokenBlocoEspecial(((BlocoEspecial) inGameMatrix[tuple.linha - 1][tuple.coluna]).getTipo());

                            inGameMatrix[tuple.linha - 1][tuple.coluna].setCor(-1);
                            inGameMatrix[tuple.linha - 1][tuple.coluna].setBloco(null);

                            Tuple nova = new Tuple(tuple.linha - 1, tuple.coluna);
                            novos.add(nova);
                        }
                    }
                }
                if (tuple.linha < 8) {
                    if (inGameMatrix[tuple.linha + 1][tuple.coluna].getCor() == null) {
                        ((BlocoEspecial) inGameMatrix[tuple.linha + 1][tuple.coluna]).setHits(1);
                        if (((BlocoEspecial) inGameMatrix[tuple.linha + 1][tuple.coluna]).getHits() < 1) {
                            setBrokenBlocoEspecial(((BlocoEspecial) inGameMatrix[tuple.linha + 1][tuple.coluna]).getTipo());

                            inGameMatrix[tuple.linha + 1][tuple.coluna].setCor(-1);
                            inGameMatrix[tuple.linha + 1][tuple.coluna].setBloco(null);

                            Tuple nova = new Tuple(tuple.linha + 1, tuple.coluna);
                            novos.add(nova);
                        }
                    }
                }
            }
            inGameMatrix[tuple.linha][tuple.coluna].setCor(-1);

            inGameMatrix[tuple.linha][tuple.coluna].setBloco(null);

        }
        toBreak.addAll(novos);
        refillTiles(generating);
        return toBreak.size();
    }

    public void refillTiles(boolean generating){
        for(int linha = inGameMatrix.length - 1; linha >= 0; linha--){
            for(int coluna = inGameMatrix[0].length - 1; coluna >= 0; coluna--){
                if(inGameMatrix[linha][coluna].getCor() != null && inGameMatrix[linha][coluna].getCor() == -1){
                    Tuple descendFrom = getTopTile(linha, coluna, generating);
                    if(descendFrom != null){
                        Bloco onTop = inGameMatrix[descendFrom.linha][descendFrom.coluna];
                        inGameMatrix[descendFrom.linha][descendFrom.coluna] = inGameMatrix[linha][coluna];
                        inGameMatrix[linha][coluna] = null;
                        inGameMatrix[linha][coluna] = onTop;
                        onTop.setCurrentPos(new Tuple(linha, coluna));
                    }
                    else{ //cria um novo bloco se não tiver nenhum em cima
                        Random rand = new Random();
                        int n = rand.nextInt(5);
                        Bloco novo = new Bloco(0, coluna, n);
                        this.inGameMatrix[0][coluna] = novo;
                        refillTiles(generating);
                    }
                }
            }
        }

//        return findMatches();
//        return 0;
    }

    private Tuple getTopTile(Integer linha, Integer coluna, boolean generating){
        if(linha >= 0){ // se não tiver chegado no topo do tabuleiro
            if(generating){
                if(inGameMatrix[linha][coluna].getCor() == null){
                    return getTopTile(linha-1, coluna, generating);
                }
            }

            if(inGameMatrix[linha][coluna].getCor() == null || !inGameMatrix[linha][coluna].getCor().equals(-1)){
                return new Tuple(linha, coluna); //se o bloco atual estiver inteiro, retorna sua posição

            }
            else
                return getTopTile(linha-1, coluna, generating); //se o bloco atual está quebrado, recursivamente busca um bloco inteiro

        }
        else //se estiver no topo do tabuleiro, retorna nulo
            return null;
    }

}
