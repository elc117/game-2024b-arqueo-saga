package io.github.t2paradigmas.utilitarios;

public class Tuple{
    public int coluna;
    public int linha;
    public Tuple(int x, int y) {
        this.linha = x;
        this.coluna = y;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha(){
        return linha;
    }
    @Override
    public boolean equals(Object o) {
        if ( o == null)
            return false;
        if(o.getClass() != Tuple.class)
            return false;
        Tuple t = (Tuple) o;
        return this.coluna == t.coluna && this.linha == t.linha;
    }
}
