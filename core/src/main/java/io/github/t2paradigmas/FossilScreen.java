package io.github.t2paradigmas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.t2paradigmas.level.Level;

public class FossilScreen implements Screen {
    private final Main game;
    private Texture background;
    private Level level;
    private Vector2 clickPos;
    private final Sprite btnContinuar;
    private boolean quiz;

    FossilScreen(Main game, Level level, boolean quiz) {
        this.game = game;
        this.level = level;
        clickPos = new Vector2();
        background = new Texture("img/bg/fim" + level.getLevelNumber() + ".png");
        btnContinuar = new Sprite(new Texture("img/botoes/continuar" + level.getLevelNumber() + ".png"));
        btnContinuar.setSize(2.543f, 0.9f);
        btnContinuar.setCenter(3.729f + 2.543f/2, 1 - 0.9f/2);
        this.quiz = quiz;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0, worldWidth, worldHeight);
        btnContinuar.draw(game.batch);
        game.batch.end();

        input();

    }

    private void input() {
        clickPos.set(Gdx.input.getX(), Gdx.input.getY());
        game.viewport.unproject(clickPos);

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Rectangle rectContinuar = btnContinuar.getBoundingRectangle();
            if(rectContinuar.contains(clickPos)){
                if(quiz)
                    game.setScreen(new QuizScreen(game, level));
                else
                    game.setScreen(new SelectLevelScreen(game));
            }
        }
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
        background.dispose();
        btnContinuar.getTexture().dispose();
    }
}
