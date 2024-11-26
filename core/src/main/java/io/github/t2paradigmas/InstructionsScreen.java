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


        game.batch.end();
    }

    private void input(){
        clickPos.set(Gdx.input.getX(), Gdx.input.getY()); //pega as coordenadas do clique
        game.viewport.unproject(clickPos); //converte para as unidades do viewport

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Rectangle rectPlay = playButton.getBoundingRectangle(); //área do botão de jogar

            //se o botão de jogar foi clicado
            if(clickPos.x > rectPlay.x && clickPos.x < rectPlay.x + rectPlay.width &&
                    clickPos.y > rectPlay.y && clickPos.y < rectPlay.y + rectPlay.height) {
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
        playButton.getTexture().dispose();
    }
}
