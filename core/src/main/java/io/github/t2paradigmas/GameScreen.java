package io.github.t2paradigmas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.t2paradigmas.blocos.Bloco;
import io.github.t2paradigmas.level.Level;
import io.github.t2paradigmas.tabuleiro.TabuleiroRenderer;
import io.github.t2paradigmas.utilitarios.Tuple;

import java.util.ArrayList;


public class GameScreen implements Screen {
    private final Main game;
    private Sprite background;
    private Level level;
    private Vector2 clickPos;
    private TabuleiroRenderer tabRender;
    private Tuple selectedBloco;
    private final Sprite markSelected;
    private final Sprite sair;
    private boolean moving;
    private ArrayList<Tuple> toBreak;
    private final GlyphLayout layout;
    private boolean isOver;


    public GameScreen(Main game, Level level) {
        this.game = game;
        background = new Sprite(new Texture("img/bg/tabuleiro.png"));
        this.level = level;
        clickPos = new Vector2();
        tabRender = new TabuleiroRenderer(level.getTabuleiro());
        selectedBloco = null;
        markSelected = new Sprite(new Texture("img/blocos/selected.png"));
        markSelected.setSize(0.9f, 0.9f);
        this.moving = false;
        this.sair = new Sprite(new Texture("img/botoes/sair.png"));
        sair.setSize(0.7f, 0.7f);
        sair.setCenter(game.viewport.getWorldHeight() -.35f, game.viewport.getWorldHeight() -.35f);
        this.toBreak = new ArrayList<>();
        layout = new GlyphLayout();
        isOver = false;
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        draw();
        if(!moving) {
            input();
        }
        draw();
        if(!moving) {
            logic();
        }
        draw();
        if(!moving) {
            isOver = isGameOver();
        }
    }

    private void draw() {
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();

        game.batch.draw(background, 0, 0, worldWidth, worldHeight);
        layout.setText(game.font, String.format("%d                                %05d\n\noii", level.getLevelNumber(), level.getScore()), Color.BLACK, 0, Align.left, false);
        game.font.draw(game.batch, layout, 3.715f, 9.588f);
        sair.draw(game.batch);
        System.out.println(background.getTexture().toString());
        if(!isOver) {
            int cont = tabRender.render(toBreak);
            setMoving(cont>0);

            for(int linha = 0; linha < 9; linha++) {
                for(int coluna = 0; coluna < 9; coluna++) {
                    level.getTabuleiro().getInGameMatrix()[linha][coluna].getBloco().draw(game.batch);
                    if(selectedBloco!=null && selectedBloco.getLinha() == linha && selectedBloco.getColuna() == coluna) {
                        markSelected.setCenter(TabuleiroRenderer.calculateXCenter(coluna), TabuleiroRenderer.calculateYCenter(linha));
                        markSelected.draw(game.batch);
                    }
                }
            }
        }

        game.batch.end();

    }

    private void input() {
        clickPos.set(Gdx.input.getX(), Gdx.input.getY()); //pega as coordenadas do clique
        game.viewport.unproject(clickPos); //converte para as unidades do viewport
        Bloco[][] inGameMatrix = level.getTabuleiro().getInGameMatrix();
        Rectangle rectFirst = inGameMatrix[0][0].getBloco().getBoundingRectangle();
        Rectangle rectLast = inGameMatrix[inGameMatrix.length - 1][inGameMatrix[0].length - 1].getBloco().getBoundingRectangle();
        Rectangle rectSair = sair.getBoundingRectangle();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            if(clickPos.x > rectSair.x && clickPos.x < rectSair.x + rectSair.width && clickPos.y > rectSair.y && clickPos.y < rectSair.y + rectSair.height){
                System.out.println(level.getScore());
                level.resetScore();

                game.setScreen(new SelectLevelScreen(game));
            }
            if(clickPos.x > rectFirst.x && clickPos.x < rectLast.x + rectLast.width && clickPos.y < rectFirst.y + rectFirst.height && clickPos.y > rectLast.y) {
                Integer linha = (int) (-(clickPos.y - 8.315)/0.9);
                Integer coluna = (int) ((clickPos.x - 0.96)/0.9);
                if(selectedBloco != null) {
                    if(inGameMatrix[linha][coluna].equals(inGameMatrix[selectedBloco.getLinha()][selectedBloco.getColuna()])) {
                        selectedBloco = null;
                    }
                    else{
                        if(level.getTabuleiro().isSwapPossible(selectedBloco.getLinha(), selectedBloco.getColuna(), linha, coluna)){
                            level.getTabuleiro().swapTiles(selectedBloco.getLinha(), selectedBloco.getColuna(), linha, coluna);
                            toBreak = level.getTabuleiro().findMatches(false);

                        }
                        selectedBloco = null;
                    }
                }
                else
                    selectedBloco = new Tuple(linha, coluna);
            }
        }


    }

    private void logic() {
        int totalBroken = level.getTabuleiro().breakMatches(toBreak, false);
        level.setScore(calcularPontuacao(totalBroken));
//        while(totalBroken > 0) {
        toBreak = level.getTabuleiro().findMatches(false);
//            totalBroken = level.getTabuleiro().breakMatches(toBreak, false);
//            level.setScore(calcularPontuacao(totalBroken));
//        }
    }

    private boolean isGameOver() {
        if(areAllBroken()){
            level.setPlayed(true);
            return true;
        }
        else if(level.getTabuleiro().getAvailableSwaps() == 0 && !areAllBroken()){
            background.setTexture(new Texture("img/bg/gameover.png"));
            return true;
        }
        return false;

    }

    private boolean areAllBroken(){
        return (level.getNumterra().equals(level.getTabuleiro().getBrokenTerra())
                && level.getNumPedregulho().equals(level.getTabuleiro().getBrokenPedregulho())
                && level.getNumRocha().equals(level.getTabuleiro().getBrokenRocha()));
    }

    public boolean getMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private int calcularPontuacao(int broken){
        if(broken > 0)
            return (broken-3)*50 + 300;
        else return 0;
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
