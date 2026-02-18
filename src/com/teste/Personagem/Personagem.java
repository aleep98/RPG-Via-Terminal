package com.teste.Personagem;

import com.teste.TipoClasse.TipoClasse;

public class Personagem {

    private String nome;
    private TipoClasse classe;
    int vida;

    public Personagem(String nome, TipoClasse classe, int vida) {
        this.nome = nome;
        this.classe = classe;
        this.vida = 100;

    }

    public int setVida(int vida) {
        this.vida = vida;
        return vida;
    }

    public int getVida() {
        return vida;
    }

    public String getNome() {
        return nome;
    }

    public void informacoes() {
        System.out.println("Eu sou: " + this.nome + " e minha classe é: " + this.classe);
    }

    public TipoClasse getClasse() {
        return classe;
    }

    public String getArma() {
        switch (classe) {
            case GUERREIRO:
                return "Espada";
            case PALADIN:
                return "Martelo";
            case MAGE:
                return "Cajado";
            default:
                return "Desconhecida";
        }
    }

    public void receberDano(int dano){
        this.vida -= dano;
        if (vida <= 0 ){
            System.out.println("O personagem " + this.nome + " foi derrotado!");
        } else {
            System.out.println("O personagem " + this.nome + " recebeu " + dano + " de dano. Vida restante: " + this.vida);
        }
    }
}
