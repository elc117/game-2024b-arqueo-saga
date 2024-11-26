package io.github.t2paradigmas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.t2paradigmas.level.Level;

import java.util.ArrayList;

public class SelectLevelScreen implements Screen {
    final Main game; //final = constante
    private final Texture background;
    private ArrayList<Sprite> playButtons;
    private ArrayList<Sprite> fossilButtons;
    private ArrayList<Sprite> lockButtons;

    public SelectLevelScreen(Main game) {
        this.game = game;
        background = new Texture("img/bg/fasesmenu.png");
        playButtons = new ArrayList<>();
        fossilButtons = new ArrayList<>();
        lockButtons = new ArrayList<>();
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

        Sprite btn;
        for(Level l : game.levels) {
            switch(l.getLevelNumber()) {
                case 1:
                    btn = new Sprite(new Texture("img/botoes/jogar1.png"));
                    btn.setSize(1.939f, 0.7f);
                    btn.setCenter(2.0045f, 7.151f);
                    playButtons.add(btn);

                    if (l.isPlayed()) {
                        btn = new Sprite(new Texture("img/botoes/fossil1.png"));
                        btn.setSize(1.939f, 0.7f);
                        btn.setCenter(2.0045f, 6.371f);
                        fossilButtons.add(btn);
                    }
                    break;
                case 2:
                    if (l.isPlayed()) {
                        btn = new Sprite(new Texture("img/botoes/jogar2.png"));
                        btn.setSize(1.939f, 0.7f);
                        btn.setCenter(4.6572f, 7.151f);
                        playButtons.add(btn);

                        btn = new Sprite(new Texture("img/botoes/fossil2.png"));
                        btn.setSize(1.939f, 0.7f);
                        btn.setCenter(4.6572f, 6.371f);
                        fossilButtons.add(btn);
                    }
                    else{
                        btn = new Sprite(new Texture("img/botoes/lock2.png"));
                        btn.setSize(1.939f, 0.973f);
                        btn.setCenter(5.0005f, 6.967f);
                        lockButtons.add(btn);
                    }
            }
        }


        for(Sprite b : playButtons) {
            b.draw(game.batch);
        }

        for(Sprite b : fossilButtons) {
            b.draw(game.batch);
        }

        for(Sprite b : lockButtons) {
            b.draw(game.batch);
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
