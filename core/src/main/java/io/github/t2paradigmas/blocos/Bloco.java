package io.github.t2paradigmas.blocos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.t2paradigmas.utilitarios.Tuple;

public class Bloco {
    private Tuple previousPos;
    private Tuple currentPos;
    private Integer cor;
    protected Sprite bloco;
    private boolean initialized;

    public Bloco(Integer linha, Integer coluna, Integer cor) {
        this.previousPos = new Tuple(linha, coluna);
        this.currentPos = new Tuple(linha, coluna);
        this.cor = cor;
        this.bloco = new Sprite(new Texture("img/blocos/bloco" + (cor + 1) + ".png"));
        this.initialized = false;
    }

    public Bloco(Integer linha, Integer coluna, String type ){
        this.previousPos = new Tuple(linha, coluna);
        this.currentPos = new Tuple(linha, coluna);
        this.cor = null;
        this.bloco = new Sprite(new Texture("img/blocos/" + type + ".png")); //textura do bloco
        this.initialized = false;
    }

    public boolean getInitialized() {
        return this.initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
    public Tuple getPreviousPos() {
        return previousPos;
    }

    public Tuple getCurrentPos() {
        return currentPos;
    }

    public void setPreviousPos(Tuple previousPos) {
        this.previousPos = previousPos;
    }

    public void setCurrentPos(Tuple currentPos) {
        this.currentPos = currentPos;
    }
    public Integer getCor() {
        return cor;
    }

    public Sprite getBloco() {
        return bloco;
    }


    public void setCor(Integer cor) {
        this.cor = cor;
    }

    public void setBloco(Sprite bloco) {
        this.bloco = bloco;
    }

}
