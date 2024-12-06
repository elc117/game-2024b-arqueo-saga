package io.github.t2paradigmas.utilitarios;

public enum tipoBloco{
    BASICO(0), TERRA(5), PEDREGULHO(6), ROCHA(7);

    public final int tipo;
    tipoBloco(int i) {
        tipo = i;
    }
}
