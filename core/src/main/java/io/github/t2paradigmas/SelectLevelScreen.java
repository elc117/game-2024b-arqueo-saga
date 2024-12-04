package io.github.t2paradigmas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.t2paradigmas.level.Level;

import java.util.ArrayList;

public class SelectLevelScreen implements Screen {
    final Main game; //final = constante
    private final Texture background;
    private Array<Sprite> playButtons;
    private ArrayList<Sprite> fossilButtons;
    private ArrayList<Sprite> lockButtons;
    Vector2 clickPos;

    public SelectLevelScreen(Main game) {
        this.game = game;
        background = new Texture("img/bg/fasesmenu.png");
        playButtons = new Array<>();
        fossilButtons = new ArrayList<>();
        lockButtons = new ArrayList<>();
        clickPos = new Vector2();

        boolean previous = false;

        for(Level l : game.levels) {
            switch(l.getLevelNumber()) {
                case 1:
                    playButtons.add(createButton("jogar1", 2.0045f, 7.151f, 1.939f, 0.7f));
                    if (l.isPlayed()) {
                        fossilButtons.add(createButton("fossil1", 2.0045f, 6.371f, 1.939f, 0.7f));
                        previous = true;
                    }
                    break;
                case 2:
                    if (l.isPlayed()) {
                        playButtons.add(createButton("jogar2", 5.0005f, 7.151f, 1.939f, 0.7f));
                        fossilButtons.add(createButton("fossil2", 5.0005f, 6.371f, 1.939f, 0.7f));

                        previous = true;
                    }
                    else if(!previous){
                        lockButtons.add(createButton("lock2", 5.0005f, 6.969f, 1.939f, 0.973f));

                    }
                    else{
                        playButtons.add(createButton("jogar2", 5.0005f, 7.151f, 1.939f, 0.7f));
                        previous = false;

                    }
                    break;
                case 3:
                    if (l.isPlayed()) {
                        playButtons.add(createButton("jogar3", 8.004f, 7.151f, 1.939f, 0.7f));
                        fossilButtons.add(createButton("fossil3", 8.004f, 6.371f, 1.939f, 0.7f));

                        previous = true;
                    }
                    else if(!previous){
                        lockButtons.add(createButton("lock3", 8.004f, 6.969f, 1.939f, 0.973f));
                    }
                    else{
                        playButtons.add(createButton("jogar3", 8.004f, 7.151f, 1.939f, 0.7f));
                        previous = false;
                    }
                    break;
                case 4:
                    if (l.isPlayed()) {
                        playButtons.add(createButton("jogar4", 3.1925f, 3.251f, 1.939f, 0.7f));
                        fossilButtons.add(createButton("fossil4", 3.1925f, 2.471f, 1.939f, 0.7f));

                        previous = true;
                    }
                    else if(!previous){
                        lockButtons.add(createButton("lock4", 3.1925f, 3.041f, 1.939f, 0.973f));

                    }
                    else{
                        playButtons.add(createButton("jogar4", 3.1925f, 3.251f, 1.939f, 0.7f));
                        previous = false;

                    }
                    break;
                case 5:
                    if (l.isPlayed()) {
                        playButtons.add(createButton("jogar5", 6.8075f, 3.251f, 1.939f, 0.7f));
                        fossilButtons.add(createButton("fossil5", 6.8075f, 2.4711f, 1.939f, 0.7f));

                        previous = true;
                    }
                    else if(!previous){
                        lockButtons.add(createButton("lock5", 6.8075f, 3.041f, 1.939f, 0.973f));

                    }
                    else{
                        playButtons.add(createButton("jogar5", 6.8075f, 3.251f, 1.939f, 0.7f));

                        previous = false;

                    }
                    break;
            }
        }

    }

    private Sprite createButton(String path, float x, float y, float w, float h){
        Sprite btn;
        btn = new Sprite(new Texture("img/botoes/"+ path + ".png"));
        btn.setSize(w, h);
        btn.setCenter(x, y);
        return btn;
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


        for(Sprite b : playButtons) {
            b.draw(game.batch);
        }

        for(Sprite b : fossilButtons) {
            b.draw(game.batch);
        }

        for(Sprite b : lockButtons) {
            b.draw(game.batch);
        }
//        game.font.draw(game.batch, String.format("%05d", game.getTotalScore()), 5, 0.8f);

        game.batch.end();

    }

    private void input() {
        clickPos.set(Gdx.input.getX(), Gdx.input.getY()); //pega as coordenadas do clique
        game.viewport.unproject(clickPos); //converte para as unidades do viewport

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Rectangle rectPlay;

            for(Sprite b : playButtons) {
                rectPlay = b.getBoundingRectangle();
                //se o botão de jogar foi clicado
                if(clickPos.x > rectPlay.x && clickPos.x < rectPlay.x + rectPlay.width &&
                    clickPos.y > rectPlay.y && clickPos.y < rectPlay.y + rectPlay.height) {
                    Level current = game.levels.get(playButtons.indexOf(b, true));
                    current.getTabuleiro().generateBlocos(current.getMatriz());
                    System.out.println(current.getMatriz()[0][0]);
                    current.resetScore();
                    game.setScreen(new GameScreen(game, current));
                }
            }

            for(Sprite b : fossilButtons) {
                rectPlay = b.getBoundingRectangle();
                if(clickPos.x > rectPlay.x && clickPos.x < rectPlay.x + rectPlay.width &&
                    clickPos.y > rectPlay.y && clickPos.y < rectPlay.y + rectPlay.height) {
//                    game.setScreen(new SelectLevelScreen(game));
                    System.out.println(playButtons.indexOf(b, true));
                }
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
        for (Sprite b : playButtons) {
            b.getTexture().dispose();
        }

        for (Sprite b : fossilButtons) {
            b.getTexture().dispose();
        }

        for (Sprite b : lockButtons) {
            b.getTexture().dispose();
        }
    }
}
