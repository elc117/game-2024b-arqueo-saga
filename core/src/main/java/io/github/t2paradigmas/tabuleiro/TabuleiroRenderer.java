package io.github.t2paradigmas.tabuleiro;


import com.badlogic.gdx.Gdx;
import io.github.t2paradigmas.blocos.Bloco;

public class TabuleiroRenderer {
    private final Tabuleiro tabuleiro;
    private boolean moving;
//    private final Tabuleiro previousTabuleiro;

    public TabuleiroRenderer(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.moving = false;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public boolean getMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public static void moveTile(Bloco tile){
        float delta = Gdx.graphics.getDeltaTime(); // retrieve the current delta
        float speed = 4;
        if(!tile.getInitialized()){
            tile.getBloco().setSize(0.9f, 0.9f);
//            tile.getBloco().setCenter(calculateXCenter(tile.getCurrentPos().getColuna()), calculateYCenter(tile.getCurrentPos().getLinha()));
            tile.getBloco().setCenter(calculateXCenter(tile.getCurrentPos().getColuna()), calculateYCenter(0) );
            tile.setInitialized(true);
        }
//        if(!tile.getCurrentPos().equals(tile.getPreviousPos()) || !tile.getInitialized()){
//            setMoving(true);
            float currentX = tile.getBloco().getX() + 0.45f;
            float currentY = tile.getBloco().getY() + 0.45f;
//            System.out.println();
            if(currentX - calculateXCenter(tile.getCurrentPos().getColuna()) > 0.0001f){
                if(currentX - calculateXCenter(tile.getCurrentPos().getColuna()) < 0.1f) {
                    tile.getBloco().translateX(-currentX + calculateXCenter(tile.getCurrentPos().getColuna()));
                }
                else
                    tile.getBloco().translateX(-speed*delta);
            }
            else if(calculateXCenter(tile.getCurrentPos().getColuna())-currentX > 0.0001f){
                if(- currentX + calculateXCenter(tile.getCurrentPos().getColuna()) < 0.1f) {
                    tile.getBloco().translateX(-currentX + calculateXCenter(tile.getCurrentPos().getColuna()));
                }
                else
                    tile.getBloco().translateX(speed*delta);
            }
            else if(currentY - calculateYCenter(tile.getCurrentPos().getLinha()) > 0.0001f){
                if(currentY - calculateYCenter(tile.getCurrentPos().getLinha()) < 0.1f) {
                    tile.getBloco().translateY(-currentY + calculateYCenter(tile.getCurrentPos().getLinha()));
//                    tile.setInitialized(true);
                }
                else
                    tile.getBloco().translateY(-speed*delta);
            }
            else if(calculateYCenter(tile.getCurrentPos().getLinha())-currentY > 0.0001f){
                if(- currentY + calculateYCenter(tile.getCurrentPos().getLinha()) < 0.1f) {
                    tile.getBloco().translateY(-currentY + calculateYCenter(tile.getCurrentPos().getLinha()));
//                    tile.setInitialized(true);
                }
                else
                    tile.getBloco().translateY(speed*delta);
            }
            else{
//                setMoving(false);
                tile.setPreviousPos(tile.getCurrentPos());
                tile.getBloco().setCenter(calculateXCenter(tile.getCurrentPos().getColuna()), calculateYCenter(tile.getCurrentPos().getLinha()));
            }
//        }
    }

    private static float calculateXCenter(int coluna){
        return 0.9f * coluna + 1.41f;
    }

    private static float calculateYCenter(int linha){
        return 7.865f - 0.9f * linha;
    }
    public void render() {

        for(int coluna = 0; coluna < 9; coluna++) {
            for(int linha = 0; linha < 9; linha++) {
//                System.out.print(tabuleiro.getInGameMatrix()[0][0].getBloco().getX());
                moveTile(tabuleiro.getInGameMatrix()[linha][coluna]);
//                tabuleiro.getInGameMatrix()[linha][coluna].getBloco().setCenter(calculateXCenter(coluna), calculateYCenter(linha));
            }
        }
    }
}
