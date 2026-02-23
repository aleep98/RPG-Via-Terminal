package com.model;

public enum TipoClasse {

    GUERREIRO(120, 18, 12, 4.0, 0.10, 1.75),
    PALADIN(140, 14, 18, 3.2, 0.08, 1.60),
    MAGE(95, 22, 8, 6.5, 0.14, 1.90);

    private int vida;
    private int ataque;
    private int defesa;
    private double dpsInicial;
    private double chanceCritica;
    private double multiplicadorCritico;

    TipoClasse(int vida, int ataque, int defesa, double dpsInicial, double chanceCritica, double multiplicadorCritico) {
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.dpsInicial = dpsInicial;
        this.chanceCritica = chanceCritica;
        this.multiplicadorCritico = multiplicadorCritico;
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

    public double getDpsInicial() {
        return dpsInicial;
    }

    public double getChanceCritica() {
        return chanceCritica;
    }

    public double getMultiplicadorCritico() {
        return multiplicadorCritico;
    }
}
