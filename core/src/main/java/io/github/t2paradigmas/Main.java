package io.github.t2paradigmas;

//import java.awt.*;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
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
    public ArrayList<Question> questions;

    public Integer getTotalScore(){
        return totalScore;
    }
    public void addScore(Integer score){
        totalScore += score;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        levels = new ArrayList<>();
        questions = new ArrayList<>();
        viewport = new FitViewport(10, 10);


        Json json = new Json();
        FileHandle questionsFileHandle = Gdx.files.internal("questions.json");
        String jsonString = questionsFileHandle.readString();
        JsonValue root = new JsonReader().parse(jsonString);

        for(JsonValue questionValue: root.get("questions")){
            Question question = json.fromJson(Question.class, questionValue.toString());
            questions.add(question);
        }

        for(int numLevel = 1; numLevel <= 5; numLevel++) {
            levels.add(LevelConstructor.createLevel(numLevel));
        }


        font = new BitmapFont();
        //font has 15pt, but we need to scale it to our viewport by ratio of viewport height to screen height
        font.setUseIntegerPositions(false);
        font.getData().setScale( 2.3f*viewport.getWorldHeight() / Gdx.graphics.getHeight());

        this.setScreen(new MenuScreen(this));
    }

    public static boolean isClicked(float clickX, float clickY, float x1, float y1, float x2, float y2) {
        return clickX > x1 && clickX < x2 && clickY > y1 && clickY < y2;
    }

    public static String formatPontos(Integer pontos){
        if(pontos < 10)
            return "0000" + pontos;
        if(pontos < 100)
            return "000" + pontos;
        if(pontos < 1000)
            return "00" + pontos;
        if(pontos < 10000)
            return "0" + pontos;
        return "" + pontos;
    }
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
