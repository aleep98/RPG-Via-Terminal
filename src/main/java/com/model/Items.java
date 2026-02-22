package com.model;

public class Items {
    private String nome;
     enum TipoItem {
        CURA, ATAQUE_TEMPORARIO
    };
    private TipoItem tipo;
    private int efeito;

    public Items(String nome, TipoItem tipo, int efeito){
        this.nome = nome;
        this.tipo = tipo;
        this.efeito = efeito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public int getEfeito() {
        return efeito;
    }

    public void setEfeito(int efeito) {
        this.efeito = efeito;
    }




     public void usarItem(Personagem jogador) {
        if (tipo == TipoItem.CURA) {
            jogador.curar(efeito);
            System.out.println("Voce recuperou " + efeito + " pontos de vida. Vida atual: " + jogador.getVida());
        } else if (tipo == TipoItem.ATAQUE_TEMPORARIO) {
            jogador.setAtaque(jogador.getAtaque() + efeito);
            System.out.println("O item " + nome + " aumentou seu ataque em +" + efeito + "!");
        }
    }
}