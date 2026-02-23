package com.model;

public class Inimigo {
    private TipoInimigos tipo;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int recompensaOuro;
    private int recompensaXp;
    private int estagio;
    private int indiceDaFase;

    public Inimigo(TipoInimigos tipo) {
        this(tipo, 1, 1);
    }

    public Inimigo(TipoInimigos tipo, int estagio, int indiceDaFase) {
        this.tipo = tipo;
        this.estagio = Math.max(1, estagio);
        this.indiceDaFase = Math.max(1, indiceDaFase);
        this.vidaMaxima = tipo.calcularVida(this.estagio, this.indiceDaFase);
        this.vida = this.vidaMaxima;
        this.ataque = tipo.calcularAtaque(this.estagio, this.indiceDaFase);
        this.recompensaOuro = tipo.calcularOuro(this.estagio);
        this.recompensaXp = tipo.calcularXp(this.estagio);
    }

    public TipoInimigos getTipoInimigos() {
        return tipo;
    }

    public void atacar() {
        System.out.println("O inimigo " + this.tipo + " esta atacando!");
    }

    public int getAtaque() {
        return ataque;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getRecompensaOuro() {
        return recompensaOuro;
    }

    public int getRecompensaXp() {
        return recompensaXp;
    }

    public int getEstagio() {
        return estagio;
    }

    public int getIndiceDaFase() {
        return indiceDaFase;
    }

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida <= 0) {
            this.vida = 0;
            System.out.println(this.tipo + " foi derrotado!");
        }
    }

    public void setTipo(TipoInimigos tipo) {
        this.tipo = tipo;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
}
