package io.github.t2paradigmas.level;

public class LevelConstructor  {
    public static Level createLevel(Integer numLevel){
        Level level = null;
        int[][] matriz =  new int[9][9];
        switch(numLevel){
            case 1:
                matriz[3][4] = 1;
                matriz[4][4] = 1;
                matriz[5][4] = 1;
                level = new Level(1, 20, 3, 0, 0, matriz);

                break;
            case 2:
                for(int coluna = 0; coluna < 9; coluna++){
                    matriz[4][coluna] = 1;
                }
                level = new Level(2, 20, 9, 0, 0, matriz);
                break;
            case 3:
                matriz[3][3] = 2;
                matriz[3][4] = 1;
                matriz[3][5] = 2;
                matriz[4][3] = 1;
                matriz[4][4] = 1;
                matriz[4][5] = 1;
                matriz[5][3] = 2;
                matriz[5][4] = 1;
                matriz[5][5] = 2;
                level = new Level(3, 25, 5, 4, 0, matriz);
                break;

        }

        return level;
    }
}
