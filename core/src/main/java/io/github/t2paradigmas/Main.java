package io.github.t2paradigmas;

import java.awt.*;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.t2paradigmas.level.Level;
import io.github.t2paradigmas.level.LevelConstructor;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public FitViewport viewport;
    public ArrayList<Level> levels;
    private  Integer totalScore = 0;

    public Integer getTotalScore(){
        return totalScore;
    }

    @Override
    public void create() {
        batch = new SpriteBatch(); //grupo de sprites
        levels = new ArrayList<>(); // lista de níveis

        for(int numLevel = 1; numLevel <= 5; numLevel++) {
            levels.add(LevelConstructor.createLevel(numLevel)); //utiliza o construtor de niveis para criar a lista de niveis
        }

        // use libGDX's default font
        font = new BitmapFont();

        viewport = new FitViewport(10, 10);

        //font has 15pt, but we need to scale it to our viewport by ratio of viewport height to screen height
        font.setUseIntegerPositions(false);
        font.getData().setScale( 2.3f*viewport.getWorldHeight() / Gdx.graphics.getHeight());

        this.setScreen(new MenuScreen(this));
    }

    private void createFonts(){}

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); // true centers the camera
    }

    @Override
    public void render() {
        super.render(); // important!
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
