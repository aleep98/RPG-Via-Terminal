package com.teste.Inimigo;

public class Inimigo {
    String nome;
    int vida;
    String tipo;
    int ataque;

    public Inimigo(String nome, int vida, int ataque){
        this.nome = nome;
        this.tipo = "desconhecido";
        this.vida = vida;
        this.ataque = ataque;
    }


    public String getNome() {
        return nome;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public void atacar(){
        System.out.println("O inimigo " + this.nome + " está atacando! ");
    }

    public int getAtaque(){
        return ataque;
    }


    public int getVida() {
        return vida;
    }

    public void receberDano(int dano) {
    this.vida -= dano;
    if (this.vida <= 0) {
        System.out.println(this.nome + " foi derrotado!");
    }
}
}
