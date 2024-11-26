package io.github.t2paradigmas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements Screen {
    final Main game;
    Texture background;

    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
