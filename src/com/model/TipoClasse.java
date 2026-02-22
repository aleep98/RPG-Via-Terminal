package com.model;

public enum TipoClasse {

    GUERREIRO(100, 15, 10),
    PALADIN(100, 12, 15),
    MAGE(100, 25, 8);

    private int vida;
    private int ataque;
    private int defesa;

    TipoClasse(int vida, int ataque, int defesa) {
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }
}