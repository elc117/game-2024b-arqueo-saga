package io.github.t2paradigmas.blocos;

import com.badlogic.gdx.graphics.Texture;

public class BlocoEspecial extends Bloco{
    private Integer hits;
    private String tipo;
    public BlocoEspecial(Integer linha, Integer coluna, String type, Integer hits) {
        super(linha, coluna, type);
        this.hits = hits;
        this.tipo = type;
    }

    public Integer getHits() {
        return hits;
    }
    public void setHits(Integer hits) {
        this.hits -= hits;
        if(this.hits > 0){
            switch(this.tipo){
                case "pedregulho":
                    this.getBloco().setTexture(new Texture("img/blocos/pedregulho1.png"));
                    break;
                case "rocha":
//                    System.out.println(3-hits);
                    this.getBloco().setTexture(new Texture("img/blocos/rocha" + (3-this.hits) + ".png"));
                    break;
            }
        }

    }
    public String getTipo() {
        return tipo;
    }
}
