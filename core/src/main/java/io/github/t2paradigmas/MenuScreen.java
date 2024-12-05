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

public class MenuScreen implements Screen {
    final Main game; //final = constante
    Texture background;
    private final Sprite playButton;
    private final Sprite instructionsButton;
    Vector2 clickPos;

    public MenuScreen(final Main game) {
        this.game = game;
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        background = new Texture("img/bg/mainmenu.png");
        playButton = new Sprite(new Texture("img/botoes/jogar3.png"));
        instructionsButton = new Sprite (new Texture("img/botoes/instrucoes.png"));

        playButton.setSize(2.825f, 1);
        playButton.setCenter(worldWidth / 2f, worldHeight /2.5f);

        instructionsButton.setSize(2.825f, 1);
        instructionsButton.setCenter(worldWidth / 2f, worldHeight /3.5f);

        clickPos = new Vector2();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        draw();
        input();
    }

    private void input() {
        clickPos.set(Gdx.input.getX(), Gdx.input.getY());
        game.viewport.unproject(clickPos); //converte para as unidades do viewport

        //se o botão esquerdo do mouse é pressionado
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Rectangle rectPlay = playButton.getBoundingRectangle();
            Rectangle rectInstructions = instructionsButton.getBoundingRectangle();

            if(rectPlay.contains(clickPos)) {
                game.setScreen(new SelectLevelScreen(game));

            }
            else if(rectInstructions.contains(clickPos)) {
                game.setScreen(new InstructionsScreen(game));
            }
        }
    }

    private void draw(){
        ScreenUtils.clear(Color.BLACK);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();

        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();


        game.batch.draw(background, 0, 0, worldWidth, worldHeight);
        playButton.draw(game.batch);
        instructionsButton.draw(game.batch);

        game.batch.end();
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
        instructionsButton.getTexture().dispose();
    }
}
