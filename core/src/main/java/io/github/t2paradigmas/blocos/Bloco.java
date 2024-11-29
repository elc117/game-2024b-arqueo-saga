package io.github.t2paradigmas.blocos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bloco {
    private Integer linha;
    private Integer coluna;
    private Integer cor;
    private Sprite bloco;

    public Bloco(Integer linha, Integer coluna, Integer cor) {
        this.linha = linha;
        this.coluna = coluna;
        this.cor = cor;
        this.bloco = new Sprite(new Texture("img/blocos/bloco" + (cor + 1) + ".png"));
    }

    public Bloco(Integer linha, Integer coluna, String type ){
        this.linha = linha;
        this.coluna = coluna;
        this.cor = null;
        this.bloco = new Sprite(new Texture("img/blocos/" + type + ".png")); //textura do bloco
    }

    public Integer getLinha() {
        return linha;
    }

    public Integer getColuna() {
        return coluna;
    }

    public Integer getCor() {
        return cor;
    }

    public Sprite getBloco() {
        return bloco;
    }

    public void setLinha(Integer linha) {
        this.linha = linha;
    }

    public void setColuna(Integer coluna) {
        this.coluna = coluna;
    }

    public void setCor(Integer cor) {
        this.cor = cor;
    }

    public void setBloco(Sprite bloco) {
        this.bloco = bloco;
    }

}
