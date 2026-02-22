package com.model;

import java.util.Objects;

public class Personagem {

    private String nome;
    private TipoClasse classe;
    private int ataque;
    private int vida;
    private Bolsa bolsa;
    private int defesa;
    private int xp;
    private int nivel;
    private int vidaMaxima;

    public Personagem(String nome, TipoClasse classe) {
        this.nome = nome;
        this.classe = Objects.requireNonNull(classe, "A classe nao pode ser nula.");
        this.vidaMaxima = classe.getVida();
        this.vida = this.vidaMaxima;
        this.ataque = classe.getAtaque();
        this.defesa = classe.getDefesa();
        this.bolsa = new Bolsa();
        this.xp = 0;
        this.nivel = 1;
    }

    public Personagem(String nome, TipoClasse classe, int ataque, int defesa, int vida, int vidaMaxima, int xp, int nivel) {
        this.nome = nome;
        this.classe = Objects.requireNonNull(classe, "A classe nao pode ser nula.");
        this.ataque = ataque;
        this.defesa = defesa;
        this.vidaMaxima = vidaMaxima;
        this.vida = Math.max(0, Math.min(vida, vidaMaxima));
        this.bolsa = new Bolsa();
        this.xp = xp;
        this.nivel = nivel;
    }

    public Bolsa getBolsa() {
        return bolsa;
    }

    public void setVida(int vida) {
        if (vida < 0) {
            this.vida = 0;
        } else if (vida > this.vidaMaxima) {
            this.vida = this.vidaMaxima;
        } else {
            this.vida = vida;
        }
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
        if (this.vida > this.vidaMaxima) {
            this.vida = this.vidaMaxima;
        }
    }

    public boolean isVidaCheia() {
        return this.vida == this.vidaMaxima;
    }

    public String getNome() {
        return nome;
    }

    public void informacoes() {
        System.out.println("Eu sou: " + this.nome + " e minha classe e: " + this.classe);
    }

    public TipoClasse getClasse() {
        return classe;
    }

    public void curar(int cura) {
        setVida(this.vida + cura);
    }

    public int getDefesa() {
        return defesa;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void receberDano(int dano) {
        setVida(this.vida - dano);
        if (vida <= 0) {
            System.out.println("O personagem " + this.nome + " foi derrotado!");
        } else {
            System.out.println("O personagem " + this.nome + " recebeu " + dano + " de dano. Vida restante: " + this.vida);
        }
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int i) {
        this.xp = i;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int i) {
        this.nivel = i;
    }

}
