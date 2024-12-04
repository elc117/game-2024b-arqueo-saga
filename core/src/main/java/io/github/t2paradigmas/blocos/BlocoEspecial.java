package io.github.t2paradigmas.blocos;

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
    }
    public String getTipo() {
        return tipo;
    }
}
