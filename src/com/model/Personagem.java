package com.model;

public class Personagem {

    private String nome;
    private TipoClasse classe;
    private int ataque;
    private int vida;
    private Bolsa bolsa;
    private int defesa;

    public Personagem(String nome, TipoClasse classe) {
        this.nome = nome;
        this.classe = classe;
        this.vida = classe.getVida();
        this.ataque = classe.getAtaque();
        this.defesa = classe.getDefesa();
        this.bolsa = new Bolsa();

    }

    public Bolsa getBolsa() {
        return bolsa;

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

    public void curar(int cura) {
        this.vida += cura;
        if (this.vida > 100) {
            this.vida = 100;
        }
    }

    public int getDefesa() {
        return defesa;
    }

    public int getAtaque() {
        return ataque;
    }

     public void receberDano(int dano) {
        this.vida -= dano;
        if (vida <= 0) {
            System.out.println("O personagem " + this.nome + " foi derrotado!");
        } else {
            System.out.println(
                    "O personagem " + this.nome + " recebeu " + dano + " de dano. Vida restante: " + this.vida);
        }
    }

}
