package io.github.t2paradigmas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.t2paradigmas.blocos.Bloco;
import io.github.t2paradigmas.level.Level;
import io.github.t2paradigmas.tabuleiro.TabuleiroRenderer;


public class GameScreen implements Screen {
    final Main game;
    private final Texture background;
    private Level level;
    Vector2 clickPos;
    TabuleiroRenderer tabRender;


    public GameScreen(Main game, Level level) {
        this.game = game;
        background = new Texture("img/bg/tabuleiro.png");
        this.level = level;
        clickPos = new Vector2();
        tabRender = new TabuleiroRenderer(level.getTabuleiro());
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

        tabRender.render();

        for(int linha = 0; linha < 9; linha++) {
            for(int coluna = 0; coluna < 9; coluna++) {
                level.getTabuleiro().getInGameMatrix()[linha][coluna].getBloco().draw(game.batch);
            }
        }
        game.batch.end();

    }

    private void input() {

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
