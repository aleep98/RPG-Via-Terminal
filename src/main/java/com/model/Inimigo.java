package com.model;

public class Inimigo {
    private TipoInimigos tipo;
    private int vida;
    private int ataque;

    public Inimigo(TipoInimigos tipo) {
        this.tipo = tipo;
        this.vida = tipo.getVida();
        this.ataque = tipo.getAtaque();
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

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida <= 0) {
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
