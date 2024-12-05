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

public class InstructionsScreen implements Screen {
    private final Main game;
    private final Texture background;
    private final Sprite playButton;
    private final Sprite menuButton;
    Vector2 clickPos;
    public InstructionsScreen(final Main game) {
        this.game = game;

        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        background = new Texture("img/bg/instrucoesTela.png");
        clickPos = new Vector2();
        playButton = new Sprite(new Texture("img/botoes/jogar3.png"));
        playButton.setSize(2.825f, 1);
        playButton.setCenter(worldWidth / 2f, worldHeight /14f);

        menuButton = new Sprite(new Texture("img/botoes/menu.png"));
        menuButton.setSize(0.912f, 0.631f);
        menuButton.setCenter(8.960f + menuButton.getWidth()/2, 9.869f - menuButton.getHeight()/2);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        draw();
        input();
    }

    private void draw(){
        ScreenUtils.clear(Color.BLACK);

        game.batch.begin();

        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();
        game.batch.draw(background, 0, 0, worldWidth, worldHeight);
        playButton.draw(game.batch);
        menuButton.draw(game.batch);


        game.batch.end();
    }

    private void input(){
        clickPos.set(Gdx.input.getX(), Gdx.input.getY());
        game.viewport.unproject(clickPos);

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Rectangle rectPlay = playButton.getBoundingRectangle();
            Rectangle rectMenu = menuButton.getBoundingRectangle();

            if(rectPlay.contains(clickPos)) {
                game.setScreen(new SelectLevelScreen(game));
            }
            else if(rectMenu.contains(clickPos)) {
                game.setScreen(new MenuScreen(game));
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
        playButton.getTexture().dispose();
    }
}
