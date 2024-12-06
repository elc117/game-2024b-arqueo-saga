package io.github.t2paradigmas.tabuleiro;


import com.badlogic.gdx.Gdx;
import io.github.t2paradigmas.blocos.Bloco;

public class TabuleiroRenderer {
    private final Tabuleiro tabuleiro;

    public TabuleiroRenderer(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

    }

    public static boolean moveTile(Bloco tile){
        float delta = Gdx.graphics.getDeltaTime(); // retrieve the current delta
        float speed = 2;
        if(!tile.getInitialized()){
            tile.getBloco().setSize(0.9f, 0.9f);
            tile.getBloco().setCenter(calculateXCenter(tile.getCurrentPos().getColuna()), calculateYCenter(0) );
            tile.getBloco().setScale(1);
            tile.setInitialized(true);
        }

            float currentX = tile.getBloco().getX() + 0.45f;
            float currentY = tile.getBloco().getY() + 0.45f;

            if(currentX - calculateXCenter(tile.getCurrentPos().getColuna()) > 0.0001f){
                if(currentX - calculateXCenter(tile.getCurrentPos().getColuna()) < 0.09f) {
                    tile.getBloco().translateX(-currentX + calculateXCenter(tile.getCurrentPos().getColuna()));
                }
                else
                    tile.getBloco().translateX(-speed*delta);
            }
            else if(calculateXCenter(tile.getCurrentPos().getColuna())-currentX > 0.0001f){
                if(- currentX + calculateXCenter(tile.getCurrentPos().getColuna()) < 0.09f) {
                    tile.getBloco().translateX(-currentX + calculateXCenter(tile.getCurrentPos().getColuna()));
                }
                else
                    tile.getBloco().translateX(speed*delta);
            }
            else if(currentY - calculateYCenter(tile.getCurrentPos().getLinha()) > 0.0001f){
                if(currentY - calculateYCenter(tile.getCurrentPos().getLinha()) < 0.09f) {
                    tile.getBloco().translateY(-currentY + calculateYCenter(tile.getCurrentPos().getLinha()));
                }
                else
                    tile.getBloco().translateY(-speed*delta);
            }
            else if(calculateYCenter(tile.getCurrentPos().getLinha())-currentY > 0.0001f){
                if(- currentY + calculateYCenter(tile.getCurrentPos().getLinha()) < 0.09f) {
                    tile.getBloco().translateY(-currentY + calculateYCenter(tile.getCurrentPos().getLinha()));
                }
                else
                    tile.getBloco().translateY(speed*delta);
            }
            else{
                tile.setPreviousPos(tile.getCurrentPos());
                tile.getBloco().setCenter(calculateXCenter(tile.getCurrentPos().getColuna()), calculateYCenter(tile.getCurrentPos().getLinha()));
                return false;
            }
            return true;
    }

    public static float calculateXCenter(int coluna){
        return 0.9f * coluna + 1.41f;
    }

    public static float calculateYCenter(int linha){
        return 7.865f - 0.9f * linha;
    }
    public int render() {
        int cont = 0;
        for(int coluna = 0; coluna < 9; coluna++) {
            for(int linha = 0; linha < 9; linha++) {
                if(moveTile(tabuleiro.getInGameMatrix()[linha][coluna]))
                    cont++;
            }
        }
        return cont;
    }
}
