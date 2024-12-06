package io.github.t2paradigmas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.t2paradigmas.level.Level;

import java.util.ArrayList;

public class QuizScreen implements Screen {

    private final Main game;
    private Sprite background;
    private Vector2 clickPos;
    private final ArrayList<Sprite> optionCircle;
    private Question question;
    private Level level;
    private GlyphLayout text;
    private ArrayList<GlyphLayout> options;
    private ArrayList<Rectangle> rectOptions;
    private boolean answered;
    private GlyphLayout answerText;
    private Sprite btnContinuar;
    private Sound soundErro;
    private Sound soundAcerto;

    public QuizScreen(Main game, Level level) {
        this.game = game;
        background = new Sprite(new Texture("img/bg/quiztela.png"));
        clickPos = new Vector2();
        optionCircle = new ArrayList<>();
        this.rectOptions = new ArrayList<>();

        for(int i = 0; i< 4; i++){
            Sprite novo = (new Sprite(new Texture("img/botoes/alternativa.png")));
            novo.setSize(0.949f, 0.676f);
            novo.setCenter(2.436f, 2.647f +(3-i)*1.025f-0.676f/2);
            optionCircle.add(novo);
            rectOptions.add(optionCircle.get(optionCircle.size()-1).getBoundingRectangle());
        }
        this.level = level;
        question = game.questions.get(level.getLevelNumber()-1);
        this.text = new GlyphLayout();
        text.setText(game.font, question.getText(), Color.BLACK, 6.969f, Align.center, true);
        this.options = new ArrayList<>();
        Array<String> letras = new Array<>();
        letras.add("A", "B", "C", "D");
        for(int i = 0; i< question.getOptions().size(); i++){
            String nova =question.getOptions().get(i);
            options.add(new GlyphLayout());
            options.get(options.size()-1).setText(game.font,letras.get(i) + "          " + nova, Color.BLACK, 6.476f, Align.left, true);
        }

        this.answered = false;
        this.answerText = new GlyphLayout();
        btnContinuar = new Sprite(new Texture("img/botoes/continuar" + level.getLevelNumber() + ".png"));
        btnContinuar.setSize(2.683f, 0.95f);
        btnContinuar.setCenter(game.viewport.getWorldWidth()/2, 2.537f - btnContinuar.getHeight()/2);

        if(game.isSoundOn()){
            this.soundAcerto = Gdx.audio.newSound(Gdx.files.internal("audio/acerto.mp3"));
            this.soundErro = Gdx.audio.newSound(Gdx.files.internal("audio/erro.mp3"));
        }
        else{
            this.soundAcerto = null;
            this.soundErro = null;
        }
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

        if(!answered){
            game.font.draw(game.batch, text, 1.526f, 7.588f);

            for (Sprite botao : optionCircle) {
                botao.draw(game.batch);
            }

            for (int i = 0; i < options.size(); i++) {
                game.font.draw(game.batch, options.get(i), 2.301f, 5.529f - i * 1.025f);
            }
        }
        else{
            game.font.draw(game.batch, answerText, 1.5f, game.viewport.getWorldHeight()/2 );
            btnContinuar.draw(game.batch);
        }

        game.batch.end();
        input();
    }

    private void input(){
        clickPos.set(Gdx.input.getX(), Gdx.input.getY());
        game.viewport.unproject(clickPos);

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            if(!answered){
                Integer answer = question.getAnswer();
                for(Rectangle rectangle : rectOptions){
                    if(rectangle.contains(clickPos)){
                        answered = true;
                        if( rectangle == rectOptions.get(answer)){
                            respostaCerta();
                        }
                        else{
                            respostaErrada();
                        }
                    }
                }

            }
            else{
                Rectangle rectContinuar = btnContinuar.getBoundingRectangle();
                if(rectContinuar.contains(clickPos)){
                    game.setScreen(new SelectLevelScreen(game));
                }
            }
        }
    }

    private void respostaCerta(){
        if(!this.question.isAnswered()){
            game.addScore(1000);
            this.question.setAnswered(true);
        }
        background.setTexture(new Texture("img/bg/acerto.png"));
        if(soundAcerto!=null)
            soundAcerto.play();
    }

    private void respostaErrada(){
        background.setTexture(new Texture("img/bg/erro.png"));
        this.answerText.setText(game.font, this.question.getOptions().get(this.question.getAnswer()), Color.BLACK, 6.969f, Align.center, true);
        if(soundErro!=null)
            soundErro.play();
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
        soundAcerto.dispose();
        soundErro.dispose();
    }
}
