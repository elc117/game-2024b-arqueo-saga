package io.github.t2paradigmas.tabuleiro;


import io.github.t2paradigmas.blocos.Bloco;

public class TabuleiroRenderer {
    private final Tabuleiro tabuleiro;

    public TabuleiroRenderer(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void render() {
        for(int coluna = 0; coluna < 9; coluna++) {
            for(int linha = 0; linha < 9; linha++) {
                tabuleiro.getInGameMatrix()[linha][coluna].getBloco().setSize(0.9f, 0.9f);
                tabuleiro.getInGameMatrix()[linha][coluna].getBloco().setCenter((0.9f * coluna+1.4f), (8.8f - 0.9f*linha - 0.945f));
            }
        }

    }
}
