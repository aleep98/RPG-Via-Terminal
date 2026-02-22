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
    private int xpNecessario;

    public Personagem(String nome, TipoClasse classe) {

        this.nome = nome;
        this.classe = Objects.requireNonNull(classe, "A classe não pode ser nula.");
        this.vida = classe.getVida();
        this.ataque = classe.getAtaque();
        this.defesa = classe.getDefesa();
        this.bolsa = new Bolsa();
        this.xp = 0;
        this.nivel = 1;
        this.xpNecessario = 100;
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

    public void setAtaque(int ataque) {
        this.ataque = ataque;
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

     public int getXp() {
        return xp;
     }

     public void setXp(int i) {
        this.xp = i;
     }

     public int getNivel(){
        return nivel;
     }

     public void setNivel(int i){
        this.nivel = i;
     }

     public int getXpNecessario() {
         return xpNecessario;
     }

        public void setXpNecessario(int xpNecessario) {
            this.xpNecessario = xpNecessario;
        }
}
