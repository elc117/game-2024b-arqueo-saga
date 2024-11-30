package io.github.t2paradigmas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.t2paradigmas.blocos.Bloco;
import io.github.t2paradigmas.level.Level;
import io.github.t2paradigmas.tabuleiro.TabuleiroRenderer;
import io.github.t2paradigmas.utilitarios.Tuple;


public class GameScreen implements Screen {
    final Main game;
    private final Texture background;
    private Level level;
    private Vector2 clickPos;
    private TabuleiroRenderer tabRender;
    private Tuple selectedBloco;



    public GameScreen(Main game, Level level) {
        this.game = game;
        background = new Texture("img/bg/tabuleiro.png");
        this.level = level;
        clickPos = new Vector2();
        tabRender = new TabuleiroRenderer(level.getTabuleiro());
        selectedBloco = null;
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        draw();
        input();
    }

    private void draw() {
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();

        game.batch.draw(background, 0, 0, worldWidth, worldHeight);
        game.font.draw(game.batch, String.format("%05d", level.getScore()), 5, 0.8f);

        tabRender.render();

        for(int linha = 0; linha < 9; linha++) {
            for(int coluna = 0; coluna < 9; coluna++) {
                level.getTabuleiro().getInGameMatrix()[linha][coluna].getBloco().draw(game.batch);
            }
        }
        game.batch.end();

    }

    private void input() {
        clickPos.set(Gdx.input.getX(), Gdx.input.getY()); //pega as coordenadas do clique
        game.viewport.unproject(clickPos); //converte para as unidades do viewport
        Bloco[][] inGameMatrix = level.getTabuleiro().getInGameMatrix();
        Rectangle rectFirst = inGameMatrix[0][0].getBloco().getBoundingRectangle();
        Rectangle rectLast = inGameMatrix[inGameMatrix.length - 1][inGameMatrix[0].length - 1].getBloco().getBoundingRectangle();


        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            if(clickPos.x > rectFirst.x && clickPos.x < rectLast.x + rectLast.width && clickPos.y < rectFirst.y + rectFirst.height && clickPos.y > rectLast.y) {
                Integer linha = (int) (-(clickPos.y - 8.315)/0.9);
                Integer coluna = (int) ((clickPos.x - 0.96)/0.9);
                if(selectedBloco != null) {
                    if(inGameMatrix[linha][coluna].equals(inGameMatrix[selectedBloco.linha][selectedBloco.coluna])) {
                        selectedBloco = null;
                    }
                    else{
                        if(level.getTabuleiro().isSwapPossible(selectedBloco.linha, selectedBloco.coluna, linha, coluna)){
                            int totalBroken = level.getTabuleiro().swapTiles(selectedBloco.linha, selectedBloco.coluna, linha, coluna);
                            level.setScore(calcularPontuacao(totalBroken));
                            while(totalBroken > 0) {
                                totalBroken = level.getTabuleiro().findMatches(false);
                                System.out.println(totalBroken);
                                level.setScore(calcularPontuacao(totalBroken));
                            }
                        }
                        selectedBloco = null;
                    }
                }
                else
                    selectedBloco = new Tuple(linha, coluna);
            }
        }


    }

    private int calcularPontuacao(int broken){
        return (broken-3)*50 + 300;
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
