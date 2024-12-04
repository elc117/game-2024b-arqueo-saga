package io.github.t2paradigmas.level;

public class LevelConstructor  {
    public static Level createLevel(Integer numLevel){
        Level level = null;
        int[][] matriz =  new int[9][9];
        switch(numLevel){
            case 1:
                matriz[4][3] = 5;
                matriz[4][4] = 5;
                matriz[4][5] = 5;
                level = new Level(1, 2, 3, 0, 0, matriz);

                break;
            case 2:
                for(int coluna = 0; coluna < 9; coluna++){
                    matriz[4][coluna] = 5;
                }
                level = new Level(2, 20, 9, 0, 0, matriz);
                break;
            case 3:
                matriz[3][3] = 6;
                matriz[3][4] = 5;
                matriz[3][5] = 6;
                matriz[4][3] = 5;
                matriz[4][4] = 5;
                matriz[4][5] = 5;
                matriz[5][3] = 6;
                matriz[5][4] = 5;
                matriz[5][5] = 6;
                level = new Level(3, 25, 5, 4, 0, matriz);
                break;
            case 4:
                for(int col = 3; col<6; col++){
                    matriz[3][col] = 6;
                }
                matriz[2][4] = 5;
                matriz[4][2] = 5;
                for(int col = 3; col<7; col++){
                    if(col%2 == 0)
                        matriz[4][col] = 6;
                    else{
                        matriz[4][col] = 5;
                    }
                }
                for(int col = 3; col<6; col++){
                    matriz[5][col] = 6;
                }
                matriz[6][4] = 5;
                level = new Level(4, 30, 5, 8, 0, matriz);
                break;
            case 5:
                matriz[0][0] = 5;
                matriz[0][8] = 5;
                matriz[8][0] = 5;
                matriz[8][8] = 5;
                for(int lin = 0; lin < 9; lin+=8){
                    for(int col = 1; col < 8; col+=4){
                        matriz[lin][col] = 6;
                        matriz[lin][col+1] = 6;
                        matriz[lin][col+2] = 6;
                    }
                }

                for(int lin = 1; lin < 8; lin+=4){
                    for(int col = 0; col < 9; col+=8){
                        matriz[lin][col] = 6;
                        matriz[lin+1][col] = 6;
                        matriz[lin+2][col] = 6;
                    }
                }
                matriz[4][4] = 7;
                level = new Level(5, 30, 4, 24, 1, matriz);
                break;
        }

        return level;
    }
}
